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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.Store;
import shoppee.com.service.StoreService;
import shoppee.com.service.impl.StoreServiceImpl;
import shoppee.com.utils.LogicHandle;
import shoppee.com.utils.StoreTokenResult;
import shoppee.com.utils.TokenResult;

@RestController
@RequestMapping("store")
public class StoreController {
	@Autowired
	StoreServiceImpl storeService;
	
	@Autowired
	private StoreService storeService1;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("login")
	public ResponseEntity<Store> login(@RequestBody Store objStore){
		if(storeService1.getStoreByEmailAndPassword(objStore.getEmail(), objStore.getPassword()) == null){
			TokenResult result = new TokenResult("False", "Incorrect username or password");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		}else {
			Store objStoreLogin = storeService1.getStoreByEmailAndPassword(objStore.getEmail(), objStore.getPassword());
			return new ResponseEntity<Store>(objStoreLogin, HttpStatus.OK);
		}
	}

	// list store
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Store>> getAllStore() {
		List<Store> listStore = storeService.findAllStore();
		if (listStore.isEmpty()) {
			ResponseEntity<List<Store>> errorListStore = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return errorListStore;
		}
		return new ResponseEntity<>(listStore, HttpStatus.OK);
	}

	// Register a new store
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Store> RegisterStore(@RequestBody(required = false) Store store) {
		List<Store> listStore = storeService.findAllStore();
		boolean checkStoreEmail = LogicHandle.functionCheckStoreEmail(listStore, store);
		boolean checkStoreName = LogicHandle.functionCheckStoreName(listStore, store);
		boolean checkStoreAddress = LogicHandle.functionCheckStoreAddress(listStore, store);
		boolean checkStorePhone = LogicHandle.functionCheckStorePhone(listStore, store);

		if (checkStoreEmail == true && checkStoreName == true) {
			Store newStore = storeService.saveStore(store);
			StoreTokenResult result = new StoreTokenResult( "False", "Đăng kí tài khoản thành công!", newStore);
			return new ResponseEntity(result, HttpStatus.CREATED);
		} else {
			if (checkStoreEmail == false) {
				String error = "Email đã tồn tại, vui lòng sử dụng email khác!";
				StoreTokenResult result = new StoreTokenResult("False", error, store);
				return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}
			if (checkStoreName == false) {
				String error = "Tên cửa hàng này đã tồn tại, vui lòng sử dụng tên cửa hàng khác!";
				StoreTokenResult result = new StoreTokenResult("False", error, store);
				return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}
			if (checkStoreAddress == false) {
				String error = "Đại chỉ cửa hàng này đã tồn tại, vui lòng nhập địa chỉ khác!";
				StoreTokenResult result = new StoreTokenResult("False", error, store);
				return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}
			if (checkStorePhone == false) {
				String error = "Số điện thoại này đã tồn tại, vui lòng nhập số điện thoại khác!";
				StoreTokenResult result = new StoreTokenResult("False", error, store);
				return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}
			String error = "Có lỗi xảy ra!";
			StoreTokenResult result = new StoreTokenResult("False", error, store);
			return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("{id}")
	public ResponseEntity<Store> getStoreById(@PathVariable(value = "id") Integer id) {
		if (storeService.getOneById(id) == null) {
			TokenResult error = new TokenResult("Failed", "Không tìm thấy tài khoản cửa hàng!");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		} else {
			Store objStore = storeService.getOneById(id);
			return new ResponseEntity<Store>(objStore, HttpStatus.OK);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("update/{id}")
	public ResponseEntity<Store> updateStore(@RequestBody Store objStore, @PathVariable(value = "id") Integer id){
		Store oldStore = storeService.getOneById(id);
		if(oldStore ==null) {
			TokenResult error = new TokenResult("Failed", "Không tìm thấy tài khoản cửa hàng!");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		}else {
			List<Store> listStore = storeService.findAllStore();
			boolean checkStoreName = LogicHandle.functionCheckStoreName(listStore, objStore);
			boolean checkStoreAddress = LogicHandle.functionCheckStoreAddress(listStore, objStore);
			boolean checkStorePhone = LogicHandle.functionCheckStorePhone(listStore, objStore);
			
			if (checkStoreName == false) {
				String error = "Tên cửa hàng này đã tồn tại, vui lòng sử dụng tên cửa hàng khác!";
				StoreTokenResult result = new StoreTokenResult("false", error, objStore);
				return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}if(checkStoreAddress == false) {
				String error = "Đại chỉ cửa hàng này đã tồn tại, vui lòng nhập địa chỉ khác!";
				StoreTokenResult result = new StoreTokenResult("false", error, objStore);
				return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}if(checkStorePhone == false) {
				String error = "Số điện thoại này đã tồn tại, vui lòng nhập số điện thoại khác!";
				StoreTokenResult result = new StoreTokenResult("false", error, objStore);
				return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}else {
				oldStore.setPassword(objStore.getPassword());
				oldStore.setStoreowner_name(objStore.getStoreowner_name());
				oldStore.setStore_name(objStore.getStore_name());
				oldStore.setAddress(objStore.getAddress());
				oldStore.setPhone(objStore.getPhone());
				oldStore.setBank_name(objStore.getBank_name());
				oldStore.setBank_id(objStore.getBank_id());
				
				storeService.addStore(oldStore);
				return new ResponseEntity<Store>(oldStore, HttpStatus.OK);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Store> deleteStore(@PathVariable(value = "id") Integer id){
		Store objStore = storeService.getOneById(id);
		if(objStore == null) {
			TokenResult error = new TokenResult("Failed", "Không tìm thấy tài khoản cửa hàng!");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		}else {
			storeService.deleteStoreById(id);
			TokenResult result = new TokenResult("True", "Xóa tài khoản thành công!");
			return new ResponseEntity(result, HttpStatus.OK);
		}
	}
}
