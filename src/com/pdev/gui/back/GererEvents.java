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
import com.pdev.entities.Event;
import com.pdev.services.EventService;

/**
 *
 * @author aminh
 */
public class GererEvents extends Form {
    
    
    
    Event currentEvent;

    TextField nomTF;
    Label nomLabel;
    PickerComponent datTF;
    PickerComponent datTFF;

   


    Button manageButton;

    Form previous;

    public GererEvents(Form previous) {
        super(AfficherEvents.currentEvent == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentEvent = AfficherEvents.currentEvent;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {





        nomLabel = new Label("Nom : ");
        nomLabel.setUIID("labelDefault");
        nomTF = new TextField();
        nomTF.setHint("Tapez le nom");


        datTF = PickerComponent.createDate(null).label("DateDebut");
        
        datTFF = PickerComponent.createDate(null).label("DateFin");




        if (currentEvent== null) {


            manageButton = new Button("Ajouter");
        } else {


            nomTF.setText(currentEvent.getNom());
            datTF.getPicker().setDate(currentEvent.getDateDebut());
             datTFF.getPicker().setDate(currentEvent.getDateFin());
           




            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(


                nomLabel, nomTF,
                datTF, datTFF,
                manageButton
        );

        this.addAll(container);
    }
    
 

    private void addActions() {

   if (currentEvent == null) {
    manageButton.addActionListener(action -> {
        if (controleDeSaisie()) {
            int responseCode = EventService.getInstance().add(
                    new Event(
                            nomTF.getText(),
                            datTF.getPicker().getDate(),
                            datTFF.getPicker().getDate()
                    ),
                    ""
            );
            if (responseCode == 200) {
                EmailSender.sendEmail(); // call the email sending method here
                Dialog.show("Succés", "Event ajouté avec succes", new Command("Ok"));
                showBackAndRefresh();
            } else {
                Dialog.show("Erreur", "Erreur d'ajout de Event. Code d'erreur : " + responseCode, new Command("Ok"));
            }
        }
    });
} else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = EventService.getInstance().edit(
                            new Event(
                                    currentEvent.getId(),
                                     nomTF.getText(),
                                    datTF.getPicker().getDate(),
                                    datTFF.getPicker().getDate()
                                 

                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "Event modifié avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else if (responseCode == 250) {
                        Dialog.show("Error", "Heure invalide", new Command("Ok"));
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de Event. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh() {
        ((AfficherEvents) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {


        if (nomTF.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }


        if (datTF.getPicker().getDate() == null) {
            Dialog.show("Avertissement", "Veuillez saisir la dat", new Command("Ok"));
            return false;
        }

 if (datTFF.getPicker().getDate() == null) {
            Dialog.show("Avertissement", "Veuillez saisir la dat", new Command("Ok"));
            return false;
        }
       

        return true;
    }
    
}
