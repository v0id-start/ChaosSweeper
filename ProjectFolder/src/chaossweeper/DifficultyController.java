package chaossweeper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DifficultyController {
    Scene menuScene;
    Scene gameScene;

    public Button easyButton;
    public Button normalButton;
    public Button hardButton;
    public Button menuButton;

    public void setDifficultyEasy()
    {
        play(1);
    }

    public void setDifficultyNormal()
    {
        play(2);
    }

    public void setDifficultyHard() { play(3); }

    public void play(int difficulty) {
        Stage window = (Stage) easyButton.getScene().getWindow();

        GameSettings.difficulty = difficulty;

        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));

            gameScene = new Scene(root, 1920, 1080);
            window.setScene(gameScene);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }



    public void goToMenu() throws IOException {
        Stage window = (Stage) menuButton.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));

        menuScene = new Scene(root, 800, 1000);
        window.setScene(menuScene);
    }
}