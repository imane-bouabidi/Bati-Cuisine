package View;
import Models.Devis;
import Models.MainOeuvre;
import Models.Materiel;
import Models.Projet;
import Services.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DevisMenu {

    private static DevisService devisS = new DevisService();
    private static ClientService clientS = new ClientService();
    private static MainOeuvreService workerS = new MainOeuvreService();
    private static MaterielService materielS = new MaterielService();
    public static void calculateProjectCost(double coutMateriel, double coutMain, Projet p) {
        Scanner scanner = new Scanner(System.in);
        double coutTotatl = 0;
        System.out.print("Entrez la date de validité du devis (AAAA-MM-JJ) : ");
        String dateValiditeStr = scanner.next();
        LocalDate dateValidite = LocalDate.parse(dateValiditeStr);
        coutTotatl = coutMateriel + coutMain + (coutMateriel + coutMain)*p.getMargeBeneficiaire();
        devisS.addDevis(new Devis(coutTotatl,LocalDate.now(),dateValidite,false,p));
    }


    public static void getDevisById(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'id du projet : ");
        String pId = scanner.next();
        UUID id = UUID.fromString(pId);

        Devis d = devisS.afficherDevis(id);
        List<MainOeuvre> wokreks = workerS.returnWorkersByProject(id);
        List<Materiel> materiels = materielS.returnMaterielsByProject(id);
        AfficherDevis(d,wokreks,materiels);
    }

    public static void AfficherDevis(Devis d, List<MainOeuvre> wokreks, List<Materiel> materiels) {
        System.out.println("-------------------Le devis du projet--------------------");

        System.out.println("Client : " + clientS.findClientById(d.getProjet().getId()).getNom());

        System.out.println("------Les composants du projet------");

        System.out.println("----Les matériaux----");
        double totalMateriels = MaterielMenu.afficherMateriels(materiels);
        double totalMainOeuvre = MainOeuvreMenu.afficherWorkers(wokreks);

        double coutTotal = totalMateriels + totalMainOeuvre;

        System.out.println("Coût total estimé avant marge et TVA : " + coutTotal + " DH");
        System.out.println("Date d'émission : " + d.getDateEmission());
        System.out.println("Date de validité : " + d.getDateValidite());
    }


}
