package shoppee.com.service;

import java.util.List;

import shoppee.com.entities.Admin;

public interface AdminService{

	public Admin getAdminByNameAndPassword(String name, String password);
	
	List<Admin> getAllAdmin();
}
