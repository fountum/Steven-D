package Model;

import javax.print.DocFlavor;

public class Student {
    private String name;
    private int grade;
    private String email;
    private int bookID;

    public Student(String name, int grade, String email) {
        this.name = name;
        this.grade = grade;
        this.email = email;
        this.bookID = -1;
    }

    public Student(String name, int grade, String email, int bookID) {
        this.name = name;
        this.grade = grade;
        this.email = email;
        this.bookID = bookID;

    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public String getEmail() {
        return email;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String toString() {
        return getName();
    }
}
