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
public class writecontroller {

public Button submit = new Button();

public TextField msg = new TextField();

public TextField rating = new TextField();

public Label feedback = new Label();

    public Label user = new Label();

    @FXML
    protected void initialize(){
        user.setText("User: "+menucontroller.manager.getStudent().getUser());
    }

    @FXML
    protected void rate(){
        try {
            menucontroller.manager.rate(msg.getText(), Integer.parseInt(rating.getText()));
            feedback.setText("Done!");
            feedback.setVisible(true);

        }
        catch (NoSuchElementException e){
            feedback.setText("You have already written a review for this course");
            feedback.setVisible(true);

        }

    }

    @FXML
    protected void exit(){
        try {
            FXMLLoader root =  new FXMLLoader(getClass().getResource("reviews.fxml"));

            Scene scene = new Scene(root.load(), Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()-20);

            Stage stage = (Stage) msg.getScene().getWindow();

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
