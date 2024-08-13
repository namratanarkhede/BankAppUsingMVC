<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View Transactions</title>
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
            <h1>View Transactions</h1>
            <form action="ViewTransactionServlet" method="post">
                <button type="submit" class="view-button">View Transactions</button>
            </form>
            <c:if test="${not empty transactions}">
                <table class="transaction-table">
                    <thead>
                        <tr>
                            <th>Transaction ID</th>
                            <th>Sender Account</th>
                            <th>Receiver Account</th>
                            <th>Transaction Type</th>
                            <th>Amount</th>
                            <th>Transaction Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="transaction" items="${transactions}">
                            <tr>
                                <td>${transaction.transactionID}</td>
                                <td>${transaction.senderAccount}</td>
                                <td>${transaction.receiverAccount}</td>
                                <td>${transaction.transactionType}</td>
                                <td>${transaction.amount}</td>
                                <td>${transaction.transactionDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty transactions}">
                <p>No transactions found.</p>
            </c:if>
        </div>
    </div>
</body>
</html>
