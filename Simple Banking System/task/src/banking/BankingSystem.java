package banking;

import java.util.*;

public class BankingSystem {

    static Scanner input = new Scanner(System.in);
    static Account account = new Account();
    static Map<String, int[] > accounts  = new HashMap<>();


    public static void main(String[] args) {

        boolean doExit = false;
        do {
            System.out.println("1. Create an account\n" + "2. Log into account\n" +
                    "3. Exit");
            int choice = input.nextInt();

            if (choice == 1) {
                System.out.println();
                createAccount();
            } else if (choice == 2) {
                System.out.println();
                accessAccount();
            } else if (choice == 3) {
                System.out.println();
                System.out.println("Bye!");
                doExit = true ;
            }

        } while (!doExit);

    }


    private static void createAccount(){

        String newCard = account.getCardNumber();
        int PIN = Integer.parseInt(account.getPin());
        int Balance = account.getBalance();
        int[] accountData = {PIN, Balance};
        accounts.put(newCard,accountData);
        System.out.println("Your card has been created\n" + "Your card number:\n" +
                newCard + "\n" + "Your card PIN:\n" + PIN + "\n");

    }

    private static void accessAccount(){

        System.out.println("Enter your card number:");
        String cardNumber = input.next();
        int[] chosenAccount =  accounts.get(cardNumber);
        System.out.println("Enter your card PIN:");
        int PIN = input.nextInt();

        if(accounts.containsKey(cardNumber) && chosenAccount[0] == PIN) {
            System.out.println("You have successfully logged in!");
            System.out.println();
            boolean menu = true;

            do{
                System.out.println("1. Balance\n" + "2. Log out\n" + "0.Exit\n");
                int choice1 = input.nextInt();
                switch (choice1) {
                    case 1:
                        System.out.println("Balance " + account.getBalance() + "\n");
                        break;
                    case 2:
                        System.out.println("You have successfully logged out!\n");
                        menu = false;
                        break;
                    case 0:
                        System.out.println("Bye");
                        menu = false;
                        break;
                    default:
                        System.out.println("There is no such option!\n");
                        break;
                }
            } while (menu);
        } else {
            System.out.println("Wrong card number or PIN!");
        }

    }
}
