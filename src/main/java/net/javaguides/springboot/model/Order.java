package net.javaguides.springboot.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "Bills")
public class Order {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	/*
	
	@Column(name = "orderDate")
	private Date orderDate;*/
	
	
	 @Column(name = "orderedProducts")
	 private List<Product> orderedProducts; // Liste des produits commandés avec leurs quantités
	    
	   
	   
	/*
	@Column(name = "totalAmount")
    private double totalAmount;*/
	
	
	@Column(name = "customerName")
    private String customerName;
	
	
	@Column(name = "tableNumber")
    private Integer tableNumber;
	
	
	@Column(name = "employeeName")
    private String employeeName;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<Product> getOrderedProducts() {
		return orderedProducts;
	}


	public void setOrderedProducts(List<Product> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

/*
	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}*/


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
	
	
	/*
	@Column(name = "notes")
    private String notes;*/



    


	






	

	
	
	
	
}
