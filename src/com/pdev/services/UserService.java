/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.pdev.entities.User;
import com.pdev.gui.back.ResetMail;
import com.pdev.utils.Statics;
import org.json.JSONObject;

/**
 *
 * @author aminh
 */
public class UserService {
    
       
     public static UserService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private static User currentUser;




    private UserService() {
        cr = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
  public int register(User user) {
    cr = new ConnectionRequest();
    cr.setUrl(Statics.BASE_URL + "registerUserJSON");
    cr.setHttpMethod("POST");
        
    cr.addArgument("email", user.getEmail());
    cr.addArgument("nom", user.getNom());
    cr.addArgument("password", user.getPassword());
      
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
  public void setCurrentUser(User user) {
    this.currentUser = user;
}
public User login(String email, String password) {
    cr = new ConnectionRequest();
    cr.setUrl(Statics.BASE_URL + "loginJSON");
    cr.setHttpMethod("POST");

    cr.addArgument("email", email);
    cr.addArgument("password", password);

    cr.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultCode = cr.getResponseCode();
            if (resultCode == 200) {
                // login successful, return user object
                JSONObject result = new JSONObject(new String(cr.getResponseData()));
               String idString = String.valueOf(result.getInt("id"));
                currentUser = new User(idString, email, password);

            } else {
                currentUser = null;
            }
            cr.removeResponseListener(this);
        }
    });

    try {
        cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
        NetworkManager.getInstance().addToQueueAndWait(cr);
    } catch (Exception ignored) {

    }

    return currentUser;
}


public void forgetPassword(String email) {
    cr = new ConnectionRequest();
    cr.setUrl(Statics.BASE_URL + "resetPasswordJSON");
    cr.setHttpMethod("POST");
        
    cr.addArgument("email", email);
      
    cr.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultCode = cr.getResponseCode();
            if (resultCode == 200) {
                // password reset successful, send email
                ResetMail.sendPassword(email);
            }
            cr.removeResponseListener(this);
        }
    });
        
    try {
        cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
        NetworkManager.getInstance().addToQueueAndWait(cr);
    } catch (Exception ignored) {
            
    }
}


}







