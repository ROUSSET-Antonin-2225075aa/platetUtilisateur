package com.example.platsetutilisateur;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accèder aux plats stockés dans une base de données Mariadb
 */
public class PlatsRepositoryMariadb implements PlatsRepositoryInterface, Closeable {

    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;

    /**
     * Constructeur de la classe
     * @param infoConnection chaîne de caractères avec les informations de connexion
     *                       (p.ex. jdbc:mariadb://mysql-[compte].alwaysdata.net/[compte]_library_db
     * @param user chaîne de caractères contenant l'identifiant de connexion à la base de données
     * @param pwd chaîne de caractères contenant le mot de passe à utiliser
     */
    public PlatsRepositoryMariadb(String infoConnection, String user, String pwd ) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Plats getPlat(String reference) {

        Plats selectedPlats = null;

        String query = "SELECT * FROM Plats WHERE reference=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, reference);

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si la référence du plat est valide)
            if( result.next() )
            {
                String title = result.getString("title");
                Integer prix = Integer.valueOf(result.getString("prix"));
                String description = result.getString("description");

                // création et initialisation de l'objet Plats
                selectedPlats = new Plats(reference, title, prix, description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedPlats;
    }

    @Override
    public ArrayList<Plats> getAllPlats() {
        ArrayList<Plats> listPlats;

        String query = "SELECT * FROM Plats";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listPlats = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                String reference = result.getString("reference");
                String title = result.getString("title");
                Integer prix = Integer.valueOf(result.getString("prix"));
                String description = result.getString("description");

                // création du plat courant
                Plats currentPlats = new Plats(reference, title, prix, description);

                listPlats.add(currentPlats);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listPlats;
    }

    @Override
    public boolean updatePlat(String reference, String title, Integer prix,String description) {
        String query = "UPDATE Plats SET title=?, prix=?, description=?  where reference=?";
        int nbRowModified = 0;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, title);
            ps.setString(2, String.valueOf(prix));
            ps.setString(4, reference);
            ps.setString(4, description);

            // exécution de la requête
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }
}