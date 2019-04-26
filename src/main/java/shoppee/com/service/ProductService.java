package shoppee.com.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import shoppee.com.entities.Product;

public interface ProductService {
	List<Product> getAllProduct();
	List<Product> getProductPagination(Pageable pageable);
	List<Product> getAllSaleProduct();
	List<Product> getSaleProductPagination(Pageable pageable);
	List<Product> getAllHotProduct();
	List<Product> getHotProductPagination(Pageable pageable);
	void addProduct(Product objProduct);
	Product getProductById(Integer id);
	void deleteProductById(Integer id);
}
