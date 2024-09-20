package View;

import java.util.Scanner;

public class ProjetMenu {

    public static void createProject() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Création d'un Nouveau Projet ---");
        System.out.print("Entrez le nom du projet : ");
        String projectName = scanner.nextLine();
        System.out.print("Entrez la surface de la cuisine (en m²) : ");
        double surface = scanner.nextDouble();
        System.out.println("Projet créé avec succès : " + projectName + " - Surface : " + surface + " m²");
    }
}
