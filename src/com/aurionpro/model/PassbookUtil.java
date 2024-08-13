package com.aurionpro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.entity.CustomerPassbook;
import com.aurionpro.entity.Transaction;

public class PassbookUtil {


    public static List<Transaction> getTransactionsByCustomerID(int customerID) {
        List<Transaction> transactions = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        String query = "SELECT t.senderAccount, t.receiverAccount, t.transactionType, t.amount, t.transactionDate " +
                       "FROM Transactions t " +
                       "JOIN Accounts a ON (t.senderAccount = a.accountNumber OR t.receiverAccount = a.accountNumber) " +
                       "WHERE a.customerID = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, customerID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setSenderAccount(resultSet.getString("senderAccount"));
                transaction.setReceiverAccount(resultSet.getString("receiverAccount"));
                transaction.setTransactionType(resultSet.getString("transactionType"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTransactionDate(resultSet.getTimestamp("transactionDate"));

                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

}
