package View;

import java.util.Scanner;

public class MainOeuvreMenu {
    public static void addMainOeuvre() {
        Scanner scanner = new Scanner(System.in);
        boolean addingMainOeuvre = true;

        while (addingMainOeuvre) {
            System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
            String laborType = scanner.nextLine();
            System.out.print("Entrez le taux horaire (€/h) : ");
            double hourlyRate = scanner.nextDouble();
            System.out.print("Entrez le nombre d'heures travaillées : ");
            double hoursWorked = scanner.nextDouble();
            System.out.print("Entrez le facteur de productivité (1.0 = standard, >1.0 = haute productivité) : ");
            double productivityFactor = scanner.nextDouble();

            double laborCost = hourlyRate * hoursWorked * productivityFactor;
            System.out.println("Main-d'œuvre ajoutée : " + laborType + " - Coût total : " + laborCost);

            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            scanner.nextLine();
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")) {
                addingMainOeuvre = false;
            }
        }
    }
}

