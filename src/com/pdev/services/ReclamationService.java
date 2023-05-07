/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.pdev.entities.Reclamation;
import com.pdev.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author aminh
 */
public class ReclamationService {
     
     public static ReclamationService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Reclamation> listReclamations;


    private ReclamationService() {
        cr = new ConnectionRequest();
    }

    public static ReclamationService getInstance() {
        if (instance == null) {
            instance = new ReclamationService();
        }
        return instance;
    }

    public ArrayList<Reclamation> getAll() {
        listReclamations = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "getRec");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listReclamations = getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listReclamations;
    }

    private ArrayList<Reclamation> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Reclamation reclamation = new Reclamation(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        (String) obj.get("Type"),
                         (String) obj.get("Description"),
                        new SimpleDateFormat("yyyy-MM-dd").parse((String) obj.get("Date"))
                     

                );

                listReclamations.add(reclamation);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listReclamations;
    }




    public int add(Reclamation reclamation, String email) {
        return manage(reclamation, false, email);
    }

    public int edit(Reclamation reclamation) {
        return manage(reclamation, true, "");
    }

    public int manage(Reclamation reclamation, boolean isEdit, String email) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "editRecJSON");
            cr.addArgument("id", String.valueOf(reclamation.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "addRecJSON");
            cr.addArgument("email", email);
        }

        
        
 
        cr.addArgument("type", reclamation.getType());
           cr.addArgument("description", reclamation.getDescription());
        cr.addArgument("date", new SimpleDateFormat("yyyy-MM-dd").format(reclamation.getDate()));
       
     


        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

    public int delete(int Id) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "deleterec");
        cr.setHttpMethod("DELETE");
        cr.addArgument("id", String.valueOf(Id));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }
    
    
    
    
    
    
    
    
    
}
