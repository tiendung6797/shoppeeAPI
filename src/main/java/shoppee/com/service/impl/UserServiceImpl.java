package shoppee.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppee.com.repository.UserRepository;

@Service
public class UserServiceImpl implements {

	@Autowired 
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllUser(){
		List<User> listUser = userRepository.findAll();
		return listUser;
	}
}
