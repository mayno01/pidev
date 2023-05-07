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
import com.pdev.entities.User;
import com.pdev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aminh
 */
public class GererUserService {
     
       public static GererUserService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<User> listUser;


    private GererUserService() {
        cr = new ConnectionRequest();
    }

    public static GererUserService getInstance() {
        if (instance == null) {
            instance = new GererUserService();
        }
        return instance;
    }

    public ArrayList<User> getAll() {
        listUser = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "getUser1");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listUser = getList();
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

        return listUser;
    }

    private ArrayList<User> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                User user = new User(
                        (int) Float.parseFloat(obj.get("id").toString()),
            
                        (String) obj.get("email"),
                        (String) obj.get("nom")
                   
              

                );

                listUser.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listUser;
    }
    
     public int add(User user, String email) {
        return manage(user, false, email);
    }

    public int edit(User user) {
        return manage(user, true, "");
    }

    public int manage(User user, boolean isEdit, String email) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "editUserJSON1");
            cr.addArgument("id", String.valueOf(user.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "addUserJSON1");
            cr.addArgument("email", email);
        }

        
        
 
     cr.addArgument("email", user.getEmail());
    cr.addArgument("nom", user.getNom());
   
              
              
                    
    
     


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
        cr.setUrl(Statics.BASE_URL + "jsonardelete1");
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
