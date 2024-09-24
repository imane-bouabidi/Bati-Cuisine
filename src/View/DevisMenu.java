package View;
import Models.Devis;
import Models.MainOeuvre;
import Models.Materiel;
import Models.Projet;
import Services.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DevisMenu {

    private static DevisService devisS = new DevisService();
    private static ClientService clientS = new ClientService();
    private static MainOeuvreService workerS = new MainOeuvreService();
    private static MaterielService materielS = new MaterielService();

    public static void calculateProjectCost(double coutMateriel, double coutMain, Projet p) {
        double coutTotatl = 0;
        LocalDate dateValidite = getValidDate("Entrez la date de validité du devis (dd/MM/yyyy) : ");
        coutTotatl = coutMateriel + coutMain + (coutMateriel + coutMain)*p.getMargeBeneficiaire();
        devisS.addDevis(new Devis(coutTotatl,LocalDate.now(),dateValidite,false,p));
    }


    public static void getDevisById(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'id du projet : ");
        String pId = scanner.next();
        UUID id = UUID.fromString(pId);

        Devis d = devisS.afficherDevis(id);
        List<MainOeuvre> wokreks = workerS.returnWorkersByProject(id);
        List<Materiel> materiels = materielS.returnMaterielsByProject(id);
        AfficherDevis(d,wokreks,materiels);
    }

    public static void AfficherDevis(Devis d, List<MainOeuvre> wokreks, List<Materiel> materiels) {
        System.out.println("-------------------Le devis du projet--------------------");

        System.out.println("Client : " + clientS.findClientById(d.getProjet().getId()).getNom());

        System.out.println("------Les composants du projet------");

        System.out.println("----Les matériaux----");
        double totalMateriels = MaterielMenu.afficherMateriels(materiels);
        double totalMainOeuvre = MainOeuvreMenu.afficherWorkers(wokreks);

        double coutTotal = totalMateriels + totalMainOeuvre;

        System.out.println("Coût total estimé avant marge : " + coutTotal + " DH");
//        System.out.println("Coût total estimé avec marge : " + coutTotal*d.getProjet().getMargeBeneficiaire() + (coutTotal*d.getProjet().getMargeBeneficiaire())/100 + " DH");
        System.out.println("Date d'émission : " + d.getDateEmission());
        System.out.println("Date de validité : " + d.getDateValidite());
    }


    public static LocalDate getValidDate(String prompt) {
        Scanner scanner = new Scanner(System.in);
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (date == null) {
            System.out.print(prompt);
            String dateString = scanner.nextLine();

            try {
                date = LocalDate.parse(dateString, formatter);
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("La date doit être supérieure à aujourd'hui. Veuillez réessayer.");
                    date = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Format de date invalide. Veuillez entrer une date au format jj/mm/aaaa.");
            }
        }

        return date;
    }

}
