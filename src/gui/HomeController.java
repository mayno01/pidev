/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import gui.MainApp;
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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.UserCRUD;

/**
 * FXML Controller class
 *
 * @author aminh
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnStudents;
    @FXML
    private Button btn_Timetable;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnClasses;
    @FXML
    private Button btnLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
        private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("/home/icons/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        @FXML
    private void user(javafx.event.ActionEvent mouseEvent) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("GererUser.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage popupStage = new Stage();
       popupStage.initModality(Modality.WINDOW_MODAL);
       popupStage.initOwner(((Node) mouseEvent.getSource()).getScene().getWindow());
       popupStage.setScene(scene);
       popupStage.show();
    }
    
    
 @FXML
private void abonment(ActionEvent mouseEvent) throws IOException {
    // Launch the MainApp class
    MainApp mainApp = new MainApp();
    mainApp.start(new Stage());

    // Close the current stage
   
}

            @FXML
    private void event(javafx.event.ActionEvent mouseEvent) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("BackI.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage popupStage = new Stage();
       popupStage.initModality(Modality.WINDOW_MODAL);
       popupStage.initOwner(((Node) mouseEvent.getSource()).getScene().getWindow());
       popupStage.setScene(scene);
       popupStage.show();
    }
            @FXML
    private void equipe(javafx.event.ActionEvent mouseEvent) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Back.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage popupStage = new Stage();
       popupStage.initModality(Modality.WINDOW_MODAL);
       popupStage.initOwner(((Node) mouseEvent.getSource()).getScene().getWindow());
       popupStage.setScene(scene);
       popupStage.show();
    }
            @FXML
    private void formation(javafx.event.ActionEvent mouseEvent) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("FormationFxml.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage popupStage = new Stage();
       popupStage.initModality(Modality.WINDOW_MODAL);
       popupStage.initOwner(((Node) mouseEvent.getSource()).getScene().getWindow());
       popupStage.setScene(scene);
       popupStage.show();
    }
            @FXML
    private void reclamation(javafx.event.ActionEvent mouseEvent) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("DashbordReclamation.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage popupStage = new Stage();
       popupStage.initModality(Modality.WINDOW_MODAL);
       popupStage.initOwner(((Node) mouseEvent.getSource()).getScene().getWindow());
       popupStage.setScene(scene);
       popupStage.show();
    }

    @FXML
    private void logout(ActionEvent event) {
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

    
}
