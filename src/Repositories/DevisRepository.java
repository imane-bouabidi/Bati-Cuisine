package Repositories;

import Models.Devis;

import java.util.List;
import java.util.UUID;

public interface DevisRepository {
    Devis getDevisById(UUID id);
    void addDevis(Devis devis);
    void updateDevis(Devis devis);
    void deleteDevis(UUID devisId);
    List<Devis> getDevisByProjetId(UUID projetId);
    //void updateAcceptedDevis(Devis devis);
}
