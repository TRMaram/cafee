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

import net.javaguides.springboot.model.Bill;
import net.javaguides.springboot.model.Category;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.service.BillService;
import net.javaguides.springboot.service.CategoryService;


@Controller

@RequestMapping(path = "/bill")
public class BillController {
  
	@Autowired
	private BillService billService;
	
	
	// display list of bills
	@GetMapping("/")
	public String BillHomePage(Model model) {
		return findPaginated(1, "uuid", "asc", model);		
	}
	
	
	
	
	@GetMapping(path="/showNewBillForm")
	public String showNewBillForm(Model model) {
		// create model attribute to bind form data
		Bill bill = new Bill();
		model.addAttribute("bill", bill);
		return "new_bill";
	}
	
	@PostMapping(path="/saveBill")
	public String saveBill(@ModelAttribute("bill") Bill bill) {
		// save bill to database
		billService.saveBill(bill);
		return "redirect:/bill/";
	}
	
	
	@GetMapping(path="/showFormForUpdateBill/{id}")
	public String showFormForUpdateBill(@PathVariable ( value = "id") int id, Model model) {
		
		// get bill from the service
		Bill bill = billService.getBillById(id);
		
		// set bill as a model attribute to pre-populate the form
		model.addAttribute("bill", bill);
		return "update_Bill";
	}
	
	
	
	@GetMapping(path="/deleteBill/{id}")
	public String deleteBill(@PathVariable (value = "id") int id) {
		
		// call delete Category method 
		this.billService.deleteBillById(id);
		return "redirect:/bill/";
	}
	
	
	
	
	@GetMapping(path="/pages/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Bill> page = billService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Bill> listBills = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listCategories", listBills);
		return "index_bill";
	}
	
	
	
	
	
	
	
	
}