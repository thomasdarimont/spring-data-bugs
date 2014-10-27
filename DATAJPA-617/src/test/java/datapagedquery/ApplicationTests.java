package datapagedquery;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import datapagedquery.domain.Customer;
import datapagedquery.repository.CustomerRepository;
import datapagedquery.repository.TestRepository;
import datapagedquery.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {

	static Logger log = Logger.getLogger(TestRepository.class.getName());

	@Autowired
	CustomerRepository repository;
	
	@Autowired
	CustomerService service;

	@Test
	public void testFindAllPaged() {
		PageRequest pr = new PageRequest(1, 10);
		Page<Customer> page = repository.findAll(pr);

		for (Customer c : page.getContent()) {
			log.info(c);
		}
	}

	@Test
	public void testFindbyNamePatternPaged() {
		PageRequest pr = new PageRequest(1, 10);
		String keyword = "%customer%";
		Page<Customer> page = repository.findByNamePattern(keyword, pr);

		for (Customer c : page.getContent()) {
			log.info(c);
		}
	}

	@Test
	public void testFindAllPagedViaService() {
		Page<Customer> page = service.findAll(1, 10);

		for (Customer c : page.getContent()) {
			log.info(c);
		}
	}

	@Test
	public void testFindByPatternPagedViaService() {
		Page<Customer> page = service.findByNamePatternPaged("customer", 1, 10);

		for (Customer c : page.getContent()) {
			log.info(c);
		}
	}

}
