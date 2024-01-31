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

public class EmployeeAddPage1Controller {

    private Scene logoutScene, employeeAddPage2Scene, employeeInventoryScene;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnAddCar;

    @FXML
    private Button btnCancel;

    public void setLogoutScene(Scene scene) {
        this.logoutScene = scene;
    }

    public void setNextPageScene(Scene scene) {
        this.employeeAddPage2Scene = scene;
    }

    public void setEmployeeInventoryScene(Scene scene) {
        this.employeeInventoryScene = scene;
    }

    @FXML
    public void nextPageSceneSwitch(MouseEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(employeeAddPage2Scene);
    }

    @FXML
    public void employeeInventorySceneSwitch(MouseEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(employeeInventoryScene);
    }

    @FXML
    public void logoutSceneSwitch(MouseEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(logoutScene);
    }
}