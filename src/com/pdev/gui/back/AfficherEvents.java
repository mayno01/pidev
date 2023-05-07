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
import com.pdev.entities.Event;
import com.pdev.services.EventService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author aminh
 */
public class AfficherEvents extends Form {
    
    
        Form previous;

    public static Event currentEvent= null;
    Button addBtn;


    public AfficherEvents(Form previous) {
        super("Events", new BoxLayout(BoxLayout.Y_AXIS));
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


        ArrayList<Event> listEvents = EventService.getInstance().getAll();


        if (listEvents.size() > 0) {
            for (Event event : listEvents) {
                Component model = makeEventModel(event);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentEvent = null;
            new GererEvents(this).show();
        });

    }

    Label sujetLabel, datLabel, datLabe2;


    private Container makeModelWithoutButtons(Event event) {
        Container eventModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        eventModel.setUIID("containerRounded");


      

        sujetLabel = new Label("nom : " + event.getNom());
        sujetLabel.setUIID("labelDefault");

        datLabel = new Label("Date_debut : " + new SimpleDateFormat("dd-MM-yyyy").format(event.getDateDebut()));
        datLabel.setUIID("labelDefault");
        datLabe2 = new Label("Date_fin : " + new SimpleDateFormat("dd-MM-yyyy").format(event.getDateFin()));
        datLabe2.setUIID("labelDefault");



        eventModel.addAll(

             sujetLabel, datLabel, datLabe2
        );

        return eventModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeEventModel(Event event) {

        Container eventModel = makeModelWithoutButtons(event);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentEvent = event;
            new GererEvents(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce event ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = EventService.getInstance().delete(event.getId());

                if (responseCode == 200) {
                    currentEvent = null;
                    dlg.dispose();
                    eventModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du event. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        eventModel.add(btnsContainer);

        return eventModel;
    }
    
}
