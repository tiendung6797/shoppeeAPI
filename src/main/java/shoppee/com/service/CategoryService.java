package shoppee.com.service;

import java.util.List;

import shoppee.com.entities.Category;

public interface CategoryService {

	List<Category> getParentCategory();
	
	List<Category> getCategoryByParent(Integer parent_id);
	
	List<Category> getAllCategory();
	
	Category addCategory(Category objCategory);

}
