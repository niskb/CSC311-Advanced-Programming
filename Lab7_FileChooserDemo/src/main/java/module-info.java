module bcs.csc311.lab7_filechooser {
    requires javafx.controls;
    requires javafx.fxml;

    opens bcs.csc311.lab7_filechooser to javafx.fxml;
    exports bcs.csc311.lab7_filechooser;
}
