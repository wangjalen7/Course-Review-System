package hw7.gui;
import hw7.menu.Course;
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
public class reviewcontroller {

    public Label ratinglabel;
    public TextField rating;
    public Label commentlabel;
    public TextField comment;
    public Button Submit;
    public TextField department;

    public PasswordField number;
    ReviewMngr manager = new ReviewMngr();
    @FXML
    private Button read = new Button();
    public TextField usernameField = new TextField();
    public TextField passwordField = new PasswordField();
    public Label feedback = new Label();

    @FXML
    protected void write(){
//        System.out.println("hw7.menu.Course:");
//        myObj.nextLine();
//        String core = myObj.nextLine();
//        String d = core.substring(0,core.length()-4);
//        String num = core.substring(core.length()-4);
//        manager.chooseCourse(new Course(d,Integer.parseInt(num)));
//        System.out.println("Enter rating(1-5):");
//        String rate = myObj.nextLine();
//        System.out.println("Add comment:");
//        String msg = myObj.nextLine();
//        try {
//            manager.rate(msg, Integer.parseInt(rate));
//            System.out.println("Done!");
//        }
//        catch (NoSuchElementException e) {
//            System.out.println("You have already written a review for this hw7.menu.Course");
//        }
        ratinglabel.setVisible(true);
        rating.setVisible(true);
        commentlabel.setVisible(true);
        comment.setVisible(true);
        Submit.setVisible(true);
        Student student = new Student(usernameField.getText(), passwordField.getText());
        try {
            manager.register(student);
            feedback.setText("Registered and Logged In!");
            feedback.setVisible(true);
        } catch (NoSuchElementException e) {
            feedback.setText("Username must be unique");
            feedback.setVisible(true);
        }
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
