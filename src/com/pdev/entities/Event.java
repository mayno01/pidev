/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.entities;

import java.util.Date;

/**
 *
 * @author aminh
 */
public class Event {
    
        private int id;
    private String nom;
    private Date DateDebut , DateFin;

    public Event() {
    }

    public Event(int id, String nom, Date DateDebut, Date DateFin) {
        this.id = id;
        this.nom = nom;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }

    public Event(String nom, Date DateDebut, Date DateFin) {
        this.nom = nom;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }
    
    
    
}
