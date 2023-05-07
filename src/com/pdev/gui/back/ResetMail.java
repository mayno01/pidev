package com.pdev.gui.back;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class ResetMail {
    
    public static void sendPassword(String email) {
        final String username = "amine.hamed@esprit.tn";
        final String password = "Esprit9raya";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("amine.hamed@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Password Reset Request");

            String body = "Your new password is 'changeMe'.";

            message.setText(body);

            Transport.send(message);

            System.out.println("Password reset email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
       
        }
    }
}
