package com.aurionpro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.aurionpro.entity.Transaction;

public class TransactionUtil {
	
	 public static List<Transaction> getAllTransactions() {
	        List<Transaction> transactions = new ArrayList<>();
	        Connection connection = DBConnection.getConnection();
	        String query = "SELECT transactionID, senderAccount, receiverAccount, transactionType, amount, transactionDate " +
	                       "FROM Transactions";
	        try {
	            PreparedStatement statement = connection.prepareStatement(query);
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                Transaction transaction = new Transaction();
	                transaction.setTransactionID(resultSet.getInt("transactionID"));
	                transaction.setSenderAccount(resultSet.getString("senderAccount"));
	                transaction.setReceiverAccount(resultSet.getString("receiverAccount"));
	                transaction.setTransactionType(resultSet.getString("transactionType"));
	                transaction.setAmount(resultSet.getDouble("amount"));
	                transaction.setTransactionDate(resultSet.getDate("transactionDate"));
	                transactions.add(transaction);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return transactions;
	    }


	    public static void addTransaction(String senderAccount, String receiverAccount, String transactionType, double amount) {
	        Connection connection = DBConnection.getConnection();
	        String query = "INSERT INTO Transactions (senderAccount, receiverAccount, transactionType, amount, transactionDate) " +
	                       "VALUES (?, ?, ?, ?, ?)";
	        try {
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setString(1, senderAccount);
	            statement.setString(2, receiverAccount);
	            statement.setString(3, transactionType);
	            statement.setDouble(4, amount);
	            statement.setTimestamp(5, new java.sql.Timestamp(new Date().getTime()));
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public static String performTransaction(int customerID, String transactionType, double amount, String receiverAccount) {
	        Connection connection = DBConnection.getConnection();
	        try {
	            connection.setAutoCommit(false);

	            // Minimum balance required
	            final double MIN_BALANCE = 5000.00;

	            // Get the sender account number
	            String getSenderAccountQuery = "SELECT accountNumber, balance FROM Accounts WHERE customerID = ?";
	            PreparedStatement ps = connection.prepareStatement(getSenderAccountQuery);
	            ps.setInt(1, customerID);
	            ResultSet rs = ps.executeQuery();
	            if (!rs.next()) {
	                return "Sender account not found.";
	            }
	            String senderAccount = rs.getString("accountNumber");
	            double senderBalance = rs.getDouble("balance");

	            // Check if the amount is greater than 0
	            if (amount <= 0) {
	                return "Amount must be greater than 0.";
	            }

	            // Handle transaction types
	            if ("credit".equals(transactionType)) {
	                updateBalance(connection, senderAccount, amount, "credit");
	                addTransaction(connection, senderAccount, null, transactionType, amount);

	            } else if ("debit".equals(transactionType)) {
	                if (senderBalance - amount < MIN_BALANCE) {
	                    return "Insufficient balance. Please add money to maintain a minimum balance of 5000.";
	                }

	                if (updateBalance(connection, senderAccount, amount, "debit")) {
	                    addTransaction(connection, senderAccount, null, transactionType, amount);
	                } else {
	                    return "Insufficient balance.";
	                }

	            } else if ("transfer".equals(transactionType)) {
	                if (receiverAccount == null || receiverAccount.isEmpty()) {
	                    return "Receiver account number is required for transfer.";
	                }

	                // Ensure that receiverAccount is not the same as senderAccount
	                if (senderAccount.equals(receiverAccount)) {
	                    return "You cannot transfer money to your own account.";
	                }

	                // Check if the receiver account exists
	                String checkReceiverQuery = "SELECT accountNumber FROM Accounts WHERE accountNumber = ?";
	                PreparedStatement psReceiver = connection.prepareStatement(checkReceiverQuery);
	                psReceiver.setString(1, receiverAccount);
	                ResultSet rsReceiver = psReceiver.executeQuery();
	                if (!rsReceiver.next()) {
	                    return "Receiver account not found.";
	                }

	                // Check balance before transfer
	                if (senderBalance - amount < MIN_BALANCE) {
	                    return "Insufficient balance. Please add money to maintain a minimum balance of 5000.";
	                }

	                if (updateBalance(connection, senderAccount, amount, "debit")) {
	                    updateBalance(connection, receiverAccount, amount, "credit");
	                    addTransaction(connection, senderAccount, receiverAccount, transactionType, amount);
	                } else {
	                    return "Insufficient balance.";
	                }
	            }

	            connection.commit();
	            return "success";

	        } catch (SQLException e) {
	            try {
	                connection.rollback();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	            e.printStackTrace();
	            return "Transaction failed due to an error.";
	        } finally {
	            try {
	                connection.setAutoCommit(true);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    
	    
	    public static double getCurrentBalance(String customerID) {
	        double balance = 0.0;
	        String query = "SELECT balance FROM Accounts WHERE customerID = ?";
	        
	        try (Connection connection = DBConnection.getConnection();
	             PreparedStatement ps = connection.prepareStatement(query)) {
	            
	            ps.setString(1, customerID);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                balance = rs.getDouble("balance");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return balance;
	    }


	    private static boolean updateBalance(Connection connection, String accountNumber, double amount, String type) throws SQLException {
	        String getBalanceQuery = "SELECT balance FROM Accounts WHERE accountNumber = ?";
	        PreparedStatement ps = connection.prepareStatement(getBalanceQuery);
	        ps.setString(1, accountNumber);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            double currentBalance = rs.getDouble("balance");
	            if ("debit".equals(type) && currentBalance < amount) {
	                return false;
	            }
	            double newBalance = "credit".equals(type) ? currentBalance + amount : currentBalance - amount;

	            String updateBalanceQuery = "UPDATE Accounts SET balance = ? WHERE accountNumber = ?";
	            ps = connection.prepareStatement(updateBalanceQuery);
	            ps.setDouble(1, newBalance);
	            ps.setString(2, accountNumber);
	            ps.executeUpdate();
	            return true;
	        }
	        return false;
	    }

	    private static void addTransaction(Connection connection, String senderAccount, String receiverAccount, String type, double amount) throws SQLException {
	        String insertTransactionQuery = "INSERT INTO Transactions (senderAccount, receiverAccount, transactionType, amount, transactionDate) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement ps = connection.prepareStatement(insertTransactionQuery);
	        ps.setString(1, senderAccount);
	        ps.setString(2, receiverAccount);
	        ps.setString(3, type);
	        ps.setDouble(4, amount);
	        ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
	        ps.executeUpdate();
	    }
	    
	    
	    
	    public static List<Transaction> getCustomerTransactions(int customerID) {
	        List<Transaction> transactions = new ArrayList<>();
	        Connection connection = DBConnection.getConnection();
	        String query = "SELECT * FROM Transactions t " +
	                       "JOIN Accounts sa ON t.senderAccount = sa.accountNumber " +
	                       "LEFT JOIN Accounts ra ON t.receiverAccount = ra.accountNumber " +
	                       "WHERE sa.customerID = ? OR ra.customerID = ?";

	        try {
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setInt(1, customerID);
	            ps.setInt(2, customerID);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Transaction transaction = new Transaction();
	                transaction.setSenderAccount(rs.getString("senderAccount"));
	                transaction.setReceiverAccount(rs.getString("receiverAccount"));
	                transaction.setTransactionType(rs.getString("transactionType"));
	                transaction.setAmount(rs.getDouble("amount"));
	                transaction.setTransactionDate(rs.getTimestamp("transactionDate"));
	                transactions.add(transaction);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return transactions;
	    }
	    
	    
	    
	    
	    public static List<Transaction> getFilteredTransactions(String transactionType, String sortOrder) {
	        List<Transaction> transactions = new ArrayList<>();
	        String query = "SELECT * FROM transactions WHERE 1=1";
	        
	        if (transactionType != null && !transactionType.isEmpty()) {
	            query += " AND transactionType = ?";
	        }
	        
	        if (sortOrder != null && !sortOrder.isEmpty()) {
	            query += " ORDER BY transactionDate " + (sortOrder.equals("desc") ? "DESC" : "ASC");
	        }
	        
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            
	            int index = 1;
	            if (transactionType != null && !transactionType.isEmpty()) {
	                stmt.setString(index++, transactionType);
	            }
	            
	            ResultSet rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	                Transaction transaction = new Transaction();
	                transaction.setTransactionID(rs.getInt("transactionID"));
	                transaction.setSenderAccount(rs.getString("senderAccount"));
	                transaction.setReceiverAccount(rs.getString("receiverAccount"));
	                transaction.setTransactionType(rs.getString("transactionType"));
	                transaction.setAmount(rs.getDouble("amount"));
	                transaction.setTransactionDate(rs.getDate("transactionDate"));
	                
	                transactions.add(transaction);
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return transactions;
	    }
}
