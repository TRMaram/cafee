package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer>{

}
