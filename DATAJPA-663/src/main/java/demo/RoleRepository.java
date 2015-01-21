package demo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

interface RoleRepository extends CrudRepository<Role, Serializable> {

	@Transactional
	@Procedure
	void someNamedProcedure(@Param("arg") String arg);

	@Transactional
	@Procedure(procedureName = "procedure_in1_out0")
	void someAdHocProcedure(String arg);
}
