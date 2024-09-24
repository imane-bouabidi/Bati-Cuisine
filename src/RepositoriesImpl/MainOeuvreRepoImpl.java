package RepositoriesImpl;

import Database.DbConnection;
import Models.MainOeuvre;
import Repositories.MainOeuvreRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class MainOeuvreRepoImpl implements MainOeuvreRepository {

    private Connection conn;
    public MainOeuvreRepoImpl() {
        this.conn = DbConnection.getInstance().getConnection();
    }
    public void addMainOeuvre(MainOeuvre mainOeuvre){
        String query = "insert into mainoeuvre(nom, type_composant, taux_tva, projet_id, productivite_ouvrier,taux_horaire, heurtravail) values (?,?,?,?,?,?,?)";
        UUID mainOeuvreId = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, mainOeuvre.getNom());
            stmt.setObject(2, mainOeuvre.getTypeComposant(), java.sql.Types.OTHER);
            stmt.setDouble(3, mainOeuvre.getTauxTVA());
            stmt.setObject(4, mainOeuvre.getProjet().getId());
            stmt.setDouble(5, mainOeuvre.getProductivite());
            stmt.setDouble(6, mainOeuvre.getTauxHoraire());
            stmt.setDouble(7, mainOeuvre.getHeurTravail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
