package application.guessingame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller {

    private Integer guess;
    @FXML
    private TextField guessInput, upperLimit;
    @FXML
    private Button guessButton, playButton;
    @FXML
    private Label error, error1;
    ObservableList<String> init = FXCollections.observableArrayList("Guess:");
    @FXML
    private ListView<String> guessesList = new ListView<>(init);
    @FXML
    private ProgressBar progress;
    @FXML
    private void playGame(ActionEvent event) throws IOException {

        Stage stage;
        Parent root;
        int lim = 100;

        try{
            lim = Integer.parseInt(upperLimit.getText());
            error1.setText("");
        } catch (NumberFormatException | NullPointerException exception){
            error1.setText("Input a number");
        } catch (Exception exception){
            System.out.println(exception);
        }

        Rand.setRand(lim);
        stage = (Stage) playButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onEnter(ActionEvent ae){
        compute(ae);
    }

    public void enter(ActionEvent ae) throws IOException {
        playGame(ae);
    }
    @FXML
    public void compute(ActionEvent e){
        boolean flag = false;

        try{
            guess = Integer.parseInt(guessInput.getText());
            error.setText("");
            flag = false;

        } catch (NumberFormatException | NullPointerException exception){
            error.setText("Enter a number");
            flag = true;
        } catch (Exception exception){
            System.out.println(exception);
        }

        guessInput.setText("");

        if (guess <= Rand.getLim() && guess >= 0) {
            progress.setProgress(1.-(Math.abs((Rand.getRand()-guess)/(double)Rand.getLim())));
        } else {
            error.setText("input outside bounds");
            flag = true;
        }

        if (guess == Rand.getRand()){
            guessButton.setText("YAY!");
            guessesList.getItems().add(String.valueOf(guess)+ " -RESULT");
            guessButton.setDisable(true);
            guessInput.setDisable(true);
            guessesList.setDisable(true);
            progress.setStyle("-fx-accent: green");
        } else if (!flag && guess < Rand.getRand()) {
            guessesList.getItems().add(String.valueOf(guess)+ " -LOW");
            guessButton.setText("LOW");
            progress.setStyle("-fx-accent: red");
        } else if (!flag && guess > Rand.getRand()){
            guessesList.getItems().add(String.valueOf(guess)+ " -HIGH");
            guessButton.setText("HIGH");
            progress.setStyle("-fx-accent: yellow");
        }

    }

}
