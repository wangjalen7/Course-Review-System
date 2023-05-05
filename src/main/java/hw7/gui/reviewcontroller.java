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
    @FXML
    private Button read = new Button();

    @FXML
    private TextField number = new TextField();

    public TextField department = new TextField();

    public Label feedback = new Label();

    public Label user = new Label();

    @FXML
    protected void initialize(){
        user.setText("User: "+menucontroller.manager.getStudent().getUser());
    }

    @FXML
    protected void write(){
        try {
            menucontroller.manager.chooseCourse(new Course(department.getText().trim().toUpperCase(),Integer.parseInt(number.getText().trim())));
            FXMLLoader root =  new FXMLLoader(getClass().getResource("write.fxml"));

            Scene scene = new Scene(root.load(), Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()-20);

            Stage stage = (Stage) read.getScene().getWindow();

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (IllegalStateException is){
            feedback.setText("Enter Valid Course Please");
            feedback.setVisible(true);
        }


    }

    @FXML
    protected void read(){
        try {
            menucontroller.manager.chooseCourse(new Course(department.getText().trim().toUpperCase(),Integer.parseInt(number.getText().trim())));
            FXMLLoader root =  new FXMLLoader(getClass().getResource("read.fxml"));

            Scene scene = new Scene(root.load(), Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()-20);

            Stage stage = (Stage) read.getScene().getWindow();

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (IllegalStateException is){
            feedback.setText("Enter Valid Course Please");
            feedback.setVisible(true);
        }
    }

    @FXML
    protected void returns(){
        try {
            FXMLLoader root =  new FXMLLoader(getClass().getResource("menu.fxml"));

            Scene scene = new Scene(root.load(), Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()-20);

            Stage stage = (Stage) read.getScene().getWindow();

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
