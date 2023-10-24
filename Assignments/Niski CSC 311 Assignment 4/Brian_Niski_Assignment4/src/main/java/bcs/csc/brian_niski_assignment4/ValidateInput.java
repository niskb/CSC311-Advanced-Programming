/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bcs.csc.brian_niski_assignment4;

/**
 *
 * @author Brian
 */
public class ValidateInput {

    public static boolean checkNameRegexMatch(String name) {
        final String nameRegex = "[A-Z][a-z]*";
        return name.matches(nameRegex);
    }

    public static boolean checkEmailRegexMatch(String email) {
        final String emailRegex = "[a-z0-9]+@[a-z0-9]+.[a-z]{2,4}";
        return email.matches(emailRegex);
    }

    public static boolean checkPhoneRegexMatch(String phone) {
        final String phoneRegex = "\\d{3}-\\d{3}-\\d{4}";
        return phone.matches(phoneRegex);
    }

    public static boolean checkSalaryRegexMatch(String salaryText) {
        final String salaryRegexWithDecimalPlaces = "[0-9]+.[0-9]{1,2}";
        if (salaryText.matches(salaryRegexWithDecimalPlaces)) {
            return true;
        } else {
            final String salaryRegex = "[0-9]+";
            if (salaryText.matches(salaryRegex)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkForMatchingValue(String ob1, String ob2) {
        return ob1.equals(ob2);
    }

    public static boolean checkForMatchingValue(double ob1, double ob2) {
        return Math.abs(ob1 - ob2) <= 0.000001;
    }

}
