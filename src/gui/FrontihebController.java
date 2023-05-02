/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Evenement;
import entite.Sponsor;
import service.EvenementService;
import service.SendEmailWthImage;
import service.SponsorService;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class FrontihebController implements Initializable {

    @FXML
    private TableColumn<Evenement, String> colNom;
    @FXML
    private TableColumn<Evenement, String> colDesc;
    @FXML
    private TableColumn<Evenement, Date> colDebut;
    @FXML
    private TableColumn<Evenement, Date> colFin;
    @FXML
    private Button btnMenuEvent;
    @FXML
    private Pane pnSponsor;
    @FXML
    private TableView<Sponsor> tvSponsor;
    @FXML
    private TableColumn<Sponsor, String> colNomSponsor;
    @FXML
    private Button btnSupprimerSponsor;
    @FXML
    private Label lbidSp;
    @FXML
    private Pane pnFormSponsor;
    @FXML
    private TextField tfNomSponsor;
    @FXML
    private TextField tfBudgetSp;
    private ComboBox<String> tfEventSp;
    @FXML
    private Button btnConfSp;
    @FXML
    private Pane pnEvent;
    @FXML
    private TableView<Evenement> tvEvent;
    @FXML
    private Label lbIdEvent;
    private Pane pnFormEvent;
    private TextField tfNomEvent;
    private TextField tfDescEvent;
    private DatePicker tfDebutEvent;
    private DatePicker tfFinEvent;
    private Label lbTitleAjoutEvent;
    private Label lbTitleModifyEvent;
    @FXML
    private Label lbTitleAjoutSp;
    @FXML
    private Label lbTitleModifySp;
    @FXML
    private TableColumn<Sponsor, String> colEventSponsor;
    @FXML
    private TableColumn<Sponsor, Float> colBudgetSponsor;
    @FXML
    private TextField tfRechercher;
    @FXML
    private TableColumn<Evenement, Float> colBudget;
    @FXML
    private Button btnAjtSponsor;
    @FXML
    private Button btnAfcSponsor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pnEvent.toFront();
        fnEventShow();
        // TODO
        ObservableList<String> valuesList = FXCollections.observableArrayList();
        EvenementService sr=new EvenementService();
        for (Evenement comp : sr.Show()) {
            valuesList.add(comp.getNom());
        }
    }    
    
    public void fnEventShow(){
        EvenementService sr=new EvenementService();
         ObservableList<Evenement> list = FXCollections.observableArrayList(sr.Show());;
    
     colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));       
     colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));        
     colDebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));   
     colFin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));   
     colBudget.setCellValueFactory(new PropertyValueFactory<>("Budget"));  

        
     tvEvent.setItems(list);}
    
    
    public void fnSpShow(){
        SponsorService sr=new SponsorService();
         ObservableList<Sponsor> list = FXCollections.observableArrayList(sr.ShowByEvent(Integer.parseInt(lbIdEvent.getText())));;
    
     colNomSponsor.setCellValueFactory(new PropertyValueFactory<>("Nom"));       
     colBudgetSponsor.setCellValueFactory(new PropertyValueFactory<>("budget"));        
     colEventSponsor.setCellValueFactory(new PropertyValueFactory<>("event_nom"));   

        
     tvSponsor.setItems(list);
    tvSponsor.setRowFactory(tv -> new TableRow<Sponsor>() {
    @Override
    protected void updateItem(Sponsor item, boolean empty) {
        super.updateItem(item, empty);
        
    }
});
      
    FilteredList<Sponsor> filteredData = new FilteredList<>(list, b -> true);
		
		tfRechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Event -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				 if(Event.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
                                 } else if(Event.getEvent_nom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
                                 } 
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Sponsor> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvSponsor.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvSponsor.setItems(sortedData);}




    @FXML
    private void fnSelectedCompetition(MouseEvent event) {
        Evenement rec= tvEvent.getSelectionModel().getSelectedItem();
        lbIdEvent.setText(rec.getId()+"");
        btnAfcSponsor.setVisible(true);
        btnAjtSponsor.setVisible(true);
    }




    @FXML
    private void fnSelectedEquipe(MouseEvent event) {
         Sponsor rec= tvSponsor.getSelectionModel().getSelectedItem();
        lbidSp.setText(rec.getId()+"");
    }




    @FXML
    private void fnMenuEvent(ActionEvent event) {
         pnEvent.toFront();
         fnEventShow();
         btnAfcSponsor.setVisible(false);
        btnAjtSponsor.setVisible(false);
    }

    private void fnMeuSponsor(ActionEvent event) {
        pnSponsor.toFront();
        fnSpShow();
    }

    private void fnAjoutSponsor(ActionEvent event) {
         pnFormSponsor.toFront();
        lbTitleAjoutSp.setVisible(true);
        lbTitleModifySp.setVisible(false);
        
    }

    private void fnModifySponsor(ActionEvent event) {
         pnFormSponsor.toFront();
        lbTitleAjoutSp.setVisible(false);
        lbTitleModifySp.setVisible(true);
        SponsorService eq =new SponsorService();
        Sponsor e=eq.getById(Integer.parseInt(lbidSp.getText()));
        tfNomSponsor.setText(e.getNom());
        tfBudgetSp.setText(e.getBudget()+"");
        tfEventSp.setValue(e.getEvent_nom());
    }

    @FXML
    private void fnSupprimerSponsor(ActionEvent event) {
        SponsorService cs =new SponsorService();
        cs.Delete(Integer.parseInt(lbidSp.getText()));
        fnSpShow();
    }

    @FXML
    private void fnConfSp(ActionEvent event) {
        SponsorService cs=new SponsorService();
        System.out.println(lbTitleAjoutSp.isVisible());
        System.out.println(lbidSp.getText());
        if(!lbTitleAjoutSp.isVisible()){
            Sponsor c =new Sponsor();
            
            
            
            String ERROR_MSG="";
            if("".equals(tfNomSponsor.getText())){
                ERROR_MSG="Le champs nom de dois pas pas étre null";
            }
            if("".equals(tfBudgetSp.getText())){
                ERROR_MSG="Le champs budget de dois pas pas étre null";
            }
            if(!"".equals(ERROR_MSG)){
            Stage window = (Stage)pnFormSponsor.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
                pnFormSponsor.toFront();
        }}else{
                service.EvenementService es=new EvenementService();
                c.setNom(tfNomSponsor.getText());
            c.setBudget(Float.parseFloat(tfBudgetSp.getText()));
            c.setEvent_id(es.getById(Integer.parseInt(lbIdEvent.getText())).getId());
               cs.add(c); 
                new SendEmailWthImage("mail","Sponsore","Sponsore a été ajouté avec succées");
               tfNomSponsor.setText("");
            tfBudgetSp.setText("");
            tfEventSp.setValue(null);
            pnSponsor.toFront();
            
        fnSpShow();
            }
            
            
            
            
        }
        
    }

    private void fnAjoutEvent(ActionEvent event) {
        pnFormEvent.toFront();
        lbTitleAjoutEvent.setVisible(true);
        lbTitleModifyEvent.setVisible(false);
    }

    private void fnModifyEvent(ActionEvent event) {
         pnFormEvent.toFront();
        lbTitleAjoutEvent.setVisible(false);
        lbTitleModifyEvent.setVisible(true);
        EvenementService cs=new EvenementService();
        Evenement rec=cs.getById(Integer.parseInt(lbIdEvent.getText()));
        tfNomEvent.setText(rec.getNom());
        tfDescEvent.setText(rec.getDescription());
        tfDebutEvent.setValue(rec.getDate_debut().toLocalDate());
        tfFinEvent.setValue(rec.getDate_fin().toLocalDate());
    }

    private void fnSupprimerEvent(ActionEvent event) {
        EvenementService cs=new EvenementService();
        cs.Delete(Integer.parseInt(lbIdEvent.getText()));
        fnEventShow();
    }

    private void fnConfEvent(ActionEvent event) {
        EvenementService cs=new EvenementService();
        if(lbTitleAjoutEvent.isVisible()){
            Evenement c =new Evenement();
            
            String ERROR_MSG="";
            if("".equals(tfNomEvent.getText())){
                ERROR_MSG="Le champs nom de dois pas pas étre null";
                System.out.println();
                System.out.println(ERROR_MSG);
            }
            if("".equals(tfDescEvent.getText())){
                ERROR_MSG="Le champs description de dois pas pas étre null";
                System.out.println(ERROR_MSG);
            }
            if(tfDebutEvent.getValue()==null){
                ERROR_MSG="Le champs date debut de dois pas pas étre null";
                System.out.println(ERROR_MSG);
            }
            if(tfFinEvent.getValue()==null){
                ERROR_MSG="Le champs date fin de dois pas pas étre null";
                System.out.println(ERROR_MSG);
            }
            if(!ERROR_MSG.equals("Le champs date fin de dois pas pas étre null") && !ERROR_MSG.equals("Le champs date debut de dois pas pas étre null")&& tfFinEvent.getValue().isBefore(tfDebutEvent.getValue()) ){
                ERROR_MSG="Le champs date fin  dois etre superieure à Le champs date debut";
            }
            if(!"".equals(ERROR_MSG)){
            Stage window = (Stage)pnFormEvent.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
                pnFormEvent.toFront();
        }}else{
                c.setNom(tfNomEvent.getText());
            c.setDescription(tfDescEvent.getText());
            c.setDate_debut(Date.valueOf(tfDebutEvent.getValue()));
            c.setDate_fin(Date.valueOf(tfFinEvent.getValue()));
               cs.add(c); 
               tfNomEvent.setText("");
            tfDescEvent.setText("");
            tfDebutEvent.setValue(null);
            tfFinEvent.setValue(null);
            pnEvent.toFront();
            fnEventShow();
            ObservableList<String> valuesList = FXCollections.observableArrayList();
        EvenementService sr=new EvenementService();
        for (Evenement comp : sr.Show()) {
            valuesList.add(comp.getNom());
        }
        tfEventSp.setItems(valuesList);
            }
            
            
        }else{
            Evenement c =new Evenement();
            
            String ERROR_MSG="";
            if("".equals(tfNomEvent.getText())){
                ERROR_MSG="Le champs nom de dois pas pas étre null";
                System.out.println();
                System.out.println(ERROR_MSG);
            }
            if("".equals(tfDescEvent.getText())){
                ERROR_MSG="Le champs description de dois pas pas étre null";
                System.out.println(ERROR_MSG);
            }
            if(tfDebutEvent.getValue()==null){
                ERROR_MSG="Le champs date debut de dois pas pas étre null";
                System.out.println(ERROR_MSG);
            }
            if(tfFinEvent.getValue()==null){
                ERROR_MSG="Le champs date fin de dois pas pas étre null";
                System.out.println(ERROR_MSG);
            }if(!ERROR_MSG.equals("Le champs date fin de dois pas pas étre null") && !ERROR_MSG.equals("Le champs date debut de dois pas pas étre null")&& tfFinEvent.getValue().isBefore(tfDebutEvent.getValue()) ){
                ERROR_MSG="Le champs date fin  dois etre superieure à Le champs date debut";
            }
            if(!"".equals(ERROR_MSG)){
            Stage window = (Stage)pnFormEvent.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
                pnFormEvent.toFront();
        }}else{
                c.setNom(tfNomEvent.getText());
            c.setDescription(tfDescEvent.getText());
            c.setDate_debut(Date.valueOf(tfDebutEvent.getValue()));
            c.setDate_fin(Date.valueOf(tfFinEvent.getValue()));
             c.setId(Integer.parseInt(lbIdEvent.getText()));
               cs.update(c); 
               tfNomEvent.setText("");
            tfDescEvent.setText("");
            tfDebutEvent.setValue(null);
            tfFinEvent.setValue(null);
            pnEvent.toFront();
            fnEventShow();
            ObservableList<String> valuesList = FXCollections.observableArrayList();
        EvenementService sr=new EvenementService();
        for (Evenement comp : sr.Show()) {
            valuesList.add(comp.getNom());
        }
        tfEventSp.setItems(valuesList);
            }

        }
    }

    @FXML
    private void fnAjtSponsor(ActionEvent event) {
        pnFormSponsor.toFront();
        lbTitleAjoutSp.setVisible(true);
        lbTitleModifySp.setVisible(false);
    }

    @FXML
    private void fnAfcSponsor(ActionEvent event) {
        pnSponsor.toFront();
        fnSpShow();
        
    }
    

    
}
