package net.app;

import net.atm.ATMCashManager;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class ATMSimulatorTest {

    @After
    public void tearDown() {
        InputOutput inputOutput = InputOutput.getInstance();
        Field field = null;
        try {
            field = InputOutput.class.getDeclaredField("instance");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        field.setAccessible(true);
        try {
            field.set(inputOutput, null);
        } catch (IllegalAccessException e) {

        }

        ATMCashManager atmCashManager = ATMCashManager.getInstance();

        try {
            field = ATMCashManager.class.getDeclaredField("instance");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        field.setAccessible(true);
        try {
            field.set(atmCashManager, null);
        } catch (IllegalAccessException e) {

        }
    }

    @Test
    public void testDeposit() {
        String input = "1 50 50 20 20 10 . 5";
        String output = "1. Deposit\r\n" +
                "2. Withdraw\r\n" +
                "3. Display Balance\r\n" +
                "4. Mini Statement\r\n" +
                "5. Exit\r\n" +
                "Select an option Enter ccy to deposit terminated by . e.g. 10 20 50 .\r\n" +
                "Accepted: 50\r\n" +
                "Accepted: 50\r\n" +
                "Accepted: 20\r\n" +
                "Accepted: 20\r\n" +
                "Accepted: 10\r\n" +
                "1. Deposit\r\n" +
                "2. Withdraw\r\n" +
                "3. Display Balance\r\n" +
                "4. Mini Statement\r\n" +
                "5. Exit\r\n" +
                "Select an option Have a good day\r\n";

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        InputOutput.getInstance(new ByteArrayInputStream(input.getBytes()), new PrintStream(outContent));

        ATMSimulator.main(new String[] {});

        assertEquals(Double.valueOf(150.0), ATMCashManager.getInstance().getCurrentBalance());
        assertEquals(output, outContent.toString());
    }

    @Test
    public void testDepositInvalidCurrency() {

        String input = "1 50 50 25 20 10 . 5";
        String output = "1. Deposit\r\n" +
                "2. Withdraw\r\n" +
                "3. Display Balance\r\n" +
                "4. Mini Statement\r\n" +
                "5. Exit\r\n" +
                "Select an option Enter ccy to deposit terminated by . e.g. 10 20 50 .\r\n" +
                "Accepted: 50\r\n" +
                "Accepted: 50\r\n" +
                "Invalid denomination 25$\r\n" +
                "Accepted: 20\r\n" +
                "Accepted: 10\r\n" +
                "1. Deposit\r\n" +
                "2. Withdraw\r\n" +
                "3. Display Balance\r\n" +
                "4. Mini Statement\r\n" +
                "5. Exit\r\n" +
                "Select an option Have a good day\r\n";

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        InputOutput.getInstance(new ByteArrayInputStream(input.getBytes()), new PrintStream(outContent));

        ATMSimulator.main(new String[] {});

        assertEquals(Double.valueOf(130.0), ATMCashManager.getInstance().getCurrentBalance());
        assertEquals(output, outContent.toString());
    }

    @Test
    public void testDisplayBalance() {

        String input = "1 50 50 25 20 10 . 3 5";
        String output = "1. Deposit\r\n" +
                "2. Withdraw\r\n" +
                "3. Display Balance\r\n" +
                "4. Mini Statement\r\n" +
                "5. Exit\r\n" +
                "Select an option Enter ccy to deposit terminated by . e.g. 10 20 50 .\r\n" +
                "Accepted: 50\r\n" +
                "Accepted: 50\r\n" +
                "Invalid denomination 25$\r\n" +
                "Accepted: 20\r\n" +
                "Accepted: 10\r\n" +
                "1. Deposit\r\n" +
                "2. Withdraw\r\n" +
                "3. Display Balance\r\n" +
                "4. Mini Statement\r\n" +
                "5. Exit\r\n" +
                "Select an option Available balance: 130.0\r\n" +
                "1. Deposit\r\n" +
                "2. Withdraw\r\n" +
                "3. Display Balance\r\n" +
                "4. Mini Statement\r\n" +
                "5. Exit\r\n" +
                "Select an option Have a good day\r\n";

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        InputOutput.getInstance(new ByteArrayInputStream(input.getBytes()), new PrintStream(outContent));

        ATMSimulator.main(new String[] {});

        assertEquals(Double.valueOf(130.0), ATMCashManager.getInstance().getCurrentBalance());
        assertEquals(output, outContent.toString());
    }

    @Test
    public void testWithdrawInSufficientBalance() {

        String input = "2 10 5";
        String output = "1. Deposit\r\n" +
                "2. Withdraw\r\n" +
                "3. Display Balance\r\n" +
                "4. Mini Statement\r\n" +
                "5. Exit\r\n" +
                "Select an option Enter an amount to withdraw \r\n" +
                "Unable to dispense cash\r\n" +
                "1. Deposit\r\n" +
                "2. Withdraw\r\n" +
                "3. Display Balance\r\n" +
                "4. Mini Statement\r\n" +
                "5. Exit\r\n" +
                "Select an option Have a good day\r\n";

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        InputOutput.getInstance(new ByteArrayInputStream(input.getBytes()), new PrintStream(outContent));

        ATMSimulator.main(new String[] {});

        assertEquals(output, outContent.toString());
    }

    @Test
    public void testWithdrawAndDisplayBalanceAndMiniStatement() {

        String input = "1 30 40 20 10 . 3 2 10 4 5";
        String output = "1. Deposit\r\n" +
                "2. Withdraw\r\n" +
                "3. Display Balance\r\n" +
                "4. Mini Statement\r\n" +
                "5. Exit\r\n" +
                "Select an option Enter ccy to deposit terminated by . e.g. 10 20 50 .\r\n" +
                "Invalid denomination 30$\r\n" +
                "Invalid denomination 40$\r\n" +
                "Accepted: 20\r\n" +
                "Accepted: 10\r\n" +
                "1. Deposit\r\n" +
                "2. Withdraw\r\n" +
                "3. Display Balance\r\n" +
                "4. Mini Statement\r\n" +
                "5. Exit\r\n" +
                "Select an option Available balance: 30.0\r\n" +
                "1. Deposit\r\n" +
                "2. Withdraw\r\n" +
                "3. Display Balance\r\n" +
                "4. Mini Statement\r\n" +
                "5. Exit\r\n" +
                "Select an option Enter an amount to withdraw \r\n" +
                "Dispensing 10$\r\n" +
                "1. Deposit\r\n" +
                "2. Withdraw\r\n" +
                "3. Display Balance\r\n" +
                "4. Mini Statement\r\n" +
                "5. Exit\r\n" +
                "Select an option --------------------------------------------------------------------------\r\n" +
                "Date Time   Transaction     Amount      Closing Balance\r\n" +
                "--------------------------------------------------------------------------\r\n" +
                "19/11/2017 01:58:07    CREDIT    30.0   30.0\r\n" +
                "19/11/2017 01:58:08    DEBIT    10.0   20.0\r\n" +
                "---------------------------------------------------------------------------\r\n" +
                "1. Deposit\r\n" +
                "2. Withdraw\r\n" +
                "3. Display Balance\r\n" +
                "4. Mini Statement\r\n" +
                "5. Exit\r\n" +
                "Select an option Have a good day\r\n";

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        InputOutput.getInstance(new ByteArrayInputStream(input.getBytes()), new PrintStream(outContent));

        ATMSimulator.main(new String[] {});

        assertEquals(Double.valueOf(20.0), ATMCashManager.getInstance().getCurrentBalance());
    }


}