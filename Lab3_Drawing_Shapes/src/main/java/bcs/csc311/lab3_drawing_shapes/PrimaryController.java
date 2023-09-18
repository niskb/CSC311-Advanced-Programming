package bcs.csc311.lab3_drawing_shapes;

import java.io.IOException;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PrimaryController {

    @FXML
    private Pane pane;
    @FXML
    private Canvas canvas;
    
    private Color penColor = Color.BLUE;
    private double radius = 1;
    private GraphicsContext gc;
    private double lineWidth = 1;
    
    
    @FXML
    private Button clear_button;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Slider slider;
    
    @FXML
    private Button transformButton;
    
    private Rectangle rectangle;
    private double angle = 0;

    public void initialize() {
        // handle value change of the slider
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            lineWidth = newValue.doubleValue();
            gc.setLineWidth(lineWidth);
        });
        
        // pane
        Circle circle = new Circle(50, 50, 20);
        // CSS
        circle.setStyle("-fx-stroke: black; -fx-fill: red;");
        // Equivalent statement
        circle.setFill(Color.BLUE);
        
        Line line = new Line(0, 0, 100, 100);
        rectangle = new Rectangle(100, 100, 20, 30);
        rectangle.setFill(Color.RED);
        pane.getChildren().addAll(circle, line, rectangle);
        
        // canvas
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(penColor);
        gc.strokeLine(0, 0, 100, 100);
        gc.stroke();
        gc.strokeRect(100, 100, 20, 30);
        gc.stroke();
        gc.setLineWidth(lineWidth);
        // gc.setStroke(Color.RED);
        // gc.fillRect(100, 100, 20, 30);
        // gc.stroke();
    }

    @FXML
    private void paneOnMouseDragged(MouseEvent event) {
        Circle circle = new Circle(event.getX(), event.getY(), lineWidth, penColor);
        pane.getChildren().add(circle);
    }

    @FXML
    private void clearButtonHandler(ActionEvent event) {
        pane.getChildren().clear();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @FXML
    private void colorPickerHandler(ActionEvent event) {
        penColor = colorPicker.getValue();
        gc.setStroke(penColor);
    }

    @FXML
    private void canvasOnMouseReleased(MouseEvent event) {
        
    }

    @FXML
    private void canvasOnMouseDragged(MouseEvent event) {
        gc.lineTo(event.getX(), event.getY());
        gc.stroke();
    }

    @FXML
    private void canvasOnMousePressed(MouseEvent event) {
        gc.setLineWidth(lineWidth);
        gc.beginPath();
        gc.moveTo(event.getX(), event.getY());
        gc.stroke();
    }
    
    @FXML
    private void transformButtonHandler(ActionEvent event) {
//        angle += 30;
//        rectangle.setRotate(angle);

          // Create a rectangle
          Rectangle rectangle = new Rectangle(0, 0, 25, 50);
          rectangle.setFill(Color.ORANGE);
    
          // Create a circle
          Circle circle = new Circle(125, 100, 50);
          circle.setFill(Color.WHITE);
          circle.setStroke(Color.BLACK);
    
          // Add circle and rectangle to the pane
          pane.getChildren().add(circle);
          pane.getChildren().add(rectangle);
    
          // Create a path transition 
          PathTransition pt = new PathTransition();
          pt.setDuration(Duration.seconds(4));
          pt.setPath(circle);
          pt.setNode(rectangle);
          pt.setOrientation(
       PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
          pt.setCycleCount(Timeline.INDEFINITE);
          pt.setAutoReverse(true);
          pt.play(); // Start animation 
    
          circle.setOnMousePressed(e -> pt.pause());
          circle.setOnMouseReleased(e -> pt.play());

    }
    
}
