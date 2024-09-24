package View;

import Models.MainOeuvre;
import Models.Materiel;
import Models.Projet;
import Services.MainOeuvreService;
import enums.TypeComposant;

import java.util.List;
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

    public static double afficherWorkers(List<MainOeuvre> workers){

        System.out.println("----La main-d'oeuvre----");
        double totalMainOeuvre = workers.stream()
                .mapToDouble(worker -> {
                    double coutMainOeuvre = (worker.getTauxHoraire() * worker.getHeurTravail()) +
                            (worker.getTauxHoraire() * worker.getHeurTravail() * worker.getProductivite());
                    System.out.println("Taux horaire : " + worker.getTauxHoraire());
                    System.out.println("Nombre d'heures travaillées : " + worker.getHeurTravail());
                    System.out.println("Facteur de productivité : " + worker.getProductivite());
                    System.out.println("Coût total de cette main-d'oeuvre : " + coutMainOeuvre);
                    System.out.println("-------------------");
                    return coutMainOeuvre;
                }).sum();

        System.out.println("Coût total de la main-d'oeuvre : " + totalMainOeuvre + " DH");
        return totalMainOeuvre;
    }

}

