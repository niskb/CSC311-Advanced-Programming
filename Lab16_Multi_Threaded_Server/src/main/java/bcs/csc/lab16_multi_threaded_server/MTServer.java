/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package bcs.csc.lab16_multi_threaded_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Brian
 */
public class MTServer {

    public static void main(String[] args) {
        boolean keepServerRunning = true;
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            Socket socket = new Socket();
            while (keepServerRunning) {
                socket = serverSocket.accept();

                ServerThread serverThread = new ServerThread(socket);
                serverThread.printThreadInfo();

                serverThread.start();
            }
            serverSocket.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static class ServerThread extends Thread {

        private Socket socket;

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        public void printThreadInfo() {
            System.out.println("Internet Address: " + socket.getInetAddress());
            System.out.println("Local Address: " + socket.getLocalAddress());
            System.out.println("Local Port: " + socket.getLocalPort());
            System.out.println("Port: " + socket.getPort());
        }

        @Override
        public void run() {
            boolean keepServerRunning = true;
            try {
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
                socket.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
