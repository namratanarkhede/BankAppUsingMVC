<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Customer</title>
    <link rel="stylesheet" href="dashboard.css">
</head>
<body>
    <div class="dashboard-container">
        <!-- Side Panel -->
        <div class="side-panel">
            <h2>Admin Dashboard</h2>
            <ul>
                <li><a href="addcustomer">Add Customer</a></li>
                <li><a href="addaccount">Add Bank Account</a></li>
               <li><a href="viewcustomer">View Customers</a></li>
                <li><a href="viewtransaction">View Transactions</a></li>
            </ul>
            <form action="LogoutServlet"	 method="post">
                <button type="submit" class="logout-button">Logout</button>
            </form>
        </div>

        <!-- Main Content Area -->
        <div class="main-content">
            <h1>Add Customer</h1>
            <form action="addcustomer" method="post" class="form-container">
                <div class="form-group">
                    <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="firstName" required>
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name:</label>
                    <input type="text" id="lastName" name="lastName" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit" class="submit-button">Add Customer</button>
            </form>
            <!-- Display success message -->
            <c:if test="${not empty successMessage}">
                <div class="success-message">
                    <p>${successMessage}</p>
                </div>
            </c:if>
            <!-- Display error message -->
            <c:if test="${not empty errorMessage}">
                <div class="error-message">
                    <p>${errorMessage}</p>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>
