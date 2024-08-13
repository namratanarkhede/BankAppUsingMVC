package com.aurionpro.model;

import com.aurionpro.entity.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUtil {

    public static Admin validateAdmin(String username, String password) {
        Admin admin = null;
        Connection connection = DBConnection.getConnection();
        String query = "SELECT * FROM Admin WHERE username = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                admin = new Admin();
                admin.setAdminID(resultSet.getInt("adminID"));
                admin.setUsername(resultSet.getString("username"));
                admin.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}

