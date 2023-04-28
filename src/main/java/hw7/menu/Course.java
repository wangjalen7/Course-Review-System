package hw7.menu;

public class Course {
    private String department;
    private int number;

    public Course(String d, int n){
        department = d;
        number = n;
    }

    public int getNumber() {
        return number;
    }

    public String getDepartment() {
        return department;
    }
}
