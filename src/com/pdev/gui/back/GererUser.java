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
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.pdev.entities.User;
import com.pdev.services.GererUserService;

/**
 *
 * @author aminh
 */
public class GererUser extends Form {
    
        Resources theme1 = UIManager.initFirstTheme("/theme");
    
     
    User currentUser;

    TextField emailTF;
    Label emailLabel;
    
       TextField nomUserTF;
    Label nomUserLabel;


    Button manageButton;
    
     

    Form previous;

    public GererUser(Form previous) {
        super(AfficherUser.currentUser == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentUser = AfficherUser.currentUser;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {





        emailLabel = new Label("email : ");
        emailLabel.setUIID("labelDefault");
        emailTF = new TextField();
        emailTF.setHint("Tapez le email");
        
        
        nomUserLabel = new Label("nom: ");
        nomUserLabel.setUIID("labelDefault");
        nomUserTF = new TextField();
        nomUserTF.setHint("Tapez le nom");
        
        
        








        if (currentUser== null) {


            manageButton = new Button("Ajouter");
        } else {


                emailTF.setText(currentUser.getEmail());

                       nomUserTF.setText(currentUser.getNom());

                   

        
           




            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");
 
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    
        container.setUIID("containerRounded");

        container.addAll(


                emailLabel, emailTF,
                nomUserLabel, nomUserTF,
            
          
                manageButton
        );

        this.addAll(container);
    }
    
 

    private void addActions() {
        

        
        
        
        
        
        
        
   if (currentUser == null) {
       
       
    manageButton.addActionListener(action -> {
        if (controleDeSaisie()) {
            int responseCode = GererUserService.getInstance().add(
                    new User(
                     
                      
                            emailTF.getText(),
                            nomUserTF.getText()
                           
                            
                            
                          
                    ),
                    ""
            );
            if (responseCode == 200) {
               // EmailSender.sendEmail(); // call the email sending method here
                Dialog.show("Succés", "User ajouté avec succes", new Command("Ok"));
                showBackAndRefresh();
            } else {
                Dialog.show("Erreur", "Erreur d'ajout de User. Code d'erreur : " + responseCode, new Command("Ok"));
            }
        }
    });
} else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = GererUserService.getInstance().edit(
                            new User(
                                    currentUser.getId(),
                                
                                      emailTF.getText(),
                            nomUserTF.getText()
                                
                                  
                         
                                 

                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "User modifié avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else if (responseCode == 250) {
                        Dialog.show("Error", "Heure invalide", new Command("Ok"));
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de User. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh() {
        ((AfficherUser) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {


        if (emailTF.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }
        
          if (nomUserTF.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }
      



       

        return true;
    }
    
}
