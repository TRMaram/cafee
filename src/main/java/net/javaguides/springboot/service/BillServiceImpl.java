package net.javaguides.springboot.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Bill;
import net.javaguides.springboot.model.BillItem;
import net.javaguides.springboot.repository.BillRepository;




@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepository billRepository;

	@Override
	public List<Bill> getAllBills() {
		return billRepository.findAll();
	}

	@Override
	public void saveBill(Bill bill) {
		// TODO Auto-generated method stub
				this.billRepository.save(bill);
		
	}

	@Override
	public Bill getBillById(int id) {
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
		this.billRepository.deleteById(id);
		
	}

	@Override
	public Page<Bill> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.billRepository.findAll(pageable);
	}
	private List<BillItem> billItems = new ArrayList<>();
	@Override
	public void addItem(BillItem item) {
		billItems.add(item);
		
	}

	@Override
	public double calculateTotal() {
		return billItems.stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
	}

	@Override
	public List<BillItem> getBillItems() {
		return billItems;
	}
	
}
