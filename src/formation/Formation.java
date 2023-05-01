/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package formation;

import gui.FormationFxmlController;
import gui.FormationFxmlController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;



/**
 *
 * @author tarek
 */
public class Formation extends Application {
    @Override
    public void start(Stage primaryStage) {
Button button1= new Button("Go to Formation Form");
Button button2= new Button("Go to Category Form ");
Button button3= new Button("Go to Statistics Form ");
  button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
  button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
  button3.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
  
button1.setOnAction(e->{
  try {
          //      FXMLLoader fxmlLoader = new FXMLLoader();
     //   fxmlLoader.setLocation(getClass().getResource("Fxml/FormationFxml.fxml"));
 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/FormationFxml.fxml"));
 
 
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Formation Management");
                      Scene scene = new Scene(fxmlLoader.load(), 1280, 800);

                 FormationFxmlController myController = fxmlLoader.getController();
         myController.chooseFile(primaryStage);

        stage.setScene(scene);

        stage.show();
        

        
            // Hide this current window (if this is what you want)
          //  ((Node)(e.getSource())).getScene().getWindow().hide();
        }
        catch (IOException err) {
            System.out.print(err);
        }
  
  


});
button2.setOnAction(e -> {
        try {
             //   FXMLLoader fxmlLoader = new FXMLLoader();
     //   fxmlLoader.setLocation(getClass().getResource("Fxml/CategoryFxml.fxml"));
 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/CategoryFxml.fxml"));
         Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        Stage stage = new Stage();
                stage.setResizable(false);

        stage.setTitle("Category Management");
        stage.setScene(scene);
        stage.show();
            // Hide this current window (if this is what you want)
          //  ((Node)(e.getSource())).getScene().getWindow().hide();
        }
        catch (IOException err) {
        }
    });  

button3.setOnAction(e -> {
        try {
             //   FXMLLoader fxmlLoader = new FXMLLoader();
     //   fxmlLoader.setLocation(getClass().getResource("Fxml/CategoryFxml.fxml"));
 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/StatisticFxml.fxml"));
         Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        Stage stage = new Stage();
                stage.setResizable(false);

        stage.setTitle("Statistics");
        stage.setScene(scene);
        stage.show();
            // Hide this current window (if this is what you want)
          //  ((Node)(e.getSource())).getScene().getWindow().hide();
        }
        catch (IOException err) {
        }
    }); 

Label label2= new Label("Welcome");
label2.setFont(new Font("Arial", 30));
VBox layout2= new VBox(30);
layout2.setStyle("-fx-background-image: url('../../images/welcome.jpg');" +
        "-fx-background-repeat: stretch;" +
        "-fx-background-size: 222 222;" +
        "-fx-background-position: center center;");
layout2.setAlignment(Pos.CENTER);
layout2.getChildren().addAll(label2, button1,button2,button3);
Scene scene= new Scene(layout2,640,480);

primaryStage.setScene(scene);
primaryStage.show();
        

//Scene 2
        
        


     
        //primaryStage.setTitle("Javafx");
        
        //primaryStage.setScene(scene);
        
       }
//Scene 1


// 
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
