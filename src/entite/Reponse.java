/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author DELL
 */
public class Reponse {
      private int id;
      private int reclamation_id;
      private String etat;
      private String reponse;
    

 public Reponse() {
    }
 
public Reponse(int id,int reclamation_id, String etat, String reponse ) {
        this.reclamation_id = reclamation_id;
        this.etat=etat;
        this.reponse=reponse;
    }

public Reponse(int reclamation_id, String etat, String reponse ) {
        this.reclamation_id = reclamation_id;
        this.etat=etat;
        this.reponse=reponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  public Reponse(int id) {
        this.id = id;
    }
  
   public Reponse(String etat, String reponse) {
        this.etat = etat;
        this.reponse = reponse;
      
       
    }

    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public int getReclamation_id() {
        return reclamation_id;
    }

    public String getEtat() {
        return etat;
    }

    public String getReponse() {
        return reponse;
    }

    @Override
    public String toString() {
        return "Reponse{" + "reclamation_id=" + reclamation_id + ", etat=" + etat + ", reponse=" + reponse + '}';
    }




}

    
     

