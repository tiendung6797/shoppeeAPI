package shoppee.com.service;

import java.util.List;

import shoppee.com.entities.Product;

public interface ProductService {
	List<Product> getAllProduct();
	void addProduct(Product objProduct);
	Product getProductById(Integer id);
	void deleteProductById(Integer id);
}
