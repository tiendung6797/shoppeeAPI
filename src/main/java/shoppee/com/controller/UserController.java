package shoppee.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.User;
import shoppee.com.service.UserService;
import shoppee.com.utils.LogicHandle;
import shoppee.com.utils.TokenResult;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("all")
	public ResponseEntity<List<User>> getAll(){
		List<User> listUser = userService.getAllUser();
		if(listUser.size() > 0) {
			return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
		}
		ResponseEntity<List<User>> errorListUser = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return errorListUser;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("add")
	public ResponseEntity<User> addUser(@RequestBody(required = false) User objUser){
		List<User> listUser = userService.getAllUser();
		boolean checkUsername = LogicHandle.functionCheckName(listUser, objUser);
		if(checkUsername == true) {
			User user = userService.addUser(objUser);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}
		TokenResult result = new TokenResult("fail", "Username is existed!!");
		return new ResponseEntity(result, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("update")
}
