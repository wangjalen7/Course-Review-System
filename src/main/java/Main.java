import java.sql.SQLException;
import java.util.Scanner;
public class main {

    login login = new login();
    DatabaseMngr manager = new DatabaseMngr();
    Student student;
    public void main(String args[]) throws SQLException {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Welcome to course program! are you a new student or " +
                "do you already have an account?");
        System.out.println("Say n for new and e for existing");
        String choice = myObj.nextLine();
        if (choice.equals("n")){
            System.out.println("ok, what is your user name and pw?");
            String username = myObj.nextLine();
            String password = myObj.nextLine();
            System.out.println("now confirm password");
            String pw = myObj.nextLine();
            if(password.equals(pw)){
                 student = new Student (username, password);
                manager.addStudent(student);

            }
            else{
                System.out.println("error, try again, wrong password");
            }
        }
        else if (choice.equals("e")){
            System.out.println("ok, what is your user name and pw?");
            String username = myObj.nextLine();
            String password = myObj.nextLine();
            student = new Student (username, password);
            if(manager.studentExists(student) == true ){
                System.out.println("good, let us continue");
            }
            else{
                System.out.println("either username or password is incorrect");
            }
        }

    }
    public void checker(String choice){
        if(choice.equals("n")){

        }

    }


}
