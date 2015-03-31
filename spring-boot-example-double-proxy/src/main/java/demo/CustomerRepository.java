package demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@PreAuthorize("hasRole('ADMIN')")
	List<Customer> findByLastnameLike(String lastname);
}
