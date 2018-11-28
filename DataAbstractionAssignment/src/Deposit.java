import java.util.Date;

public class Deposit {
    private double amount;
    private Date date;
    private String account;

    Deposit(double amount, Date date, String account){
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    //Requires: nothing
    //modifies: nothing
    //effects: prints out the deposit, the date it occurred, and what account it belongs to
    public String toString(){
        //your code here
        return ("Deposit of " + amount + " on " + date.toString() + " into account " + account);
    }
}
