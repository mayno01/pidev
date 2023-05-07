/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdev.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.pdev.entities.Equipe;
import com.pdev.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public class EquipeService {
    
    
     
     public static EquipeService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Equipe> listEquipes;


    private EquipeService() {
        cr = new ConnectionRequest();
    }

    public static EquipeService getInstance() {
        if (instance == null) {
            instance = new EquipeService();
        }
        return instance;
    }

    public ArrayList<Equipe> getAll() {
        listEquipes = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "getEquipemob");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listEquipes = getList();
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

        return listEquipes;
    }

    private ArrayList<Equipe> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Equipe equipe = new Equipe(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        (String) obj.get("nom"),
                         (String) obj.get("description"),
                        new SimpleDateFormat("yyyy-MM-dd").parse((String) obj.get("dateCreation"))
                     

                );

                listEquipes.add(equipe);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listEquipes;
    }
    
    
    
    
    
        public int delete(int Id) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "jsondelete");
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

        
          public int add(Equipe equipe, String email) {
        return manage(equipe, false, email);
    }

    public int edit(Equipe equipe) {
        return manage(equipe, true, "");
    }

    public int manage(Equipe equipe, boolean isEdit, String email) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "editEquipeJSON");
            cr.addArgument("id", String.valueOf(equipe.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "addEquipeJSON");
            cr.addArgument("email", email);
        }

        
        
 
        cr.addArgument("nom", equipe.getNom());
        
        cr.addArgument("description", equipe.getDescription());
        cr.addArgument("DateCreation", new SimpleDateFormat("yyyy-MM-dd").format(equipe.getDatecreation()));
       
     


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
