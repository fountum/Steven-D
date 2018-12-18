public class Teacher extends Person {
    private static int teacherids = 1;
    private int id;
    Course[] courses = new Course[8];

    Teacher(){
        this.id = teacherids;
        teacherids++;
    }

    Teacher(String firstName, String lastName){
        super(firstName, lastName);
        this.id = teacherids;
        teacherids++;
    }

    public int getId() {
        return id;
    }

    public void getCourses() {
        int i = 0;
        for (Course c : courses) {
            if (c != null){
                System.out.println(i + ". " + c.toString());
                i++;
            }
        }
    }

    public void addCourse(Course course){
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null){
                courses[i] = course;
                break;
            }
        }
    }

    public void removeCourse(String title) {
        Course[] clone = new Course[8];
        boolean passed = false;
        for (int i = 0; i < courses.length; i++) {
            System.out.println(i);
            if (courses[i] == null){
                break;
            }
            else if (courses[i].getTitle().equals(title)){
                passed = true;
            }
            else if (passed == true){
                System.out.println(courses[i]);
                clone[i - 1] = courses[i];
            }
            else{
                clone[i] = courses[i];
                System.out.println(courses[i]);
            }
        }

        for (int i = 0; i < courses.length; i++) {
            courses[i] = clone[i];
        }
    }

    public void changeCourse(String title, int numofStudents, int day, String otherClass){
        Course[] clone = new Course[8];
        for (int i = 0; i < courses.length; i++) {
            System.out.println(i);
            if (courses[i] == null){
                break;
            }
            else if (courses[i].getTitle().equals(otherClass)){
                clone[i] = new Course(title, numofStudents, day);
            }
            else{
                clone[i] = courses[i];
                System.out.println(courses[i]);
            }
        }

        for (int i = 0; i < courses.length; i++) {
            courses[i] = clone[i];
        }
    }
}
