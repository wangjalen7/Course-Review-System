package hw7.menu;

public class Course {
    private String department;
    private int number;

    public Course(String d, int n) throws IllegalStateException{
        if(d.length() <= 4 && d.length() > 0 && String.valueOf(n).length() == 4 && d.equals(d.toUpperCase()) && d.chars().allMatch(Character::isLetter)) {
            department = d;
            number = n;
        }
        else{
            throw new IllegalStateException();
        }
    }

    public int getNumber() {
        return number;
    }

    public String getDepartment() {
        return department;
    }
}
