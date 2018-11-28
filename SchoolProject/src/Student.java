public class Student {
    /*
    Student object
    Fields:
    firstName and lastName: the student's first and last name
    grade: what grade the student is in
    id: the unique ID given to the student
     */
    private String firstName;
    private String lastName;
    private int grade;
    private int id;
    private static int studentNum = 0;

    Student(String firstName, String lastName, int grade){
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.id = studentNum;
        studentNum++;
    }

    //returns student object as their name and grade
    public String toString() {
        return ("Name: " + firstName + " " + lastName + " Grade: " + grade);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (!firstName.equals("")) {
            this.firstName = firstName;
        }
        else{
            System.out.println("Invalid name");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (!lastName.equals("")) {
            this.lastName = lastName;
        }
        else{
            System.out.println("Invalid name");
        }
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        if (grade > 0 && grade < 13) {
            this.grade = grade;
        }
        else{
            System.out.println("Invalid grade");
        }
    }

    public int getId() {
        return id;
    }
}
