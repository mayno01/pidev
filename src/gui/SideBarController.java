package gui;

import gui.MainWindowbackController;
import gui.MainApp;
import util.Animations;
import util.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.Cursor;


import java.net.URL;
import java.util.ResourceBundle;

public class SideBarController implements Initializable {

    private final Color COLOR_GRAY = new Color(0.9, 0.9, 0.9, 1);
    private final Color COLOR_PRIMARY = Color.web("#000000");
    private final Color COLOR_DARK = new Color(1, 1, 1, 0.65);
    private Button[] liens;

    @FXML
    private Button btnAbonnements;
    @FXML
    private Button btnReservations;
    @FXML
    private AnchorPane mainComponent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        liens = new Button[]{
                btnAbonnements,
                btnReservations,
        };

        mainComponent.setBackground(new Background(new BackgroundFill(COLOR_PRIMARY, CornerRadii.EMPTY, Insets.EMPTY)));

        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            lien.setBackground(new Background(new BackgroundFill(COLOR_PRIMARY, CornerRadii.EMPTY, Insets.EMPTY)));
            Animations.animateButton(lien, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
        }
        btnAbonnements.setTextFill(Color.WHITE);
        btnReservations.setTextFill(Color.WHITE);
        btnAbonnements.setCursor(Cursor.HAND);
        btnReservations.setCursor(Cursor.HAND);

    }

    @FXML
    private void afficherAbonnements(ActionEvent event) {
        goToLink(Constants.FXML_BACK_DISPLAY_ALL_ABONNEMENT);

        btnAbonnements.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnAbonnements, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }

    @FXML
    private void afficherReservations(ActionEvent event) {
        goToLink(Constants.FXML_BACK_DISPLAY_ALL_RESERVATION);

        btnReservations.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnReservations, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }

    private void goToLink(String link) {
        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            Animations.animateButton(lien, COLOR_GRAY, COLOR_DARK, COLOR_PRIMARY, 0, false);
        }
        MainWindowbackController.getInstance().loadInterface(link);
    }

    @FXML
    public void logout(ActionEvent actionEvent) {
        MainApp.getInstance().logout();
    }
}
