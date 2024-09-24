package Services;

import Models.MainOeuvre;
import Repositories.MainOeuvreRepository;
import RepositoriesImpl.MainOeuvreRepoImpl;

import java.util.List;
import java.util.UUID;

public class MainOeuvreService {

    public static MainOeuvreRepoImpl workerImpl = new MainOeuvreRepoImpl();
    public static void addMainOeuvre(MainOeuvre mainOeuvre){
        workerImpl.addMainOeuvre(mainOeuvre);
    }
    public List<MainOeuvre> returnWorkersByProject(UUID pId){
     return workerImpl.returnWorkersByProject(pId);
    }
}
