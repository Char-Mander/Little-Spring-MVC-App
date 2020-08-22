package com.TestCode.ProductCategoryManager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.TestCode.ProductCategoryManager.ProductService;
import com.TestCode.ProductCategoryManager.Model.Product;

@Controller
public class ProductController {
	@Autowired
	private ProductService p_service;
	
	@RequestMapping("/newProduct")
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "new_product_form";
	}

	@RequestMapping(value="/saveProduct", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		if(product.getPrice()<=0) return "redirect:/error";
		if(product.getCurrency().isBlank()) product.setCurrency("EUR");
		p_service.save(product);
		return "index";
	}
	
	@RequestMapping("/editProduct/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") long id) {
		ModelAndView mav = new ModelAndView("edit_product_form");
		Product product = p_service.get(id);
		mav.addObject("product", product);
		return mav;
	}
	
	@RequestMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(name = "id") long id) {
		p_service.delete(id);
		return "redirect:/";
	}
	
	
}
