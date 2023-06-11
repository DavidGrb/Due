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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.util.Duration;

public class dueGameFormController {


    private Stage stage;
    private Scene scene;
    private Parent root;
    //wenn 1 dann player1 wenn -1 dann player2
    private int currentPlayer = 1;


    @FXML
    private GridPane kartenPlayer1 = new GridPane();

    @FXML
    private GridPane kartenPlayer2 = new GridPane();

    @FXML
    private StackPane stack = new StackPane();

    private boolean hasHappened1 = false;
    private boolean hasHappened2 = false;

    @FXML
    public void switchScenelookAway(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("lookAway.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
        if (currentPlayer == 1) {
            root = FXMLLoader.load(getClass().getResource("layoutPlayers.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("layoutPlayers.fxml"));
            root = loader.load();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            //Zugriff auf den Controller des geladenen FXML-Dokuments
            dueGameFormController playersController = loader.getController();

            if (hasHappened1 == false) {
                // Aufruf der Methode zum Hinzufügen der Karten zum GridPane
                playersController.saveCardsPlayers();
                hasHappened1 = true;
            }

            // Setzen der Event Handler für die Buttons
            playersController.setButtonHandlers();
        } else if (currentPlayer == -1) {
            root = FXMLLoader.load(getClass().getResource("layoutPlayer2.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("layoutPlayer2.fxml"));
            root = loader.load();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            // Zugriff auf den Controller des geladenen FXML-Dokuments
            dueGameFormController player2Controller = loader.getController();

            if (hasHappened2 == false) {
                // Aufruf der Methode zum Hinzufügen der Karten zum GridPane
                player2Controller.saveCardsPlayers();
                hasHappened2 = true;
            }

            // Setzen der Event Handler für die Buttons
            player2Controller.setButtonHandlers();

        }

    }

    //Logik

    String[] colors = {"Red", "Blue", "Green", "Yellow"};


    @FXML
    public void saveCardsPlayers() {
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

    public void changePlayer() {
        currentPlayer = currentPlayer * -1;
    }

    @FXML
    public void handleButtonClicked(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        // Den Button aus dem GridPane entfernen
        kartenPlayer1.getChildren().remove(clickedButton);

        // Den Button dem StackPane hinzufügen
        stack.getChildren().add(clickedButton);
        changePlayer();
        try {
            switchScenelookAway(event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setButtonHandlers() {
        for (Node node : kartenPlayer1.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setOnAction(this::handleButtonClicked);
            }
        }
        for (Node node : kartenPlayer2.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setOnAction(this::handleButtonClicked);
            }
        }
    }


}
