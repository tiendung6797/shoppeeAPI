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
import shoppee.com.utils.StoreTokenResult;

@RestController
@RequestMapping("/store")
public class StoreController {
	@Autowired
	StoreServiceImpl storeService;

	//list store
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<Store>> getAllStore(){
		List<Store> listStore = storeService.findAllStore();
		if(listStore.isEmpty()) {
			ResponseEntity<List<Store>> errorListStore = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return errorListStore;
		}
		return new ResponseEntity<>(listStore, HttpStatus.OK);
	}
	
	//Register a new store
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Store> RegisterStore(@RequestBody(required = false) Store store) {
		List<Store> listStore = storeService.findAllStore();
		boolean checkStoreEmail = LogicHandle.functionCheckStoreEmail(listStore, store);
		boolean checkStoreName = LogicHandle.functionCheckStoreName(listStore, store);
		
		if(checkStoreEmail == true && checkStoreName == true) {
			Store newStore = storeService.saveStore(store);
			StoreTokenResult result = new StoreTokenResult("Đăng kí thành công!", "false", newStore);
			return new ResponseEntity(result, HttpStatus.CREATED);
		} 
		else {
			if (checkStoreEmail == false) {
				String error = "Email đã tồn tại, vui lòng sử dụng email khác!";
				StoreTokenResult result = new StoreTokenResult("false", error, store);
				return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}
			if (checkStoreName == false) {
				String error = "Tên cửa hàng này đã tồn tại, vui lòng sử dụng tên cửa hàng khác!";
				StoreTokenResult result = new StoreTokenResult("false", error, store);
				return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}
			String error = "Có lỗi xảy ra!";
			StoreTokenResult result = new StoreTokenResult("false", error, store);
			return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}
