/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bcs.csc.brian_niski_assignment5;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Brian
 */
public class Employee {

    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty email;
    private SimpleStringProperty phone;
    private SimpleDoubleProperty salary;

    public Employee(String firstName, String lastName, String email, String phone, double salary) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.salary = new SimpleDoubleProperty(salary);
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public void setPhone(String phone) {
        this.phone = new SimpleStringProperty(phone);
    }

    public void setSalary(double salary) {
        this.salary = new SimpleDoubleProperty(salary);
    }

    public String getFirstName() {
        return this.firstName.getValue();
    }

    public String getLastName() {
        return this.lastName.getValue();
    }

    public String getEmail() {
        return this.email.getValue();
    }

    public String getPhone() {
        return this.phone.getValue();
    }

    public double getSalary() {
        return this.salary.getValue();
    }

    @Override
    public String toString() {
        return "Employee{" + "firstName=" + firstName.getValue() + ", lastName=" + lastName.getValue() + ", email=" + email.getValue() + ", phone=" + phone.getValue() + ", salary=" + salary.getValue() + '}';
    }

}
