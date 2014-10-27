package datapagedquery.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import datapagedquery.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByName(String name);

	Page<Customer> findByNamePattern(@Param("pattern") String pattern, Pageable pageable);
}
