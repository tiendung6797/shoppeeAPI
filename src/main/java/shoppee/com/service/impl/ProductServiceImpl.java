package shoppee.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppee.com.entities.Product;
import shoppee.com.repository.ProductRepository;
import shoppee.com.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository; 

	@Override
	public List<Product> getAllProduct() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public void addProduct(Product objProduct) {
		productRepository.save(objProduct);
	}

	@Override
	public Product getProductById(Integer id) {
		return productRepository.getProductById(id);
	}

	@Override
	public void deleteProductById(Integer id) {
		productRepository.deleteById(id);
	}

}
