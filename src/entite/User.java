/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author aminh
 */
public class User {
      private int id;
    private String email;
    private String nom;
    private String roles;
    private String password;
   private String reset_password;
   private boolean blocked ;
   private String pictureAsString;

    public User() {
    }
    
      // Constructor to set default values for roles and blocked
    public User(String email, String nom, String password) {
        this.email = email;
        this.nom = nom;
        this.roles = "USER ROLE";
        this.password = password;
        this.reset_password = null;
        this.blocked = false;
    }

    public User(int id, String email, String nom, String roles, String password) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.roles = roles;
        this.password = password;
    }

    public User(String email, String nom, String roles, String password) {
        this.email = email;
        this.nom = nom;
        this.roles = roles;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id, String email, String nom) {
        this.id = id;
        this.email = email;
        this.nom = nom;
    }



    public User(int id, String email, String nom, String roles, String password, String reset_password) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.roles = roles;
        this.password = password;
        this.reset_password = reset_password;
    }

    public User(String email, String nom, String roles, String password, String reset_password) {
        this.email = email;
        this.nom = nom;
        this.roles = roles;
        this.password = password;
        this.reset_password = reset_password;
    }

    public User(String reset_password) {
        this.reset_password = reset_password;
    }

    public User(int id, String email, String nom, String roles, String password, String reset_password, boolean blocked, String pictureAsString) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.roles = roles;
        this.password = password;
        this.reset_password = reset_password;
        this.blocked = blocked;
        this.pictureAsString = pictureAsString;
    }

    public User(String email, String nom, String roles, String password, String reset_password, boolean blocked, String pictureAsString) {
        this.email = email;
        this.nom = nom;
        this.roles = roles;
        this.password = password;
        this.reset_password = reset_password;
        this.blocked = blocked;
        this.pictureAsString = pictureAsString;
    }

    public User(int id, String pictureAsString) {
        this.id = id;
        this.pictureAsString = pictureAsString;
    }

    public User(int id, String email, String nom, String pictureAsString) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.pictureAsString = pictureAsString;
    }

    public String getPictureAsString() {
        return pictureAsString;
    }

    public void setPictureAsString(String pictureAsString) {
        this.pictureAsString = pictureAsString;
    }

   


 
    
    

    public String getReset_password() {
        return reset_password;
    }

    public void setReset_password(String reset_password) {
        this.reset_password = reset_password;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    
    
      public int getBlocked() {
        return blocked ? 1 : 0;
    }
    
    

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", nom=" + nom + ", roles=" + roles + ", password=" + password + '}';
    }
    
    
}
