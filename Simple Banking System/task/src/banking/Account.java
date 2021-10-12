package banking;

import java.util.Random;

public class Account {

    String cardNumber;
    String pin;
    int balance;
    static Random randy = new Random();

    public String getCardNumber() {


        return "400000" + createNewRandom() + "4";

    }

    public String getPin() {

        int p = randy.nextInt(9999 - 1000) + 1000;

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


}
