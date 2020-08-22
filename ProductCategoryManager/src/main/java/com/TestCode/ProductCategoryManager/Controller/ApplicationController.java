package com.TestCode.ProductCategoryManager.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.TestCode.ProductCategoryManager.CategoryService;
import com.TestCode.ProductCategoryManager.ProductService;
import com.TestCode.ProductCategoryManager.Model.Category;
import com.TestCode.ProductCategoryManager.Model.Product;

@Controller
public class ApplicationController {
	@Autowired
	private ProductService p_service;
	@Autowired
	private CategoryService c_service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product> productsList = p_service.showAll();
		model.addAttribute("productsList", productsList);
		return "index";
	}

	@RequestMapping("/addCategoryToProduct/{id}")
	public String addCategoryToProduct(@PathVariable(name = "id") long id, Model model) {
		Product product = p_service.get(id);
		List<Category> categoriesList = c_service.showAll();
		if(categoriesList.isEmpty()) return "redirect:/";
		List<Category> newCategories = new ArrayList<Category>();
		if(!product.GetCategories().isEmpty()) {
			for(Category c : categoriesList) {
				if(!product.GetCategories().contains(c)) { 
					newCategories.add(c);
				}
			}
			if(newCategories.isEmpty()) return "redirect:/";
		}
		model.addAttribute("categoriesList", product.GetCategories().size()>0 ? newCategories : categoriesList);
		model.addAttribute("product_id", id);
		return "add_category_to_product";
	}
	
	@RequestMapping("/categoryToAdd/{id}")
	public String categoryToAdd(@PathVariable(name = "id") long id, @ModelAttribute("category") Category category) {
		System.out.println("Categoría con id: " + category.getId() + " , y producto con id: " + id);
		Product p = p_service.get(id);
		p.AddCategories(category);
		p_service.save(p);
		System.out.println("Antes");
		category.AddProducts(p);
		System.out.println("Después de añadir");
		c_service.save(category);
		System.out.println("Tamaño del array de productos: " + p.GetCategories().size());
		System.out.println("Tamaño del array de categorías: " + category.GetProducts().size());
		return "redirect:/";
	}
	
	@RequestMapping("/deleteCategoryFromProduct/{id}")
	public String deleteCategoryFromProduct(@PathVariable(name = "id") long id, Model model) {
		List<Category> categoriesList = c_service.showAll();
		if(categoriesList.isEmpty()) return "redirect:/";
		model.addAttribute("categoriesList", categoriesList);
		model.addAttribute("product_id", id);
		return "delete_category_from_product";
	}
}
