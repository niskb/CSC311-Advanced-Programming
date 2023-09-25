module bcs.csc311.lab6_tableview {
    requires javafx.controls;
    requires javafx.fxml;

    opens bcs.csc311.lab6_tableview to javafx.fxml;
    exports bcs.csc311.lab6_tableview;
}
