package com.groupeisi.monetablissement.Services;

import java.util.List;

//public class ClasseService {
//}

import com.groupeisi.monetablissement.Dao.ClasseDAO;
import com.groupeisi.monetablissement.models.Classe;

import java.util.List;

public class ClasseService {
    private final ClasseDAO classeDAO = new ClasseDAO();

    public void ajouterClasse(String nom) {
        if (nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom de la classe ne peut pas être vide.");
        }
        classeDAO.ajouterClasse(new Classe(0, nom));
    }

    public List<Classe> getClasses() {
        return classeDAO.getClasses();
    }

    public void supprimerClasse(int id) {
        classeDAO.supprimerClasse(id);
    }
}
