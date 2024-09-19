package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client {
    private UUID id;
    private String nom ;
    private String adresse;
    private String telephone;
    private String estProfessionnel;
    private List<Projet> projets;

    public Client(UUID id, String nom, String adresse, String telephone, String estProfessionnel){
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.estProfessionnel = estProfessionnel;
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

    public List<Projet> getProjet() {
        return projets;
    }

    public void setProjet(List<Projet> projets) {
        this.projets = projets;
    }
}
