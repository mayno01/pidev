/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Evenement;
import entite.Sponsor;
import util.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author houss
 */
public class EvenementService implements IServiceI<Evenement>{

    Connection conn;
    PreparedStatement ste;

    public EvenementService() {
        conn=DataSource.getInstance().getCnx();
    }
    
    
    @Override
    public void add(Evenement entity) {
String sql = "insert into evenment(title,description,date_debut,date_fin) Values(?,?,?,?)";
        try {
            ste=conn.prepareStatement(sql);
            ste.setString(2, entity.getDescription());
            ste.setString(1, entity.getNom().toString());
            ste.setDate(3, entity.getDate_debut());
            ste.setDate(4, entity.getDate_fin());
               
            
            ste.executeUpdate();
            System.out.println("Evenement Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void update(Evenement entity) {
        String sql = "update  evenment set title= ? ,description= ? ,date_debut= ?,date_fin=? where id= ?";
        try {
            ste=conn.prepareStatement(sql);
            ste.setString(2, entity.getDescription());
            ste.setString(1, entity.getNom().toString());
            ste.setDate(3, entity.getDate_debut());
            ste.setDate(4, entity.getDate_fin());
            ste.setInt(5, entity.getId());
               
            
            ste.executeUpdate();
            System.out.println("Evenment Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void Delete(int id) {
         String sql = "DELETE from evenment where id= '"+id+"' "; 
        String sql1="DELETE from sponsor where event_id= '"+id+"' "; 
        try{

            
           Statement st= conn.createStatement();
           st.executeUpdate(sql1);        
           st.executeUpdate(sql);
           System.out.println("Evenment supprimé avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public List<Evenement> Show() {
        ObservableList<Evenement> Competitionlist = FXCollections.observableArrayList();
        service.SponsorService sp=new SponsorService();
        List<Sponsor> lis=FXCollections.observableArrayList();
        try{
        Statement st= conn.createStatement();
        String query = "select * from evenment";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        Evenement comp;
        while (rs.next()) {
            float somme=0;
           comp = new Evenement(rs.getInt("id"),rs.getString("title"),rs.getString("description")
                   ,rs.getDate("date_debut"),rs.getDate("date_fin")); 
           lis=sp.getSponsors(comp.getId());
           for(int i =0 ; i<lis.size();i++){
               somme=somme+lis.get(i).getBudget();
           }
           comp.setBudget(somme);
            Competitionlist.add(comp);

        }
         return Competitionlist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Competitionlist;
    }

    @Override
    public Evenement getById(int id) {
        Evenement comp=new Evenement();
        try{
        Statement st= conn.createStatement();
        String query = "select * from evenment where id="+id+"";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        
        while (rs.next()) {
           comp = new Evenement(rs.getInt("id"),rs.getString("title"),rs.getString("description")
                   ,rs.getDate("date_debut"),rs.getDate("date_fin")); 
            

        }
         return comp;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return comp;
    }
    
}
