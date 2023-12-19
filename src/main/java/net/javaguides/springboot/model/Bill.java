package net.javaguides.springboot.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "Bills")
public class Bill {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "uuid")
	private String uuid = UUID.randomUUID().toString();
	
	@Column(name = "billDate")
	private Date billDate;
	
	/*
	@Column(name = "list_products")
    private List<Product> products; // Liste des produits avec leurs quantités
	*/
	
	@Column(name = "totalAmount")
    private double totalAmount;
	
	
	@Column(name = "customerName")
    private String customerName;
	
	
	@Column(name = "tableNumber")
    private Integer tableNumber;
	
	
	@Column(name = "employeeName")
    private String employeeName;
	
	
	@Column(name = "paymentMethod")
    private String paymentMethod;
	
	
	
	@Column(name = "notes")
    private String notes;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}



	public Date getBillDate() {
		return billDate;
	}



	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}


/*
	public List<Product> getProducts() {
		return products;
	}



	public void setProducts(List<Product> products) {
		this.products = products;
	}*/



	public double getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public Integer getTableNumber() {
		return tableNumber;
	}



	public void setTableNumber(Integer tableNumber) {
		this.tableNumber = tableNumber;
	}



	public String getEmployeeName() {
		return employeeName;
	}



	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}



	public String getPaymentMethod() {
		return paymentMethod;
	}



	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}



	public String getNotes() {
		return notes;
	}



	public void setNotes(String notes) {
		this.notes = notes;
	}
	

	
	
	
	
}
