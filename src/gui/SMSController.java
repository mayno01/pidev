/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.lookups.v1.PhoneNumber;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.Smstwilio;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class SMSController implements Initializable {

    /**
     * Initializes the controller class.
     */
    

    // Remplacez ces valeurs par votre compte Twilio SID et Auth Token
public static final String ACCOUNT_SID = "AC1c7c8d5286dc4f70661a9fec4fe860ea";
public static final String AUTH_TOKEN = "648ed3c81d7e25a1446b5d7b426b62c9";

// Remplacez cette valeur par le numéro Twilio que vous avez acheté
public static final String TWILIO_NUMBER = "+16813213673";

private Smstwilio twilioSMS;

@FXML
private TextField phoneTextField;
@FXML
private TextField messageTextField;
@FXML
private Button send;

@Override
public void initialize(URL url, ResourceBundle rb) {
    twilioSMS = new Smstwilio(ACCOUNT_SID, AUTH_TOKEN, TWILIO_NUMBER);
}

@FXML
public void handleSendButtonAction(ActionEvent event) {
    String recipientPhoneNumber = phoneTextField.getText();
    String message = messageTextField.getText();
    if (!recipientPhoneNumber.matches("^\\+?[1-9]\\d{1,14}$")) {
        Alert alert = new Alert(AlertType.ERROR, "Invalid phone number format: " + recipientPhoneNumber);
        alert.showAndWait();
        return;
    }
    try {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
   Message sms = Message.creator(new com.twilio.type.PhoneNumber(recipientPhoneNumber), new com.twilio.type.PhoneNumber(TWILIO_NUMBER), message).create();
        System.out.println(sms.getSid());
        Alert alert = new Alert(AlertType.INFORMATION, "SMS sent successfully");
        alert.showAndWait();
    } catch (Exception e) {
        Alert alert = new Alert(AlertType.ERROR, e.getMessage());
        alert.showAndWait();
    }
}

}
    
    

