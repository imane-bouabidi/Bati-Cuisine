package View;

import Models.Materiel;
import Models.Projet;
import Services.MaterielService;
import enums.TypeComposant;

import java.util.List;
import java.util.Scanner;

public class MaterielMenu {

    public static MaterielService materielService = new MaterielService();
    public static double addMaterials(Projet p) {
        Scanner scanner = new Scanner(System.in);
        boolean addingMaterials = true;
        double materialCost =0;

        while (addingMaterials) {
            System.out.print("Entrez le nom du matériau : ");
            String materialName = scanner.nextLine();
            System.out.print("Entrez le taux TVA : ");
            double tauxTVA = scanner.nextDouble();
            System.out.print("Entrez la quantité de ce matériau : ");
            double quantity = scanner.nextDouble();
            System.out.print("Entrez le coût unitaire de ce matériau (dh/unité) : ");
            double unitCost = scanner.nextDouble();
            System.out.print("Entrez le coût de transport de ce matériau (dh) : ");
            double transportCost = scanner.nextDouble();
            System.out.print("Entrez le coefficient de qualité (1.0 = standard, >1.0 = haute qualité) : ");
            double qualityCoefficient = scanner.nextDouble();

             materialCost = (quantity * unitCost * qualityCoefficient) + transportCost;
            System.out.println("Matériau ajouté : " + materialName + " - Coût total : " + materialCost);
            materielService.addMateriel(new Materiel(materialName, TypeComposant.MATERIAU,tauxTVA,p,transportCost,qualityCoefficient,unitCost,quantity));
            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            scanner.nextLine();
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")) {
                addingMaterials = false;
            }
        }
    return materialCost;
    }

    public static double afficherMateriels(List<Materiel> materiels){
        double totalMateriels = materiels.stream()
                .mapToDouble(materiel -> {
                    double coutMateriel = (materiel.getCoutUnitaire() * materiel.getQuantite()) + materiel.getCoutTransport() +
                            (materiel.getCoutUnitaire() * materiel.getQuantite() * materiel.getCoefficientQuantite());
                    System.out.println("Nom du matériau : " + materiel.getNom());
                    System.out.println("Quantité : " + materiel.getQuantite());
                    System.out.println("Coût unitaire : " + materiel.getCoutUnitaire());
                    System.out.println("Coût de transport : " + materiel.getCoutTransport());
                    System.out.println("Coefficient de qualité : " + materiel.getCoefficientQuantite());
                    System.out.println("Coût total de ce matériau : " + coutMateriel);
                    System.out.println("-------------------");
                    return coutMateriel;
                }).sum(); // Somme des coûts des matériaux

        System.out.println("Coût total des matériaux : " + totalMateriels + " DH");
        return totalMateriels;
    }


}
