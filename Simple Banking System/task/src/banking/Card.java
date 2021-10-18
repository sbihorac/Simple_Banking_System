package banking;

public class Card {

    private String cardNumber;
    private String pin;
    private int balance;

    public Card(String cardNumber, String pin, int balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public Card(){

    }

    public int getBalance() {
        return balance;
    }

    public int setBalance(int balance) {
        this.balance = balance;
        return balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardnNumber) {
        this.cardNumber = cardnNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String createNewCardNumber() {
        cardNumber = checkSum();
        return cardNumber;
    }

    public String createNewPin(){

        setPin(String.format("%04d", (long) (Math.random() * 9999)) );
        return pin;
    }

    public void createNewCard(){
        createNewCardNumber();
        createNewPin();
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", pin='" + pin + '\'' +
                ", balance=" + balance +
                '}';
    }

    /**
     * Generate check digit using luhn algorithm
     * Source from: https://gist.github.com/stanzheng/5781833
     *
     * @param cardNumber
     * @return
     */
    private static int generateCheckDigit(String cardNumber) {
        int sum = 0;
        int remainder = (cardNumber.length() + 1) % 2;
        for (int i = 0; i < cardNumber.length(); i++) {

            // Get the digit at the current position.
            int digit = Integer.parseInt(cardNumber.substring(i, (i + 1)));

            if ((i % 2) == remainder) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        // The check digit is the number required to make the sum a multiple of
        // 10.
        int mod = sum % 10;
        int checkDigit = ((mod == 0) ? 0 : 10 - mod);

        return checkDigit;
    }

    public String checkSum() {
        String card = "400000" + String.format("%09d", (long) (Math.random() * 999999999L));
        for (int i = 0; i <= 9; i++) {
            String checksum = card + i;
            if (check(checksum)) {
                return checksum;
            }
        }
        throw new IllegalStateException("Probably you made a mistake in the card number. Please try again!\n");
    }

    public static boolean check(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }}