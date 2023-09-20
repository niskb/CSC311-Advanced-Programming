module bcs.csc311.lab5_multimedia {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens bcs.csc311.lab5_multimedia to javafx.fxml;
    exports bcs.csc311.lab5_multimedia;
}
