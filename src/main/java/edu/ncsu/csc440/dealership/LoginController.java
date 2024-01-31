package edu.ncsu.csc440.dealership;

import edu.ncsu.csc440.DatabaseHandler.DatabaseHandler;
import edu.ncsu.csc440.model.Customer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {
    private Scene createAccountScene, customerInventoryScene, employeeInventoryScene, managerInventoryScene;

    private Customer cus;

    @FXML
    private Label lblPassword;

    @FXML
    private TextField tfldPassword;

    @FXML
    private Button btnCreateAccount;

    @FXML
    private TextField tfldUsername;

    @FXML
    private Button btnCustomerLogin;

    @FXML
    private Button btnEmployeeLogin;

    @FXML
    private Button btnManagerLogin;

    @FXML
    private Label lblUsername;

    @FXML
    public void login(MouseEvent actionEvent) {
        String username = tfldUsername.getText();
        String password = tfldPassword.getText();
        try {
            final Connection connection = DatabaseHandler.getInstance();
            cus = new Customer(connection, username, password);
            customerInventorySceneSwitch(actionEvent);
        } catch(IllegalArgumentException | SQLException e) {
            System.out.println("Access Denied B Word");
        }
    }

    public void setCreateAccountScene(Scene scene) {
        this.createAccountScene = scene;
    }

    public void setCustomerInventoryScene(Scene scene) {
        this.customerInventoryScene = scene;
    }

    public void setEmployeeInventoryScene(Scene scene) {
        this.employeeInventoryScene = scene;
    }

    public void setManagerInventoryScene(Scene scene) {
        this.managerInventoryScene = scene;
    }

    @FXML
    public void createAccountSceneSwitch(MouseEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(createAccountScene);
    }

    @FXML
    public void customerInventorySceneSwitch(MouseEvent actionEvent) { //login current function call
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(customerInventoryScene);
    }

    @FXML
    public void employeeInventorySceneSwitch(MouseEvent actionEvent) { //login current function call
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(employeeInventoryScene);
    }

    @FXML
    public void managerInventorySceneSwitch(MouseEvent actionEvent) { //login current function call
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(managerInventoryScene);
    }
}
