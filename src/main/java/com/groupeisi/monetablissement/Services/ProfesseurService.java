package com.groupeisi.monetablissement.Services;

import com.groupeisi.monetablissement.Dao.ProfesseurDAO;

import java.util.List;

import com.groupeisi.monetablissement.Dao.ProfesseurDAO;
import com.groupeisi.monetablissement.models.Prof;

import java.util.List;

public class ProfesseurService {
    private final ProfesseurDAO professeurDAO = new ProfesseurDAO();

    public void ajouterProfesseur(String nom, String prenom) {
        if (nom.isEmpty()) {
            throw new IllegalArgumentException("Nom est obligatoires.");
        }
        professeurDAO.ajouterProfesseur(new Prof(0, nom, prenom));
    }

    public List<Prof> getProfesseurs() {
        return professeurDAO.getProfesseurs();
    }

    public void supprimerProfesseur(int id) {
        professeurDAO.supprimerProfesseur(id);
    }
}
