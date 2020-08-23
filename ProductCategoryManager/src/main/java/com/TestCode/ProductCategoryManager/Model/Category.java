package com.TestCode.ProductCategoryManager.Model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name="category")
@Entity
public class Category {
	

	private long id;
	
	private String name;
	
	@ManyToMany(mappedBy = "categories")
    List<ProductCategory> products = new ArrayList<ProductCategory>();
	
	public Category() { }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<ProductCategory> GetProducts(){
		return products;
	}
	/*
	public ProductCategory GetProductFromList(long id) {
		ProductCategory product = null;
		for(ProductCategory p : products) {
			if(p.getId()==id) product = p;
		}
		return product;
	}
	*/
	public void AddProducts(ProductCategory p) {
		products.add(p);
	}
	
	public void DeleteProduct(ProductCategory id) {
		if(products.contains(id)) products.remove(id);
	}
	
}
