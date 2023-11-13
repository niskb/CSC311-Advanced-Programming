/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bcs.csc.brian_niski_assignment5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Brian
 */
public class EmployeeDBController {

    private static final String DB_URL = "jdbc:derby:EmployeeDB";

    public static void createTable(boolean isCreated) {
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
            System.out.println("Connected to " + DB_URL + "!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
