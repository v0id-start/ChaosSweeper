package chaossweeper;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ExplosionBox {


    public static void display() {
        try {

            // get URL of the video file

            URL url = ExplosionBox.class.getResource("explosion.mp4");

            // create a Media object for the specified URL

            Media media = new Media(url.toExternalForm());

            // create a MediaPlayer to control Media playback
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("MINE");

            //URL urlToExplosion = (ExplosionBox.class.getResource("explosion.mp4"));
            //String path = urlToExplosion.getPath();

            //String path = "media/explosion.mp4";
            //Media media = new Media(new File(path).toURI().toString());

            //MediaPlayer mediaPlayer = new MediaPlayer(media);

            mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    primaryStage.close();
                }
            });

            MediaView mediaView = new MediaView(mediaPlayer);


            Scene scene = new Scene(new Pane(mediaView), 800, 500);
            primaryStage.setScene(scene);
            primaryStage.show();

            mediaPlayer.play();

        }    catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
