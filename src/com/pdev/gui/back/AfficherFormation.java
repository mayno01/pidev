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
import com.pdev.entities.Formation;
import com.pdev.services.FormationService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author aminh
 */
public class AfficherFormation extends Form{
     
        Form previous;

    public static Formation currentFormation= null;
    Button addBtn;


    public AfficherFormation(Form previous) {
        super("Formations", new BoxLayout(BoxLayout.Y_AXIS));
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
        addBtn = new Button("Ajouter");
        addBtn.setUIID("buttonWhiteCenter");
        this.add(addBtn);


        ArrayList<Formation> listFormations = FormationService.getInstance().getAll();


        if (listFormations.size() > 0) {
            for (Formation formation : listFormations) {
                Component model = makeFormationModel(formation);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentFormation = null;
            new GererFormation(this).show();
        });

    }

    Label sujetLabel,sujetLabel1,sujetLabel1l;


    private Container makeModelWithoutButtons(Formation formation) {
        Container formationModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        formationModel.setUIID("containerRounded");


      

        sujetLabel = new Label("nom : " + formation.getNom());
        sujetLabel.setUIID("labelDefault");
        
         sujetLabel1 = new Label("description : " + formation.getDescription());
        sujetLabel1.setUIID("labelDefault");

        sujetLabel1 = new Label("meet : " + formation.getMeet());
        sujetLabel1.setUIID("labelDefault");
        sujetLabel1l = new Label("prix : " + formation.getPrix());
        sujetLabel1l.setUIID("labelDefault");



        formationModel.addAll(

             sujetLabel,sujetLabel1 ,sujetLabel1l
        );

        return formationModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeFormationModel(Formation formation) {

        Container formationModel = makeModelWithoutButtons(formation);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentFormation = formation;
            new GererFormation(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer cette formation ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = FormationService.getInstance().delete(formation.getId());

                if (responseCode == 200) {
                    currentFormation = null;
                    dlg.dispose();
                    formationModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du formation. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        formationModel.add(btnsContainer);

        return formationModel;
    }
    
}
