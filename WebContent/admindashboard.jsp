<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin Dashboard</title>
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
            <h1>Welcome, Admin!</h1></br>
            <p>Select an option from the side panel to get started.</p>
        </div>
    </div>
</body>
</html>
