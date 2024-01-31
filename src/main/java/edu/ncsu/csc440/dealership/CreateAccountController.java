package edu.ncsu.csc440.dealership;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreateAccountController {
    private Scene loginScene;

    @FXML
    private TextField tfldFirstName;
    @FXML
    private Label lblFirstName;
    @FXML
    private TextField tfldLastName;
    @FXML
    private Label lblLastName;
    @FXML
    private Label lblDateOfBirth;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblPassword;
    @FXML
    private Label lblRetypePassword;
    @FXML
    private TextField tfldDateOfBirth;
    @FXML
    private TextField tfldUsername;
    @FXML
    private Button btnCreateAccount;
    @FXML
    private TextField tfldEmail;
    @FXML
    private TextField tfldPassword;
    @FXML
    private TextField tfldRetypePassword;
    @FXML
    private Button btnCancel;

    public void setLoginScene(Scene scene) {
        this.loginScene = scene;
    }

    public void createAccount() {
        String username = tfldUsername.getText();
        String password = tfldPassword.getText();
        String retypedPassword = tfldRetypePassword.getText();
        String firstName = tfldFirstName.getText();
        String lastName = tfldLastName.getText();
        String email = tfldEmail.getText();
    }

    @FXML
    public void loginSwitch(MouseEvent actionEvent) {
        tfldFirstName.clear();
        tfldLastName.clear();
        tfldEmail.clear();
        tfldPassword.clear();
        tfldRetypePassword.clear();
        tfldUsername.clear();
        tfldDateOfBirth.clear();
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(loginScene);
    }
}
