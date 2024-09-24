package RepositoriesImpl;

import Database.DbConnection;
import Models.Materiel;
import Models.Projet;
import Repositories.MaterielRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MaterielRepoImpl implements MaterielRepository {
    private Connection conn;

    public MaterielRepoImpl() {
        this.conn = DbConnection.getInstance().getConnection();
    }

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
}
