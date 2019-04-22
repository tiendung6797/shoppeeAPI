package shoppee.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.Store;
import shoppee.com.service.StoreService;
import shoppee.com.utils.TokenResult;

@RestController
@RequestMapping("store")
public class StoreLoginController {

	@Autowired
	private StoreService storeService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("login")
	public ResponseEntity<Store> login(@RequestBody Store objStore){
		if(storeService.getStoreByEmailAndPassword(objStore.getEmail(), objStore.getPassword()) == null){
			TokenResult result = new TokenResult("False", "Incorrect username or password");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		}else {
			Store objStoreLogin = storeService.getStoreByEmailAndPassword(objStore.getEmail(), objStore.getPassword());
			return new ResponseEntity<Store>(objStoreLogin, HttpStatus.OK);
		}
	}
}
