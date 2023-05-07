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
import com.pdev.gui.Login;
import com.pdev.services.UserService;

/**
 * This class is used to reset the password for a user.
 */
public class ForgetPassword extends Form {

    private TextField emailTF;
    private Button resetButton;

    private Form previous;

    public ForgetPassword(Form previous) {
        super("Reset Password", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

           getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> {
    LoginUser loginUser = new LoginUser(new Login()); // create an instance of the LoginUser form with a Login object as an argument
    loginUser.show(); // show the LoginUser form
});
    }

    private void addGUIs() {
        emailTF = new TextField();
        emailTF.setHint("Enter your email address");

        resetButton = new Button("Reset Password");
        resetButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(
                new Label("Enter your email address:"),
                emailTF,
                resetButton
        );

        this.addAll(container);
    }

    private void addActions() {
    resetButton.addActionListener(action -> {
    String email = emailTF.getText();
    if (email.isEmpty()) {
        Dialog.show("Warning", "Email field is empty", new Command("Ok"));
        return;
    }

    UserService.getInstance().forgetPassword(email); // Call forgetPassword method

    Dialog.show("Success", "Password sent successfully", new Command("Ok"));
       new LoginUser(new Login()).show();
});

    }
}
