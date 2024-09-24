package View;

import Models.Projet;
import Services.ProjetService;

import java.util.Scanner;
import java.util.UUID;

public class ProjetMenu {

    private static ProjetService projetService = new ProjetService();
    public static Projet createProject(UUID id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Création d'un Nouveau Projet ---");
        System.out.print("Entrez le nom du projet : ");
        String projectName = scanner.nextLine();
        System.out.print("Entrez le pourcentage de la marge bénéficière : ");
        double marge = scanner.nextDouble();

        return projetService.creerProjet(projectName,marge,id);
    }
}
