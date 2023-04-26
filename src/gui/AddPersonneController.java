/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Personne;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.PersonneService;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class AddPersonneController implements Initializable {

    @FXML
    private TextField txtn;
    @FXML
    private TextField txtp;
    @FXML
    private TextField txta;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Personne p1=new 
        Personne(txtn.getText(), txtp.getText(), Integer.parseInt(txta.getText()));
        PersonneService ps=new PersonneService();
        ps.insertPst(p1);
        
        FXMLLoader loader= new FXMLLoader(getClass()
                .getResource("Recuperation.fxml"));
        
        
        Parent root=loader.load();
        txtn.getScene().setRoot(root);
        RecuperationController recup=loader.getController();
        recup.setLabel(txtn.getText());
        
        
    }
    
}
