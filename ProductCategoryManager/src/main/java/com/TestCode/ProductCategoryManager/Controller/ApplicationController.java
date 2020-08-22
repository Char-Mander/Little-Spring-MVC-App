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
		for(Product p : productsList) {
			System.out.println("Producto: " + p.getId() + " con " + p.GetCategories().size() + " elementos en categorías");
		}
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
	
	@RequestMapping("/categoryToAdd/{id_product}/{category_id}")
	public String categoryToAdd(@PathVariable(name = "id_product") long id_product, @PathVariable(name = "category_id") long id_category) {
		Product p = p_service.get(id_product);
		Category c = c_service.get(id_category);
		p.AddCategories(c);
		System.out.println("Se ha añadido la categoría " + c.getName() + " a la lista del producto");
		c.AddProducts(p);
		System.out.println("Se ha añadido el producto " + p.getName() + " a la lista de categorías");
		p_service.save(p);
		System.out.println("Después de añadir la categoría con id: " + c.getId() + " y nombre: " + c.getName());
		c_service.save(c);
		System.out.println("Tamaño del array de productos: " + p.GetCategories().size());
		System.out.println("Tamaño del array de categorías: " + c.GetProducts().size());
		return "redirect:/";
	}
	
	@RequestMapping("/deleteCategoryFromProduct/{id}")
	public String deleteCategoryFromProduct(@PathVariable(name = "id") long id, Model model) {
		Product product = p_service.get(id);
		if(product.GetCategories().isEmpty()) return "redirect:/";
		model.addAttribute("categoriesList", product.GetCategories());
		model.addAttribute("product_id", id);
		return "delete_category_from_product";
	}	

	@RequestMapping("/deleteCategoryFromProduct/{id_product}/{id_category}")
	public String categoryToDelete(@PathVariable(name = "id_product") long id_product, @PathVariable(name = "id_category") long id_category) {
		Product p = p_service.get(id_product);
		Category c = c_service.get(id_category);
		p.DeleteCategory(id_category);
		System.out.println("Se ha eliminado la categoría " + c.getName() + " de la lista del producto");
		c.DeleteProduct(id_product);
		System.out.println("Se ha eliminado el producto " + p.getName() + " de la lista de categorías");
		p_service.save(p);
		System.out.println("Después de eliminar la categoría con id: " + c.getId() + " y nombre: " + c.getName());
		c_service.save(c);
		System.out.println("Tamaño del array de productos: " + p.GetCategories().size());
		System.out.println("Tamaño del array de categorías: " + c.GetProducts().size());
		return "redirect:/";
	}
}
