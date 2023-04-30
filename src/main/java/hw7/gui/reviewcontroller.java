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
public class reviewcontroller {
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
    private Button write = new Button();

    @FXML
    private Button Return = new Button();

    @FXML
    private TextField rating = new TextField();


    @FXML
    private TextField review = new TextField();

    @FXML
    protected void write(){

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
    @FXML
    protected void setReview(){

        try{
            review.isVisible(true);
            number.isVisible(true);
            String message = review.getText();
            Integer rating = Integer.parseInt(number.getText());
            Course c = new Course(department.getText(), Integer.parseInt(number.getText()));
            manager.chooseCourse(c);
            manager.rate(message, rating);
            review.clear();
            number.clear();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void getNumber(){
        try{
            Course c = new Course(department.getText(), Integer.parseInt(number.getText()));
            manager.chooseCourse(c);
            reviews.setText(manager.Average());
        }

        catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void getReview(){
        try{
            Course c = new Course(department.getText(), Integer.parseInt(number.getText()));
            manager.chooseCourse(c);
            ArrayList<String> messages= new ArrayList<>();
            for(Review r: manager.output()){
                messages.add(r.getMessage());
            }
            reviews.setText(String.valueOf(messages));
        }

        catch(IOException e){
            e.printStackTrace();
        }
    }



}
