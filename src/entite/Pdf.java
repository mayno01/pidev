/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import service.ReponseService;




/**
 *
 * @author DELL
 */
public class Pdf {
    
    
    
    
    public void GeneratePdf(String filename, ReponseRec p, int id) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {

        Document document = new Document() {
        };
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();

        ReponseService us = new ReponseService();
      
        document.add(new Paragraph("            le reclamation :"+p.getType()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));

        document.add(new Paragraph("description :" + p.getDescription()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("date :" + p.getDate()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("etat:" + p.getEtat()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("reponse :" + p.getReponse()));
        document.add(new Paragraph("                      "));
      
       

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.add(new Paragraph("                              Reformers                     "));
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }

}

    
