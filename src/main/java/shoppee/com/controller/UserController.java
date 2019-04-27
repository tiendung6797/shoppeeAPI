package shoppee.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.User;
import shoppee.com.service.UserService;
import shoppee.com.service.impl.UserServiceImpl;
import shoppee.com.utils.LogicHandle;
import shoppee.com.utils.TokenResult;
import shoppee.com.utils.UserTokenResult;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private UserService userService1;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("login")
	public ResponseEntity<User> login(@RequestBody User objUser){
		if(userService1.getUserByNameAndPassword(objUser.getEmail(), objUser.getPassword()) == null) {
			TokenResult result = new TokenResult("False", "Incorrect username or password");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		}else {
			User objUserLogin = userService1.getUserByNameAndPassword(objUser.getEmail(), objUser.getPassword());
			return new ResponseEntity<User>(objUserLogin, HttpStatus.OK);
		}
	}

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
	@PostMapping("register")
	public ResponseEntity<User> addUser(@RequestBody(required = false) User objUser) {
		List<User> listUser = userService.getAllUser();
		boolean checkUsername = LogicHandle.functionCheckName(listUser, objUser);
		if (checkUsername == true) {
			User user = userService.addUser(objUser);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}
		UserTokenResult result = new UserTokenResult("False", "Username hoặc phone đã tồn tại!", objUser);
		return new ResponseEntity(result, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@CrossOrigin()
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer id) {
		if (userService.getOneById(id) == null) {
			TokenResult error = new TokenResult("False", "Không tìm thấy tài khoản User!");
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
			TokenResult result = new TokenResult("False", "Không tìm thấy tài khoản User!");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		} else {
			List<User> listUser = userService.getAllUser();
			boolean checkUsername = LogicHandle.functionCheckName(listUser, objUser);
			if (checkUsername == false) {
				UserTokenResult result = new UserTokenResult("False", "Username hoặc phone đã tồn tại!", objUser);
				return new ResponseEntity(result, HttpStatus.NOT_FOUND);
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
			TokenResult error = new TokenResult("False", "Không tìm thấy tài khoản User!");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		} else {
			userService.deleteUserById(id);
			TokenResult result = new TokenResult("True", "Xóa tài khoản thành công!");
			return new ResponseEntity(result, HttpStatus.OK);
		}
	}
}
