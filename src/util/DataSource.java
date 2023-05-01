/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author houss
 */
public class DataSource {
    private String url="jdbc:mysql://localhost:3306/pidev1";
    private String username = "root";
    private String password ="";
    private java.sql.Connection cnx;
   static DataSource instance ;
    
    private DataSource() {
        
        try {
            
           cnx= DriverManager.getConnection(url, username, password);
           System.out.println ("Database Connected ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); 
        }
    }
    
    public java.sql.Connection getConnection(){
        return cnx;
    }
    
    public static DataSource getInstance() {
        if (instance==null){
            instance = new DataSource();
        }
        return instance ; 
    }
}
