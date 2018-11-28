import java.util.ArrayList;

public class School {
    /*
    School object
    Fields:
    studentList: list of all the Student objects attending the school
    teacherList: list of all the Teacher objects attending the school
    name: name of the school
    type: what kind of school it is, ex. secondary school, university, middle school
    population: number of students attending the school
     */
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Teacher> teacherList = new ArrayList<>();
    private String name;
    private String type;
    private int population;

    School(String name, String type){
        this.name = name;
        this.type = type;
        population = 0;
    }

    //returns School object as the School's name, type, and population
    public String toString() {
        return ("Name: " + name + " Type: " + type + " Population: " + population);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.equals("")) {
            this.name = name;
        }
        else{
            System.out.println("Invalid name");
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!type.equals("")) {
            this.type = type;
        }
        else{
            System.out.println("Invalid school type");
        }
    }

    //population has no setter because it is based on the studentList
    public int getPopulation() {
        return population;
    }

    //Adds a Student object to the School
    public void addStudent(Student Student){
        studentList.add(Student);
        population++;
    }

    //Removes a student object from the school
    public void removeStudent(String firstName, String lastName){
        for (int i = 0; i < studentList.size(); i++){
            if (studentList.get(i).getFirstName().equals(firstName) && studentList.get(i).getLastName().equals(lastName)){
                studentList.remove(i);
                System.out.println("Student removed.");
                population--;
            }
        }

    }

    //prints all Student objects in the School
    public void showStudents(){
        for (int i = 0; i <studentList.size(); i++){
            System.out.println (studentList.get(i));
        }
    }

    //adds Teacher objects to the school
    public void addTeacher(Teacher Teacher){
        teacherList.add(Teacher);
    }

    //Removes teacher objects from the School
    public void removeTeacher(String firstName, String lastName){
        for (int i = 0; i < teacherList.size(); i++){
            if (teacherList.get(i).getFirstName().equals(firstName) && teacherList.get(i).getLastName().equals(lastName)){
                teacherList.remove(i);
                System.out.println("Teacher removed.");
            }
        }
    }

    //prints all Teacher objects in the school
    public void showTeachers(){
        for (int i = 0; i < teacherList.size(); i++){
            System.out.println (teacherList.get(i));
        }
    }



}
