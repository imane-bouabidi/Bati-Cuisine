package RepositoriesImpl;

import Database.DbConnection;
import Models.Devis;
import Repositories.DevisRepository;
import Services.ProjetService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DevisRepoImpl implements DevisRepository {
    private Connection conn;
    public DevisRepoImpl() {
        this.conn = DbConnection.getInstance().getConnection();
    }

    public static ProjetService projetS = new ProjetService();
//here
    public void addDevis(Devis devis){
        String query = "insert into devis(montant_estime,date_emission,date_validite,accepte,projet_id) values (?,?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setDouble(1, devis.getMontantEstime());
            stmt.setDate(2, java.sql.Date.valueOf(devis.getDateEmission()));
            stmt.setDate(3, java.sql.Date.valueOf(devis.getDateValidite()));
            stmt.setBoolean(4, devis.getAccepte());
            stmt.setObject(5, devis.getProjet().getId());
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateDevis(Devis devis){
        String query = "update devis set montant_estime = ?,date_emission = ?,date_validite = ?,accepte = ? where id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setObject(5, devis.getId());
            stmt.setDouble(1, devis.getMontantEstime());
            stmt.setDate(2, java.sql.Date.valueOf(devis.getDateEmission()));
            stmt.setDate(3, java.sql.Date.valueOf(devis.getDateValidite()));
            stmt.setBoolean(4, devis.getAccepte());
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteDevis(UUID devisId){
        String query = "delete from devis where id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setObject(1, devisId);
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public Devis afficherDevisByProjectId(UUID id){
        String query = "select d.id as devis_id,montant_estime, date_emission, date_validite, accepte, projet_id from devis as d join projet as p on d.projet_id = p.id where p.id = ?";
        Devis devis = new Devis();
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                devis.setId(UUID.fromString(rs.getString("devis_id")));
                devis.setMontantEstime(rs.getDouble("montant_estime"));
                devis.setDateEmission(rs.getDate("date_emission").toLocalDate());
                devis.setDateValidite(rs.getDate("date_validite").toLocalDate());
                devis.setAccepte(rs.getBoolean("accepte"));
                devis.setProjet(projetS.getProjetById(UUID.fromString(rs.getString("projet_id"))));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return devis;
    }

    public List<Devis> getDevisByProjetId(UUID projetId) {
        List<Devis> devisList = new ArrayList<>();

        String sql = "SELECT * FROM devis WHERE projet_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, projetId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Devis devis = new Devis();
                    devis.setId(UUID.fromString(rs.getString("id")));
                    devis.setMontantEstime(rs.getDouble("montant_estime"));
                    devis.setDateEmission(rs.getDate("date_emission").toLocalDate());
                    devis.setDateValidite(rs.getDate("date_validite").toLocalDate());
                    devis.setAccepte(rs.getBoolean("accepte"));

                    devisList.add(devis);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return devisList;
    }



}
