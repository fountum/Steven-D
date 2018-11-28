
public class Main {
    public static void main(String[] args) {
        //Creates new a school
        School windy = new School("Windermere", "Secondary School");

        //Creating and adding 10 students to the school windy
        Student a = new Student("Alison", "Wang", 8);
        Student b = new Student("Brian", "Johnson", 9);
        Student c = new Student("Carly", "Shay", 10);
        Student d = new Student("David", "Rotini", 11);
        Student e = new Student("Erica", "Carson", 12);
        Student f = new Student("First", "Last", 11);
        Student g = new Student("Greatest", "Ever",10);
        Student h = new Student("Henry", "Lu", 9);
        Student i = new Student("Eye", "Queue", 8);
        Student j = new Student("Jackson", "Michael", 9);

        windy.addStudent(a);
        windy.addStudent(b);
        windy.addStudent(c);
        windy.addStudent(d);
        windy.addStudent(e);
        windy.addStudent(f);
        windy.addStudent(g);
        windy.addStudent(h);
        windy.addStudent(i);
        windy.addStudent(j);

        //creating and adding 3 teachers to windy
        Teacher k = new Teacher("Kevin", "Williams", "Math");
        Teacher l = new Teacher("Louis", "Last", "English");
        Teacher m = new Teacher("Matt", "Water", "Physical Education");

        windy.addTeacher(k);
        windy.addTeacher(l);
        windy.addTeacher(m);

        //showing all of the teachers and students in the school
        windy.showStudents();
        windy.showTeachers();

        //removing the last 2 students
        windy.removeStudent("Eye", "Queue");
        windy.removeStudent("Jackson", "Michael");

        //removing the last student
        windy.removeTeacher("Matt", "Water");

        //showing the updated teacher and student list
        windy.showStudents();
        windy.showTeachers();

    }
}
