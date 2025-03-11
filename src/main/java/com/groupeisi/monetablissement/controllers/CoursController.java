package com.groupeisi.monetablissement.controllers;

import com.groupeisi.monetablissement.Dao.CoursDAO;
import com.groupeisi.monetablissement.models.Cours;
import com.groupeisi.monetablissement.models.Prof;
import com.groupeisi.monetablissement.utils.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CoursController implements Initializable {
    private CoursDAO dao = new CoursDAO();

    @FXML
    private ComboBox<Prof> profComboBox;

    @FXML
    private TableView<Cours> coursTable;

    @FXML
    private TableColumn<Cours, Integer> idColumn;

    @FXML
    private TableColumn<Cours, String> nomColumn;

    @FXML
    private TableColumn<Cours, Integer> profidColumn;

    @FXML
    private TextField nomcours;

    @FXML
    private Button retourCours;

    private ObservableList<Cours> coursList = FXCollections.observableArrayList();
    private ObservableList<Prof> profsList = FXCollections.observableArrayList();

    @FXML
    void ajouterCours(ActionEvent event) {
        String nom = nomcours.getText();
        Prof selectedProf = profComboBox.getValue();

        if (nom.isEmpty() || selectedProf == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }

        Cours cours = new Cours();
        cours.setNom(nom);
        cours.setProf_id(selectedProf.getId());

        dao.ajouterCours(cours.getNom(), cours.getProf_id());

        nomcours.clear();
        profComboBox.getSelectionModel().clearSelection();
        loadCours(); // Rafraîchir le tableau
    }

    @FXML
    void supprimerCours(ActionEvent event) {
        Cours selectedCours = coursTable.getSelectionModel().getSelectedItem();
        if (selectedCours != null) {
            dao.supprimerCours(selectedCours.getId());
            coursList.remove(selectedCours);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner un cours à supprimer.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        loadProfessors();
        loadCours();
    }

    private void initializeTable() {
        idColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nomColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNom()));
        profidColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getProf_id()).asObject());

        coursTable.setItems(coursList);
    }

    private void loadCours() {
        coursList.clear();
        coursList.addAll(dao.getCours());
    }

    private void loadProfessors() {
        String SQL = "SELECT * FROM prof";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                profsList.add(new Prof(rs.getInt("id"), rs.getString("nom")));
            }

            profComboBox.setItems(profsList);
            profComboBox.setCellFactory(param -> new ListCell<Prof>() {
                @Override
                protected void updateItem(Prof item, boolean empty) {
                    super.updateItem(item, empty);
                    setText((empty || item == null) ? null : item.getNom());
                }
            });

            profComboBox.setConverter(new StringConverter<Prof>() {
                @Override
                public String toString(Prof object) {
                    return (object != null) ? object.getNom() : "";
                }

                @Override
                public Prof fromString(String s) {
                    return profsList.stream()
                            .filter(prof -> prof.getNom().equals(s))
                            .findFirst()
                            .orElse(null);
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retournerAuTableau() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/groupeisi/monetablissement/view/TableauDesGestions.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) retourCours.getScene().getWindow(); // Récupérer la fenêtre actuelle
            stage.setScene(scene);
            stage.setTitle("Tableau de Gestion");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}







































//package com.groupeisi.monetablissement.controllers;
//
//import com.groupeisi.monetablissement.Dao.CoursDAO;
//import com.groupeisi.monetablissement.models.Cours;
//import com.groupeisi.monetablissement.models.Prof;
//import com.groupeisi.monetablissement.utils.DatabaseConnection;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.util.StringConverter;
//
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//public class CoursController implements Initializable {
//    private Connection con;
//    private CoursDAO dao = new CoursDAO();
//
//    @FXML
//    private ComboBox<Prof> profComboBox;  // Correction du typage
//
//    @FXML
//    private TableView<Cours> coursTable;
//
//    @FXML
//    private TableColumn<Cours, Integer> idColumn;
//
//    @FXML
//    private TextField nomcours;
//
//    @FXML
//    private TableColumn<Cours, String> nomColumn;
//
//    @FXML
//    private TableColumn<Cours, Integer> profidColumn;
//
//    private ObservableList<Prof> profsList = FXCollections.observableArrayList();
//
//    @FXML
//    void ajouterCours(ActionEvent event) {
//        Cours cours = new Cours();
//        cours.setNom(nomcours.getText());
//
//        Prof selectedProf = profComboBox.getValue();
//        if (selectedProf != null) {
//            cours.setProf_id(selectedProf.getId()); // Correction ici
//        } else {
//            cours.setProf_id(0);  // Valeur par défaut si aucun prof sélectionné
//        }
//
//        dao.ajouterCours(cours);
//    }
//
//    @FXML
//    void supprimerCours(ActionEvent event) {
//        Cours selectedCours = coursTable.getSelectionModel().getSelectedItem();
//        if (selectedCours != null) {
//            dao.supprimerCours(selectedCours.getId());
//            coursTable.getItems().remove(selectedCours);
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Suppression impossible");
//            alert.setHeaderText(null);
//            alert.setContentText("Veuillez sélectionner un cours à supprimer.");
//            alert.showAndWait();
//        }
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        loadProfessors();
//    }
//
//    private void loadProfessors() {
//        String SQL = "SELECT * FROM prof";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement statement = conn.prepareStatement(SQL);
//             ResultSet rs = statement.executeQuery()) {
//
//            while (rs.next()) {
//                profsList.add(new Prof(rs.getInt("id"), rs.getString("nom")));
//            }
//
//            profComboBox.setItems(profsList);
//            profComboBox.setCellFactory(param -> new ListCell<Prof>() {
//                @Override
//                protected void updateItem(Prof item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText((empty || item == null) ? null : item.getNom());
//                }
//            });
//
//            profComboBox.setConverter(new StringConverter<Prof>() {
//                @Override
//                public String toString(Prof object) {
//                    return (object != null) ? object.getNom() : "";
//                }
//
//                @Override
//                public Prof fromString(String s) {
//                    return profsList.stream()
//                            .filter(prof -> prof.getNom().equals(s))
//                            .findFirst()
//                            .orElse(null);
//                }
//            });
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}



















