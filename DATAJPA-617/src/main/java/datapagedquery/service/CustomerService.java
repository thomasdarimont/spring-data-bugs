package datapagedquery.service;

import org.springframework.data.domain.Page;

import datapagedquery.domain.Customer;

public interface CustomerService {
	
	Page<Customer> findAll(int pageNum, int pageSize);
	
	Page<Customer> findByNamePatternPaged(String keyword, int pageNum, int pageSize);
}
