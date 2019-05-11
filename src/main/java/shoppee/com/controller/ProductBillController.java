package shoppee.com.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.dto.BillDto;
import shoppee.com.dto.Dto;
import shoppee.com.dto.ProductBillDto;
import shoppee.com.entities.Payment;
import shoppee.com.entities.Product;
import shoppee.com.entities.ProductBill;
import shoppee.com.entities.Store;
import shoppee.com.entities.User;
import shoppee.com.service.impl.PaymentServiceImpl;
import shoppee.com.service.impl.ProductBillServiceImpl;
import shoppee.com.service.impl.ProductServiceImpl;
import shoppee.com.service.impl.SizeSeviceImpl;
import shoppee.com.service.impl.StoreServiceImpl;
import shoppee.com.service.impl.UserServiceImpl;
import shoppee.com.utils.TokenResult;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/productbill")
public class ProductBillController {
	
	@Autowired
	private ProductBillServiceImpl productBillService;
	
	@Autowired
	private ProductServiceImpl productService;
	
	@Autowired
	private StoreServiceImpl storeService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private PaymentServiceImpl paymentService;
	
	@Autowired
	private SizeSeviceImpl sizeSevice;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("buy")
	public ResponseEntity<List<ProductBill>> buyItems(@RequestBody String JSON_DATA) throws JSONException {
		
		// pro_id, user_id, bill_number, quantity, size, payment_id, sum_bill

		JSONObject JsonObj  = new JSONObject(JSON_DATA);
		JSONArray listProductBill = JsonObj.getJSONArray("bill");
		double sum_bill = 0;
		if(listProductBill.length() > 0) {
			for(int i = 0; i < listProductBill.length(); i++) {
				final JSONObject objProductBill = listProductBill.getJSONObject(i);
				Product objProduct = productService.getProductById(objProductBill.getInt("pro_id"));
				if(objProduct.getSale_price() == 0) {
					sum_bill += objProduct.getRegular_price()*objProductBill.getInt("quantity");
				}else {
					sum_bill += objProduct.getSale_price()*objProductBill.getInt("quantity");
				}
			}
			for(int i = 0; i < listProductBill.length(); i++) {
				final JSONObject objProductBill = listProductBill.getJSONObject(i);
				Product objProduct = productService.getProductById(objProductBill.getInt("pro_id"));
				productService.updateCountSelled(objProductBill.getInt("pro_id"));
				sizeSevice.updateQuantity(objProductBill.getInt("pro_id"), objProductBill.getString("size"));
				if(objProduct.getSale_price() == 0) {
					if(objProductBill.getInt("payment_id") == 1) {
						ProductBill productBill = new ProductBill(0, objProductBill.getInt("pro_id"), objProduct.getPro_name(), objProductBill.getInt("quantity"), objProduct.getColor(), objProductBill.getString("size"), objProductBill.getString("bill_number"), 
								objProductBill.getInt("user_id"), objProduct.getStore_id(), objProductBill.getInt("payment_id"),objProduct.getRegular_price()*objProductBill.getInt("quantity"), sum_bill, "Chưa thanh toán-Chưa giao hàng");
						productBillService.addProductBill(productBill);
					}else {
						ProductBill productBill = new ProductBill(0, objProductBill.getInt("pro_id"), objProduct.getPro_name(), objProductBill.getInt("quantity"), objProduct.getColor(), objProductBill.getString("size"), objProductBill.getString("bill_number"), 
								objProductBill.getInt("user_id"), objProduct.getStore_id(), objProductBill.getInt("payment_id"), objProduct.getRegular_price()*objProductBill.getInt("quantity"), sum_bill, "Đã thanh toán-Chưa giao hàng");
						productBillService.addProductBill(productBill);
					}
				}else {
					if(objProductBill.getInt("payment_id") == 1) {
						ProductBill productBill = new ProductBill(0, objProductBill.getInt("pro_id"), objProduct.getPro_name(), objProductBill.getInt("quantity"), objProduct.getColor(), objProductBill.getString("size"), objProductBill.getString("bill_number"),
								objProductBill.getInt("user_id"), objProduct.getStore_id(), objProductBill.getInt("payment_id"), objProduct.getSale_price()*objProductBill.getInt("quantity"), sum_bill, "Chưa thanh toán-Chưa giao hàng");
						productBillService.addProductBill(productBill);
					}else {
						ProductBill productBill = new ProductBill(0, objProductBill.getInt("pro_id"), objProduct.getPro_name(), objProductBill.getInt("quantity"), objProduct.getColor(), objProductBill.getString("size"), objProductBill.getString("bill_number"),
								objProductBill.getInt("user_id"), objProduct.getStore_id(), objProductBill.getInt("payment_id"), objProduct.getSale_price()*objProductBill.getInt("quantity"), sum_bill, "Đã thanh toán-Chưa giao hàng");
						productBillService.addProductBill(productBill);
					}
				}
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
		}
		TokenResult error = new TokenResult("False", "");
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
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
			listProductBillNomoney.add(new ProductBillDto(objProductBill.getId(), objProduct, objProductBill.getQuantity(), objProductBill.getColor(), objProductBill.getSize(), objProductBill.getBill_number(), 
							objProductBill.getUser_id(), objStore, objProductBill.getPayment_id(), objProductBill.getCost(), objProductBill.getStatus()));
		}
		if (listProductBillNomoney.size() > 0) {
			return new ResponseEntity<List<ProductBillDto>>(listProductBillNomoney, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ProductBillDto>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("yesmoney/{user_id}")
	public ResponseEntity<List<ProductBillDto>> getProductByUserAndYesMoney(@PathVariable(value = "user_id") Integer user_id) {
		List<ProductBillDto> listProductBillYesmoney = new ArrayList<ProductBillDto>();
		List<ProductBill> listProductBill = productBillService.getProductByUserAndYesMoney(user_id);
		for(ProductBill objProductBill : listProductBill) {
			Product objProduct = productService.getProductById(objProductBill.getPro_id());
			Store objStore = storeService.getOneById(objProductBill.getStore_id());
			listProductBillYesmoney.add(new ProductBillDto(objProductBill.getId(), objProduct, objProductBill.getQuantity(), objProductBill.getColor(), objProductBill.getSize(), objProductBill.getBill_number(), 
							objProductBill.getUser_id(), objStore, objProductBill.getPayment_id(), objProductBill.getCost(), objProductBill.getStatus()));
		}
		if (listProductBillYesmoney.size() > 0) {
			return new ResponseEntity<List<ProductBillDto>>(listProductBillYesmoney, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ProductBillDto>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("cancel/{user_id}")
	public ResponseEntity<List<ProductBillDto>> getProductByUserAndCancel(@PathVariable(value = "user_id") Integer user_id) {
		List<ProductBillDto> listProductBillCancel = new ArrayList<ProductBillDto>();
		List<ProductBill> listProductBill = productBillService.getProductByUserAndCancel(user_id);
		for(ProductBill objProductBill : listProductBill) {
			Product objProduct = productService.getProductById(objProductBill.getPro_id());
			Store objStore = storeService.getOneById(objProductBill.getStore_id());
			listProductBillCancel.add(new ProductBillDto(objProductBill.getId(), objProduct, objProductBill.getQuantity(), objProductBill.getColor(), objProductBill.getSize(), objProductBill.getBill_number(), 
							objProductBill.getUser_id(), objStore, objProductBill.getPayment_id(), objProductBill.getCost(), objProductBill.getStatus()));
		}
		if (listProductBillCancel.size() > 0) {
			return new ResponseEntity<List<ProductBillDto>>(listProductBillCancel, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ProductBillDto>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("{store_id}")
	public ResponseEntity<List<ProductBillDto>> getProductByStore(@PathVariable(value = "store_id") Integer store_id) {
		List<ProductBillDto> listProductBillByStore = new ArrayList<ProductBillDto>();
		List<ProductBill> listProductBill = productBillService.getProductByStore(store_id);
		for(ProductBill objProductBill : listProductBill) {
			Product objProduct = productService.getProductById(objProductBill.getPro_id());
			Store objStore = storeService.getOneById(objProductBill.getStore_id());
			listProductBillByStore.add(new ProductBillDto(objProductBill.getId(), objProduct, objProductBill.getQuantity(), objProductBill.getColor(), objProductBill.getSize(), objProductBill.getBill_number(), 
							objProductBill.getUser_id(), objStore, objProductBill.getPayment_id(), objProductBill.getCost(), objProductBill.getStatus()));
		}
		if (listProductBillByStore.size() > 0) {
			return new ResponseEntity<List<ProductBillDto>>(listProductBillByStore, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ProductBillDto>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("cancel/{bill_number}")
	public void updateBillCancel(@PathVariable(value = "bill_number") String bill_number) {
		productBillService.updateBillCancel(bill_number);
	}
	
	@PutMapping("success/{bill_number}")
	public void updateBillSuccess(@PathVariable(value = "bill_number") String bill_number) {
		productBillService.updateBillSuccess(bill_number);
	}
	
	@GetMapping("byBill")
	public ResponseEntity<List<BillDto>> getByBill(){
		List<BillDto> listByBill = new ArrayList<BillDto>();
		List<ProductBill> listProductBill = productBillService.getBill();
		for(ProductBill objProductBill : listProductBill) {
			User objUser = userService.getOneById(objProductBill.getUser_id());
			Payment objPayment = paymentService.getPaymentById(objProductBill.getPayment_id());
			listByBill.add(new BillDto(objProductBill.getBill_number(), objUser.getFullname(), objUser.getAddress(), objProductBill.getSum_bill() , objPayment.getPayment_name(), objProductBill.getStatus()));
		}
		if (listByBill.size() > 0) {
			return new ResponseEntity<List<BillDto>>(listByBill, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<BillDto>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("byStore")
	public ResponseEntity<List<BillDto>> getByStore(){
		List<BillDto> listByStore = new ArrayList<BillDto>();
		List<Dto> list = productBillService.getByStore();
		for(Dto objDto : list) {
			Store objStore = storeService.getOneById(objDto.getStore_id());
			listByStore.add(new BillDto(objStore.getStore_name(), objStore.getAddress(), objStore.getPhone(), objDto.getSum_cost(), objStore.getBank_name(), objStore.getBank_id()));
		}
		return new ResponseEntity<List<BillDto>>(listByStore, HttpStatus.OK);
	}
	
}
