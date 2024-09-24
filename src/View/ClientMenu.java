package View;

import Models.Client;
import RepositoriesImpl.ClientRepoImpl;
import Services.ClientService;

import java.util.Scanner;
import java.util.UUID;

public class ClientMenu {

    private static ClientService clienSer = new ClientService();
    public static UUID  handleClientSelection() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.print("Choisissez une option : ");
        UUID clientId = null;
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                clientId = findExistingClient();
                break;
            case 2:
                clientId = addNewClient();
                break;
            default:
                System.out.println("Option invalide.");
        }
        return clientId;
    }

    public static UUID findExistingClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom du client : ");
        String clientName = scanner.nextLine();
        Client client = clienSer.findClientByName(clientName);
        UUID clientId = null;
        if(client!=null){

            System.out.println("Client trouvé :");
            System.out.println("Nom: " + clientName);
            System.out.println("Adresse : 12 Rue des Fleurs, Paris");
            System.out.println("Numéro de téléphone : 06 12345678");
            System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("y")) {
                clientId = client.getId();
            } else {
                handleClientSelection();
            }
        }else{
            System.out.println("Client n'existe pas !");
        }
        return clientId;
    }

    public static UUID addNewClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom du nouveau client : ");
        String clientName = scanner.nextLine();
        System.out.print("Entrez l'adresse du client : ");
        String address = scanner.nextLine();
        System.out.print("Entrez le numéro de téléphone du client : ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Le client est il professionnel? (y/n) : ");
        String inp = scanner.nextLine();
        boolean professionnel;
        if (inp.equalsIgnoreCase("y")) {
             professionnel = true;
        }else {
             professionnel = false;

        }
        Client c = new Client(clientName,address,phoneNumber,professionnel);
         UUID clientId = clienSer.addClient(c);
        return clientId;
    }

    public static void createNewProjectForClient(String clientName) {
        System.out.println("Création d'un projet pour " + clientName);
    }
}
