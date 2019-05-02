package shoppee.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import shoppee.com.entities.Product;
import shoppee.com.service.impl.ProductServiceImpl;
import shoppee.com.utils.ProductTokenResult;
import shoppee.com.utils.TokenResult;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
	 * get product by pagination
	 * */
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductPagination(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
		      @RequestParam(name = "size", required = false, defaultValue = "8") Integer size,
		      @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort){
		Sort sortable = null;
	    if (sort.equals("ASC")) {
	      sortable = Sort.by("count_view").ascending();
	    }
	    if (sort.equals("DESC")) {
	      sortable = Sort.by("count_view").descending();
	    }
	    Pageable pageable = PageRequest.of(page, size, sortable);
	    List<Product> listProductPagination = productService.getProductPagination(pageable);
		
	    if(listProductPagination.isEmpty()) {
			ResponseEntity<List<Product>> errorList = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return errorList;
		}
		return new  ResponseEntity<>(listProductPagination,HttpStatus.OK);
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
	 * get all sale products
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/sale/all", method = RequestMethod.GET) 
	public ResponseEntity<List<Product>> getAllSaleProduct() {
		List<Product> listPro = productService.getAllSaleProduct();
		if (listPro.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(listPro, HttpStatus.OK);
	}
	
	/*
	 * get sale products pagination
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/sale", method = RequestMethod.GET) 
	public ResponseEntity<List<Product>> getSaleProductPagination(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
		      @RequestParam(name = "size", required = false, defaultValue = "8") Integer size,
		      @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		Sort sortable = null;
	    if (sort.equals("ASC")) {
	      sortable = Sort.by("count_view").ascending();
	    }
	    if (sort.equals("DESC")) {
	      sortable = Sort.by("count_view").descending();
	    }
	    Pageable pageable = PageRequest.of(page, size, sortable);
		List<Product> listSalePro = productService.getSaleProductPagination(pageable);
		
		if (listSalePro.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(listSalePro, HttpStatus.OK);
	}
	
	/*
	 * get all hot products
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/hot/all", method = RequestMethod.GET) 
	public ResponseEntity<List<Product>> getAllHotProduct() {
		List<Product> listPro = productService.getAllHotProduct();
		if (listPro.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(listPro, HttpStatus.OK);
	}
	
	/*
	 * get hot products pagination
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/hot", method = RequestMethod.GET) 
	public ResponseEntity<List<Product>> getHotProductPagination(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
		      @RequestParam(name = "size", required = false, defaultValue = "8") Integer size,
		      @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		Sort sortable = null;
	    if (sort.equals("ASC")) {
	      sortable = Sort.by("count_selled").ascending();
	    }
	    if (sort.equals("DESC")) {
	      sortable = Sort.by("count_selled").descending();
	    }
	    Pageable pageable = PageRequest.of(page, size, sortable);
		List<Product> listPro = productService.getHotProductPagination(pageable);
		
		if (listPro.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(listPro, HttpStatus.OK);
	}
	
	
	/*
	 * get store's products pagination
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/store/{storeId}", method = RequestMethod.GET) 
	public ResponseEntity<List<Product>> getStoreProductPagination(
			@PathVariable("storeId") Integer storeId,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
		    @RequestParam(name = "size", required = false, defaultValue = "8") Integer size,
		    @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		Sort sortable = null;
	    if (sort.equals("ASC")) {
	      sortable = Sort.by("count_selled").ascending();
	    }
	    if (sort.equals("DESC")) {
	      sortable = Sort.by("count_selled").descending();
	    }
	    Pageable pageable = PageRequest.of(page, size, sortable);
		List<Product> listPro = productService.getStoreProductPagination(pageable, storeId);
		
		if (listPro.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(listPro, HttpStatus.OK);
	}
	
	/*
	 * get store's products pagination without current product
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/store/{storeId}/{proId}", method = RequestMethod.GET) 
	public ResponseEntity<List<Product>> getStoreProductPagination(
			@PathVariable("storeId") Integer storeId,
			@PathVariable("proId") Integer proId,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
		    @RequestParam(name = "size", required = false, defaultValue = "8") Integer size,
		    @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		Sort sortable = null;
	    if (sort.equals("ASC")) {
	      sortable = Sort.by("count_selled").ascending();
	    }
	    if (sort.equals("DESC")) {
	      sortable = Sort.by("count_selled").descending();
	    }
	    Pageable pageable = PageRequest.of(page, size, sortable);
		List<Product> listPro = productService.getStoreProductPagination(pageable, storeId, proId);
		
		if (listPro.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(listPro, HttpStatus.OK);
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
	
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseEntity<Product> addProduct (@RequestBody Product product, 
			@RequestParam("size")){
		productService.addProduct(product);
		return new ResponseEntity("Thêm thành công!", HttpStatus.CREATED);
	}*/
	
	
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
//		oldProduct.setLink_main_img(product.getLink_main_img());
		oldProduct.setDescription(product.getDescription());
		oldProduct.setColor(product.getColor());
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
