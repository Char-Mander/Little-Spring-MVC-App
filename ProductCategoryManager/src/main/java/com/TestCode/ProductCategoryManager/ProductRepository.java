package com.TestCode.ProductCategoryManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.TestCode.ProductCategoryManager.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query("from Product p left join fetch p.categories where p.id = :id")
    public Product getWithCategories(@Param("id") Long id);
}
