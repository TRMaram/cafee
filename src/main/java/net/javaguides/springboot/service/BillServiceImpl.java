package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Bill;
import net.javaguides.springboot.model.Category;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.BillRepository;
import net.javaguides.springboot.repository.CategoryRepository;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepository billRepository;

	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<Bill> getAllBills() {
		return billRepository.findAll();
	}

	@Override
	public Page<Bill> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.billRepository.findAll(pageable);
	}

	@Override
	public void saveBill(Bill bill) {
		// TODO Auto-generated method stub
		this.billRepository.save(bill);
		
	}

	@Override
	public Bill getBillById(int id) {
		// TODO Auto-generated method stub
		Optional<Bill> optional = billRepository.findById(id);
		Bill bill = null;
		if (optional.isPresent()) {
			bill = optional.get();
		} else {
			throw new RuntimeException(" Bill not found for id :: " + id);
		}
		return bill;
	}

	@Override
	public void deleteBillById(int id) {
		// TODO Auto-generated method stub
		this.billRepository.deleteById(id);
		
	}

	@Override
	public ResponseEntity<String> generateReport(Map requestMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Bill>> getBills() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<byte[]> getPdf(Map requestMap) {
		// TODO Auto-generated method stub
		return null;
	}
}
