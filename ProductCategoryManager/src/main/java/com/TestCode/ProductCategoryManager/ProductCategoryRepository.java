package com.TestCode.ProductCategoryManager;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TestCode.ProductCategoryManager.Model.ProductCategory;
import com.TestCode.ProductCategoryManager.Model.ProductCategoryId;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, ProductCategoryId> {

}
