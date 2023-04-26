/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Reclamation;
import entite.Reponse;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ReponseService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AddReponseController implements Initializable {
     
     @FXML
    private ChoiceBox<String> etat;        
    @FXML
    private TextArea reponse;
     @FXML
    private Button envoyer;
     
     private String[] etatt ={"non traitee" , "traitee"};
     
     Reponse rv=new Reponse();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         etat.getItems().addAll(etatt);
         etat.setOnAction(this::getEtat);
         
    }    
    public void getEtat(ActionEvent event){
        String T = etat.getValue();
        
    }
        
     
     public Boolean ValidateFields() {
        if ( etat.getValue()==null | reponse.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Into The Fields");
            alert.showAndWait();
            return false;
        }

        return true;
     }
    
    
    @FXML
    private void ajouter(ActionEvent event) throws IOException  {
         
            
             String et = etat.getValue().toString();
            
             String re=reponse.getText();
           
          
          
        
        if( ValidateFields()==true){
      
     
     
        
           ReponseService rs=new ReponseService();
         
          Reponse r1=new Reponse(rv.getId(), et, re);
    
            
       
        rs.insertPst(r1);
        
        
          Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText("Reponse added with succes");
        alert.showAndWait();
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("DashbordReponse.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();}
    
}
    
     void setDAta(int id) {
      
       rv.setId(id);
      
       
       
    
     }
     
     
        @FXML
    private void afficher(ActionEvent event) throws IOException {
         javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("DashbordReponse.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
    @FXML
    private void clear(ActionEvent event) throws IOException{
        etat.setValue(null);
       reponse.clear();


    }
    }
