package banking;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DatabaseManager {

    static final Scanner s = new Scanner(System.in);

    static Connection conn = null;
    static Map<String, Card> cards = new HashMap<>();
    static Card c = new Card();

    public static void makeDBConnection(String fileName) {
        String sql = "CREATE TABLE IF NOT EXISTS card (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    number text NOT NULL UNIQUE,\n"
                + "    pin text NOT NULL,\n"
                + "    balance integer\n"
                + ");";
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + fileName);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertToDB(Card c) {

        String sql = "INSERT INTO card (number, pin, balance) VALUES(?, ?, ?)";
        try (PreparedStatement s = conn.prepareStatement(sql)) {
            s.setString(1, c.getCardNumber());
            s.setString(2, c.getPin());
            s.setInt(3, c.getBalance());
            s.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        cards.put(c.getCardNumber(), c);
        System.out.println(c);
    }

    public static int balanceStatus(String number) {
        String sql = "SELECT number, balance FROM card";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (number.equals(resultSet.getString("number"))) {
                    return resultSet.getInt("balance");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public static void addIncome(String number, int InputtedIncome) {
        String sql = "UPDATE card SET " +
                "balance = ?" +
                "WHERE number = ?";
        int balance = balanceStatus(number);
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, balance + InputtedIncome);
            statement.setString(2, number);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean isCardInDB(String cardNumber, String pin) {
        String sql = "SELECT number, pin FROM card";
        try {
            Connection conn = DatabaseManager.conn;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (cardNumber.equals(resultSet.getString("number")) &&
                        pin.equals(resultSet.getString("pin"))) {
                    return true;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public static boolean isCardInDB(String number) {
        String sql = "SELECT number, pin FROM card";
        try {
            Connection conn = DatabaseManager.conn;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (number.equals(resultSet.getString("number")))
                    return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    public static void deleteAccount(Card c) {
        String sql = "DELETE FROM card WHERE number = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getCardNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void closeDBConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }