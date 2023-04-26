/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class StatController implements Initializable {
      ReclamationService es = new ReclamationService();
         @FXML
    private PieChart pieChart;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<PieChart.Data> pieChartData;
        try {
            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Reclamation Entraineur", es.EntraineurType()),
                    new PieChart.Data("Reclamation Compettion", es.CompetitionType()),
                    new PieChart.Data("Reclamation Evenement", es.EvenementType())
                  
            );
            pieChart.setData(pieChartData);
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

    
}
