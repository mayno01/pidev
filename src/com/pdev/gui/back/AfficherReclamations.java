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
import com.pdev.entities.Reclamation;
import com.pdev.services.ReclamationService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author aminh
 */
public class AfficherReclamations extends Form {
    
    
    
        Form previous;

    public static Reclamation currentReclamation= null;
    Button addBtn;


    public AfficherReclamations(Form previous) {
        super("Reclamations", new BoxLayout(BoxLayout.Y_AXIS));
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


        ArrayList<Reclamation> listReclamations = ReclamationService.getInstance().getAll();


        if (listReclamations.size() > 0) {
            for (Reclamation reclamation : listReclamations) {
                Component model = makeReclamationModel(reclamation);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentReclamation = null;
            new GererReclamations(this).show();
        });

    }

    Label sujetLabel,sujetLabel1, datLabel;


    private Container makeModelWithoutButtons(Reclamation reclamation) {
        Container reclamationModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
     reclamationModel.setUIID("containerRounded");


      

        sujetLabel = new Label("type : " + reclamation.getType());
        sujetLabel.setUIID("labelDefault");
        
                sujetLabel1 = new Label("description : " + reclamation.getDescription());
        sujetLabel1.setUIID("labelDefault");


        datLabel = new Label("Date: " + new SimpleDateFormat("dd-MM-yyyy").format(reclamation.getDate()));
        datLabel.setUIID("labelDefault");
       


        reclamationModel.addAll(

             sujetLabel,sujetLabel1, datLabel
        );

        return     reclamationModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeReclamationModel(    Reclamation     reclamation) {

        Container     reclamationModel = makeModelWithoutButtons(    reclamation);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentReclamation=     reclamation;
            new GererReclamations(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce reclamation ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = ReclamationService.getInstance().delete(reclamation.getId());

                if (responseCode == 200) {
                    currentReclamation = null;
                    dlg.dispose();
                    reclamationModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du reclamation. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        reclamationModel.add(btnsContainer);

        return reclamationModel;
    }
    
    
    
    
}
