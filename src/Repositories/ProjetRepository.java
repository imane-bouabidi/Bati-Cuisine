package Repositories;

import Models.Projet;

import java.util.UUID;

public interface ProjetRepository {
    UUID addProjet(Projet projet);
    void updateProjet(Projet projet);
    void deleteProjet(UUID id);
    Projet getProjetById(UUID id);
}
