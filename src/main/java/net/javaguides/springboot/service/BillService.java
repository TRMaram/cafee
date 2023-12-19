package net.javaguides.springboot.service;

import java.util.List;

import org.hibernate.mapping.Map;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import net.javaguides.springboot.model.Bill;



public interface BillService {
	//ResponseEntity<String> generateReport(Map requestMap);

	//ResponseEntity<List<Bill>> getBills();

	//ResponseEntity<byte[]> getPdf(Map requestMap);
	List<Bill> getAllBills();
	void saveBill(Bill bill);
	Bill getBillById(int id);
	void deleteBillById(int id);
	Page<Bill> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
}
