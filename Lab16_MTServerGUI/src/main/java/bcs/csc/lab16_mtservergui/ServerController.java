package bcs.csc.lab16_mtservergui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ServerController {

    @FXML
    private static TextArea textArea;

    private ServerSocket serverSocket;
    private Socket socket;

    private static DataInputStream in;
    private static DataOutputStream out;

    private static double radius;
    private static double area;

    public void initialize() {
        boolean keepServerRunning = true;
        try {
            serverSocket = new ServerSocket(8001);
            textArea.appendText("Server started " + new Date() + "\n");
            socket = new Socket();
            while (keepServerRunning) {
                socket = serverSocket.accept();

                ServerThread serverThread = new ServerThread(socket);
                serverThread.printThreadInfo();

                Platform.runLater(() -> {
                    textArea.appendText("Client connected\n");
                });
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
            new Thread(() -> {
                boolean keepServerRunning = true;
                try {
                    in = new DataInputStream(socket.getInputStream());
                    out = new DataOutputStream(socket.getOutputStream());
                    System.out.println("Server started");

                    while (keepServerRunning) {
                        radius = in.readDouble();
                        area = radius * radius * Math.PI;
                        out.writeDouble(area);

                        Platform.runLater(() -> {
                            textArea.appendText("Radius received from client: " + radius + "\n");
                            textArea.appendText("Area sent to client: " + area + "\n");
                        });
                        System.out.println("Radius received from client: " + radius);
                        System.out.println("Area sent to client: " + area);
                    }
                    socket.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }).start();
        }
    }

}
