package hw7.gui;
import hw7.menu.Review;
import hw7.menu.ReviewMngr;
import hw7.menu.Course;
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
import java.util.ArrayList;
import java.util.NoSuchElementException;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.security.Key;
public class reviewcontroller extends menucontroller{
    public Label ratinglabel;
    public Label commentlabel;
    public TextField comment;
    public Button Submit;
    public Label feedback2;
    ReviewMngr manager = new ReviewMngr();
    @FXML
    private Button read = new Button();
    @FXML
    private Label reviews = new Label();
    @FXML
    private TextField department = new TextField();
    @FXML
    private TextField number = new TextField();
    @FXML
    private Button average = new Button();
    @FXML
    private Button returns = new Button();

    @FXML
    private TextField rating = new TextField();
    @FXML
    private TextField review = new TextField();

    @FXML
    protected void write(){
        ratinglabel.setVisible(true);
        rating.setVisible(true);
        commentlabel.setVisible(true);
        comment.setVisible(true);
        Submit.setVisible(true);
    }

    public void submitreview() {
        Course course = new Course(department.getText(), Integer.parseInt(number.getText()));
        String rate = rating.getText();
        String message = comment.getText();
        manager.chooseCourse(course);
        try {
            manager.rate(message, Integer.parseInt(rate));
            feedback2.setText("Done!");
            feedback2.setVisible(true);
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
            feedback2.setText("You have already written a review for this course");
            feedback2.setVisible(true);
        }
        department.clear();
        number.clear();
        rating.clear();
        comment.clear();
        ratinglabel.setVisible(false);
        rating.setVisible(false);
        commentlabel.setVisible(false);
        comment.setVisible(false);
        Submit.setVisible(false);
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

    @FXML
    protected void average(){
        Course c = new Course(department.getText(), Integer.parseInt(number.getText()));
        manager.chooseCourse(c);
        reviews.setText(Double.toString( manager.Average()));
    }
    @FXML
    protected void read(){
        Course c = new Course(department.getText(), Integer.parseInt(number.getText()));
        manager.chooseCourse(c);
        ArrayList<String> messages= new ArrayList<>();
        for(Review r: manager.output()){
            messages.add(r.getMessage());
        }
        reviews.setText(String.valueOf(messages));
    }



}
