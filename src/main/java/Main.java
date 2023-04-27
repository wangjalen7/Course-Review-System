import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

public class main {

    login login = new login();
    DatabaseMngr manager = new DatabaseMngr();
    Student student;
    public void main(String args[]) throws SQLException {
   menu:     while(true) {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Welcome to course program! are you a new student or " +
                    "do you already have an account?");
            System.out.println("Say n for new and e for existing");
            String choice = myObj.nextLine();
            if (choice.equals("n")) {
                System.out.println("ok, what is your user name and pw?");
                String username = myObj.nextLine();
                String password = myObj.nextLine();
                System.out.println("now confirm password");
                String pw = myObj.nextLine();
                if (password.equals(pw)) {
                    student = new Student(username, password);
                    manager.addStudent(student);

                } else {
                    System.out.println("error, try again, wrong password");
                }
            } else if (choice.equals("e")) {
                System.out.println("ok, what is your user name and pw?");
                String username = myObj.nextLine();
                String password = myObj.nextLine();
                student = new Student(username, password);
                if (manager.studentExists(student) == true) {
                    System.out.println("good, let us continue");
                } else {
                    System.out.println("either username or password is incorrect");
                }
            }
      choices:      while (true) {
                System.out.println("press 1 to exit, press 2 to select a class for reviewing, 3 for reading review,  4 for finding average numeric press");
                try {
                    Integer number = myObj.nextInt();
                    switch (number) {
                        case 1:
                            System.exit();
                            break;
                        case 2:
                            System.out.println("what is course id?");
                            String id = myObj.nextLine();
                            System.out.println("what is course number?");
                            String number = myObj.nextLine();
                            addReview(id, number, student);
                            break;
                        case 3:
                            System.out.println("what is course id?");
                            String id = myObj.nextLine();
                            System.out.println("what is course number?");
                            String number = myObj.nextLine();
                            getReviews(id, number);
                            break;
                        case 4:
                            System.out.println("what is course id?");
                            String id = myObj.nextLine();
                            System.out.println("what is course number?");
                            String number = myObj.nextLine();
                            getAverage(id, number);
                            break;
                        case 5:
                            break menu;
                        default:
                            System.out.println("wrong number");
                            break;
                    }
                    break choices;
                } catch (InputMismatchException e) {
                    System.out.println("wrong inputt");
                    break choices;
                }
            }
        }
    }
    public void checker(String choice){
        if(choice.equals("n")){

        }

    }
    public Void getReviews(String a, String b) throws  SQLException{
        Course course = new Course(a, b);
        if(manager.newCourse(course) == false){
            System.out.println("course is not in database");
        }
        else if(manager.getReviewsByCourse(course) == null){
            System.out.println("no reviews");
        }
        else{
            for(Review r: manager.getReviewsByCourse(course)){
                System.out.println(r.getMessage());
            }
        }

    }
    public Void getAverage(String a, String b){
        Course course = new Course(a, b);
        ArrayList<Review> reviews = manager.getReviewsByCourse(course);
        double x = 0;
        double y = 0;
        for(Review r: reviews){
           x+= r.getRating();
           y+=1;
        }
        System.out.println(x/y);
    }



    public void addReview(String a, String b, Student student) throws SQLException {
        Course course = new Course(a, b);
        if (manager.newCourse(course) == false) {
            manager.addCourse(course);
        }
        if (manager.reviewExists(course, student) == true {
            System.out.println("review exists");
        }
        else{
            Scanner scan = new Scanner(System.in);
            System.out.println("What do you want to Say?");
            String Review = scan.nextLine();
            System.out.println("What is your numeric review");
            int score = scan.nextInt();
            Review review = new Review(student, course, Review, score);
            manager.addReview(review);
        }
    }
}
