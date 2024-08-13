<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View Customers</title>
    <link rel="stylesheet" href="dashboard.css">
</head>
<body>
    <div class="dashboard-container">
        <!-- Side Panel -->
        <div class="side-panel">
            <h2>Admin Dashboard</h2>
            <ul>
                <li><a href="addCustomer.jsp">Add Customer</a></li>
                <li><a href="addAccount.jsp">Add Bank Account</a></li>
                <li><a href="viewCustomer.jsp">View Customers</a></li>
                <li><a href="viewTransaction.jsp">View Transactions</a></li>
            </ul>
            <form action="LogoutServlet" method="post">
                <button type="submit" class="logout-button">Logout</button>
            </form>
        </div>

        <!-- Main Content Area -->
        <div class="main-content">
            <h1>View Customers</h1>
            <form action="ViewCustomerServlet" method="post">
                <button type="submit" class="view-button">View Customers</button>
            </form>
            <c:if test="${not empty customerAccounts}">
                <table class="customer-table">
                    <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Account Number</th>
                            <th>Balance</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="customerAccount" items="${customerAccounts}">
                            <tr>
                                <td>${customerAccount.firstName}</td>
                                <td>${customerAccount.lastName}</td>
                                <td>${customerAccount.accountNumber}</td>
                                <td>${customerAccount.balance}</td>
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
