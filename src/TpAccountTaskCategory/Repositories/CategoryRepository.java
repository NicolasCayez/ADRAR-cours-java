package TpAccountTaskCategory.Repositories;

import TpAccountTaskCategory.Models.Account;
import TpAccountTaskCategory.Models.Category;
import TpAccountTaskCategory.Models.Task;
import TpTabCollectionEtSQL.Database;
import TpTabCollectionEtSQL.Model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryRepository {
    /* ------------------------------------------ **
     * CONNEXION TO DATABASE
     * ------------------------------------------ */
    public static Connection connexion = Database.connexion;

    /* ------------------------------------------ **
     * add (INSERT) category in Database
     * ------------------------------------------ */
    public static boolean add(Category category){
        // Returned boolean
        boolean result = false;
        // Category instance
        Category newCategory = null;
        // Action
        try {
            Statement statement = connexion.createStatement();
            //requête SQL avec paramètres
            String sql = "INSERT INTO category (name)" + "VALUES (?)";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setString(1, category.getName());
            //Exécution de la requête
            int addedRows = preparedStatement.executeUpdate();
            //test si l'enregistrement est ok
            if (addedRows > 0) {
                // INSERT ok, result = true
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // returns true if INSERT ok, false if not ok
        return result;
    }

    /* ------------------------------------------ **
     * findById
     * ------------------------------------------ */
    public static Category findById(int id){
        Category categoryFound = new Category();
        try{
            // Connection à la BDD ...
            Statement stmt = connexion.createStatement();
            //requête SQL
            String sql = "SELECT id" +
                    "FROM category" +
                    "WHERE id = (?)";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setInt(1, id);
            //Exécution de la requête
            ResultSet resultRows = preparedStatement.executeQuery();
            //Parcours du résultat
            while(resultRows.next()){
                //Si la réponse est différente de null
                if (resultRows.getString(1)!= null){
                    categoryFound = new Category();
                    categoryFound.setId(resultRows.getInt("id"));
                    categoryFound.setName(resultRows.getString("name"));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //retourne une catégory
        return categoryFound;
    }

    /* ------------------------------------------ **
     * findAllByTaskId
     * ------------------------------------------ */
    public static ArrayList<Category> findAllByTaskId(int id){
        ArrayList<Category> categoryList = new ArrayList<>();
        Category oneCategory = new Category();
        try{
            // Connection à la BDD ...
            Statement stmt = connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //requête SQL
            String sql = "SELECT category.id, category.name " +
                    "FROM category " +
                    "INNER JOIN task_category ON category.id = task_category.category_id " +
                    "INNER JOIN task ON task.id = task_category.task_id " +
                    "WHERE task.id = (?)";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setInt(1, id);
            //Exécution de la requête
            ResultSet resultRows = preparedStatement.executeQuery();
            //Parcours du résultat
            while(resultRows.next()){
                //Si la réponse est différente de null
                if (resultRows.getString(1)!= null){
                    oneCategory = new Category();
                    oneCategory.setId(resultRows.getInt("id"));
                    oneCategory.setName(resultRows.getString("name"));
                    categoryList.add(oneCategory);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //retourne une catégory
        return categoryList;
    }

    /* ------------------------------------------ **
     * findAll
     * ------------------------------------------ */
    public static ArrayList<Category> findAll(){
        ArrayList<Category> categoryList = new ArrayList<>();
        Category oneCategory = null;
        try{
            // Connection à la BDD ...
            Statement stmt = connexion.createStatement();
            //requête SQL
            String sql = "SELECT id, name FROM category";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Exécution de la requête
            ResultSet resultRows = preparedStatement.executeQuery();
            //Parcours du résultat
            while(resultRows.next()){
                //Si la réponse est différente de null
                if (resultRows.getString(1)!= null){
                    oneCategory = new Category();
                    oneCategory.setId(resultRows.getInt("id"));
                    oneCategory.setName(resultRows.getString("name"));
                    categoryList.add(oneCategory);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //retourne un ArrayList de catégories
        return categoryList;
    }

    /* ------------------------------------------ **
     * nameAlreadyExists -> chercher doublons par name
     * ------------------------------------------ */
    public static boolean nameAlreadyExists(String name){
        boolean found = false;
        try{
            // Connection à la BDD ...
            Statement stmt = connexion.createStatement();
            //requête SQL
            String sql = "SELECT id FROM category WHERE name = (?)";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setString(1, name);
            ResultSet result = preparedStatement.executeQuery();
            //Parcours du résultat
            while(result.next()){
                //Si la réponse est différente de null
                if (result.getString(1)!= null){
                    // Il y a un résultat
                    found = true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //retourne true si doublon trouvé
        return found;
    }
}
