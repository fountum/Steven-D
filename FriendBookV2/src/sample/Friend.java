package sample;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Friend {
    private String name;
    private int age;
    private String gender;

    public Friend(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;

    }

    public Friend(String name, int age, String gender, String group) throws IOException {
        this.name = name;
        this.age = age;
        this.gender = gender;

        this.toFile(group, false);

    }

    public void toFile(String group, boolean remake) throws IOException {
        if (remake == false) {
            FileWriter fw = new FileWriter(group + ".txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(name);
            bw.newLine();
            bw.write(String.valueOf(age));
            bw.newLine();
            bw.write(gender);
            bw.newLine();
            bw.write(";");
            bw.newLine();
            bw.close();
        }
        else{
            FileWriter fw = new FileWriter(group, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(name);
            bw.newLine();
            bw.write(String.valueOf(age));
            bw.newLine();
            bw.write(gender);
            bw.newLine();
            bw.write(";");
            bw.newLine();
            bw.close();
        }
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
