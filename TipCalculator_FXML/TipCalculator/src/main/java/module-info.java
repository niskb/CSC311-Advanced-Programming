module bcs.csc311.tipcalculator {
    requires javafx.controls;
    requires javafx.fxml;

    opens bcs.csc311.tipcalculator to javafx.fxml;
    exports bcs.csc311.tipcalculator;
}
