package com.groupeisi.monetablissement.models;

public class Cours {
    private int id;
    private String nom;
    private int prof_id;

    public Cours() {
    }

    public Cours(int id, String nom, int prof_id) {
        this.id = id;
        this.nom = nom;
        this.prof_id = prof_id;
    }

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

    public int getProf_id() {
        return prof_id;
    }

    public void setProf_id(int prof_id) {
        this.prof_id = prof_id;
    }
}
