package edu.ncsu.csc440.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee extends Model {

	private int employeeId;
	private int dealershipId;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private double salary;

	public Employee(Connection connection, int dealershipId, String firstName, String lastName, String phone, String email, double salary) throws SQLException {
		super(connection);
		this.dealershipId = dealershipId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.salary = salary;

		String query = "INSERT INTO Employee (DealershipId, FirstName, LastName, Phone, Email, Salary) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, this.dealershipId);
		stmt.setString(2, this.firstName);
		stmt.setString(3, this.lastName);
		stmt.setString(4, this.phone);
		stmt.setString(5, this.email);
		stmt.setDouble(6, this.salary);
		stmt.executeUpdate();

		ResultSet resultSet = stmt.getGeneratedKeys();
		if (resultSet.next()) {
			this.employeeId = resultSet.getInt(1);
		}
	}
	public Employee(Connection connection, int employeeId) throws SQLException {
		super(connection);
		String query = "SELECT * from Employee where EmployeeId = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, employeeId);

		ResultSet resultSet = stmt.executeQuery();
		if (!resultSet.next()) {
			throw new IllegalArgumentException("Employee does not exist in the system!");
		}

		this.employeeId = resultSet.getInt("EmployeeId");
		this.dealershipId = resultSet.getInt("DealershipId");
		this.firstName = resultSet.getString("FirstName");
		this.lastName = resultSet.getString("LastName");
		this.phone = resultSet.getString("Phone");
		this.email = resultSet.getString("Email");
		this.salary = resultSet.getDouble("Salary");
	}

	// Now create setters. Those values can't be null.
	private int updateField(String field, String value, int limit) {
		if (value == null || value.length() > limit) {
			throw new IllegalArgumentException(field + " invalid");
		}

		try {
			final String query = "UPDATE Employee SET " + field + " = ? WHERE EmployeeId = ?";
			final PreparedStatement stmt = super.getConnection().prepareStatement(query);
			stmt.setString(1, value);
			stmt.setInt(2, this.employeeId);
			return stmt.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
	public void setFirstName(String firstName) throws SQLException {
		if (firstName == null) {
			throw new IllegalArgumentException("First name can't be null");
		}
		this.firstName = firstName;
		updateField("firstName", firstName, 50);
	}

	public void setLastName(String lastName) throws SQLException {
		if (lastName == null) {
			throw new IllegalArgumentException("Last name can't be null");
		}
		this.lastName = lastName;
		updateField("lastName", lastName, 50);
	}

	public void setPhone(String phone) throws SQLException {
		if (phone == null) {
			throw new IllegalArgumentException("Phone can't be null");
		}
		this.phone = phone;
		updateField("phone", phone, 15);
	}

	public void setEmail(String email) throws SQLException {
		if (email == null) {
			throw new IllegalArgumentException("Email can't be null");
		}
		this.email = email;
		updateField("email", email, 100);
	}

	public void setSalary(double salary) throws SQLException {
		if (salary < 0) {
			throw new IllegalArgumentException("Salary can't be less than 0");
		}
		this.salary = salary;
		updateField("salary", String.valueOf(salary), 20);
	}

	public void setDealershipId(int dealershipId) throws SQLException {
		if (dealershipId < 0) {
			throw new IllegalArgumentException("Dealership ID must be greater than 0");
		}
		this.dealershipId = dealershipId;
		updateField("dealershipId", String.valueOf(dealershipId), 10);
	}
	
	// these are getters
	public int getEmployeeid() {
		return employeeId;
	}
	public int getDealershipId() {
		return dealershipId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public double getSalary() {
		return salary;
	}
}
