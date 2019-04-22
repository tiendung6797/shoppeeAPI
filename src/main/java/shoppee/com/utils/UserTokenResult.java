package shoppee.com.utils;

import shoppee.com.entities.User;

public class UserTokenResult {
	private String success;
	private String error;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public UserTokenResult() {
		super();
	}
	public UserTokenResult(String success, String error, User user) {
		super();
		this.success = success;
		this.error = error;
		this.user = user;
	}
	
	
	
}
