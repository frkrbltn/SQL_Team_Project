module edu.ncsu.csc440.dealership {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    exports edu.ncsu.csc440.dealership;
    opens edu.ncsu.csc440.dealership to javafx.fxml;
}