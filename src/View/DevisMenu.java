package View;
import Models.Devis;
import Models.Projet;
import Services.ClientService;
import Services.DevisService;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class DevisMenu {

    private static DevisService devisS = new DevisService();
    private static ClientService clientS = new ClientService();
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

        devisS.afficherDevis(id);
    }

    public void AfficherDevis(Devis d) {
        System.out.println("-------------------Le devis du projet--------------------");
        System.out.println("Client :" + clientS.findClientById(d.getProjet().getId()).getNom());
        System.out.println("------Les composants du projet------");
        System.out.println("----Les materiaux----");
        System.out.println("");
        System.out.println("-------------------Le devis du projet--------------------");
        System.out.println("-------------------Le devis du projet--------------------");
        System.out.println("-------------------Le devis du projet--------------------");
    }

}
