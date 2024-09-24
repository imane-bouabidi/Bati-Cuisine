package RepositoriesImpl;

import Database.DbConnection;
import Models.MainOeuvre;
import Models.Materiel;
import Models.Projet;
import Repositories.MaterielRepository;
import Services.ProjetService;
import enums.TypeComposant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MaterielRepoImpl implements MaterielRepository {
    private Connection conn;

    public MaterielRepoImpl() {
        this.conn = DbConnection.getInstance().getConnection();
    }
    ProjetService projetS = new ProjetService();

    public void addMateriel(Materiel materiel) {
        String query = "insert into materiaux(nom,type_composant,taux_tva,projet_id,cout_transport,coefficient_quantite,cout_unitaire,quantite) values (?,?,?,?,?,?,?,?)";
        UUID materielId = null;
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, materiel.getNom());
            stmt.setObject(2, materiel.getTypeComposant(),java.sql.Types.OTHER);
            stmt.setDouble(3, materiel.getTauxTVA());
            stmt.setObject(4, materiel.getProjet().getId());
            stmt.setDouble(5, materiel.getCoutTransport());
            stmt.setDouble(6, materiel.getCoefficientQuantite());
            stmt.setDouble(7, materiel.getCoutUnitaire());
            stmt.setDouble(8, materiel.getQuantite());
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Materiel> returnMaterielsByProject(UUID id){
        List<Materiel> materiels = new ArrayList<>();
        String query = "select * from materiaux where projet_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Materiel materiel = new Materiel(
                        rs.getString("nom"),
                        TypeComposant.valueOf(rs.getString("type_composant")),
                        rs.getDouble("taux_tva"),
                        projetS.getProjetById(UUID.fromString(rs.getString("projet_id"))),
                        rs.getDouble("cout_transport"),
                        rs.getDouble("coefficient_quantite"),
                        rs.getDouble("cout_unitaire"),
                        rs.getDouble("quantite")
                );
                materiels.add(materiel);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return materiels;
    }
}
