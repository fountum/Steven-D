package sample;

public class GenderException extends Throwable {
    String gender;

    GenderException(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Gender cannot be empty";
    }
}
