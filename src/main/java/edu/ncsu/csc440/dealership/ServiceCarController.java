package edu.ncsu.csc440.dealership;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

public class ServiceCarController {
    @FXML
    private Button serviceButton;
    @FXML
    private TableView dealershipTable;
    @FXML
    private TableView carTable;
    @FXML
    private TableView serviceTable;
    @FXML
    private DatePicker serviceDate;

    @FXML
    public void serviceCar() {
        System.out.println("SERVICE CAR BUTTON WAS PRESSED");
    }

}
