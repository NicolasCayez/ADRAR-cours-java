package TpAccountTaskCategory.Repositories;

import TpAccountTaskCategory.Models.Account;
import TpAccountTaskCategory.Models.Task;
import TpTabCollectionEtSQL.Database;
import TpTabCollectionEtSQL.Model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AccountRepository {
    /* ------------------------------------------ **
     * CONNEXION TO DATABASE
     * ------------------------------------------ */
    public static Connection connexion = Database.connexion;

    /* ------------------------------------------ **
     * add (INSERT) account in Database
     * ------------------------------------------ */
    public static boolean add(Account account){
        // Returned boolean
        boolean result = false;
        // Account instance
        Account newAccount = null;
        // Action
        try {
            Statement statement = connexion.createStatement();
            //requête SQL avec paramètres
            String sql = "INSERT INTO account (lastname, firstname, email, password)" + "VALUES (?, ?, ?, ?)";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setString(1, account.getLastname());
            preparedStatement.setString(2, account.getFirstname());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getPassword());
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
    public static Account findById(Integer id){
        Account accountFound = new Account();
        try{
            // Connection à la BDD ...
            Statement stmt = connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //requête SQL
            String sql = "SELECT id, lastname, firstname, email, password " +
                    "FROM account " +
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
                    accountFound = new Account();
                    accountFound.setId(resultRows.getInt("id"));
                    accountFound.setLastname(resultRows.getString("lastname"));
                    accountFound.setFirstname(resultRows.getString("firstname"));
                    accountFound.setEmail(resultRows.getString("email"));
                    accountFound.setPassword(resultRows.getString("password"));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //retourne un account
        return accountFound;
    }

    /* ------------------------------------------ **
     * findAll
     * ------------------------------------------ */
    public static ArrayList<Account> findAll(){
        ArrayList<Account> accountList = new ArrayList<>();
        Account oneAccount = null;
        try{
            // Connection à la BDD ...
            Statement stmt = connexion.createStatement();
            //requête SQL
            String sql = "SELECT id, lastname, firstname, email, password FROM account";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Exécution de la requête
            ResultSet resultRows = preparedStatement.executeQuery();
            //Parcours du résultat
            while(resultRows.next()){
                //Si la réponse est différente de null
                if (resultRows.getString(1)!= null){
                    oneAccount = new Account();
                    oneAccount.setId(resultRows.getInt("id"));
                    oneAccount.setLastname(resultRows.getString("lastname"));
                    oneAccount.setFirstname(resultRows.getString("firstname"));
                    oneAccount.setEmail(resultRows.getString("email"));
                    oneAccount.setPassword(resultRows.getString("password"));
                    accountList.add(oneAccount);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //retourne un ArrayList d'account
        return accountList;
    }


    /* ------------------------------------------ **
     * nameAlreadyExists -> chercher doublons par email
     * ------------------------------------------ */
    public static boolean emailAlreadyExists(String email){
        boolean found = false;
        try{
            // Connection à la BDD ...
            Statement stmt = connexion.createStatement();
            //requête SQL
            String sql = "SELECT id FROM account WHERE email = (?)";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setString(1, email);
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
