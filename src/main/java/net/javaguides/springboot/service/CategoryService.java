package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Category;
import net.javaguides.springboot.model.Employee;

public interface CategoryService {
	List<Category> getAllCategories();
	void saveCategory(Category category);
	Category getCategoryById(int categoryId);
	void deleteCategoryById(int id);
	Page<Category> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
}
