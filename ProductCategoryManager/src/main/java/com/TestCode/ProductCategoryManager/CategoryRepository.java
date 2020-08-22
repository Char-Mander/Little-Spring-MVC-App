package com.TestCode.ProductCategoryManager;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TestCode.ProductCategoryManager.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
