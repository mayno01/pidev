/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entite.CFormation;
import entite.Category;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static java.util.Objects.nonNull;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import util.DataSource;
import util.MailSender;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class FormationFxmlController implements Initializable {

    /**
     * Initializes the controller class.
     */
     PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;
    private Label label;
    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField photo;
    @FXML
    private TextField meet;
    @FXML
    private TextField  prix;
    @FXML
    private ComboBox<Category> category;
    @FXML
    private Button bsave;
    @FXML
    private Button bupdate;
    @FXML
    private TableView<CFormation> table;
    @FXML
    private TableColumn<CFormation, Integer> colid;
    @FXML
    private TableColumn<CFormation, String> colnom;
    @FXML
    private TableColumn<CFormation, String> coldescription;
    @FXML
    private TableColumn<CFormation, String> colphoto;
    @FXML
    private TableColumn<CFormation, String> colprix;
    @FXML
    private TableColumn<CFormation, String> colmeet;
    @FXML
    private TableColumn<CFormation, String> colcategory;
    
    private Stage stage;
    @FXML
    private Button bdelete;
    @FXML
    private Label title;
    @FXML
    private AnchorPane AnchorPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Category> categoryl = getCategories() ;
        category.setItems(categoryl);
        title.setFont(new Font("Arial", 30));
 
      
         // TODO
        affiche();
    }
    
    public List<String> getCategory(){
        ObservableList<Category> list =  getCategories();
      List<String> listNom =   list.stream().map(item->item.getTitre()).collect(Collectors.toList());
      return listNom;
    }
    
    @FXML
    private void tablehandleButtonAction(javafx.scene.input.MouseEvent event) {
        CFormation et = table.getSelectionModel().getSelectedItem();
        id.setText(String.valueOf(et.getId()));
        nom.setText(et.getNom());
        description.setText(et.getDescription());
        photo.setText(et.getPhoto());
        prix.setText(String.valueOf(et.getPrix()));
        meet.setText(et.getMeet());
        category.getSelectionModel().select(et.getCategory());

        bsave.setDisable(true);
    }
 
    public ObservableList<CFormation> getFormation() {
        ObservableList<CFormation> list = FXCollections.observableArrayList();
        String select = "SELECT id, nom, description, meet, photo, prix, category_id from formation order by id";
         con =  new DataSource().getCnx();
         
        try {
            Statement s = con.createStatement();
            rs = s.executeQuery(select);
            
            while (rs.next() ) {
                 ObservableList<Category> categoryList = FXCollections.observableArrayList();
                CFormation f = new CFormation();
                f.setId(rs.getInt("id"));
                f.setNom(rs.getString("nom"));
                f.setDescription(rs.getString("description"));
                f.setMeet(rs.getString("meet"));
                f.setPrix(rs.getInt("prix"));
                f.setPhoto(rs.getString("photo"));
                int cat_id = rs.getInt("category_id");
                Category cat = new Category();
                cat.setId(cat_id);
                String sql = "select * from category where id = ?";
                try
                {
                    PreparedStatement stm = con.prepareStatement(sql);
                       stm.setString(1, String.valueOf(cat_id));
                    ResultSet   result = stm.executeQuery();
                       while(result.next())
                       {
                           Category categoryItem = new Category();
                           categoryItem.setTitre(result.getString("titre"));
                           categoryItem.setId(result.getInt("id"));
                           categoryList.add(categoryItem);
                       }
                }
                 catch (SQLException ex) {
                   Logger.getLogger(FormationFxmlController.class.getName())
                    .log(Level.SEVERE, null, ex);
                  }
                
     

                f.setCategory(categoryList.get(0));
                
                              System.out.println(f);

                list.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormationFxmlController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;
 
    }
    
     public ObservableList<Category> getCategories() {
        ObservableList<Category> list = FXCollections.observableArrayList();
 
        String select = "SELECT * from category";
         con =  new DataSource().getCnx();
        try {
            Statement s = con.createStatement();
            rs = s.executeQuery(select);
            while (rs.next()) {
                Category g = new Category();
                g.setId(rs.getInt("id"));
                g.setTitre(rs.getString("titre"));

                list.add(g);
            }
        } catch (SQLException ex) {
         
        }
        return list;
 
    }
// 
    public void affiche() {
        ObservableList<CFormation> list = getFormation();
        colid.setCellValueFactory(new PropertyValueFactory<CFormation, Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<CFormation, String>("nom"));
        coldescription.setCellValueFactory(new PropertyValueFactory<CFormation, String>("description"));
        colphoto.setCellValueFactory(new PropertyValueFactory<CFormation, String>("photo"));
        colprix.setCellValueFactory(new PropertyValueFactory<CFormation, String>("prix"));
        colcategory.setCellValueFactory(new PropertyValueFactory<CFormation, String>("category"));
        colmeet.setCellValueFactory(new PropertyValueFactory<CFormation, String>("meet"));
        table.setItems(list);
 
    }
    
      public static boolean alertConfirmation(String header, String text) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setHeaderText(header);
        dialog.setContentText(text);
        dialog.setResizable(true);
        dialog.getDialogPane().setPrefSize(350, 200);
        final Optional<ButtonType> result = dialog.showAndWait();
        return result.get() == ButtonType.OK;
    }
 
    private void insert() {
        
         if(  nom.getText() == null || description.getText() == null || meet.getText() == null ||  photo.getText() == null ||  prix.getText() == null || nom.getText().trim().isEmpty() || description.getText().trim().isEmpty() || category.getSelectionModel().getSelectedItem().toString().trim().isEmpty() || meet.getText().trim().isEmpty() ||  photo.getText().trim().isEmpty() ||  prix.getText().trim().isEmpty() ){
        Alert fail= new Alert(AlertType.INFORMATION);
        fail.setHeaderText("Failure");
        fail.setContentText("Please fill the required fields");
        fail.showAndWait();
    } else {
              con = new DataSource().getCnx();
        String insert = "INSERT INTO formation (nom,description,category_id,meet,photo,prix )VALUES(?,?,?,?,?,?)";
        try {
            st = con.prepareStatement(insert);
            st.setString(1, nom.getText());
            
            st.setString(2, description.getText());
            st.setInt(3, category.getSelectionModel().getSelectedItem().getId());
            st.setString(4, meet.getText());
            st.setString(5, photo.getText());
            st.setInt(6, Integer.parseInt(prix.getText()));


            st.executeUpdate();
         
              try {
                  
              MailSender.sendPOST();
         } catch (IOException ex) {
             Logger.getLogger(FormationFxmlController.class.getName()).log(Level.SEVERE, null, ex);
         }
        } catch (SQLException ex) {
            Logger.getLogger(FormationFxmlController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
       
    }
       
    }
 
    public void delete() {
        con = new DataSource().getCnx();
         boolean confirm = alertConfirmation("Are you sure ?", "are you sure you want to delete this formation ?");
         if (confirm) {
           String delete = "DELETE FROM formation  where id = ?";
        try {
            st = con.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(id.getText()));
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(FormationFxmlController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
         }
      
    }
// 
    private void update() {
         if(  nom.getText() == null || description.getText() == null || category.getSelectionModel().getSelectedItem().toString() == null || meet.getText() == null ||  photo.getText() == null ||  prix.getText() == null || nom.getText().trim().isEmpty() || description.getText().trim().isEmpty() || category.getSelectionModel().getSelectedItem().toString().trim().isEmpty() || meet.getText().trim().isEmpty() ||  photo.getText().trim().isEmpty() ||  prix.getText().trim().isEmpty() ){
        Alert fail= new Alert(AlertType.INFORMATION);
        fail.setHeaderText("Failure");
        fail.setContentText("Please fill the required fields");
        fail.showAndWait();
    } else { 
        con = new DataSource().getCnx();
        String update
                = "UPDATE formation SET nom=?,description=?,category_id=?,meet=?,photo=?,prix=? where id =?";
        try {
            st = con.prepareStatement(update);
            st.setString(1, nom.getText());
            st.setString(2, description.getText());
            st.setInt(3, category.getValue().getId());
            st.setString(4, meet.getText());
            st.setString(5, photo.getText());
            st.setString(6, prix.getText());
            st.setInt(7, Integer.parseInt(id.getText()));


            st.executeUpdate();
               Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Succes");
        alert.setContentText("Formation succesfully updated!");
        alert.showAndWait();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(FormationFxmlController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }}
 
    void clear() {
        id.setText(null);
        nom.setText(null);
        description.setText(null);
        meet.setText(null);
        prix.setText(null);
        photo.setText(null);
        category.getSelectionModel().clearSelection();
        category.setValue(null);
        bsave.setDisable(false);
         table.getItems().clear();
           table.getItems().addAll(getFormation());
    }
// 
    @FXML
    private void saveEvent(ActionEvent event) {
        insert();
       
        clear();
    }
 
    @FXML
    private void updateEvent(ActionEvent event) {
        update();
        clear();
        bsave.setDisable(false);
    }
 
    @FXML
    private void deleteEvent(ActionEvent event) {
        delete();
        clear();
    }
 
    @FXML
    private void clearEvent(ActionEvent event) {
        clear();
    }
  
   public void chooseFile(Stage stage){
    this.stage = stage;
   }
   
    @FXML
    private void openFileChooser(javafx.scene.input.MouseEvent event) {
           final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        photo.setText(file.getAbsolutePath());
                    }
    }

   
}
