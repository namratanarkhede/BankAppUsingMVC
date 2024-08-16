package com.aurionpro.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.entity.Transaction;
import com.aurionpro.model.TransactionUtil;


@WebServlet("/viewtransaction")
public class ViewTransactionServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String transactionType = request.getParameter("transactionType");
	        String sortOrder = request.getParameter("sortOrder");
	        List<Transaction> transactions;

	        if (transactionType == null && sortOrder == null) {
	            transactions = TransactionUtil.getAllTransactions();
	        } else {
	            transactions = TransactionUtil.getFilteredTransactions(transactionType, sortOrder);
	        }

	        request.setAttribute("transactions", transactions);
	        request.getRequestDispatcher("viewTransaction.jsp").forward(request, response);
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String transactionType = request.getParameter("transactionType");
	        String sortOrder = request.getParameter("sortOrder");
	        List<Transaction> transactions = TransactionUtil.getFilteredTransactions(transactionType, sortOrder);

	        request.setAttribute("transactions", transactions);
	        request.getRequestDispatcher("viewTransaction.jsp").forward(request, response);
	    }
}
