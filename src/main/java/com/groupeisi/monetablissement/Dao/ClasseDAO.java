package com.groupeisi.monetablissement.Dao;

import com.groupeisi.monetablissement.models.Classe;
import com.groupeisi.monetablissement.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClasseDAO {
    public void ajouterClasse(Classe classe) {
        String query = "INSERT INTO Classe (nom) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, classe.getNom());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Classe> getClasses() {
        List<Classe> classes = new ArrayList<>();
        String query = "SELECT * FROM Classe";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                classes.add(new Classe(rs.getInt("id"), rs.getString("nom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public void supprimerClasse(int id) {
        String query = "DELETE FROM Classe WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
