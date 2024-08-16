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
                <li><a href="newtransaction">New Transaction</a></li>
                <li><a href="viewpassbook">View Passbook</a></li>
                <li><a href="editprofile">Edit Profile</a></li>
            </ul>
            <form action="LogoutServlet" method="post">
                <button type="submit" class="logout-button">Logout</button>
            </form>
        </div>

        <!-- Main Content Area -->
        <div class="main-content">
            <div class="passbook-container">
                <div class="form-container">
                    <form action="viewpassbook" method="get">
                        <label for="transactionType">Select Transaction Type:</label>
                        <select id="transactionType" name="transactionType" onchange="this.form.submit()">
                            <option value="">All</option>
                            <option value="credit" <c:if test="${param.transactionType == 'credit'}">selected</c:if>>Credit</option>
                            <option value="debit" <c:if test="${param.transactionType == 'debit'}">selected</c:if>>Debit</option>
                            <option value="transfer" <c:if test="${param.transactionType == 'transfer'}">selected</c:if>>Transfer</option>
                        </select>

                        <br><br>

                        <label for="startDate">Start Date:</label>
                        <input type="date" id="startDate" name="startDate" value="<c:out value="${param.startDate}" />">

                        <label for="endDate">End Date:</label>
                        <input type="date" id="endDate" name="endDate" value="<c:out value="${param.endDate}" />">

                        <button type="submit">Filter</button>
                    </form>
                </div>

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
