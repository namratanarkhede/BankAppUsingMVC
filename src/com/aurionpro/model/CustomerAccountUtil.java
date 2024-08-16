package com.aurionpro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.aurionpro.entity.CustomerAccount;

public class CustomerAccountUtil {
	 
    public static List<CustomerAccount> getSortedAndFilteredCustomerAccounts(String sortField, String searchValue) {
        List<CustomerAccount> customerAccounts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String query = "SELECT c.firstName, c.lastName, a.accountNumber, a.balance " +
                           "FROM Customers c JOIN Accounts a ON c.customerID = a.customerID " +
                           "WHERE " + sortField + " LIKE ? " +
                           "ORDER BY " + sortField;

            statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchValue + "%");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CustomerAccount customerAccount = new CustomerAccount();
                customerAccount.setFirstName(resultSet.getString("firstName"));
                customerAccount.setLastName(resultSet.getString("lastName"));
                customerAccount.setAccountNumber(resultSet.getString("accountNumber"));
                customerAccount.setBalance(resultSet.getDouble("balance"));
                customerAccounts.add(customerAccount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customerAccounts;
    }
    
    
    
    public static List<CustomerAccount> getAllCustomerAccounts() {
        List<CustomerAccount> customerAccounts = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        String query = "SELECT c.firstName, c.lastName, a.accountNumber, a.balance " +
                       "FROM Customers c JOIN Accounts a ON c.customerID = a.customerID";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CustomerAccount customerAccount = new CustomerAccount();
                customerAccount.setFirstName(resultSet.getString("firstName"));
                customerAccount.setLastName(resultSet.getString("lastName"));
                customerAccount.setAccountNumber(resultSet.getString("accountNumber"));
                customerAccount.setBalance(resultSet.getDouble("balance"));
                customerAccounts.add(customerAccount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerAccounts;
    }
}
