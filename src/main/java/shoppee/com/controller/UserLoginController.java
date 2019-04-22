package shoppee.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.User;
import shoppee.com.service.UserService;
import shoppee.com.utils.TokenResult;

@RestController
@RequestMapping("user")
public class UserLoginController {

	
	@Autowired
	private UserService userService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("login")
	public ResponseEntity<User> login(@RequestBody User objUser){
		if(userService.getUserByNameAndPassword(objUser.getEmail(), objUser.getPassword()) == null) {
			TokenResult result = new TokenResult("False", "Incorrect username or password");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		}else {
			User objUserLogin = userService.getUserByNameAndPassword(objUser.getEmail(), objUser.getPassword());
			return new ResponseEntity<User>(objUserLogin, HttpStatus.OK);
		}
	}
}
