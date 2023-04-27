/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Reclamation;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class RecuperationController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ListView<Reclamation> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ReclamationService  ps=new ReclamationService();
        List<Reclamation> listp=ps.readAll();
        
        ObservableList<Reclamation> obs=
                FXCollections.observableArrayList(listp);
        list.setItems(obs);
    }    

    public void setLabel(String label) {
        this.label.setText(label);
    }
    
    
}
