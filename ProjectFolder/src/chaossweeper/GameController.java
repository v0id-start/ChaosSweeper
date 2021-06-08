package chaossweeper;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    Scene menuScene;

    public GridPane gridPane;

    public Button menuButton;
    public Label minesLeftLabel;
    public Label winLabel;

    public Pane timeLeftPane;

    public void goToMenu() throws IOException {
        GameManager.countDown.setRunning(false);
        Stage window = (Stage) menuButton.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));

        menuScene = new Scene(root, 800, 1000);
        window.setScene(menuScene);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        int numRows = 15;
        int numCols = 15;
        int numMines = 40;

        int timeLeft = 60;

        switch (GameSettings.difficulty)
        {
            case (1):
            {
                numRows = 9;
                numCols = 9;
                numMines = 10;
                timeLeft = 60;
                break;
            }
            case (2):
            {
                numRows = 16;
                numCols = 16;
                numMines = 40;
                timeLeft = 240;
                break;
            }
            case (3):
            {
                numRows = 30;
                numCols = 16;
                numMines = 99;
                timeLeft = 300;
                break;
            }

        }

        CountDown timeLeftCd = new CountDown(timeLeft);

        GameManager.countDown = timeLeftCd;

        timeLeftCd.setAlignment(Pos.BASELINE_RIGHT);
        timeLeftPane.getChildren().add(timeLeftCd);

        GameManager.totalNumMines = numMines;

        GameManager.minesLeftLabel = minesLeftLabel;
        GameManager.setMinesLeft(numMines);

        GameManager.winLabel = winLabel;

        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(numRows, numCols, numMines, gridPane, 5);
        GameManager.cellBoard = mineSweeperBoard.getBoard();


    }
}
