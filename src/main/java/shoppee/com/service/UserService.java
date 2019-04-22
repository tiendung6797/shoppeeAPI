package shoppee.com.service;

import java.util.List;

import shoppee.com.entities.User;

public interface UserService {

	List<User> getAllUser();
	User addUser(User objUser);
	User getOneById(Integer id);
	void deleteUserById(Integer id);
}
