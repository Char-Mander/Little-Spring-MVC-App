package com.TestCode.ProductCategoryManager.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Table(name="product")
@Entity
public class Product {
	

	private Long id;
	private String name;  
	private String description;
	private float price;
	private String currency;	
	@ManyToMany
	@JoinTable(
			  name = "productcategory", 
			  joinColumns = @JoinColumn(name = "id_product"), 
			  inverseJoinColumns = @JoinColumn(name = "id_category"))
    List<Category> categories = new ArrayList<Category>();
	
	public Product() { }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<Category> GetCategories(){
		return categories;
	}
	
	public boolean HasTheCategory(Category c) {
		return categories.contains(c);
	}
	
	public Category GetCategoryFromList(long id) {
		Category category = null;
		for(Category c : categories) {
			if(c.getId()==id) category = c;
		}
		return category;
	}
	
	public void AddCategories(Category c) {
		categories.add(c);
	}
	
	public void DeleteCategory(long id) {
		for(Category c : categories) {
			if(c.getId()==id) categories.remove(c);
			return;
		}
	}
}
