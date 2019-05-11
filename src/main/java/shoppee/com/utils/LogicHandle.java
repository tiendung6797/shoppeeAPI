package shoppee.com.utils;

import java.util.List;

import shoppee.com.entities.Admin;
import shoppee.com.entities.Category;
import shoppee.com.entities.Store;
import shoppee.com.entities.User;
import shoppee.com.payload.AdminRequest;

public class LogicHandle {

	public static boolean functionCheckName(List<User> listUser, User user) {
		if(!listUser.isEmpty() && user != null) {
			for(User objUser : listUser) {
				if(objUser.getEmail().equals(user.getEmail()) || objUser.getPhone().equals(user.getPhone())) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean functionCheckPhone(List<User> listUser, String phone) {
		if(!listUser.isEmpty()) {
			for(User objUser : listUser) {
				if(objUser.getPhone().equals(phone)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean functionCheckName(List<Admin> listAdmin, AdminRequest objAdmin) {
		if(!listAdmin.isEmpty() && objAdmin != null) {
			for(Admin admin : listAdmin) {
				if(admin.getUsername().equals(objAdmin.getUser_name()))
					return false;
			}
		}
		return true;
	}
	
	/*check email(username) của store tạo mới có trùng với email có sẵn trong database chưa*/
	public static boolean functionCheckStoreEmail(List<Store> listStore, Store store) {
		if(!listStore.isEmpty() && store != null) {
			for(Store objStore : listStore) {
				if(objStore.getEmail().equals(store.getEmail())) {
					return false;
				}
			}
		}
		return true;
	}
	
	/* 	
	 * check store_name của store tạo mới có trùng với store_name có sẵn trong database chưa 
	 * vì store_name là duy nhất
	*/
	public static boolean functionCheckStoreName(List<Store> listStore, Store store) {
		if(!listStore.isEmpty() && store != null) {
			for(Store objStore : listStore) {
				if(objStore.getStore_name().equals(store.getStore_name())) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean functionCheckStoreAddress(List<Store> listStore, Store store) {
		if(!listStore.isEmpty() && store != null) {
			for(Store objStore : listStore) {
				if(objStore.getAddress().equals(store.getAddress())) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean functionCheckStorePhone(List<Store> listStore, Store store) {
		if(!listStore.isEmpty() && store != null) {
			for(Store objStore : listStore) {
				if(objStore.getPhone().equals(store.getPhone())) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean functionCheckCatName(List<Category> listAllCategory, Category objCategory) {
		if(!listAllCategory.isEmpty() && objCategory != null) {
			for(Category category : listAllCategory) {
				if(objCategory.getCat_name().equals(category.getCat_name())) {
					return false;
				}
			}
		}
		return true;
	}
}
