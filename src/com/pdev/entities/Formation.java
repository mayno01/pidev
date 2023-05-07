/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.entities;

/**
 *
 * @author aminh
 */
public class Formation {
    int id ,prix;
    String nom ,description ,meet;

    public Formation() {
    }

    public Formation(int id, int prix, String nom, String description, String meet) {
        this.id = id;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.meet = meet;
    }

    public Formation(int prix, String nom, String description, String meet) {
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.meet = meet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
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

    public String getMeet() {
        return meet;
    }

    public void setMeet(String meet) {
        this.meet = meet;
    }
    
    
    
    
}
