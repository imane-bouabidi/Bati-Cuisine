package View;

import Models.Projet;
import Services.ProjetService;

import java.util.Scanner;
import java.util.UUID;

public class Main{

    public static void main(String[] args) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            displayMainMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    handleNewProject();
                    break;
                case 2:
                    displayExistingProjects();
                    break;
                case 3:
                    afficherDevis();
                    break;
                case 4:
                    System.out.println("Quitter. Merci d'avoir utilisé l'application !");
                    running = false;
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("=== Menu Principal ===");
        System.out.println("1. Créer un nouveau projet");
        System.out.println("2. Afficher les projets existants");
        System.out.println("3. Afficher le devis d'un projet");
        System.out.println("4. Quitter");
        System.out.print("Choisissez une option : ");
    }

    private static void handleNewProject() {
        UUID clientId = ClientMenu.handleClientSelection();

        Projet p = ProjetMenu.createProject(clientId);

        double coutMateriel = MaterielMenu.addMaterials(p);

        double coutMain = MainOeuvreMenu.addMainOeuvre(p);

        DevisMenu.calculateProjectCost(coutMateriel,coutMain,p);
    }

    private static void displayExistingProjects() {
    }

    private static void afficherDevis() {
        DevisMenu.getDevisById();
    }

}

