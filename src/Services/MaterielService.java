package Services;

import Models.Materiel;
import Repositories.MaterielRepository;
import RepositoriesImpl.MaterielRepoImpl;

import java.util.UUID;

public class MaterielService {

    public static MaterielRepoImpl materielImpl = new MaterielRepoImpl();
    public void addMateriel(Materiel materiel){
         materielImpl.addMateriel(materiel);
    }
}
