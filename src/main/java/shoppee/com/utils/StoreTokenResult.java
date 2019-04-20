package shoppee.com.utils;

import shoppee.com.entities.Store;

public class StoreTokenResult {
	private String success;
	private String error;
	private Store store;
	
	public StoreTokenResult() {
		super();
	}

	public StoreTokenResult(String success, String error, Store store) {
		super();
		this.success = success;
		this.error = error;
		this.store = store;
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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	
}
