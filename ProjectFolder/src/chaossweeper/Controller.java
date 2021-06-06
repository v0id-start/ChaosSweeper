package chaossweeper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Scene difficultyScene;
    Scene customScene;

    public Button playButton;
    public Button customButton;
    public Button exitButton;

    public PieChart pieChart;

    public void goToDifficulty() throws IOException {
        Stage window = (Stage) playButton.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("difficulty.fxml"));

        difficultyScene = new Scene(root, 1000, 1000);
        window.setScene(difficultyScene);
    }

    public void goToCustom() throws IOException {
        Stage window = (Stage) customButton.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("custom.fxml"));

        customScene = new Scene(root, 1000, 1000);
        window.setScene(customScene);
    }

    public void exit()
    {
        Stage window = (Stage) exitButton.getScene().getWindow();
        window.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PieChart.Data youData = new PieChart.Data("Chaos", 100);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(youData);

        pieChart.setData(pieChartData);

    }
}
