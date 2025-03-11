package com.groupeisi.monetablissement.controllers;

import com.groupeisi.monetablissement.Dao.EtudiantDAO;
import com.groupeisi.monetablissement.models.Classe;
import com.groupeisi.monetablissement.models.Cours;
import com.groupeisi.monetablissement.models.Etudiant;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EtudiantController implements Initializable {
    private DatabaseConnection db;
    private EtudiantDAO dao = new EtudiantDAO();

    @FXML
    private TableColumn<Etudiant, Integer> idColumn;

    @FXML
    private TableColumn<Etudiant, String> nomColumn;

    @FXML
    private TableColumn<Etudiant, String> prenomColumn;

    @FXML
    private TableColumn<Etudiant, String> classeColumn; // Modifier pour afficher classeNom

    @FXML
    private ComboBox<Classe> classeComboBox;

    @FXML
    private TableView<Etudiant> etudiantTable;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private Button retourEtu;

    private ObservableList<Cours> coursList = FXCollections.observableArrayList();
    private ObservableList<Prof> etudiantList = FXCollections.observableArrayList();


    private void afficherEtudiant() {
        ObservableList<Etudiant> etudiantList = FXCollections.observableArrayList(dao.getAllEtudiants());

        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        prenomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
        classeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClasseNom())); // Affichage du nom de la classe

        etudiantTable.setItems(etudiantList);
    }

    @FXML
    void ajouterEtudiant(ActionEvent event) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(nom.getText());
        etudiant.setPrenom(prenom.getText());
        etudiant.setClasse_id(classeComboBox.getValue().getId());

        dao.ajouterEtudiant(etudiant);
        afficherEtudiant(); // Recharger la table après l'ajout
    }


//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        initializeTable(location, resources);
//        loadEtudiant();
//        loadCours();
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.db = new DatabaseConnection();
        afficherEtudiant(); // Charger les étudiants dès le lancement

        ObservableList<Classe> list = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM Classe";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                list.add(new Classe(rs.getInt("id"), rs.getString("nom")));
            }

            this.classeComboBox.setItems(list);
            this.classeComboBox.setConverter(new StringConverter<Classe>() {
                @Override
                public Classe fromString(String s) {
                    return list.stream().filter(classe -> classe.getNom().equals(s)).findFirst().orElse(null);
                }

                @Override
                public String toString(Classe object) {
                    return (object != null) ? object.getNom() : "";
                }
            });

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    private void loadEtudiant() {
//        coursList.clear();
//        coursList.addAll(dao.getAllEtudiants());
//    }


    @FXML
    void supprimerEtudiant(ActionEvent event) {
        Etudiant selectedEtudiant = etudiantTable.getSelectionModel().getSelectedItem();
        if (selectedEtudiant != null) {
            dao.supprimerEtudiant(selectedEtudiant.getId());
            etudiantList.remove(selectedEtudiant);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner un Etudiant à supprimer.");
            alert.showAndWait();
        }
    }

    @FXML
    private void retournerAuTableau() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/groupeisi/monetablissement/view/TableauDesGestions.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) retourEtu.getScene().getWindow(); // Récupérer la fenêtre actuelle
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
//import com.groupeisi.monetablissement.Dao.EtudiantDAO;
//import com.groupeisi.monetablissement.models.Classe;
//import com.groupeisi.monetablissement.models.Cours;
//import com.groupeisi.monetablissement.models.Etudiant;
//import com.groupeisi.monetablissement.models.Prof;
//import com.groupeisi.monetablissement.utils.DatabaseConnection;
//import javafx.beans.Observable;
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
//public class EtudiantController implements Initializable {
//    Connection con ;
//    private DatabaseConnection db;
//
//
//    EtudiantDAO dao=new EtudiantDAO();
//
//    @FXML
//    private TableColumn<Etudiant, String> classeColumn;
//
//
//    @FXML
//    private ComboBox<Classe> classeComboBox;
//
//    @FXML
//    private TableView<Etudiant> etudiantTable;
//
//    @FXML
//    private TableColumn<Etudiant, Integer> idColumn;
//
//    @FXML
//    private TextField nom;
//
//    @FXML
//    private TableColumn<Etudiant, String> nomColumn;
//
//    @FXML
//    private TextField prenom;
//
//    @FXML
//    private TableColumn<Etudiant, String> prenomColumn;
//
//    private ObservableList<Etudiant> etudiantList = FXCollections.observableArrayList();
//    private ObservableList<Prof> profsList = FXCollections.observableArrayList();
//
//
//
//
//
//    private void afficherEtudiant(){
//        ObservableList<Etudiant> etudiantlist = FXCollections.observableArrayList(dao.getAllEtudiants());
//
////        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
////        nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
////        prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
//
//        etudiantTable.setItems(etudiantlist);
//    };
//
//
//
//    @FXML
//    void ajouterEtudiant(ActionEvent event) {
//        Etudiant etudiant =new Etudiant();
//        etudiant.setNom(nom.getText());
//        etudiant.setPrenom(prenom.getText());
//        etudiant.setClasse_id(classeComboBox.getValue().getId());
//
//        dao.ajouterEtudiant(etudiant);
//       // dao.afficherEtudiants(etudiant); // Charger les étudiants au démarrage
//
//    }
//
//    @FXML
//    void supprimerEtudiant(ActionEvent event) {
//        Etudiant selectedEtudiant = etudiantTable.getSelectionModel().getSelectedItem();
//        if (selectedEtudiant != null) {
//            dao.supprimerEtudiant(selectedEtudiant.getId());
//            etudiantList.remove(selectedEtudiant);
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner un Etudiant à supprimer.");
//            alert.showAndWait();
//        }
//
//
//    }
//
//
////    @Override
////    public void initialize(URL location, ResourceBundle resources) {
////        this.db = new DatabaseConnection();
////        afficherEtudiants(); // Charger les étudiants au démarrage
////    }
//
//
//
//
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        this.db = new DatabaseConnection();
//
//        idColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
//        nomColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNom()));
//        prenomColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPrenom()));
//        //classeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getClasse_id()).asObject().asString());
//        classeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getClasseNom()));
//
//        afficherEtudiant();
//
//
//
//        //afficherEtudiants(); // Charger les étudiants au démarrage
//        ObservableList<Classe> list = FXCollections.observableArrayList();
//        String SQL = "SELECT * FROM Classe";
//        PreparedStatement statement = null;
//        try {
//            Connection conn = DatabaseConnection.getConnection();
//            statement = conn.prepareStatement(SQL);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                list.add(new Classe(rs.getInt("id"), rs.getString("nom")));
//            }
//            this.classeComboBox.setItems(list);
//            this.classeComboBox.setCellFactory(param -> new ListCell<Classe>() {
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
//            this.classeComboBox.setConverter(new StringConverter<Classe>() {
//                @Override
//                public Classe fromString(String s) {
//                    return list.stream().filter(classe -> classe.getNom().equals(s)).findFirst().orElse(null);
//                }
//
//                @Override
//                public String toString(Classe object) {
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
//}
