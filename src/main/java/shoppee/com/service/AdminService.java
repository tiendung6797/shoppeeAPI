package shoppee.com.service;

import java.util.List;

import shoppee.com.entities.Admin;

public interface AdminService{

	Admin getAdminByNameAndPassword(String name, String password);
	
	List<Admin> getAllAdmin();

	Admin addAdmin(Admin objAdmin);
	
	Admin getAdminById(Integer id);
	
	void deleteAdmin(Integer id);
}