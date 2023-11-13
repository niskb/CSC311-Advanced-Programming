package bcs.csc.brian_niski_assignment5;

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
import javafx.scene.layout.Region;

public class PrimaryController {

    @FXML
    private MenuBar fileMenuBar;
    @FXML
    private MenuItem openFileMenuItem;
    @FXML
    private MenuItem saveFileMenuItem;
    @FXML
    private Menu sortMenu;
    @FXML
    private MenuItem firstNameMenuItem;
    @FXML
    private MenuItem lastNameSortMenuItem;
    @FXML
    private MenuItem emailSortMenuItem;
    @FXML
    private MenuItem phoneSortMenuItem;
    @FXML
    private MenuItem salarySortMenuItem;
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem aboutHelpMenuItem;
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, String> firstNameTableColumn;
    @FXML
    private TableColumn<Employee, String> lastNameTableColumn;
    @FXML
    private TableColumn<Employee, String> emailTableColumn;
    @FXML
    private TableColumn<Employee, String> phoneTableColumn;
    @FXML
    private TableColumn<Employee, Double> salaryTableColumn;
    @FXML
    private Label statusLabel;
    @FXML
    private Label statusLabelText;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField salaryTextField;
    @FXML
    private Button addEmployeeButton;
    @FXML
    private Button deleteEmployeeButton;

    private ObservableList<Employee> myList = FXCollections.observableArrayList();
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem createTableMenuItem;
    @FXML
    private MenuItem closeFileMenuItem;
    @FXML
    private Button listEmployeesButton;

    public void initialize() {
        firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        phoneTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone"));
        salaryTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
        tableView.setItems(myList);
    }

    @FXML
    private void createTable(ActionEvent event) {
        EmployeeDBController.createTable(true, statusLabelText);
    }

    @FXML
    private void openFileChooserRead(ActionEvent event) {
        EmployeeDBController.importCSV(statusLabelText, myList);
    }

    @FXML
    private void openFileChooserWrite(ActionEvent event) {
        EmployeeDBController.exportCSV(statusLabelText, myList);
    }

    @FXML
    private void listEmployeesButtonOnAction(ActionEvent event) {
        EmployeeDBController.listRecords(myList, statusLabelText);
    }

    @FXML
    private void addEmployee(ActionEvent event) {
        EmployeeDBController.addRecord(statusLabelText, firstNameTextField, lastNameTextField, emailTextField, phoneTextField, salaryTextField, myList, tableView);
    }

    @FXML
    private void deleteEmployee(ActionEvent event) {
        EmployeeDBController.deleteRecord(statusLabelText, tableView, myList);
    }

    // Extra Credit 1
    @FXML
    private void editEmployeee(ActionEvent event) {
        EmployeeDBController.updateRecord(tableView, statusLabelText, firstNameTextField, lastNameTextField, emailTextField, phoneTextField, salaryTextField, myList);
    }

    @FXML
    private void closeAndExitApplication(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void sortItemsByFirstName(ActionEvent event) {
        if (firstNameTableColumn.getSortType().equals(TableColumn.SortType.ASCENDING)) {
            firstNameTableColumn.setSortType(TableColumn.SortType.DESCENDING);
        } else {
            firstNameTableColumn.setSortType(TableColumn.SortType.ASCENDING);
        }
        tableView.getSortOrder().clear();
        tableView.getSortOrder().add(firstNameTableColumn);
        tableView.sort();
        statusLabelText.setText("Data sorted by First Name " + firstNameTableColumn.getSortType());
    }

    @FXML
    private void sortItemsByLastName(ActionEvent event) {
        if (lastNameTableColumn.getSortType().equals(TableColumn.SortType.ASCENDING)) {
            lastNameTableColumn.setSortType(TableColumn.SortType.DESCENDING);
        } else {
            lastNameTableColumn.setSortType(TableColumn.SortType.ASCENDING);
        }
        tableView.getSortOrder().clear();
        tableView.getSortOrder().add(lastNameTableColumn);
        tableView.sort();
        statusLabelText.setText("Data sorted by Last Name " + lastNameTableColumn.getSortType());
    }

    @FXML
    private void sortItemsByEmail(ActionEvent event) {
        if (emailTableColumn.getSortType().equals(TableColumn.SortType.ASCENDING)) {
            emailTableColumn.setSortType(TableColumn.SortType.DESCENDING);
        } else {
            emailTableColumn.setSortType(TableColumn.SortType.ASCENDING);
        }
        tableView.getSortOrder().clear();
        tableView.getSortOrder().add(emailTableColumn);
        tableView.sort();
        statusLabelText.setText("Data sorted by Email " + emailTableColumn.getSortType());
    }

    @FXML
    private void sortItemsByPhone(ActionEvent event) {
        if (phoneTableColumn.getSortType().equals(TableColumn.SortType.ASCENDING)) {
            phoneTableColumn.setSortType(TableColumn.SortType.DESCENDING);
        } else {
            phoneTableColumn.setSortType(TableColumn.SortType.ASCENDING);
        }
        tableView.getSortOrder().clear();
        tableView.getSortOrder().add(phoneTableColumn);
        tableView.sort();
        statusLabelText.setText("Data sorted by Phone " + phoneTableColumn.getSortType());
    }

    @FXML
    private void sortItemsBySalary(ActionEvent event) {
        if (salaryTableColumn.getSortType().equals(TableColumn.SortType.ASCENDING)) {
            salaryTableColumn.setSortType(TableColumn.SortType.DESCENDING);
        } else {
            salaryTableColumn.setSortType(TableColumn.SortType.ASCENDING);
        }
        tableView.getSortOrder().clear();
        tableView.getSortOrder().add(salaryTableColumn);
        tableView.sort();
        statusLabelText.setText("Data sorted by Salary " + salaryTableColumn.getSortType());
    }

    @FXML
    private void showAboutAlert(ActionEvent event) {
        Alert aboutAlert = new Alert(AlertType.INFORMATION);
        aboutAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        aboutAlert.setTitle("About");
        aboutAlert.setHeaderText("Assignment 5 by Brian Niski");
        aboutAlert.setContentText("I certify this submission is my original work.");
        aboutAlert.showAndWait();
    }

    // Extra Credit 1
    @FXML
    private void enableEditEmployeeOnMousePress(MouseEvent event) {
        int selectedIndex = -1;
        try {
            selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            firstNameTextField.setText(myList.get(selectedIndex).getFirstName());
            lastNameTextField.setText(myList.get(selectedIndex).getLastName());
            emailTextField.setText(myList.get(selectedIndex).getEmail());
            phoneTextField.setText(myList.get(selectedIndex).getPhone());
            salaryTextField.setText(String.valueOf(myList.get(selectedIndex).getSalary()));
            statusLabelText.setText("An employee has been selected");
        } catch (Exception e) {
            if (selectedIndex == -1) {
                statusLabelText.setText("An employee has not been selected");
            }
        }
    }

}
