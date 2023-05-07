/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import com.pdev.entities.Equipe;
import com.pdev.services.EquipeService;

/**
 *
 * @author LENOVO
 */
public class GererEquipe extends Form {
      
    Equipe currentEquipe;

    TextField nomTF;
    Label nomLabel;
    TextField nomTF2;
    Label nomLabel2;
    PickerComponent datTF;
  

   


    Button manageButton;

    Form previous;

    public GererEquipe(Form previous) {
        super(AfficherEquipe.currentEquipe == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentEquipe = AfficherEquipe.currentEquipe;

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


        datTF = PickerComponent.createDate(null).label("DateCreation");
        
      




        if (currentEquipe== null) {


            manageButton = new Button("Ajouter");
        } else {


            nomTF.setText(currentEquipe.getNom());
              nomTF2.setText(currentEquipe.getDescription());
            datTF.getPicker().setDate(currentEquipe.getDatecreation());
           
           




            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(


                nomLabel, nomTF,
                 nomLabel2, nomTF2,
                datTF, 
                manageButton
        );

        this.addAll(container);
    }
    
 

    private void addActions() {

   if (currentEquipe == null) {
    manageButton.addActionListener(action -> {
        if (controleDeSaisie()) {
            int responseCode = EquipeService.getInstance().add(
                    new Equipe(
                            nomTF.getText(),
                              nomTF.getText(),
                            datTF.getPicker().getDate()
                          
                    ),
                    ""
            );
            if (responseCode == 200) {
              //  EmailSender.sendEmail(); // call the email sending method here
                Dialog.show("Succés", "Equipe ajouté avec succes", new Command("Ok"));
                showBackAndRefresh();
            } else {
                Dialog.show("Erreur", "Erreur d'ajout de Equipe. Code d'erreur : " + responseCode, new Command("Ok"));
            }
        }
    });
} else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = EquipeService.getInstance().edit(
                            new Equipe(
                                    currentEquipe.getId(),
                                     nomTF.getText(),
                                     nomTF2.getText(),
                                    datTF.getPicker().getDate()
                                  
                                 

                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "Equipe modifié avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else if (responseCode == 250) {
                        Dialog.show("Error", "Heure invalide", new Command("Ok"));
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de Equipe. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh() {
        ((AfficherEquipe) previous).refresh();
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

        if (datTF.getPicker().getDate() == null) {
            Dialog.show("Avertissement", "Veuillez saisir la dat", new Command("Ok"));
            return false;
        }

 
       

        return true;
    }
    
}
