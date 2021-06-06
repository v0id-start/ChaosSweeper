package chaossweeper;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cell {
    private int value;

    private int row;
    private int col;

    private Button button;

    private boolean clicked;
    private boolean flagged;

    public static Image flag = new Image("flag.png");
    public static Image mine = new Image("mine.png");


    public Cell(int val, int r, int c)
    {

        this.value = val;

        this.row = r;
        this.col = c;

        this.clicked = false;
        this.flagged = false;

        this.button = new Button();
    }

    public Cell()
    {
        this.value = -2;

        this.row = -1;
        this.col = -1;

        this.clicked = false;
        this.flagged = false;

        this.button = new Button();
    }
    public boolean getFlagged() { return this.flagged; }

    public void clickCell()
    {
        if (!this.flagged)
        {
            this.clicked = true;
            this.getButton().setStyle("-fx-background-color: #4a4a4a;");

            if (this.value == 0)
            {
                GameManager.addSafeCell();
                this.getButton().setText("");
                this.getButton().setStyle("-fx-background-color: #404040;");
            }
            else if (this.value == -1)
            {
                this.getButton().setStyle("-fx-background-color: black;");
                this.getButton().setText("");
                ImageView mineView = new ImageView(mine);

                mineView.setFitHeight(30);
                mineView.setPreserveRatio(true);
                this.getButton().setGraphic(mineView);

                GameManager.loseGame();

            }
            else
            {
                GameManager.addSafeCell();
                this.getButton().setText(String.valueOf(this.getValue()));
                switch (this.value)
                {
                    case (1):
                    {
                        this.getButton().setStyle("-fx-text-fill: #17a6ff;");
                        break;
                    }
                    case (2):
                    {
                        this.getButton().setStyle("-fx-text-fill: #00f508;");
                        break;
                    }
                    case (3):
                    {
                        this.getButton().setStyle("-fx-text-fill: #ff172e;");
                        break;
                    }
                    case (4):
                    {
                        this.getButton().setStyle("-fx-text-fill: #9012ff;");
                        break;
                    }
                    case (5):
                    {
                        this.getButton().setStyle("-fx-text-fill: #ff00ea;");
                        break;
                    }
                    case (6):
                    {
                        this.getButton().setStyle("-fx-text-fill: #f5fc12;");
                        break;
                    }
                    case (7):
                    {
                        this.getButton().setStyle("-fx-text-fill: #919191;");
                        break;
                    }
                    case (8):
                    {
                        this.getButton().setStyle("-fx-text-fill: #00ff88;");
                        break;
                    }

                }


            }


            if (this.getValue() == 0)
                clickUntilEdge();
        }

    }

    public void middleClickCell()
    {
        int[][] dirs = { {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

        if (this.clicked)
        {
            int flags = 0;

            for (int[] dir : dirs)
            {
                Cell adjacentCell = MineSweeperBoard.getAdjacentCell(GameManager.cellBoard, this.row, this.col, dir);
                if (adjacentCell.getFlagged())
                    flags++;
            }

            if (flags == this.getValue())
            {
                for (int[] dir : dirs)
                {
                    Cell adjacentCell = MineSweeperBoard.getAdjacentCell(GameManager.cellBoard, this.row, this.col, dir);
                    if (!adjacentCell.getFlagged() && !adjacentCell.clicked)
                        adjacentCell.clickCell();
                }
            }
        }


    }

    public void flagCell()
    {

        if (!this.clicked)
        {
            ImageView flagView = new ImageView(flag);

            flagView.setFitHeight(24);
            flagView.setPreserveRatio(true);
            if (!this.flagged)
            {
                GameManager.removeMineLeft();
                this.getButton().setGraphic(flagView);
                this.flagged = true;
            }
            else if (this.flagged)
            {
                GameManager.addMineLeft();
                this.getButton().setGraphic(null);
                this.flagged = false;
            }

        }

    }

    public void clickUntilEdge()
    {
        int[][] dirs = { {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

        for (int[] dir : dirs)
        {
            boolean edgeReached = false;

            Cell adjacentCell = MineSweeperBoard.getAdjacentCell(GameManager.cellBoard, this.row, this.col, dir);
            int adjacentValue = adjacentCell.getValue();

            if (adjacentValue == 0 && !adjacentCell.clicked)
                adjacentCell.clickCell();
            else if (adjacentValue != 0 && !edgeReached)
            {
                adjacentCell.clickCell();
                edgeReached = true;
            }


        }
    }



    public int getValue()
    {
        return this.value;
    }

    public Button getButton()
    {
        return this.button;
    }

    public int getRow() { return this.row; }
    public int getCol() { return this.col; }

    public String toString()
    {
        return "X: " + this.getCol() + "|  Y: " + this.getRow() + "|  Val: " + this.getValue();
    }
}
