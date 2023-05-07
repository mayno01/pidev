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
import com.pdev.entities.User;
import com.pdev.gui.Login;
import com.pdev.services.UserService;

/**
 *
 * This class is used to register new users
 */
public class RegisterUser extends Form {

    TextField nomTF;
    Label nomLabel;
    TextField passwordTF;
    Label passwordLabel;
    TextField emailTF;
    Label emailLabel;

    Button registerButton;

    Form previous;

    public RegisterUser(Form previous) {
        super("Register User", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

         getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> {
    LoginUser loginUser = new LoginUser(new Login()); // create an instance of the LoginUser form with a Login object as an argument
    loginUser.show(); // show the LoginUser form
});
    }

    private void addGUIs() {
        
         emailLabel = new Label("Email: ");
        emailLabel.setUIID("labelDefault");
        emailTF = new TextField();
        emailTF.setHint("Enter your email address");
        
        
        nomLabel = new Label("Username: ");
        nomLabel.setUIID("labelDefault");
        nomTF = new TextField();
        nomTF.setHint("Enter your username");

        passwordLabel = new Label("Password: ");
        passwordLabel.setUIID("labelDefault");
        passwordTF = new TextField();
        passwordTF.setHint("Enter your password");
        passwordTF.setConstraint(TextField.PASSWORD);

       

        registerButton = new Button("Register");
        registerButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(
                  emailLabel, emailTF,
                nomLabel, nomTF,
                passwordLabel, passwordTF,
              
                registerButton
        );

        this.addAll(container);
    }

    private void addActions() {
        registerButton.addActionListener(action -> {
            if (controleDeSaisie()) {
                int responseCode = UserService.getInstance().register(
                        new User(
                                emailTF.getText(),
                                nomTF.getText(),
                                passwordTF.getText()
                                
                        )
                );
               
                         

                if (responseCode == 200) {
                    Dialog.show("Success", "User registered successfully", new Command("Ok"));
                      new LoginUser(new Login()).show();
                } else {
                    Dialog.show("Error", "Error while registering user. Error code: " + responseCode, new Command("Ok"));
                }
            }
        });
    }

 
    private void showBackAndRefresh() {
        ((LoginUser) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {
        if (nomTF.getText().equals("")) {
            Dialog.show("Warning", "Username field is empty", new Command("Ok"));
            return false;
        }

        if (passwordTF.getText().equals("")) {
            Dialog.show("Warning", "Password field is empty", new Command("Ok"));
            return false;
        }

        if (emailTF.getText().equals("")) {
            Dialog.show("Warning", "Email field is empty", new Command("Ok"));
            return false;
        }

        return true;
    }

}
