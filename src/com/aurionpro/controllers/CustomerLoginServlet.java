package com.aurionpro.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.entity.Customer;
import com.aurionpro.model.CustomerUtil;

@WebServlet("/customerlogin")
public class CustomerLoginServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the GET request to the login page
        request.getRequestDispatcher("customerlogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Check if email or password is null/empty
        if (email == null || email.isEmpty()) {
            request.setAttribute("error", "Email cannot be empty.");
            request.getRequestDispatcher("customerlogin.jsp").forward(request, response);
            return;
        }

        if (password == null || password.isEmpty()) {
            request.setAttribute("error", "Password cannot be empty.");
            request.getRequestDispatcher("customerlogin.jsp").forward(request, response);
            return;
        }

        // Validate customer credentials
        Customer customer = CustomerUtil.validateCustomer(email, password);

        if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
            session.setAttribute("username", email);
            session.setAttribute("customerAccount", customer.getAccountNumber());

            response.sendRedirect("customerdashboard");
        } else {
            request.setAttribute("error", "Invalid email or password.");
            request.getRequestDispatcher("customerlogin.jsp").forward(request, response);
        }
    }
}
