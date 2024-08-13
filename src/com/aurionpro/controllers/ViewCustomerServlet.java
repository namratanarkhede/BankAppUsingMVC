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

/**
 * Servlet implementation class ViewCustomerServlet
 */
@WebServlet("/ViewCustomerServlet")
public class ViewCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CustomerAccount> customerAccounts = CustomerAccountUtil.getAllCustomerAccounts();
        request.setAttribute("customerAccounts", customerAccounts);
        request.getRequestDispatcher("viewCustomer.jsp").forward(request, response);
    }
}
