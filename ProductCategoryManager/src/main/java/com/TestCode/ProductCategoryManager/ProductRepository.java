package com.TestCode.ProductCategoryManager;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TestCode.ProductCategoryManager.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
