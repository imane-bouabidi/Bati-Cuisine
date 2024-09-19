package RepositoriesImpl;

import Models.Client;
import Repositories.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientRepoImpl implements ClientRepository {
    private Connection conn;
    public ClientRepoImpl(Connection conn) {
        this.conn = conn;
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

}
