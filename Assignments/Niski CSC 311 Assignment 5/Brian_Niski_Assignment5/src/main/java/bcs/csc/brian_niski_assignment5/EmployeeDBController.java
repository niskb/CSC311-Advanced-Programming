/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bcs.csc.brian_niski_assignment5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;

/**
 *
 * @author Brian
 */
public class EmployeeDBController {

    private static final String DB_URL = "jdbc:derby:EmployeeDB";

    public static void createTable(boolean isCreated, Label statusLabelText) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL + "; create=true");
            Statement statement = connection.createStatement();
            if (isCreated) {
                statement.execute("DROP TABLE employee");
            }
            String query = "CREATE TABLE employee ("
                    + "First_Name VARCHAR(24),"
                    + "Last_Name VARCHAR(24),"
                    + "Email VARCHAR(24),"
                    + "Phone VARCHAR(12),"
                    + "Salary DOUBLE)";
            statement.execute(query);
            connection.close();
            statusLabelText.setText("Connected to " + DB_URL + "!");
        } catch (SQLException e) {
            statusLabelText.setText("Unable to connect to " + DB_URL);
        }
    }

    public static void importCSV(Label statusLabelText, ObservableList<Employee> myList) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.CSV"));
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
                    Connection connection = DriverManager.getConnection(DB_URL);
                    String query = "INSERT INTO employee (First_Name, Last_Name, Email, Phone, Salary) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, phone);
                    preparedStatement.setDouble(5, salary);
                    preparedStatement.executeUpdate();
                    connection.close();
                }
            } catch (FileNotFoundException ex) {
                statusLabelText.setText("Unable to load file");
            } catch (SQLException ex) {
                statusLabelText.setText("Unable to connect to database");
            }
            inFile.close();
            statusLabelText.setText("Loaded file from " + selectedFile.getAbsolutePath());
        }
    }

    public static void exportCSV(Label statusLabelText, ObservableList<Employee> myList) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.CSV"));
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

    public static void listRecords(ObservableList<Employee> myList, Label statusLabelText) {
        try {
            statusLabelText.setText("Listing employees from " + DB_URL + "...");
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM employee";
            ResultSet result = stmt.executeQuery(query);
            String firstName, lastName, email, phone;
            double salary;
            while (result.next()) {
                firstName = result.getString("First_Name");
                lastName = result.getString("Last_Name");
                email = result.getString("Email");
                phone = result.getString("Phone");
                salary = result.getDouble("Salary");
                myList.add(new Employee(firstName, lastName, email, phone, salary));
            }
            connection.close();
            statusLabelText.setText("Listed employees from " + DB_URL + "");
        } catch (SQLException ex) {
            statusLabelText.setText("There was a failure reading the database for listing records");
        }
    }

    public static void addRecord(Label statusLabelText, TextField firstNameTextField, TextField lastNameTextField, TextField emailTextField, TextField phoneTextField, TextField salaryTextField, ObservableList<Employee> myList, TableView tableView) {
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
                    tableView.getSelectionModel().clearSelection();
                    firstNameTextField.clear();
                    lastNameTextField.clear();
                    emailTextField.clear();
                    phoneTextField.clear();
                    salaryTextField.clear();
                    Connection connection = DriverManager.getConnection(DB_URL);
                    String query = "INSERT INTO employee (First_Name, Last_Name, Email, Phone, Salary) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, phone);
                    preparedStatement.setDouble(5, salary);
                    preparedStatement.executeUpdate();
                    connection.close();
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

    public static void deleteRecord(Label statusLabelText, TableView tableView, ObservableList<Employee> myList) {
        try {
            statusLabelText.setText("Deleting selected employee...");
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            String selectedEmail = myList.get(selectedIndex).getEmail();
            tableView.getItems().remove(selectedIndex);
            tableView.getSelectionModel().clearSelection();
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            String query = "DELETE FROM employee WHERE Email = '" + selectedEmail + "'";
            statement.executeUpdate(query);
            statusLabelText.setText("The selected employee, with email " + selectedEmail + ", has been deleted");
        } catch (Exception e) {
            statusLabelText.setText("There was a failure to removing the selected employee, or no employee was selected");
        }
    }

    private static void generateInvalidInputAlert(String errorMessage) {
        Alert invalidInputAlert = new Alert(Alert.AlertType.WARNING);
        invalidInputAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        invalidInputAlert.setTitle("Warning");
        invalidInputAlert.setHeaderText("Invalid Input");
        invalidInputAlert.setContentText(errorMessage);
        invalidInputAlert.showAndWait();
    }

    // Extra Credit 1
    public static void updateRecord(TableView tableView, Label statusLabelText, TextField firstNameTextField, TextField lastNameTextField, TextField emailTextField, TextField phoneTextField, TextField salaryTextField, ObservableList<Employee> myList) {
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
