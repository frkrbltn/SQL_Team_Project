package edu.ncsu.csc440.model;

import java.sql.*;

/**
 * Object representing an InvoiceLine for use in an Invoice.
 */
public class InvoiceLine extends Model {
    /** The id of the invoiceline in the database */
    private int InvoiceLineId;
    /** The id of the dealership associated with the invoiceline */
    private int  InvoiceId;
    /** The id of the car associated with this invoiceline */
    private int CarId;
    /** The cost of the thing in the invoiceline */
    private float amount;
    /** The date that the invoiceline item was charged. */
    private Date date;

    /**
     * Constructor for an InvoiceLine object that exists in the database.
     * @param connection connection to the database
     * @param InvoiceLineId Id of the invoiceline in the database
     * @throws SQLException If the query was unable to execute
     */
    public InvoiceLine(Connection connection, int InvoiceLineId) throws SQLException {
        super(connection);

        String query = "SELECT * FROM invoiceline WHERE InvoiceLineId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, Integer.toString(InvoiceLineId));
        ResultSet results = stmt.executeQuery();
        if (!results.next()){
            throw new IllegalArgumentException("InvoiceLine doesn't exist");
        }
        setInvoiceLineId(results.getInt("InvoiceLineId"));
        setInvoiceId(results.getInt("InvoiceId"));
        setCarId(results.getInt("CarId"));
        setAmount(results.getFloat("Amount"));
        setDate(results.getDate("Date"));
    }

    /**
     * Constructor for creating a new invoiceline and adding it to the database
     * @param connection connection to the database
     * @param InvoiceId id of the invoice associated with the invoiceline
     * @param CarId id of the car associated with the invoiceline
     * @param Amount amount associated with the invoiceline
     * @param Date date that the invoiceline was charged.
     * @throws SQLException if the query was unable to execute.
     */
    public InvoiceLine(Connection connection, int DealershipId, int InvoiceId, int CarId, float Amount, Date Date) throws SQLException {
        super(connection);
        setInvoiceId(InvoiceId);
        setCarId(CarId);
        setAmount(Amount);
        setDate(Date);

        String query = "INSERT INTO serviceline VALUES (NULL, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, Integer.toString(InvoiceId));
        stmt.setString(2, Integer.toString(CarId));
        stmt.setString(3, Float.toString(Amount));
        stmt.setString(4, Date.toString());

        stmt.executeUpdate();
        ResultSet results = stmt.getGeneratedKeys();

        if (results.next()){
            setInvoiceLineId(results.getInt(1));
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

    public int getInvoiceLineId() {
        return InvoiceLineId;
    }

    public void setInvoiceLineId(int InvoiceLineId) {
        this.InvoiceLineId = InvoiceLineId;
    }

    public int getCarId() {
        return CarId;
    }

    public void setCarId(int carId) {
        CarId = carId;
    }

    public int getInvoiceId() {
        return InvoiceId;
    }

    public void setInvoiceId(int InvoiceId) {
        this.InvoiceId = InvoiceId;
    }


}
