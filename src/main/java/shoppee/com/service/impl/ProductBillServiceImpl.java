package shoppee.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
