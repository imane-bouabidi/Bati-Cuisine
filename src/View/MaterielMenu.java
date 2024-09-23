package View;

import Models.Materiel;
import Models.Projet;
import Services.MaterielService;
import enums.TypeComposant;

import java.util.Scanner;

public class MaterielMenu {

    public static MaterielService materielService = new MaterielService();
    public static void addMaterials(Projet p) {
        Scanner scanner = new Scanner(System.in);
        boolean addingMaterials = true;

        while (addingMaterials) {
            System.out.print("Entrez le nom du matériau : ");
            String materialName = scanner.nextLine();
            System.out.print("Entrez le taux TVA : ");
            double tauxTVA = scanner.nextDouble();
            System.out.print("Entrez la quantité de ce matériau : ");
            double quantity = scanner.nextDouble();
            System.out.print("Entrez le coût unitaire de ce matériau (€/unité) : ");
            double unitCost = scanner.nextDouble();
            System.out.print("Entrez le coût de transport de ce matériau (€) : ");
            double transportCost = scanner.nextDouble();
            System.out.print("Entrez le coefficient de qualité (1.0 = standard, >1.0 = haute qualité) : ");
            double qualityCoefficient = scanner.nextDouble();

            double materialCost = (quantity * unitCost * qualityCoefficient) + transportCost;
            System.out.println("Matériau ajouté : " + materialName + " - Coût total : " + materialCost);
            materielService.addMateriel(new Materiel(materialName, TypeComposant.MATERIAU,tauxTVA,p,transportCost,qualityCoefficient,unitCost,quantity));
            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            scanner.nextLine();
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")) {
                addingMaterials = false;
            }
        }
    }
}
