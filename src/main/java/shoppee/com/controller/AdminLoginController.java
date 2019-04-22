package shoppee.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.Admin;
import shoppee.com.service.AdminService;
import shoppee.com.utils.TokenResult;

@RestController
@RequestMapping("admin")
public class AdminLoginController {

	@Autowired
	private AdminService adminService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("login")
	public ResponseEntity<Admin> login(@RequestBody Admin objAdmin) {
		if (adminService.getAdminByNameAndPassword(objAdmin.getUsername(), objAdmin.getPassword()) == null) {
			// Incorrect username or password
			TokenResult result = new TokenResult("False", "Incorrect username or password");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		} else {
			// Get information of user login
			Admin adminLogin = adminService.getAdminByNameAndPassword(objAdmin.getUsername(), objAdmin.getPassword());
			return new ResponseEntity<Admin>(adminLogin, HttpStatus.OK);
		}
	}

}