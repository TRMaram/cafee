package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Product;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	
}
