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
import com.TestCode.ProductCategoryManager.ProductCategoryService;
import com.TestCode.ProductCategoryManager.ProductService;
import com.TestCode.ProductCategoryManager.Model.Category;
import com.TestCode.ProductCategoryManager.Model.Product;
import com.TestCode.ProductCategoryManager.Model.ProductCategory;
import com.TestCode.ProductCategoryManager.Model.ProductCategoryId;

@Controller
public class ApplicationController {
	@Autowired
	private ProductService p_service;
	@Autowired
	private CategoryService c_service;
	@Autowired
	private ProductCategoryService pc_service;
	
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
		System.out.println("Después de añadir la categoría con id: " + c.getId() + " y nombre: " + c.getName());
		ProductCategory pc = new ProductCategory();
		ProductCategoryId pcid = new ProductCategoryId();
		pcid.setId_product(id_product);
		pcid.setId_category(id_category);
		pc.setProductCategoryId(pcid);
		pc_service.save(pc);
		System.out.println("Se ha guardado el productcategory");
		p.AddCategories(pc);
		System.out.println("Se ha añadido la categoría " + p.getName() + " a la lista de productos");
		p_service.save(p);
		System.out.println("Se ha guardado la categoría " + p.getName() + " a la lista de productos");
		c.AddProducts(pc);
		System.out.println("Se ha añadido el producto " + p.getName() + " a la lista de categorías");
		c_service.save(c);
		System.out.println("Se ha guardado el producto " + p.getName() + " a la lista de categorías");
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
		ProductCategory pc = new ProductCategory();
		ProductCategoryId pcid = new ProductCategoryId();
		pc_service.get(pcid);
		pcid.setId_product(id_product);
		pcid.setId_category(id_category);
		pc.setProductCategoryId(pcid);
		System.out.println("Después de obtener el productocategoría");
		p.DeleteCategory(pc);
		System.out.println("Se ha eliminado la categoría " + c.getName() + " de la lista del producto");
		c.DeleteProduct(pc);
		System.out.println("Se ha eliminado el producto " + p.getName() + " de la lista de categorías");
		p_service.delete(p.getId());
		System.out.println("Después de eliminar la categoría de la lista de productos");
		c_service.delete(c.getId());
		System.out.println("Después de eliminar el producto de la lista de categorías");
		pc_service.delete(pcid);
		System.out.println("Tamaño del array de productos: " + p.GetCategories().size());
		System.out.println("Tamaño del array de categorías: " + c.GetProducts().size());
		return "redirect:/";
	}
}
