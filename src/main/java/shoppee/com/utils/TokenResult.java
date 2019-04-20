package shoppee.com.utils;

import shoppee.com.entities.User;

public class TokenResult {
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
	public TokenResult(String success, String error) {
		super();
		this.success = success;
		this.error = error;
	}
	public TokenResult(String error) {
		super();
		this.error = error;
	}

	public TokenResult() {
		super();
	}
	public TokenResult(String success, String error, User user) {
		super();
		this.success = success;
		this.error = error;
		this.user = user;
	}
	
	
	
}