//package com.groupeisi.monetablissement.controllers;
//
//import com.groupeisi.monetablissement.Dao.CoursDAO;
//import com.groupeisi.monetablissement.models.Classe;
//import com.groupeisi.monetablissement.models.Cours;
//import com.groupeisi.monetablissement.models.Prof;
//import com.groupeisi.monetablissement.utils.DatabaseConnection;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.util.StringConverter;
//
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//public class CoursController {
//    Connection con;
//    private DatabaseConnection db;
//    CoursDAO dao = new CoursDAO();
//
//    @FXML
//    private ComboBox<?> profComboBox;
//
//    @FXML
//    private TableView<?> coursTable;
//
//    @FXML
//    private TableColumn<?, ?> idColumn;
//
//    @FXML
//    private TextField nomcours;
//
//    @FXML
//    private TableColumn<?, ?> nomColumn;
//
//    @FXML
//    private TableColumn<?, ?> profidColumn;
//
//    @FXML
//    void ajouterCours(ActionEvent event) {
//        Cours cours = new Cours();
//        cours.setNom(nomcours.getText());
//        cours.setProf_id(0);
//
//        dao.ajouterCours(cours);
//
//    }
//
//    @FXML
//    void supprimerCours(ActionEvent event) {
//
//    }
//
//
//
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        this.db = new DatabaseConnection();
//        //afficherEtudiants(); // Charger les étudiants au démarrage
//        ObservableList<Classe> list = FXCollections.observableArrayList();
//        String SQL = "SELECT * FROM prof";
//        PreparedStatement statement = null;
//        try {
//            Connection conn = DatabaseConnection.getConnection();
//            statement = conn.prepareStatement(SQL);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                list.add(new Classe(rs.getInt("id"), rs.getString("nom")));
//            }
//            this.profComboBox.setItems(list);
//            this.profComboBox.setCellFactory(param -> new ListCell<Prof>() {
//                @Override
//                protected void updateItem(Classe item, boolean empty) {
//                    super.updateItem(item, empty);
//                    if (empty) {
//                        setText(null);
//                    } else {
//                        setText(item.getNom());
//                    }
//                }
//            });
//            this.profComboBox.setConverter(new StringConverter<Prof>() {
//                @Override
//                public Prof fromString(String s) {
//                    return list.stream().filter(prof -> prof.getId().equals(s)).findFirst().orElse(null);
//                }
//
//                @Override
//                public String toString(Prof object) {
//                    if (object!= null) {
//                        return object.getNom();
//                    } else {
//                        return "";
//                    }
//                }
//            });
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//
//
//
//
//}