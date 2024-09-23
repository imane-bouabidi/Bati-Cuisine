package Services;

import Models.Client;
import RepositoriesImpl.ClientRepoImpl;

import java.util.UUID;

public class ClientService {
    private ClientRepoImpl clientimpl = new ClientRepoImpl();


    public Client findClientByName(String name){

        return clientimpl.findClientByName(name);
    }

    public UUID addClient(Client client){
        return clientimpl.addClient(client);
    }
}
