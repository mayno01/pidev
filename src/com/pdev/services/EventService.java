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
import com.pdev.entities.Event;
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
public class EventService {
    
    
     public static EventService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Event> listEvents;


    private EventService() {
        cr = new ConnectionRequest();
    }

    public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }

    public ArrayList<Event> getAll() {
        listEvents = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "getEVent");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listEvents = getList();
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

        return listEvents;
    }

    private ArrayList<Event> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Event event = new Event(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        (String) obj.get("nom"),
                        new SimpleDateFormat("yyyy-MM-dd").parse((String) obj.get("DateDebut")),
                        new SimpleDateFormat("yyyy-MM-dd").parse((String) obj.get("DateFin"))

                );

                listEvents.add(event);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listEvents;
    }




    public int add(Event event, String email) {
        return manage(event, false, email);
    }

    public int edit(Event event) {
        return manage(event, true, "");
    }

    public int manage(Event event, boolean isEdit, String email) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "editEventJSON");
            cr.addArgument("id", String.valueOf(event.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "addEventJSON");
            cr.addArgument("email", email);
        }

        
        
 
        cr.addArgument("nom", event.getNom());
        cr.addArgument("DateDebut", new SimpleDateFormat("yyyy-MM-dd").format(event.getDateDebut()));
         cr.addArgument("DateFin", new SimpleDateFormat("yyyy-MM-dd").format(event.getDateFin()));
     


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
    
}
