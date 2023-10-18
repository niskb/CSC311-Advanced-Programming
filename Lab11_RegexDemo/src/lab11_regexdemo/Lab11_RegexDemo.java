/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab11_regexdemo;

import java.util.Scanner;

/**
 *
 * @author Brian
 */
public class Lab11_RegexDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String text, regex;
        Scanner keyboard = new Scanner(System.in);

//      regex = "[a-z]"; // any single lowercase letter
//      regex = "[a-zA-Z]"; // any single letter
//      regex = "[a-zA-Z0-9]"; // any single letter or digit
//      regex = "[a-zA-Z]+"; // any one or more letters
//      regex = "[A-Z][a-zA-Z]*"; // first char must be uppercase letter and zero or more letters after
//      regex = "(a|A)(p|P)(p|P)(l|L)(e|E)"; // lowercase or uppercase letter for the word "apple", () is a group, | is or operation
//      regex = "apple"; // apple
//      regex = "^apple(.*)"; // string starting with apple, apple followed by any character and more
//      regex = "\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d"; // phone number, 123-456-7890
//      regex = "\\d{3}-\\d{3}-\\d{4}"; // phone number, using amount of digits
//      regex = "[^apple]"; // any single character not in the list []
        regex = "[a-z0-9]+@[a-z0-9]+.[a-z]{2,4}"; // email, domain name last digits from 2 to 4

        System.out.print("Enter a text: ");
        text = keyboard.nextLine();

        while (!(text.equals("exit"))) {
            if (text.matches(regex)) {
                System.out.println("match");
            } else {
                System.out.println("not match");
            }
            System.out.print("Enter a text: ");
            text = keyboard.nextLine();
        }
    }

}
