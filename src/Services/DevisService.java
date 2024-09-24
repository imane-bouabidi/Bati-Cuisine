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

    public void addDevis(Devis devis){
        devisImpl.addDevis(devis);
    }
    public void afficherDevis(UUID id){
        devisImpl.afficherDevisByProjectId(id);
    }

}
