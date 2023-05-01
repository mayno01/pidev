package gui;


import entite.Abonnement;
import gui.MainWindowbackController;
import service.AbonnementService;
import util.AlertUtils;
import util.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageControllerba implements Initializable {

    @FXML
    public TextField typeTF;
    @FXML
    public TextField titreTF;
    @FXML
    public TextField prixTF;
    @FXML
    public TextField dureeTF;
    @FXML
    public TextField niveauAccessTF;
    @FXML
    public TextField reservationsTotalTF;
    @FXML
    public TextField reservationsRestantesTF;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Abonnement currentAbonnement;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        currentAbonnement = ShowAllControllerba.currentAbonnement;

        if (currentAbonnement != null) {
            topText.setText("Modifier abonnement");
            btnAjout.setText("Modifier");

            try {
                typeTF.setText(currentAbonnement.getType());
                titreTF.setText(currentAbonnement.getTitre());
                prixTF.setText(String.valueOf(currentAbonnement.getPrix()));
                dureeTF.setText(String.valueOf(currentAbonnement.getDuree()));
                niveauAccessTF.setText(currentAbonnement.getNiveauAccess());
                reservationsTotalTF.setText(String.valueOf(currentAbonnement.getReservationsTotal()));
                reservationsRestantesTF.setText(String.valueOf(currentAbonnement.getReservationsRestantes()));

            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            topText.setText("Ajouter abonnement");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {

            Abonnement abonnement = new Abonnement(
                    typeTF.getText(),
                    titreTF.getText(),
                    Float.parseFloat(prixTF.getText()),
                    Integer.parseInt(dureeTF.getText()),
                    niveauAccessTF.getText(),
                    Integer.parseInt(reservationsTotalTF.getText()),
                    Integer.parseInt(reservationsRestantesTF.getText())
            );

            if (currentAbonnement == null) {
                if (AbonnementService.getInstance().add(abonnement)) {
                    AlertUtils.makeSuccessNotification("Abonnement ajouté avec succés");
                    MainWindowbackController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_ABONNEMENT);
                } else {
                    AlertUtils.makeError("Erreur");
                }
            } else {
                abonnement.setId(currentAbonnement.getId());
                if (AbonnementService.getInstance().edit(abonnement)) {
                    AlertUtils.makeSuccessNotification("Abonnement modifié avec succés");
                    ShowAllControllerba.currentAbonnement = null;
                    MainWindowbackController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_ABONNEMENT);
                } else {
                    AlertUtils.makeError("Erreur");
                }
            }

        }
    }


    private boolean controleDeSaisie() {


        if (typeTF.getText().isEmpty()) {
            AlertUtils.makeInformation("type ne doit pas etre vide");
            return false;
        }


        if (titreTF.getText().isEmpty()) {
            AlertUtils.makeInformation("titre ne doit pas etre vide");
            return false;
        }


        if (prixTF.getText().isEmpty()) {
            AlertUtils.makeInformation("prix ne doit pas etre vide");
            return false;
        }


        try {
            Float.parseFloat(prixTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("prix doit etre un réel");
            return false;
        }
        if (dureeTF.getText().isEmpty()) {
            AlertUtils.makeInformation("duree ne doit pas etre vide");
            return false;
        }


        try {
            Integer.parseInt(dureeTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("duree doit etre un nombre");
            return false;
        }

        if (niveauAccessTF.getText().isEmpty()) {
            AlertUtils.makeInformation("niveauAccess ne doit pas etre vide");
            return false;
        }


        if (reservationsTotalTF.getText().isEmpty()) {
            AlertUtils.makeInformation("reservationsTotal ne doit pas etre vide");
            return false;
        }


        try {
            Integer.parseInt(reservationsTotalTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("reservationsTotal doit etre un nombre");
            return false;
        }

        if (reservationsRestantesTF.getText().isEmpty()) {
            AlertUtils.makeInformation("reservationsRestantes ne doit pas etre vide");
            return false;
        }


        try {
            Integer.parseInt(reservationsRestantesTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("reservationsRestantes doit etre un nombre");
            return false;
        }

        return true;
    }
}