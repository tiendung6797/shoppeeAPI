package shoppee.com.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppee.com.entities.Admin;
import shoppee.com.repository.AdminRepository;
import shoppee.com.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public Admin getAdminByNameAndPassword(String name, String password) {
		Admin adminObj = adminRepository.getAdminByUsernameAndPassword(name, password);
		return adminObj;
	}

	@Override
	public List<Admin> getAllAdmin() {
		List<Admin> listAdmin = adminRepository.findAll();
		return listAdmin;
		
	}
	
	@Override
	public Admin addAdmin(Admin objAdmin) {
		Admin admin = adminRepository.save(objAdmin);
		return admin;
		
	}

	@Override
	public Admin getAdminById(Integer id) {
		Optional<Admin> objAdmin = adminRepository.findById(id);
		return objAdmin.get();
	}

	@Override
	public void deleteAdmin(Integer id) {
		// TODO Auto-generated method stub
		adminRepository.deleteById(id);
	}

}