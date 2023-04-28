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
public class Sponsor {
    public int id,event_id;
    public String nom,event_nom;
    public float budget;

    public Sponsor() {
    }

    public Sponsor(int id, int competition_id, String nom,float budget) {
        this.id = id;
        this.event_id = competition_id;
        this.nom = nom;
        this.budget=budget;
    }

    public Sponsor(int competition_id, String nom,float budget) {
        this.event_id = competition_id;
        this.nom = nom;
        this.budget=budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return event_id;
    }

    public void setEventId(int event_id) {
        this.event_id = event_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public String getEvent_nom() {
        return event_nom;
    }

    public void setEvent_nom(String event_nom) {
        this.event_nom = event_nom;
    }

    
    @Override
    public String toString() {
        return "Sponsor{" + "id=" + id + ", event_id=" + event_id + ", nom=" + nom + ", budget=" + budget + '}';
    }
    
    
    
    
}
