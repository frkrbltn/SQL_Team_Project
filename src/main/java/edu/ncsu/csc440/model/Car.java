package edu.ncsu.csc440.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Car class representing an instance of a car for our project.
 */
public class Car extends Model {
    
    private int CarId;
    private String make;
    private String model;
    private int year;
    /**
     * Constructor for a Car object that exists in the database.
     * 
     * @param connection connection to the database
     * @param CarId the id of the car in the database
     * @throws SQLException if the sql query fails.
     * @throws IllegalArgumentException if no car with the given id exists in the system.
     */
    public Car(Connection connection, int CarId) throws SQLException {
        super(connection);
        String query = "SELECT * FROM Car WHERE CarId = ?";
        PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, CarId);
        ResultSet results = stmt.executeQuery();
        if (!results.next()){
            throw new IllegalArgumentException("Car doesn't exist in the system!");
        }
        setCarId(results.getInt("CarId"));
        setMake(results.getString("Make"));
        setModel(results.getString("Model"));
        setYear(results.getInt("Year"));
    }
    
    /**
     * Constructor for a new car object thatt will be added to the database.
     * 
     * @param connection connection to the database
     * @param make the cars make
     * @param model the cars model
     * @param year the cars manufacture year
     * @throws SQLException if the SQL query can't be executed
     */
    public Car(Connection connection, String make, String model, int year) throws SQLException {
        super(connection);
        setMake(make);
        setModel(model);
        setYear(year);
        String query = "INSERT INTO Car VALUES (NULL, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.make);
        stmt.setString(2, this.model);
        stmt.setInt(3, this.year);
        stmt.executeUpdate();
        ResultSet results = stmt.getGeneratedKeys();
        if (results.next()){
            setCarId(results.getInt(1));
        } else {
        	throw new IllegalArgumentException("Unable to add car");
        }
    }
    
    public String getMake() {
        return make;
    }

    private void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    private void setYear(int year) {
        this.year = year;
    }

    public int getCarId() {
        return CarId;
    }

    public void setCarId(int carId) {
        CarId = carId;
    }

    
}
