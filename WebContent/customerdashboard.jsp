<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    
    <title>Customer Dashboard</title>
    <link rel="stylesheet" href="dashboard.css">
    <script type="text/javascript">
        function redirectToLogin() {
            window.location.href = "customerlogin.jsp";
        }
    </script>
</head>
<body>
<c:if test="${empty customer.firstName}">
<script type="text/javascript">
    redirectToLogin();
</script>
</c:if>
	
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
            <h1>Welcome, ${customer.firstName}!</h1></br>
            <p>Select an option from the side panel to get started.</p>
        </div>
    </div>
</body>
</html>
