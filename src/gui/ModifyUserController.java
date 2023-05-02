/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.User;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserCRUD;

/**
 * FXML Controller class
 *
 * @author aminh
 */
public class ModifyUserController implements Initializable {

    @FXML
    private TextField emailUser;
    @FXML
    private TextField NomUser;
    
    private User selectedUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


@FXML
private void deleteUser(ActionEvent event) throws IOException {
  

    // Create an alert dialog
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm Deletion");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete this user?");

    // Show the alert and wait for user response
    Optional<ButtonType> result = alert.showAndWait();

    // If the user clicked OK, delete the user and show a success message
    if (result.isPresent() && result.get() == ButtonType.OK) {
        UserCRUD.deleteUser(selectedUser.getId());

        // Show a success message
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText(null);
        successAlert.setContentText("User deleted successfully!");

        // Wait for the user to click OK and then navigate to the GererUser interface
        successAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("GererUser.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}



@FXML
private void updateUser(ActionEvent event) {
 
    String txtemail = emailUser.getText();
    String txtnom = NomUser.getText();
    
   
    User e = new User();
     e.setId(selectedUser.getId());
    e.setEmail(txtemail);
    e.setNom(txtnom);
    UserCRUD.modifierUser(e);

    // create an Alert
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText(null);
    alert.setContentText("User successfully updated.");

    // add event handler to the alert
    alert.setOnCloseRequest(o -> {
        try {
            // load GererUser interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GererUser.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    });

    // show the alert
    alert.showAndWait();
}



   public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
        
        emailUser.setText(selectedUser.getEmail());
        NomUser.setText(selectedUser.getNom());

       
    }


    
}
