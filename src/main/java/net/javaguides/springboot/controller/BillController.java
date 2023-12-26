package net.javaguides.springboot.controller;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import org.xhtmlrenderer.pdf.ITextRenderer;

import net.javaguides.springboot.model.Bill;
import net.javaguides.springboot.model.BillItem;
import net.javaguides.springboot.model.Category;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.ProductRepository;
import net.javaguides.springboot.service.BillService;
import net.javaguides.springboot.service.CategoryService;
import net.javaguides.springboot.service.ProductService;


@Controller

@RequestMapping(path = "/Bill")
public class BillController {

	@Autowired
	private BillService billService;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@GetMapping(path="/")
	public String viewBillHomePage(Model model) {
		return findPaginated(1, "createdBy", "asc", model);		
	}
	
	@GetMapping(path="/showNewBillForm")
	public String showNewBillForm(Model model) {
		// create model attribute to bind form data
		Bill bill = new Bill();
		model.addAttribute("bill", bill);

		List<Product> products=productService.getAllProducts();
		model.addAttribute("listproduct",products);
		return "new_Bill2.html";
	}
	
	@PostMapping(path="/saveBill")
	public String saveBill(@ModelAttribute("bill") Bill bill) {
		double total = 0;
		// save Bill to database
		bill.setUuid("user1");
		LocalDateTime datetime = LocalDateTime.now();
		bill.setCreatedBy(datetime);
		String p=bill.getProduct();
		String[] numbersAsStrings = p.split(",");
        int[] numbers = new int[numbersAsStrings.length];
        for (int i = 0; i < numbersAsStrings.length; i++) {
            numbers[i] = Integer.parseInt(numbersAsStrings[i]);
            long pp = numbers[i];
            Integer price= productRepository.FindPrixById(pp) ;
            Product p1=productService.getProductById(pp);
            System.out.println(price);
            int q=p1.getQuantity();
            total = total + price * bill.getQuantity();
             
             
             q= q- bill.getQuantity();
             if(q>0) {
             p1.setQuantity(q);}else {
            	 p1.setQuantity(0);
            	 System.out.println("quantité insuffisant !!");
             }
             
             
          }
        System.out.println(total);
        bill.setTotalAmount(total);
		
		billService.saveBill(bill);
		generateBill(bill);
		return "redirect:/Bill/";
	}
	
    /*@GetMapping(path="/showFormForUpdateBill/{id}")
	public String showFormForUpdateBill(@PathVariable ( value = "id") int id, Model model) {
		
		// get Bill from the service
		Bill bill = billService.getBillById(id);
		
		// set Bill as a model attribute to pre-populate the form
		model.addAttribute("bill", bill);
		return "update_Bill";
	}*/
	
	@GetMapping(path="/deleteBill/{id}")
	public String deleteBill(@PathVariable (value = "id") int id) {
		
		// call delete Bill method 
		this.billService.deleteBillById(id);
		return "redirect:/Bill/";
	}
	
	
	//@GetMapping("/generateBill")
    public void generateBill(@ModelAttribute("bill") Bill bill) {
        // Mock bill data
        System.out.println(
        bill.getUuid()+" "+
        bill.getCreatedBy()+" "+
        bill.getPaymentMethod()+" "+
        bill.getQuantity()+" "+
        bill.getProduct()+" "+
        bill.getTotalAmount()
        );
        
        // Generate HTML content using Thymeleaf template
        //String htmlContent = generateHtmlContent(model);
        String htmlContent = "<html><body><h2>Bill</h2> <h2>Cafe Moonlight</h2> <h4>Date :"+"     "+bill.getCreatedBy()
        +"</h4>  <h4>CaisseUser :"+"     "+bill.getUuid()+"</h4> <table border='2px'><tr><th>Product</th><th>Quantity</th><th>Payment Method</th><th>Total Amount</th></tr><tr><td>"+
        bill.getProduct()+"</td><td>"+bill.getQuantity()+"</td><td>"+bill.getPaymentMethod()+"</td><td>"
        +bill.getTotalAmount()+"</td></tr></table></body></html>";
        

        // Convert HTML to PDF using Flying Saucer
        generatePdfFromHtml(htmlContent, "bill.pdf");

     
    }

   

    private void generatePdfFromHtml(String htmlContent, String pdfPath) {
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
             

            try (FileOutputStream  os = new FileOutputStream(pdfPath)) {
                renderer.createPDF(os);
            }

            System.out.println("PDF generated successfully at: " + pdfPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostMapping(path="/addItem")
    public String addItem(@ModelAttribute BillItem item) {
        billService.addItem(item);
        return "redirect:/bill";
    }

    @GetMapping("/bill")
    public String showBill(Model model) {
        model.addAttribute("billItems", billService.getBillItems());
        model.addAttribute("total", billService.calculateTotal());
        return "generatebill";
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
		return "indexBill.html";
	}
}
