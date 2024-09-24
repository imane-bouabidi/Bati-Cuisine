package Repositories;

import Models.Materiel;

import java.util.List;
import java.util.UUID;

public interface MaterielRepository {

    void addMateriel(Materiel materiel);
    List<Materiel> returnMaterielsByProject(UUID id);


}
