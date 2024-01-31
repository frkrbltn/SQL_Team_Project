package edu.ncsu.csc440.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Inventory extends Model {

    private int dealershipid;
    private int carid;

    public Inventory ( Connection connection, int dealershipid, int carid ) {
        super( connection );
        try {

            String query = "SELECT FROM chinook.dealer WHERE DealershipId = ?";
            final PreparedStatement stmt = super.getConnection().prepareStatement( query );
            stmt.setInt( 1, dealershipid );
            final ResultSet delResults = stmt.executeQuery();

            final String query2 = "SELECT FROM chinook.car WHERE CarId = ?";
            final PreparedStatement stmt2 = super.getConnection().prepareStatement( query2 );
            stmt2.setInt( 1, carid );
            final ResultSet carResults = stmt2.executeQuery();

            if ( !delResults.next() | !carResults.next() ) {
                throw new IllegalArgumentException( "Car/Dealership Does Not Exist" );
            }

            query = "INSERT INTO dealership.inventory (...) VALUES (...)";
            final Statement stmt3 = super.getConnection().createStatement();
            final int result = stmt3.executeUpdate( query );

            if ( result == 1 ) {
                this.dealershipid = dealershipid;
                this.carid = carid;
            }

        }
        catch ( final SQLException e ) {
            e.printStackTrace();
        }
    }

    public int getDealershipid () {
        return dealershipid;
    }

    public int getCarid () {
        return carid;
    }

}
