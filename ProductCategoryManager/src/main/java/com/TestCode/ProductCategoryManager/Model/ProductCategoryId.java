package com.TestCode.ProductCategoryManager.Model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ProductCategoryId implements Serializable{
	private long id_product;
	private long id_category;
	
	public long getId_product() {
		return id_product;
	}
	public void setId_product(long id_product) {
		this.id_product = id_product;
	}
	public long getId_category() {
		return id_category;
	}
	public void setId_category(long id_category) {
		this.id_category = id_category;
	}
}
