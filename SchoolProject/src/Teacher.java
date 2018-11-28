public class Teacher {
    /*A teacher object
    Fields:
    firstName and lastName: first name and last name
    subject: subject the teacher teaches
    */
    private String firstName;
    private String lastName;
    private String subject;

    Teacher(String firstName, String lastName, String subject){
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;

    }

    //returns teacher objects as their full name and their subject
    public String toString() {
        return ("Name: " + firstName + " " + lastName + " Subject: " + subject);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (!subject.equals("") && !subject.equals(" ")) {
            this.subject = subject;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (!firstName.equals("")) {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (!lastName.equals("")) {
            this.lastName = lastName;
        }
    }
}

