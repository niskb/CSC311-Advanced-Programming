module bcs.csc.lab16_mtservergui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens bcs.csc.lab16_mtservergui to javafx.fxml;
    exports bcs.csc.lab16_mtservergui;
}
