import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Customer pe = new Customer("asd", 1234, 400, 400);
        pe.deposit(199, new Date(), Customer.CHECKING);
        pe.deposit(50.5, new Date(), Customer.CHECKING);
        pe.deposit(1000.49, new Date(), Customer.CHECKING);

        pe.displayDeposits();
    }
}
