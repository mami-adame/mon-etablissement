package com.groupeisi.monetablissement.controllers;

import com.groupeisi.monetablissement.Dao.ClasseDAO;
import com.groupeisi.monetablissement.models.Classe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClasseController implements Initializable {
    ClasseDAO dao = new ClasseDAO();

    @FXML
    private TableView<Classe> classeTable;

    @FXML
    private TableColumn<Classe, Integer> idColumn;

    @FXML
    private TableColumn<Classe, String> nomColumn;

    @FXML
    private Button retourClasse;

    @FXML
    private TextField nom;

    private ObservableList<Classe> classeList = FXCollections.observableArrayList();

    @FXML
    void ajouterClasse(ActionEvent event) {
        String nomClasse = nom.getText().trim();
        if (!nomClasse.isEmpty()) {
            Classe nouvelleClasse = new Classe(0, nomClasse);
            dao.ajouterClasse(nouvelleClasse);
            nom.clear();
            loadClasses(); // Rafraîchir la liste
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez entrer un nom de classe.");
            alert.showAndWait();
        }
    }

    @FXML
    void supprimerClasse(ActionEvent event) {
        Classe selectedClasse = classeTable.getSelectionModel().getSelectedItem();
        if (selectedClasse != null) {
            dao.supprimerClasse(selectedClasse.getId());
            loadClasses(); // Rafraîchir la liste
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner une classe à supprimer.");
            alert.showAndWait();
        }
    }

    private void loadClasses() {
        List<Classe> classes = dao.getClasses();
        classeList.setAll(classes);
        classeTable.setItems(classeList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nomColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNom()));
        loadClasses();
    }

    @FXML
    private void retournerAuTableau() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/groupeisi/monetablissement/view/TableauDesGestions.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) retourClasse.getScene().getWindow(); // Récupérer la fenêtre actuelle
            stage.setScene(scene);
            stage.setTitle("Tableau de Gestion");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}