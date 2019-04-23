package shoppee.com.service.impl;

import java.util.List;

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
	
}
