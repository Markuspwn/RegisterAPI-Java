package edu.uark.commands.employees;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.controllers.exceptions.ConflictException;
import edu.uark.controllers.exceptions.UnprocessableEntityException;
import edu.uark.models.api.Employee;
import edu.uark.models.entities.EmployeeEntity;
import edu.uark.models.repositories.EmployeeRepository;
import edu.uark.models.repositories.interfaces.EmployeeRepositoryInterface;
import org.apache.commons.lang3.StringUtils;

public class EmployeeCreateCommand implements ResultCommandInterface<Employee> {
	@Override
	public Employee execute() {
		//Validations
		if (StringUtils.isBlank(this.apiEmployee.getEmpID())) {
			throw new UnprocessableEntityException("lookupcode");
		}

		EmployeeEntity employeeEntity = this.employeeRepository.byLookupCode(this.apiEmployee.getEmpID());
		if (employeeEntity != null) {
			throw new ConflictException("Record ID"); //Lookupcode already defined for another product.
		}
		
		//No ENTITY object was returned from the database, thus the API object's lookupcode must be unique.
		employeeEntity = new EmployeeEntity(apiEmployee); //Create a new ENTITY object from the API object details.
		employeeEntity.save(); //Write, via an INSERT, the new record to the database.
		
		this.apiEmployee.setRecord_ID(employeeEntity.getRecord_ID()); //Synchronize information generated by the database upon INSERT.
		this.apiEmployee.setCreatedOn(employeeEntity.getCreatedOn());

		return this.apiEmployee;
	}

	//Properties
	private Employee apiEmployee;
	public Employee getApiEmployee() {
		return this.apiEmployee;
	}
	public EmployeeCreateCommand setApiEmployee(Employee apiEmployee) {
		this.apiEmployee = apiEmployee;
		return this;
	}
	
	private EmployeeRepositoryInterface employeeRepository;
	public EmployeeRepositoryInterface getEmployeeRepository() {
		return this.employeeRepository;
	}
	public EmployeeCreateCommand setProductRepository(EmployeeRepositoryInterface employeeRepository) {
		this.employeeRepository = employeeRepository;
		return this;
	}
	
	public EmployeeCreateCommand() {
		this.employeeRepository = new EmployeeRepository();
	}
}