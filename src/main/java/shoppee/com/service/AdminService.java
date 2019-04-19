<<<<<<< HEAD
package shoppee.com.service;

import java.util.List;

import shoppee.com.entities.Admin;

public interface AdminService{

	Admin getAdminByNameAndPassword(String name, String password);
	
	List<Admin> getAllAdmin();
}
=======
package shoppee.com.service;

import java.util.List;

import shoppee.com.entities.Admin;

public interface AdminService{

	public Admin getAdminByNameAndPassword(String name, String password);
	
	List<Admin> getAllAdmin();
}
>>>>>>> branch 'master' of https://github.com/nguyentiendung6797/shoppeeAPI.git
