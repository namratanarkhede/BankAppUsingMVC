package com.aurionpro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class AccountUtil {

    public static boolean createAccountForCustomer(int customerId) {
        Connection connection = DBConnection.getConnection();
        String accountNumber = generateAccountNumber();
        String query = "INSERT INTO Accounts (accountNumber, customerID, balance) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, accountNumber);
            statement.setInt(2, customerId);
            statement.setDouble(3, 0.0); // Initial balance
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String generateAccountNumber() {
        // Simple example of generating a random account number
        Random random = new Random();
        int number = 10000000 + random.nextInt(90000000);
        return String.valueOf(number);
    }
    

    public static double getBalance(String accountNumber) {
        double balance = 0;
        Connection connection = DBConnection.getConnection();
        String query = "SELECT balance FROM Accounts WHERE accountNumber = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, accountNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                balance = resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public static void updateBalance(String accountNumber, double newBalance) {
        Connection connection = DBConnection.getConnection();
        String query = "UPDATE Accounts SET balance = ? WHERE accountNumber = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, newBalance);
            statement.setString(2, accountNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
