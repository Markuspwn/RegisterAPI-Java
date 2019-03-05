package edu.uark.commands.employees;

import edu.uark.commands.VoidCommandInterface;
import edu.uark.controllers.exceptions.NotFoundException;
import edu.uark.models.entities.EmployeeEntity;
import edu.uark.models.repositories.EmployeeRepository;
import edu.uark.models.repositories.interfaces.EmployeeRepositoryInterface;

import java.util.UUID;

public class EmployeeDeleteCommand implements VoidCommandInterface {
	@Override
	public void execute() {
		EmployeeEntity employeeEntity = this.employeeRepository.get(this.productId);
		if (employeeEntity == null) { //No record with the associated record ID exists in the database.
			throw new NotFoundException("Employee");
		}

		employeeEntity.delete();
	}

	//Properties
	private UUID productId;
	public UUID getEmployeeId() {
		return this.productId;
	}
	public EmployeeDeleteCommand setEmployeeId(UUID productId) {
		this.productId = productId;
		return this;
	}
	
	private EmployeeRepositoryInterface employeeRepository;

	public EmployeeRepositoryInterface getEmployeeRepository()
	{
		return this.employeeRepository;
	}
	public EmployeeDeleteCommand setEmployeeRepository(EmployeeRepositoryInterface employeeRepository) {
		this.employeeRepository = employeeRepository;
		return this;
	}
	
	public EmployeeDeleteCommand()
	{
		this.employeeRepository = new EmployeeRepository();
	}
}
