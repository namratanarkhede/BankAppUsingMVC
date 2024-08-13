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


@WebServlet("/ViewTransactionServlet")
public class ViewTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Transaction> transactions = TransactionUtil.getAllTransactions();
        request.setAttribute("transactions", transactions);
        request.getRequestDispatcher("viewTransaction.jsp").forward(request, response);
    }

}
