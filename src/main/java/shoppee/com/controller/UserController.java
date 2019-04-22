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

import shoppee.com.entities.User;
import shoppee.com.service.impl.UserServiceImpl;
import shoppee.com.utils.LogicHandle;
import shoppee.com.utils.TokenResult;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@GetMapping("all")
	public ResponseEntity<List<User>> getAll() {
		List<User> listUser = userService.getAllUser();
		if (listUser.size() > 0) {
			return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
		}
		ResponseEntity<List<User>> errorListUser = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return errorListUser;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("add")
	public ResponseEntity<User> addUser(@RequestBody(required = false) User objUser) {
		List<User> listUser = userService.getAllUser();
		boolean checkUsername = LogicHandle.functionCheckName(listUser, objUser);
		if (checkUsername == true) {
			User user = userService.addUser(objUser);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}
		TokenResult result = new TokenResult("fail", "Username or phone is existed!!", objUser);
		return new ResponseEntity(result, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer id) {
		if (userService.getOneById(id) == null) {
			TokenResult error = new TokenResult("Failed", "Not found this user with this id!!");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		} else {
			User objUser = userService.getOneById(id);
			return new ResponseEntity<User>(objUser, HttpStatus.OK);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("update/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User objUser, @PathVariable(value = "id") Integer id) {
		User oldUser = userService.getOneById(id);
		if (oldUser == null) {
			TokenResult result = new TokenResult("false", "Not found this user with this id!!");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		} else {
			List<User> listUser = userService.getAllUser();
			boolean checkUsername = LogicHandle.functionCheckName(listUser, objUser);
			if (checkUsername == false) {
				TokenResult error = new TokenResult("Failed", "Phone is existed!!");
				return new ResponseEntity(error, HttpStatus.NOT_FOUND);
			} else {
				oldUser.setPassword(objUser.getPassword());
				oldUser.setFullname(objUser.getFullname());
				oldUser.setPhone(objUser.getPhone());
				oldUser.setAddress(objUser.getAddress());

				userService.addUser(oldUser);
				return new ResponseEntity<User>(oldUser, HttpStatus.OK);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Integer id) {
		User objUser = userService.getOneById(id);
		if (objUser == null) {
			TokenResult result = new TokenResult("false", "Not found this user with this id!!");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		} else {
			userService.deleteUserById(id);
			TokenResult result = new TokenResult("True", "Delete User success!!");
			return new ResponseEntity(result, HttpStatus.OK);
		}
	}
}
