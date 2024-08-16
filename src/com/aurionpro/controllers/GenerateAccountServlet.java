package com.aurionpro.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.model.AccountUtil;

/**
 * Servlet implementation class GenerateAccountServlet
 */
@WebServlet("/generateaccount")
public class GenerateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        int customerId = Integer.parseInt(request.getParameter("customerId"));

	        try {
	            boolean success = AccountUtil.createAccountForCustomer(customerId);
	            if (success) {
	                request.setAttribute("successMessage", "Account created successfully.");
	            } else {
	                request.setAttribute("errorMessage", "Error creating account.");
	            }
	        } catch (Exception e) {
	            request.setAttribute("errorMessage", "Error creating account: " + e.getMessage());
	        }

	        // Forward to the JSP page
	        request.getRequestDispatcher("addAccount.jsp").forward(request, response);
	    }
}
