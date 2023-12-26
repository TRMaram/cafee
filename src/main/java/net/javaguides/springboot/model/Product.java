package net.javaguides.springboot.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



import jakarta.persistence.*;

@Entity
@Table(name = "produits")
public class Product {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String Description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", insertable = true, updatable =true)
	@Fetch(FetchMode.JOIN)
	private Category category;
	
	@Column(name = "prix")
	private String prix;
	
	@Column(name = "status")
	private String status;

	@Column(name = "quantity")
	private Integer quantity;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}


	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		prix = this.prix;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	

	
	
	
	
	
}
