import javax.security.sasl.SaslClient;
import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int accountNumber;
    private ArrayList<Deposit> deposits;
    private ArrayList<Withdraw> withdraws;
    private double checkBalance;
    private double savingBalance;
    private double savingRate;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    private final int OVERDRAFT = -100;

    Customer(){
        //create default constructor
        this.name = "Jane Smith";
        this.accountNumber = 0;
        this.checkBalance = 150;
        this.savingBalance = 150;
        this.deposits = new ArrayList<>();
        this.withdraws = new ArrayList<>();

    }
    Customer(String name, int accountNumber, double checkDeposit, double savingDeposit){
        //constructor code here
        this.name = name;
        this.accountNumber = accountNumber;
        this.checkBalance = checkDeposit;
        this.savingBalance = savingDeposit;
        this.deposits = new ArrayList<>();
        this.withdraws = new ArrayList<>();

    }
    //Requires: double, date, String
    //Modifies: this
    //Effects: creates a new deposit object and adds it to the checkBalance/savingBalance and the ArrayList deposits. Cannot deposit 0 or negative values
    public double deposit(double amt, Date date, String account){
        //your code here
        if (amt > 0) {
            deposits.add(new Deposit(amt, date, account));
            if (account == CHECKING) {
                checkBalance += amt;
                return checkBalance;
            } else {
                savingBalance += amt;
                return savingBalance;
            }
        }
        else{
            return -101;
        }
    }
    //Requires: double, date, String
    //Modifies: this
    //Effects: creates a new withdraw object and subtracts it from the checkBalance/savingBalance and the ArrayList withdraw. Cannot withdraw 0 or negative values.
    public double withdraw(double amt, Date date, String account){
        //your code here
        if (amt > 0 && !checkOverdraft(amt, account)) {
            withdraws.add(new Withdraw(amt, date, account));
            if (account == CHECKING) {
                checkBalance -= amt;
                return checkBalance;
            } else {
                savingBalance -= amt;
                return savingBalance;
            }
        }
        else{
            return -101;
        }
    }
    //Requires: double, String
    //Modifies: nothing
    //Effects: checks if the user's withdraw goes below -100, returns true if so
    private boolean checkOverdraft(double amt, String account){
        //your code here
        if (account == CHECKING){
            if (checkBalance - amt < OVERDRAFT){
                return true;
            }
        }
        else if (account == SAVING){
            if (savingBalance - amt < OVERDRAFT){
                return true;
            }
        }
        return false;
    }
    //do not modify
    public void displayDeposits(){
        for(Deposit d : deposits){
            System.out.println(d);
        }
    }
    //do not modify
    public void displayWithdraws(){
        for(Withdraw w : withdraws){
            System.out.println(w);
        }
    }

}
