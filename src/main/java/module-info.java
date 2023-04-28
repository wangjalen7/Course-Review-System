module GUI.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens hw7.gui to javafx.fxml;
    exports hw7.gui;
}