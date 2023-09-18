module bcs.csc311.lab4_bouncing_ball {
    requires javafx.controls;
    requires javafx.fxml;

    opens bcs.csc311.lab4_bouncing_ball to javafx.fxml;
    exports bcs.csc311.lab4_bouncing_ball;
}
