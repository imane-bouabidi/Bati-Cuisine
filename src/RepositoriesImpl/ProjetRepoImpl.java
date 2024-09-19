package RepositoriesImpl;

import Database.DbConnection;
import Models.Client;
import Models.Projet;
import Repositories.ProjetRepository;
import enums.EtatProjet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProjetRepoImpl implements ProjetRepository {

    private Connection conn;

    public ProjetRepoImpl() {
        this.conn = DbConnection.getInstance().getConnection();
    }

    ClientRepoImpl clientImpl = new ClientRepoImpl();
    DevisRepoImpl devisImpl = new DevisRepoImpl();


    public void addProjet(Projet projet) {
        String query = "insert into projet values (?,?,?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, projet.getNomProjet());
            stmt.setDouble(2, projet.getMargeBeneficiaire());
            stmt.setDouble(3, projet.getCoutTotal());
            stmt.setObject(4, projet.getEtatProjet());
            stmt.setObject(5, projet.getClient().getId());
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updateProjet(Projet projet) {

    }
    public void deleteProjet(UUID id) {

    }

    public Projet getProjetById(UUID id){
        String query = "select * from projet where id = ?";
        Projet projet = null;
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                projet.setId(UUID.fromString(rs.getString("id")));
                projet.setNomProjet(rs.getString("nom_projet"));
                projet.setMargeBeneficiaire(rs.getDouble("marge_beneficiaire"));
                projet.setCoutTotal(rs.getDouble("cout_total"));
                projet.setEtatProjet(EtatProjet.valueOf(rs.getString("etat_projet")));
                projet.setClient(clientImpl.findClientById(UUID.fromString(rs.getString("client_id"))));
                projet.setDevis(devisImpl.getDevisByProjetId(UUID.fromString(rs.getString("id"))));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return projet;
    }
}
