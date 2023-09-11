package bcs.csc311.lab3_drawing_shapes;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PrimaryController {

    @FXML
    private Pane pane;
    @FXML
    private Canvas canvas;
    
    Color penColor = Color.BLUE;
    double radius = 1;

    public void initialize() {
        Circle circle = new Circle(50, 50, 30);
        circle.setFill(Color.BLUE);
        pane.getChildren().add(circle);
    }

    @FXML
    private void paneOnMouseDragged(MouseEvent event) {
        Circle circle = new Circle(event.getX(), event.getY(), radius, penColor);
        pane.getChildren().add(circle);
    }
    
}
