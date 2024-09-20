package View;

import Models.Client;
import RepositoriesImpl.ClientRepoImpl;
import Services.ClientService;

import java.util.Scanner;

public class ClientMenu {

    private static ClientService clienSer = new ClientService();
    public static void handleClientSelection() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.print("Choisissez une option : ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                findExistingClient();
                break;
            case 2:
                addNewClient();
                break;
            default:
                System.out.println("Option invalide.");
        }
    }

    public static void findExistingClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom du client : ");
        String clientName = scanner.nextLine();
        Client client = clienSer.findClientByName(clientName);
        if(client!=null){

            System.out.println("Client trouvé :");
            System.out.println("Nom: " + clientName);
            System.out.println("Adresse : 12 Rue des Fleurs, Paris");
            System.out.println("Numéro de téléphone : 06 12345678");
            System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("y")) {
                createNewProjectForClient(clientName);
            } else {
                handleClientSelection();
            }
        }else{
            System.out.println("Client n'existe pas !");
        }
    }

    public static void addNewClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom du nouveau client : ");
        String clientName = scanner.nextLine();
        System.out.print("Entrez l'adresse du client : ");
        String address = scanner.nextLine();
        System.out.print("Entrez le numéro de téléphone du client : ");
        String phoneNumber = scanner.nextLine();

        System.out.println("Client ajouté avec succès !");
        createNewProjectForClient(clientName);
    }

    public static void createNewProjectForClient(String clientName) {
        System.out.println("Création d'un projet pour " + clientName);
    }
}
