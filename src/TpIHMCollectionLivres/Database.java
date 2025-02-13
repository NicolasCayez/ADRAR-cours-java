package TpIHMCollectionLivres;

import TpIHMCollectionLivres.Env;
import TpIHMCollectionLivres.Model.Livre;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Database {
    //Connexion à la BDD
    public static Connection connexion;
    static {
        try {
            connexion = DriverManager.getConnection(TpTabCollectionEtSQL.Env.DB_URL, TpTabCollectionEtSQL.Env.DB_LOGIN,
                    Env.DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Livre addLivre(Livre livre) {
        //instancier un Objet User null
        Livre livreAdd = null;
        try{
            //Connection à la BDD...
            Statement stmt = connexion.createStatement();
            //requête SQL
            String sql = "INSERT INTO livre (titre, description_livre, date_publication, genre)" + "VALUES (?, ?, ?, ?)";
            //Préparation de la requête
            PreparedStatement preparedStatement =
                    connexion.prepareStatement(sql);

            //Bind des paramètres
            preparedStatement.setString(1, livre.getTitre());
            preparedStatement.setString(2, livre.getDescription());
            preparedStatement.setString(3, livre.getDatePublication());
//            preparedStatement.setString(4, livre.getGenre().toString());
//            String genreString = "";
//            for (String unGenre: livre.getGenre()){
//                genreString += unGenre + ",";
//            }
//            preparedStatement.setString(4, genreString);
            preparedStatement.setString(4, livre.getGenreAsString());
            //Exécution de la requête
            int addedRows = preparedStatement.executeUpdate();
            //test si l'enregistrement est ok
            if (addedRows > 0) {
                //Création d'un Objet User
                livreAdd = new Livre();
                livreAdd.setTitre(livre.getTitre());
                livreAdd.setDescription(livre.getDescription());
                livreAdd.setDatePublication(livre.getDatePublication());
                livreAdd.setGenre(livre.getGenre());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //Retourne un Objet User
        return livreAdd;
    }

    public static ArrayList<Livre> getCollection(){
        ArrayList<Livre> collection = new ArrayList<>();
        Livre livreGet = null;
        try{
            // Connection à la BDD ...
            Statement stmt = connexion.createStatement();
            //requête SQL
            String sql = "SELECT id, titre, description_livre, date_publication, genre FROM livre";
            //Préparation de la requête
            PreparedStatement preparedStatement =
                    connexion.prepareStatement(sql);
            //Bind des paramètres
            ResultSet rs = preparedStatement.executeQuery();
            //Parcours du résultat
            while(rs.next()){
                //Si la réponse est différente de null
                if (rs.getString(1)!= null){
                    livreGet = new Livre();
                    livreGet.setId(rs.getInt("id"));
                    livreGet.setTitre(rs.getString("titre"));
                    livreGet.setDescription(rs.getString("description_livre"));
                    livreGet.setDatePublication(rs.getString("date_publication"));
//                    livreGet.setGenre(rs.getString("genre"));
//                    String[] genresList = rs.getString("genre").split(Pattern.quote("."));
//                    ArrayList<String> genreArrayList = new ArrayList<>();
//                    for(String unGenre : genresList){
//                        genreArrayList.add(unGenre);
//                    }
                    livreGet.setGenreFromString(rs.getString("genre"));
                    collection.add(livreGet);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
//retourne un objet
        return collection;
    }

    public static Livre getLivreByTitre(Livre livre){
        Livre livreGet = null;
        try{
            // Connection à la BDD ...
            Statement stmt = connexion.createStatement();
            //requête SQL
            String sql = "SELECT titre, description, date_publication, genre FROM livre WHERE titre=?";
            //Préparation de la requête
            PreparedStatement preparedStatement =
                    connexion.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setString(1, livre.getTitre());
            ResultSet rs = preparedStatement.executeQuery();
            //Parcours du résultat
            while(rs.next()){
                //Si la réponse est différente de null
                if (rs.getString(1)!= null){
                    livreGet = new Livre();
                    livreGet.setTitre(rs.getString("titre"));
                    livreGet.setDescription(rs.getString("description"));
                    livreGet.setDatePublication(rs.getString("date_publication"));
//                    livreGet.setGenre(rs.getString("genre"));
                    String[] genresList = rs.getString("genre").split(Pattern.quote("."));
                    ArrayList<String> genreArrayList = new ArrayList<>();
                    for(String unGenre : genresList){
                        genreArrayList.add(unGenre);
                    }
                    livreGet.setGenre(genreArrayList);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
//retourne un objet
        return livreGet;
    }

    public static boolean removeLivre(Livre livre) {
        boolean result = false;
        try{
            //Connection à la BDD...
            Statement stmt = connexion.createStatement();
            //requête SQL
            String sql = "DELETE FROM livre WHERE id = (?)";
            //Préparation de la requête
            PreparedStatement preparedStatement =
                    connexion.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setInt(1, livre.getId());
            //Exécution de la requête
            int deletedRows = preparedStatement.executeUpdate();
            //test si l'enregistrement est ok
            if (deletedRows > 0) {
                //réussi
                result = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //Retourne un Objet User
        return result;
    }
}
