package edu.ncsu.csc440.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class Customer extends Model {

    private int customerId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String bank;
    private String creditCard;
    private List<Car> ownedCars;

    private String username;
    private String password;

    public Customer(Connection connection, int customerId) throws SQLException {
        super(connection);
        String query = "SELECT * from Customer where CustomerId = ?";
        PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, customerId);
        ResultSet result = stmt.executeQuery();
        if (!result.next()) {
            throw new IllegalArgumentException("Customer doesn't exist in system!");
        }
        this.customerId = result.getInt("CustomerId");
        this.firstName = result.getString("FirstName");
        this.lastName = result.getString("LastName");
        this.birthDate = result.getDate("BirthDate");
        this.bank = result.getString("Bank");
        this.creditCard = result.getString("CreditCard");
        ownedCars = new ArrayList<>();
    }

    public Customer(Connection connection, String username, String password) throws SQLException {
        super(connection);
        String query = "select * from customer where UserName = ? and Password = ?;";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet result = stmt.executeQuery();
        if (!result.next()) {
            throw new IllegalArgumentException("Customer doesn't exist in system!");
        }
        this.customerId = result.getInt("CustomerId");
        this.firstName = result.getString("FirstName");
        this.lastName = result.getString("LastName");
        this.birthDate = result.getDate("BirthDate");
        this.bank = result.getString("Bank");
        this.creditCard = result.getString("CreditCard");
        // FIX to load cars from sql database
        ownedCars = new ArrayList<>();
    }

    public Customer(Connection connection, String firstName, String lastName, Date birthDate, String bank,
                    String creditCard, String userName, String password) throws SQLException {
        super(connection);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.bank = bank;
        this.creditCard = creditCard;
        this.username = userName;
        this.password = password;

        String query = "INSERT INTO Customer (FirstName, LastName, BirthDate, Bank, CreditCard) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.firstName);
        stmt.setString(2, this.lastName);
        stmt.setDate(3, this.birthDate);
        stmt.setString(4, this.bank);
        stmt.setString(5, this.creditCard);
        stmt.executeUpdate();
        ResultSet results = stmt.getGeneratedKeys();
        if (results.next()) {
            setCustomerId(results.getInt(1));
            ownedCars = new ArrayList<>();
        }
    }
    
    /*
     * TODO
     */
    public void makeCarPurchase(Car car, Dealership dealership) throws SQLException {
        // Questions: Should we have a class and a method to remove the purchase car from dealerhip inventory
        // If I need to remove it, do we have to remove first then add the car (I remember Teacher mention we have to
        // in some order but I dont remember it well)
        // teacher also mentioned about something transactional statements. Should I add that transactional statemetns too
        // In case I added them. We can change them later on
        getConnection().setAutoCommit(false);

        try {
            // Check if the car is available at the dealership
            String checkQuery = "SELECT * FROM Inventory WHERE CarId = ? AND DealershipId = ?";
            PreparedStatement checkStmt = getConnection().prepareStatement(checkQuery);
            checkStmt.setInt(1, car.getCarId());
            checkStmt.setInt(2, dealership.getDealershipId());
            ResultSet checkResult = checkStmt.executeQuery();
            if (!checkResult.next()) {
                throw new IllegalArgumentException("Car not available at the Dealership.");
            }

//            String removeQuery = "DELETE FROM Inventory WHERE CarId = ? AND DealershipId = ?";
//            PreparedStatement removeStmt = getConnection().prepareStatement(removeQuery);
//            removeStmt.setInt(1, car.getCarId());
//            removeStmt.setInt(2, dealership.getDealershipId());
//            removeStmt.executeUpdate();

            ownedCars.add(car);

            getConnection().commit();
        } catch (SQLException e) {
            getConnection().rollback();
            throw e;
        } finally {
            getConnection().setAutoCommit(true);
        }
    }

    private int updateField(String field, String value, int limit) throws SQLException {
        if (value == null || value.length() > limit) {
            throw new IllegalArgumentException(field + " exceeds limit or is null");
        }

        final String query = "UPDATE Customer SET " + field + " = ? WHERE CustomerId = ?";
        final PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setString(1, value);
        stmt.setInt(2, this.customerId);

        return stmt.executeUpdate();
    }

    private void setFirstName(String firstName) throws SQLException {
        if (updateField("FirstName", firstName, 50) > 0) {
            this.firstName = firstName;
        }
    }

    private void setLastName(String lastName) throws SQLException {
        if (updateField("LastName", lastName, 50) > 0) {
            this.lastName = lastName;
        }
    }

    private void setBirthDate(Date birthDate) throws SQLException {
        final String query = "UPDATE Customer SET BirthDate = ? WHERE CustomerId = ?";
        final PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setDate(1, birthDate);
        stmt.setInt(2, this.customerId);
        stmt.executeUpdate();
    }

    private void setBank(String bank) throws SQLException {
        if (updateField("bank", bank, 100) > 0) {
            this.bank = bank;
        }
    }

    private void setCreditCard(String creditCard) throws SQLException {
        if (updateField("creditCard", creditCard, 19) > 0) {
            this.creditCard = creditCard;
        }
    }

    private void setCustomerId(int customerId) throws SQLException {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Customer ID must be positive.");
        }
        if (updateField("CustomerId", Integer.toString(customerId), 10) > 0) {
            this.customerId = customerId;
        }
    }
    /*
        These 2 setters were written by James Clyburn. They may need further modifications
     */
    private void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getBank() {
        return bank;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public List<Car> getOwnedCars() {
        return ownedCars;
    }
    /*
    The following code was written by James Clyburn. May not be correct, please check
     */
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void loadOwnedCars() throws SQLException {
        ownedCars.clear(); // Clear the current list of owned cars
        String query = "SELECT CarId FROM ownership WHERE CustomerId = ?";
        PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setInt(1, this.customerId);

        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            int carId = resultSet.getInt("CarId");
            Car car = new Car(getConnection(), carId); // Assuming you have a constructor in Car class that loads car details using carId
            ownedCars.add(car);
        }
    }

}