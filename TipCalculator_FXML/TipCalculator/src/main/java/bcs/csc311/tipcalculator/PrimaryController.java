package bcs.csc311.tipcalculator;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PrimaryController {

    @FXML
    private TextField amountTF;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private TextField tipTF;
    @FXML
    private TextField totalTF;
    @FXML
    private Button calculateButton;
    @FXML
    private ToggleGroup tipPercentageGroup;

    double tipPercentage = 0;
    
    public void initialize() {
        rb1.setUserData(15.0);
        rb2.setUserData(20.0);
        rb3.setUserData(25.0);
    }
    
    @FXML
    private void getSelectedTipPercentage(ActionEvent event) {
        tipPercentage = (double) tipPercentageGroup.getSelectedToggle().getUserData();
    }

    @FXML
    private void calculateButtonHandler(ActionEvent event) {
        try {
        String amt = amountTF.getText();
        double amount = Double.parseDouble(amt);
        double tip = amount * tipPercentage / 100;
        double total = amount + tip;
        tipTF.setText(String.format("%.2f", tip));
        totalTF.setText(String.format("%.2f", total));
        } catch (Exception e) {
            System.out.println("Fail");
        }
    }
}
