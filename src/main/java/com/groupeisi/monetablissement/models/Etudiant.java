package com.groupeisi.monetablissement.models;

public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private int classe_id;
    private String classeNom; // Nom de la classe (ajouté)

   public Etudiant() {}

    public Etudiant(int id, String nom, String prenom, int classe_id, String classeNom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.classe_id = classe_id;
        this.classeNom = classeNom;
    }

    public String getClasseNom() {
        return classeNom;
    }

    public void setClasseNom(String classeNom) {
        this.classeNom = classeNom;
    }


// Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getClasse_id() {
        return classe_id;
    }

    public void setClasse_id(int classe_id) {
        this.classe_id = classe_id;
    }
}
