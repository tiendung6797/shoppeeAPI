package shoppee.com.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.Admin;
import shoppee.com.entities.Role;
import shoppee.com.exception.AppException;
import shoppee.com.payload.JwtAuthenticationResponse;
import shoppee.com.payload.LoginRequest;
import shoppee.com.repository.AdminRepository;
import shoppee.com.repository.RoleRepository;
import shoppee.com.security.JwtTokenProvider;
import shoppee.com.security.UserPrincipal;
import shoppee.com.service.AdminService;
import shoppee.com.utils.AdminTokenResult;
import shoppee.com.utils.LogicHandle;
import shoppee.com.utils.TokenResult;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
    RoleRepository roleRepository;
	
	@Autowired
    AdminRepository adminRepository;

	@Autowired
	private AdminService adminService;
	
	/*@Autowired
	private AdminService adminService1;*/
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;
	    

	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("login")
	public ResponseEntity<Admin> login(@RequestBody Admin objAdmin) {
		if (adminService1.getAdminByNameAndPassword(objAdmin.getUsername(), objAdmin.getPassword()) == null) {
			// Incorrect username or password
			TokenResult result = new TokenResult("False", "Incorrect username or password");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		} else {
			// Get information of user login
			Admin adminLogin = adminService1.getAdminByNameAndPassword(objAdmin.getUsername(), objAdmin.getPassword());
			return new ResponseEntity<Admin>(adminLogin, HttpStatus.OK);
		}
	}*/
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateAdmin(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		loginRequest.getUsername(),
                		loginRequest.getPassword()
                )
        );
		
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
	
	//@Secured("ROLE_ADMIN")
	@PostMapping("add")
	public ResponseEntity<?> addAdmin(Principal userLogin, @RequestBody(required = false) Admin objAdmin) {
		Admin ad = adminRepository.findByUsername(userLogin.getName());
		if (ad.getRole().getroleId() != 1) {
			TokenResult rs = new TokenResult("false", "Không có quyền truy cập");
			return new ResponseEntity(rs, HttpStatus.NOT_ACCEPTABLE);
		}
		
		List<Admin> listAdmin = adminService.getAllAdmin();
		boolean checkUsername = LogicHandle.functionCheckName(listAdmin, objAdmin);
		if (checkUsername == false) {
			AdminTokenResult result = new AdminTokenResult("False", "Username đã tồn tại. Vui lòng nhập lại username!", objAdmin);
			return new ResponseEntity(result, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		Admin admin = new Admin(objAdmin.getUsername(), objAdmin.getPassword());
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		
		Role adminRole = roleRepository.findByRoleName("ROLE_MOD");
                /*.orElseThrow(() -> new AppException("User Role not set."));*/
		admin.setRole(adminRole);
		
		adminService.addAdmin(admin);
		return new ResponseEntity("Thêm tài khoản admin thành công!", HttpStatus.CREATED);
	}
	
	//@Secured("ROLE_ADMIN")
	@GetMapping("all")
	public ResponseEntity<List<Admin>> getAll(Principal userLogin) {
		Admin ad = adminRepository.findByUsername(userLogin.getName());
		if (ad.getRole().getroleId() != 1) {
			TokenResult rs = new TokenResult("false", "Không có quyền truy cập");
			return new ResponseEntity(rs, HttpStatus.NOT_ACCEPTABLE);
		}
		
		List<Admin> listAdmin = adminService.getAllAdmin();
		if (listAdmin.size() > 0) {
			return new ResponseEntity<List<Admin>>(listAdmin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable(value = "id") Integer id) {
		if (adminService.getAdminById(id) == null) {
			TokenResult error = new TokenResult("False", "Không tìm thấy tài khoản admin!!");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		} else {
			Admin objAdmin = adminService.getAdminById(id);
			return new ResponseEntity<Admin>(objAdmin, HttpStatus.OK);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("update/{id}")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin objAdmin, @PathVariable(value = "id") Integer id) {
		Admin oldAdmin = adminService.getAdminById(id);
		if (oldAdmin == null) {
			TokenResult error = new TokenResult("False", "Không tìm thấy tài khoản admin!!");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		} else {
			oldAdmin.setPassword(objAdmin.getPassword());
			oldAdmin.setFullname(objAdmin.getFullname());

			adminService.addAdmin(oldAdmin);
			return new ResponseEntity<Admin>(oldAdmin, HttpStatus.OK);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable(value = "id") Integer id) {
		Admin objAdmin = adminService.getAdminById(id);
		if (objAdmin == null) {
			TokenResult error = new TokenResult("False", "Không tìm thấy tài khoản admin!!");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		} else {
			adminService.deleteAdmin(id);
			TokenResult result = new TokenResult("True", "Xóa tài khoản Admin thành công!!");
			return new ResponseEntity(result, HttpStatus.OK);
		}
	}
}