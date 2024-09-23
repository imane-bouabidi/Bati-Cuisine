package Services;

import Models.Devis;
import RepositoriesImpl.DevisRepoImpl;

import java.util.List;
import java.util.UUID;

public class DevisService {

    public static DevisRepoImpl devisImpl = new DevisRepoImpl();
    public List<Devis> getDevisByProjetId(UUID ProjetId){
        return devisImpl.getDevisByProjetId(ProjetId);
    }
}
