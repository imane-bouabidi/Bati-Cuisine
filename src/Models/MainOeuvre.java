package Models;

import enums.TypeComposant;

import java.util.UUID;

public class MainOeuvre extends Composant{
    private UUID id;
    private double productivite;

    public MainOeuvre(String nom, TypeComposant typeComposant, double tauxTVA, Projet projet, UUID id, double productivite) {
        super(nom, typeComposant, tauxTVA, projet);
        this.id = id;
        this.productivite = productivite;
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
}
