package shoppee.com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import shoppee.com.dto.BillDto;
import shoppee.com.dto.ProductBillDto;
import shoppee.com.entities.Product;
import shoppee.com.entities.ProductBill;
import shoppee.com.entities.Store;
import shoppee.com.service.impl.ProductBillServiceImpl;
import shoppee.com.service.impl.ProductServiceImpl;
import shoppee.com.service.impl.StoreServiceImpl;
import shoppee.com.utils.TokenResult;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/productbill")
public class ProductBillController {
	
	@Autowired
	ProductBillServiceImpl productBillService;
	
	@Autowired
	ProductServiceImpl productService;
	
	@Autowired
	StoreServiceImpl storeService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("buy")
	public ResponseEntity<List<ProductBill>> buyItems(@RequestBody String jsonString) throws JsonParseException, JsonMappingException, IOException {

		String str = jsonString.replace("{\"bill\":[", "[");
		String jsonFormat = str.replace("]}", "]");
		
		ObjectMapper mapper = new ObjectMapper();
		List<ProductBill> listProductBill = mapper.readValue(jsonFormat, mapper.getTypeFactory().constructCollectionType(List.class, ProductBill.class));
		
		if(listProductBill.size() > 0) {
			for(ProductBill objProductBill : listProductBill) {
				Product objProduct = productService.getProductById(objProductBill.getPro_id());
				if(objProduct.getSale_price() == 0) {
					ProductBill productBill = new ProductBill(0, objProductBill.getPro_id(), objProductBill.getquantity(), objProduct.getColor(), 
							objProductBill.getSize(), objProductBill.getBill_number(), objProductBill.getUser_id(), objProduct.getStore_id(), objProductBill.getPayment_id(), objProduct.getRegular_price()*objProductBill.getquantity(), 0);
					productBillService.addProductBill(productBill);
				}else {
					ProductBill productBill = new ProductBill(0, objProductBill.getPro_id(), objProductBill.getquantity(), objProduct.getColor(), 
							objProductBill.getSize(), objProductBill.getBill_number(), objProductBill.getUser_id(), objProduct.getStore_id(), objProductBill.getPayment_id(), objProduct.getSale_price()*objProductBill.getquantity(), 0);
					productBillService.addProductBill(productBill);
				}
			}
			return new ResponseEntity<>(HttpStatus.OK);	
		}
		TokenResult error = new TokenResult("False", "");
		return new ResponseEntity(error, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<ProductBill>> getAllProductBill() {
		List<ProductBill> listProductBill = productBillService.getAllProductBill();
		if (listProductBill.size() > 0) {
			return new ResponseEntity<List<ProductBill>>(listProductBill, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("nomoney/{user_id}")
	public ResponseEntity<List<ProductBillDto>> getProductByUserAndNoMoney(@PathVariable(value = "user_id") Integer user_id) {
		List<ProductBillDto> listProductBillNomoney = new ArrayList<ProductBillDto>();
		List<ProductBill> listProductBill = productBillService.getProductByUserAndNoMoney(user_id);
		for(ProductBill objProductBill : listProductBill) {
			Product objProduct = productService.getProductById(objProductBill.getPro_id());
			Store objStore = storeService.getOneById(objProductBill.getStore_id());
			listProductBillNomoney.add(new ProductBillDto(objProductBill.getId(), objProduct, objProductBill.getquantity(), objProductBill.getColor(), objProductBill.getSize(), objProductBill.getBill_number(), 
							objProductBill.getUser_id(), objStore, objProductBill.getPayment_id(), objProductBill.getCost(), objProductBill.getStatus()));
		}
		if (listProductBillNomoney.size() > 0) {
			return new ResponseEntity<List<ProductBillDto>>(listProductBillNomoney, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("yesmoney/{user_id}")
	public ResponseEntity<List<ProductBillDto>> getProductByUserAndYesMoney(@PathVariable(value = "user_id") Integer user_id) {
		List<ProductBillDto> listProductBillYesmoney = new ArrayList<ProductBillDto>();
		List<ProductBill> listProductBill = productBillService.getProductByUserAndYesMoney(user_id);
		for(ProductBill objProductBill : listProductBill) {
			Product objProduct = productService.getProductById(objProductBill.getPro_id());
			Store objStore = storeService.getOneById(objProductBill.getStore_id());
			listProductBillYesmoney.add(new ProductBillDto(objProductBill.getId(), objProduct, objProductBill.getquantity(), objProductBill.getColor(), objProductBill.getSize(), objProductBill.getBill_number(), 
							objProductBill.getUser_id(), objStore, objProductBill.getPayment_id(), objProductBill.getCost(), objProductBill.getStatus()));
		}
		if (listProductBillYesmoney.size() > 0) {
			return new ResponseEntity<List<ProductBillDto>>(listProductBillYesmoney, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("cancel/{user_id}")
	public ResponseEntity<List<ProductBillDto>> getProductByUserAndCancel(@PathVariable(value = "user_id") Integer user_id) {
		List<ProductBillDto> listProductBillCancel = new ArrayList<ProductBillDto>();
		List<ProductBill> listProductBill = productBillService.getProductByUserAndCancel(user_id);
		for(ProductBill objProductBill : listProductBill) {
			Product objProduct = productService.getProductById(objProductBill.getPro_id());
			Store objStore = storeService.getOneById(objProductBill.getStore_id());
			listProductBillCancel.add(new ProductBillDto(objProductBill.getId(), objProduct, objProductBill.getquantity(), objProductBill.getColor(), objProductBill.getSize(), objProductBill.getBill_number(), 
							objProductBill.getUser_id(), objStore, objProductBill.getPayment_id(), objProductBill.getCost(), objProductBill.getStatus()));
		}
		if (listProductBillCancel.size() > 0) {
			return new ResponseEntity<List<ProductBillDto>>(listProductBillCancel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("{store_id}")
	public ResponseEntity<List<ProductBillDto>> getProductByStore(@PathVariable(value = "store_id") Integer store_id) {
		List<ProductBillDto> listProductBillByStore = new ArrayList<ProductBillDto>();
		List<ProductBill> listProductBill = productBillService.getProductByStore(store_id);
		for(ProductBill objProductBill : listProductBill) {
			Product objProduct = productService.getProductById(objProductBill.getPro_id());
			Store objStore = storeService.getOneById(objProductBill.getStore_id());
			listProductBillByStore.add(new ProductBillDto(objProductBill.getId(), objProduct, objProductBill.getquantity(), objProductBill.getColor(), objProductBill.getSize(), objProductBill.getBill_number(), 
							objProductBill.getUser_id(), objStore, objProductBill.getPayment_id(), objProductBill.getCost(), objProductBill.getStatus()));
		}
		if (listProductBillByStore.size() > 0) {
			return new ResponseEntity<List<ProductBillDto>>(listProductBillByStore, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	/*@GetMapping("bill")
	public ResponseEntity<List<ProductBill>> getBill(){
		List<ProductBill> listProductBill = productBillService.getBill();
		
		if (listProductBill.size() > 0) {
			return new ResponseEntity<List<ProductBill>>(listProductBill, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}*/
	
}
