package shoppee.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.Admin;
import shoppee.com.service.AdminService;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("all")
	public ResponseEntity<List<Admin>> getAll(){
		List<Admin> listAdmin = adminService.getAllAdmin();
		if (listAdmin.size() > 0) {
			return new ResponseEntity<List<Admin>>(listAdmin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}