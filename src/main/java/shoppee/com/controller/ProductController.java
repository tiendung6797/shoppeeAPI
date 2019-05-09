package shoppee.com.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import shoppee.com.entities.Category;
import shoppee.com.entities.File;
import shoppee.com.entities.Product;
import shoppee.com.entities.Size;
import shoppee.com.payload.UploadFileResponse;
import shoppee.com.service.FileStorageService;
import shoppee.com.service.impl.CategoryServiceImpl;
import shoppee.com.service.impl.ProductServiceImpl;
import shoppee.com.service.impl.SizeSeviceImpl;
import shoppee.com.utils.ProductTokenResult;
import shoppee.com.utils.TokenResult;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProductController {
	@Autowired
	ProductServiceImpl productService;
	
	@Autowired
	CategoryServiceImpl categoryService;
	
	@Autowired
	SizeSeviceImpl sizeSeviceImpl;
	
	@Autowired
    private FileStorageService fileStorageService;
	
	/*
	 * get all product for admin
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/admin/product/all", method = RequestMethod.GET) 
	public ResponseEntity<List<Product>> getAllProductAdmin() {
		List<Product> listPro = productService.getAllProductAdmin();
		if (listPro.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(listPro, HttpStatus.OK);
	}
	
	/*
	 * get all product for public (without p.active = 0)
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/product/all", method = RequestMethod.GET) 
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> listPro = productService.getAllProduct();
		if (listPro.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(listPro, HttpStatus.OK);
	}
	
	/*
	 * get product by pagination for admin
	 * */
	@RequestMapping(value="/admin/product", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductPaginationAdmin(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
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
	    List<Product> listProductPagination = productService.getProductPaginationAdmin(pageable);
		
	    if(listProductPagination.isEmpty()) {
			ResponseEntity<List<Product>> errorList = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return errorList;
		}
		return new  ResponseEntity<>(listProductPagination,HttpStatus.OK);
	}
	
	/*
	 * get product by pagination for public
	 * */
	@RequestMapping(value="/product", method = RequestMethod.GET)
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
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET) 
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
		Product product = productService.getProductById(id);
		if (product != null) {
			product.setCount_view(product.getCount_view() + 1); 
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		else {
			ProductTokenResult result = new ProductTokenResult("False", "Không tìm thấy sản phẩm này!", null);
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	 * get latest product of store
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/product/latest/{storeId}", method = RequestMethod.GET) 
	public ResponseEntity<Product> getLatestProduct(@PathVariable("storeId") Integer storeId) {
		
		Product product = productService.getLatestProductOfStore(storeId);
		
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		else {
			ProductTokenResult result = new ProductTokenResult("False", "Không tìm thấy sản phẩm này!", null);
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		}
	}
	
	
	/*
	 * get product by cat id
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/product/cat/{catId}", method = RequestMethod.GET) 
	public ResponseEntity<List<Product>> getProductByCatId(@PathVariable("catId") Integer catId,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
		    @RequestParam(name = "size", required = false, defaultValue = "8") Integer size,
		    @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		
		// kiểm tra có tồn tại category có id trong database hay k
		Category cat = categoryService.getCategoryById(catId);
		if(cat == null) {
			TokenResult result = new TokenResult("False", "Không tìm thấy danh mục!");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		}
		
		Sort sortable = null;
	    if (sort.equals("ASC")) {
	      sortable = Sort.by("pro_id").ascending();
	    }
	    if (sort.equals("DESC")) {
	      sortable = Sort.by("pro_id").descending();
	    }
	    Pageable pageable = PageRequest.of(page, size, sortable);
		
		List<Product> listPro = productService.getProductByCatId(pageable, catId);
		if (listPro.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(listPro, HttpStatus.OK);
	}
	
	/*
	 * get all sale products
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/product/sale/all", method = RequestMethod.GET) 
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
	@RequestMapping(value = "/product/sale", method = RequestMethod.GET) 
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
	@RequestMapping(value = "/product/hot/all", method = RequestMethod.GET) 
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
	@RequestMapping(value = "/product/hot", method = RequestMethod.GET) 
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
	 * get store's products pagination for admin
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/admin/product/store/{storeId}", method = RequestMethod.GET) 
	public ResponseEntity<List<Product>> getStoreProductPaginationAdmin(
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
		List<Product> listPro = productService.getStoreProductPaginationAdmin(pageable, storeId);
		
		if (listPro.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(listPro, HttpStatus.OK);
	}
	
	/*
	 * get store's products pagination for public
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/product/store/{storeId}", method = RequestMethod.GET) 
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
	@RequestMapping(value = "/product/store/{storeId}/{proId}", method = RequestMethod.GET) 
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
	@RequestMapping(value="/product/add",method=RequestMethod.POST)
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		
		productService.addProduct(product);
		return new ResponseEntity("Thêm thành công!", HttpStatus.CREATED);
	}
	
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/product/add/{store_id}", method=RequestMethod.POST)
	public ResponseEntity<Product> addProduct (
			@RequestParam("pro_name") String pro_name,
			@RequestParam("cat_id") int cat_id,
			@PathVariable("store_id") int store_id ){
		
		Product product = new Product(pro_name, store_id, cat_id);
		productService.addProduct(product);
		return new ResponseEntity("Thêm thành công!", HttpStatus.CREATED);
	}*/
	
	/*
	 * upfile
	 * */
	@PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, Product product) {
        File dbFile = fileStorageService.storeFile(file, product);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getFile_id())
                .toUriString();

        return new UploadFileResponse(dbFile.getFile_name(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
	
	
	/*
	 * add product 2
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/product/add/{storeId}/{proId}", method = RequestMethod.PUT) 
	public ResponseEntity<Product> addProduct(
			@RequestParam("sizeType") String sizeType,
			@PathVariable("proId") int proId,
			@PathVariable("storeId") int storeId,
			@RequestParam("cat_id") int cat_id,
			@RequestParam("sale_product") int sale_product,
			@RequestParam("hot_product") int hot_product,
			@RequestParam("active") int active,
			@RequestParam("regular_price") double regular_price,
			@RequestParam("sale_price") double sale_price,
			@RequestParam("pro_name") String pro_name,
			@RequestParam("description") String description,
			@RequestParam("color") String color,
			@RequestParam("materials") String materials,
			@RequestParam("made_in") String made_in,
			@RequestParam("size1") Integer size1,
			@RequestParam("size2") Integer size2,
			@RequestParam("size3") Integer size3,
			@RequestParam("size4") Integer size4,
			@RequestParam("size5") Integer size5,
			@RequestParam("listFile") MultipartFile[] files ) {
		
		// kiểm tra có tồn tại product có id trong database hay chưa
		Product oldProduct = productService.getProductById(proId);
		if(oldProduct == null) {
			TokenResult result = new TokenResult("False", "Không tìm thấy!");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		}
		
		oldProduct.setPro_name(pro_name);
		oldProduct.setStore_id(storeId);
		oldProduct.setCat_id(cat_id);
		oldProduct.setDescription(description);
		oldProduct.setColor(color);
		oldProduct.setMaterials(materials);
		oldProduct.setMade_in(made_in);
		oldProduct.setSale_product(sale_product);
		oldProduct.setHot_product(hot_product);
		oldProduct.setRegular_price(regular_price);
		oldProduct.setSale_price(sale_price);
		oldProduct.setActive(active);
		
		productService.addProduct(oldProduct);
		
		ArrayList<Integer> listSizeQuantity = new ArrayList<>();
		listSizeQuantity.add(size1);
		listSizeQuantity.add(size2);
		listSizeQuantity.add(size3);
		listSizeQuantity.add(size4);
		listSizeQuantity.add(size5);
		
		if ("freeSize".equalsIgnoreCase(sizeType)) {
			Size objSize = new Size("free size", size1);
			sizeSeviceImpl.addSize(objSize);
		} else {
			if ("charSize".equalsIgnoreCase(sizeType)) {
				String[] listCharSize = {"S", "M", "L", "XL", "XXL"};
				
				for (int i = 0; i < listCharSize.length; i++) {
					Size objSize = new Size(listCharSize[i], listSizeQuantity.get(i));
					sizeSeviceImpl.addSize(objSize);
				}
				
			} else {
				if ("numberSize".equalsIgnoreCase(sizeType)) {
					String[] listNumberSize = {"28", "29", "30", "31", "32"};
					for (int i = 0; i < listNumberSize.length; i++) {
						Size objSize = new Size(listNumberSize[i], listSizeQuantity.get(i));
						sizeSeviceImpl.addSize(objSize);
					}
				}
			}
		}
		
		// upload file
		Arrays.asList(files)
	        .stream()
	        .map(file -> uploadFile(file, oldProduct))
	        .collect(Collectors.toList());
		
		
		TokenResult result = new TokenResult("Thêm sản phẩm thành công!", "False");
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	/*
	 * update product 
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/product/update/{storeId}/{proId}", method = RequestMethod.PUT) 
	public ResponseEntity<Product> updateProduct(
			@RequestParam("sizeType") String sizeType,
			@PathVariable("proId") int proId,
			@PathVariable("storeId") int storeId,
			@RequestParam("cat_id") int cat_id,
			@RequestParam("sale_product") int sale_product,
			@RequestParam("hot_product") int hot_product,
			@RequestParam("active") int active,
			@RequestParam("regular_price") double regular_price,
			@RequestParam("sale_price") double sale_price,
			@RequestParam("pro_name") String pro_name,
			@RequestParam("description") String description,
			@RequestParam("color") String color,
			@RequestParam("materials") String materials,
			@RequestParam("made_in") String made_in,
			@RequestParam("size1") Integer size1,
			@RequestParam("size2") Integer size2,
			@RequestParam("size3") Integer size3,
			@RequestParam("size4") Integer size4,
			@RequestParam("size5") Integer size5,
			@RequestParam("listFile") MultipartFile[] files ) {
		
		// kiểm tra có tồn tại product có id trong database hay chưa
		Product oldProduct = productService.getProductById(proId);
		if(oldProduct == null) {
			TokenResult result = new TokenResult("False", "Không tìm thấy!");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		}
		
		oldProduct.setPro_name(pro_name);
		oldProduct.setStore_id(storeId);
		oldProduct.setCat_id(cat_id);
		oldProduct.setDescription(description);
		oldProduct.setColor(color);
		oldProduct.setMaterials(materials);
		oldProduct.setMade_in(made_in);
		oldProduct.setSale_product(sale_product);
		oldProduct.setHot_product(hot_product);
		oldProduct.setRegular_price(regular_price);
		oldProduct.setSale_price(sale_price);
		oldProduct.setActive(active);
		
		productService.addProduct(oldProduct);
		
		ArrayList<Integer> listSizeQuantity = new ArrayList<>();
		listSizeQuantity.add(size1);
		listSizeQuantity.add(size2);
		listSizeQuantity.add(size3);
		listSizeQuantity.add(size4);
		listSizeQuantity.add(size5);
		
		if ("freeSize".equalsIgnoreCase(sizeType)) {
			Size objSize = new Size("free size", size1);
			sizeSeviceImpl.addSize(objSize);
		} else {
			if ("charSize".equalsIgnoreCase(sizeType)) {
				String[] listCharSize = {"S", "M", "L", "XL", "XXL"};
				
				for (int i = 0; i < listCharSize.length; i++) {
					Size objSize = new Size(listCharSize[i], listSizeQuantity.get(i));
					sizeSeviceImpl.addSize(objSize);
				}
				
			} else {
				if ("numberSize".equalsIgnoreCase(sizeType)) {
					String[] listNumberSize = {"28", "29", "30", "31", "32"};
					for (int i = 0; i < listNumberSize.length; i++) {
						Size objSize = new Size(listNumberSize[i], listSizeQuantity.get(i));
						sizeSeviceImpl.addSize(objSize);
					}
				}
			}
		}
		
		// upload file
		Arrays.asList(files)
	        .stream()
	        .map(file -> uploadFile(file, oldProduct))
	        .collect(Collectors.toList());
		
		
		TokenResult result = new TokenResult("Update sản phẩm thành công!", "False");
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	/*
	 * update count_view
	 * */
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/product/update/countView/{proId}", method = RequestMethod.PUT) 
	public ResponseEntity<Product> updateCountView(@RequestParam("proId") int proId) {
		
		// kiểm tra có tồn tại product có id trong database hay chưa
		Product oldProduct = productService.getProductById(proId);
		if(oldProduct == null) {
			TokenResult result = new TokenResult("False", "Không tìm thấy!");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		}
		
		oldProduct.setCount_view(oldProduct.getCount_view() + 1);
		
		productService.addProduct(oldProduct);
		
		TokenResult result = new TokenResult("Update sản phẩm thành công!", "False");
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	
	
	 * update count_selled
	 * 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/product/update/countSelled/{proId}", method = RequestMethod.PUT) 
	public ResponseEntity<Product> updateCountSelled(@RequestParam("proId") int proId) {
		
		// kiểm tra có tồn tại product có id trong database hay chưa
		Product oldProduct = productService.getProductById(proId);
		if(oldProduct == null) {
			TokenResult result = new TokenResult("False", "Không tìm thấy!");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		}
		
		oldProduct.setCount_selled(oldProduct.getCount_selled() + 1);
		
		productService.addProduct(oldProduct);
		
		TokenResult result = new TokenResult("Update sản phẩm thành công!", "False");
		return new ResponseEntity(result, HttpStatus.OK);
	}
	*/
	
	/*
	 * delete product
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/product/delete/{proId}", method = RequestMethod.DELETE) 
	public ResponseEntity<Product> deleteProduct(@PathVariable(value = "proId") Integer proId) {
		Product objProduct = productService.getProductById(proId);
		if (objProduct == null) {
			TokenResult result = new TokenResult("False", "Không tìm thấy!");
			return new ResponseEntity(result, HttpStatus.NOT_FOUND);
		} else {
			productService.deleteProductById(proId);
			TokenResult result = new TokenResult("Xóa thành công!", "False");
			return new ResponseEntity(result, HttpStatus.OK);
		}
	}
	
	
	
	
	
	
}
