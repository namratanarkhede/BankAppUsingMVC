<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View Transactions</title>
    <link rel="stylesheet" href="dashboard.css">
    <style>
    /* Style the label for dropdowns */
.dropdown-label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    color: #333;
}

/* Style the select dropdowns */
.dropdown-select {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 16px;
    box-sizing: border-box;
    background-color: #fff;
    color: #333;
}

/* Style the select dropdowns on focus */
.dropdown-select:focus {
    border-color: #007bff;
    outline: none;
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.25);
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
            <h1>View Transactions</h1>
            <form action="viewtransaction" method="post">
    <label for="transactionType" class="dropdown-label">Transaction Type:</label>
    <select id="transactionType" name="transactionType" class="dropdown-select">
        <option value="">All</option>
        <option value="credit">Credit</option>
        <option value="debit">Debit</option>
        <option value="transfer">Transfer</option>
    </select>
    <br><br>
    <label for="sortOrder" class="dropdown-label">Sort By Date:</label>
    <select id="sortOrder" name="sortOrder" class="dropdown-select">
        <option value="asc">Ascending</option>
        <option value="desc">Descending</option>
    </select>
    <br><br>
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
