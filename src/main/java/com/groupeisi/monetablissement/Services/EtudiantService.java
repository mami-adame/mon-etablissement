package com.groupeisi.monetablissement.Services;

import com.groupeisi.monetablissement.Dao.EtudiantDAO;
import com.groupeisi.monetablissement.models.Etudiant;

public class EtudiantService {

        private final EtudiantDAO etudiantDAO = new EtudiantDAO();

        public void ajouterEtudiant(Etudiant etudiant) {
            if (etudiant.getNom().isEmpty() || etudiant.getPrenom().isEmpty()) {
                throw new IllegalArgumentException("Nom et Prenom sont obligatoires.");
            }
            etudiantDAO.ajouterEtudiant(etudiant);
        }

//        public List<Etudiant> getEtudiants() {
//            return etudiantDAO.getEtudiants();
//        }


}
