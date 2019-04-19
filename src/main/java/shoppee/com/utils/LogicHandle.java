package shoppee.com.utils;

import java.util.List;

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
}
