package edu.ncsu.csc440.core;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import edu.ncsu.csc440.DatabaseHandler.DatabaseHandler;
import edu.ncsu.csc440.model.Car;
import edu.ncsu.csc440.model.Customer;
import edu.ncsu.csc440.model.Dealership;
import edu.ncsu.csc440.model.Employee;

/**
 * Feel free to use this file to test your implementations.
 */

public class Demo {
    public static void main ( String[] args ) throws SQLException {
        final Connection connection = DatabaseHandler.getInstance();
        System.out.println( connection.getSchema() );
        Car car = new Car(connection, "ligma2", "bollz", 69);
        Dealership d = new Dealership(connection, "bollzzz", "NC", 1234);
        Date date = new Date(1, 1, 2002);
        Customer c = new Customer(connection, "mia", "a", date, "BoA", "123456789", "miaaa", "1234");
        Employee e = new Employee(connection, d.getDealershipId(), "mi", "a", "1111", "m@edu", 0);
        connection.close();
    }
}
