package Services;

import Models.Client;
import Models.MainOeuvre;
import Models.Materiel;
import Models.Projet;
import RepositoriesImpl.ClientRepoImpl;
import RepositoriesImpl.ProjetRepoImpl;

import java.util.UUID;

public class ProjetService {

    // Ajout des repositories pour la gestion des données
    private ClientRepoImpl clientRepo = new ClientRepoImpl();
    private ProjetRepoImpl projetRepo = new ProjetRepoImpl();

    public Client rechercherClient(UUID id) {
        return clientRepo.findClientById(id);
    }

    //public void ajouterClient(Client client) {
        //clientRepo.addClient(client);
    //}

    public Projet creerProjet(String nomProjet, double marge, UUID id) {
        Projet projet = new Projet();
        projet.setNomProjet(nomProjet);
        projet.setMargeBeneficiaire(marge);
        projet.setClient(rechercherClient(id));
        UUID pId = projetRepo.addProjet(projet);
        projet.setId(pId);
        return projet;
    }

    public void ajouterMateriau(Projet projet, Materiel materiau) {
        //projet.ajouterMateriau(materiau);
    }

    public void ajouterMainOeuvre(Projet projet, MainOeuvre mainOeuvre) {

        //projet.ajouterMainOeuvre(mainOeuvre);
    }

    public void calculerCoutTotal(Projet projet, double tva, double marge) {
        //double coutTotal = projet.calculerCoutTotal(tva, marge);
        //projet.setCoutTotal(coutTotal);
        //projetRepo.updateProjet(projet);
        //System.out.println(projet);
    }

    public void afficherProjets() {
    }
}
