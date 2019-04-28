package shoppee.com.utils;

import shoppee.com.entities.Category;

public class CatelogyTokenResult {

	private String success;
	private String error;
	private Category category;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public CatelogyTokenResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CatelogyTokenResult(String success, String error, Category category) {
		super();
		this.success = success;
		this.error = error;
		this.category = category;
	}

}
