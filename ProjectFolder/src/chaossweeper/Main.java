package chaossweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage  window;

    Scene menuScene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        window = primaryStage;
        window.setTitle("CHAOS SWEEPER");
        menuScene = new Scene(root, 1000, 1000);

        window.setScene(menuScene);

        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
