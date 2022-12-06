module application.guessingame {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.guessingame to javafx.fxml;
    exports application.guessingame;
}