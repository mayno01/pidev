/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;
import service.UserCRUD;

/**
 * FXML Controller class
 *
 * @author aminh
 */
public class LoginController implements Initializable {

    @FXML
    private TextField emailuser;
    @FXML
    private PasswordField passworduser;
    @FXML
    private Button idlogin;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

@FXML
private void Login(ActionEvent event) throws SQLException {
    String mail = emailuser.getText();
    String password = passworduser.getText();
    if ((!mail.isEmpty()) && (!password.isEmpty())) {

        UserCRUD u = new UserCRUD();
        Pair<Integer, String> loginResult = u.loginUser(mail, password);

        if (loginResult.getKey() == -2) {
            // User is blocked
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Your account is blocked!");
            alert.showAndWait();
        } else if (loginResult.getKey() != -1) {
            // User is not blocked and logged in successfully
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Login successful!");
            alert.showAndWait();
            String role = loginResult.getValue();
            if (role.equals("UserFace.fxml")) {
                // Load the user screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource(role));
                Parent root;
                Stage stage;
                try {
                    root = loader.load();
                    stage = (Stage) idlogin.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    UserFaceController controller = loader.getController();
                    controller.setEmail(mail);
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            } else if (role.equals("GererUser.fxml")) {
                // Load the admin screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource(role));
                Parent root;
                Stage stage;
                try {
                    root = loader.load();
                    stage = (Stage) idlogin.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        } else {
            // Login failed
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login failed!");
            alert.showAndWait();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Fill in the form to continue!");
        alert.showAndWait();
    }
}





    @FXML
    private void registerUser(ActionEvent event) throws IOException {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterUser.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();
    }

    @FXML
    private void ForgetPass(ActionEvent event) throws IOException {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgetPassword.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();
    }
    
}
