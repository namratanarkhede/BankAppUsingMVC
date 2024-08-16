<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>New Transaction</title>
    <link rel="stylesheet" href="dashboard.css">
    <style>
        .transaction-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
        }

        h2.transaction-heading {
            font-size: 24px;
            margin-bottom: 20px;
            text-align: center;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            font-size: 16px;
            margin-bottom: 8px;
            color: #333;
            font-weight: bold;
        }

        input[type="number"],
        input[type="text"],
        select {
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            width: 100%;
            box-sizing: border-box;
        }

        input[type="number"]:focus,
        input[type="text"]:focus,
        select:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.25);
        }

        button {
            background-color: #007bff;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            padding: 15px;
            border-radius: 5px;
            border: 1px solid #f5c6cb;
            margin-top: 20px;
            text-align: center;
        }

        .success-message {
            background-color: #d4edda;
            color: #155724;
            padding: 15px;
            border-radius: 5px;
            border: 1px solid #c3e6cb;
            margin-top: 20px;
            text-align: center;
        }
    </style>
    <script>
        function toggleReceiverAccount() {
            var transactionType = document.getElementById("transactionType").value;
            var receiverAccountField = document.getElementById("receiverAccountField");
            if (transactionType === "transfer") {
                receiverAccountField.style.display = "block";
            } else {
                receiverAccountField.style.display = "none";
            }
        }

        function validateTransactionForm() {
            var transactionType = document.getElementById("transactionType").value;
            var senderAccount = '${customer.customerID}'; // Get the sender account from the session
            var receiverAccount = document.getElementById("receiverAccount").value;
            var amount = parseFloat(document.getElementById("amount").value);

            if (amount <= 0) {
                alert("Amount must be greater than zero.");
                return false;
            }

            if (transactionType === "transfer" && receiverAccount === senderAccount) {
                alert("You cannot transfer money to your own account.");
                return false;
            }
            return true;
        }
    </script>
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
            <div class="transaction-container">
                <h2 class="transaction-heading">New Transaction</h2>
                
                 <!-- Display current balance -->
                <c:if test="${not empty currentBalance}">
                    <p><strong>Current Balance:</strong> ${currentBalance}</p>
                </c:if>
                <form action="newtransaction" method="post" onsubmit="return validateTransactionForm()">
                    <label for="transactionType">Transaction Type:</label>
                    <select id="transactionType" name="transactionType" onchange="toggleReceiverAccount()">
                        <option value="credit">Credit</option>
                        <option value="debit">Debit</option>
                        <option value="transfer">Transfer</option>
                    </select>

                    <label for="amount">Amount:</label>
                    <input type="number" id="amount" name="amount" required>

                    <div id="receiverAccountField" style="display:none;">
                        <label for="receiverAccount">Receiver Account Number:</label>
                        <input type="text" id="receiverAccount" name="receiverAccount">
                    </div>

                    <button type="submit">Submit</button>
                </form>

                <c:if test="${not empty errorMessage}">
                    <p class="error-message">${errorMessage}</p>
                </c:if>
                <c:if test="${not empty successMessage}">
                    <p class="success-message">${successMessage}</p>
                </c:if>

                <!-- Display current balance -->
                <c:if test="${not empty currentBalance}">
                    <p><strong>Current Balance:</strong> ${currentBalance}</p>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
