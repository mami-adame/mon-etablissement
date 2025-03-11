package com.groupeisi.monetablissement.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TableauDesGestionsController {

    @FXML
    private Button gestionClasse;

    @FXML
    private Button gestionCours;

    @FXML
    private Button gestionEtudiant;

    @FXML
    private Button gestionProf;



    // Méthode pour changer de scène
    private void changerDeScene(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/groupeisi/monetablissement/view/" + fxmlFile));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) gestionClasse.getScene().getWindow(); // Récupère la fenêtre actuelle
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void afficherGestionClasses() {
        changerDeScene("Classes.fxml", "Gestion des Classes");
    }

    @FXML
    private void afficherGestionCours() {
        changerDeScene("Cours.fxml", "Gestion des Cours");
    }

    @FXML
    private void afficherGestionProfesseurs() {
        changerDeScene("Professeurs.fxml", "Gestion des Professeurs");
    }

    @FXML
    private void afficherGestionEtudiants() {
        changerDeScene("Etudiants.fxml", "Gestion des Étudiants");
    }
}



