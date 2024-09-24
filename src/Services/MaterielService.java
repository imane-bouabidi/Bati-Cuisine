package Services;

import Models.Materiel;
import Repositories.MaterielRepository;
import RepositoriesImpl.MaterielRepoImpl;

import java.util.List;
import java.util.UUID;

public class MaterielService {

    public static MaterielRepoImpl materielImpl = new MaterielRepoImpl();
    public void addMateriel(Materiel materiel){
         materielImpl.addMateriel(materiel);
    }
    public List<Materiel> returnMaterielsByProject(UUID pId){
         return materielImpl.returnMaterielsByProject(pId);
    }
}
