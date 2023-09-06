/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package niski.farmingdale;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author niski
 */
public class PrimaryController implements Initializable {

    @FXML
    private TextField investmentAmountTF;
    @FXML
    private TextField numberOfYearsTF;
    @FXML
    private TextField annualInterestRateTF;
    @FXML
    private TextField futureValueTF;
    @FXML
    private Button calculateBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void calculateFutureValue(ActionEvent event) {
        try {
            int investmentAmount = Integer.parseInt(investmentAmountTF.getText());
            int numberOfYears = Integer.parseInt(numberOfYearsTF.getText());
            double annualInterestRate = Double.parseDouble(annualInterestRateTF.getText());
            double futureValue = investmentAmount * (Math.pow(1 + (annualInterestRate / 100), (numberOfYears)));
            futureValueTF.setText(String.format("%.2f", futureValue));
        } catch (NumberFormatException numberFormatException) {
            futureValueTF.setText("Failure");
        }
    }
    
}
