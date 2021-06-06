package chaossweeper;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameManager {

    public static Cell[][] cellBoard;

    public static int minesLeft;
    public static Label minesLeftLabel;

    public static int totalNumMines = minesLeft;
    public static int numSafeCellsClicked = 0;

    public static Stage window;
    public static void addMineLeft()
    {
        minesLeft++;
        minesLeftLabel.setText(String.valueOf(minesLeft));
    }
    public static void removeMineLeft()
    {
        minesLeft--;
        minesLeftLabel.setText(String.valueOf(minesLeft));
    }
    public static void setMinesLeft(int m)
    {
        minesLeft = m;
        minesLeftLabel.setText(String.valueOf(minesLeft));
    }

    public static void addSafeCell()
    {
        numSafeCellsClicked++;
        if ((cellBoard.length * cellBoard[0].length) - totalNumMines == numSafeCellsClicked)
        {
            System.out.println("YOU WIN!");
        }
    }

    public static void loseGame()
    {
        Stage window = (Stage) minesLeftLabel.getScene().getWindow();
        System.out.println("YOU LOSE");
        window.close();
        ExplosionBox.display();
    }
}
