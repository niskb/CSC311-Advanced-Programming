/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bcs.csc.lab16_multi_threaded_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Brian
 */
public class Client {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8000);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            System.err.print("Enter a radius or -1 to exit: ");
            double radius = keyboard.nextDouble();

            while (radius != -1) {
                out.writeDouble(radius);
                System.out.println("Sent to server: " + radius);

                double area = in.readDouble();
                System.out.println("Received from server: " + area);

                System.err.print("Enter a radius or -1 to exit: ");
                radius = keyboard.nextDouble();
            }

            socket.close();
        } catch (InputMismatchException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        keyboard.close();
    }

}
