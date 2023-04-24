package org.example;
import java.util.List;
import java.sql.*;
import java.util.*;
public class SQLInteractor {
    public static final String STATE_DATABASE_PATH = "Database.sqlite3";

    Connection connection;

    private void isManagerConnected() {
        if (connection == null) {
            throw new IllegalStateException("Manager hasn't connected yet");
        }
    }
    private boolean doesTableExist(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet rs = meta.getTables(null, null, tableName, new String[] {"TABLE"});
        boolean returnBool = rs.next();
        rs.close();
        return returnBool;
    }
    @Override
    public void connect() throws SQLException, ClassNotFoundException {
        if (connection != null) {
            throw new IllegalStateException("Manager is already connected");
        }
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" +
                    STATE_DATABASE_PATH);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createTables() throws SQLException, IllegalStateException{
        isManagerConnected();
        if (doesTableExist(connection, "Stops")) {
            throw new IllegalStateException("the tables already exist");
        }
        try{
            String students = "CREATE TABLE STUDENT (" +
                    "id INTEGER not NULL PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR(255) NOT NULL," +
                    "password  VARCHAR(255) NOT NULL" +
                    ")";

            String courses = "CREATE TABLE COURSES (" +
                    "id INTEGER not NULL PRIMARY KEY AUTOINCREMENT," +
                    "section VARCHAR(255) NOT NULL," +
                    "number VARCHAR(255) NOT NULL" +
                    ")";
            String review = "CREATE TABLE ROUTES (" +
                    "id INTEGER not NULL PRIMARY KEY AUTOINCREMENT," +
                    "FOREIGN KEY (Student) REFERENCES STUDENTS(id) " +
                    "FOREIGN KEY (Course) REFERENCES COURSES(id) ON DELETE CASCADE,"+
                    "review VARCHAR(255) NOT NULL,"+
                    "numericRating INTEGER NOT NULL"+
                    ")";
            Statement statement = connection.createStatement();
            statement.execute(students);
            statement.execute(courses);
            statement.execute(review);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}
