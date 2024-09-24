package Models;

import enums.TypeComposant;

import java.util.UUID;

public abstract class Composant {
    private String nom;
    private TypeComposant typeComposant;
    private double tauxTVA;
    private Projet projet;

    public Composant(String nom, TypeComposant typeComposant, double tauxTVA,Projet projet) {
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.tauxTVA = tauxTVA;
        this.projet = projet;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeComposant getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }

    public double getTauxTVA() {
        return tauxTVA;
    }

    public void setTauxTVA(double tauxTVA) {
        this.tauxTVA = tauxTVA;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
