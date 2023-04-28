/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entite.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.UserCRUD;
import java.io.File;
import java.io.FileOutputStream;    
import java.util.stream.Collectors;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * FXML Controller class
 *
 * @author aminh
 */
public class GererUserController implements Initializable {

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> colId;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colNom;


     
        ObservableList<User> obl = FXCollections.observableArrayList();
    @FXML
    private TableColumn<User, Button> colButton;
    @FXML
    private Button exportButton;
    @FXML
    private TextField rech;
private ObservableList<User> userList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          getListUser();
                       table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                // Get selected item
                User selectedUser = table.getSelectionModel().getSelectedItem();
                if (selectedUser != null) {
                    // Open new window for editing the selected row
                    openEditWindows(selectedUser);
                }
            }
        });
                       
                       
                       
                       // Create a filtered list that wraps the userList
    FilteredList<User> filteredList = new FilteredList<>(userList, p -> true);

    // Bind the filtered list to the table
    table.setItems(filteredList);

    // Add a listener to the rech TextField to update the filtered list
    rech.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredList.setPredicate(user -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            if (user.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (user.getNom().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }

            return false;
        });
    });
    }    
    
    
    
    
        
private void openEditWindows(User selectedUser) {
    try {
        // Load the FXML file for the edit window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyUser.fxml"));
        Parent root = loader.load();

        // Get the controller instance
        ModifyUserController controller = loader.getController();

        // Call a method on the controller to pass the selected Event
        controller.setSelectedUser(selectedUser);

        // Create a new scene and stage for the edit window
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit User");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

 
            public void getListUser() {
        UserCRUD cmd = new UserCRUD();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                  colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
     
      // Button cell for like/unlike
colButton.setCellFactory(column -> {
    return new TableCell<User, Button>() {
        final Button blockButton = new Button("Block");
        final Button unblockButton = new Button("Unblock");

        {
            blockButton.setOnAction(event -> {
                User eventObj = getTableView().getItems().get(getIndex());
                eventObj.setBlocked(true);
                cmd.modifierBlocked(eventObj); // Update the event in the database
                setGraphic(unblockButton);
            });

            unblockButton.setOnAction(event -> {
                User eventObj = getTableView().getItems().get(getIndex());
                eventObj.setBlocked(false);
                cmd.modifierBlocked(eventObj); // Update the event in the database
                setGraphic(blockButton);
            });
        }

@Override
protected void updateItem(Button item, boolean empty) {
    super.updateItem(item, empty);
    if (empty) {
        setGraphic(null);
        setText(null);
    } else {
        User eventObj = getTableView().getItems().get(getIndex());
        if (eventObj.isBlocked()) {
            unblockButton.setOnAction(event -> {
                eventObj.setBlocked(false);
                cmd.modifierBlocked(eventObj); // Update the event in the database
                setGraphic(blockButton);
            });
            setGraphic(unblockButton);
        } else {
            blockButton.setOnAction(event -> {
                eventObj.setBlocked(true);
                cmd.modifierBlocked(eventObj); // Update the event in the database
                setGraphic(unblockButton);
            });
            setGraphic(blockButton);
        }
        setText(null);
        
        // Set default state based on boolean value in the database
        if (eventObj.isBlocked()) {
            setGraphic(unblockButton);
        } else {
            setGraphic(blockButton);
        }
    }
}


    };
});

  
    
        
        // Display row data
       List<User> listUser = cmd.consulterUser();
    userList.addAll(listUser);
    }

    @FXML
    private void getDetailCmd(MouseEvent event) {
        
        
    }

    @FXML
    private void refrechdon(ActionEvent event) {
        
                 UserCRUD cmd = new UserCRUD();
    List<User> listUser = cmd.consulterUser();

    // Update the table view with the new data
    userList.clear();
    userList.addAll(listUser);
    }



  @FXML
private void LogoutUser(ActionEvent event) {
    // Call the logoutUser method to clear the stored user information
     UserCRUD cmd = new UserCRUD();
    UserCRUD.logoutUser();

    // You can also redirect the user to the login page or perform any other necessary actions
    // For example, assuming you have a Login.fxml file in your resources folder:
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@FXML
private void exportToExcel(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();

    // Set extension filter for Excel files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show save file dialog
    File file = fileChooser.showSaveDialog(exportButton.getScene().getWindow());
 ObservableList<User> observableList = table.getItems();
    if (file != null) {
        try {
            // Create new Excel workbook and sheet
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Users");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Email");
            headerRow.createCell(2).setCellValue("Nom");

            // Add data rows
            for (int i = 0; i < observableList.size(); i++) {
                Row row = sheet.createRow(i+1);
                row.createCell(0).setCellValue(observableList.get(i).getId());
                row.createCell(1).setCellValue(observableList.get(i).getEmail());
                row.createCell(2).setCellValue(observableList.get(i).getNom());
            }

            // Write to file
            FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Successful");
            alert.setHeaderText(null);
            alert.setContentText("Users exported to Excel file.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Export Failed");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while exporting users to Excel file.");
            alert.showAndWait();
        }
    }
}

    @FXML
    private void stat(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("StatUser.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage popupStage = new Stage();
       popupStage.initModality(Modality.WINDOW_MODAL);
       popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
       popupStage.setScene(scene);
       popupStage.show();
    }

    



    
}
