package bcs.csc311.brian_niski_assignment_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class PrimaryController {

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem openMenuItem;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private Label statusLabel;
    @FXML
    private Label statusLabelText;
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, String> firstNameTableColumn;
    @FXML
    private TableColumn<Employee, String> lastNameTableColumn;
    @FXML
    private TableColumn<Employee, Double> salaryTableColumn;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField salaryTextField;
    @FXML
    private Button addEmployeeButton;
    @FXML
    private Button editEmployeeButton;
    @FXML
    private Button deleteEmployeeButton;
    @FXML
    private Button clearButton;

    private ObservableList<Employee> myList = FXCollections.observableArrayList();

    public void initialize() {
        firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        salaryTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
        tableView.setItems(myList);
    }

    @FXML
    private void openMenuItemOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File (System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.CSV"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Scanner inFile = null;
            String line = "";
            try {
                inFile = new Scanner(selectedFile);
                statusLabelText.setText("Reading from file " + selectedFile.getAbsolutePath());
                while (inFile.hasNext()) {
                    line = inFile.nextLine();
                    String[] splitByComma = line.split(",");
                    String firstName = splitByComma[0];
                    String lastName = splitByComma[1];
                    double salary = Double.parseDouble(splitByComma[2]);
                    Employee employee = new Employee(firstName, lastName, salary);
                    myList.add(employee);
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            inFile.close();
            statusLabelText.setText("Loaded file from " + selectedFile.getAbsolutePath());
        }

    }

    @FXML
    private void saveMenuItemOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File (System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.CSV"));
        File selectedFile = fileChooser.showSaveDialog(null);
        String content = "";
        if (selectedFile != null) {
            statusLabelText.setText("Writing file to " + selectedFile.getAbsolutePath());
            PrintStream outFile = null;
            for (int i = 0; i < myList.size(); i++) {
                content += myList.get(i).getFirstName() + "," + myList.get(i).getLastName() + "," + myList.get(i).getSalary() + "\n";
            }
            try {
                outFile = new PrintStream(selectedFile);
                outFile.print(content);
            } catch (FileNotFoundException ex) {
                statusLabelText.setText("There was a failure to save file");
            }
            String alertText = "The file has been saved successfully.";
            Alert alert = new Alert(AlertType.CONFIRMATION, alertText);
            alert.showAndWait();
            statusLabelText.setText("Saved file to " + selectedFile.getAbsolutePath());
            outFile.close();
        }
    }

    @FXML
    private void closeMenuItemOnAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void helpMenuItemOnAction(ActionEvent event) {
        String alertText = "Student Name: Brian Niski\nI certify that this submission is my original work.";
        Alert alert = new Alert(AlertType.INFORMATION, alertText);
        alert.setTitle("About");
        alert.showAndWait();
    }

    @FXML
    private void addEmployeeButtonOnAction(ActionEvent event) {
        try {
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            double salary = Double.parseDouble(salaryTextField.getText());
            Employee employee = new Employee(firstName, lastName, salary);
            myList.add(employee);
            firstNameTextField.clear();
            lastNameTextField.clear();
            salaryTextField.clear();
            statusLabelText.setText("An employee has been added");
        } catch (Exception e) {
            statusLabelText.setText("There was a failure to add an employee");
        }
    }
    
    @FXML
    private void editEmployeeOnMousePressed(MouseEvent event) {
        int selectedIndex = -1;
        try {
            selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            firstNameTextField.setText(myList.get(selectedIndex).getFirstName());
            lastNameTextField.setText(myList.get(selectedIndex).getLastName());
            salaryTextField.setText(String.valueOf(myList.get(selectedIndex).getSalary()));
            statusLabelText.setText("An employee has been selected");
        } catch (Exception e) {
            if(selectedIndex == -1) {
                statusLabelText.setText("An employee has not been selected");
            }
        }
    }
    
    @FXML
    private void editEmployeeButtonOnAction(ActionEvent event) {
        try {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            double salary = Double.parseDouble(salaryTextField.getText());
            Employee employee = new Employee(firstName, lastName, salary);
            myList.add(employee);
            tableView.getItems().remove(selectedIndex);
            tableView.getSelectionModel().clearSelection();
            statusLabelText.setText("An employee has been edited");
        } catch (Exception e) {
            statusLabelText.setText("There was a failure to edit an employee, or no employee was selected");
        }
    }

    @FXML
    private void deleteEmployeeButtonOnAction(ActionEvent event) {
        try {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            String firstName = myList.get(selectedIndex).getFirstName();
            String lastName = myList.get(selectedIndex).getLastName();
            double salary = myList.get(selectedIndex).getSalary();
            String alertText = firstName + " " + lastName + " with salary of $" + String.format("%.2f", Double.valueOf(salary)) + " is being deleted.";
            Alert alert = new Alert(AlertType.WARNING, alertText);
            alert.showAndWait();
            tableView.getItems().remove(selectedIndex);
            tableView.getSelectionModel().clearSelection();
            statusLabelText.setText("The selected employee has been deleted");
        } catch (Exception e) {
            statusLabelText.setText("There was a failure to removing the selected employee, or no employee was selected");
        }
    }

    @FXML
    private void clearButtonOnAction(ActionEvent event) {
        try {
            ObservableList<Employee> toBeCleared = tableView.getItems();
            String alertText = "";
            for(int i = 0; i < toBeCleared.size(); i++) {
                alertText = alertText + toBeCleared.get(i).toString() + "\n";
            }
            alertText = alertText + "These employee(s) will be cleared!";
            Alert alert = new Alert(AlertType.WARNING, alertText);
            alert.showAndWait();
            tableView.getItems().clear();
            firstNameTextField.clear();
            lastNameTextField.clear();
            salaryTextField.clear();
            statusLabelText.setText("The table has been cleared");
        } catch (Exception e) {
            statusLabelText.setText("There was a failure in clearing the table");
        }
    }

}
