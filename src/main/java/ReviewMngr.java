import java.sql.SQLException;
import java.util.NoSuchElementException;

public class ReviewMngr {
    private Student student;
    private Course course;

    private Review review;

    private DatabaseMngr db;

    public void rate(){
        db= new DatabaseMngr();
        Student e = new Student("vya9tu","pa$$word");
        Course c = new Course("CS",3140);
        Review r = new Review(e,c,"great course!!!!",5);
        try {
            db.connect();
            db.createTables();
            db.addCourse(c);
            db.addReview(r);
            boolean ere = db.newStudent(e);
            db.delete();
            db.disconnect();
        }
        catch(SQLException  s){
            s.printStackTrace();
        }
        catch(ClassNotFoundException cn){
            cn.printStackTrace();
        }
    }

    public void login(Student s) throws SQLException{
        try {
            db.connect();
            if (!db.newStudent(s)) {
                student = db.getStudent(s);
            } else {
                throw new NoSuchElementException();
            }
            db.disconnect();
        }
        catch(ClassNotFoundException c){

        }
    }

    public void register(Student s) throws SQLException{
        if(db.newStudent(s)){
            db.addStudent(s);
            student = s;
        }
    }



}
