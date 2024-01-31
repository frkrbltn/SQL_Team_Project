package edu.ncsu.csc440.dealership;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WolfACarApplication extends Application {
    Scene loginScene, createAccountScene, customerInventoryScene, serviceCarScene, customerBuyPage1Scene, customerBuyPage2Scene, customerBuyPage3Scene, customerBuyPage4Scene, employeeInventoryScene, employeeAddPage1Scene, employeeAddPage2Scene, managerInventoryScene, managerAddDealershipPage1Scene, managerAddEmployeePage1Scene;

    @Override
    public void start(Stage stage) throws IOException {
        //login
        FXMLLoader loginLoader = new FXMLLoader(WolfACarApplication.class.getResource("login.fxml"));
        loginScene = new Scene(loginLoader.load());
        LoginController loginController = loginLoader.getController();

        //create account
        FXMLLoader createAccountLoader = new FXMLLoader(WolfACarApplication.class.getResource("create-account.fxml"));
        createAccountScene = new Scene(createAccountLoader.load());
        CreateAccountController createAccountController = createAccountLoader.getController();

        //customer inventory (customer login)
        FXMLLoader customerInventoryLoader = new FXMLLoader(WolfACarApplication.class.getResource("customer-inventory.fxml"));
        customerInventoryScene = new Scene(customerInventoryLoader.load());
        CustomerInventoryController customerInventoryController = customerInventoryLoader.getController();

        //customer buy page 1
        FXMLLoader customerBuyPage1Loader = new FXMLLoader(WolfACarApplication.class.getResource("customer-buy-1.fxml"));
        customerBuyPage1Scene = new Scene(customerBuyPage1Loader.load());
        CustomerBuyPage1Controller customerBuyPage1Controller = customerBuyPage1Loader.getController();

        //customer buy page 2
        FXMLLoader customerBuyPage2Loader = new FXMLLoader(WolfACarApplication.class.getResource("customer-buy-2.fxml"));
        customerBuyPage2Scene = new Scene(customerBuyPage2Loader.load());
        CustomerBuyPage2Controller customerBuyPage2Controller = customerBuyPage2Loader.getController();

        //customer buy page 3
        FXMLLoader customerBuyPage3Loader = new FXMLLoader(WolfACarApplication.class.getResource("customer-buy-3.fxml"));
        customerBuyPage3Scene = new Scene(customerBuyPage3Loader.load());
        CustomerBuyPage3Controller customerBuyPage3Controller = customerBuyPage3Loader.getController();

        //customer buy page 4
        FXMLLoader customerBuyPage4Loader = new FXMLLoader(WolfACarApplication.class.getResource("customer-buy-4.fxml"));
        customerBuyPage4Scene = new Scene(customerBuyPage4Loader.load());
        CustomerBuyPage4Controller customerBuyPage4Controller = customerBuyPage4Loader.getController();

        //service car
        FXMLLoader serviceCarLoader = new FXMLLoader(WolfACarApplication.class.getResource("service-car.fxml"));
        serviceCarScene = new Scene(serviceCarLoader.load());
        ServiceCarController serviceCarController = serviceCarLoader.getController();

        //employee inventory (employee login)
        FXMLLoader employeeInventoryLoader = new FXMLLoader(WolfACarApplication.class.getResource("employee-inventory.fxml"));
        employeeInventoryScene = new Scene(employeeInventoryLoader.load()); //TODO: use this scene to add at login page
        EmployeeInventoryController employeeInventoryController = employeeInventoryLoader.getController();

        //employee add page 1
        FXMLLoader employeeAddPage1Loader = new FXMLLoader(WolfACarApplication.class.getResource("employee-add-1.fxml"));
        employeeAddPage1Scene = new Scene(employeeAddPage1Loader.load());
        EmployeeAddPage1Controller employeeAddPage1Controller = employeeAddPage1Loader.getController();

        //employee add page 2
        FXMLLoader employeeAddPage2Loader = new FXMLLoader(WolfACarApplication.class.getResource("employee-add-2.fxml"));
        employeeAddPage2Scene = new Scene(employeeAddPage2Loader.load());
        EmployeeAddPage2Controller employeeAddPage2Controller = employeeAddPage2Loader.getController();

        //manager inventory (manager login)
        FXMLLoader managerInventoryLoader = new FXMLLoader(WolfACarApplication.class.getResource("manager-inventory.fxml"));
        managerInventoryScene = new Scene(managerInventoryLoader.load()); //TODO: use this scene to add at login page
        ManagerInventoryController managerInventoryController = managerInventoryLoader.getController();

        //manager add dealership page 1
        FXMLLoader managerAddDealershipPage1Loader = new FXMLLoader(WolfACarApplication.class.getResource("manager-add-dealership-1.fxml"));
        managerAddDealershipPage1Scene = new Scene(managerAddDealershipPage1Loader.load());
        ManagerAddDealershipPage1Controller managerAddDealershipPage1Controller = managerAddDealershipPage1Loader.getController();

        //manager add employee page 1
        FXMLLoader managerAddEmployeePage1Loader = new FXMLLoader(WolfACarApplication.class.getResource("manager-add-employee-1.fxml"));
        managerAddEmployeePage1Scene = new Scene(managerAddEmployeePage1Loader.load());
        ManagerAddEmployeePage1Controller managerAddEmployeePage1Controller = managerAddEmployeePage1Loader.getController();


        //main pages controllers
        loginController.setCreateAccountScene(createAccountScene);
        loginController.setCustomerInventoryScene(customerInventoryScene);
        loginController.setEmployeeInventoryScene(employeeInventoryScene);
        loginController.setManagerInventoryScene(managerInventoryScene); //TODO : create
        createAccountController.setLoginScene(loginScene);

        //customer
        customerInventoryController.setLogoutScene(loginScene);
        customerInventoryController.setBuyScene(customerBuyPage1Scene);
        customerInventoryController.setServiceScene(serviceCarScene);

        //customer - buying controllers
        customerBuyPage1Controller.setLogoutScene(loginScene);
        customerBuyPage1Controller.setNextPageScene(customerBuyPage2Scene);

        customerBuyPage2Controller.setLogoutScene(loginScene);
        customerBuyPage2Controller.setNextPageScene(customerBuyPage3Scene);

        customerBuyPage3Controller.setLogoutScene(loginScene);
        customerBuyPage3Controller.setNextPageScene(customerBuyPage4Scene);

        customerBuyPage4Controller.setLogoutScene(loginScene);
        customerBuyPage4Controller.setNextPageScene(customerInventoryScene);

        //employee
        employeeInventoryController.setEmployeeAddPage1Scene(employeeAddPage1Scene);
        employeeInventoryController.setLogoutScene(loginScene);

        //employee - adding controllers
        employeeAddPage1Controller.setEmployeeInventoryScene(employeeInventoryScene);
        employeeAddPage1Controller.setNextPageScene(employeeAddPage2Scene);
        employeeAddPage1Controller.setLogoutScene(loginScene);

        employeeAddPage2Controller.setNextPageScene(employeeInventoryScene);
        employeeAddPage2Controller.setLogoutScene(loginScene);

        //manager
        managerInventoryController.setStaffAddDealershipPage1Scene(managerAddDealershipPage1Scene);
        managerInventoryController.setStaffAddEmployeePage1Scene(managerAddEmployeePage1Scene);
        managerInventoryController.setLogoutScene(loginScene);

        //manager - adding controllers
        managerAddDealershipPage1Controller.setManagerInventoryScene(managerInventoryScene);
        managerAddDealershipPage1Controller.setLogoutScene(loginScene);

        managerAddEmployeePage1Controller.setManagerInventoryScene(managerInventoryScene);
        managerAddEmployeePage1Controller.setLogoutScene(loginScene);

        //this
        stage.setTitle("WolfACar");
        stage.setScene(loginScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
