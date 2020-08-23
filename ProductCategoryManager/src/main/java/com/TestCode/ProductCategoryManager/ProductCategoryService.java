package com.TestCode.ProductCategoryManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.TestCode.ProductCategoryManager.Model.ProductCategory;
import com.TestCode.ProductCategoryManager.Model.ProductCategoryId;

public class ProductCategoryService {
	@Autowired
	private ProductCategoryRepository repo;
	
	public ProductCategory get(ProductCategoryId id) {
		return repo.findById(id).get();
	}
	
	public List<ProductCategory> showAll(){
		return repo.findAll();
	}
	
	public void save(ProductCategory pc) {
		repo.save(pc);
	}
	
	public void delete(ProductCategoryId id) {
		repo.deleteById(id);
	}
	
}
