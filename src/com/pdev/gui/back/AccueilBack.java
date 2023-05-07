package com.pdev.gui.back;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.pdev.gui.Login;

public class AccueilBack extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;

    public AccueilBack() {
        super("Menu", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
    }

    private void addGUIs() {
        label = new Label("Admin"/*MainApp.getSession().getEmail()*/);
        label.setUIID("links");
        Button btnDeconnexion = new Button();
        btnDeconnexion.setMaterialIcon(FontImage.MATERIAL_ARROW_FORWARD);
        btnDeconnexion.addActionListener(action -> {
            Login.loginForm.showBack();
        });

        Container userContainer = new Container(new BorderLayout());
        userContainer.setUIID("userContainer");
        userContainer.add(BorderLayout.CENTER, label);
        userContainer.add(BorderLayout.EAST, btnDeconnexion);

        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.addAll(
                userContainer,
            // makenewbutton
              
                makeUserButton(),
                makeEquipeButton(),
                makeReclamationButton(),
                makeFormationButton(),
                makeEventsButton()
                
        );

        this.add(menuContainer);
    }

 

    private Button makeEventsButton() {
        Button button = new Button("Events");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOK);
        button.addActionListener(action -> new AfficherEvents(this).show());
        return button;
    }
    
  
     
       private Button makeReclamationButton() {
        Button button = new Button("Reclamation");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOK);
        button.addActionListener(action -> new AfficherReclamations(this).show());
        return button;
    }
       
           private Button makeEquipeButton() {
        Button button = new Button("Equipe");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOK);
        button.addActionListener(action -> new AfficherEquipe(this).show());
        return button;
    }
   
                   

        private Button makeUserButton() {
        Button button = new Button("users");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOK);
        button.addActionListener(action -> new AfficherUser(this).show());
        return button;
    }
        
              private Button makeFormationButton() {
        Button button = new Button("Formation");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOK);
        button.addActionListener(action -> new AfficherFormation(this).show());
        return button;
    }

}
