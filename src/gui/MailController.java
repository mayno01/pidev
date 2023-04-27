/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.io.IOException;


import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 * FXML Controller class
 *
 * @author DELL
 */
public class MailController implements Initializable {
    
     @FXML
    private Button send;
    @FXML
    private Button retour;
    @FXML
    private TextArea areaText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
     public Boolean ValidateFields() {
        if (areaText.getText().isEmpty() ) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Your field is empty");
            alert.showAndWait();
            return false;
        }

        return true;

    } 
     
      /* @FXML
    private void Send(ActionEvent event) throws MessagingException {
        if(ValidateFields()){
            String messageContent = areaText.getText();
           String messageSubject = "Test Email Subject";
          sendMail(messageContent, messageSubject);
      
    
      Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Your message has been sent successfully. ");
            alert.showAndWait();}
        
    }*/
    
    
     @FXML
    private void Return(ActionEvent event) throws IOException {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("DashbordReclamation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
    
    
     public  void sendMail(String c, String s) throws MessagingException, javax.mail.MessagingException{
    Properties proprietes=new Properties();
    proprietes.put("mail.smtp.auth","true");
    proprietes.put("mail.smtp.starttls.enable","true");
    proprietes.put("mail.smtp.host","smtp.gmail.com");
    proprietes.put("mail.smtp.port","587");
      String myAccountMail="ibtihellebenyahmed@gmail.com";
      String password="pibxwgbrzasmtmkn";
      Session session=Session.getInstance(proprietes, new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication(){
      return new PasswordAuthentication(myAccountMail, password);}
});
      
       
        Message message= new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountMail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ibtihellebenyahmed@gmail.com"));
            message.setText(c);
            message.setText(s);
        Transport.send(message);
        
        
    }
     
     
       @FXML
    private void Send(ActionEvent event) throws MessagingException, javax.mail.MessagingException {
        if(ValidateFields()){
            String messageContent = areaText.getText();
           String messageSubject = areaText.getText();
          sendMail(messageContent, messageSubject);
      
    
      Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Your message has been sent successfully. ");
            alert.showAndWait();}
        
    }
    
}
