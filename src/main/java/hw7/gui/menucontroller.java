package hw7.gui;

import hw7.menu.ReviewMngr;
import hw7.menu.Student;
import javafx.fxml.FXMLLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.lang.Exception;
import java.util.NoSuchElementException;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.security.Key;


public class menucontroller {

    public static ReviewMngr manager = new ReviewMngr();
    @FXML
    public Button login = new Button();

    public Button register = new Button();

    public TextField usernameField = new TextField();

    public PasswordField passwordField = new PasswordField();
    public PasswordField confirm = new PasswordField();

    public Label feedback = new Label();

    @FXML
    protected void login(){

        Student student = new Student(usernameField.getText(),passwordField.getText());
        try{
            manager.login(student);
            feedback.setText("Logged In!");
            feedback.setVisible(true);
            switchToMainScreen();
        }
        catch(NoSuchElementException e){
            feedback.setText("Incorrect username or password");
            feedback.setVisible(true);
        }
    }

    @FXML
    protected void register() {
        if(passwordField.getText().equals(confirm.getText())) {
            Student student = new Student(usernameField.getText(), passwordField.getText());
            try {
                manager.register(student);
                feedback.setText("Registered and Logged In!");
                feedback.setVisible(true);
                switchToMainScreen();
            } catch (NoSuchElementException e) {
                feedback.setText("Username must be unique");
                feedback.setVisible(true);
            }
        }
        else{
            feedback.setText("Passwords Must Match");
            feedback.setVisible(true);
        }
    }


    private void switchToMainScreen(){
        try {
            // Load the main FXML file
            FXMLLoader root =  new FXMLLoader(getClass().getResource("reviews.fxml"));

            // Create a new Scene with the main FXML file
            Scene scene = new Scene(root.load(), Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()-20);

            // Get the current stage
            Stage stage = (Stage) usernameField.getScene().getWindow();

            // Set the new Scene on the stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void exit(){
        Platform.exit();
    }
}
