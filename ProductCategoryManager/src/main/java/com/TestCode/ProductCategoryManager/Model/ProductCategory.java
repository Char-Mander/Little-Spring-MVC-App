package com.TestCode.ProductCategoryManager.Model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ProductCategory {
	@EmbeddedId
	private ProductCategoryId productCategoryId;
	
	public void setProductCategoryId(ProductCategoryId productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
}
