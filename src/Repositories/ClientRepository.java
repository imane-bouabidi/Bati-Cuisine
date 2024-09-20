package Repositories;

import Models.Client;
import Models.Projet;

import java.util.List;
import java.util.UUID;

public interface ClientRepository {
    List<Client> findAllClients();
    Client findClientById(UUID id);
    Client findClientByName(String name);
    void addClient(Client client);
    void updateClient(Client client);
    void deleteClient(UUID id);
    List<Projet> getClientProjects(UUID id);
}
