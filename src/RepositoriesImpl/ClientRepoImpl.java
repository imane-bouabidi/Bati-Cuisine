package RepositoriesImpl;

import Database.DbConnection;
import Models.Client;
import Models.Projet;
import Repositories.ClientRepository;
import enums.EtatProjet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientRepoImpl implements ClientRepository {
    private Connection conn;
    public ClientRepoImpl() {
        this.conn = DbConnection.getInstance().getConnection();
    }

    public List<Client> findAllClients(){
        String query = "select * from client";
        List<Client> clients = new ArrayList<>();
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Client client = new Client(
                     UUID.fromString(rs.getString("id")),
                     rs.getString("nom"),
                     rs.getString("adresse"),
                     rs.getString("telephone"),
                     rs.getString("estProfessionnel")
                );
                clients.add(client);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return clients;
    }

    public Client findClientById(UUID id){
        String query = "select * from client where id = ?";
        Client client = null;
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                client.setId(UUID.fromString(rs.getString("id")));
                client.setNom(rs.getString("nom"));
                client.setAdresse(rs.getString("adresse"));
                client.setTelephone(rs.getString("telephone"));
                client.setEstProfessionnel(rs.getString("estProfessionnel"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return client;
    }

    public Client findClientByName(String name){
        String query = "select * from client where name = ?";
        Client client = null;
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                client.setId(UUID.fromString(rs.getString("id")));
                client.setNom(rs.getString("nom"));
                client.setAdresse(rs.getString("adresse"));
                client.setTelephone(rs.getString("telephone"));
                client.setEstProfessionnel(rs.getString("estProfessionnel"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return client;
    }

    public void addClient(Client client){
        String query = "insert into client values (?,?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setObject(1, client.getId());
            stmt.setObject(2, client.getNom());
            stmt.setObject(3, client.getAdresse());
            stmt.setObject(4, client.getTelephone());
            stmt.setObject(5, client.getEstProfessionnel());
            stmt.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateClient(Client client){
        String query = "update client set nom = ?, adresse = ?, telephone = ?, estProfessionnel = ? where id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setObject(5, client.getId());
            stmt.setObject(1, client.getNom());
            stmt.setObject(2, client.getAdresse());
            stmt.setObject(3, client.getTelephone());
            stmt.setObject(4, client.getEstProfessionnel());
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteClient(UUID clientId){
        String query = "delete from client where id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setObject(1,clientId);
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Projet> getClientProjects(UUID clientId){
        String query = "select p.id as projet_id, p.nom_projet, p.marge_beneficiaire, p.cout_total,p.etat_projet from client as c join projet as p on c.id = p.client_id where client_id = ?";
        List<Projet> projets = new ArrayList<>();

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setObject(1,clientId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Projet projet = new Projet();
                projet.setId(UUID.fromString(rs.getString("projet_id")));
                projet.setNomProjet(rs.getString("nom_projet"));
                projet.setMargeBeneficiaire(rs.getDouble("marge_beneficiaire"));
                projet.setCoutTotal(rs.getDouble("cout_total"));
                projet.setEtatProjet(EtatProjet.valueOf(rs.getString("etat_projet")));
                projets.add(projet);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
            return projets;
    }

}
