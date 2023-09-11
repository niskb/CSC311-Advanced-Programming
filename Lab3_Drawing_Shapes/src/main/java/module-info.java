module bcs.csc311.lab3_drawing_shapes {
    requires javafx.controls;
    requires javafx.fxml;

    opens bcs.csc311.lab3_drawing_shapes to javafx.fxml;
    exports bcs.csc311.lab3_drawing_shapes;
}
