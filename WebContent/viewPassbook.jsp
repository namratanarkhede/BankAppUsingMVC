<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View Passbook</title>
    
    <link rel="stylesheet" href="dashboard.css">
    <link rel="stylesheet" href="passbook.css">
</head>
<body>
    <div class="dashboard-container">
        <!-- Side Panel -->
        <div class="side-panel">
            <h2>Customer Dashboard</h2>
            <ul>
                <li><a href="newTransaction.jsp">New Transaction</a></li>
                <li><a href="viewPassbook.jsp">View Passbook</a></li>
                <li><a href="editProfile.jsp">Edit Profile</a></li>
            </ul>
            <form action="LogoutServlet" method="post">
                <button type="submit" class="logout-button">Logout</button>
            </form>
        </div>

        <!-- Main Content Area -->
        <div class="main-content">
            <div class="passbook-container">
                <h2>View Passbook</h2>
                <form action="ViewPassbookServlet" method="post">
                    <button type="submit">Show Passbook</button>
                </form>

                <c:if test="${not empty transactions}">
                    <table>
                        <thead>
                            <tr>
                                <th>Sender Account Number</th>
                                <th>Receiver Account Number</th>
                                <th>Transaction Type</th>
                                <th>Amount</th>
                                <th>Transaction Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="transaction" items="${transactions}">
                                <tr>
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
    </div>
</body>
</html>
