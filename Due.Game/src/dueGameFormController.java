import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.util.Duration;

public class dueGameFormController {


    private Stage stage;
    private Scene scene;
    private Parent root;

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
        root = FXMLLoader.load(getClass().getResource("layoutPlayers.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
