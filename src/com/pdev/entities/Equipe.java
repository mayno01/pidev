/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdev.entities;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Equipe {
    int id ;
    String nom,description ; 
    Date date_creation;

    public Equipe() {
    }

    public Equipe(int id, String nom, String description, Date date_creation) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date_creation = date_creation;
    }

    public Equipe(String nom, String description, Date date_creation) {
        this.nom = nom;
        this.description = description;
        this.date_creation = date_creation;
    }

    public Equipe(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Equipe(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDatecreation() {
        return date_creation;
    }

    public void setDatecreation(Date date_creation) {
        this.date_creation = date_creation;
    }

    
}
