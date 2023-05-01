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
public class Evenement {
    public int id;
    public String title,description;
    public Date date_debut,date_fin;
    public float Budget;
    public Evenement() {
    }

    public Evenement(int id, String title, String description, Date date_debut, Date date_fin) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Evenement(String title, String description, Date date_debut, Date date_fin) {
        this.title = title;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return title;
    }

    public void setNom(String nom) {
        this.title = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getBudget() {
        return Budget;
    }

    public void setBudget(float Budget) {
        this.Budget = Budget;
    }
    
    

    @Override
    public String toString() {
        return "Competition{" + "id=" + id + ", nom=" + title + ", description=" + description + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
    
    
}
