/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import util.DataSource;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author aminh
 */
public class UserCRUD {
    
    
    
        Connection cnx;
         String query;
       public UserCRUD() {
         cnx = DataSource.getInstance().getCnx();
    }
    
    
    
    private static String emailUser = "";
    private static String pwduser = "";

public Pair<Integer, String> loginUser(String email, String password) throws SQLException {
    Connection conn = DataSource.getInstance().getCnx();
    PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE email = ?");
    ps.setString(1, email);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        int id = rs.getInt("id");
        boolean isBlocked = rs.getBoolean("blocked");
        if (isBlocked) {
            // User is blocked
            return new Pair<>(-2, null);
        }
        String hashedPassword = rs.getString("password");
        if (BCrypt.checkpw(password, hashedPassword)) {
            String role = rs.getString("roles");
            if (role.equals("USER ROLE")) {
                return new Pair<>(id, "UserFace.fxml");
            } else if (role.equals("ADMIN ROLE")) {
                return new Pair<>(id, "GererUser.fxml");
            } else {
                // Unknown role
                return new Pair<>(-1, null);
            }
        } else {
            // Incorrect password
            return new Pair<>(-1, null);
        }
    } else {
        // Login failed
        return new Pair<>(-1, null);
    }
}




     
     
     public static void logoutUser() {
    emailUser = "";
    pwduser = "";
    System.out.println("User Logged Out Successfully!");
}
    
    
public boolean registerUser(String email, String nom, String password) {
    boolean userCreated = false;

    try {
        // Check if user already exists
        String checkQuery = "SELECT * FROM user WHERE email=?";
        PreparedStatement checkPst = DataSource.getInstance().getCnx().prepareStatement(checkQuery);
        checkPst.setString(1, email);
        ResultSet checkResult = checkPst.executeQuery();

        if (checkResult.next()) {
            // User already exists
            System.out.println("User with email " + email + " already exists!");
        } else {
            // User does not exist, create new user
            User user = new User(email, nom, password); // Create a new user with default values
            String insertQuery = "INSERT INTO user (email, nom, roles, password, blocked) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertPst = DataSource.getInstance().getCnx().prepareStatement(insertQuery);
            insertPst.setString(1, user.getEmail());
            insertPst.setString(2, user.getNom());
            insertPst.setString(3, user.getRoles());
            insertPst.setString(4, user.getPassword());
            insertPst.setBoolean(5, user.isBlocked());
            int rowsAffected = insertPst.executeUpdate();

            if (rowsAffected > 0) {
                // User created successfully
                System.out.println("User with email " + email + " created successfully!");
                userCreated = true;
            } else {
                System.out.println("Failed to create user with email " + email + "!");
            }
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    return userCreated;
}



    
    
 
    
    
    public User getUserByEmail(String email) throws SQLException {
    User user = null;
    
    String query = "SELECT * FROM user WHERE email=?";
    PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(query);
    pst.setString(1, email);
    ResultSet result = pst.executeQuery();
    
    if (result.next()) {
        user = new User();
        user.setId(result.getInt("id"));
        user.setEmail(result.getString("email"));
        user.setNom(result.getString("nom"));
        user.setRoles(result.getString("roles"));
        user.setPassword(result.getString("password"));
        user.setReset_password(result.getString("reset_token"));
        user.setBlocked(result.getBoolean("blocked"));
    }
    
    return user;
}

    
    


    
    
    
    
    
    
    
        public ObservableList<User> consulterUser() {
        List<User> listUser = new ArrayList<>();
        String query="";
        

            query ="SELECT * FROM user";
        
        if (query !=""){
        try {

            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                User c = new User();

                c.setId(res.getInt(1));
                 c.setEmail(res.getString("email"));
                c.setNom(res.getString("nom"));
                c.setBlocked(res.getBoolean("blocked"));
                
      
               
               

                listUser.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        }
        ObservableList<User> observableList = FXCollections.observableList(listUser);
        return observableList;
    }
    
    
    
    
    
    
    
     
  public static void deleteUser(int id) {
    try {
        String requete = "DELETE FROM user WHERE id=?";
        PreparedStatement pstDon = DataSource.getInstance().getCnx().prepareStatement(requete);
        pstDon.setInt(1, id);
        pstDon.executeUpdate();
   
        System.out.println("user supprimé!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
    
    
    
  
  
  
  
  
  
  
  
  
       
public static void modifierUser(User t) {
    try {
        String requete = "UPDATE user SET email=?, nom=? WHERE id=?" ;
        PreparedStatement pst= DataSource.getInstance().getCnx().prepareStatement(requete);

        pst.setString(1, t.getEmail());
        pst.setString(2, t.getNom());
        pst.setInt(3, t.getId());
        pst.executeUpdate();
        System.out.println("user modifié!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}







public static void modifierBlocked(User t) {
    try {
        String requete = "UPDATE user SET blocked=? WHERE id=?" ;
        PreparedStatement pst= DataSource.getInstance().getCnx().prepareStatement(requete);

    
        pst.setInt(1, t.getBlocked());
        pst.setInt(2, t.getId());
        pst.executeUpdate();
        System.out.println("block modifié!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    









public static User getUserByEmaile (String email) throws SQLException {
    Connection conn = DataSource.getInstance().getCnx();
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        pst = conn.prepareStatement("SELECT * FROM user WHERE email = ?");
        pst.setString(1, email);
        rs = pst.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String userEmail = rs.getString("email");

            return new User(id, name, userEmail);
        } else {
            return null;
        }
    } finally {
        if (rs != null) {
            rs.close();
        }

        if (pst != null) {
            pst.close();
        }
    }
}



















    
}

