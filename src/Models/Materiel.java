package Models;

import enums.TypeComposant;

import java.util.UUID;

public class Materiel extends Composant{
    private UUID id;
    private double coutTransport;
    private double coefficientQuantite;
    private double coutUnitaire;
    private double quantite;

    public Materiel(String nom, TypeComposant typeComposant, double tauxTVA, Projet projet,double coutTransport, double coefficientQuantite, double coutUnitaire, double quantite) {
        super(nom, typeComposant, tauxTVA, projet);
        this.coutTransport = coutTransport;
        this.coefficientQuantite = coefficientQuantite;
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(double coutTransport) {
        this.coutTransport = coutTransport;
    }

    public double getCoefficientQuantite() {
        return coefficientQuantite;
    }

    public void setCoefficientQuantite(double coefficientQuantite) {
        this.coefficientQuantite = coefficientQuantite;
    }

    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }


}
