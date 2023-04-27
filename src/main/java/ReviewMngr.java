import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ReviewMngr {
    private Student student;
    private Course course;

    private DatabaseMngr db;

    public ReviewMngr() throws SQLException{
        db = new DatabaseMngr();
        try {
            db.connect();
            db.createTables();
        }
        catch (ClassNotFoundException c){

        }
    }

    public void rate(String msg, int rating){

        try {
            db.addReview(new Review(student,course,msg,rating));
        }
        catch(SQLException  s){
            s.printStackTrace();
        }
    }

    public void login(Student s) throws SQLException{

            if (db.studentExists(s)) {
                student = db.getStudent(s);
            } else {
                throw new NoSuchElementException();
            }
    }

    public void register(Student s) throws SQLException{
            if (!db.studentExists(s)) {
                db.addStudent(s);
                student = s;
            }
    }

    public void chooseCourse(Course c) {
        try {
            if (db.newCourse(c)) {
                db.addCourse(c);
            }
            course = c;
        }
        catch (SQLException s){

        }
    }

    public List<Review> output(){
        List<Review> l = new ArrayList<>();
        try {
            return db.getReviews();
        }catch (SQLException s){

        }
        return l;
    }

    public void logout(){
        student = null;

        try {
            db.disconnect();
        }
        catch (SQLException e){

        }
    }


}
