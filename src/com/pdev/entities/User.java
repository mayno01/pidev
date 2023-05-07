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
public class User {
    int id ;
    String email , nom , password;

    public User() {
    }

    public User(int id, String email, String nom, String password) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.password = password;
    }

    public User(String email, String nom, String password) {
        this.email = email;
        this.nom = nom;
        this.password = password;
    }

    public User(String nom, String password) {
        this.nom = nom;
        this.password = password;
    }

    public User(int id, String email, String nom) {
        this.id = id;
        this.email = email;
        this.nom = nom;
    }
 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
