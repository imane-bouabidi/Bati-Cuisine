package Models;

import java.util.UUID;

public class Client {
    private UUID id;
    private String nom ;
    private String adresse;
    private String telephone;
    private String estProfessionnel;
    private Projet projet;

    public Client(UUID id, String nom, String adresse, String telephone, String estProfessionnel, Projet projet) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.estProfessionnel = estProfessionnel;
        this.projet = projet;
    }

    public Client() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEstProfessionnel() {
        return estProfessionnel;
    }

    public void setEstProfessionnel(String estProfessionnel) {
        this.estProfessionnel = estProfessionnel;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
