package chaossweeper;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameManager {

    public static Cell[][] cellBoard;

    public static int minesLeft;
    public static Label minesLeftLabel;

    public static Label winLabel;

    public static int totalNumMines;
    public static int numSafeCellsClicked = 0;

    public static Stage window;

    public static boolean gameLost = false;
    public static boolean firstClicked = false;

    public static CountDown countDown;
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
            winGame();
        }
    }

    public static void checkIfWon()
    {
        boolean won = true;
        for (Cell[] cellArr : cellBoard)
            for (Cell c : cellArr)
                if (!c.getClicked() && c.getValue() != -1)
                    won = false;

        if (won)
            winGame();
    }

    public static void winGame()
    {
        winLabel.setOpacity(1);
        countDown.setRunning(false);
    }

    public static void loseGame()
    {
        Stage window = (Stage) minesLeftLabel.getScene().getWindow();

        try{
            ExplosionBox.display();
        }catch (Exception e)
        {
            System.out.println(e);
        }
        window.close();

        System.out.println("YOU LOSE");
        for (Cell[] cellArr : cellBoard)
        {
            for (Cell c : cellArr)
            {
                if (c.getValue()==-1)
                    c.clickCell();

            }
        }
    }

    public static void resetGame()
    {

        numSafeCellsClicked = 0;

        gameLost = false;
        firstClicked = false;
    }
}
