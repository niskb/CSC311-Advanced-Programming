/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package bcs.csc.lab13_coffeedbdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Brian
 */
public class Lab13_CoffeeDBDemo {

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:CoffeeDB; create=true")) {
            Statement stmt = conn.createStatement();

//            stmt.execute("DROP TABLE coffee");
//            System.out.println("Table coffee dropped.");

            String query = "CREATE TABLE coffee ("
                    + "Description VARCHAR(20),"
                    + "ProdNum CHAR(10) NOT NULL PRIMARY KEY,"
                    + "Price DOUBLE)";
            stmt.execute(query);
            System.out.println("Table coffee created.");

            String sqlStatement = "INSERT INTO Coffee "
                    + "(ProdNum, Price, Description)"
                    + " VALUES ('22-001', 8.65, 'Honduran Dark'),"
                    + "('18-002', 14.85, 'Kona Dark')";
            int rows = stmt.executeUpdate(sqlStatement);
            System.out.println(rows + " records were added to the database.");

            rows = stmt.executeUpdate("DELETE FROM coffee WHERE ProdNum = '22-001'");
            System.out.println(rows + " record(s) were deleted from the database.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
