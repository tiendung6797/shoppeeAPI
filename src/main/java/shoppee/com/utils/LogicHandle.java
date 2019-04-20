package shoppee.com.utils;

import java.util.List;

import shoppee.com.entities.Store;
import shoppee.com.entities.User;

public class LogicHandle {

	public static boolean functionCheckName(List<User> listUser, User user) {
		if(!listUser.isEmpty() && user != null) {
			for(User objUser : listUser) {
				if(objUser.getEmail().equals(user.getEmail())) {
					return false;
				}
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
}
