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

/**
 * Servlet implementation class NewTransactionServlet
 */
@WebServlet("/NewTransactionServlet")
public class NewTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        Customer customer = (Customer) session.getAttribute("customer");

	        String transactionType = request.getParameter("transactionType");
	        double amount = Double.parseDouble(request.getParameter("amount"));
	        String receiverAccount = request.getParameter("receiverAccount");

	        String result = TransactionUtil.performTransaction(customer.getCustomerID(), transactionType, amount, receiverAccount);

	        if (result.equals("success")) {
	            request.setAttribute("successMessage", "Transaction completed successfully.");
	        } else {
	            request.setAttribute("errorMessage", result);
	        }

	        request.getRequestDispatcher("newTransaction.jsp").forward(request, response);
	    }
}
