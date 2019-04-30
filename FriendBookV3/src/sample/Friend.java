package sample;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Friend implements Comparable<Friend>{
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

        this.toFile(group);

    }

    public void toFile(String group) throws IOException {
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

    @Override
    public int compareTo(Friend o) {
        if (age > o.age){
            return 1;
        } else if (age < o.age) {
            return -1;
        } else{
            return 0;
        }
    }
}
