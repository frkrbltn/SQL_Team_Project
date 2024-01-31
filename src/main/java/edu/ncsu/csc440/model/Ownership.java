package edu.ncsu.csc440.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ownership extends Model {

    private int  customerid;
    private int  carid;
    private Date date;

    public Ownership ( Connection connection, int customerid, int carid, Date date ) {
        super( connection );
        try {

            String query = "SELECT FROM chinook.customer WHERE CustomerId = ?";
            final PreparedStatement stmt = super.getConnection().prepareStatement( query );
            stmt.setInt( 1, customerid );
            final ResultSet cusResults = stmt.executeQuery();

            final String query2 = "SELECT FROM chinook.car WHERE CarId = ?";
            final PreparedStatement stmt2 = super.getConnection().prepareStatement( query2 );
            stmt2.setInt( 1, carid );
            final ResultSet carResults = stmt2.executeQuery();

            if ( !cusResults.next() | !carResults.next() ) {
                throw new IllegalArgumentException( "Car/Customer Does Not Exist" );
            }

            query = "INSERT INTO dealership.ownership (...) VALUES (...)";
            final Statement stmt3 = super.getConnection().createStatement();
            final int result = stmt3.executeUpdate( query );

            if ( result == 1 ) {
                this.customerid = customerid;
                this.carid = carid;
                this.date = date;
            }

        }
        catch ( final SQLException e ) {
            e.printStackTrace();
        }
    }

    public int getCustomerid () {
        return customerid;
    }

    public int getCarid () {
        return carid;
    }

    public Date getDate () {
        return date;
    }

}
