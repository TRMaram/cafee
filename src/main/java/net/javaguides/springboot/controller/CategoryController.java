package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Category;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.CategoryService;
import net.javaguides.springboot.service.EmployeeService;

@Controller

@RequestMapping(path = "/Category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	// display list of Categories
	@GetMapping(path="/")
	public String viewCategoryHomePage(Model model) {
		return findPaginated(1, "name", "asc", model);		
	}
	
	@GetMapping(path="/showNewCategoryForm")
	public String showNewCategoryForm(Model model) {
		// create model attribute to bind form data
		Category category = new Category();
		model.addAttribute("category", category);
		return "new_Category";
	}
	
	@PostMapping(path="/saveCategory")
	public String saveCategory(@ModelAttribute("category") Category category) {
		// save Category to database
		categoryService.saveCategory(category);
		return "redirect:/Category/";
	}
	
	@GetMapping(path="/showFormForUpdateCategory/{id}")
	public String showFormForUpdateCategory(@PathVariable ( value = "id") int id, Model model) {
		
		// get Category from the service
		Category category = categoryService.getCategoryById(id);
		
		// set Category as a model attribute to pre-populate the form
		model.addAttribute("category", category);
		return "update_Category";
	}
	
	@GetMapping(path="/deleteCategory/{id}")
	public String deleteCategory(@PathVariable (value = "id") int id) {
		
		// call delete Category method 
		this.categoryService.deleteCategoryById(id);
		return "redirect:/Category/";
	}
	
	
	@GetMapping(path="/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Category> page = categoryService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Category> listCategories = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listCategories", listCategories);
		return "indexCategory";
	}
}