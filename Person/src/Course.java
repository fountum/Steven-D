public class Course {
    private String title;
    private int numofStudents;
    private int day;

    public Course(String title, int numofStudents, int day) {
        this.title = title;
        this.numofStudents = numofStudents;
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return (title + " # of Students: " + numofStudents + " Day: " + day);
    }
}
