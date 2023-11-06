/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package bcs.csc.lab13_coffeedbdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
//        createTable();
//        addRecordPPS();
        listRecord();
//        deleteRecord();
//        listRecord();
        queryDatabaseWhileLoop();
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
                    + "(ProdNum, Description, Price)"
                    + " VALUES ('22-001', 'Honduran Dark', 8.65),"
                    + "('18-002', 'Kona Dark', 14.85)";
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
            String query = "INSERT INTO Coffee (ProdNum, Description, Price) VALUES ('" + prodNum + "', '" + description + "', " + price + ")";
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
            // Reminder: primaryKey cannot be duplicated
            /*String query = "INSERT INTO Coffee (ProdNum, Description, Price) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, prodNum);
            pstmt.setString(2, description);
            pstmt.setDouble(3, price);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " record was added to the database.");*/
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

    public static void queryDatabase() {
        System.out.println("\nQuery Database =====");
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a sql statement: ");
        String query = keyboard.nextLine();
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            // By default, cursor is first row going forward
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = stmt.executeQuery(query);
            // To get the number of rows
            resultSet.last();
            int numRows = resultSet.getRow();
            System.out.println("The number of rows: " + numRows);
            resultSet.first();
            // To get the meta data, columns and names of columns
            ResultSetMetaData meta = resultSet.getMetaData();
            int numCols = meta.getColumnCount();
            String[] colNames = new String[numCols];
            for (int i = 0; i < numCols; i++) {
                colNames[i] = meta.getColumnLabel(i + 1);
            }
            String[][] tableData = new String[numRows][numCols];
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    tableData[i][j] = resultSet.getString(j + 1);
                    System.out.print(tableData[i][j] + "\t");
                }
                System.out.println();
                resultSet.next();
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        keyboard.close();
    }

    public static void queryDatabaseWhileLoop() {
        System.out.println("\nQuery Database =====");
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a sql statement: ");
        String query = keyboard.nextLine();
        while (!query.equals("exit")) {
            try {
                Connection conn = DriverManager.getConnection(DB_URL);
                // By default, cursor is first row going forward
                Statement stmt = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                ResultSet resultSet = stmt.executeQuery(query);
                // To get the number of rows
                resultSet.last();
                int numRows = resultSet.getRow();
                System.out.println("The number of rows: " + numRows);
                resultSet.first();
                // To get the meta data, columns and names of columns
                ResultSetMetaData meta = resultSet.getMetaData();
                int numCols = meta.getColumnCount();
                String[] colNames = new String[numCols];
                for (int i = 0; i < numCols; i++) {
                    colNames[i] = meta.getColumnLabel(i + 1);
                }
                String[][] tableData = new String[numRows][numCols];
                for (int i = 0; i < numRows; i++) {
                    for (int j = 0; j < numCols; j++) {
                        tableData[i][j] = resultSet.getString(j + 1);
                        System.out.print(tableData[i][j] + "\t");
                    }
                    System.out.println();
                    resultSet.next();
                }
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
            System.out.print("Enter a sql statement: ");
            query = keyboard.nextLine();
        }
        keyboard.close();
    }

}
