package hw7.gui;
import hw7.menu.Course;
import hw7.menu.ReviewMngr;
import hw7.menu.Student;
import hw7.menu.Review;
import javafx.animation.PauseTransition;
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
import java.util.List;
import java.util.NoSuchElementException;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.security.Key;
public class readcontroller {

    public Button exit = new Button();

    public VBox reviews = new VBox();

    public Label avg = new Label();

    public GridPane root = new GridPane();

    public Label user = new Label();

    @FXML
    protected void initialize(){
        try {
            user.setText("User: "+menucontroller.manager.getStudent().getUser());
            List<Review> list = menucontroller.manager.output();
            int i = 1;
            avg.setText("Average Rating: " + menucontroller.manager.Average());
            for (Review r : list) {
                Label l = new Label(i + ": " + r.getCourse().getDepartment().trim().toUpperCase() + " " + r.getCourse().getNumber()
                        + " | " + r.getRating() + " | " + r.getMessage());
                reviews.getChildren().add(l);
                i++;
            }
        }
        catch(IllegalStateException s) {
            avg.setText("This Course has no reviews, rerouting......");
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.runLater(this::exit));
            delay.play();
        }

    }

    @FXML
    protected void exit(){
        try {
            FXMLLoader rot =  new FXMLLoader(getClass().getResource("reviews.fxml"));

            Scene scene = new Scene(rot.load(), Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()-20);

            Stage stage = (Stage) root.getScene().getWindow();

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
