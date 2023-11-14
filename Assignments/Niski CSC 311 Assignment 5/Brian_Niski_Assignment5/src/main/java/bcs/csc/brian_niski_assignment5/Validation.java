/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bcs.csc.brian_niski_assignment5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;

/**
 *
 * @author Brian
 */
public class Validation {

    public static boolean validateFirstName(String firstName) {
        final String nameRegex = "[A-Z][a-z]+";
        return firstName.matches(nameRegex);
    }

    public static boolean validateLastName(String lastName) {
        final String nameRegex = "[A-Z][a-z]*(['-]?[A-Z][a-z]+)*";
        if ((lastName.charAt(lastName.length() - 1) == '\'') || (lastName.charAt(lastName.length() - 1) == '-')) {
            return false;
        }
        return lastName.matches(nameRegex);
    }

    public static boolean validateEmail(String email) {
        final String emailRegex = "[a-z0-9]+@[a-z0-9]+[.][a-z]{2,4}";
        return email.matches(emailRegex);
    }

    public static boolean validatePhone(String phone) {
        final String phoneRegex = "\\d{3}[-]\\d{3}[-]\\d{4}";
        return phone.matches(phoneRegex);
    }

    public static boolean validateSalary(String salaryText) {
        final String salaryRegexWithDecimalPlaces = "[0-9]+([.][0-9]{0,2})?";
        return salaryText.matches(salaryRegexWithDecimalPlaces);
    }

    public static boolean checkForMatchingValue(String ob1, String ob2) {
        return ob1.equals(ob2);
    }

    public static boolean checkForMatchingValue(double ob1, double ob2) {
        return Math.abs(ob1 - ob2) <= 0.000001;
    }

    // Extra Credit 2 //
    public static boolean scanDatabaseForMatchingEmail(String DB_URL, String email, Label statusLabelText) {
        int numberOfRows = -1;
        boolean foundMatch = false;
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM employee";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String scannedEmail = resultSet.getString("email");
                if (email.equals(scannedEmail)) {
                    foundMatch = true;
                    break;
                }
            }
            connection.close();
            return foundMatch;
        } catch (SQLException e) {
            statusLabelText.setText("Unable to connect to database");
        }
        return false;
    }

}
