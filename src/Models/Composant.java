package Models;

import java.util.UUID;

public abstract class Composant {
    private String nom;
    private String typeComposant;
    private double tauxTVA;
    private Projet projet;

    public Composant(String nom, String typeComposant, double tauxTVA,Projet projet) {
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.tauxTVA = tauxTVA;
        this.projet = projet;
    }
}
