

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
public class DepositTest {
    Customer cus;
    @Before
    public void setup() {
        cus = new Customer();
    }
    @Test
    public void testDeposit(){
        assertEquals(550, cus.deposit(400, new Date(), cus.CHECKING ),0);
        assertEquals(1000, cus.deposit(850, new Date(), cus.SAVING), 0);

    }

    @Test
    public void testDepositNegativeValue(){
        assertEquals(-101, cus.deposit(-1999, new Date(), cus.CHECKING), 0);
        assertEquals(-101, cus.deposit(0, new Date(), cus.SAVING), 0);
    }

    @Test
    public void testWithdraw(){
        assertEquals(50, cus.withdraw(100, new Date(), cus.CHECKING), 0);
        assertEquals(-20, cus.withdraw(70, new Date(), cus.CHECKING), 0);
        assertEquals(0, cus.withdraw(150, new Date(), cus.SAVING), 0);
        assertEquals(-1, cus.withdraw(1, new Date(), cus.SAVING), 0);
    }
    @Test
    public void testWithdrawOverdraft(){
        assertEquals(0, cus.withdraw(150, new Date(), cus.CHECKING), 0);
        assertEquals(-100, cus.withdraw(100, new Date(), cus.CHECKING),0);
        assertEquals(-101, cus.withdraw(100000.23, new Date(), cus.CHECKING), 0);
        assertEquals(0, cus.withdraw(150, new Date(), cus.SAVING), 0);
        assertEquals(-100, cus.withdraw(100.00, new Date(), cus.SAVING),0);
        assertEquals(-101, cus.withdraw(100000.0, new Date(), cus.SAVING), 0);
    }

    @Test
    public void testWithdrawNegativeValue(){
        assertEquals(-101, cus.withdraw(-1.30, new Date(), cus.CHECKING), 0);
        assertEquals(-101, cus.withdraw(0, new Date(), cus.CHECKING), 0);
        assertEquals(-101, cus.withdraw(-1.12, new Date(), cus.SAVING), 0);
        assertEquals(-101, cus.withdraw(0, new Date(), cus.SAVING), 0);

    }


}
