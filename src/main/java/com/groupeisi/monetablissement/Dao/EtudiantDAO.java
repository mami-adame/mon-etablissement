package com.groupeisi.monetablissement.Dao;

import com.groupeisi.monetablissement.models.Etudiant;
import com.groupeisi.monetablissement.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO {

    public void ajouterEtudiant(Etudiant etudiant) {
        String query = "INSERT INTO Etudiant (nom, prenom, classe_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, etudiant.getNom());
            stmt.setString(2, etudiant.getPrenom());
            stmt.setInt(3, etudiant.getClasse_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Etudiant> getAllEtudiants() {
        List<Etudiant> etudiantList = new ArrayList<>();
        // Jointure avec la table Classe pour récupérer le nom de la classe
        String SQL = "SELECT e.id, e.nom, e.prenom, e.classe_id, c.nom AS classeNom FROM etudiant e " +
                "JOIN classe c ON e.classe_id = c.id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(rs.getInt("id"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setClasse_id(rs.getInt("classe_id"));
                etudiant.setClasseNom(rs.getString("classeNom")); // Récupération du nom de la classe
                etudiantList.add(etudiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiantList;
    }


public void supprimerEtudiant(int id) {
        String query = "DELETE FROM etudiant WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



//package com.groupeisi.monetablissement.Dao;
//
//import com.groupeisi.monetablissement.models.Etudiant;
//import com.groupeisi.monetablissement.utils.DatabaseConnection;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EtudiantDAO {
//    public void ajouterEtudiant(Etudiant etudiant) {
//        String query = "INSERT INTO Etudiant (nom, prenom, classe_id) VALUES (?, ?, ?)";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, etudiant.getNom());
//            stmt.setString(2, etudiant.getPrenom());
//            stmt.setInt(3, etudiant.getClasse_id());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public List<Etudiant> getAllEtudiants() {
//        List<Etudiant> etudiantList = new ArrayList<>();
//        String SQL = "SELECT e.id, e.nom, e.prenom, c.nom AS classe_nom FROM etudiant e JOIN classe c ON e.classe_id = c.id";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement statement = conn.prepareStatement(SQL);
//             ResultSet rs = statement.executeQuery()) {
//
//            while (rs.next()) {
//                Etudiant etudiant = new Etudiant();
//                etudiant.setId(rs.getInt("id"));
//                etudiant.setNom(rs.getString("nom"));
//                etudiant.setPrenom(rs.getString("prenom"));
//                etudiant.setClasse_id(rs.getInt("classe_id"));
//                etudiant.setClasseNom(rs.getString("classe_nom")); // Nouvelle colonne pour stocker le nom de la classe
//                etudiantList.add(etudiant);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return etudiantList;
//    }
//
//
//    public void supprimerEtudiant(int id) {
//        String query = "DELETE FROM etudiant WHERE id = ?";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setInt(1, id);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
