package shoppee.com.service;

import java.util.List;

import shoppee.com.entities.ProductBill;

public interface ProductBillService {
	
	void addProductBill(ProductBill objProductBill);
	
	List<ProductBill> getAllProductBill();
	
	List<ProductBill> getProductByUserAndNoMoney(Integer user_id);
	
	List<ProductBill> getProductByUserAndYesMoney(Integer user_id);
	
	List<ProductBill> getProductByUserAndCancel(Integer user_id);
	
	List<ProductBill> getProductByStore(Integer store_id);
	
	List<ProductBill> getBill();
	
	void updateBillCancel(String bill_number);
	
	void updateBillSuccess(String bill_number);
	
	
}
