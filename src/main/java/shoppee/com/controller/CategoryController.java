package shoppee.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.Category;
import shoppee.com.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryService;
	
	@GetMapping("allparent")
	public ResponseEntity<List<Category>> getgetParentCategory(){
		List<Category> listParentCategory = categoryService.getParentCategory();
		if(listParentCategory.size() > 0) {
			return new ResponseEntity<List<Category>>(listParentCategory, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
