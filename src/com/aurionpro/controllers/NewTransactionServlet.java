package com.aurionpro.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.entity.Customer;
import com.aurionpro.entity.Transaction;
import com.aurionpro.model.TransactionUtil;
@WebServlet("/newtransaction")
public class NewTransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("customerlogin.jsp");
            return;
        }

        request.setAttribute("currentBalance", TransactionUtil.getCurrentBalance(String.valueOf(customer.getCustomerID())));
        request.getRequestDispatcher("newTransaction.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("customerlogin.jsp");
            return;
        }

        String transactionType = request.getParameter("transactionType");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String receiverAccount = request.getParameter("receiverAccount");

        String customerID = String.valueOf(customer.getCustomerID());

        if ("transfer".equals(transactionType) && customerID.equals(receiverAccount)) {
            request.setAttribute("errorMessage", "You cannot transfer money to your own account.");
            request.setAttribute("currentBalance", TransactionUtil.getCurrentBalance(customerID));
            request.getRequestDispatcher("newTransaction.jsp").forward(request, response);
            return;
        }

        if (amount <= 0) {
            request.setAttribute("errorMessage", "Amount must be greater than zero.");
            request.setAttribute("currentBalance", TransactionUtil.getCurrentBalance(customerID));
            request.getRequestDispatcher("newTransaction.jsp").forward(request, response);
            return;
        }

        String result = TransactionUtil.performTransaction(customer.getCustomerID(), transactionType, amount, receiverAccount);

        if ("success".equals(result)) {
            request.setAttribute("successMessage", "Transaction completed successfully.");
        } else {
            request.setAttribute("errorMessage", result);
        }

        request.setAttribute("currentBalance", TransactionUtil.getCurrentBalance(customerID));
        request.getRequestDispatcher("newTransaction.jsp").forward(request, response);
    }
}