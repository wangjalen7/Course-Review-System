package hw7.menu;

public class Student {
    private String user;
    private String password;

    public Student(String u, String p){
        user = u;
        password = p;
    }

    public Student(){
        user = "";
        password = "";
    }

    public String getUser() {
        return user;
    }

    public String getPassword(){
        return password;
    }
}
