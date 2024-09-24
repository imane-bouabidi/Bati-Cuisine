package View;

import Models.MainOeuvre;
import Models.Projet;
import Services.MainOeuvreService;
import enums.TypeComposant;

import java.util.Scanner;

public class MainOeuvreMenu {
    public static double addMainOeuvre(Projet p) {
        Scanner scanner = new Scanner(System.in);
        boolean addingMainOeuvre = true;
        double coutMainOeuvre = 0;

        while (addingMainOeuvre) {
            System.out.print("Entrez le nom : ");
            String nom = scanner.nextLine();
            System.out.print("Entrez le taux TVA : ");
            double tauxTVA = scanner.nextDouble();
            System.out.print("Entrez le taux horaire (dh/h) : ");
            double tauxHoraire = scanner.nextDouble();
            System.out.print("Entrez le nombre d'heures travaillées : ");
            double heuresTravail = scanner.nextDouble();
            System.out.print("Entrez le facteur de productivité (1.0 = standard, >1.0 = haute productivité) : ");
            double productivite = scanner.nextDouble();

            coutMainOeuvre = tauxHoraire * heuresTravail * productivite;
            System.out.println("Main-d'œuvre ajoutée : " + nom + " - Coût total : " + coutMainOeuvre);

            MainOeuvreService.addMainOeuvre(new MainOeuvre(nom, TypeComposant.WORKER, tauxTVA, p, productivite, heuresTravail, tauxHoraire));

            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            scanner.nextLine();
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")) {
                addingMainOeuvre = false;
            }
        }
        return coutMainOeuvre;
    }

}

