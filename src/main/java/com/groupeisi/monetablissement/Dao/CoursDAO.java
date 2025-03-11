package com.groupeisi.monetablissement.Dao;

import com.groupeisi.monetablissement.models.Cours;
import com.groupeisi.monetablissement.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDAO {


    public void ajouterCours(String nom, int profId) {
        String query = "INSERT INTO cours (nom, prof_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom);
            stmt.setInt(2, profId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    public void ajouterCours(Cours cours) {
//        String query = "INSERT INTO cours (nom, prof_id) VALUES (?, ?)";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, cours.getNom());
//            stmt.setInt(2, cours.getProf_id());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public List<Cours> getCours() {
        List<Cours> coursList = new ArrayList<>();
        String query = "SELECT * FROM cours";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                coursList.add(new Cours(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("prof_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coursList;
    }

    public void supprimerCours(int id) {
        String query = "DELETE FROM cours WHERE id = ?";
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
//import com.groupeisi.monetablissement.models.Cours;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.groupeisi.monetablissement.models.Cours;
//import com.groupeisi.monetablissement.utils.DatabaseConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import java.sql.*;
//        import java.util.ArrayList;
//import java.util.List;
//
//public class CoursDAO {
//    public void ajouterCours(String nom, int profId) {
//        String query = "INSERT INTO cours (nom,prof_id) VALUES ( ?, ?)";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            //stmt.setInt(3, Id);
//            stmt.setString(1, nom);
//            stmt.setInt(2, profId);  // Utiliser le paramètre `profId`
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public List<Cours> getCours() {
//        List<Cours> coursList = new ArrayList<>();
//        String query = "SELECT * FROM cours";
//        try (Connection conn = DatabaseConnection.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(query)) {
//            while (rs.next()) {
//                coursList.add(new Cours(
//                        rs.getInt("id"),
//                        rs.getString("nom"),
//                        rs.getInt("prof_id")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return coursList;
//    }
//
//    public void supprimerCours(int id) {
//        String query = "DELETE FROM cours WHERE id = ?";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setInt(1, id);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void ajouterCours(Cours cours) {
//    }
//}

