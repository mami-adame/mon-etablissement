package com.groupeisi.monetablissement.controllers;

import com.groupeisi.monetablissement.Dao.ProfesseurDAO;
import com.groupeisi.monetablissement.models.Prof;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ProfesseurController {
    ProfesseurDAO dao = new ProfesseurDAO();

    @FXML
    private TableColumn<Prof, Integer> idColumn;

    @FXML
    private TextField nom;

    @FXML
    private TableColumn<Prof, String> nomColumn;

    @FXML
    private TextField prenom;

    @FXML
    private TableColumn<Prof, String> prenomColumn;

    @FXML
    private TableView<Prof> professeurTable;

    @FXML
    private Button retourProf;

    private ObservableList<Prof> professeurList = FXCollections.observableArrayList();


    public void afficherProfesseurs() {
        professeurList.clear();
        professeurList.addAll(dao.getProfesseurs());
        professeurTable.setItems(professeurList);


        List<Prof> profs = dao.getProfesseurs();
        System.out.println("Professeurs récupérés : " + profs.size());  // Ajoutez cette ligne pour vérifier si des profs sont récupérés
        professeurList.addAll(profs);


    }


    @FXML
    void ajouterProfesseur(ActionEvent event) {
        Prof prof = new Prof();
        prof.setNom(nom.getText());
        prof.setPrenom(prenom.getText());
        dao.ajouterProfesseur(prof);
        afficherProfesseurs();  // Recharger la liste après ajout
    }


    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        afficherProfesseurs(); // Charger les professeurs au démarrage
    }

    @FXML
    void supprimerProfesseur(ActionEvent event) {
        Prof selectedProf = professeurTable.getSelectionModel().getSelectedItem();
        if (selectedProf != null) {
            dao.supprimerProfesseur(selectedProf.getId());
            professeurList.remove(selectedProf);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner un Professeur à supprimer.");
            alert.showAndWait();
        }
    }

    @FXML
    private void retournerAuTableau() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/groupeisi/monetablissement/view/TableauDesGestions.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) retourProf.getScene().getWindow(); // Récupérer la fenêtre actuelle
            stage.setScene(scene);
            stage.setTitle("Tableau de Gestion");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
