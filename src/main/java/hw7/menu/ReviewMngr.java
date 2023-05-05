package hw7.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ReviewMngr {
    private Student student;
    private Course course;

    private DatabaseMngr db;

    public ReviewMngr() {

        try {
            db = new DatabaseMngr();
            db.connect();
            db.createTables();
        }
        catch (ClassNotFoundException c){

        }
        catch(SQLException s){

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

    public void login(Student s){
        try {
            if (db.studentExists(s) && db.getStudent(s).getPassword().equals(s.getPassword())) {
                student = db.getStudent(s);
            } else {
                throw new NoSuchElementException();
            }
        }
        catch(SQLException sq){

        }
    }

    public void register(Student s) {
        try {
            if (!db.studentExists(s)) {
                db.addStudent(s);
                student = s;
            } else {
                throw new NoSuchElementException();
            }
        }
        catch (SQLException sq){

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
            l = db.getReviews(course);
        }catch (SQLException s){

        }
        if(!l.isEmpty())
            return l;
        else
            throw new IllegalStateException();
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

    public Student getStudent(){
        return student;
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
