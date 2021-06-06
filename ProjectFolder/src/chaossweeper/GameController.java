package chaossweeper;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    Scene menuScene;

    public GridPane gridPane;

    public Button menuButton;
    public Label minesLeftLabel;

    public void goToMenu() throws IOException {
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

        switch (GameSettings.difficulty)
        {
            case (1):
            {
                numRows = 10;
                numCols = 10;
                numMines = 10;
                break;
            }
            case (2):
            {
                numRows = 15;
                numCols = 15;
                numMines = 40;
                break;
            }
            case (3):
            {
                numRows = 30;
                numCols = 16;
                numMines = 99;
                break;
            }

        }


        GameManager.minesLeftLabel = minesLeftLabel;
        GameManager.setMinesLeft(numMines);

        MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(numRows, numCols, numMines, gridPane);
        GameManager.cellBoard = mineSweeperBoard.getBoard();


    }
}
