package shoppee.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.Category;
import shoppee.com.service.impl.CategoryServiceImpl;
import shoppee.com.utils.CatelogyTokenResult;
import shoppee.com.utils.LogicHandle;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryService;

	@GetMapping("parent")
	public ResponseEntity<List<Category>> getParentCategory() {
		List<Category> listParentCategory = categoryService.getParentCategory();
		if (listParentCategory.size() > 0) {
			return new ResponseEntity<List<Category>>(listParentCategory, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("byparent/{parent_id}")
	public ResponseEntity<List<Category>> getCategoryByParent(@PathVariable(value = "parent_id") Integer parent_id) {
		List<Category> listCategoryByParent = categoryService.getCategoryByParent(parent_id);
		if (listCategoryByParent.size() > 0) {
			return new ResponseEntity<List<Category>>(listCategoryByParent, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("add")
	public ResponseEntity<Category> addCategory(@RequestBody(required = false) Category objCategory) {
		List<Category> listAllCategory = categoryService.getAllCategory();
		boolean checkCatName = LogicHandle.functionCheckCatName(listAllCategory, objCategory);
		if (checkCatName == true) {
			Category category = categoryService.addCategory(objCategory);
			return new ResponseEntity<Category>(category, HttpStatus.CREATED);
		}

		CatelogyTokenResult result = new CatelogyTokenResult("False",
				"Danh mục đã tồn tại. Vui lòng nhập lại tên danh mục!!", objCategory);
		return new ResponseEntity(result, HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
