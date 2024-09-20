package Services;

import Models.Client;
import RepositoriesImpl.ClientRepoImpl;

public class ClientService {
    private ClientRepoImpl clientimpl = new ClientRepoImpl();


    public Client findClientByName(String name){
        return clientimpl.findClientByName(name);
    }
}
