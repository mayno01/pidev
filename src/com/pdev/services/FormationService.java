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
import com.pdev.entities.Formation;
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
public class FormationService {
        
     public static FormationService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Formation> listFormation;


    private FormationService() {
        cr = new ConnectionRequest();
    }

    public static FormationService getInstance() {
        if (instance == null) {
            instance = new FormationService();
        }
        return instance;
    }

    public ArrayList<Formation> getAll() {
        listFormation = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "getFormationmob");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listFormation = getList();
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

        return listFormation;
    }

    private ArrayList<Formation> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Formation formation = new Formation(
                        (int) Float.parseFloat(obj.get("id").toString()),
   (int) Float.parseFloat(obj.get("prix").toString()),
                        (String) obj.get("nom"),
                         (String) obj.get("description"),
                            (String) obj.get("meet")
                       
         
                     

                );

                listFormation.add(formation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listFormation;
    }
    
    
    
    
    
        public int delete(int Id) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "jsondeletefor");
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

        
          public int add(Formation formation, String email) {
        return manage(formation, false, email);
    }

    public int edit(Formation formation) {
        return manage(formation, true, "");
    }

    public int manage(Formation formation, boolean isEdit, String email) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "editFormaJSON");
            cr.addArgument("id", String.valueOf(formation.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "addFormaJSON");
            cr.addArgument("email", email);
        }

        
        
 
        cr.addArgument("nom", formation.getNom());
        
         cr.addArgument("description", formation.getDescription());
         cr.addArgument("meet", formation.getMeet());
         cr.addArgument("prix", Float.toString(formation.getPrix()));
    
       
     


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
    
}
