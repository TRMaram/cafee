package net.javaguides.springboot.model;

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
	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_fk", nullable = false)
	private Category category;*/
	
	@Column(name = "prix")
	private String Prix;
	
	@Column(name = "status")
	private String status;

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
/*
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}*/

	public String getPrix() {
		return Prix;
	}

	public void setPrix(String prix) {
		Prix = prix;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
