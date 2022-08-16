package edu.uchicago.chakradhar.emailer;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Session;

public class Emailer {

    public static void sendEmail(String body) throws Exception {
        String FROM = "chakradhar@uchicago.edu";
        String FROMNAME = "chakri-from";
        String TO = "chakrispam@gmail.com";
        String SMTP_USERNAME = "AKIASOLZXQJRCDBJVEVK";
        String SMTP_PASSWORD = "BCazHY2lQUbqoG+q1YWu+waZtxkMVqhcuXUv9PC4UkLL";
        String HOST = "email-smtp.us-east-1.amazonaws.com";
        int PORT = 587;
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject("Request for contact from favs App");
        msg.setContent(body, "text/html");

        Transport transport = session.getTransport();
        try {
            System.out.println("Sending...");
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());

        } finally {
            transport.close();
        }

    }
}
