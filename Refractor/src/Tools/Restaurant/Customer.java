package Tools.Restaurant;

public class Customer {
    private String name;
    private String street;
    private String postalCode;
    private int houseNum;

    public Customer(String name, String street, String postalCode, int houseNum ){
        this.name = name;
        this.street = street;
        this.postalCode = postalCode;
        this.houseNum = houseNum;
    }

    public Customer() {
        this.name = "Jonathan Z.";
        this.street = "ABC st.";
        this.postalCode = "A1B 2C3";
        this.houseNum = 1234;
    }

    public String toString() {
        return (name + " Address: "+ houseNum + " " + street + " Postal Code: " + postalCode );
    }

    public String getAddress(){
        return (houseNum + " " + street);
    }

    public String getName(){
        return (name);
    }



}
