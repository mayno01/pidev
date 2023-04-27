/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entite.Reclamation;
import entite.Reponse;
import entite.ReponseRec;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

/**
 *
 * @author DELL
 */
public class ReponseService implements IService<Reponse> {
     private Connection conn;
     private ResultSet rs;

    public ReponseService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Reponse t) {
         String requete = "Insert into Reponse (reclamation_id,reponse,etat) values"
                + "('" + t.getReclamation_id() + "','" + t.getReponse() + "'," + t.getEtat() + ")";
         
        try {
            Statement ste = conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertPst(Reponse r) {
        String requete = "insert into Reponse (reclamation_id,reponse,etat) values(?,?,?)";
        try {
            // Date date=Date.valueOf(r.getDate());

            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, r.getReclamation_id());
            pst.setString(2, r.getReponse());
            pst.setString(3, r.getEtat());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void update(Reponse t) {
     String tableName = "reponse"; // replace this with the actual table name
    String query = "UPDATE " + tableName + " SET  etat = ?, reponse = ? WHERE id = ?";

    try {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, t.getEtat());
        ps.setString(2, t.getReponse());
         ps.setInt(3, t.getId());
       
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public void delete(int id) {
            String requete = "delete from Reponse where id = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(requete);
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public Reponse readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Reponse> readAll() {
         
        String req = "select * from reponse inner join Reclamation where reclamation.id = reponse.Reclamation_id";
        ArrayList<Reponse> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){

                Reponse r=new Reponse(rs.getInt("id"),rs.getInt("reclamation_id"),rs.getString("reponse"),rs.getString("etat") );
                list.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }
    
    

    public ArrayList<ReponseRec> getAll() {
         
        String req = "select * from reponse inner join Reclamation where reclamation.id = reponse.Reclamation_id";
        ArrayList<ReponseRec> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){

                ReponseRec r=new ReponseRec(rs.getInt("reponse.id"),rs.getString("type"), rs.getString("description") ,rs.getDate("date"),rs.getString("etat") ,rs.getString("reponse"));
                list.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }
    
    
 
    
    
     public ObservableList<ReponseRec> chercherReclamation(String chaine){
          String sql="SELECT * FROM reponse "
           + "INNER JOIN Reclamation ON reclamation.id = reponse.Reclamation_id "
           + "WHERE reclamation.type LIKE ? OR reclamation.description LIKE ?";
            
             conn = DataSource.getInstance().getCnx();
            String ch="%"+chaine+"%";
            
        // System.out.println(sql);
            ObservableList<ReponseRec> myList= FXCollections.observableArrayList();
        try {
           
            Statement ste= conn.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =conn.prepareStatement(sql);  
            stee.setString(1, ch);

            stee.setString(2, ch);
          
           
        // System.out.println(stee);

            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                ReponseRec v = new ReponseRec ();
                v.setId(rs.getInt("id"));
               v.setType(rs.getString("type"));
                v.setDescription(rs.getString("description"));
               v.setDate(rs.getDate("date"));
                v.setEtat(rs.getString("etat"));
                v.setReponse(rs.getString("reponse"));
               
               

                myList.add(v);
               // System.out.println("titre trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
     
     
     
     
     public int calculnb(String etat) {

        PreparedStatement pre;
        int count = 19;
        try {
            Statement stmt = conn.createStatement();

            String query = "SELECT COUNT(*) FROM reponse WHERE etat='"+etat+"'";

            ResultSet rs = stmt.executeQuery(query);

            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;

     }

    @Override
    public void add(Reponse entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reponse> Show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reponse getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

    
    
    
    

