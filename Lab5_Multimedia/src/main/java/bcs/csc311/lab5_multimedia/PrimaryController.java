package bcs.csc311.lab5_multimedia;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class PrimaryController {

    @FXML
    private ImageView imageView;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button stopButton;
    @FXML
    private MediaView mediaView;
    
    private MediaPlayer mediaPlayer;

    public void initialize() {
//      Image image = new Image("file:image/372x279.jpg");
        Image image = new Image(new File("image/372x279.jpg").toURI().toString()); 
        imageView.setImage(image);
        
        Media media = new Media(new File("media/stock.mp4").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    }
    
    @FXML
    private void playButtonHandler(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    private void pauseButtonHandler(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    private void stopButtonHandler(ActionEvent event) {
        mediaPlayer.stop();
    }

    
    
}
