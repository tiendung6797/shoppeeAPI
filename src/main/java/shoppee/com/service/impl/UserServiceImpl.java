package shoppee.com.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppee.com.entities.User;
import shoppee.com.repository.UserRepository;
import shoppee.com.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired 
	private UserRepository userRepository;

	@Override
	public List<User> getAllUser() {
		List<User> listUser = userRepository.findAll();
		return listUser;
	}

	@Override
	public User addUser(User objUser) {
		User user = userRepository.save(objUser);
		return user;
	}
	
	@Override
	public User getOneById(Integer id) {
		Optional<User> objUser = userRepository.findById(id);
		return objUser.get();
	}

	@Override
	public void deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Override
	public User getUserByNameAndPassword(String name, String password) {
		// TODO Auto-generated method stub
		User objUser = userRepository.getUserByUsernameAndPassword(name, password);
		
		return objUser;
	}
}
