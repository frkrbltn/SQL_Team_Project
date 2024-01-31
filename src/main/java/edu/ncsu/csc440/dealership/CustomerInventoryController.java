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

public class CustomerInventoryController {

    private Scene logoutScene, customerBuyPage1Scene, serviceCarScene;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnBuyACar;

    @FXML
    private Button btnServiceACar;

    public void setLogoutScene(Scene scene) {
        this.logoutScene = scene;
    }

    public void setBuyScene(Scene scene) {
        this.customerBuyPage1Scene = scene;
    }

    public void setServiceScene(Scene scene) {
        this.serviceCarScene = scene;
    }

    @FXML
    public void customerBuyPage1SceneSwitch(MouseEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(customerBuyPage1Scene);
    }

    @FXML
    public void serviceCarSceneSwitch(MouseEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(serviceCarScene);
    }

    @FXML
    public void logoutSceneSwitch(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(logoutScene);
    }
}