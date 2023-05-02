/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entite.Category;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class CategoryFxmlController implements Initializable {

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
    private TextField titre;
    
    
    @FXML
    private Button bsave;
    @FXML
    private Button bupdate;
    @FXML
    private TableView<Category> table;
    @FXML
    private TableColumn<Category, Integer> colid;
    @FXML
    private TableColumn<Category, String> coltitre;
   
    private Stage stage;
    @FXML
    private Button bdelete;
    @FXML
    private Label title;
    @FXML
    private AnchorPane AnchorPane2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        ObservableList<Category> categoryl = getCategories() ;
//        category.setItems(categoryl);
 
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
        Category et = table.getSelectionModel().getSelectedItem();
        id.setText(String.valueOf(et.getId()));
        titre.setText(et.getTitre());
        
      
        bsave.setDisable(true);
    }
 
    public ObservableList<Category> getCats() {
        ObservableList<Category> list = FXCollections.observableArrayList();
        String select = "SELECT id,titre from category order by id";
         con =  new DataSource() .getCnx();
         
        try {
            Statement s = con.createStatement();
            rs = s.executeQuery(select);
            
            while (rs.next() ) {
                 //ObservableList<Category> categoryList = FXCollections.observableArrayList();
                Category f = new Category();
                f.setId(rs.getInt("id"));
                f.setTitre(rs.getString("titre"));
            
                System.out.println(f);

                list.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryFxmlController.class.getName())
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
        ObservableList<Category> list = getCats();
        colid.setCellValueFactory(new PropertyValueFactory<Category, Integer>("id"));
        coltitre.setCellValueFactory(new PropertyValueFactory<Category, String>("titre"));
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
        if(titre.getText() == null || titre.getText().trim().isEmpty()){
        Alert fail= new Alert(Alert.AlertType.INFORMATION);
        fail.setHeaderText("Failure");
        fail.setContentText("Please fill the required fields");
        fail.showAndWait();
         } else {
        con = new DataSource().getCnx();
        String insert = "INSERT INTO category (titre)VALUES(?)";
        try {
            st = con.prepareStatement(insert);
            st.setString(1, titre.getText());
          
            st.executeUpdate();
             Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Succes");
        alert.setContentText("Category succesfully created!");
        alert.showAndWait();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryFxmlController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
       
        }
    }
 
    public void delete() {
        con = new DataSource().getCnx();
         boolean confirm = alertConfirmation("Are you sure ?", "are you sure you want to delete the selected category ?");
         if (confirm) {
           String delete = "DELETE FROM category  where id = ?";
        try {
            st = con.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(id.getText()));
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryFxmlController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
         }
      
    }
// 
    private void update() {
         if(  titre.getText() == null ||  titre.getText().trim().isEmpty()){
        Alert fail= new Alert(AlertType.INFORMATION);
        fail.setHeaderText("Failure");
        fail.setContentText("Please fill the required fields");
        fail.showAndWait();
    } else {
        con = new DataSource().getCnx();
        String update
                = "UPDATE category SET titre=? where id =?";
        try {
            st = con.prepareStatement(update);
            st.setString(1, titre.getText());
            st.setInt(2, Integer.parseInt(id.getText()));


            st.executeUpdate();
             Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Succes");
        alert.setContentText("Category succesfully updated!");
        alert.showAndWait();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryFxmlController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }}
 
    void clear() {
        id.setText(null);
        titre.setText(null);
        bsave.setDisable(false);
         table.getItems().clear();
           table.getItems().addAll(getCats());
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

//    @FXML
//    private void openFileChooser(javafx.scene.input.MouseEvent event) {
//           final FileChooser fileChooser = new FileChooser();
//        File file = fileChooser.showOpenDialog(stage);
//                    if (file != null) {
//                        photo.setText(file.getAbsolutePath());
//                    }
//    }

  
    
    
}
