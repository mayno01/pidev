/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 * FXML Controller class
 *
 * @author aminh
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private TextField emailU;
    @FXML
    private Button resetButton;
    
        private Connection conn;
    private PreparedStatement pst;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
    
@FXML
private void ResetPass(ActionEvent event) {
    try {
        // get the email address entered by the user
        String email = emailU.getText();

        // check if the email exists in the database
     
        conn = util.DataSource.getInstance().getCnx();
        String sql = "SELECT * FROM user WHERE email=?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();

        // if the email exists, send an email containing a key to reset the password
        if (rs.next()) {
            // Generate a random key for resetting the password
            String key = generateKey(5);

            // Save the key to the database for the user
            saveKeyToDatabase(email, key);

            // Send an email containing the key to the user
            sendResetPasswordEmail(email, key);

            // Open the ResetPassword.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
            Parent root = loader.load();
            ResetPasswordController resetController = loader.getController();
            resetController.setEmail(email);
            resetController.setKey(key);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // close the current window
            Stage currentStage = (Stage) resetButton.getScene().getWindow();
            currentStage.close();
        } else {
            // show an alert if the email doesn't exist
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("The email address you entered does not exist.");
            alert.showAndWait();
        }
    } catch (SQLException | IOException ex) {
        ex.printStackTrace();
    }
}


private String generateKey(int keyLength) {
    String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    StringBuilder keyBuilder = new StringBuilder(keyLength);
    for (int i = 0; i < keyLength; i++) {
        keyBuilder.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
    }
    return keyBuilder.toString();
}

 private void saveKeyToDatabase(String email, String key) {
    try {
        conn = util.DataSource.getInstance().getCnx();
        String sql = "UPDATE user SET reset_token=? WHERE email=?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, key);
        pst.setString(2, email);
        pst.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
 
 
private void sendResetPasswordEmail(String email, String key) {
    final String username = "amine.hamed@esprit.tn";
    final String password = "Esprit9raya";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // added trust

    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
      });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(email));
        message.setSubject("Reset Password Key");
        message.setText("Dear User,"
            + "\n\n Here is your reset password key: " + key);

        Transport.send(message);

        System.out.println("Email sent successfully!");

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}




    
}
