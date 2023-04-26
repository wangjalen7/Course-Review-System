import java.util.List;
import java.sql.*;
import java.util.*;
public class DatabaseMngr {
    public static final String STATE_DATABASE_PATH = "Reviews.sqlite3";

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

    public void disconnect() throws SQLException {
        connection.close();
    }

    public void createTables() throws SQLException, IllegalStateException{
        isManagerConnected();
        if (doesTableExist(connection, "STOPS")) {
            throw new IllegalStateException("the tables already exist");
        }
        try{
            String students = "CREATE TABLE STUDENTS (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(255) NOT NULL," +
                    "password VARCHAR(255) NOT NULL)";

            String courses = "CREATE TABLE COURSES (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "department VARCHAR(255) NOT NULL," +
                    "Catalog_Number int NOT NULL)";
            String reviews = "CREATE TABLE REVIEWS (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "student_name VARCHAR(255) NOT NULL," +
                    "course_number int NOT NULL," +
                    "message VARCHAR(255) NOT NULL," +
                    "rating int NOT NULL," +
                    "FOREIGN KEY (student_name) REFERENCES STUDENTS(name) ON DELETE CASCADE," +
                    "FOREIGN KEY (course_number) REFERENCES COURSES(Catalog_Number) ON DELETE CASCADE" +
                    ")";
            Statement statement = connection.createStatement();
            statement.execute(students);
            statement.execute(courses);
            statement.execute(reviews);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    public void clear() throws SQLException{
        isManagerConnected();
        Statement statement = connection.createStatement();
        if (doesTableExist(connection, "STUDENTS")) {
            try {
                String delete = "DELETE FROM STUDENTS";
                statement.execute(delete);
            } catch (SQLException e) {
                // Print the error message to understand the issue better
                e.printStackTrace();
                System.out.println("");
            }
        }

        if (doesTableExist(connection, "COURSES")) {
            try {
                String delete = "DELETE FROM COURSES";
                statement.execute(delete);
            } catch (SQLException e) {
                // Print the error message to understand the issue better
                e.printStackTrace();
                System.out.println("");
            }
        }
        if (doesTableExist(connection, "REVIEWS")) {
            try {
                String delete = "DELETE FROM REVIEWS";
                statement.execute(delete);
            } catch (SQLException e) {
                // Print the error message to understand the issue better
                e.printStackTrace();
                System.out.println("");
            }
        }
        else if(!doesTableExist(connection, "COURSES") && !doesTableExist(connection, "STUDENTS") && !doesTableExist(connection, "REVIEWS")) {
            throw new IllegalStateException("The Tables don't exist");
        }

    }
    public void delete() throws SQLException{
        isManagerConnected();
        Statement statement = connection.createStatement();
        if (doesTableExist(connection, "STUDENTS")) {
            try {
                String delete = "DROP TABLE IF EXISTS STUDENTS";
                statement.execute(delete);
            } catch (SQLException e) {
                // Print the error message to understand the issue better
                e.printStackTrace();
                System.out.println("");
            }
        }

        if (doesTableExist(connection, "COURSES")) {
            try {
                String delete = "DROP TABLE IF EXISTS COURSES";
                statement.execute(delete);
            } catch (SQLException e) {
                // Print the error message to understand the issue better
                e.printStackTrace();
                System.out.println("");
            }
        }
        if (doesTableExist(connection, "REVIEWS")) {
            try {
                String delete = "DROP TABLE IF EXISTS REVIEWS";
                statement.execute(delete);
            } catch (SQLException e) {
                // Print the error message to understand the issue better
                e.printStackTrace();
                System.out.println("");
            }
        }
        else if(!doesTableExist(connection, "COURSES") && !doesTableExist(connection, "STUDENTS") && !doesTableExist(connection, "REVIEWS")) {
            throw new IllegalStateException("The Tables don't exist");
        }

    }

    public void addStudent(Student s) throws SQLException{
        isManagerConnected();
        if(!doesTableExist(connection,"STUDENTS")){
            throw new IllegalStateException("Students table doesn't exist");
        }
        try{
            Statement statement = connection.createStatement();
            String ex = "INSERT INTO STUDENTS VALUES " ;
            ex += "( NULL,'" + s.getUser() + "', '" + s.getPassword() + "')";
            statement.execute(ex);
        }
        catch(SQLException sq){
            sq.printStackTrace();
        }

    }

    public void addCourse(Course c) throws SQLException{
        isManagerConnected();
        if(!doesTableExist(connection,"COURSES")){
            throw new IllegalStateException("Courses table doesn't exist");
        }
        try{
            Statement statement = connection.createStatement();
            String ex = "INSERT INTO COURSES VALUES " ;
            ex += "( NULL,'" + c.getDepartment() + "', " + c.getNumber() + ")";
            statement.execute(ex);
        }
        catch(SQLException sq){
            sq.printStackTrace();
        }
    }

    public void addReview(Review r) throws SQLException{
        isManagerConnected();
        if(!doesTableExist(connection,"REVIEWS")){
            throw new IllegalStateException("Reviews table doesn't exist");
        }
        try{
            Statement statement = connection.createStatement();
            String ex = "INSERT INTO REVIEWS VALUES " ;
            ex += "( NULL,'" + r.getStudent().getUser() + "', " + r.getCourse().getNumber() + ", '" + r.getMessage() + "', " + r.getRating() + ")";
            statement.execute(ex);
        }
        catch(SQLException sq){
            sq.printStackTrace();
        }
    }

    public boolean studentExists(Student s) throws SQLException{
        isManagerConnected();
        Boolean exists = false;
        if(!doesTableExist(connection,"STUDENTS")){
            throw new IllegalStateException("Students table doesn't exist");
        }
        try{
            Statement statement = connection.createStatement();
            ResultSet rs  = statement.executeQuery("SELECT * FROM STUDENTS WHERE name = '" + s.getUser() + "'");
            exists = rs.next();
        }
        catch(SQLException sq){
            sq.printStackTrace();
        }
        return !exists;
    }
    public boolean reviewExists(Review r, Student s) throws SQLException{
        isManagerConnected();
        Boolean exists = false;
        if(!doesTableExist(connection,"REVIEWS")){
            throw new IllegalStateException("Students table doesn't exist");
        }
        try{
            Statement statement = connection.createStatement();
            ResultSet rs  = statement.executeQuery("SELECT * FROM REVIEWS WHERE coursenumber =" + r.getCourse() + "  AND   student_name = " +
                    "studentnumber = "+ s);
            exists = rs.next();
        }
        catch(SQLException sq){
            sq.printStackTrace();
        }
        return !exists;
    }
    public Review getReview(Course course){
        isManagerConnected();
        if (!doesTableExist(connection, "REVIEWS")) {
            throw new IllegalStateException("Reviews table doesn't exist");
        }
        try{
        String command = "SELECT * FROM REVIEWS WHERE courseNumber = " + course;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(command);
        if(rs.next()){
            Integer id = rs.getInt("id");
            String name = rs.getString("student_name");
            Integer course = rs.getInt("course_number");
            String message = rs.getString("message");
            Integer rating = rs.getInt("rating");
            Review review = new Review(name, course, message, rating);
            return review;
        }
    } catch (SQLException e) {
        throw new IllegalStateException(e);
    }

    public boolean newCourse(Course c) throws SQLException{
        isManagerConnected();
        Boolean exists = false;
        if(!doesTableExist(connection,"COURSES")){
            throw new IllegalStateException("Courses table doesn't exist");
        }
        try{
            Statement statement = connection.createStatement();
            ResultSet rs  = statement.executeQuery("SELECT * FROM STUDENTS WHERE id = " + c.getNumber());
            exists = rs.next();
        }
        catch(SQLException sq){
            sq.printStackTrace();
        }
        return !exists;
    }

    public Student getStudent(Student s) throws SQLException{
        isManagerConnected();
        Student new_s = new Student();
        Boolean exists = false;
        if(!doesTableExist(connection,"STUDENTS")){
            throw new IllegalStateException("Students table doesn't exist");
        }
        try{
            Statement statement = connection.createStatement();
            ResultSet rs  = statement.executeQuery("SELECT * FROM STUDENTS WHERE name = '" + s.getUser() + "'");
            new_s = new Student(rs.getString(0),rs.getString(1));
        }
        catch(SQLException sq){
            sq.printStackTrace();
        }
        return new_s;
    }

}
