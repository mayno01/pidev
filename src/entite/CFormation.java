/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

import java.util.Objects;

/**
 *
 * @author tarek
 */
public class CFormation {
     private int id;
    private int id_category;
    private String nom;
    private String description;
    private String photo;
    private String meet;
    private int prix;
    private Category category;
    private double prix_total;

    public CFormation() {
    }

    public CFormation(int id, String nom, String description, String photo, String meet, int prix, Category category) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.meet = meet;
        this.prix = prix;
        this.category = category;
    }

    public CFormation(int id, int id_category, String nom, String description, String photo, String meet, int prix, Category category) {
        this.id = id;
        this.id_category = id_category;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.meet = meet;
        this.prix = prix;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", id_category=" + id_category + ", nom=" + nom + ", description=" + description + ", photo=" + photo + ", meet=" + meet + ", prix=" + prix + ", category=" + category + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMeet() {
        return meet;
    }

    public void setMeet(String meet) {
        this.meet = meet;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(double prix_total) {
        this.prix_total = prix_total;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
        hash = 43 * hash + this.id_category;
        hash = 43 * hash + Objects.hashCode(this.nom);
        hash = 43 * hash + Objects.hashCode(this.description);
        hash = 43 * hash + Objects.hashCode(this.photo);
        hash = 43 * hash + Objects.hashCode(this.meet);
        hash = 43 * hash + this.prix;
        hash = 43 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CFormation other = (CFormation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_category != other.id_category) {
            return false;
        }
        if (this.prix != other.prix) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.meet, other.meet)) {
            return false;
        }
        return Objects.equals(this.category, other.category);
    }

   
}
    

