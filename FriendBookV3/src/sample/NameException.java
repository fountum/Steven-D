package sample;

public class NameException extends Throwable{
    String name = "";

    NameException(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        if (!name.replaceAll("\\s", "").matches("[a-zA-Z]+")){
            return ("Letters and spaces only");
        }
        if (name.length() < 3){
            return ("Name too short. Minimum 4 letters");
        }


        if (name.split(" ").length < 2){
            return ("Needs first and last name");
        }
        return "Invalid name";
    }
}
