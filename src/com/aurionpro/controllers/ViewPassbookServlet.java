package com.aurionpro.controllers;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.entity.Customer;
import com.aurionpro.entity.CustomerPassbook;
import com.aurionpro.entity.Transaction;
import com.aurionpro.model.PassbookUtil;
import com.aurionpro.model.TransactionUtil;


@WebServlet("/viewpassbook")
public class ViewPassbookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        String transactionType = request.getParameter("transactionType");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        if (customer != null) {
            List<Transaction> transactions = PassbookUtil.getTransactionsByCustomerIDAndType(
                customer.getCustomerID(),
                transactionType,
                startDate,
                endDate
            );
            request.setAttribute("transactions", transactions);
        } else {
            request.setAttribute("errorMessage", "User not logged in.");
        }

        request.getRequestDispatcher("viewPassbook.jsp").forward(request, response);
    }
}