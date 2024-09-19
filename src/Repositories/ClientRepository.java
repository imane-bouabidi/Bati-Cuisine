package Repositories;

import Models.Client;

import java.util.List;
import java.util.UUID;

public interface ClientRepository {
    List<Client> findAllClients();
    Client findClientById(UUID id);
    void addClient(Client client);
    void updateClient(Client client);
    void deleteClient(UUID id);
}
