package net.javaguides.springboot.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "bill")
public class Bill{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "product")
	private String product; 
	@Column(name="quantity")
	private int quantity; 
	@Column(name = "paymentmethod")
	private String paymentMethod;

	@Column(name = "totalAmount")
	private Double totalAmount;
	
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "createdby")
	private LocalDateTime createdBy;

	
	
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	

	

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}



	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}



	public LocalDateTime getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(LocalDateTime datetime) {
		this.createdBy = datetime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
