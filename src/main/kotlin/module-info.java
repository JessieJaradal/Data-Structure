module com.example.data_structure {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires com.dlsc.formsfx;

    opens com.example.data_structure to javafx.fxml;
    exports com.example.data_structure;
}