/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entites.Competition;
import Entites.Equipe;
import Services.CompetitionService;
import Services.EquipeService;
import Services.SendSms;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.List;
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
import jfxtras.scene.control.agenda.Agenda;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class BackController implements Initializable {

    @FXML
    private Button btnMenuCompetition;
    @FXML
    private Button btnMenuEquipe;
    @FXML
    private Pane pnFormComp;
    @FXML
    private TextField tfNomComp;
    @FXML
    private TextField tfDescComp;
    @FXML
    private DatePicker tfDebutComp;
    @FXML
    private DatePicker tfFinComp;
    @FXML
    private Label lbTitleAjoutCompetition;
    @FXML
    private Label lbTitleModifyCompetition;
    @FXML
    private Button btnConfCompetition;
    @FXML
    private Pane pnCompetition;
    @FXML
    private TableView<Competition> tvCompetition;
    @FXML
    private TableColumn<Competition, String> colNom;
    @FXML
    private TableColumn<Competition, String> colDesc;
    @FXML
    private TableColumn<Competition, Date> colDebut;
    @FXML
    private TableColumn<Competition, Date> colFin;
    @FXML
    private Button btnAjoutCompetition;
    @FXML
    private Button btnModifyCompetition;
    @FXML
    private Button btnSupprimerCompetition;
    @FXML
    private Pane pnEquipe;
    @FXML
    private TableView<Equipe> tvEquipe;
    @FXML
    private TableColumn<Equipe, String> colNomEquipe;
    @FXML
    private TableColumn<Equipe, String> colDescEquipe;
    @FXML
    private TableColumn<Equipe, String> colCompEquipe;
    @FXML
    private TableColumn<Equipe, Date> colDateEquipe;
    @FXML
    private Button btnAjoutEquipe;
    @FXML
    private Button btnModifyEquipe;
    @FXML
    private Button btnSupprimerEquipe;
    @FXML
    private Pane pnFormEquipe;
    @FXML
    private TextField tfNomEq;
    @FXML
    private TextField tfDescEq;
    @FXML
    private ComboBox<String> tfCompEq;
    @FXML
    private DatePicker tfDateEq;
    @FXML
    private Label lbTitleAjoutEquipe;
    @FXML
    private Label lbTitleModifyEquipe;
    @FXML
    private Button btnConfEq;
    @FXML
    private Label lbIdComp;
    @FXML
    private Label lbidEq;
    @FXML
    private TextField tfRechercher;
    @FXML
    private DatePicker tfDebutFilter;
    @FXML
    private DatePicker tfFinFilter;
    @FXML
    private Button btnFilter;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnMenuAgenda;
    @FXML
    private Pane pnAgenda;
    @FXML
    private Agenda PlanningAgenda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pnCompetition.toFront();
        fnCompShow();
        // TODO
        fnEqShow();
        ObservableList<String> valuesList = FXCollections.observableArrayList();
        CompetitionService sr=new CompetitionService();
        for (Competition comp : sr.Show()) {
            valuesList.add(comp.getNom());
        }
        tfCompEq.setItems(valuesList);
    }    
    
    public void fnCompShow(){
        CompetitionService sr=new CompetitionService();
         ObservableList<Competition> list = FXCollections.observableArrayList(sr.Show());;
    
     colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));       
     colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));        
     colDebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));   
     colFin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));   

        
     tvCompetition.setItems(list);}
    
    
    public void fnEqShow(){
        EquipeService sr=new EquipeService();
         ObservableList<Equipe> list = FXCollections.observableArrayList(sr.Show());;
    
     colNomEquipe.setCellValueFactory(new PropertyValueFactory<>("Nom"));       
     colDescEquipe.setCellValueFactory(new PropertyValueFactory<>("Description"));        
     colCompEquipe.setCellValueFactory(new PropertyValueFactory<>("competition_nom"));   
     colDateEquipe.setCellValueFactory(new PropertyValueFactory<>("date_creation"));   

        
     tvEquipe.setItems(list);
     //Recherche
    tvEquipe.setRowFactory(tv -> new TableRow<Equipe>() {
    @Override
    protected void updateItem(Equipe item, boolean empty) {
        super.updateItem(item, empty);
        
    }
});
      
    FilteredList<Equipe> filteredData = new FilteredList<>(list, b -> true);
		
		tfRechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Event -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Event.getDate_creation().toString().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if(Event.getCompetition_nom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				}else if(Event.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				}else if(Event.getDate_creation().toString().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				}
				     else  
				    	 return false; 
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Equipe> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvEquipe.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvEquipe.setItems(sortedData);}

    @FXML
    private void fnMenuCompetition(ActionEvent event) {
        pnCompetition.toFront();
    }

    @FXML
    private void fnMenuEquipe(ActionEvent event) {
        pnEquipe.toFront();
    }

    @FXML
    private void fnConfCompetition(ActionEvent event) {
        CompetitionService cs=new CompetitionService();
        if(lbTitleAjoutCompetition.isVisible()){
            Competition c =new Competition();
            
            String ERROR_MSG="";
            if("".equals(tfNomComp.getText())){
                ERROR_MSG="Le champs nom de dois pas pas étre null";
                System.out.println();
                System.out.println(ERROR_MSG);
            }
            if("".equals(tfDescComp.getText())){
                ERROR_MSG="Le champs description de dois pas pas étre null";
                System.out.println(ERROR_MSG);
            }
            if(tfDebutComp.getValue()==null){
                ERROR_MSG="Le champs date debut de dois pas pas étre null";
                System.out.println(ERROR_MSG);
            }
            if(tfFinComp==null){
                ERROR_MSG="Le champs date fin de dois pas pas étre null";
                System.out.println(ERROR_MSG);
            }
            if(!ERROR_MSG.equals("Le champs date fin de dois pas pas étre null") && !ERROR_MSG.equals("Le champs date debut de dois pas pas étre null")&& tfFinComp.getValue().isBefore(tfDebutComp.getValue()) ){
                ERROR_MSG="Le champs date fin  dois etre superieure à Le champs date debut";
            }
            if(!"".equals(ERROR_MSG)){
            Stage window = (Stage)pnFormComp.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
                pnFormComp.toFront();
        }}else{
                c.setNom(tfNomComp.getText());
            c.setDescription(tfDescComp.getText());
            c.setDate_debut(Date.valueOf(tfDebutComp.getValue()));
            c.setDate_fin(Date.valueOf(tfFinComp.getValue()));
               cs.add(c); 
               tfNomComp.setText("");
            tfDescComp.setText("");
            tfDebutComp.setValue(null);
            tfFinComp.setValue(null);
            pnCompetition.toFront();
            fnCompShow();
            }
            
            
        }else{
            Competition c =new Competition();
            
            String ERROR_MSG="";
            if("".equals(tfNomComp.getText())){
                ERROR_MSG="Le champs nom de dois pas pas étre null";
            }
            if("".equals(tfDescComp.getText())){
                ERROR_MSG="Le champs description de dois pas pas étre null";
            }
            if(tfDebutComp.getValue()==null){
                ERROR_MSG="Le champs date debut de dois pas pas étre null";
            }
            if(tfFinComp.getValue()==null){
                ERROR_MSG="Le champs date fin de dois pas pas étre null";
            }
            if(!"".equals(ERROR_MSG)){
            Stage window = (Stage)pnFormEquipe.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
                pnFormEquipe.toFront();
        }}else{
                c.setNom(tfNomComp.getText());
            c.setDescription(tfDescComp.getText());
            c.setDate_debut(Date.valueOf(tfDebutComp.getValue()));
            c.setDate_fin(Date.valueOf(tfFinComp.getValue()));
            c.setId(Integer.parseInt(lbIdComp.getText()));
               cs.update(c); 
               
            tfNomComp.setText("");
            tfDescComp.setText("");
            tfDebutComp.setValue(null);
            tfFinComp.setValue(null);
            pnCompetition.toFront();
        fnCompShow();
            }
        }
        
    }

    @FXML
    private void fnSelectedCompetition(MouseEvent event) {
        Competition rec= tvCompetition.getSelectionModel().getSelectedItem();
        lbIdComp.setText(rec.getId()+"");
    }

    @FXML
    private void fnAjoutCompetition(ActionEvent event) {
        pnFormComp.toFront();
        lbTitleAjoutCompetition.setVisible(true);
        lbTitleModifyCompetition.setVisible(false);
    }

    @FXML
    private void fnModifyCompetition(ActionEvent event) {
        pnFormComp.toFront();
        lbTitleAjoutCompetition.setVisible(false);
        lbTitleModifyCompetition.setVisible(true);
        CompetitionService cs=new CompetitionService();
        Competition rec=cs.getById(Integer.parseInt(lbIdComp.getText()));
        tfNomComp.setText(rec.getNom());
        tfDescComp.setText(rec.getDescription());
        tfDebutComp.setValue(rec.getDate_debut().toLocalDate());
        tfFinComp.setValue(rec.getDate_fin().toLocalDate());
    }

    @FXML
    private void fnSupprimerCompetition(ActionEvent event) {
        CompetitionService cs=new CompetitionService();
        cs.Delete(Integer.parseInt(lbIdComp.getText()));
        fnCompShow();
    }

    @FXML
    private void fnSelectedEquipe(MouseEvent event) {
         Equipe rec= tvEquipe.getSelectionModel().getSelectedItem();
        lbidEq.setText(rec.getId()+"");
    }

    @FXML
    private void fnAjoutEquipe(ActionEvent event) {
        pnFormEquipe.toFront();
        lbTitleAjoutEquipe.setVisible(true);
        lbTitleModifyEquipe.setVisible(false);
    }

    @FXML
    private void fnModifyEquipe(ActionEvent event) {
         pnFormEquipe.toFront();
        lbTitleAjoutEquipe.setVisible(false);
        lbTitleModifyEquipe.setVisible(true);
        EquipeService eq =new EquipeService();
        Equipe e=eq.getById(Integer.parseInt(lbidEq.getText()));
        tfNomEq.setText(e.getNom());
        tfDescEq.setText(e.getDescription());
        tfCompEq.setValue(e.getCompetition_nom());
        tfDateEq.setValue(e.getDate_creation().toLocalDate());
        
    }

    @FXML
    private void fnSupprimerEquipe(ActionEvent event) {
        EquipeService cs =new EquipeService();
        cs.Delete(Integer.parseInt(lbidEq.getText()));
        fnEqShow();
    }

    @FXML
    private void fnConfEq(ActionEvent event) {
        EquipeService cs=new EquipeService();
        System.out.println(lbTitleAjoutEquipe.isVisible());
        System.out.println(lbidEq.getText());
        if(lbTitleAjoutEquipe.isVisible()){
            Equipe c =new Equipe();
            
            
            
            String ERROR_MSG="";
            if("".equals(tfNomEq.getText())){
                ERROR_MSG="Le champs nom de dois pas pas étre null";
            }
            if("".equals(tfDescEq.getText())){
                ERROR_MSG="Le champs description de dois pas pas étre null";
            }
            if("".equals(tfCompEq.getValue())){
                ERROR_MSG="Le champs competition de dois pas pas étre null";
            }
            if(tfDateEq.getValue()==null){
                ERROR_MSG="Le champs date de dois pas pas étre null";
            }
            
            if(!"".equals(ERROR_MSG)){
            Stage window = (Stage)pnFormEquipe.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
                pnFormEquipe.toFront();
        }}else{
                c.setNom(tfNomEq.getText());
            c.setDescription(tfDescEq.getText());
            c.setCompetition_id(cs.getCompByName(tfCompEq.getValue()).getId());
            c.setDate_creation(Date.valueOf(tfDateEq.getValue()));
               cs.add(c); 
                SendSms.send("+21628599271","Equpe ajouté avec succées" );
               tfNomEq.setText("");
            tfDescComp.setText("");
            tfCompEq.setValue(null);
            tfDateEq.setValue(null);
            pnEquipe.toFront();
            
        fnEqShow();
            }
            
            
            
            
        }else{
            Equipe c =new Equipe();
           
            String ERROR_MSG="";
            if("".equals(tfNomEq.getText())){
                ERROR_MSG="Le champs nom de dois pas pas étre null";
            }
            if("".equals(tfDescEq.getText())){
                ERROR_MSG="Le champs description de dois pas pas étre null";
            }
            if("".equals(tfCompEq.getValue())){
                ERROR_MSG="Le champs competition de dois pas pas étre null";
            }
            if(tfDateEq.getValue()==null){
                ERROR_MSG="Le champs date de dois pas pas étre null";
            }
            if(!"".equals(ERROR_MSG)){
            Stage window = (Stage)pnFormEquipe.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
                pnFormEquipe.toFront();
        }}else{
                 c.setNom(tfNomEq.getText());
            c.setDescription(tfDescEq.getText());
            c.setCompetition_id(cs.getCompByName(tfCompEq.getValue()).getId());
            c.setDate_creation(Date.valueOf(tfDateEq.getValue()));
            c.setId(Integer.parseInt(lbidEq.getText()));
               cs.update(c); 
               tfNomEq.setText("");
            tfDescComp.setText("");
            tfCompEq.setValue(null);
            tfDateEq.setValue(null);
            pnEquipe.toFront();
            
        fnEqShow();
            }
            
        }
    }

    @FXML
    private void fnFilter(ActionEvent event) {
        CompetitionService cs=new CompetitionService();
        int i=0;
        if(tfDebutFilter.getValue()==null && tfFinFilter.getValue()==null){
            fnCompShow();
        }else if(tfDebutFilter.getValue()!=null && tfFinFilter.getValue()==null){
            CompetitionService sr=new CompetitionService();
         ObservableList<Competition> list = FXCollections.observableArrayList(sr.ShowByDate(Date.valueOf(tfDebutFilter.getValue()), null, 1));;
    
     colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));       
     colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));        
     colDebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));   
     colFin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));   

        
     tvCompetition.setItems(list);
        }else if(tfDebutFilter.getValue()==null && tfFinFilter.getValue()!=null){
            CompetitionService sr=new CompetitionService();
         ObservableList<Competition> list = FXCollections.observableArrayList(sr.ShowByDate( null,Date.valueOf(tfFinFilter.getValue()), 2));;
    
     colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));       
     colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));        
     colDebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));   
     colFin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));   

        
     tvCompetition.setItems(list);
        }else{
            CompetitionService sr=new CompetitionService();
         ObservableList<Competition> list = FXCollections.observableArrayList(sr.ShowByDate( Date.valueOf(tfDebutFilter.getValue()),Date.valueOf(tfFinFilter.getValue()), 3));;
    
     colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));       
     colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));        
     colDebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));   
     colFin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));   

        
     tvCompetition.setItems(list);
        }
    }

    @FXML
    private void fnReset(ActionEvent event) {
        tfDebutFilter.setValue(null);
        tfFinFilter.setValue(null);
        tfDebutFilter.setPromptText("Debut");
        tfFinFilter.setPromptText("Fin");
        fnCompShow();
    }           

    @FXML
    private void fnMenuAgenda(ActionEvent event) {
        pnAgenda.toFront();
        CompetitionService sr=new CompetitionService();
                    PlanningAgenda.appointments().clear();
                    
        List<Competition> plan = sr.Show();

        for(int i=0; i<plan.size();i++) {
            LocalDateTime localDateTime = plan.get(i).getDate_debut().toLocalDate().atTime(8, 00);
            ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
            Calendar calendarDebut = Calendar.getInstance();
            calendarDebut.setTimeInMillis(zonedDateTime.toInstant().toEpochMilli());

            Calendar calendarFin = Calendar.getInstance();
            PlanningAgenda.appointments() ;
            calendarFin.setTimeInMillis(zonedDateTime.toInstant().toEpochMilli());
            Agenda.AppointmentImplLocal app=new Agenda.AppointmentImplLocal();
            app.setStartLocalDateTime(plan.get(i).getDate_debut().toLocalDate().atTime(8, 00));
            app.setEndLocalDateTime(plan.get(i).getDate_fin().toLocalDate().atTime(17, 00));
            app.setDescription(plan.get(i).getNom());
            PlanningAgenda.appointments().add(app);
           
        }
    }
    
}
