/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.gui.back;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.pdev.entities.User;
import com.pdev.services.GererUserService;
import java.util.ArrayList;

/**
 *
 * @author aminh
 */
public class AfficherUser extends Form {
    
        Resources theme1 = UIManager.initFirstTheme("/theme");
    
        Form previous;

    public static User currentUser= null;
    Button addBtn;


    public AfficherUser(Form previous) {
        super("User", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
       
  


        ArrayList<User> listUser = GererUserService.getInstance().getAll();


        if (listUser.size() > 0) {
            for (User user : listUser) {
                Component model = makeUserModel(user);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
  

    }

    Label sujetLabel, sujetLabe2;


    private Container makeModelWithoutButtons(User user) {
        Container userModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        userModel.setUIID("containerRounded");


      

        sujetLabel = new Label("email : " + user.getEmail());
        sujetLabel.setUIID("labelDefault");
        
       sujetLabe2 = new Label("username : " + user.getNom());
        sujetLabe2.setUIID("labelDefault");
        
   
     
     



        userModel.addAll(

             sujetLabel,sujetLabe2 
        );

        return userModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeUserModel(User user) {

        Container userModel = makeModelWithoutButtons(user);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentUser = user;
            new GererUser(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce User ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = GererUserService.getInstance().delete(user.getId());

                if (responseCode == 200) {
                    currentUser = null;
                    dlg.dispose();
                    userModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du User. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        userModel.add(btnsContainer);

        return userModel;
    }
    
}
