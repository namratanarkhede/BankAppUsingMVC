package com.aurionpro.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.entity.Customer;
import com.aurionpro.model.CustomerUtil;


@WebServlet("/addaccount")
public class AddAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerIdParam = request.getParameter("customerId");

        if (customerIdParam == null || customerIdParam.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Customer ID cannot be empty.");
            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            return;
        }

        int customerId;
        try {
            customerId = Integer.parseInt(customerIdParam);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid Customer ID.");
            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            return;
        }

        Customer customer = CustomerUtil.getCustomerById(customerId);

        if (customer != null) {
            request.setAttribute("customer", customer);
            request.setAttribute("successMessage", "Customer found successfully.");
        } else {
            request.setAttribute("errorMessage", "Customer not found.");
        }

        request.getRequestDispatcher("addAccount.jsp").forward(request, response);
    }
}