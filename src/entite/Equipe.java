/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;

/**
 *
 * @author houss
 */
public class Equipe {
    public int id,competition_id;
    public String nom,description,competition_nom;
    public Date date_creation;

    public Equipe() {
    }

    public Equipe(int id, int competition_id, String nom, String description, Date date_creation) {
        this.id = id;
        this.competition_id = competition_id;
        this.nom = nom;
        this.description = description;
        this.date_creation = date_creation;
    }

    public Equipe(int competition_id, String nom, String description, Date date_creation) {
        this.competition_id = competition_id;
        this.nom = nom;
        this.description = description;
        this.date_creation = date_creation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(int competition_id) {
        this.competition_id = competition_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public String getCompetition_nom() {
        return competition_nom;
    }

    public void setCompetition_nom(String competition_nom) {
        this.competition_nom = competition_nom;
    }
    
    

    @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", competition_id=" + competition_id + ", nom=" + nom + ", description=" + description + ", date_creation=" + date_creation + '}';
    }
    
    
}
