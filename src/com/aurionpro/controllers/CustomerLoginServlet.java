	package com.aurionpro.controllers;
	
	import java.io.IOException;
	
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;
	
	import com.aurionpro.entity.Customer;
	import com.aurionpro.model.CustomerUtil;
	
	/**
	 * Servlet implementation class CustomerLoginServlet
	 */
	@WebServlet("/CustomerLoginServlet")
	public class CustomerLoginServlet extends HttpServlet {


	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");

	        // Validate email format
	        if (!isValidEmail(email)) {
	            request.setAttribute("errorMessage", "Invalid email format.");
	            request.getRequestDispatcher("customerlogin.jsp").forward(request, response);
	            return;
	        }

	        // Validate password (e.g., at least 8 characters)
	        if (!isValidPassword(password)) {
	            request.setAttribute("errorMessage", "Password must be at least 8 characters long.");
	            request.getRequestDispatcher("customerlogin.jsp").forward(request, response);
	            return;
	        }

	        // Validate customer credentials
	        Customer customer = CustomerUtil.validateCustomer(email, password);

	        if (customer != null) {
	            HttpSession session = request.getSession();
	            session.setAttribute("customer", customer);
	            session.setAttribute("username", email);

	            // Store customer account number in session
	            session.setAttribute("customerAccount", customer.getAccountNumber());

	            response.sendRedirect("customerdashboard.jsp");
	        } else {
	            request.setAttribute("errorMessage", "Invalid email or password.");
	            request.getRequestDispatcher("customerlogin.jsp").forward(request, response);
	        }
	    }

	    private boolean isValidEmail(String email) {
	        // Simple regex for email validation
	        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	        return email.matches(emailRegex);
	    }

	    private boolean isValidPassword(String password) {
	        // Password should be at least 8 characters long
	        return password != null && password.length() >= 8;
	    }
	}
