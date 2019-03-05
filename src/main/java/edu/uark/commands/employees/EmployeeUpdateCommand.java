package edu.uark.commands.employees;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.controllers.exceptions.NotFoundException;
import edu.uark.controllers.exceptions.UnprocessableEntityException;
import edu.uark.models.api.Employee;
import edu.uark.models.entities.EmployeeEntity;
import edu.uark.models.repositories.EmployeeRepository;
import edu.uark.models.repositories.interfaces.EmployeeRepositoryInterface;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class EmployeeUpdateCommand implements ResultCommandInterface<Employee> {
	@Override
	public Employee execute() {
		//Validations
		if (StringUtils.isBlank(this.apiEmployee.getEmpID())) {
			throw new UnprocessableEntityException("employee ID");
		}

		EmployeeEntity employeeEntity = this.employeeRepository.get(this.productId);
		if (employeeEntity == null) { //No record with the associated record ID exists in the database.
			throw new NotFoundException("Employee");
		}
		
		this.apiEmployee = employeeEntity.synchronize(this.apiEmployee); //Synchronize any incoming changes for UPDATE to the database.
		
		employeeEntity.save(); //Write, via an UPDATE, any changes to the database.
		
		return this.apiEmployee;
	}

	//Properties
	private UUID productId;
	public UUID getEmployeeId() {
		return this.productId;
	}
	public EmployeeUpdateCommand setEmployeeId(UUID productId) {
		this.productId = productId;
		return this;
	}
	
	private Employee apiEmployee;
	public Employee getApiEmployee() {
		return this.apiEmployee;
	}
	public EmployeeUpdateCommand setApiEmployee(Employee apiEmployee) {
		this.apiEmployee = apiEmployee;
		return this;
	}
	
	private EmployeeRepositoryInterface employeeRepository;
	public EmployeeRepositoryInterface getEmployeeRepository() {
		return this.employeeRepository;
	}
	public EmployeeUpdateCommand setEmployeeRepository(EmployeeRepositoryInterface employeeRepository) {
		this.employeeRepository = employeeRepository;
		return this;
	}
	
	public EmployeeUpdateCommand()
	{
		this.employeeRepository = new EmployeeRepository();
	}
}
