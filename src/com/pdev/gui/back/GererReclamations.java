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
import com.pdev.entities.Reclamation;
import com.pdev.services.ReclamationService;

/**
 *
 * @author aminh
 */
public class GererReclamations extends Form {
    
    
    
    
     Reclamation currentReclamation;

    TextField nomTF;
    Label nomLabel;
     TextField nomTF3;
    Label nomLabel3;
    PickerComponent datTF;
  
   


    Button manageButton;

    Form previous;

    public GererReclamations(Form previous) {
        super(AfficherReclamations.currentReclamation == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentReclamation = AfficherReclamations.currentReclamation;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {





        nomLabel = new Label("Type : ");
        nomLabel.setUIID("labelDefault");
        nomTF = new TextField();
        nomTF.setHint("Tapez le type");
        
        nomLabel3 = new Label("Description : ");
        nomLabel3.setUIID("labelDefault");
        nomTF3 = new TextField();
        nomTF3.setHint("Tapez le description");


        datTF = PickerComponent.createDate(null).label("DateDebut");
      




        if (currentReclamation== null) {


            manageButton = new Button("Ajouter");
        } else {


            nomTF.setText(currentReclamation.getType());
            
            nomTF3.setText(currentReclamation.getDescription());
            datTF.getPicker().setDate(currentReclamation.getDate());
         
           




            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(


                nomLabel, nomTF,
                  nomLabel3, nomTF3,
                datTF, 
                manageButton
        );

        this.addAll(container);
    }
    
 

    private void addActions() {

   if ( currentReclamation== null) {
    manageButton.addActionListener(action -> {
        if (controleDeSaisie()) {
            int responseCode = ReclamationService.getInstance().add(
                    new Reclamation(
                            nomTF.getText(),
                               nomTF3.getText(),
                            datTF.getPicker().getDate()
                          
                    ),
                    ""
            );
            if (responseCode == 200) {
               // EmailSender.sendEmail(); // call the email sending method here
                Dialog.show("Succés", "reclamation ajouté avec succes", new Command("Ok"));
                showBackAndRefresh();
            } else {
                Dialog.show("Erreur", "Erreur d'ajout de reclamation. Code d'erreur : " + responseCode, new Command("Ok"));
            }
        }
    });
} else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = ReclamationService.getInstance().edit(
                            new Reclamation(
                                    currentReclamation.getId(),
                                     nomTF.getText(),  nomTF3.getText(),
                                    datTF.getPicker().getDate()
                                   
                                 

                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "reclamation modifié avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else if (responseCode == 250) {
                        Dialog.show("Error", "Heure invalide", new Command("Ok"));
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de reclamation. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh() {
        ((AfficherReclamations) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {


        if (nomTF.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }

        if (nomTF3.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }

        if (datTF.getPicker().getDate() == null) {
            Dialog.show("Avertissement", "Veuillez saisir la dat", new Command("Ok"));
            return false;
        }


       

        return true;
    }
    
}
