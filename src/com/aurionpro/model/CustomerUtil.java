package com.aurionpro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.entity.Customer;
import com.aurionpro.entity.CustomerPassbook;
import java.sql.*;
public class CustomerUtil {

    public static boolean addCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isAdded = false;
        
        try {
            connection = DBConnection.getConnection();
            String sql = "INSERT INTO customers (firstName, lastName, email, password) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPassword());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isAdded = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isAdded;
    }
    
    
    
    public static Customer getCustomerById(int customerId) {
        Customer customer = null;
        Connection connection = DBConnection.getConnection();
        String query = "SELECT * FROM Customers WHERE customerID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer();
                customer.setCustomerID(resultSet.getInt("customerID"));
                customer.setFirstName(resultSet.getString("firstName"));
                customer.setLastName(resultSet.getString("lastName"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
	    
//	    public static Customer validateCustomer(String email, String password) {
//	        Customer customer = null;
//	        Connection connection = DBConnection.getConnection();
//	        String query = "SELECT * FROM Customers WHERE email = ? AND password = ?";
//	        try {
//	            PreparedStatement statement = connection.prepareStatement(query);
//	            statement.setString(1, email);
//	            statement.setString(2, password);
//	            ResultSet resultSet = statement.executeQuery();
//	            if (resultSet.next()) {
//	                customer = new Customer();
//	                customer.setCustomerID(resultSet.getInt("customerID"));
//	                customer.setFirstName(resultSet.getString("firstName"));
//	                customer.setLastName(resultSet.getString("lastName"));
//	                customer.setEmail(resultSet.getString("email"));
//	                customer.setPassword(resultSet.getString("password"));
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	        return customer;
//	    }
//    
    
    public static Customer validateCustomer(String email, String password) {
        Customer customer = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish DB connection
            connection = DBConnection.getConnection();
            
            // Prepare SQL query
            String query = "SELECT c.customerID, c.firstName, c.lastName, c.email, c.password, a.accountNumber " +
                           "FROM Customers c " +
                           "JOIN Accounts a ON c.customerID = a.customerID " +
                           "WHERE c.email = ? AND c.password = ?";
            
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                customer = new Customer();
                customer.setCustomerID(resultSet.getInt("customerID"));
                customer.setFirstName(resultSet.getString("firstName"));
                customer.setLastName(resultSet.getString("lastName"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
                customer.setAccountNumber(resultSet.getString("accountNumber")); // Assuming Customer has this field
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customer;
    }
    
    
    public static boolean validateCustomer1(String email, String password) {
        Connection connection = DBConnection.getConnection();
        String query = "SELECT * FROM Customers WHERE email = ? AND password = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateCustomerProfile(int customerID, String firstName, String lastName, String newPassword) {
        Connection connection = DBConnection.getConnection();
        String query = "UPDATE Customers SET firstName = ?, lastName = ?, password = ? WHERE customerID = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, newPassword);
            statement.setInt(4, customerID);
            
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    
//    
//    
//    public List<CustomerPassbook> getCustomerTransaction(String senderAccount) {
//        List<CustomerPassbook> transactions = new ArrayList<>();
//        Connection connection = DBConnection.getConnection();
//        String query = "SELECT * FROM Transactions WHERE senderAccount = ? OR receiverAccount = ?";
//
//        try {
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, senderAccount);
//            ps.setString(2, senderAccount);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                CustomerPassbook passbook = new CustomerPassbook(
//                    rs.getString("senderAccount"),
//                    rs.getString("receiverAccount"),
//                    rs.getString("transactionType"),
//                    rs.getDouble("amount"),
//                    rs.getTimestamp("transactionDate")
//                );
//                transactions.add(passbook);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return transactions;
//    }
}
