package TpAccountTaskCategory.Repositories;

import TpAccountTaskCategory.Models.Task;
import TpTabCollectionEtSQL.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TaskRepository {
    /* ------------------------------------------ **
     * CONNEXION TO DATABASE
     * ------------------------------------------ */
    public static Connection connexion = Database.connexion;

    /* ------------------------------------------ **
     * add (INSERT) task in Database
     * ------------------------------------------ */
    public static boolean add(Task task, ArrayList<Integer> categoriesIds){
        // Returned boolean
        boolean result = false;
        // Task instance
        Task newTask = null;
        // Action
        try {
            Statement statement = connexion.createStatement();
            //requête SQL avec paramètres (TASK)
            String sql = "INSERT INTO task (title, description, createdAt, status, account_id) VALUES (?, ?, ?, ?, ?)";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //Bind des paramètres
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDate(3, task.getCreatedAt());
            preparedStatement.setInt(4, task.getStatus() ? 1 : 0);// status true -> 1 / false -> 0
            preparedStatement.setInt(5, task.getAccount().getId());
            //Exécution de la requête
            preparedStatement.executeUpdate();
            ResultSet resultRows = preparedStatement.getGeneratedKeys();
            boolean taskInserted = false;
            while(resultRows.next()){
                //Si la réponse est différente de null
                if (resultRows.getString(1)!= null){
                    newTask = new Task();
                    newTask.setId(resultRows.getInt(1));
                    taskInserted = true;
                }
            }
            int taskAddedRows = 0;
            for (int oneCategoryId : categoriesIds) {
                //requête SQL avec paramètres (TASK_CATEGORY)
                String sql2 = "INSERT INTO task_category (task_id, category_id) VALUES (?, ?)";
                //Préparation de la requête
                PreparedStatement preparedStatement2 = connexion.prepareStatement(sql2);
                //Bind des paramètres
                preparedStatement2.setInt(1, newTask.getId());
                preparedStatement2.setInt(2, oneCategoryId);
                //Exécution de la requête
                taskAddedRows = preparedStatement2.executeUpdate();
            }
            //test si l'enregistrement est ok
            if (taskInserted && taskAddedRows>0) {
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
    public static Task findById(int id){
        Task taskFound = new Task();
        try{
            // Connection à la BDD ...
            Statement stmt = connexion.createStatement();
            //requête SQL
            String sql = "SELECT id, title, description, createdAt, status, account_id " +
                    "FROM task " +
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
                    taskFound = new Task();
                    taskFound.setId(resultRows.getInt("id"));
                    taskFound.setTitle(resultRows.getString("title"));
                    taskFound.setDescription(resultRows.getString("description"));
                    taskFound.setCreatedAt(resultRows.getDate("createdAt"));
                    taskFound.setStatus(resultRows.getBoolean("status"));
                    taskFound.setAccount(AccountRepository.findById(resultRows.getInt("account_id")));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //retourne un ArrayList de task
        return taskFound;
    }

    /* ------------------------------------------ **
     * findAll
     * ------------------------------------------ */
    public static ArrayList<Task> findAll(){
        ArrayList<Task> taskList = new ArrayList<>();
        Task oneTask = null;
        try{
            // Connection à la BDD ...
            Statement stmt = connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //requête SQL
            String sql = "SELECT id, title, description, createdAt, status, account_id " +
                    "FROM task";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Exécution de la requête
            ResultSet resultRows = preparedStatement.executeQuery();
            //Parcours du résultat
            while(resultRows.next()){
                //Si la réponse est différente de null
                if (resultRows.getString(1)!= null){
                     oneTask = new Task();
                     oneTask.setId(resultRows.getInt("id"));
                     oneTask.setTitle(resultRows.getString("title"));
                     oneTask.setDescription(resultRows.getString("description"));
                     oneTask.setCreatedAt(resultRows.getDate("createdAt"));
                     oneTask.setStatus(resultRows.getBoolean("status"));
                     oneTask.setAccount(AccountRepository.findById(resultRows.getInt("account_id")));
                     taskList.add(oneTask);
                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        //retourne un ArrayList de task
        return taskList;
    }

    /* ------------------------------------------ **
     * activate Task
     * ------------------------------------------ */
    public static boolean activateTask(Task task){
        // Returned boolean
        boolean result = false;
        // Action
        try {
            Statement statement = connexion.createStatement();
            //requête SQL avec paramètres (TASK)
            String sql = "UPDATE task SET status = 1 WHERE id = (?)";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //Bind des paramètres
            preparedStatement.setInt(1, task.getId());
            //Exécution de la requête
            preparedStatement.executeUpdate();
            int resultRows = preparedStatement.executeUpdate();
            boolean taskInserted = false;
            if (resultRows>0){
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // returns true if INSERT ok, false if not ok
        return result;
    }

    /* ------------------------------------------ **
     * Deactivate Task
     * ------------------------------------------ */
    public static boolean deactivateTask(Task task){
        // Returned boolean
        boolean result = false;
        // Action
        try {
            Statement statement = connexion.createStatement();
            //requête SQL avec paramètres (TASK)
            String sql = "UPDATE task SET status = 0 WHERE id = (?)";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //Bind des paramètres
            preparedStatement.setInt(1, task.getId());
            //Exécution de la requête
            preparedStatement.executeUpdate();
            int resultRows = preparedStatement.executeUpdate();
            boolean taskInserted = false;
            if (resultRows>0){
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // returns true if INSERT ok, false if not ok
        return result;
    }
}
