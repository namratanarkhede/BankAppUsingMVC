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

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); 
        properties.put("mail.smtp.port", "587"); 
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session mailSession = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("email", "password"); // Replace with your email and password
            }
        });

        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("email")); // Replace with your email
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            response.sendRedirect("viewCustomer.jsp?success=Email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
            response.sendRedirect("viewCustomer.jsp?error=Failed to send email");
        }
    }
}
