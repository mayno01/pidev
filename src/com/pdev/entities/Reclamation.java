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
public class Reclamation {
    int id;
    String type;
    String description;
    Date date ;

    public Reclamation() {
    }

    public Reclamation(int id, String type, String description, Date date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public Reclamation(String type, String description, Date date) {
        this.type = type;
        this.description = description;
        this.date = date;
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
    
    
    
}
