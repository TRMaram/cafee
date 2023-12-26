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

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;

@Controller

public class HomeController {

		@GetMapping("/")
	public String viewHomePage(Model model) {
		return "index2";	
	}
		@GetMapping("/admin_index")
		public String viewHomePageAdmin(Model model) {
			return "admin_index";	
		}
		@GetMapping("/Employees")
		public String viewHomePageEmp(Model model) {
			return "redirect:/Employees/";	
		}
		@GetMapping("/Products")
		public String viewHomePageProd(Model model) {
			return "redirect:/product";	
		}
		@GetMapping("/Categories")
		public String viewHomePageCat(Model model) {
			return "redirect:/Category/";	
		}
		@GetMapping("/Bill")
		public String viewHomePageBill(Model model) {
			return "redirect:/Bill/";	
		}
		@GetMapping("/serveur")
		public String viewHomePageServeur(Model model) {
			return "redirect:/Bill/showNewBillForm";	
		}
	
	
}
