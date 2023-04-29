package hw7.gui;
import hw7.menu.Course;
import hw7.menu.Review;
import hw7.menu.ReviewMngr;
import hw7.menu.Student;
import javafx.fxml.FXMLLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
public class reviewcontroller {

    public Label ratinglabel;
    public TextField rating;
    public Label commentlabel;
    public TextField comment;
    public Button Submit;
    public TextField department;

    public TextField number;
    ReviewMngr manager = new ReviewMngr();
    @FXML
    private Button read = new Button();
//    public TextField usernameField = new TextField();
//    public TextField passwordField = new PasswordField();
    public Label feedback = new Label();

    @FXML
    protected void write(){
        ratinglabel.setVisible(true);
        rating.setVisible(true);
        commentlabel.setVisible(true);
        comment.setVisible(true);
        Submit.setVisible(true);
    }
    public void submitreview() {
        String d = department.getText();
        String num = number.getText();
        manager.chooseCourse(new Course(d,Integer.parseInt(num)));
        String rate = rating.getText();
        String message = comment.getText();
        try {
            manager.rate(message, Integer.parseInt(rate));
            feedback.setText("Done!");
            feedback.setVisible(true);
        }
        catch (NoSuchElementException e) {
            feedback.setText("You have already written a review for this course");
            feedback.setVisible(true);
        }
        rating.clear();
        comment.clear();
        ratinglabel.setVisible(false);
        rating.setVisible(false);
        commentlabel.setVisible(false);
        comment.setVisible(false);
        Submit.setVisible(false);
    }
    @FXML
    protected void read(){

    }

    @FXML
    protected void returns(){
        try {
            // Load the main FXML file
            FXMLLoader root =  new FXMLLoader(getClass().getResource("menu.fxml"));

            // Create a new Scene with the main FXML file
            Scene scene = new Scene(root.load(), Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()-20);

            // Get the current stage
            Stage stage = (Stage) read.getScene().getWindow();

            // Set the new Scene on the stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
