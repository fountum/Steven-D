package sample;



public class Friend {
    private String name;
    private int age;
    private String gender;

    public Friend(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String toString(){
        return name;
    }

    public String getName(){
        return(name);
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
