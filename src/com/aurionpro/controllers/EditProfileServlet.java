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

@WebServlet("/editprofile")
public class EditProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");

        String message = "";

        if (CustomerUtil.validateCustomer1(customer.getEmail(), password)) {
            if (CustomerUtil.updateCustomerProfile(customer.getCustomerID(), firstName, lastName, newPassword)) {
                message = "Profile updated successfully.";
                if (newPassword != null && !newPassword.isEmpty()) {
                    // Invalidate the session if the password is changed
                    session.invalidate();
                    message += " Please log in again with the new password.";
                    // Redirect to the customer login page
                    response.sendRedirect("customerlogin.jsp?message=" + message);
                    return;
                }
            } else {
                message = "Error updating profile.";
            }
        } else {
            message = "Current password is incorrect.";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("editProfile.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("customerlogin.jsp");
            return;
        }

        request.getRequestDispatcher("editProfile.jsp").forward(request, response);
    }
}