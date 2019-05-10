package shoppee.com.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import shoppee.com.entities.Product;

public interface ProductService {
	//void addProductFirst(String pro_name, int store_id, int cat_id);
	void addProduct(Product objPro);
	Product getProductById(Integer id);
	Product getLatestProductOfStore(Integer storeId);
	void deleteProductById(Integer id);
	
	int updateCountView(Integer pro_id);
	int updateCountSelled(Integer pro_id);
	
	List<Product> getAllProductAdmin();
	List<Product> getAllProduct();
	
	List<Product> getProductPaginationAdmin(Pageable pageable);
	List<Product> getProductPagination(Pageable pageable);
	
	List<Product> getAllSaleProduct();
	List<Product> getSaleProductPagination(Pageable pageable);
	List<Product> getAllHotProduct();
	List<Product> getHotProductPagination(Pageable pageable);
	
	List<Product> getStoreProductPagination(Pageable pageable, Integer storeId, Integer proId);
	
	List<Product> getStoreProductPaginationAdmin(Pageable pageable, Integer storeId);
	List<Product> getStoreProductPagination(Pageable pageable, Integer storeId);
	
	List<Product> getProductByCatId(Pageable pageable, Integer catId);
	
}
