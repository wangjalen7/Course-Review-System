package hw7.gui;

import hw7.menu.ReviewMngr;
import hw7.menu.Student;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.lang.Exception;
import java.util.NoSuchElementException;

public class menucontroller {

    ReviewMngr manager;
    @FXML
    public Button login = new Button();

    public Button register = new Button();

    @FXML
    protected void login(){
        if (password.equals(pw)) {
            Student student = new Student(username, password);
            try {
                manager.register(student);
                System.out.println("Registered and Logged In!");
            }
            catch(NoSuchElementException e){
                System.out.println("Username must be unique");
            }
        } else {
            System.out.println("error, try again, wrong password");
        }
    }
}
