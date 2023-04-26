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
public class ReponseRec {
     private int id;
      private String type;
       private String description;
      private Date date;
     
      private String etat;
      private String reponse;

    @Override
    public String toString() {
        return "ReponseRec{" + "id=" + id + ", type=" + type + ", description=" + description + ", date=" + date + ", etat=" + etat + ", reponse=" + reponse + '}';
    }

  

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

   
    public String getEtat() {
        return etat;
    }

    public String getReponse() {
        return reponse;
    }
     

    

    public ReponseRec(int id, String type, String description, Date date, String etat, String reponse) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.reponse = reponse;
    }
     public ReponseRec() {
    }
 
    
      
      
    
    
}
