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

public class LoginUser extends Form {

    private TextField emailTF;
    private TextField passwordTF;
    private Button loginButton;

    private Login loginForm;

    public LoginUser(Login loginForm) {
        super("Login", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
        addActions();
        this.loginForm = loginForm;
    }

    private void addGUIs() {
        Label emailLabel = new Label("Email: ");
        emailLabel.setUIID("labelDefault");
        emailTF = new TextField();
        emailTF.setHint("Enter your email");

        Label passwordLabel = new Label("Password: ");
        passwordLabel.setUIID("labelDefault");
        passwordTF = new TextField();
        passwordTF.setHint("Enter your password");
        passwordTF.setConstraint(TextField.PASSWORD);

        loginButton = new Button("Login");
        loginButton.setUIID("buttonWhiteCenter");

        Button registerButton = new Button("Register");
        registerButton.setUIID("buttonWhiteCenter");
        
           Button forgetPasswordButton = new Button("Forget Password");
        forgetPasswordButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(
                emailLabel, emailTF,
                passwordLabel, passwordTF,
                loginButton,
                registerButton,
                forgetPasswordButton
        );

        this.addAll(container);

        registerButton.addActionListener(action -> {
            new RegisterUser(new Form()).show();
        });
        
        forgetPasswordButton.addActionListener(action -> {
            new ForgetPassword(new Form()).show();
        });
    }

    private void addActions() {
        loginButton.addActionListener(action -> {
            String email = emailTF.getText();
            String password = passwordTF.getText();
            if (!email.isEmpty() && !password.isEmpty()) {
                User user = UserService.getInstance().login(email, password);
                if (user != null) {
                    // login successful, navigate to next form
                    new AccueilBack().show();
                    // close the current login form
                    this.showBack();
                } else {
                    Dialog.show("Error", "Invalid email or password", new Command("Ok"));
                }
            } else {
                Dialog.show("Warning", "Email and password are required", new Command("Ok"));
            }
        });
    }

    public void refresh() {
        // Reset the text fields
        emailTF.setText("");
        passwordTF.setText("");
    }

}
