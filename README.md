# Bank Application

This is a bank application developed using the MVC (Model-View-Controller) architecture. The application allows administrators to manage customers and accounts, while customers can perform transactions and manage their profiles.

## MVC Model Overview

### What is MVC?

The **MVC (Model-View-Controller)** is a software design pattern that separates an application into three interconnected components:

- **Model:** Represents the application's data and the logic to manage it. It directly handles data management, database interactions, and business rules.
- **View:** The UI part of the application that represents the presentation of the data to the user. This layer handles the rendering of the data from the model and sending it to the user.
- **Controller:** Acts as an intermediary between the Model and View components. It receives user input, processes it (often using the Model), and determines the output or the next View to display.

By separating these concerns, MVC promotes code modularity, reusability, and maintainability.

### How MVC is Implemented in This Project

- **Model:** The `Customer`, `Account`, `Transaction`, and other entity classes represent the data. The `CustomerUtil`, `AccountUtil`, and `TransactionUtil` classes handle the business logic, database interactions, and other operations.
- **View:** JSP pages are used as the view layer for rendering user interfaces for both admin and customer dashboards. Examples include `adminDashboard.jsp`, `customerDashboard.jsp`, and other JSP files for specific features.
- **Controller:** Servlets are used as controllers, handling requests and directing them to the appropriate models and views. Examples include `AddCustomerServlet`, `NewTransactionServlet`, and `EditProfileServlet`.

## Application Features

### Admin Dashboard

The admin dashboard allows administrators to manage customers, accounts, and transactions. Key features include:

- **Add Customer:** Admins can add new customers by providing their details such as first name, last name, email, and password.
- **Add Account:** Admins can generate a new account for an existing customer.
- **View Customers:** Admins can view a list of all customers along with their account details and balances.
- **View Transactions:** Admins can view the transaction history for all customers.

### Customer Dashboard

The customer dashboard provides customers with the ability to manage their accounts and perform transactions. Key features include:

- **New Transaction:** Customers can perform credit, debit, and transfer transactions. In the case of a transfer, the customer can specify the recipient's account number.
- **View Passbook:** Customers can view their transaction history.
- **Edit Profile:** Customers can update their profile information, including changing their password.

## Technology Stack

The following technologies were used to build the application:

- **JSP (JavaServer Pages):** For the view layer (UI components).
- **Servlet:** For the controller layer (handling requests and routing).
- **JDBC (Java Database Connectivity):** For interacting with the database.
- **MySQL:** The database management system used to store customer, account, and transaction data.
- **HTML/CSS:** For the front-end structure and styling of the web pages.
- **Java:** The core programming language used for business logic and server-side processing.

## Installation and Setup

To run this application on your local machine:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/namratanarkhede/BankAppUsingMVC.git
2. **Set up the database:**
   - Install MySQL, create a new database named `bankdbfinal`. You can do this by logging into the MySQL command line or using a GUI tool like MySQL Workbench.
   - Run the provided SQL scripts to create the required tables (customers, accounts, transactions, etc.).
  ```sql
    USE bankdbfinal;
    CREATE TABLE Admin (
    adminID INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
    );

    CREATE TABLE Customers (
    customerID INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
    );

    CREATE TABLE Accounts (
    accountNumber VARCHAR(20) PRIMARY KEY,
    customerID INT,
    balance DECIMAL(15, 2) NOT NULL,
    FOREIGN KEY (customerID) REFERENCES Customers(customerID)
    );

    CREATE TABLE Transactions (
    transactionID INT PRIMARY KEY AUTO_INCREMENT,
    senderAccount VARCHAR(20),
    receiverAccount VARCHAR(20),
    transactionType ENUM('credit', 'debit', 'transfer') NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    transactionDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (senderAccount) REFERENCES Accounts(accountNumber),
    FOREIGN KEY (receiverAccount) REFERENCES Accounts(accountNumber)
    );
```

3. **Configure the database connection:**
   - Open the DBConnection.java file.
   - Update it with your MySQL credentials:
   ```bash
   String url = "jdbc:mysql://localhost:3306/bankdbfinal";
   String username = "your-username";
   String password = "your-password";

4. **Run the project:**
   - To run the bank application, follow these steps:
   1. **Open the Project:**
      - Navigate to the `web_content` directory and locate the `main.jsp` file.
   2. **Start the Server:**
      - Right-click on `main.jsp`.
      - Select the option `Run on Server` from the context menu.
      - The Tomcat server will start automatically.
    3. **View the Project:**
       - Once the server is running, your default web browser will open the application.
       - You can now interact with the bank application through the web interface.

> **Note:** Ensure that the Tomcat server is properly configured in your IDE (e.g., Eclipse) before running the project.
     
## Demo video
Here is a demo video of the project showcasing the key features and functionalities.
https://drive.google.com/drive/u/1/folders/1NeEI09v5ggrsAdgyT29GvtPTgdJfvqJu


