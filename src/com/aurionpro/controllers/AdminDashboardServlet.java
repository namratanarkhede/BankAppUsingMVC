package com.aurionpro.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admindashboard")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Retrieve the existing session, do not create a new one
        
        if (session != null && session.getAttribute("admin") != null) {
            // Admin is logged in, forward to admin dashboard
            request.getRequestDispatcher("admindashboard.jsp").forward(request, response);
        } else {
            // Admin is not logged in, redirect to admin login page
            response.sendRedirect("adminlogin.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
