package demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

@NamedStoredProcedureQueries({ //
@NamedStoredProcedureQuery(name = "Role.someNamedProcedure", procedureName = "procedure_in1_out0", parameters = { //
		@StoredProcedureParameter(name = "arg", mode = ParameterMode.IN, type = String.class) }) //
})
@Entity
public class Role {

	@Id @GeneratedValue//
	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
