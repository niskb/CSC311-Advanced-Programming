module niski.farmingdale {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens niski.farmingdale to javafx.fxml;
    exports niski.farmingdale;
}
