package bcs.csc311.lab6_tableview;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {

    @FXML
    private TextField countryTF;
    @FXML
    private TextField capitalTF;
    @FXML
    private TextField populationTF;
    @FXML
    private Button addButton;
    @FXML
    private TableView<Country> tableView;
    @FXML
    private TableColumn<Country, String> countryColumn;
    @FXML
    private TableColumn<Country, String> capitalColumn;
    @FXML
    private TableColumn<Country, Double> populationColumn;

    ObservableList<Country> myList = FXCollections.observableArrayList(
            new Country("France", "Paris", 60),
            new Country("USA", "Washington DC", 300)
    );
    @FXML
    private Button clearButton;
    
    public void initialize() {
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        capitalColumn.setCellValueFactory(new PropertyValueFactory<>("capital"));
        populationColumn.setCellValueFactory(new PropertyValueFactory<>("population"));
        tableView.setItems(myList);
    }
    
    @FXML
    private void addButtonHandler(ActionEvent event) {
        try {
        String country = countryTF.getText();
        String capital = capitalTF.getText();
        double population = Double.parseDouble(populationTF.getText());
        
        Country newCountry = new Country(country, capital, population);
        myList.add(newCountry);
        } catch (Exception e) {
            System.out.println("Fail");
        }
    }

    @FXML
    private void clearButtonHandler(ActionEvent event) {
        // Clear text fields
        countryTF.clear();
        capitalTF.clear();
        populationTF.clear();
    }
    
}
