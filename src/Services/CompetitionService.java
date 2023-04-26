/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Competition;
import Utils.MyConnection;
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
public class CompetitionService implements IService<Competition>{

    Connection conn;
    PreparedStatement ste;

    public CompetitionService() {
        conn=MyConnection.getInstance().getConnection();
    }
    
    
    @Override
    public void add(Competition entity) {
String sql = "insert into competition(nom,description,date_debut,date_fin) Values(?,?,?,?)";
        try {
            ste=conn.prepareStatement(sql);
            ste.setString(2, entity.getDescription());
            ste.setString(1, entity.getNom().toString());
            ste.setDate(3, entity.getDate_debut());
            ste.setDate(4, entity.getDate_fin());
               
            
            ste.executeUpdate();
            System.out.println("competition Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void update(Competition entity) {
        String sql = "update  competition set nom= ? ,description= ? ,date_debut= ?,date_fin=? where id= ?";
        try {
            ste=conn.prepareStatement(sql);
            ste.setString(2, entity.getDescription());
            ste.setString(1, entity.getNom().toString());
            ste.setDate(3, entity.getDate_debut());
            ste.setDate(4, entity.getDate_fin());
            ste.setInt(5, entity.getId());
               
            
            ste.executeUpdate();
            System.out.println("competition Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void Delete(int id) {
         String sql = "DELETE from competition where id= '"+id+"' "; 
        String sql1="DELETE from equipe where competition_id= '"+id+"' "; 
        try{

            
           Statement st= conn.createStatement();
           st.executeUpdate(sql1);        
           st.executeUpdate(sql);
           System.out.println("competition supprimé avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public List<Competition> Show() {
        ObservableList<Competition> Competitionlist = FXCollections.observableArrayList();
        try{
        Statement st= conn.createStatement();
        String query = "select * from competition";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        Competition comp;
        while (rs.next()) {
           comp = new Competition(rs.getInt("id"),rs.getString("nom"),rs.getString("description")
                   ,rs.getDate("date_debut"),rs.getDate("date_fin")); 
            Competitionlist.add(comp);

        }
         return Competitionlist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Competitionlist;
    }

    public List<Competition> ShowByDate(Date debut,Date fin,int type) {
        ObservableList<Competition> Competitionlist = FXCollections.observableArrayList();
        try{
        Statement st= conn.createStatement();
        String query="";
        if(type==1){
           query = "select * from competition where date_debut>='"+debut.toString()+"'"; 
        }else if(type==2){
            query = "select * from competition where date_fin<='"+fin.toString()+"'"; 
        }else{
           query = "select * from competition where date_fin<='"+fin.toString()+"' and  date_debut>='"+debut.toString()+"'";
        }
        
        
        ResultSet rs;
        rs = st.executeQuery(query);
        Competition comp;
        while (rs.next()) {
           comp = new Competition(rs.getInt("id"),rs.getString("nom"),rs.getString("description")
                   ,rs.getDate("date_debut"),rs.getDate("date_fin")); 
            Competitionlist.add(comp);

        }
         return Competitionlist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Competitionlist;
    }
    @Override
    public Competition getById(int id) {
        Competition comp=new Competition();
        try{
        Statement st= conn.createStatement();
        String query = "select * from competition where id="+id+"";
        
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
    
}
