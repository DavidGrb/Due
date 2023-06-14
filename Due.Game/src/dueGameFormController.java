import com.sun.tools.jconsole.JConsoleContext;
import com.sun.tools.jconsole.JConsolePlugin;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class dueGameFormController {

    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    private static int currentPlayer = 1;

    @FXML
    private GridPane kartenPlayer1;

    @FXML
    private GridPane kartenPlayer2;

    @FXML
    private StackPane stack = new StackPane();

    private static boolean hasHappened1 = false;
    private static boolean hasHappened2 = false;

    private static List<Card> cardsPlayer1 = new ArrayList<Card>();
    private static List<Card> cardsPlayer2 = new ArrayList<Card>();

    @FXML
    private Text curCard; //Das text Element mit der Cur Card

    private static String currentCard = createStackCard(); //Die Aktuelle Karte Als String

    //erstellt die Stack-Karte am Anfang
    public static String createStackCard(){
        String[] colors = {"Rot", "Blau", "Grün", "Gelb"};
        int rndNum = (int) Math.floor(Math.random() * (9 + 1));
        int rndColor = (int) Math.floor(Math.random() * (3 + 1));
        currentCard = colors[rndColor] +" "+rndNum;
        return currentCard;
    }


    @FXML
    public void switchScenelookAway(ActionEvent event) throws Exception {

        if(currentPlayer == 1){
            root = FXMLLoader.load(getClass().getResource("lookAway.fxml"));
        }else{
            root = FXMLLoader.load(getClass().getResource("lookAway2.fxml"));
        }
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("layoutPlayers.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            dueGameFormController playersController = loader.getController();
            kartenPlayer1 = playersController.kartenPlayer1;

            curCard = playersController.curCard;

            curCard.setText("Stapel-Karte: "+currentCard);

            if (hasHappened1 == false) {
                playersController.saveCardsPlayers();
                System.out.println("saveCardsPlayers() aufgerufen");
                hasHappened1 = true;
            }
            fillGridPane();
            playersController.setButtonHandlers();
        } else if (currentPlayer == -1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("layoutPlayer2.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            dueGameFormController player2Controller = loader.getController();
            kartenPlayer2 = player2Controller.kartenPlayer2;

            curCard = player2Controller.curCard;

            curCard.setText("Stapel-Karte: "+currentCard);

            if (hasHappened2 == false) {
                player2Controller.saveCardsPlayers();
                hasHappened2 = true;
            }
            fillGridPane();
            player2Controller.setButtonHandlers();
        }
        System.out.println(kartenPlayer1.getChildren());
        System.out.println(cardsPlayer1);
        System.out.println(cardsPlayer2);
    }

    public void switchSceneWinner(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("winnerScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    //Logik

    String[] colors = {"Rot", "Blau", "Grün", "Gelb"};

    public boolean canThrowCard(Button card){
        String cardColor = card.getText().split(" ")[0];
        int cardNumber = Integer.parseInt(card.getText().split(" ")[1]);

        if(cardColor.equals(currentCard.split(" ")[0]) || cardNumber == Integer.parseInt(currentCard.split(" ")[1])){
            return true;
        }else{
            return false;
        }
    }


    @FXML
    public void saveCardsPlayers() {
        //füllt ein deck mit zufällig generierten karten
        if(currentPlayer == 1){
            for (int i = 0; i < 7; i++) {
                int rndNum = (int) Math.floor(Math.random() * (9 + 1));
                int rndColor = (int) Math.floor(Math.random() * (3 + 1));
                cardsPlayer1.add(new Card(colors[rndColor], rndNum));

            }
        }else{
            for (int i = 0; i < 7; i++) {
                int rndNum = (int) Math.floor(Math.random() * (9 + 1));
                int rndColor = (int) Math.floor(Math.random() * (3 + 1));
                cardsPlayer2.add(new Card(colors[rndColor], rndNum));
            }
        }
    }


    public void changePlayer() {
        currentPlayer = currentPlayer * -1;
    }

    private void fillGridPane() {
        if(currentPlayer == 1){
            for (int i = 0; i < cardsPlayer1.size(); i++) {
                kartenPlayer1.add(cardsPlayer1.get(i).getButton(), i, 0);
            }
        }else{
            for (int i = 0; i < cardsPlayer2.size(); i++) {
                kartenPlayer2.add(cardsPlayer2.get(i).getButton(), i, 0);
            }
        }
    }

    public boolean noCardsleft(){
        if(currentPlayer == 1){
            if(cardsPlayer1.size() == 0){
                return true;
            }else{
                return false;
            }
        }else{
            if(cardsPlayer2.size() == 0){
                return true;
            }else{
                return false;
            }
        }
    }

    @FXML
    public void handleButtonClicked(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

       if (canThrowCard(clickedButton)){
           stack.getChildren().add(clickedButton);
           System.out.println(stack.getChildren());

           currentCard = clickedButton.getText();

           // Den Button aus dem GridPane entfernen

           if(currentPlayer == 1){
               kartenPlayer1.getChildren().remove(clickedButton);

               cardsPlayer1 = new ArrayList<>();
               for (Node node : kartenPlayer1.getChildren()) {
                   String[] attribs = ((Button) node).getText().split(" ");
                   cardsPlayer1.add(new Card(attribs[0], Integer.parseInt(attribs[1])));
               }
               if (noCardsleft()) {
                   try {
                       switchSceneWinner(event);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
               System.out.println(cardsPlayer1);
           }else{
               kartenPlayer2.getChildren().remove(clickedButton);

               cardsPlayer2 = new ArrayList<>();
               for (Node node : kartenPlayer2.getChildren()) {
                   String[] attribs = ((Button) node).getText().split(" ");
                   cardsPlayer2.add(new Card(attribs[0], Integer.parseInt(attribs[1])));
               }
               if (noCardsleft()) {
                   try {
                       switchSceneWinner(event);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }

           // Den Button dem StackPane hinzufügen
           changePlayer();
           if (!noCardsleft()){
               try {
                   switchScenelookAway(event);
               } catch (Exception e) {
                   throw new RuntimeException(e);
               }
           }
       }
    }


    public void setButtonHandlers() {
        if(currentPlayer == 1){
            for (Node node : kartenPlayer1.getChildren()) {
                if (node instanceof Button) {
                    Button button = (Button) node;
                    button.setOnAction(this::handleButtonClicked);
                }
            }
        }else{
            for (Node node : kartenPlayer2.getChildren()) {
                if (node instanceof Button) {
                    Button button = (Button) node;
                    button.setOnAction(this::handleButtonClicked);
                }
            }
        }
    }
}