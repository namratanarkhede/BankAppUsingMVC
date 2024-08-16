<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank Application</title>
<link rel="stylesheet" href="style.css">
<!-- Option 1: Include in HTML -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<!-- navbar -->
    <nav class="navbar navbar-expand-md navbar-light">
        <div class="container-xxl">
            <a href="#into" class="navbar-brand">
                <span class="fw-bold text-secondary">
                    <i class="bi bi-bank"></i>
                    Bank Application
                </span>
            </a>
            <!-- toggle button for mobile nav -->
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#main-nav"
                aria-controls="main-nav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <!-- navbar links -->
            <div class="collapse navbar-collapse justify-content-end align-center" id="main-nav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="#about">About Me</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="adminlogin">Admin Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="customerlogin">Customer Login</a>
                    </li>
                    <li class="nav-item d-md-none">
                        <a class="nav-link" href="#contact">Contact Bank</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
 <!-- main image and intro text-->
    <section id="intro">
        <div class="container-lg">
            <div class="row justify-content-center align-items-center">
                <div class="col-md-5 text-center text-md-start">
                    <h1>
                        <div class="display-2"> Bank Application</div>
                        <div class="display-5 text-muted"> Namrata Narkhede </div>
                    </h1>
                    <p class="lead my-4 text-muted">
                    Welcome to our banking application! Here, you can easily manage your finances by performing a variety of operations such as viewing your account balance, transferring funds, and checking your transaction history. Whether you need to add new customers, manage accounts, or handle transactions, our intuitive interface ensures a seamless experience. Start exploring and take control of your financial journey today!
                    
                    
                    </p>
         
                </div>
                <div class="col-md-5 text-center d-none d-md-block">
                <img src="logo.webp" class="img-fluid" alt="Responsive image">
                </div>
            </div>
        </div>

    </section>
    
    <!-- Footer -->
    <footer class="footer">
        <div class="container text-center">
            <p>&copy; 2024 Bank Application. All rights reserved.</p>
            <p><a href="mailto:info@bankapplication.com">info@bankapplication.com</a></p>
        </div>
    </footer>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    
</body>
</html>
