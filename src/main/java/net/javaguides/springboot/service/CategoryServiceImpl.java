package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Category;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.CategoryRepository;
import net.javaguides.springboot.repository.EmployeeRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();	}

	@Override
	public void saveCategory(Category category) {
		this.categoryRepository.save(category);
		
	}

	@Override
	public Category getCategoryById(int id) {
		Optional<Category> optional = categoryRepository.findById(id);
		Category category = null;
		if (optional.isPresent()) {
			category = optional.get();
		} else {
			throw new RuntimeException(" Category not found for id :: " + id);
		}
		return category;
	}

	@Override
	public void deleteCategoryById(int id) {
		this.categoryRepository.deleteById(id);

		
	}

	@Override
	public Page<Category> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.categoryRepository.findAll(pageable);
	}

	
}
