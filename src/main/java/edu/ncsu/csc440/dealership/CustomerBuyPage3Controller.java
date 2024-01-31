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

public class CustomerBuyPage3Controller {

    private Scene logoutScene, customerBuyPage4Scene;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnBuyACar;

    public void setLogoutScene(Scene scene) {
        this.logoutScene = scene;
    }

    public void setNextPageScene(Scene scene) {
        this.customerBuyPage4Scene = scene;
    }

    @FXML
    public void nextPageSceneSwitch(MouseEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(customerBuyPage4Scene);
    }

    @FXML
    public void logoutSceneSwitch(MouseEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(logoutScene);
    }
}