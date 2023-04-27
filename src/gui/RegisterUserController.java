/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserCRUD;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author aminh
 */
public class RegisterUserController implements Initializable {

    @FXML
    private TextField emailU;
    @FXML
    private TextField nomU;
    @FXML
    private PasswordField PasswordU;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

@FXML
private void RegisterU(ActionEvent event) throws IOException {
    String email = emailU.getText();
    String nom = nomU.getText();
    String password = PasswordU.getText();

    // Create a new instance of the LoginController class
    UserCRUD u = new UserCRUD();

    // Validate the user input
    String errorMessage = "";
    if (email.trim().isEmpty()) {
        errorMessage += "Email field can't be blank.\n";
    }
    if (!email.matches("[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}")) {
        errorMessage += "Invalid email format.\n";
    }
    if (password.length() < 6) {
        errorMessage += "Password must be at least 6 characters long.\n";
    }

    if (errorMessage.isEmpty()) {
        // Hash the password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Attempt to register the user
        boolean userCreated = u.registerUser(email, nom, hashedPassword);

        if (userCreated) {
            // Registration successful, show a success message
            System.out.println("User registration successful!");

            // Load the FXML file for the GererUser page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent gererUserParent = loader.load();

            // Set the GererUser page as the root node of the scene
            Scene gererUserScene = new Scene(gererUserParent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(gererUserScene);
            stage.show();
        } else {
            // Registration failed, show an error message
            System.out.println("User registration failed!");
        }
    } else {
        // Show error popup window
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}




    @FXML
    private void LoginU(ActionEvent event) throws IOException {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();
    }
    
}
