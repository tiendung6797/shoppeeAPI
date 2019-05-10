package shoppee.com.service;

import java.util.List;

import shoppee.com.entities.Store;

public interface StoreService {
	List<Store> findAllStore();
	List<Store> findStoreById(Integer id);
	
	Store saveStore(Store store);
	Store updateStore(Store store);
	
	public List<Store> listStore();
	
	public Store getStoreByEmail(String name);
	
	public Store getStoreByEmailAndPassword(String email, String password);
	
	public Store getOneById(Integer store_id);
	
	public Store addStore(Store objStore);
	
	public void deleteStoreById(Integer store_id);
	
}
