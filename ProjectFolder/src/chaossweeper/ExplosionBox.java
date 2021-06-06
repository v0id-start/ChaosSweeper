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

public class ExplosionBox {

/*
    public static void display()
    {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("MINE");
        window.setMinWidth(1000);
        window.setMinHeight(1000);


        Button okButton = new Button("Ok");
        okButton.setOnAction(event -> window.close());

        Media media = new Media("explosion.mp4");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView (mediaPlayer);

        VBox layout = new VBox(1000);
        layout.getChildren().addAll(mediaView, okButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }


 */


    public static void display()
    {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("MINE");

        File mediaFile = new File("explosion.mp4");
        Media media = null;
        try {
            media = new Media(mediaFile.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        MediaPlayer mediaPlayer = new MediaPlayer(media);

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
    }


}
