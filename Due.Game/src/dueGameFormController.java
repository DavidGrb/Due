import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.util.Duration;

public class dueGameFormController {


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private GridPane kartenPlayer1 = new GridPane();

    @FXML
    private GridPane kartenPlayer2 = new GridPane();



    @FXML
    public void switchScenelookAway(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("lookAway.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Duration delay = Duration.seconds(3);
        Timeline timeline = new Timeline(new KeyFrame(delay, event1 -> {
            try {
                switchScenelayoutPlayers();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));
        timeline.play();

    }

    public void switchScenelayoutPlayers() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("layoutPlayers.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // Zugriff auf den Controller des geladenen FXML-Dokuments
        dueGameFormController playersController = loader.getController();

        // Aufruf der Methode zum Hinzufügen der Karten zum GridPane
        playersController.saveCardsPlayers();
    }

    //Logik

    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String RESET = "\033[0m";  // Text Reset

    String[] colors = {"Red","Blue","Green","Yellow"};


    @FXML
    public void saveCardsPlayers(){
        //füllt ein deck mit zufällig generierten karten
        for (int i = 0; i < 7; i++) {
            int rndNum = (int) Math.floor(Math.random() * (9 + 1));
            int rndColor = (int) Math.floor(Math.random() * (3 + 1));
            kartenPlayer1.add(new Button(colors[rndColor] + " " + rndNum), i, 0);
        }
        for (int i = 0; i < 7; i++) {
            int rndNum = (int) Math.floor(Math.random() * (9 + 1));
            int rndColor = (int) Math.floor(Math.random() * (3 + 1));
            kartenPlayer2.add(new Button(colors[rndColor] + " " + rndNum), i, 0);
        }
    }






}
