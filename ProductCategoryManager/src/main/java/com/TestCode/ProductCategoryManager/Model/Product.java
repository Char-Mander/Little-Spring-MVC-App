package com.TestCode.ProductCategoryManager.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	@OneToMany(mappedBy = "productcategoryId.id_product")
    List<ProductCategory> categories = new ArrayList<ProductCategory>();
	
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

	public List<ProductCategory> GetCategories(){
		return categories;
	}
	
	public boolean HasTheCategory(ProductCategory c) {
		return categories.contains(c);
	}
	/*
	public Category GetCategoryFromList(long id) {
		ProductCategory category = null;
		for(ProductCategory c : categories) {
			if(c.getcatgetId()==id) category = c;
		}
		return category;
	}*/
	
	public void AddCategories(ProductCategory c) {
		categories.add(c);
	}
	
	public void DeleteCategory(ProductCategory id) {
		if(categories.contains(id)) categories.remove(id);
	}
}
