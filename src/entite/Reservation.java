package entite;


import entite.Abonnement;
import util.RelationObject;

import java.time.LocalDate;

public class Reservation {

    private int id;
    private Abonnement abonnement;
    private RelationObject joueur;
    private RelationObject entraineur;
    private String sujet;
    private LocalDate date;
    private String heure;

    public Reservation(int id, Abonnement abonnement, RelationObject joueur, RelationObject entraineur, String sujet, LocalDate date, String heure) {
        this.id = id;
        this.abonnement = abonnement;
        this.joueur = joueur;
        this.entraineur = entraineur;
        this.sujet = sujet;
        this.date = date;
        this.heure = heure;
    }

    public Reservation(Abonnement abonnement, RelationObject joueur, RelationObject entraineur, String sujet, LocalDate date, String heure) {
        this.abonnement = abonnement;
        this.joueur = joueur;
        this.entraineur = entraineur;
        this.sujet = sujet;
        this.date = date;
        this.heure = heure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    public RelationObject getJoueur() {
        return joueur;
    }

    public void setJoueur(RelationObject joueur) {
        this.joueur = joueur;
    }

    public RelationObject getEntraineur() {
        return entraineur;
    }

    public void setEntraineur(RelationObject entraineur) {
        this.entraineur = entraineur;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }


}