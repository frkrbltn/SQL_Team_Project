package edu.ncsu.csc440.dealership;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

public class EmployeeInventoryController {

    private Scene logoutScene, employeeAddPage1Scene;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnAddCar;


    public void setLogoutScene(Scene scene) {
        this.logoutScene = scene;
    }

    public void setEmployeeAddPage1Scene(Scene scene) {
        this.employeeAddPage1Scene = scene;
    }


    @FXML
    public void employeeAddPage1SceneSwitch(MouseEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(employeeAddPage1Scene);
    }


    @FXML
    public void logoutSceneSwitch(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(logoutScene);
    }
}