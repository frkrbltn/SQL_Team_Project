package edu.ncsu.csc440.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Model {
    public Manager(Connection connection) {
        super(connection);
    }
    public void addEmployee(String name, String pass) throws SQLException {
        // SQL statement to insert a new employee into the employee table
        String query = "INSERT INTO employee (name, password) VALUES (?, ?)";
        PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setString(1, name);
        stmt.setString(2, pass);
        stmt.executeUpdate();
    }
    public void deleteEmployee(String name) throws SQLException {
        // SQL statement to delete an employee from the employee table
        String query = "DELETE FROM employee WHERE name = ?";
        PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setString(1, name);
        stmt.executeUpdate();
    }
    public List<Employee> getEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee";
        PreparedStatement stmt = getConnection().prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int employeeId = rs.getInt("employeeId");
            Employee employee = new Employee(getConnection(), employeeId);
            employees.add(employee);
        }
        return employees;
    }

    public Employee getEmployee(int employeeId) throws SQLException {
        String query = "SELECT * FROM employee WHERE employeeId = ?";
        PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setInt(1, employeeId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Employee(getConnection(), employeeId);
        } else {
            throw new SQLException("Employee with ID " + employeeId + " not found.");
        }
    }
}
