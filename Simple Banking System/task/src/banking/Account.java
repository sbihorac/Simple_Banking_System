package banking;

import java.util.Random;

public class Account {

    String cardNumber;
    String pin;
    int balance;
    static Random randy = new Random();

    public String getCardNumber() {

        String accountNumberWithoutCheckDigit = "400000" + createNewRandom();
        return accountNumberWithoutCheckDigit + generateCheckDigit(accountNumberWithoutCheckDigit);

    }

    public String getPin() {

        int p = randy.nextInt(9999);

        return String.format("%04d", p);
    }


    public int getBalance() {
        balance = 0;
        return balance;
    }


    static String createNewRandom() {

        int newRandom = randy.nextInt(999999999);

        return String.format("%09d", newRandom);

    }

    public Account() {
        this.cardNumber = getCardNumber();
        this.pin = getPin();
        this.balance = getBalance();
    }

    public  String toString(Object[] objects) {
        return "Account{" +
                "cardNumber='" + cardNumber + '\'' +
                ", pin='" + pin + '\'' +
                ", balance=" + balance +
                '}';
    }

    private static int generateCheckDigit(String number) {
        int sum = 0;
        int remainder = (number.length() + 1) % 2;
        for (int i = 0; i < number.length(); i++) {

            // Get the digit at the current position.
            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == remainder) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        // The check digit is the number required to make the sum a multiple of 10
        int mod = sum % 10;
        int checkDigit = ((mod == 0) ? 0 : 10 - mod);

        return checkDigit;
    }

}
