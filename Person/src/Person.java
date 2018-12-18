public class Person {
    protected String firstName;
    protected String lastName;

    public Person() {
        this.firstName = "John";
        this.lastName = "Smith";
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String speak() {
        return "Hello.";
    }

    public String toString(){
        System.out.println(speak());
        return ("Name: " + firstName + " " + lastName);
    }
}
