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
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Bill;
import net.javaguides.springboot.model.Category;
import net.javaguides.springboot.service.BillService;
import net.javaguides.springboot.service.CategoryService;


@Controller

@RequestMapping(path = "/Bill")
public class BillController {

	@Autowired
	private BillService billService;
	
	// display list of Categories
	@GetMapping(path="/")
	public String viewBillHomePage(Model model) {
		return findPaginated(1, "name", "asc", model);		
	}
	
	@GetMapping(path="/showNewBillForm")
	public String showNewBillForm(Model model) {
		// create model attribute to bind form data
		Bill bill = new Bill();
		model.addAttribute("Bill", bill);
		return "new_Bill";
	}
	
	@PostMapping(path="/saveBill")
	public String saveBill(@ModelAttribute("Bill") Bill bill) {
		// save Bill to database
		billService.saveBill(bill);
		return "redirect:/Bill/";
	}
	
	@GetMapping(path="/showFormForUpdateBill/{id}")
	public String showFormForUpdateBill(@PathVariable ( value = "id") int id, Model model) {
		
		// get Bill from the service
		Bill bill = billService.getBillById(id);
		
		// set Bill as a model attribute to pre-populate the form
		model.addAttribute("Bill", bill);
		return "update_Bill";
	}
	
	@GetMapping(path="/deleteBill/{id}")
	public String deleteBill(@PathVariable (value = "id") int id) {
		
		// call delete Bill method 
		this.billService.deleteBillById(id);
		return "redirect:/Bill/";
	}
	
	
	@GetMapping(path="/page/{pageNo}")
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
		
		model.addAttribute("listBills", listBills);
		return "indexBill";
	}
}
