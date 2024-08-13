package com.aurionpro.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.entity.Admin;
import com.aurionpro.model.AdminUtil;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        Admin admin = AdminUtil.validateAdmin(username, password);

	        if (admin != null) {
	            HttpSession session = request.getSession();
	            session.setAttribute("admin", admin);
	            response.sendRedirect("admindashboard.jsp");  // Redirect to admin dashboard
	        } else {
	            response.sendRedirect("adminlogin.jsp?error=Invalid%20Credentials");
	        }
	    }
}
