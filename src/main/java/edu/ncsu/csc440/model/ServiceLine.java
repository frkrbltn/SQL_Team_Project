package edu.ncsu.csc440.model;

import java.sql.*;
/**
 * Object representing a ServiceLine for use in an Service.
 */
public class ServiceLine extends Model {
    /** The id of the serviceline in the database */
    private int ServiceLineId;
    /** The id of the service associated with the serviceline */
    private int ServiceId;
    /** The cost of the thing in the serviceline */
    private float amount;
    /** The date that the serviceline item was charged. */
    private Date date;

    /**
     * Constructor for creating a ServiceLine object when the serviceline exists in the database
     * @param connection connection to the database
     * @param ServiceLineId Id of the serviceline in the database
     * @throws SQLException if the query cannot execute
     */
    public ServiceLine(Connection connection, int ServiceLineId) throws SQLException {
        super(connection);

        String query = "SELECT * FROM serviceline WHERE ServiceLineId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, Integer.toString(ServiceLineId));
        ResultSet results = stmt.executeQuery();
        if (!results.next()){
            throw new IllegalArgumentException("ServiceLine doesn't exist");
        }
        setServiceLineId(results.getInt("ServiceLineId"));
        setServiceId(results.getInt("ServiceId"));
        setAmount(results.getFloat("Amount"));
        setDate(results.getDate("Date"));
    }

    /**
     * Constructor for creating a new serviceline and adding it to the database.
     * @param connection connection to the database
     * @param ServiceId id of the service it is associated with
     * @param CarId id of the car it is associated with
     * @param CustomerId id of the customer it is associated with
     * @param Amount price of the service for the serviceline
     * @param Date date that the service was charged.
     * @throws SQLException if the query cannot execute.
     */
    public ServiceLine(Connection connection, int ServiceId, int CarId, int CustomerId, float Amount, Date Date) throws SQLException {
        super(connection);
        setServiceId(ServiceId);
        setAmount(Amount);
        setDate(Date);

        String query = "INSERT INTO serviceline VALUES (NULL, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, Integer.toString(ServiceId));
        stmt.setString(2, Float.toString(Amount));
        stmt.setString(3, Date.toString());

        stmt.executeUpdate();
        ResultSet results = stmt.getGeneratedKeys();

        if (results.next()){
            setServiceLineId(results.getInt(1));
        }

    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        if (!(amount > 0)){
            throw new IllegalArgumentException("The amount cannot be less than or equal to 0");
        }
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getServiceLineId() {
        return ServiceLineId;
    }

    public void setServiceLineId(int serviceLineId) {
        ServiceLineId = serviceLineId;
    }

    public int getServiceId() {
        return ServiceId;
    }

    public void setServiceId(int serviceId) {
        ServiceId = serviceId;
    }


}
