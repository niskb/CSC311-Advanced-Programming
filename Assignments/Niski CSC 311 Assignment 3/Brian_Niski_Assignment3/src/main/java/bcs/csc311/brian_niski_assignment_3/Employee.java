/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bcs.csc311.brian_niski_assignment_3;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Brian
 */
public class Employee {

    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleDoubleProperty salary;

    public Employee(String firstName, String lastName, double salary) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.salary = new SimpleDoubleProperty(salary);
    }

    public String getFirstName() {
        return firstName.getValue();
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public String getLastName() {
        return this.lastName.getValue();
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public double getSalary() {
        return salary.doubleValue();
    }

    public void setSalary(double salary) {
        this.salary = new SimpleDoubleProperty(salary);
    }

    @Override
    public String toString() {
        return "Employee: [First Name: " + firstName.getValue() + " Last Name: " + lastName.getValue() + " Salary: $" + String.format("%.2f", Double.valueOf(salary.doubleValue())) + "]";
    }

}
