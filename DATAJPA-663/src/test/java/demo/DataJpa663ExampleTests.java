package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * This test case helps to reproduce the problem uncomment the {@link Transactional} on the {@link RoleRepository}
 * methods. When transactions are not properly set up then repetitive calls to such repository methods can exhaust the
 * database connection pool.
 * <p>
 * We set the spring.datasource.max-active to 1 on application.properties to make the problem directly visible.
 * 
 * @author Thomas Darimont
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DataJpa663Example.class)
public class DataJpa663ExampleTests {

	@Autowired RoleRepository repository;

	@Test
	public void callNamedProcedureMultipleTimes() {

		repository.someNamedProcedure("A");
		repository.someNamedProcedure("B");
		repository.someNamedProcedure("C");
	}

	@Test
	public void callAdHocProcedureMultilpeTimes() {

		repository.someAdHocProcedure("A");
		repository.someAdHocProcedure("B");
		repository.someAdHocProcedure("C");
	}
}
