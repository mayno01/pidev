/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ChoiceBox;
import java.time.LocalDate;
import service.ReclamationService;
import java.time.ZoneId;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AddReclamationController implements Initializable {
   

    @FXML
    private ChoiceBox<String> ttype;        
    @FXML
    private TextArea desc;
    @FXML
    private DatePicker date;
    
    @FXML
    void date(ActionEvent evt ){
        
    }
   
    @FXML
    private Button btn;
    
     
   
     private String[] type ={"Reclamation Entraineur" , "Reclamation Competition","Reclamation Evenement"};

    /**
     * Initializes the controller class.
     */
    //@Override
    public void initialize(URL url, ResourceBundle rb) {
        
     
         ttype.getItems().addAll(type);
         ttype.setOnAction(this::getType);
         
        
       
    } 
    public void getType(ActionEvent event){
        String T = ttype.getValue();
        
    }
    
    //convertion
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
     public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
     
     
     
     public Boolean ValidateFields() {
        if (ttype.getValue()==null | desc.getText().isEmpty() | date.getValue()==null ) {
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
          String t = ttype.getValue().toString();
            String d=desc.getText();
            LocalDate datee = date.getValue();
          
          
        
        if( ValidateFields()==true){
      
       // System.out.println(datee);
     
        
           ReclamationService rs=new ReclamationService();
          Date sd = convertToDateViaSqlDate(datee);
                //  System.out.println(sd);

          Reclamation r1=new Reclamation(t, d, sd);
    
            
       
        rs.insertPst(r1);
        
       Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText("Reclamation added with succes");
        alert.showAndWait();
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("DashbordReclamation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();}
    }
    
     
        
    
      
    

 @FXML
    private void cancelButton(ActionEvent event) throws IOException {
       javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene sceneview = new Scene(tableview);
       
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
    //to clear textfields:
    
 @FXML
    private void clear(ActionEvent event) throws IOException{
        ttype.setValue(null);
       desc.clear();

        date.setValue(null);
     

    }
    
       @FXML
    private void afficher(ActionEvent event) throws IOException {
         javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("DashbordReclamation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
}

