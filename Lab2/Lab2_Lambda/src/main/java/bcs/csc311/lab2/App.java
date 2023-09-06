package bcs.csc311.lab2;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    TextField amountTF, tipTF, totalTF;
    ToggleGroup tipPercentageGroup;

    @Override
    public void start(Stage stage) {
        tipPercentageGroup = new ToggleGroup();
        Label labelamount = new Label("Amount");
        amountTF = new TextField();
        HBox hb1 = new HBox(labelamount, amountTF);
        hb1.setPadding(new Insets(10, 10, 5, 10));
        hb1.setSpacing(20);
        hb1.setAlignment(Pos.CENTER_RIGHT);        
        RadioButton rb1 = new RadioButton("15%");
        RadioButton rb2 = new RadioButton("20%");  
        RadioButton rb3 = new RadioButton("25%");        
        RadioButton rb4 = new RadioButton("30%");
        rb1.setUserData(15.0);        
        rb2.setUserData(20.0);   
        rb3.setUserData(25.0);
        rb4.setUserData(30.0);       
        rb1.setToggleGroup(tipPercentageGroup);
        rb2.setToggleGroup(tipPercentageGroup);
        rb3.setToggleGroup(tipPercentageGroup);
        rb4.setToggleGroup(tipPercentageGroup);

        HBox hb2 = new HBox(rb1, rb2, rb3, rb4);  
        hb2.setPadding(new Insets(5, 10, 10, 10));
        hb2.setSpacing(10);        
        hb2.setAlignment(Pos.CENTER_RIGHT);
        
        Button button = new Button("Calculate");

        Label labeltip = new Label("Tip");
        tipTF = new TextField();
        HBox hb3 = new HBox(labeltip, tipTF);  
        hb3.setPadding(new Insets(5, 10, 5, 10));
        hb3.setSpacing(20);  
        hb3.setAlignment(Pos.CENTER_RIGHT);
        
        Label labeltotal = new Label("Total");
        totalTF = new TextField();
        HBox hb4 = new HBox(labeltotal, totalTF);
        hb4.setPadding(new Insets(5, 10, 15, 10));
        hb4.setSpacing(20); 
        hb4.setAlignment(Pos.CENTER_RIGHT);
        
        VBox vbox = new VBox(hb1, hb2, button, hb3, hb4);
        vbox.setPadding(new Insets(5,5,5,5));
        vbox.setAlignment(Pos.CENTER);
                     
        var scene = new Scene(vbox);
        stage.setTitle("Tip Calculator");
        stage.setScene(scene);
        stage.show();
        
        // handle button event
        // Lambda expression
        button.setOnAction(eh -> {
            String amt = amountTF.getText();
            double amount = Double.parseDouble(amt);
            double tipPercentage = (double) tipPercentageGroup.getSelectedToggle().getUserData();
            double tip = amount * tipPercentage / 100;
            double total = amount + tip;
            tipTF.setText(String.format("%.2f", tip));
            totalTF.setText(String.format("%.2f", total));
        });
        
    }

    public static void main(String[] args) {
        launch();
    }


}