package Models;

import enums.TypeComposant;

import java.util.UUID;

public class MainOeuvre extends Composant{
    private UUID id;
    private double productivite;
    private double tauxHoraire;
    private double heurTravail;

    public MainOeuvre(String nom, TypeComposant typeComposant, double tauxTVA, Projet projet, double productivite, double heurTravail,double tauxHoraire) {
        super(nom, typeComposant, tauxTVA, projet);
        this.productivite = productivite;
        this.tauxHoraire = tauxHoraire;
        this.heurTravail = heurTravail;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getProductivite() {
        return productivite;
    }

    public void setProductivite(double productivite) {
        this.productivite = productivite;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public double getHeurTravail() {
        return heurTravail;
    }

    public void setHeurTravail(double heurTravail) {
        this.heurTravail = heurTravail;
    }
}
