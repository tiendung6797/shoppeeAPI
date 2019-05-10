package shoppee.com.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppee.com.entities.Store;
import shoppee.com.repository.StoreRepository;
import shoppee.com.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{
	@Autowired
	StoreRepository storeRepository;
	
	@Override
	public List<Store> findAllStore() {
		List<Store> listStore = storeRepository.findAll();
		return listStore;
	}

	@Override
	public List<Store> findStoreById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Store saveStore(Store store) {
		store = storeRepository.save(store);
		return store;
	}

	@Override
	public Store updateStore(Store store) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Store> listStore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store getStoreByEmail(String email) {
		return storeRepository.getStoreByEmail(email);
	}

	@Override
	public Store getStoreByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		Store objStore = storeRepository.getStoreByUsernameAndPassword(email, password);
		return objStore;
	}

	@Override
	public Store getOneById(Integer id) {
		Optional<Store> objStore = storeRepository.findById(id);
		return objStore.get();
	}

	@Override
	public Store addStore(Store objStore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStoreById(Integer id) {
		// TODO Auto-generated method stub
		storeRepository.deleteById(id);
	}
}
