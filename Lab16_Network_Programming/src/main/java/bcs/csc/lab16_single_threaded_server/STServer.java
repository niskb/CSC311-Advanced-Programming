/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package bcs.csc.lab16_single_threaded_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Brian
 */
public class STServer {

    public static void main(String[] args) {
        boolean keepServerRunning = true;
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Server started");

            while (keepServerRunning) {
                double radius = in.readDouble();
                System.out.println("Received from client: " + radius);

                double area = radius * radius * Math.PI; // of a circle
                out.writeDouble(area);
                System.out.println("Sent to client: " + area);
            }

            serverSocket.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
