/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdev.gui.back;import com.codename1.components.InteractionDialog;
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
import com.pdev.entities.Equipe;
import com.pdev.services.EquipeService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class AfficherEquipe extends Form {
    
    
        Form previous;

    public static Equipe currentEquipe= null;
    Button addBtn;


    public AfficherEquipe(Form previous) {
        super("Equipes", new BoxLayout(BoxLayout.Y_AXIS));
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


        ArrayList<Equipe> listEquipes = EquipeService.getInstance().getAll();


        if (listEquipes.size() > 0) {
            for (Equipe equipe : listEquipes) {
                Component model = makeEquipeModel(equipe);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentEquipe = null;
            new GererEquipe(this).show();
        });

    }

    Label sujetLabel,sujetLabel1,datLabel;


    private Container makeModelWithoutButtons(Equipe equipe) {
        Container equipeModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        equipeModel.setUIID("containerRounded");


      

        sujetLabel = new Label("nom : " + equipe.getNom());
        sujetLabel.setUIID("labelDefault");
        
         sujetLabel1 = new Label("description : " + equipe.getDescription());
        sujetLabel1.setUIID("labelDefault");

        datLabel = new Label("date_creation : " + new SimpleDateFormat("dd-MM-yyyy").format(equipe.getDatecreation()));
        datLabel.setUIID("labelDefault");
       



        equipeModel.addAll(

             sujetLabel,sujetLabel1 ,datLabel
        );

        return equipeModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeEquipeModel(Equipe equipe) {

        Container equipeModel = makeModelWithoutButtons(equipe);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentEquipe = equipe;
            new GererEquipe(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer cet equipe ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = EquipeService.getInstance().delete(equipe.getId());

                if (responseCode == 200) {
                    currentEquipe = null;
                    dlg.dispose();
                    equipeModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du equipe. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        equipeModel.add(btnsContainer);

        return equipeModel;
    }
    
}
