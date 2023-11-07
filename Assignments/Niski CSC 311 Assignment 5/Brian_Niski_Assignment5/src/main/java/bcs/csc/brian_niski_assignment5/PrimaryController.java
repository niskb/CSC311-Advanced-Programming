package bcs.csc.brian_niski_assignment5;

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
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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
    @FXML
    private Button clearAllButton;
    @FXML
    private Button removeDuplicatesButton;

    private ObservableList<Employee> myList = FXCollections.observableArrayList();
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem createTableMenuItem;
    @FXML
    private MenuItem closeFileMenuItem;

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
        //
    }

    @FXML
    private void openFileChooserRead(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.CSV"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Scanner inFile = null;
            String line = "";
            try {
                inFile = new Scanner(selectedFile);
                statusLabelText.setText("Reading from file " + selectedFile.getAbsolutePath() + "...");
                while (inFile.hasNext()) {
                    line = inFile.nextLine();
                    String[] splitByComma = line.split(",");
                    String firstName = splitByComma[0];
                    String lastName = splitByComma[1];
                    String email = splitByComma[2];
                    String phone = splitByComma[3];
                    double salary = Double.parseDouble(splitByComma[4]);
                    Employee employee = new Employee(firstName, lastName, email, phone, salary);
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
    private void openFileChooserWrite(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.CSV"));
        File selectedFile = fileChooser.showSaveDialog(null);
        String content = "";
        if (selectedFile != null) {
            statusLabelText.setText("Writing file to " + selectedFile.getAbsolutePath() + "...");
            PrintStream outFile = null;
            for (int i = 0; i < myList.size(); i++) {
                content += myList.get(i).getFirstName() + "," + myList.get(i).getLastName() + "," + myList.get(i).getEmail() + "," + myList.get(i).getPhone() + "," + myList.get(i).getSalary() + "\n";
            }
            try {
                outFile = new PrintStream(selectedFile);
                outFile.print(content);
            } catch (FileNotFoundException ex) {
                statusLabelText.setText("There was a failure to save file");
            }
            outFile.close();
            statusLabelText.setText("Saved file to " + selectedFile.getAbsolutePath());
        }
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

    @FXML
    private void addEmployee(ActionEvent event) {
        String errorMessage = "";
        try {
            statusLabelText.setText("Adding employee to the table...");
            boolean hasBlankString = false;
            boolean hasInvalidInput = false;
            String firstName = firstNameTextField.getText();
            if (firstName.equals("")) {
                errorMessage += "No first name was entered.\n";
                hasBlankString = true;
            }
            if (!Validation.validateFirstName(firstName)) {
                errorMessage += "Invalid first name. The first letter must be capital.\n";
                hasInvalidInput = true;
            }
            String lastName = lastNameTextField.getText();
            if (lastName.equals("")) {
                errorMessage += "No last name was entered.\n";
                hasBlankString = true;
            }
            if (!Validation.validateLastName(lastName)) {
                errorMessage += "Invalid last name. The first letter must be capital.\n";
                hasInvalidInput = true;
            }
            String email = emailTextField.getText();
            if (email.equals("")) {
                errorMessage += "No email was entered.\n";
                hasBlankString = true;
            }
            if (!Validation.validateEmail(email)) {
                errorMessage += "Invalid email. The email must follow a simple email rule: string1@string2.domain.\nThe "
                        + "string1 and string2 contain lowercase letters and digits. The domain contains 2 to 4 lowercase letters.\n";
                hasInvalidInput = true;
            }
            String phone = phoneTextField.getText();
            if (phone.equals("")) {
                errorMessage += "No phone was entered.\n";
                hasBlankString = true;
            }
            if (!Validation.validatePhone(phone)) {
                errorMessage += "Invalid phone. The phone must follow the format xxx-xxx-xxxx.\n";
                hasInvalidInput = true;
            }
            if (salaryTextField.getText().equals("")) {
                errorMessage += "No salary was entered.";
                hasBlankString = true;
            }
            if (!hasBlankString) {
                if (!Validation.validateSalary(salaryTextField.getText())) {
                    if (salaryTextField.getText().contains(".")) {
                        errorMessage += "Invalid salary. The salary with decimal point only allows up to two decimal places.";
                        hasInvalidInput = true;
                    } else {
                        errorMessage += "Invalid salary. The salary must contain digits.";
                        hasInvalidInput = true;
                    }
                }
                if (!hasInvalidInput) {
                    double salary = Double.parseDouble(salaryTextField.getText());
                    Employee employee = new Employee(firstName, lastName, email, phone, salary);
                    myList.add(employee);
                    tableView.getSelectionModel().clearSelection();
                    firstNameTextField.clear();
                    lastNameTextField.clear();
                    emailTextField.clear();
                    phoneTextField.clear();
                    salaryTextField.clear();
                    statusLabelText.setText("An employee has been added");
                } else {
                    statusLabelText.setText("Unable to add employee to the table");
                    generateInvalidInputAlert(errorMessage);
                }
            } else {
                statusLabelText.setText("Unable to add employee to the table");
                generateInvalidInputAlert(errorMessage);
            }
        } catch (Exception e) {
            statusLabelText.setText("There was a failure to add an employee");
            generateInvalidInputAlert(errorMessage);
        }
    }

    private static void generateInvalidInputAlert(String errorMessage) {
        Alert invalidInputAlert = new Alert(AlertType.WARNING);
        invalidInputAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        invalidInputAlert.setTitle("Warning");
        invalidInputAlert.setHeaderText("Invalid Input");
        invalidInputAlert.setContentText(errorMessage);
        invalidInputAlert.showAndWait();
    }

    @FXML
    private void deleteEmployee(ActionEvent event) {
        try {
            statusLabelText.setText("Deleting selected employee...");
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            tableView.getItems().remove(selectedIndex);
            tableView.getSelectionModel().clearSelection();
            statusLabelText.setText("The selected employee has been deleted");
        } catch (Exception e) {
            statusLabelText.setText("There was a failure to removing the selected employee, or no employee was selected");
        }
    }

    @FXML
    private void clearAll(ActionEvent event) {
        try {
            statusLabelText.setText("Clearing the table...");
            ObservableList<Employee> toBeCleared = tableView.getItems();
            tableView.getItems().clear();
            firstNameTextField.clear();
            lastNameTextField.clear();
            emailTextField.clear();
            phoneTextField.clear();
            salaryTextField.clear();
            statusLabelText.setText("The table has been cleared");
        } catch (Exception e) {
            statusLabelText.setText("There was a failure in clearing the table");
        }
    }

    @FXML
    private void removeDuplicates(ActionEvent event) {
        statusLabelText.setText("Removing duplicate(s) from the table...");
        for (int i = 0; i < tableView.getItems().size(); i++) {
            Employee employee = tableView.getItems().get(i);
            for (int j = 0; j < tableView.getItems().size(); j++) {
                if (i != j) {
                    boolean firstNameIsEqual = Validation.checkForMatchingValue(employee.getFirstName(), tableView.getItems().get(j).getFirstName());
                    boolean lastNameIsEqual = Validation.checkForMatchingValue(employee.getLastName(), tableView.getItems().get(j).getLastName());
                    boolean emailIsEqual = Validation.checkForMatchingValue(employee.getEmail(), tableView.getItems().get(j).getEmail());
                    boolean phoneIsEqual = Validation.checkForMatchingValue(employee.getPhone(), tableView.getItems().get(j).getPhone());
                    boolean salaryIsEqual = Validation.checkForMatchingValue(employee.getSalary(), tableView.getItems().get(j).getSalary());
                    if (firstNameIsEqual && lastNameIsEqual && emailIsEqual && phoneIsEqual && salaryIsEqual) {
                        tableView.getItems().remove(tableView.getItems().get(j));
                    }
                }
            }
        }
        sortItemsByFirstName(null);
        statusLabelText.setText("Any duplicate(s) found were removed from the table");
    }

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

    @FXML
    private void editEmployeee(ActionEvent event) {
        String errorMessage = "";
        try {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            statusLabelText.setText("Editing selected employee from the table...");
            boolean hasBlankString = false;
            boolean hasInvalidInput = false;
            String firstName = firstNameTextField.getText();
            if (firstName.equals("")) {
                errorMessage += "No first name was entered.\n";
                hasBlankString = true;
            }
            if (!Validation.validateFirstName(firstName)) {
                errorMessage += "Invalid first name. The first letter must be capital.\n";
                hasInvalidInput = true;
            }
            String lastName = lastNameTextField.getText();
            if (lastName.equals("")) {
                errorMessage += "No last name was entered.\n";
                hasBlankString = true;
            }
            if (!Validation.validateLastName(lastName)) {
                errorMessage += "Invalid last name. The first letter must be capital.\n";
                hasInvalidInput = true;
            }
            String email = emailTextField.getText();
            if (email.equals("")) {
                errorMessage += "No email was entered.\n";
                hasBlankString = true;
            }
            if (!Validation.validateEmail(email)) {
                errorMessage += "Invalid email. The email must follow a simple email rule: string1@string2.domain.\nThe "
                        + "string1 and string2 contain lowercase letters and digits. The domain contains 2 to 4 lowercase letters.\n";
                hasInvalidInput = true;
            }
            String phone = phoneTextField.getText();
            if (phone.equals("")) {
                errorMessage += "No phone was entered.\n";
                hasBlankString = true;
            }
            if (!Validation.validatePhone(phone)) {
                errorMessage += "Invalid phone. The phone must follow the format xxx-xxx-xxxx.\n";
                hasInvalidInput = true;
            }
            if (salaryTextField.getText().equals("")) {
                errorMessage += "No salary was entered.";
                hasBlankString = true;
            }
            if (!hasBlankString) {
                if (!Validation.validateSalary(salaryTextField.getText())) {
                    if (salaryTextField.getText().contains(".")) {
                        errorMessage += "Invalid salary. The salary with decimal point only allows up to two decimal places.";
                        hasInvalidInput = true;
                    } else {
                        errorMessage += "Invalid salary. The salary must contain digits.";
                        hasInvalidInput = true;
                    }
                }
                if (!hasInvalidInput) {
                    double salary = Double.parseDouble(salaryTextField.getText());
                    Employee employee = new Employee(firstName, lastName, email, phone, salary);
                    myList.add(employee);
                    tableView.getSelectionModel().clearSelection();
                    tableView.getItems().remove(selectedIndex);
                    firstNameTextField.clear();
                    lastNameTextField.clear();
                    emailTextField.clear();
                    phoneTextField.clear();
                    salaryTextField.clear();
                    statusLabelText.setText("The selected employee has been edited");
                } else {
                    statusLabelText.setText("Unable to edit the selected employee");
                    generateInvalidInputAlert(errorMessage);
                }
            } else {
                statusLabelText.setText("Unable to edit the selected employee, or no employee was selected");
                generateInvalidInputAlert(errorMessage);
            }
        } catch (Exception e) {
            statusLabelText.setText("There was a failure to edit the selected employee, or no employee was selected");
            generateInvalidInputAlert(errorMessage);
        }
    }

}
