/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package bcs.csc.lab13_coffeedbdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Brian
 */
public class Lab13_CoffeeDBDemo {

    private final static String DB_URL = "jdbc:derby:CoffeeDB";

    public static void main(String[] args) {
        createTable();
        addRecordPPS();
        listRecord();
        deleteRecord();
        listRecord();
    }

    public static void createTable() {
        System.out.println("\nCreate Table =====");
        try {
            Connection conn = DriverManager.getConnection(DB_URL + "; create=true");
            // Don't drop the table for the first time don't add the next two statements:
            // Drawback in Derby to check if the table already exists
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE Coffee");
            System.out.println("Table Coffee dropped.");
            String query = "CREATE TABLE Coffee ("
                    + "Description VARCHAR(20),"
                    + "ProdNum CHAR(10) NOT NULL PRIMARY KEY,"
                    + "Price DOUBLE)";
            stmt.execute(query);
            System.out.println("Table Coffee created.");
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void listRecord() {
        System.out.println("\nList Record =====");
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Coffee";
            ResultSet result = stmt.executeQuery(query);
            String prodNum, description;
            double price;
            while (result.next()) {
                prodNum = result.getString("ProdNum");
                description = result.getString("Description");
                price = result.getDouble("Price");
                System.out.printf("%-10s%-20s%.2f\n", prodNum, description, price);
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void addRecord() {
        System.out.println("\nAdd Record =====");
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();

            String sqlStatement = "INSERT INTO Coffee "
                    + "(ProdNum, Price, Description)"
                    + " VALUES ('22-001', 8.65, 'Honduran Dark'),"
                    + "('18-002', 14.85, 'Kona Dark')";
            int rows = stmt.executeUpdate(sqlStatement);
            System.out.println(rows + " records were added to the database.");

            Scanner keyboard = new Scanner(System.in);
            String prodNum, description;
            double price;
            System.out.print("Enter the prodNumber of a record: ");
            prodNum = keyboard.nextLine();
            System.out.print("Enter the description of the record: ");
            description = keyboard.nextLine();
            System.out.print("Enter the price of the record: ");
            price = keyboard.nextDouble();      // column names                      // variables
            String query = "INSERT INTO Coffee (ProdNum, Price, Description) VALUES ('" + prodNum + "'," + price + ", '" + description + "')";
            rows = stmt.executeUpdate(query);
            System.out.println(rows + " record was added to the database.");
            keyboard.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void addRecordPPS() {
        System.out.println("\nAdd Record PreparedStatement =====");
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();

            Scanner keyboard = new Scanner(System.in);
            String prodNum, description;
            double price;
            System.out.print("Enter the prodNumber of a record: ");
            prodNum = keyboard.nextLine();
            System.out.print("Enter the description of the record: ");
            description = keyboard.nextLine();
            System.out.print("Enter the price of the record: ");
            price = keyboard.nextDouble();
            String query = "INSERT INTO Coffee (ProdNum, Price, Description) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, prodNum);
            pstmt.setDouble(2, price);
            pstmt.setString(3, description);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " record was added to the database.");
            keyboard.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void deleteRecord() {
        System.out.println("\nDelete Record =====");
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            int rows = stmt.executeUpdate("DELETE FROM Coffee WHERE ProdNum = '22-001'");
            System.out.println(rows + " record(s) were deleted from the database.");
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

}
