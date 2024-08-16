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
            <div class="profile-container">
                <h2>Edit Profile</h2>
                <form action="editprofile" method="post">
                    <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="firstName" value="${customer.firstName}" required>
                    
                    <label for="lastName">Last Name:</label>
                    <input type="text" id="lastName" name="lastName" value="${customer.lastName}" required>
                    
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                    
                    <label for="newPassword">New Password:</label>
                    <input type="password" id="newPassword" name="newPassword">
                    
                    <button type="submit">Update Profile</button>
                </form>
                <c:if test="${not empty param.message}">
                    <p>${param.message}</p>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
