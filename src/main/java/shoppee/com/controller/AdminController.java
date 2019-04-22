package shoppee.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.Admin;
import shoppee.com.service.impl.AdminServiceImpl;
import shoppee.com.utils.LogicHandle;
import shoppee.com.utils.TokenResult;
import shoppee.com.utils.AdminTokenResult;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private AdminServiceImpl adminService;

	@GetMapping("all")
	public ResponseEntity<List<Admin>> getAll() {
		List<Admin> listAdmin = adminService.getAllAdmin();
		if (listAdmin.size() > 0) {
			return new ResponseEntity<List<Admin>>(listAdmin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("add")
	public ResponseEntity<Admin> addAdmin(@RequestBody(required = false) Admin objAdmin) {
		List<Admin> listAdmin = adminService.getAllAdmin();
		boolean checkUsername = LogicHandle.functionCheckName(listAdmin, objAdmin);
		if (checkUsername == true) {
			Admin admin = adminService.addAdmin(objAdmin);
			return new ResponseEntity<Admin>(admin, HttpStatus.CREATED);
		}

		AdminTokenResult result = new AdminTokenResult("False", "Username đã tồn tại. Vui lòng nhập lại username!!", objAdmin);
		return new ResponseEntity(result, HttpStatus.UNPROCESSABLE_ENTITY);
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