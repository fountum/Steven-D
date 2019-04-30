package Model;


public class MyExceptions extends Throwable {
    Book book = null;
    Student student = null;
    boolean taken = false;

    public MyExceptions(Student s) {
        student = s;
    }

    public MyExceptions(Boolean taken) {
        this.taken = taken;
    }

    public MyExceptions(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        if (taken == true) {
            return ("Book is already taken out");
        } else if (student != null) {
            if (student.getName().equals("")) {
                return ("Name can't be blank");
            } else if (student.getGrade() < 1 || student.getGrade() > 12) {
                return ("Invalid grade");
            } else if (!student.getEmail().contains("@")) {
                return ("Email must contain a @");
            } else if (student.getEmail().isEmpty()) {
                return ("Email can't be blank");
            }
        } else if (book != null) {
            if (book.getTitle().isEmpty()) {
                return ("Title can't be blank");
            } else if (book.getAuthor().isEmpty()) {
                return ("Author can't be blank");
            }
        }
        return ("");
    }
}
