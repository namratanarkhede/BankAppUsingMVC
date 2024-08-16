<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Bank Account</title>
    <link rel="stylesheet" href="dashboard.css">
    <script>
        function toggleCustomerDetails(showCustomer, showSuccess, showError) {
            var customerDetails = document.getElementById('customerDetails');
            var successMessage = document.getElementById('successMessage');
            var errorMessage = document.getElementById('errorMessage');
            var customerAddedMessage = document.getElementById('customerAddedMessage');

            customerDetails.style.display = showCustomer ? 'block' : 'none';
            successMessage.style.display = showSuccess ? 'block' : 'none';
            errorMessage.style.display = showError ? 'block' : 'none';
            customerAddedMessage.style.display = showSuccess && '${successMessage}' === 'Customer added successfully!' ? 'block' : 'none';
        }

        document.addEventListener('DOMContentLoaded', function() {
            var customer = '${customer}';
            var errorMessage = '${errorMessage}';
            var successMessage = '${successMessage}';
            
            var showCustomer = customer ? true : false;
            var showSuccess = successMessage && successMessage !== 'Customer added successfully!' ? true : false;
            var showError = errorMessage ? true : false;

            toggleCustomerDetails(showCustomer, showSuccess, showError);
        });
    </script>
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
            <h1>Add Bank Account</h1>
            <form action="searchcustomer" method="post" class="form-container">
                <div class="form-group">
                    <label for="customerId">Customer ID:</label>
                    <input type="text" id="customerId" name="customerId" required>
                </div>
                <button type="submit" class="search-button">Search Customer</button>
            </form>

            <!-- Display customer details -->
            <div id="customerDetails" style="display: none;">
                <h2>Customer Details</h2>
                <p><strong>First Name:</strong> ${customer.firstName}</p>
                <p><strong>Last Name:</strong> ${customer.lastName}</p>
                <p><strong>Email:</strong> ${customer.email}</p>

                <!-- Form to generate account -->
                <form action="generateaccount" method="post" class="form-container">
                    <input type="hidden" name="customerId" value="${customer.customerID}">
                    <button type="submit" class="generate-button">Generate Account Number</button>
                </form>
            </div>

            <!-- Display success or error message -->
            <div id="successMessage" style="display: none;">
                <div class="success-message">
                    <p>${successMessage}</p>
                </div>
            </div>
            <div id="errorMessage" style="display: none;">
                <div class="error-message">
                    <p>${errorMessage}</p>
                </div>
            </div>
            <div id="customerAddedMessage" style="display: none;">
                <div class="success-message">
                    <p>Customer added successfully!</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
