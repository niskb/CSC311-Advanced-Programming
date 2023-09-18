package niski.farmingdale.brian_niski_assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PrimaryController {

    /**
     * Import primary.fxml containers/controls.
     */
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private RadioButton lineRadioButton;
    @FXML
    private ToggleGroup shapeRadioButtonGroup;
    @FXML
    private RadioButton rectangleRadioButton;
    @FXML
    private RadioButton ovalRadioButton;
    @FXML
    private RadioButton freeDrawingRadioButton;
    @FXML
    private Slider slider;
    @FXML
    private Button clearCanvasButton;
    @FXML
    private Canvas canvas;

    /**
     * Additional containers/controls needed.
     */
    private Color penColor;
    private GraphicsContext gc;
    private byte selectedShapeCase;
    private int selectedSize;

    /**
     * Mouse event coordinate points on press and on release.
     */
    private double startX;
    private double startY;
    private double endX;
    private double endY;

    /**
     * Initialize the JavaFX UI controls.
     */
    public void initialize() {
        // Method to set new value from the slider.
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedSize = newValue.intValue();
            gc.setLineWidth(selectedSize);
        });

        // Set default pen color to black.
        penColor = Color.BLACK;

        // Use the GraphicsContext Class to draw line, rectangle, oval shapes, and free drawing.
        gc = canvas.getGraphicsContext2D();

        // Initialize the pen color.
        gc.setStroke(penColor);

        // Set the default pen size to 1.
        selectedSize = 1;

        // Initialize the pen size.
        gc.setLineWidth(selectedSize);
    }

    /**
     * Set the pen color based on action event of the color picker.
     *
     * @param event
     */
    @FXML
    private void colorPickerHandler(ActionEvent event) {
        penColor = colorPicker.getValue();
        gc.setStroke(penColor);
    }

    /**
     * Set the selected shape tool based on action event of the group of the
     * radio buttons. If the lineRadioButton is selected, set the selected shape
     * case to 0. If the rectangleRadioButton is selected, set the selected
     * shape case to 1. If the ovalRadioButton is selected, set the selected
     * shape case to 2. If the freeDrawingRadioButton is selected, set the
     * selected shape case to 3.
     *
     * @param event
     */
    @FXML
    private void getSelectedShape(ActionEvent event) {
        if (shapeRadioButtonGroup.getSelectedToggle() == lineRadioButton) {
            selectedShapeCase = 0;
        } else if (shapeRadioButtonGroup.getSelectedToggle() == rectangleRadioButton) {
            selectedShapeCase = 1;
        } else if (shapeRadioButtonGroup.getSelectedToggle() == ovalRadioButton) {
            selectedShapeCase = 2;
        } else if (shapeRadioButtonGroup.getSelectedToggle() == freeDrawingRadioButton) {
            selectedShapeCase = 3;
        }
    }

    /**
     * Clear the canvas when the clear button is selected.
     *
     * @param event
     */
    @FXML
    private void clearButtonHandler(ActionEvent event) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * When the mouse is pressed in the canvas, get the starting x position and
     * starting y position. If the selected shape case is the
     * freeDrawingRadioButton (3), begin the path on the GraphicsContext.
     *
     * @param event
     */
    @FXML
    private void canvasOnMousePressed(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();
        if (selectedShapeCase == 3) {
            gc.beginPath();
        }
    }

    /**
     * While the mouse is being dragged, if the selected shape case is the
     * freeDrawingRadioButton (3), the GraphicsContext is set for free drawing
     * and draw continuously.
     *
     * @param event
     */
    @FXML
    private void canvasOnMouseDragged(MouseEvent event) {
        if (selectedShapeCase == 3) {
            gc.lineTo(event.getX(), event.getY());
            gc.stroke();
        }
    }

    /**
     * Get the x and y values of where the mouse is released. If the selected
     * shape case is lineRadioButton (0), the GraphicsContext is set for line
     * drawing. Draw line on release. If the selected shape case is
     * rectangleRadioButton (1), the GraphicsContext is set for rectangle
     * drawing. Draw rectangle on release. If the selected shape case is
     * ovalRadioButton (2), the GraphicsContext is set for oval drawing. Draw
     * oval on release. If the selected shape case is freeDrawingRadioButton
     * (3), the GraphicsContext is set for free drawing. Close the path on the
     * GraphicsContext.
     *
     * @param event
     */
    @FXML
    private void canvasOnMouseReleased(MouseEvent event) {
        endX = event.getX();
        endY = event.getY();
        switch (selectedShapeCase) {
            case 0:
                gc.strokeLine(startX, startY, endX, endY);
                break;
            case 1:
                if (isSmallerPosition()) {
                    gc.strokeRect(endX, endY, Math.abs(endX - startX), Math.abs(endY - startY));
                } else {
                    gc.strokeRect(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
                }
                break;
            case 2:
                if (isSmallerPosition()) {
                    gc.strokeOval(endX, endY, Math.abs(endX - startX), Math.abs(endY - startY));
                } else {
                    gc.strokeOval(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
                }
                break;
            case 3:
                gc.closePath();
                break;
            default:
                break;
        }
    }

    /**
     * Checks the position of the mouse on release is smaller than the position
     * where it was initially pressed.
     *
     * @return
     */
    private boolean isSmallerPosition() {
        return ((endX < startX) && (endY < startY));
    }

}
