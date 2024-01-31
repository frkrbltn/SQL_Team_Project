package edu.ncsu.csc440.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Service extends Model {
    private int serviceId;
    private int employeeId;
    private int carId;
    private int customerId;
    private Date serviceDate;
    private double total;

    public Service(Connection connection, int employeeId, Date serviceDate, double total) {
        super(connection);
        setEmployeeId(employeeId);
        setServiceDate(serviceDate);
        setTotal(total);

        try {
            String query = "INSERT INTO Service (EmployeeId, ServiceDate, Total) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, this.employeeId);
            stmt.setDate(2, new java.sql.Date(this.serviceDate.getTime()));
            stmt.setDouble(3, this.total);

            ResultSet results = stmt.getGeneratedKeys();
            if (results.next()) {
                setServiceId(results.getInt(1));
            } else {
                throw new SQLException("Service insertion failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


	public void setCarId(int carId) {
		this.carId = carId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

    // Now create setters. Those values can't be null.
    private void setServiceId(int serviceId) {
        // all service id must be greater than 0 and different from each other
        // if service id is less than 0, throw an exception
        if (serviceId < 0) {
            throw new IllegalArgumentException("Service ID must be greater than 0");
        }
        this.serviceId = serviceId;
    }

    public void setEmployeeId(int employeeId) {
        // all employee id must be greater than 0 and different from each other
        // if employee id is less than 0, throw an exception
        if (employeeId < 0) {
            throw new IllegalArgumentException("Employee ID must be greater than 0");
        }
        this.employeeId = employeeId;
    }

    public void setServiceDate(Date serviceDate) {
        // service date can't be null
        if (serviceDate == null) {
            throw new IllegalArgumentException("Service date can't be null");
        }
        this.serviceDate = serviceDate;
    }

    public void setTotal(double total) {
        // total can't be null
        if (total < 0) {
            throw new IllegalArgumentException("Total can't be null");
        }
        this.total = total;
    }
    
	// these are getters
    public int getServiceId() {
        return serviceId;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public Date getServiceDate() {
        return serviceDate;
    }
    public double getTotal() {
        return total;
    }
    public int getCarId() {
		return carId;
	}
	public int getCustomerId() {
		return customerId;
	}
}
