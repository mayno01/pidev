/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import connexion.Connexion;
import entite.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeString.search;
import service.ReclamationService;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DashbordReclamationController implements Initializable {
     @FXML
    private TableView<Reclamation> tabrec;
    @FXML
    private TableColumn<Reclamation, Integer> id;
    @FXML
    private TableColumn<Reclamation,String > type;
      @FXML
    private TableColumn<Reclamation,String > description;
    @FXML
    private TableColumn<Reclamation, String> date;
     @FXML
    private TableColumn<Reclamation, String> etat;
  
    @FXML
    private Button refresh;
    @FXML
    private Button delete;
    @FXML
    private Button cancel;
     @FXML
    private ComboBox<String> Trie;
      @FXML
    private Button mailen;
      
        @FXML
    private Button sms;
        
  @FXML
    private Button stat;
    
     
     
      private Connection connection = null;
        ObservableList<Reclamation> List = FXCollections.observableArrayList();
          ResultSet resultSet = null;
            private Statement ste;
          PreparedStatement preparedStatement = null;
          String req = null;
             Connection conn = DataSource.getInstance().getCnx();
             
            // private final static int rowsPerPage = 3;
              
   /* Reclamation u = new Reclamation();
  
    List listp = new ArrayList();
    ReclamationService sp = new ReclamationService();
 */
   // public ObservableList<Reclamation> list = FXCollections.observableArrayList(sp.readAll());
             
     /* public void views() throws SQLException {  
      
        listp = sp.readAll();
        ObservableList<Reclamation> l = FXCollections.observableArrayList(listp);  
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
       
        System.out.println("Perfect Saw !");
        tabrec.setEditable(true);
        tabrec.setItems(l);
  }*/

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ReclamationService rs = new ReclamationService();
        ObservableList<Reclamation> or = FXCollections.observableArrayList(rs.readAll());
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
         type.setCellValueFactory(new PropertyValueFactory<>("type"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
         date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
       
        tabrec.setItems(or);
       
        
         ObservableList<String> list = FXCollections.observableArrayList("Reclamation Entraineur", "Reclamation Competition", "Reclamation Evenement");
        Trie.setItems(list);
        /* try {
           views();
         pagination.setPageFactory(this::createPage);
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        //Modifier Starts Here
        /* tabrec.setOnKeyReleased((KeyEvent e) -> {
             if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) {
                 
                 Reclamation rowData = tabrec.getSelectionModel().getSelectedItem();
                 /**
                  * fill the fields with the selected data *
                  */
                 //Event et = ComTitre.getSelectionModel().getSelectedItem();
                 // LocalDate df= Updatec.getValue();
                 
                 
                
            // }
             //file:/C:/Users/EZZEDINE/Desktop/PIDev_Java/src/tunisiagottalent/ui/img/blog.png
       /* tabrec.setOnMouseClicked((MouseEvent x)->{
                   int selectedIndex = tabrec.getSelectionModel().getSelectedIndex();
                   if (selectedIndex!=-1) {                     
                    Reclamation pi = (Reclamation) tabrec.getSelectionModel().getSelectedItem();                        
                                   
                         }                
                    });
        });*/
        
     
        

                 
                 
        
        
        
    }    
     
    @FXML
    private void Actualiser(ActionEvent event) {
         ReclamationService rs = new ReclamationService();
        ObservableList<Reclamation> or = FXCollections.observableArrayList(rs.readAll());
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
         type.setCellValueFactory(new PropertyValueFactory<>("type"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
         date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
       
        tabrec.setItems(or);
    }
    
    
     @FXML
    private void Supprimer(ActionEvent event) {
        ReclamationService cs = new ReclamationService();
        Reclamation r = (Reclamation) tabrec.getSelectionModel().getSelectedItem();
        cs.delete(r.getId());
    }
    
    
     @FXML
      private void modifier(ActionEvent event) {
        Reclamation r = tabrec.getSelectionModel().getSelectedItem();
         

if(r==null){
        
           System.out.println("Aucun reclamation séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun reclamation séléctionné");

            alert.showAndWait();
     
        }else {
          try {   
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("EditReclamation.fxml"));
        Scene scene=new Scene(loader.load());
        

       EditReclamationController ec= loader.getController();
        Stage stageAff=new Stage();
        stageAff.setScene(scene);
        stageAff.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        int as=tabrec.getSelectionModel().getSelectedItem().getId();
        String type = tabrec.getSelectionModel().getSelectedItem().getType();
           String description = tabrec.getSelectionModel().getSelectedItem().getDescription();
              Date date = tabrec.getSelectionModel().getSelectedItem().getDate();
      

        
       
        
        ec.setDAta(tabrec.getSelectionModel().getSelectedItem().getId(),
                tabrec.getSelectionModel().getSelectedItem().getType(),
                tabrec.getSelectionModel().getSelectedItem().getDescription()
                ,date);
                 
                 
       
        } catch(IOException ex)
    {
     System.out.println("eer");
}
        }
     
          
    
    }
    
    
      @FXML
    private void retour(ActionEvent event) throws IOException {
         javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AddReponse.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
     @FXML
    private void repondre(ActionEvent event) throws IOException {
        
        Reclamation r = tabrec.getSelectionModel().getSelectedItem();
         

if(r==null){
        
           System.out.println("Aucun reclamation séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun reclamation séléctionné");

            alert.showAndWait();
     
        }
else {
    try {
        
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AddReponse.fxml"));
        Scene scene=new Scene(loader.load());
        AddReponseController ec= loader.getController();
        Stage stageAff=new Stage();
        stageAff.setScene(scene);
        stageAff.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        
        ec.setDAta(tabrec.getSelectionModel().getSelectedItem().getId());
                 
              
         
    }
    catch(IOException ex)
    {
     System.out.println("eer");
}
    
}
}
 private void clear() {
        id.setText(null);
        type.setText(null);
        description.setText(null);
        date.setText(null);
      

    }
    
    
    
    
       @FXML
    private void Trie(ActionEvent event) {
        String N = (String) Trie.getValue();
        if (N == "Reclamation Entraineur") {
            try {
                List.clear();

                req = "select * from reclamation WHERE type = 'Reclamation Entraineur' ORDER BY Id ASC ";
                 preparedStatement = conn.prepareStatement(req);
             resultSet  = preparedStatement.executeQuery();


                while (resultSet.next()) {
                    List.add(new Reclamation(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            resultSet.getString("type"),
                           
                            resultSet.getDate("date")
                           
                    ));
                    tabrec.setItems(List);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

       
        if (N == "Reclamation Competition") {
            try {
                List.clear();

                req = "select * from reclamation WHERE type = 'Reclamation Competition' ORDER BY date DESC";
                 preparedStatement = conn.prepareStatement(req);
             resultSet  = preparedStatement.executeQuery();


                while (resultSet.next()) {
                    List.add(new Reclamation(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            resultSet.getString("type"),
                           
                            resultSet.getDate("date")
                           
                    ));
                    tabrec.setItems(List);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
           if (N == "Reclamation Evenement") {
            try {
                List.clear();

                req = "select * from reclamation WHERE type = 'Reclamation Evenement'ORDER BY date DESC ,Id ASC";
                 preparedStatement = conn.prepareStatement(req);
             resultSet  = preparedStatement.executeQuery();


                while (resultSet.next()) {
                    List.add(new Reclamation(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            resultSet.getString("type"),
                            resultSet.getDate("date")
                           
                    ));
                    tabrec.setItems(List);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
     /*private Node createPage(int pageIndex) {
		int fromIndex = pageIndex * rowsPerPage;
		int toIndex = Math.min(fromIndex + rowsPerPage, list.size());
		tabrec.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));

		return tabrec;
	}*/
    
    
        @FXML
     private void Mail(ActionEvent event) throws IOException {
    
   
           javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("mail.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
 
       
        
}
     
     
     
           @FXML
     private void SMSS(ActionEvent event) throws IOException {
    
   
           javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("SMS.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
 
       
     }
     
     
     
      
           @FXML
     private void statt(ActionEvent event) throws IOException {
    
   
           javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Stat.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
 
       
     }
}
