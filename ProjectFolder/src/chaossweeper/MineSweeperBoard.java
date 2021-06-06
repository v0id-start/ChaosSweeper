package chaossweeper;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.Random;

public class MineSweeperBoard {

    private Cell[][] board;

    private int numRows;
    private int numCols;

    private int numMines;

    private GridPane gridPane;

    public MineSweeperBoard(int rows, int cols, int mines, GridPane grid)
    {
        this.numRows = rows;
        this.numCols = cols;
        this.numMines = mines;

        this.board = createCellBoard(rows, cols, mines);

        gridPane = grid;
        this.populateGridPane(45, 45);
    }

    public int getRows() { return  this.numRows; }
    public int getCols() { return  this.numCols; }

    private Cell[][] createCellBoard(int rowNum, int colNum, int numMines)
    {
        Cell[][] cellBoard = new Cell[rowNum][colNum];

        // First place mines on board at random positions
        while (numMines > 0)
        {
            Random rnd = new Random();

            int rndRow = rnd.nextInt(rowNum);
            int rndCol = rnd.nextInt(colNum);

            if (cellBoard[rndRow][rndCol] == null)
            {
                cellBoard[rndRow][rndCol] = new Cell(-1, rndCol, rndRow);
                numMines -= 1;
            }

        }

        // Next fill in numbers based on how many mines are in adjacent cells
        for (int r = 0; r < rowNum; r++)
        {
            for (int c = 0; c < colNum; c++)
            {
                if (cellBoard[r][c] == null)
                {
                    int numAdjacentMines = getNumAdjacentMines(cellBoard, r, c);
                    cellBoard[r][c] = new Cell(numAdjacentMines, r, c);
                }
            }
        }

        return cellBoard;

    }

    private int getNumAdjacentMines(Cell[][] cellBoard, int r, int c)
    {
        int adjacentMines = 0;

        int[][] dirs = { {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

        for (int[] dir : dirs)
            if (getAdjacentCell(cellBoard, r, c, dir).getValue() == -1)
                adjacentMines++;


        return adjacentMines;

    }


    public static Cell getAdjacentCell(Cell[][] cellBoard, int r, int c, int[] dir)
    {
        Cell cell = new Cell();

        // Check NorthEast
        if ( (r + dir[0] >= 0 && r + dir[0] < cellBoard.length) && (c + dir[1] < cellBoard[0].length && c + dir[1] >= 0) )
        {
            if (cellBoard[ r+dir[0] ][ c+dir[1] ] != null)
                cell = cellBoard[ r+dir[0] ] [ c+dir[1] ];
        }


        return cell;
    }


    private void populateGridPane(int colHeight, int rowLength)
    {
        for (int r = 0; r < this.board.length; r++)
        {
            for (int c = 0; c < this.board[0].length; c++)
            {
                Cell currentCell = this.board[r][c];
                Button cellButton = currentCell.getButton();

                cellButton.getStyleClass().add("letter-cell");

                cellButton.setMaxWidth(Double.MAX_VALUE);
                cellButton.setMaxHeight(Double.MAX_VALUE);

                cellButton.setOnMouseClicked(mouseEvent -> {
                    MouseButton button = mouseEvent.getButton();
                    if (button == MouseButton.PRIMARY)
                        currentCell.clickCell();
                    else if (button == MouseButton.SECONDARY)
                        currentCell.flagCell();
                    else if (button == MouseButton.MIDDLE)
                        currentCell.middleClickCell();
                });


                this.gridPane.add(cellButton, r, c);

            }

        }

        for (int i = 0; i < this.board[0].length; i++)
        {
            RowConstraints rowConstraint = new RowConstraints(rowLength);
            gridPane.getRowConstraints().add(rowConstraint);
        }

        for (int i = 0; i < this.board.length; i++)
        {
            ColumnConstraints columnConstraint = new ColumnConstraints(colHeight);
            gridPane.getColumnConstraints().add(columnConstraint);
        }
    }

    public Cell[][] getBoard()
    {
        return this.board;
    }

    public String toString()
    {
        String s = "";

        for (Cell[] cellArr : this.board)
        {
            for (Cell cell : cellArr)
            {
                if (cell != null)
                    s += cell.getValue();
                else
                    s += "n";
            }

            s+="\n";
        }
        return s;
    }
}
