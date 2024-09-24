package View;
import Models.Devis;
import Models.Projet;
import Services.DevisService;

import java.time.LocalDate;
import java.util.Scanner;

public class DevisMenu {

    private static DevisService devisS = new DevisService();
    public static void calculateProjectCost(double coutMateriel, double coutMain, Projet p) {
        Scanner scanner = new Scanner(System.in);
        double coutTotatl = 0;
        System.out.print("Entrez la date de validité du devis (AAAA-MM-JJ) : ");
        String dateValiditeStr = scanner.next();
        LocalDate dateValidite = LocalDate.parse(dateValiditeStr);
        coutTotatl = coutMateriel + coutMain + (coutMateriel + coutMain)*p.getMargeBeneficiaire();
        devisS.addDevis(new Devis(coutTotatl,LocalDate.now(),dateValidite,false,p));
    }

}
