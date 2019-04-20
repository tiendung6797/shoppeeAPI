package shoppee.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.Store;
import shoppee.com.service.impl.StoreServiceImpl;
import shoppee.com.utils.LogicHandle;

@RestController
@RequestMapping("/store")
public class StoreController {
	@Autowired
	StoreServiceImpl storeService;

	//list store
	
	
	//Register a new store
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Store> RegisterStore(@RequestBody(required = false) Store store) {
		List<Store> listStore = storeService.findAllStore();
		boolean checkStoreEmail = LogicHandle.functionCheckStoreEmail(listStore, store);
		boolean checkStoreName = LogicHandle.functionCheckStoreName(listStore, store);
		
		if(checkStoreEmail == true && checkStoreName == true) {
			Store newStore = storeService.saveStore(store);
			return new ResponseEntity<Store>(newStore, HttpStatus.CREATED);
		} 
		else {
			if (checkStoreName == false) {
				String message = "Tên cửa hàng này đã tồn tại, vui lòng sử dụng tên cửa hàng khác!";
				return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
			}
			if (checkStoreEmail == false) {
				String message = "Email đã tồn tại, vui lòng sử dụng email khác!";
				return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
			}
		}
		
		return null;
		
	}
	
	
}
