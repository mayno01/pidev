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
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class EditReclamationController implements Initializable {
      @FXML
    private ChoiceBox<String> type;        
    @FXML
    private TextArea description;
    @FXML
    private DatePicker date;
    
    @FXML
    void date(ActionEvent evt ){
        
    }
   
    @FXML
    private Button send;
      @FXML
    private Button cancel;
      Reclamation r;
Reclamation rv=new Reclamation();
      
        private String[] tyype ={"Reclamation Entraineur" , "Reclamation Competition","Reclamation Evenement"};


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         type.getItems().addAll(tyype);
         type.setOnAction(this::getType);
         
     
        


    }    
        public void getType(ActionEvent event){
        String T = type.getValue();
        
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
     public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
     
      public Boolean ValidateFields() {
        if (type.getValue()==null | description.getText().isEmpty() | date.getValue()==null ) {
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
    private void save(ActionEvent event) throws IOException {
           if (rv == null) {

            System.out.println("choisir une reclamation");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modify Reclamation");
            alert.setHeaderText(null);
            alert.setContentText("your review is not modified !");

            alert.showAndWait();
        }else {
              if ( ValidateFields()==true) {
              rv.setType(type.getValue());
             rv.setDescription(description.getText());
              LocalDate datee = date.getValue();
               Date sd = convertToDateViaSqlDate(datee);
            rv.setDate(sd);
          
             ReclamationService es = new ReclamationService();
             try{
             es.update(rv);
             System.out.println("ok");}
             catch (Exception ex) {
            System.out.println(ex.getMessage());
        
            System.out.println("Modification terminé");}
              
             
           
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification terminée avec succès.");
        alert.setHeaderText(null);
    alert.setContentText("Your reclamation  has been modified.");
        alert.showAndWait();
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("DashbordReclamation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();}
      
    }
    }
    
       void setDAta(int id, String t, String d , Date dt) {
       LocalDate sd = convertToLocalDateViaSqlDate(dt);
       rv.setId(id);
       type.setValue(t);
       description.setText(d);
       date.setValue(sd);
       
    
    
      
    }
     
    @FXML
    private void Return(ActionEvent event) throws IOException{
        type.setValue(null);
       description.clear();

        date.setValue(null);
     

    }
    
     
    
}
