package com.groupeisi.monetablissement.Services;

import com.groupeisi.monetablissement.Dao.CoursDAO;
import com.groupeisi.monetablissement.models.Cours;

import java.util.List;

public class CoursService {
    private final CoursDAO coursDAO = new CoursDAO();

    public void ajouterCours(String nom, int prof_id) {
        coursDAO.ajouterCours(nom, prof_id);
    }

    public List<Cours> getCours() {
        return coursDAO.getCours();
    }

    public void supprimerCours(int id) {
        coursDAO.supprimerCours(id);
    }
}
