<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit Profile</title>
    <link rel="stylesheet" href="dashboard.css">
    
    <link rel="stylesheet" href="profile.css">
    
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
           <div class="profile-container">
        <h2>Edit Profile</h2>
        <form action="EditProfileServlet" method="post">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" value="${customer.firstName}" required>
            
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" value="${customer.lastName}" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password">
            
            <label for="newPassword">New Password:</label>
            <input type="password" id="newPassword" name="newPassword">
            
            <button type="submit">Update Profile</button>
        </form>
        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>
    </div>
        </div>
    </div>
</body>
</html>
