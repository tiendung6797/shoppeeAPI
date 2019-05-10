package shoppee.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppee.com.dto.ProductBillDto;
import shoppee.com.entities.ProductBill;
import shoppee.com.repository.ProductBillRepository;
import shoppee.com.service.ProductBillService;

@Service
public class ProductBillServiceImpl implements ProductBillService{

	@Autowired
	private ProductBillRepository productBillRepository;
	
	@Override
	public void addProductBill(ProductBill objProductBill) {
		// TODO Auto-generated method stub
		productBillRepository.save(objProductBill);
		
	}

	@Override
	public List<ProductBill> getAllProductBill() {
		// TODO Auto-generated method stub
		return (List<ProductBill>) productBillRepository.findAll();
	}

	@Override
	public List<ProductBill> getProductByUserAndNoMoney(Integer user_id) {
		// TODO Auto-generated method stub
		return (List<ProductBill>) productBillRepository.getProductByUserAndNoMoney(user_id);
	}

	@Override
	public List<ProductBill> getProductByUserAndYesMoney(Integer user_id) {
		// TODO Auto-generated method stub
		return (List<ProductBill>) productBillRepository.getProductByUserAndYesMoney(user_id);
	}

	@Override
	public List<ProductBill> getProductByUserAndCancel(Integer user_id) {
		// TODO Auto-generated method stub
		return (List<ProductBill>) productBillRepository.getProductByUserAndCancel(user_id);
	}

	@Override
	public List<ProductBill> getProductByStore(Integer store_id) {
		// TODO Auto-generated method stub
		return (List<ProductBill>) productBillRepository.getProductByStore(store_id);
	}

	@Override
	public List<ProductBill> getBill() {
		// TODO Auto-generated method stub
		return (List<ProductBill>) productBillRepository.getBill();
	}

	@Override
	public void updateBillCancel(String bill_number) {
		// TODO Auto-generated method stub
		productBillRepository.updateBillCancel(bill_number);
	}

	@Override
	public void updateBillSuccess(String bill_number) {
		// TODO Auto-generated method stub
		productBillRepository.updateBillSuccess(bill_number);
	}


}
