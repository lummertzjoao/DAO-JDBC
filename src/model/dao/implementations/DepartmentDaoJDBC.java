package model.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DB;
import database.DBException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection connection;

	public DepartmentDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Department department) {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("INSERT INTO department "
					+ "(Id, Name) " + "VALUES " + "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, department.getId());
			statement.setString(2, department.getName());
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
		}
	}

	@Override
	public void update(Department department) {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("UPDATE department " + "SET Name = ?, Id = ? " + "WHERE Id = ?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, department.getName());
			statement.setInt(2, department.getId());
			statement.setInt(3, department.getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("DELETE FROM department WHERE Id = ?");
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection
					.prepareStatement("SELECT department.* " + "FROM department " + "WHERE department.Id = ?");
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Department department = new Department();
				department.setId(resultSet.getInt("Id"));
				department.setName(resultSet.getString("Name"));
				return department;
			}
			return null;
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareStatement("SELECT department.* " + "FROM department " + "ORDER BY Name");

			resultSet = statement.executeQuery();

			List<Department> departments = new ArrayList<>();

			while (resultSet.next()) {
				Department department = new Department();
				department.setId(resultSet.getInt("Id"));
				department.setName(resultSet.getString("Name"));
				departments.add(department);
			}
			return departments;
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
			DB.closeResultSet(resultSet);
		}
	}

}
