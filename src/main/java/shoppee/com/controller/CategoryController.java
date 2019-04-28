package shoppee.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.entities.Category;
import shoppee.com.service.impl.CategoryServiceImpl;
import shoppee.com.utils.CatelogyTokenResult;
import shoppee.com.utils.LogicHandle;
import shoppee.com.utils.TokenResult;

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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("{id}")
	public ResponseEntity<Category> getAdiminById(@PathVariable(value = "id") Integer id) {
		if(categoryService.getCategoryById(id) == null) {
			TokenResult error = new TokenResult("False", "Không tìm thấy danh mục!!");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		}else {
			Category objCategory = categoryService.getCategoryById(id);
			return new ResponseEntity<Category>(objCategory, HttpStatus.OK);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("update/{id}")
	public ResponseEntity<Category> updateCategory(@RequestBody Category objCategory, @PathVariable(value = "id") Integer id){
		Category oldCategory = categoryService.getCategoryById(id);
		if(oldCategory == null) {
			TokenResult error = new TokenResult("False", "Không tìm thấy danh mục!!");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		}else {
			oldCategory.setCat_name(objCategory.getCat_name());
			oldCategory.setParent_id(objCategory.getParent_id());
			
			categoryService.addCategory(oldCategory);
			return new ResponseEntity<Category>(oldCategory, HttpStatus.OK);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable(value = "id") Integer id){
		Category objCategory = categoryService.getCategoryById(id);
		if(objCategory == null) {
			TokenResult error = new TokenResult("False", "Không tìm thấy danh mục!!");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		}else {
			categoryService.deleteCategory(id);
			TokenResult result = new TokenResult("True", "Xóa danh mục thành công!!");
			return new ResponseEntity(result, HttpStatus.OK);
		}
	}
	
}
