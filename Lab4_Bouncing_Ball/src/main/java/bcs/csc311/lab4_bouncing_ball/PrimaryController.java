package bcs.csc311.lab4_bouncing_ball;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class PrimaryController {

    @FXML
    private Pane pane;
    @FXML
    private Circle ball;
    
    private double dx = 1;
    private double dy = 1;
    
    public void initialize() {
        // define a timeline animation
        Timeline timelineAnimation = new Timeline(
            new KeyFrame( Duration.millis(3), e-> {
                ball.setLayoutX(ball.getLayoutX() + dx);
                ball.setLayoutY(ball.getLayoutY() + dy);
                Bounds bounds = pane.getBoundsInLocal();
                if (ball.getLayoutX() <= bounds.getMinX() + ball.getRadius() ||
                    ball.getLayoutX() >= bounds.getMaxX() - ball.getRadius() ) {
                    dx = -dx;
                }
                if (ball.getLayoutY() <= bounds.getMinY() + ball.getRadius() ||
                    ball.getLayoutY() >= bounds.getMaxY() - ball.getRadius() ) {
                    dy = -dy;
                }
            })
        );
        // set the timeline animation to run indefinitely
        timelineAnimation.setCycleCount(Timeline.INDEFINITE);
        timelineAnimation.play();
    }
    
}
