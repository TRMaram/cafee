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
import org.springframework.web.bind.annotation.RequestParam;



import net.javaguides.springboot.model.Category;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.service.CategoryService;
import net.javaguides.springboot.service.EmployeeService;
import net.javaguides.springboot.service.ProductService;


@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService servicecategory;
	
	// display list of produits
	@GetMapping("/product")
	public String ProuctHomePage(Model model) {
		return findPaginate(1, "name", "asc", model);		
	}
	
	
	@GetMapping("/showNewProductForm")
	public String showNewProductForm(Model model) {
		// create model attribute to bind form data
		Product product = new Product();
		model.addAttribute("product", product);
		List<Category> categories=servicecategory.getAllCategories();
		model.addAttribute("listcategory",categories);
		return "new_product";
	}
	
	@PostMapping("/saveProduct")
	public String saveProduct(
	@ModelAttribute("product") Product product,
	@RequestParam(value="category")int categoryId, Model model) {
	Category categ=servicecategory.getCategoryById(categoryId);
	product.setCategory(categ);
	
		// save produit to database
		productService.saveProduct(product);
		
		return "redirect:/product";
	}
	
	@GetMapping("/showFormUpdate/{id}")
	public String showFormUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get product from the service
		Product product = productService.getProductById(id);
		
		// set prouit as a model attribute to pre-populate the form
		model.addAttribute("product", product);
		List<Category> categories=servicecategory.getAllCategories();
		model.addAttribute("listcategory",categories);
		return "update_product";
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable (value = "id") long id) {
		
		// call delete produit method 
		this.productService.deleteProductById(id);
		return "redirect:/product";
	}
	
	
	@GetMapping("/pages/{pageNo}")
	public String findPaginate(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Product> page = productService.findPaginate(pageNo, pageSize, sortField, sortDir);
		List<Product> listProducts = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listProducts", listProducts);
		return "index_product";
	}
}
