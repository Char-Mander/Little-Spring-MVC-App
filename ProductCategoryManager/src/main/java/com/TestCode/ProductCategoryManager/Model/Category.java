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
    List<Product> products = new ArrayList<Product>();
	
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
	
	public List<Product> GetProducts(){
		return products;
	}
	
	public Product GetProductFromList(long id) {
		Product product = null;
		for(Product p : products) {
			if(p.getId()==id) product = p;
		}
		return product;
	}
	
	public void AddProducts(Product p) {
		products.add(p);
	}
	
	public void DeleteProduct(long id) {
		for(Product p : products) {
			if(p.getId()==id) products.remove(p);
			return;
		}
	}
	
}
