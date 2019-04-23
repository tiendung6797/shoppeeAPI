package shoppee.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.Product;
import shoppee.com.service.impl.ProductServiceImpl;
import shoppee.com.utils.ProductTokenResult;
import shoppee.com.utils.TokenResult;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductServiceImpl productService;
	
	/*
	 * get all product
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/all", method = RequestMethod.GET) 
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> listPro = productService.getAllProduct();
		if (listPro.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(listPro, HttpStatus.OK);
	}
	
	/*
	 * get product by id
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) 
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
		Product product = productService.getProductById(id);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		else {
			ProductTokenResult result = new ProductTokenResult("False", "Không tìm thấy sản phẩm này!", null);
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	 * add product
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseEntity<Product> addProduct (@RequestBody Product product){
		productService.addProduct(product);
		return new ResponseEntity("Thêm thành công!", HttpStatus.CREATED);
	}
	
	
	/*
	 * update product
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT) 
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, 
			@Valid @RequestBody Product product) {
		
		// kiểm tra có tồn tại product có id trong database hay chưa
		Product oldProduct = productService.getProductById(id);
		if(oldProduct == null) {
			TokenResult result = new TokenResult("False", "Không tìm thấy!");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		}
		
		oldProduct.setPro_name(product.getPro_name());
		oldProduct.setStore_id(product.getStore_id());
		oldProduct.setCat_id(product.getCat_id());
//		oldProduct.setMain_picture(product.getMain_picture());
//		oldProduct.setPictures(product.getPictures());
		oldProduct.setDescription(product.getDescription());
		oldProduct.setColor(product.getColor());
		oldProduct.setSize(product.getSize());
		oldProduct.setQuantity(product.getQuantity());
		oldProduct.setMaterials(product.getMaterials());
		oldProduct.setMade_in(product.getMade_in());
		oldProduct.setSale_product(product.getSale_product());
		oldProduct.setHot_product(product.getHot_product());
		oldProduct.setRegular_price(product.getRegular_price());
		oldProduct.setSale_price(product.getSale_price());
		oldProduct.setCount_selled(product.getCount_selled());
		oldProduct.setCount_view(product.getCount_view());
		oldProduct.setActive(product.getActive());
		
		productService.addProduct(oldProduct);
		
		ProductTokenResult result = new ProductTokenResult("Update sản phẩm thành công!", "False", oldProduct);
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	
	/*
	 * delete product
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") Integer id) {
		Product objProduct = productService.getProductById(id);
		if (objProduct == null) {
			TokenResult result = new TokenResult("False", "Không tìm thấy!");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		} else {
			productService.deleteProductById(id);
			TokenResult result = new TokenResult("Xóa thành công!", "False");
			return new ResponseEntity(result, HttpStatus.OK);
		}
	}
	
	
}
