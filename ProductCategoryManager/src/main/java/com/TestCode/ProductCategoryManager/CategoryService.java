package com.TestCode.ProductCategoryManager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TestCode.ProductCategoryManager.Model.Category;

@Service
@Transactional
public class CategoryService {
	@Autowired
	private CategoryRepository repo;
	
	public Category get(Long id) {
		return repo.findById(id).get();
	}
	
	public List<Category> showAll(){
		return repo.findAll();
	}
	
	public void save(Category category) {
		repo.save(category);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
