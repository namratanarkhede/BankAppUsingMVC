package com.aurionpro.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.entity.Customer;
import com.aurionpro.model.CustomerUtil;

/**
 * Servlet implementation class SearchCustomerServlet
 */
@WebServlet("/SearchCustomerServlet")
public class SearchCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        Customer customer = CustomerUtil.getCustomerById(customerId);

        if (customer != null) {
            request.setAttribute("customer", customer);
            request.setAttribute("successMessage", "Customer found successfully!");
        } else {
            request.setAttribute("errorMessage", "Customer not found.");
        }

        // Forward to the JSP page
        request.getRequestDispatcher("addAccount.jsp").forward(request, response);
    }
}