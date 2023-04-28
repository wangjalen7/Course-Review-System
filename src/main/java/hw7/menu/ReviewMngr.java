package hw7.menu;

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
        if(db.first(student,course)) {
            try {
                db.addReview(new Review(student, course, msg, rating));
                if (db.newCourse(course))
                    db.addCourse(course);
                if (!db.studentExists(student))
                    db.addStudent(student);
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
        else{
            throw new NoSuchElementException();
        }
    }

    public void login(Student s) throws SQLException{
            if (db.studentExists(s) && db.getStudent(s).getPassword().equals(s.getPassword())) {
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
            else{
                throw new NoSuchElementException();
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
            return db.getReviews(course);
        }catch (SQLException s){

        }
        return l;
    }

    public double Average (){
        int sum = 0;
        double total = 0;
        List<Review> l = output();
        for(Review r: l){
            sum += r.getRating();
            total++;
        }
        return sum/total;
    }

    public void logout(){
        student = null;

    }

    public void exit(){
        try {
            db.disconnect();
        }
        catch (SQLException e){

        }
    }


}
