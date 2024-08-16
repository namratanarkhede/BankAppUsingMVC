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


@WebServlet("/adminlogin")
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the GET request to the login page
        request.getRequestDispatcher("adminlogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if username or password is null/empty
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Username and password cannot be empty.");
            request.getRequestDispatcher("adminlogin.jsp").forward(request, response);
            return;
        }

        // Validate admin credentials
        Admin admin = AdminUtil.validateAdmin(username, password);

        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            response.sendRedirect("admindashboard");  // Redirect to Admin Dashboard Servlet
        } else {
            request.setAttribute("error", "Invalid credentials.");
            request.getRequestDispatcher("adminlogin.jsp").forward(request, response);
        }
    }
}