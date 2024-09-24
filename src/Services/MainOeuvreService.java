package Services;

import Models.MainOeuvre;
import Repositories.MainOeuvreRepository;
import RepositoriesImpl.MainOeuvreRepoImpl;

public class MainOeuvreService {

    public static MainOeuvreRepoImpl workerImpl = new MainOeuvreRepoImpl();
    public static void addMainOeuvre(MainOeuvre mainOeuvre){
        workerImpl.addMainOeuvre(mainOeuvre);
    }

}
