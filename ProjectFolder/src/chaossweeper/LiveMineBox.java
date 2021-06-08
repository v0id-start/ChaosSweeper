package chaossweeper;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Random;


public class LiveMineBox {
    public static Image mine = new Image("mine.png");


    public static void findMine(int chance)
    {
        Random rnd = new Random();
        int foundNum = rnd.nextInt(chance);

        if (foundNum == 0)
            display();
    }

    public static void display()
    {

        Stage primaryStage = new Stage();

        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("LIVE MINE");
        primaryStage.setMinWidth(250);

        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);


        // Create and configure VBox
        // gap between components is 20
        VBox vb = new VBox(20);
        // center the components within VBox
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10, 10, 10, 10));
        // Make it as wide as the application frame (scene)
        vb.setPrefWidth(scene.getWidth());
        // Move the VBox down a bit
        vb.setLayoutY(30);
        CountDown countDown = new CountDown(5);

        countDown.getLabel().setStyle("-fx-text-fill: red;");


        ImageView mineView = new ImageView(mine);

        mineView.setFitHeight(20);
        mineView.setPreserveRatio(true);

        // Add the button and timerLabel to the VBox
        vb.getChildren().addAll(mineView, countDown);


        Random rnd = new Random();
        int mineType = rnd.nextInt(3);

        // Solve Arithmetic
        if (mineType == 0)
        {
            char[] operations = {'+', '-', 'x'};
            int numOne = rnd.nextInt(12)+1;
            int numTwo = rnd.nextInt(12)+1;
            char operator = operations[rnd.nextInt(operations.length)];

            Label equation = new Label(String.valueOf(numOne) + " " + operator + " " + String.valueOf(numTwo)+ " = ");
            equation.setStyle("-fx-font-size: 24px;");

            TextField answerField = new TextField();
            answerField.setPrefWidth(30);

            Button defuseButton = new Button("Defuse");
            defuseButton.setOnAction(event -> defuseMath(numOne, numTwo, operator, answerField.getText(), primaryStage, countDown));
            defuseButton.setDefaultButton(true);

            HBox equationBox = new HBox();
            equationBox.setAlignment(Pos.CENTER);
            equationBox.getChildren().addAll(equation, answerField);

            vb.getChildren().addAll(equationBox, defuseButton);
        }
        else if (mineType == 1)
        {
            String randString = getAlphaNumericString(3);
            Label instruction = new Label("Type the password: " + randString);
            instruction.setStyle("-fx-font-size: 24px;");
            instruction.setFont(javafx.scene.text.Font.font("Rockwell Extra Bold"));


            TextField answerField = new TextField();
            answerField.setPrefWidth(40);

            Button defuseButton = new Button("Defuse");
            defuseButton.setOnAction(event -> defuseString(randString, answerField.getText(), primaryStage, countDown));
            defuseButton.setDefaultButton(true);

            HBox equationBox = new HBox();
            equationBox.setSpacing(10);

            equationBox.setAlignment(Pos.CENTER);
            equationBox.getChildren().addAll(instruction, answerField);

            vb.getChildren().addAll(equationBox, defuseButton);
        }
        else if (mineType == 2)
        {
            String[] colors = {"RED", "BLUE", "GREEN"};
            int rndNum = rnd.nextInt(colors.length);
            String correctColorString = colors[rndNum];

            Text colorText = new Text(correctColorString);

            String randColorString = colors[rnd.nextInt(colors.length)];
            while (randColorString.equals(correctColorString))
            {
                randColorString = colors[rnd.nextInt(colors.length)];
            }

            switch (randColorString)
            {
                case ("RED"):
                {
                    colorText.setFill(Color.RED);
                    break;
                }
                case ("BLUE"):
                {
                    colorText.setFill(Color.BLUE);
                    break;
                }
                case ("GREEN"):
                {
                    colorText.setFill(Color.GREEN);
                    break;
                }
            }
            colorText.setStyle("-fx-font-size: 24px;");

            HBox instructionBox = new HBox();
            instructionBox.setAlignment(Pos.CENTER);
            instructionBox.setSpacing(10);
            Label instruction = new Label("Cut the");
            Label instruction2 = new Label(" wire");

            instruction.setStyle("-fx-font-size: 24px;");
            instruction2.setStyle("-fx-font-size: 24px;");

            instructionBox.getChildren().addAll(instruction, colorText, instruction2);


            HBox wireBox = new HBox();
            wireBox.setAlignment(Pos.CENTER);
            wireBox.setSpacing(20);

            Button redWire = new Button();
            redWire.setStyle("-fx-background-color: red;");
            redWire.setOnAction(event -> defuseWire(correctColorString, "RED", primaryStage, countDown));
            redWire.setMinHeight(50);
            redWire.setMinWidth(30);

            Button blueWire = new Button();
            blueWire.setStyle("-fx-background-color: blue;");
            blueWire.setOnAction(event -> defuseWire(correctColorString, "BLUE", primaryStage, countDown));
            blueWire.setMinHeight(50);
            blueWire.setMinWidth(30);

            Button greenWire = new Button();
            greenWire.setStyle("-fx-background-color: green;");
            greenWire.setOnAction(event -> defuseWire(correctColorString, "GREEN", primaryStage, countDown));
            greenWire.setMinHeight(50);
            greenWire.setMinWidth(30);

            wireBox.getChildren().addAll(redWire, blueWire, greenWire);

            vb.getChildren().addAll(instructionBox, wireBox);
        }




        // Add the VBox to the root component
        root.getChildren().add(vb);

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void defuseMath(int numOne, int numTwo, char operator, String ans, Stage window, CountDown cd)
    {
        boolean defused = false;

        switch (operator)
        {
            case ('+'):
            {
                if (numOne + numTwo == Integer.parseInt(ans))
                    defused = true;
                break;
            }

            case ('-'):
            {
                if (numOne - numTwo == Integer.parseInt(ans))
                    defused = true;
                break;
            }

            case ('x'):
            {
                if (numOne * numTwo == Integer.parseInt(ans))
                    defused = true;
                break;
            }

        }

        if (defused)
            defuse(window, cd);
    }

    public static void defuseString(String rndString, String ansString, Stage window, CountDown cd)
    {
        if (rndString.equals(ansString))
            defuse(window, cd);
    }

    public static void defuseWire(String correctColor, String ansColor, Stage window, CountDown cd)
    {
        if (correctColor.equals(ansColor))
            defuse(window,cd);
        else
        {
            GameManager.loseGame();
            window.close();
        }
    }

    public static void defuse(Stage window, CountDown cd)
    {
        cd.setRunning(false);
        window.close();
    }


    // function to generate a random string of length n
    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

}
