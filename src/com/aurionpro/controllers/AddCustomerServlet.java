package com.aurionpro.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.entity.Customer;
import com.aurionpro.model.CustomerUtil;

@WebServlet("/addcustomer")
public class AddCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String validationMessage = validateInput(firstName, lastName, email, password);
        if (validationMessage != null) {
            request.setAttribute("errorMessage", validationMessage);
            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
            return;
        }

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(password);

        boolean isAdded = CustomerUtil.addCustomer(customer);

        if (isAdded) {
            request.setAttribute("successMessage", "Customer added successfully!");
        } else {
            request.setAttribute("errorMessage", "Failed to add customer. Please try again.");
        }

        request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
    }

    private String validateInput(String firstName, String lastName, String email, String password) {
        if (firstName == null || firstName.trim().isEmpty()) {
            return "First name is required.";
        }
        if (!isAlphabetic(firstName)) {
            return "First name should only contain alphabetic characters.";
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            return "Last name is required.";
        }
        if (!isAlphabetic(lastName)) {
            return "Last name should only contain alphabetic characters.";
        }
        if (email == null || !isValidEmail(email)) {
            return "Invalid email format.";
        }
        if (password == null || !isValidPassword(password)) {
            return "Password must be at least 8 characters long.";
        }
        return null; // Return null if all inputs are valid
    }

    private boolean isAlphabetic(String input) {
        return input.matches("^[a-zA-Z]+$");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }
}
