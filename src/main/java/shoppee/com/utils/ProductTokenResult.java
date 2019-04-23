package shoppee.com.utils;

import shoppee.com.entities.Product;

public class ProductTokenResult {
	private String success;
	private String error;
	private Product product;
	
	public ProductTokenResult() {
		super();
	}
	
	public ProductTokenResult(String success, String error, Product product) {
		super();
		this.success = success;
		this.error = error;
		this.product = product;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
