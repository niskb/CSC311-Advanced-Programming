package bcs.csc.lab16_mtservergui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientController {

    @FXML
    private TextField radiusTF;
    @FXML
    private TextArea textArea;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private double radius;
    private double area;

    public void initialize() {
        try {
            socket = new Socket("localhost", 8000);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void handleRadiusTF(ActionEvent event) {
        try {
            radius = Double.parseDouble(radiusTF.getText());
            radiusTF.clear();

            out.writeDouble(radius);
            area = in.readDouble();

            Platform.runLater(() -> {
                textArea.appendText("Radius sent to server: " + radius + "\n");
                textArea.appendText("Area received from server: " + area + "\n");
            });
            System.out.println("Radius sent to server: " + radius);
            System.out.println("Area received from server: " + area);

            socket.close();
        } catch (NumberFormatException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
