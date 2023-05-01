/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entite.Reclamation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 *
 * @author DELL
 */
public class ReclamationService implements IService<Reclamation> {

    private Connection conn;
    private int nb1, nb2, nb3;

    public ReclamationService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Reclamation r) {
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
         String formattedDate = sdf.format(r.getDate());
        String requete = "insert into reclamation (type,description,date) values"
              + "('" + r.getType() + "','" + r.getDescription() + "'," + formattedDate + ")";
        try {
            Statement ste = conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    
    
   public void insertPst(Reclamation r) {
        String requete = "insert into reclamation(type,description,date) values(?,?,?)";
        try {
          // Date date=Date.valueOf(r.getDate());
           
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         String formattedDate = sdf.format(r.getDate());
            
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, r.getType());
            pst.setString(2, r.getDescription());
            pst.setString(3, formattedDate);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Reclamation r) {
       String tableName = "reclamation"; // replace this with the actual table name
    String query = "UPDATE " + tableName + " SET type = ?, description = ?, date = ? WHERE id = ?";

    try {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, r.getType());
        ps.setString(2, r.getDescription());
        ps.setDate(3, r.getDate());
        ps.setInt(4, r.getId());
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    

  


      


    @Override
    public void delete(int id) {
        String requete = "delete from Reclamation where id = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(requete);
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public Reclamation readById(int id) {
    Reclamation R = null;
        String q = "SELECT * FROM Reclamation WHERE id=" + id;

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(q);
            if (rs.next()) {
                R = new Reclamation(rs.getInt("id"), rs.getString("type") , rs.getString("description"),rs.getDate("date"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return R;
    }
    

    @Override
    public ArrayList<Reclamation> readAll() {
        String requete = "select * from Reclamation";
        ArrayList<Reclamation> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){

                Reclamation r=new Reclamation(rs.getInt("id"), rs.getString("type") , rs.getString("description"),rs.getDate("date"));
                list.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
     public int EntraineurType() throws SQLException {
        String req = "SELECT count(id) from reclamation where type ='Reclamation Entraineur';";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            nb1 = rs.getInt(1);
        }
        return nb1;
    }
     
     
     
     
      public int CompetitionType() throws SQLException {
        String req = "SELECT count(id) from reclamation where type ='Reclamation Competition';";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            nb2 = rs.getInt(1);
        }
        return nb2;
    }
      
            public int EvenementType() throws SQLException {
        String req = "SELECT count(id) from reclamation where type ='Reclamation Evenement';";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            nb3 = rs.getInt(1);
        }
        return nb3;
    }

    @Override
    public void add(Reclamation entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> Show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reclamation getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
      

}