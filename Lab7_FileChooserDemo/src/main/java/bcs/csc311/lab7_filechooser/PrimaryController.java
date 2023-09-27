package bcs.csc311.lab7_filechooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class PrimaryController {

    @FXML
    private TextArea textArea;

    @FXML
    private void openMenuItemHandler(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Scanner inFile = null;
            String line = "";
            try {
                inFile = new Scanner(selectedFile);
                while (inFile.hasNext()) {
                    line += inFile.nextLine() + "\n";
//                line = inFile.nextLine() + "\n";
//                textArea.appendText(line);
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            textArea.setText(line);
            inFile.close();
        }
    }

    @FXML
    private void saveMenuItemHandler(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showSaveDialog(null);
        String content = "";
        if (selectedFile != null) {
            PrintStream outFile = null;
            try {
                outFile = new PrintStream(selectedFile);
                content = textArea.getText();
                outFile.print(content);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            outFile.close();
        }
    }

    @FXML
    private void closeMenuItemHandler(ActionEvent event) {
        Platform.exit();
    }

}
