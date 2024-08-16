<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View Customers</title>
    <link rel="stylesheet" href="dashboard.css">
    <style>
        /* Style the label */
        .sort-label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }

        /* Style the select dropdown */
        .sort-select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            box-sizing: border-box;
            background-color: #fff;
            color: #333;
        }

        /* Style the select dropdown on focus */
        .sort-select:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.25);
        }

        /* Style the send email button */
        .send-email-btn {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }

        .send-email-btn:hover {
            background-color: #0056b3;
        }

        /* Style the table */
        .customer-table {
            width: 100%;
            border-collapse: collapse;
        }

        .customer-table th, .customer-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        .customer-table th {
            background-color: #f2f2f2;
        }
    </style>
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
            <form action="LogoutServlet" method="post">
                <button type="submit" class="logout-button">Logout</button>
            </form>
        </div>

        <!-- Main Content Area -->
        <div class="main-content">
            <h1>View Customers</h1>

            <!-- Sort and Search Form -->
            <form action="viewcustomer" method="post" class="form-container">
                <div class="form-group">
                    <label for="sortField" class="sort-label">Sort By:</label>
                    <select id="sortField" name="sortField" class="sort-select">
                        <option value="firstName">First Name</option>
                        <option value="lastName">Last Name</option>
                        <option value="accountNumber">Account Number</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="searchValue">Search:</label>
                    <input type="text" id="searchValue" name="searchValue" placeholder="Enter value to search">
                </div>
                <button type="submit" class="view-button">Search and View Customers</button>
                </br></br>
                <button type="submit" class="view-button">View All Customers</button>
            </form>

            <!-- Display Customer Accounts -->
            <c:if test="${not empty customerAccounts}">
                <table class="customer-table">
                    <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Account Number</th>
                            <th>Balance</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="customerAccount" items="${customerAccounts}">
                            <tr>
                                <td>${customerAccount.firstName}</td>
                                <td>${customerAccount.lastName}</td>
                                <td>${customerAccount.accountNumber}</td>
                                <td>${customerAccount.balance}</td>
                                <td>
                                    <form action="SendEmailServlet" method="post">
                                        <input type="hidden" name="email" value="${customerAccount.email}">
                                        <input type="hidden" name="password" value="${customerAccount.password}">
                                        <button type="submit" class="send-email-btn">Send Email</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty customerAccounts}">
                <p>No customers found.</p>
            </c:if>
        </div>
    </div>
</body>
</html>
