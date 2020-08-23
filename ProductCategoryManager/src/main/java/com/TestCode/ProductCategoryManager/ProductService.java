package com.TestCode.ProductCategoryManager;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TestCode.ProductCategoryManager.Model.Product;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductRepository repo;
	
	public Product get(Long id) {
		return repo.findById(id).get();
	}
	
	public List<Product> showAll(){
		return repo.findAll();
	}
	
	public void save(Product product) {
		repo.save(product);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
}
