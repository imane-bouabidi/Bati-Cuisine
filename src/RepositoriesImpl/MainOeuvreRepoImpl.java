package RepositoriesImpl;

import Database.DbConnection;
import Models.Client;
import Models.MainOeuvre;
import Repositories.MainOeuvreRepository;
import Services.ProjetService;
import enums.TypeComposant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainOeuvreRepoImpl implements MainOeuvreRepository {

    private Connection conn;
    public MainOeuvreRepoImpl() {
        this.conn = DbConnection.getInstance().getConnection();
    }

    ProjetService projetS = new ProjetService();
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


    public List<MainOeuvre> returnWorkersByProject(UUID id){
        List<MainOeuvre> mainOeuvres = new ArrayList<>();
        String query = "select * from mainoeuvre where projet_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                MainOeuvre worker = new MainOeuvre(
                        rs.getString("nom"),
                        TypeComposant.valueOf(rs.getString("type_composant")),
                        rs.getDouble("taux_tva"),
                        projetS.getProjetById(UUID.fromString(rs.getString("projet_id"))),
                        rs.getDouble("productivite_ouvrier"),
                        rs.getDouble("taux_horaire"),
                        rs.getDouble("heurtravail")
                );
                mainOeuvres.add(worker);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return mainOeuvres;
    }
}
