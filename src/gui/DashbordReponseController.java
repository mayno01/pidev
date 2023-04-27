/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entite.Pdf;
import entite.Reponse;
import entite.ReponseRec;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import service.ReponseService;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Scale;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DashbordReponseController implements Initializable {
      @FXML
    private TableView<ReponseRec> tabrec;
    @FXML
    private TableColumn<ReponseRec, Integer> id;
    @FXML
    private TableColumn<ReponseRec,String > type;
      @FXML
    private TableColumn<ReponseRec,String > description;
    @FXML
    private TableColumn<ReponseRec, String> date;
     @FXML
    private TableColumn<ReponseRec, String> etat;
        @FXML
    private TableColumn<ReponseRec, String> reponse;
  
    @FXML
    private Button refresh;
    
     @FXML
    private TextField Recherche;
     
      @FXML
    private ComboBox<String> ExporterListee;
     
       @FXML
    private TextField Metier;
       private Timer timer;
private final int DELAY = 500;



     
     
   //  ObservableList<ReponseRec> List = FXCollections.observableArrayList();
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
ObservableList<String> list4 = FXCollections.observableArrayList("PDF", "Excel", "Imprimer");
        ExporterListee.setItems(list4);
    }
        
      @FXML
    private void Actualiser(ActionEvent event) {
         ReponseService rs = new ReponseService();
        ObservableList<ReponseRec> or = FXCollections.observableArrayList(rs.getAll());
       
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
         type.setCellValueFactory(new PropertyValueFactory<>("type"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
         date.setCellValueFactory(new PropertyValueFactory<>("date"));
          etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
           reponse.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        
       
        tabrec.setItems(or);
        ExporterListee.setValue("Exporter");
        
        
    }
    @FXML
    private void Supprimer(ActionEvent event) {
        ReponseService cs = new ReponseService();
        ReponseRec r = (ReponseRec) tabrec.getSelectionModel().getSelectedItem();
        cs.delete(r.getId());
    }
        
       
    
    
    
    
    
    
    
     @FXML
      private void modifier(ActionEvent event) {
       ReponseRec r = tabrec.getSelectionModel().getSelectedItem();
         

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
                         .getResource("EditReponse.fxml"));
        Scene scene=new Scene(loader.load());
        

       EditReponseController ec= loader.getController();
        Stage stageAff=new Stage();
        stageAff.setScene(scene);
        stageAff.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        int as=tabrec.getSelectionModel().getSelectedItem().getId();
      
              String etat = tabrec.getSelectionModel().getSelectedItem().getEtat();
              String reponse = tabrec.getSelectionModel().getSelectedItem().getReponse();
      

        
       
        
       ec.setDAta(tabrec.getSelectionModel().getSelectedItem().getId(),
                tabrec.getSelectionModel().getSelectedItem().getEtat(),
                tabrec.getSelectionModel().getSelectedItem().getReponse()
       );
                 
                 
       
        } catch(IOException ex)
    {
     System.out.println("eer");
}
        }
     
          
    
    }
      
       @FXML
       
       
    public  void Recherche(KeyEvent event) {
        ReponseService re = new ReponseService();
        String chaine = Recherche.getText();
        populateTable(re.chercherReclamation(chaine));
    }
      public void populateTable(ObservableList<ReponseRec> branlist){
       tabrec.setItems(branlist);
   
       }
      
      
      
      /* private void notiff() {
        ReponseService sv = new ReponseService();
        Reponse v = new Reponse();
        String etatv = Metier.getText();
        System.out.println(etatv);
        int y = sv.calculnb((Metier.getText()));
        System.out.println(y);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("attention");
        tray.setMessage("il existe " + y + " reclamation  " + etatv + "");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(2000));
    }
       
@FXML
    private void notif(KeyEvent event) {
          
        notiff();
    
    }*/
      
      
      private void notiff() {
    if (timer != null) {
        timer.cancel(); // Cancel the previous timer if it's still running
    }
    timer = new Timer();
    timer.schedule(new TimerTask() {
        @Override
        public void run() {
            ReponseService sv = new ReponseService();
            Reponse v = new Reponse();
            String etatv = Metier.getText();
           // System.out.println(etatv);
            int y = sv.calculnb((Metier.getText()));
           // System.out.println(y);
            Platform.runLater(() -> {
    TrayNotification tray = new TrayNotification();
    AnimationType type = AnimationType.POPUP;
    tray.setAnimationType(type);
    tray.setTitle("attention");
    tray.setMessage("il existe " + y + " reclamation  " + etatv + "");
    tray.setNotificationType(NotificationType.INFORMATION);
    tray.showAndDismiss(Duration.millis(2000));
});
        }
    }, DELAY);
}

