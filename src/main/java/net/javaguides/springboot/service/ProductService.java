package net.javaguides.springboot.service;

import java.util.List; 

import org.springframework.data.domain.Page;


import net.javaguides.springboot.model.Product;


public interface ProductService {
	List<Product> getAllProducts();
	void saveProduct(Product product);
	Product getProductById(long id);
	void deleteProductById(long id);
	Page<Product> findPaginate(int pageNo, int pageSize, String sortField, String sortDirection);
}
