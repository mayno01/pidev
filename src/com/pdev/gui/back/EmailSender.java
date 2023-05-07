package com.pdev.gui.back;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    
    public static void sendEmail() {
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("amin.hamed45@gmail.com"));
            message.setSubject("New Article Added");

            String body = "A new Article has been added.";

            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
