public class Review {
    private Student student;
    private Course course;
    private String message;
    private int rating;

    public Review(Student s, Course c, String msg, int rate){
        student = s;
        course = c;
        message = msg;
        if( rate >= 1 && rate <= 5)
            rating = rate;
        else
            throw new IllegalArgumentException("Rating must be 1-5");
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getMessage() {
        return message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rate){
        if( rate >= 1 && rate <= 5)
            rating = rate;
        else
            throw new IllegalArgumentException("Rating must be 1-5");
    }
}
