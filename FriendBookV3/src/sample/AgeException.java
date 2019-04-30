package sample;

public class AgeException extends Throwable {
    int age = 0;

    AgeException(int age) {
        this.age = age;
    }

    public String toString() {
        return "Invalid age";
    }
}
