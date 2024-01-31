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

public class ManagerAddEmployeePage1Controller {

    private Scene logoutScene, managerInventoryScene;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Button btnCancel;

    public void setLogoutScene(Scene scene) {
        this.logoutScene = scene;
    }

    public void setManagerInventoryScene(Scene scene) {
        this.managerInventoryScene = scene;
    }


    @FXML
    public void managerInventorySceneSwitch(MouseEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(managerInventoryScene);
    }


    @FXML
    public void logoutSceneSwitch(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(logoutScene);
    }
}