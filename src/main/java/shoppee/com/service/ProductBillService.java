package shoppee.com.service;

import java.util.List;

import shoppee.com.entities.ProductBill;

public interface ProductBillService {
	
	void addProductBill(ProductBill objProductBill);
	
	List<ProductBill> getAllProductBill();

}
