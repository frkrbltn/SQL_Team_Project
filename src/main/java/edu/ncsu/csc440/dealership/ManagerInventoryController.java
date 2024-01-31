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

public class ManagerInventoryController {

    private Scene logoutScene, managerAddDealershipPage1Scene, manangerAddEmployeePage1Scene;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnAddDealership;

    @FXML
    private Button btnAddEmployee;


    public void setLogoutScene(Scene scene) {
        this.logoutScene = scene;
    }

    public void setStaffAddDealershipPage1Scene(Scene scene) {
        this.managerAddDealershipPage1Scene = scene;
    }

    public void setStaffAddEmployeePage1Scene(Scene scene) {
        this.manangerAddEmployeePage1Scene = scene;
    }

    @FXML
    public void staffAddDealershipPage1SceneSwitch(MouseEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(managerAddDealershipPage1Scene);
    }

    @FXML
    public void setStaffAddEmployeePage1Scene(MouseEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(manangerAddEmployeePage1Scene);
    }


    @FXML
    public void logoutSceneSwitch(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(logoutScene);
    }
}