@FXML
private void notif(KeyEvent event) {
    if (event.getCode() == KeyCode.ENTER) {
        notiff();
    } else {
        notiffDelay();
    }
}

private void notiffDelay() {
    if (timer != null) {
        timer.cancel(); // Cancel the previous timer if it's still running
    }
    timer = new Timer();
    timer.schedule(new TimerTask() {
        @Override
        public void run() {
            notiff();
        }
    }, DELAY);
}




/*@FXML
    private void PDF(MouseEvent event) {
                            ReponseRec voy = tabrec.getSelectionModel().getSelectedItem();

        Pdf pd=new Pdf();
        try{
                    pd.GeneratePdf("MesInformations",voy,voy.getId());

            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }*/
   
    
    /* @FXML
    private void Excel(ActionEvent event) throws IOException, SQLException {
        Writer writer = null;
                ReponseService sv = new ReponseService();
                ArrayList<ReponseRec> list = sv.getAll();
         try {
         
            File file = new File("C:\\Users\\DELL\\Downloads\\ConnexionBD3A44 (3)\\ConnexionBD3A44\\src\\gui\\Reprec.csv");
            writer = new BufferedWriter(new FileWriter(file));
            
            for (ReponseRec ev : list) {

                String text = ev.getType()+" | " +ev.getDescription()+ " | " + ev.getDate()+ " | "+ev.getEtat()+" | "+ev.getReponse()+ "\n";
                System.out.println(text);
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            writer.flush();
             writer.close();
             Alert alert= new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("excel");
        alert.setHeaderText(null);
        alert.setContentText("!!!excel exported!!!");
        alert.showAndWait();
        }
    }
    */
    
    public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        node.getTransforms().add(scale);
        
        if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();
                
            }
        }
        node.getTransforms().remove(scale);
        
    }
    
    
    
     @FXML
    private void ImprimerAction(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
                printNode(tabrec);
    }
    
    
     @FXML
    private void ExporterrListe(ActionEvent event) throws IOException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, SQLException {
        String N = (String) ExporterListee.getValue();

        if (N.equals("PDF") ){
                     // ExporterListee.setValue("Exporter");
                  ReponseRec rec = tabrec.getSelectionModel().getSelectedItem();
        
        Pdf pd=new Pdf();
        try{
                    pd.GeneratePdf("mes informations",rec,rec.getId());
                    Alert alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("PDF");
                    alert.setHeaderText(null);
                    alert.setContentText("!!!PDF exported!!!");
                    alert.showAndWait();
            System.out.println("impression done");

        } catch (Exception ex) {
            //Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert= new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText("!!!Selectioner une Reclamation!!!");
                  
                    alert.showAndWait();
                    // ExporterListee.setValue("Exporter");
                   // ExporterListee.getSelectionModel().clearSelection();

            }
        }
        if (N.equals("Excel") ){
             //ExporterListee.setValue("Exporter");

             Writer writer = null;
                ReponseService sv = new ReponseService();
                ArrayList<ReponseRec> list = sv.getAll();
                
                
         try {
           
            File file = new File("C:\\Users\\DELL\\Downloads\\ConnexionBD3A44 (3)\\ConnexionBD3A44\\src\\gui\\Reprec.csv");
            writer = new BufferedWriter(new FileWriter(file));
            
            for (ReponseRec ev : list) {

                String text = ev.getType()+" | " +ev.getDescription()+ " | " + ev.getDate()+ "  | "+ev.getEtat()+" | "+ev.getReponse()+ "\n";
                System.out.println(text);
                writer.write(text);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            // ExporterListee.getSelectionModel().clearSelection();

        }
        finally {
            writer.flush();
             writer.close();
             Alert alert= new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("excel");
        alert.setHeaderText(null);
        alert.setContentText("!!!excel exported!!!");
        alert.showAndWait();
        }
           
        }
        if (N.equals("Imprimer") ){
                               //   ExporterListee.setValue("Exporter");
                            printNode(tabrec);

        }
        
     //  ExporterListee.setValue("Exporter");
     
     
       
        
    }
    
}

    
    
    
    

   
      
      
  
     
     
     
     







    

