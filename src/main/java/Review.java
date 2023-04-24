public class Review {
    private int student_id;
    private int course_id;
    private String message;
    private int rating;

    public Review(int sid, int cid, String msg, int rate){
        student_id = sid;
        course_id = cid;
        message = msg;
        if( rate >= 1 && rate <= 5)
            rating = rate;
        else
            throw new IllegalArgumentException("Rating must be 1-5");
    }

    public int getStudent_id() {
        return student_id;
    }

    public int getCourse_id() {
        return course_id;
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
