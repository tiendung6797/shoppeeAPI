package shoppee.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.ProductBill;
import shoppee.com.service.impl.ProductBillServiceImpl;
import shoppee.com.utils.TokenResult;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/productbill")
public class ProductBillController {
	
	@Autowired
	ProductBillServiceImpl productBillService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("buy")
	public ResponseEntity<List<ProductBill>> buyItems(@RequestBody List<ProductBill> listProductBill) {
		if(listProductBill.size() > 0) {
			for(ProductBill objProductBill : listProductBill) {
				productBillService.addProductBill(objProductBill);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}
		TokenResult error = new TokenResult("False", "");
		return new ResponseEntity(error, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<ProductBill>> getParentCategory() {
		List<ProductBill> listProductBill = productBillService.getAllProductBill();
		if (listProductBill.size() > 0) {
			return new ResponseEntity<List<ProductBill>>(listProductBill, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
