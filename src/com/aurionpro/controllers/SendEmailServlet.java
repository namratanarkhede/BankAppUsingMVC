package com.aurionpro.controllers;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String recipientEmail = request.getParameter("email");
        String username = request.getParameter("username");
        String subject = "Your Account Information";
        String body = "Dear " + username + ",\n\n" +
                      "Here is your account information:\n" +
                      "Username: " + username + "\n" +
                      "Please contact support if you have any questions.\n\n" +
                      "Best regards,\n" +
                      "Bank Admin";

        // Set up the mail server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP server
        properties.put("mail.smtp.port", "587"); // Replace with your SMTP port
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Authenticate the mail session
        Session mailSession = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("20102106.namratanarkhede@gmail.com", "Namrata10@"); // Replace with your email and password
            }
        });

        try {
            // Create a new email message
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("20102106.namratanarkhede@gmail.com")); // Replace with your email
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            // Send the email
            Transport.send(message);

            // Redirect to a success page or message
            response.sendRedirect("viewCustomer.jsp?success=Email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
            response.sendRedirect("viewCustomer.jsp?error=Failed to send email");
        }
    }
}
