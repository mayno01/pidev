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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author aminh
 */
public class ResetPasswordController implements Initializable {

    @FXML
    private PasswordField newpass;
    @FXML
    private PasswordField confirmerpass;
    @FXML
    private Button resetButton;
    
        private Connection conn;
    private PreparedStatement pst;
     private String email;
     private String key;
    @FXML
    private TextField resetkey;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

@FXML
private void resetpass(ActionEvent event) {
    try {
        // get the new password entered by the user
        String password = newpass.getText();

        // check if the password and confirm password fields match
        if (!password.equals(confirmerpass.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match. Please try again.");
            alert.showAndWait();
            return;
        }

        // check if the key entered matches the key passed from the previous window
        String resetKey = resetkey.getText();
        if (!resetKey.equals(key)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("The reset key you entered is invalid. Please try again.");
            alert.showAndWait();
            return;
        }

        // hash the new password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // update the password in the database
        conn = util.DataSource.getInstance().getCnx();
        String sql = "UPDATE user SET password=? WHERE email=?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, hashedPassword);
        pst.setString(2, email);
        pst.executeUpdate();

        // show a success message in an alert dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Password has been successfully updated.");
        alert.showAndWait();
        

        // Load the login interface FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();

        // Set the new scene to the current window
        Stage currentStage = (Stage) newpass.getScene().getWindow();
        Scene scene = new Scene(root);
        currentStage.setScene(scene);

        
    } catch (SQLException | IOException ex) {
        ex.printStackTrace();
    }
}




    // public method to set the email address from the previous window
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setKey(String key) {
    this.key = key;
}


    
}
