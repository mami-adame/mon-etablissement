package com.groupeisi.monetablissement.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.groupeisi.monetablissement.models.Prof;
import com.groupeisi.monetablissement.utils.DatabaseConnection;

import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

public class ProfesseurDAO {
    public void ajouterProfesseur(Prof professeur) {
        String query = "INSERT INTO prof (nom, prenom) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, professeur.getNom());
            stmt.setString(2, professeur.getPrenom());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Prof> getProfesseurs() {
        List<Prof> professeurs = new ArrayList<>();
        String query = "SELECT * FROM prof";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                professeurs.add(new Prof(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professeurs;
    }

    public void supprimerProfesseur(int id) {
        String query = "DELETE FROM Professeur WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

