
package edu.ncsu.csc440.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/** 
 * Class representing a Dealership for our project.
*/
public class Dealership extends Model{
    /** Id of the dealership in the database */
    private int DealershipId;
    /** Name of the dealership */
    private String name;
    /** Address of the dealership */
    private String location;
    /** The dealership's phone number */
    private int phone;
    /** The dealership's phone number */
    private List<Car> cars = new ArrayList<Car>();


    /**
     * Constructor for a dealership that exists in the database.
     * 
     * @param connection connection to the database
     * @param dealershipId id of the dealership in the database
     * @throws SQLException if the query cannot be executed
     */
    public Dealership(Connection connection, int dealershipId) throws SQLException {
        super(connection);
        String query = "SELECT * FROM Dealership WHERE DealershipId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, Integer.toString(dealershipId));
        ResultSet results = stmt.executeQuery();
        if (!results.next()){
            throw new IllegalArgumentException("Dealership doesn't exist in system!");
        }
        setDealershipId(results.getInt("DealershipId"));
        setLocation(results.getString("Location"));
        setPhone(results.getInt("Phone"));

    }


    /**
     * Constructor for a new dealership. The new dealership is added to the database.
     * 
     * @param connection connection to the database
     * @param name the name of the dealership
     * @param location the address of the dealership
     * @param phone the phone number of the dealership
     * @throws SQLException if the query cannot be executed
     */
    public Dealership(Connection connection, String name, String location, int phone) throws SQLException {
        super(connection);
        setName(name);
        setLocation(location);
        setPhone(phone);

        String query = "INSERT INTO dealership VALUES (NULL, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.name);
        stmt.setString(2, this.location);
        stmt.setString(3, Integer.toString(this.phone));
        stmt.executeUpdate();
        ResultSet results = stmt.getGeneratedKeys();
        if (results.next()){
            setDealershipId(results.getInt(1));
        }

    }
    
    public Car addCarToInventory(String make, String model, int year) {    	
    	try {
			Car car = new Car(this.getConnection(), make, model, year);
			
			String query = "INSERT INTO Inventory (DealershipId, CarId) VALUES (?, ?)";
			final PreparedStatement stmt = super.getConnection().prepareStatement( query );
            stmt.setInt( 1, this.getDealershipId() );
            stmt.setInt( 1, car.getCarId() );
            final ResultSet results = stmt.executeQuery();

            if ( results.next() ) {
            	cars.add(car);
            	return car;
            }
            return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public boolean removeCarFromInventory(int carId) {
        try {
            String query = "DELETE FROM Inventory WHERE DealershipId = ? AND CarId = ?";
            try (PreparedStatement stmt = super.getConnection().prepareStatement(query)) {
                stmt.setInt(1, this.DealershipId);
                stmt.setInt(2, carId);

                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public int getDealershipId() {
        return DealershipId;
    }

    public void setDealershipId(int dealershipId) {
        DealershipId = dealershipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public List<Car> getCars() {
        return cars;
    }
}
