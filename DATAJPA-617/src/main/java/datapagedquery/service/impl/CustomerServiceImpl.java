package datapagedquery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import datapagedquery.domain.Customer;
import datapagedquery.repository.CustomerRepository;
import datapagedquery.service.CustomerService;

//@Service("customerService")
//@Transactional("transactionManager")
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired private CustomerRepository customerRepository;

	@Transactional(readOnly = true)
	public Page<Customer> findAll(int pageNum, int pageSize) {
		PageRequest pr = new PageRequest(pageNum, pageSize);
		return customerRepository.findAll(pr);
	}

	@Transactional(readOnly = true, noRollbackFor = Exception.class)
	public Page<Customer> findByNamePatternPaged(String keyword, int pageNum, int pageSize) {
		PageRequest pr = new PageRequest(pageNum, pageSize);
		String pattern = "%" + keyword + "%";
		return customerRepository.findByNamePattern(pattern, pr);
	}
}
