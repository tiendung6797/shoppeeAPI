package shoppee.com.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppee.com.entities.Category;
import shoppee.com.repository.CategoryRepository;
import shoppee.com.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getParentCategory(){
		List<Category> listParentCategory = categoryRepository.getParentCategory();
		return listParentCategory;
	}

	@Override
	public List<Category> getCategoryByParent(Integer parent_id) {
		List<Category> listCategoryByParent = categoryRepository.getCategoryByParent(parent_id);
		return listCategoryByParent;
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> listAllCategory = categoryRepository.getAllCategory();
		return listAllCategory;
	}

	@Override
	public Category addCategory(Category objCategory) {
		Category category = categoryRepository.save(objCategory);
		return category;
	}

	@Override
	public Category getCategoryById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Category> objCategory = categoryRepository.findById(id);
		return objCategory.get();
	}

	@Override
	public void deleteCategory(Integer id) {
		// TODO Auto-generated method stub
		categoryRepository.deleteById(id);
	}
	
}
