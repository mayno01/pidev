/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

/**
 *
 * @author tarek
 */
public class Category {
      private int id;
    private String titre;

    public Category() {
    }

    public Category(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public Category(String titre) {
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return this.getTitre();
    }
    
    
    
    
}
