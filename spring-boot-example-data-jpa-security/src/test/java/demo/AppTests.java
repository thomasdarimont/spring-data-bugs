package demo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.customer.Customer;
import demo.customer.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class AppTests {

	@Autowired CustomerRepository repository;

	@Before
	public void setup() {

		repository.save(new Customer("First1", "Last1"));
		repository.save(new Customer("First2", "Last2"));
	}

	@Test
	public void contextLoads() {

		List<Customer> result = repository.findByLastnameLike("Tom");

		System.out.println(result);
	}

}
