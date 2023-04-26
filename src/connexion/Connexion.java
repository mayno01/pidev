/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import entite.Reclamation;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import service.ReclamationService;
import service.ReponseService;
import util.DataSource;

/**
 *
 * @author wiemhjiri
 */
public class Connexion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
//        DataSource ds1 = DataSource.getInstance();
//        System.out.println(ds1);
//        DataSource ds2 = DataSource.getInstance();
//        System.out.println(ds2);
//
//        DataSource ds3 = DataSource.getInstance();
//        System.out.println(ds3);

      //  Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse("2010-11-01");


        //Reclamation r1=new Reclamation("ibtihellllll", "3a45",new Date(11,11,2015));
        //ReclamationService rs = new ReclamationService();
        //int id=175;
        // Reclamation rec =rs.readById(175);
         //System.out.println(rec);
        // Reclamation r1=new Reclamation("ibtihellllll", "3a45",new Date(11,11,2015));
        ReponseService rs = new ReponseService();

        // rs.insert(r1);
       // rs.insertPst(r1);
      // rs.getAll().forEach(System.out::println);
     int n ;
     n= rs.calculnb("non traitee");
     System.out.println(n);
     
     
      
      
         
        //rs.delete(233);
        
       /*  Reclamation reclamation = new Reclamation();
    reclamation.setId(175);
    reclamation.setType("saaadaaada");
    reclamation.setDescription("some description");
    reclamation.setDate(new Date(1,12,2020));
    
     ReclamationService reclamationService = new ReclamationService();
      reclamationService.update(reclamation);*/
}
}
