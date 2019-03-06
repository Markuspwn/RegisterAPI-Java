package edu.uark.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import edu.uark.dataaccess.entities.BaseEntity;
import edu.uark.dataaccess.repository.DatabaseTable;
import edu.uark.models.api.Employee;
import edu.uark.models.entities.fieldnames.EmployeeFieldNames;
import java.util.UUID;

//connects from fieldnames
public class EmployeeEntity extends BaseEntity<EmployeeEntity> {
	@Override

	protected void fillFromRecord(ResultSet rs) throws SQLException
	{
		//this.record_ID = rs.getString(EmployeeFieldNames.REC_ID);
		this.first_Name = rs.getString(EmployeeFieldNames.F_NAME);
		this.last_Name = rs.getString(EmployeeFieldNames.L_NAME);
		this.emp_ID = rs.getString(EmployeeFieldNames.EMP_ID);
		this.active_bool = rs.getString(EmployeeFieldNames.ACT_BOOL);
		this.role = rs.getInt(EmployeeFieldNames.ROLE);
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record)
	{
		// record.put(EmployeeFieldNames.REC_ID, this.record_ID);
		record.put(EmployeeFieldNames.F_NAME, this.first_Name);
		record.put(EmployeeFieldNames.L_NAME, this.last_Name);
		record.put(EmployeeFieldNames.EMP_ID, this.emp_ID);
		record.put(EmployeeFieldNames.ACT_BOOL, this.active_bool);
		record.put(EmployeeFieldNames.ROLE, this.role);

		return record;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Data type get, set methods.

	//Record ID
	//string

	//declare
	//private UUID record_ID;

	//get: method
	//public UUID getRecord_ID()
//	{
//		return this.record_ID;
//	}

	//set: method
//	public EmployeeEntity setRecord_ID(UUID record_ID)
//	{
//		if (!StringUtils.equals(this.record_ID, record_ID))
//		{
//			this.record_ID = record_ID;
//			this.propertyChanged(EmployeeFieldNames.REC_ID);
//		}
//
//		return this;
//	}

	//~~~
	//First name
	//string

	//declaration
	private String first_Name;

	//get: method
	public String getFirstName()
	{
		return this.first_Name;
	}

	//set:
	public EmployeeEntity setFirstName(String first_Name)
	{
		if (this.first_Name != first_Name)
		{
			this.first_Name = first_Name;
			this.propertyChanged(EmployeeFieldNames.F_NAME);
		}

		return this;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Last Name
	//string


	//declaration
	private String last_Name;

	//get: method
	public String getLastName()
	{
		return this.last_Name;
	}

	//set:
	public EmployeeEntity setLastName(String last_Name)
	{
		if (this.last_Name != last_Name)
		{
			this.last_Name = last_Name;
			this.propertyChanged(EmployeeFieldNames.L_NAME);
		}

		return this;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~
	//Employee Id
	//string

	//declaration
	private String emp_ID;

	//get: method
	public String getEmpID()
	{
		return this.emp_ID;
	}

	//set:
	public EmployeeEntity setEmpID(String emp_ID)
	{
		if (this.emp_ID != emp_ID)
		{
			this.emp_ID = emp_ID;
			this.propertyChanged(EmployeeFieldNames.EMP_ID);
		}

		return this;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~
	//Active Boolean
	//string

	//declaration
	private String active_bool;

	//get: method
	public String getBool()
	{
		return this.active_bool;
	}

	//set:
	public EmployeeEntity setBool(String active_bool)
	{
		if (this.active_bool != active_bool)
		{
			this.active_bool = active_bool;
			this.propertyChanged(EmployeeFieldNames.ACT_BOOL);
		}

		return this;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~
	//Role
	//int

	//declaration
	private int role;

	//get: method
	public int getRole()
	{
		return this.role;
	}

	//set:
	public EmployeeEntity setRole(int role)
	{
		if (this.role != role)
		{
			this.role = role;
			this.propertyChanged(EmployeeFieldNames.ROLE);
		}

		return this;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~

	//syncronize method
	public Employee synchronize(Employee apiEmployee)
	{

		//this.setRecord_ID(apiEmployee.getRecord_ID());

		this.setFirstName(apiEmployee.getFirstName());
		this.setLastName(apiEmployee.getLastName());
		this.setEmpID(apiEmployee.getLastName());
		this.setEmpID(apiEmployee.getEmpID());
		this.setBool(apiEmployee.getBool());
		this.setRole(apiEmployee.getRole());


		apiEmployee.setCreatedOn(this.getCreatedOn());

		return apiEmployee;
	}

	public EmployeeEntity()
	{
		super(DatabaseTable.EMPLOYEE);

		//default content
		//this.record_ID =  StringUtils.EMPTY;
		this.first_Name =  StringUtils.EMPTY;
		this.last_Name =  StringUtils.EMPTY;
		this.emp_ID =  StringUtils.EMPTY;
		this.active_bool =  StringUtils.EMPTY;
		this.role = -1;
	}

	public EmployeeEntity(Employee apiEmployee)
	{
		super(DatabaseTable.EMPLOYEE);

		//this.record_ID = apiEmployee.getRecord_ID();
		this.first_Name = apiEmployee.getFirstName();
		this.last_Name = apiEmployee.getLastName();
		this.emp_ID = apiEmployee.getEmpID();
		this.active_bool = apiEmployee.getBool();
		this.role = apiEmployee.getRole();

	}

}
