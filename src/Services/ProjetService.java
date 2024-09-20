package Services;

import Models.Client;
import Models.Projet;
import RepositoriesImpl.ClientRepoImpl;
import RepositoriesImpl.ProjetRepoImpl;

public class ProjetService {

    // Ajout des repositories pour la gestion des données
    private ClientRepoImpl clientRepo = new ClientRepoImpl();
    private ProjetRepoImpl projetRepo = new ProjetRepoImpl();

    public Client rechercherClient(String nomClient) {
        return clientRepo.findClientByName(nomClient);
    }

    public void ajouterClient(Client client) {
        clientRepo.addClient(client);
    }



}
