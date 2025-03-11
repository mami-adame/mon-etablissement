package com.groupeisi.monetablissement.models;

public class Prof {
    private int id;
    private String nom;
    private String prenom;

    public Prof() {
    }

    public Prof(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Prof(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    // Getters and Setters

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
}
