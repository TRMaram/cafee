package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import net.javaguides.springboot.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("Select p from Product p where p.category.id = ?1")
	List<Product> FindByCategory(Long category_id);
}
