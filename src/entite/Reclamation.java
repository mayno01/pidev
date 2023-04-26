/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;
import java.sql.Date;

import java.time.LocalDate;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;



/**
 *
 * @author DELL
 */
public class Reclamation {
      private int id;
      private String type;
      private String description;
      private Date date;
     


   /* public Reclamation(ChoiceBox<String> ttype, TextArea desc, DatePicker date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
       
     public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public Reclamation() {
    }

    public Reclamation(int id, String type, String description , Date date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date = date;
     
    }
    
    public Reclamation(int id) {
        this.id = id;
    }

    public Reclamation(String type, String description,Date date) {
        this.type = type;
        this.description = description;
        this.date = date;

       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", type=" + type + ", description=" + description + ", date=" + date + '}';
    }

  

   

   

    
    
    
    
    
}
 
      
    

