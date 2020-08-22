package com.TestCode.ProductCategoryManager.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.TestCode.ProductCategoryManager.CategoryService;
import com.TestCode.ProductCategoryManager.Model.Category;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService c_service;
	
	@RequestMapping("/showCategories")
	public String showAllCategories(Model model) {
		List<Category> categoriesList = c_service.showAll();
		model.addAttribute("categoriesList", categoriesList);
		return "categories";
	}
	
	
	@RequestMapping("/newCategory")
	public String showNewCategoryForm(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "new_category_form";
	}
	
	@RequestMapping(value="/saveCategory", method = RequestMethod.POST)
	public String saveCategory(@ModelAttribute("category") Category category) {
		c_service.save(category);
		return "redirect:/showCategories";
	}
	
	@RequestMapping("/editCategory/{id}")
	public ModelAndView showEditCategoryForm(@PathVariable(name = "id") long id) {
		ModelAndView mav = new ModelAndView("edit_category_form");
		Category category = c_service.get(id);
		mav.addObject("category", category);
		return mav;
	}
	
	@RequestMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable(name = "id") long id) {
		c_service.delete(id);
		return "redirect:/showCategories";
	}
}
