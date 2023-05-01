/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.User;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.UserCRUD;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author aminh
 */
public class UserFaceController implements Initializable {
         private Connection conn;
    private PreparedStatement pst;
   


    @FXML
    private Button uploadButton;
    @FXML
    private Label selectedFileLabel;
    
 
    
     private String email;
    @FXML
    private ImageView imageView;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label userEmailLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            User user = UserCRUD.getUserByEmaile(email);

            if (user != null) {
                userNameLabel.setText(user.getNom());
                userEmailLabel.setText(user.getEmail());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
@FXML
private void handleUploadButtonAction(ActionEvent event) {

    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select an image file");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    File selectedFile = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());
    if (selectedFile != null) {
        selectedFileLabel.setText(selectedFile.getName());
        try {
            conn = util.DataSource.getInstance().getCnx();
            String sql = "UPDATE user SET picture = ? WHERE email = ?";
            pst = conn.prepareStatement(sql);

            byte[] fileBytes = Files.readAllBytes(selectedFile.toPath());
            String pictureAsString = Base64.getEncoder().encodeToString(fileBytes); // Encode the byte array as a String
            pst.setString(1, pictureAsString);
            pst.setString(2, email);
            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Image uploaded successfully!");
                alert.showAndWait();

                // refresh the interface by reloading the user information
                setEmail(email);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to upload image.");
                alert.showAndWait();
            }

        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while uploading the image.");
            alert.showAndWait();
        }
    }
}


public void setEmail(String email) {
    this.email = email;

    try  {
        conn = DataSource.getInstance().getCnx();
        String sql = "SELECT picture, nom FROM user WHERE email = ?";
        pst = conn.prepareStatement(sql);  
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String pictureAsString = rs.getString("picture");
            if (pictureAsString != null && !pictureAsString.isEmpty()) {
                byte[] imageData = Base64.getDecoder().decode(pictureAsString); // Decode the String as a byte array
                Image image = new Image(new ByteArrayInputStream(imageData));
                imageView.setImage(image);
                imageView.setFitWidth(77); // set the desired width
                imageView.setFitHeight(77); // set the desired height
            }
            String name = rs.getString("nom");
            userNameLabel.setText(name);
            userEmailLabel.setText(email);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("An error occurred while retrieving user information.");
        alert.showAndWait();
    }
}






    @FXML
    private void logoutr(ActionEvent event) {
         // Call the logoutUser method to clear the stored user information
     UserCRUD cmd = new UserCRUD();
    UserCRUD.logoutUser();

    // You can also redirect the user to the login page or perform any other necessary actions
    // For example, assuming you have a Login.fxml file in your resources folder:
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void frontend(ActionEvent event) {
        MainBack.getInstance().loadBack();
        
    }
}