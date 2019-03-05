package edu.uark.controllers;

import edu.uark.commands.employees.*;
import edu.uark.models.api.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeRestController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Employee> getEmployee()
	{
		return (new EmployeesQuery()).execute();
	}

	@RequestMapping(value = "/{recordId}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable UUID productId) {
		return (new EmployeeQuery()).
			setEmployeeId(productId).
			execute();
	}

	@RequestMapping(value = "/byrecordID/{employeeRecordID}", method = RequestMethod.GET)
	public Employee getEmployeeByLookupCode(@PathVariable String productLookupCode) {
		return (new EmployeeByLookupCodeQuery()).
			setLookupCode(productLookupCode).
			execute();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee product) {
		return (new EmployeeCreateCommand()).
			setApiEmployee(product).
			execute();
	}
	
	@RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT)
	public Employee update(@PathVariable UUID productId, @RequestBody Employee product) {
		return (new EmployeeUpdateCommand()).
			setEmployeeId(productId).
			setApiEmployee(product).
			execute();
	}
	
	@RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable UUID productId) {
		(new EmployeeDeleteCommand()).
			setEmployeeId(productId).
			execute();
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Successful test. (EmployeeRestController)";
	}
}
