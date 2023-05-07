/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.gui.back;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.pdev.entities.Formation;
import com.pdev.services.FormationService;

/**
 *
 * @author aminh
 */
public class GererFormation extends Form {
     Formation currentFormation;

    TextField nomTF;
    Label nomLabel;
    TextField nomTF2;
    Label nomLabel2;
    TextField meetTF2;
    Label meetLabel2;   
   TextField prixTF2;
    Label prixLabel2;

   


    Button manageButton;

    Form previous;

    public GererFormation(Form previous) {
        super(AfficherFormation.currentFormation == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentFormation = AfficherFormation.currentFormation;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {





        nomLabel = new Label("Nom : ");
        nomLabel.setUIID("labelDefault");
        nomTF = new TextField();
        nomTF.setHint("Tapez le nom");
        
        
        
          nomLabel2 = new Label("Description : ");
        nomLabel2.setUIID("labelDefault");
        nomTF2= new TextField();
        nomTF2.setHint("Tapez le description");


           meetLabel2 = new Label("meet : ");
         meetLabel2.setUIID("labelDefault");
        meetTF2= new TextField();
        meetTF2.setHint("Tapez le meet");
        
        
            prixLabel2 = new Label("prix : ");
       prixLabel2.setUIID("labelDefault");
    prixTF2= new TextField();
        prixTF2.setHint("Tapez le prix");
        
      




        if (currentFormation== null) {


            manageButton = new Button("Ajouter");
        } else {


            nomTF.setText(currentFormation.getNom());
            nomTF2.setText(currentFormation.getDescription());
            meetTF2.setText(currentFormation.getMeet());
            prixTF2.setText(String.valueOf(currentFormation.getPrix()));

           
           




            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(


                nomLabel, nomTF,
                 nomLabel2, nomTF2,
               meetLabel2,meetTF2,
              prixLabel2,prixTF2,
                manageButton
        );

        this.addAll(container);
    }
    
 

    private void addActions() {

   if (currentFormation == null) {
    manageButton.addActionListener(action -> {
        if (controleDeSaisie()) {
            int responseCode = FormationService.getInstance().add(
                    new Formation(
                             Integer.parseInt(prixTF2.getText()),
                            nomTF.getText(),
                              nomTF.getText(),
                         meetTF2.getText()
                       
                    ),
                    ""
            );
            if (responseCode == 200) {
              //  EmailSender.sendEmail(); // call the email sending method here
                Dialog.show("Succés", "Formation ajouté avec succes", new Command("Ok"));
                showBackAndRefresh();
            } else {
                Dialog.show("Erreur", "Erreur d'ajout de Formation. Code d'erreur : " + responseCode, new Command("Ok"));
            }
        }
    });
} else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = FormationService.getInstance().edit(
                            new Formation(
                                    currentFormation.getId(),
                                     Integer.parseInt(prixTF2.getText()),
                                     nomTF.getText(),
                                     nomTF2.getText(),
                                    meetTF2.getText()
                                  
                                 

                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "Formation modifié avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else if (responseCode == 250) {
                        Dialog.show("Error", "Heure invalide", new Command("Ok"));
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de Formation. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh() {
        ((AfficherFormation) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {


        if (nomTF.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }
 if (nomTF2.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }

       if (meetTF2.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }


   if (prixTF2.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }
       

        return true;
    }
    
}
