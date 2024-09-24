package RepositoriesImpl;

import Database.DbConnection;
import Models.Client;
import Models.Projet;
import Repositories.ProjetRepository;
import Services.DevisService;
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

    public static ClientRepoImpl clientImpl = new ClientRepoImpl();
    public static DevisService devisService = new DevisService();

    public UUID addProjet(Projet projet) {
        String query = "insert into projet(nom_projet,marge_beneficiaire,client_id) values (?,?,?) returning id";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, projet.getNomProjet());
            stmt.setDouble(2, projet.getMargeBeneficiaire());
            stmt.setObject(3, projet.getClient().getId(), java.sql.Types.OTHER);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return UUID.fromString(rs.getString("id"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return projet.getId();
    }
    public void updateProjet(Projet projet) {

    }
    public void deleteProjet(UUID id) {

    }

    public Projet getProjetById(UUID id){
        String query = "select * from projet where id = ?";
        Projet projet = new Projet();
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
//                projet.setDevis(devisImpl.getDevisByProjetId(UUID.fromString(rs.getString("id"))));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return projet;
    }
}
