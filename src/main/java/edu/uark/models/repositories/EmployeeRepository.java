package edu.uark.models.repositories;

import edu.uark.dataaccess.repository.BaseRepository;
import edu.uark.dataaccess.repository.DatabaseTable;
import edu.uark.dataaccess.repository.helpers.PostgreFunctionType;
import edu.uark.dataaccess.repository.helpers.SQLComparisonType;
import edu.uark.dataaccess.repository.helpers.where.WhereClause;
import edu.uark.dataaccess.repository.helpers.where.WhereContainer;
import edu.uark.models.api.Employee;
import edu.uark.models.entities.EmployeeEntity;
import edu.uark.models.entities.fieldnames.EmployeeFieldNames;
import edu.uark.models.repositories.interfaces.EmployeeRepositoryInterface;

import java.sql.SQLException;

public class EmployeeRepository extends BaseRepository<EmployeeEntity> implements EmployeeRepositoryInterface {
	@Override
	public EmployeeEntity byLookupCode(String lookupCode) {
		return this.firstOrDefaultWhere(
			new WhereContainer(
				(new WhereClause()).
					postgreFunction(PostgreFunctionType.LOWER).
					table(this.primaryTable).
					fieldName(EmployeeFieldNames.EMP_ID).
					comparison(SQLComparisonType.EQUALS)
			),
			(ps) -> {
				try {
					ps.setObject(1, lookupCode.toLowerCase());
				} catch (SQLException e) {}

				return ps;
			}
		);
	}

	@Override
	public EmployeeEntity createOne() {
		return new EmployeeEntity();
	}

	public EmployeeRepository() {
		super(DatabaseTable.EMPLOYEE);
	}
}
