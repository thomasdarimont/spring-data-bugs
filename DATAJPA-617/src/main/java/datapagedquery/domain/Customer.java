package datapagedquery.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
    @NamedQuery(
            name = "Customer.findByNamePattern",
            query = "select c from Customer c where c.name like :pattern"
        )    
}) 
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
