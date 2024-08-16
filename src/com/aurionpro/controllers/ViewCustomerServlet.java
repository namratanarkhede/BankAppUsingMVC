package com.aurionpro.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.entity.CustomerAccount;
import com.aurionpro.model.CustomerAccountUtil;
import com.aurionpro.model.CustomerUtil;
@WebServlet("/viewcustomer")
public class ViewCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect to the same form page or handle GET requests
        request.getRequestDispatcher("viewCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sortField = request.getParameter("sortField");
        String searchValue = request.getParameter("searchValue");

        List<CustomerAccount> customerAccounts;

        if (searchValue != null && !searchValue.trim().isEmpty()) {
            customerAccounts = CustomerAccountUtil.getSortedAndFilteredCustomerAccounts(sortField, searchValue);
        } else {
            customerAccounts = CustomerAccountUtil.getAllCustomerAccounts();
        }

        request.setAttribute("customerAccounts", customerAccounts);

        request.getRequestDispatcher("viewCustomer.jsp").forward(request, response);
    }
}