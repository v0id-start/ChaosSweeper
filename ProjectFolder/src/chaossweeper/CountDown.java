package chaossweeper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CountDown extends VBox {

    private Timeline animation;
    private String S = "";
    private int numSeconds;

    private boolean running = true;
    Label label = new Label();


    public CountDown(int nSeconds) {
        label.setText(String.valueOf(nSeconds));

        this.numSeconds = nSeconds;


        label.setFont(javafx.scene.text.Font.font("Rockwell Extra Bold", 50));
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);

        getChildren().add(label);
        animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> timeLabel()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public Label getLabel() { return this.label; }
    public void setRunning(boolean val) { this.running = val; }

    public void timeLabel() {
        if (running)
        {
            numSeconds--;
            S = numSeconds + "";
            label.setText(S);

            if (numSeconds < 11)
                this.label.setStyle("-fx-text-fill: red;");

            if (numSeconds < 1)
            {
                GameManager.loseGame();
                running = false;
                Stage window = (Stage) this.getScene().getWindow();
                window.close();
            }
        }

    }

}