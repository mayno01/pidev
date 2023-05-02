/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Competition;
import entite.Equipe;
import util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author houss
 */
public class EquipeService implements IService<Equipe>{
    
    Connection conn;
    PreparedStatement ste;

    public EquipeService() {
        conn=DataSource.getInstance().getCnx();
    }

    @Override
    public void add(Equipe entity) {
        String sql = "insert into equipe(competition_id,nom,description,date_creation) Values(?,?,?,?)";
        try {
            ste=conn.prepareStatement(sql);
            ste.setString(3, entity.getDescription());
            ste.setString(2, entity.getNom().toString());
            ste.setDate(4, entity.getDate_creation());
            ste.setInt(1, entity.getCompetition_id());
               
            
            ste.executeUpdate();
            System.out.println("equipe Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void update(Equipe entity) {
        String sql = "update  equipe set competition_id=?,nom=?,description=?,date_creation=? where id=?";
        try {
            ste=conn.prepareStatement(sql);
            ste.setString(3, entity.getDescription());
            ste.setString(2, entity.getNom().toString());
            ste.setDate(4, entity.getDate_creation());
            ste.setInt(1, entity.getCompetition_id());
            ste.setInt(5, entity.getId());
               
            System.out.println(ste.toString()+"************");
            ste.executeUpdate();
            System.out.println("equipe modifie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void Delete(int id) {
         String sql = "DELETE from equipe where id= '"+id+"' "; 
        try{

            
           Statement st= conn.createStatement();       
           st.executeUpdate(sql);
           System.out.println("Equipe supprimé avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public List<Equipe> Show() {
        ObservableList<Equipe> Equipelist = FXCollections.observableArrayList();
        service.CompetitionService sr=new CompetitionService();
        try{
        Statement st= conn.createStatement();
        String query = "select * from equipe";
        
            ResultSet rs;
        rs = st.executeQuery(query);
        Equipe eq;
        while (rs.next()) {
           eq = new Equipe(rs.getInt("id"),rs.getInt("competition_id"),rs.getString("nom"),rs.getString("description")
                   ,rs.getDate("date_creation")); 
           eq.setCompetition_nom(sr.getById(eq.getCompetition_id()).getNom());
            Equipelist.add(eq);
            System.out.println(eq.toString());

        }
         return Equipelist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Equipelist;
    }

    @Override
    public Equipe getById(int id) {
        
        Equipe eq=new Equipe();
        service.CompetitionService sr=new CompetitionService();
        try{
        Statement st= conn.createStatement();
        String query = "select * from equipe where id="+id+"";
        
            ResultSet rs;
        rs = st.executeQuery(query);
        while (rs.next()) {
           eq = new Equipe(rs.getInt("id"),rs.getInt("competition_id"),rs.getString("nom"),rs.getString("description")
                   ,rs.getDate("date_creation")); 
            eq.setCompetition_nom(sr.getById(eq.getCompetition_id()).getNom());

        }
         return eq;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return eq;
    }
    
    public Competition getCompByName(String id) {
        
        Competition comp=new Competition();
        service.CompetitionService sr=new CompetitionService();
        try{
        Statement st= conn.createStatement();
        String query = "select * from competition where nom='"+id+"'";
            System.out.println(query);
        
        ResultSet rs;
        rs = st.executeQuery(query);
        
        while (rs.next()) {
           comp = new Competition(rs.getInt("id"),rs.getString("nom"),rs.getString("description")
                   ,rs.getDate("date_debut"),rs.getDate("date_fin")); 
            

        }
         return comp;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return comp;
    }
     public List<Equipe> ShowByComp(int id) {
        ObservableList<Equipe> Equipelist = FXCollections.observableArrayList();
        service.CompetitionService sr=new CompetitionService();
        try{
        Statement st= conn.createStatement();
        String query = "select * from equipe where competition_id="+id+"";
        
            ResultSet rs;
        rs = st.executeQuery(query);
        Equipe eq;
        while (rs.next()) {
           eq = new Equipe(rs.getInt("id"),rs.getInt("competition_id"),rs.getString("nom"),rs.getString("description")
                   ,rs.getDate("date_creation")); 
           eq.setCompetition_nom(sr.getById(eq.getCompetition_id()).getNom());
            Equipelist.add(eq);
            System.out.println(eq.toString());

        }
         return Equipelist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Equipelist;
    }

    @Override
    public void insert(Equipe t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Equipe readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equipe> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
