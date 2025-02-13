package TpAccountTaskCategory;

import TpAccountTaskCategory.Models.Account;
import TpAccountTaskCategory.Models.Category;
import TpAccountTaskCategory.Models.Task;
import TpAccountTaskCategory.Repositories.AccountRepository;
import TpAccountTaskCategory.Repositories.CategoryRepository;
import TpAccountTaskCategory.Repositories.TaskRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /* ------------------------------------------ **
     * MAIN
     * ------------------------------------------ */
    public static void main(String[] args) {
        /* ------------------------------------------ **
         * ATTRIBUTS
         * ------------------------------------------ */
        Scanner scanner = new Scanner(System.in);

        /* ------------------------------------------ **
         * PROGRAMME
         * ------------------------------------------ */
        int choixMenu = -1;
        boolean fini = false;
        while (fini == false) {
            System.out.println("----- ACTIONS POSSIBLES -----");
            System.out.println("- 1 - Afficher la liste des taches");
            System.out.println("- 2 - Ajouter un compte");
            System.out.println("- 3 - Ajouter une catégorie");
            System.out.println("- 4 - Ajouter une tache");
            System.out.println("- 5 - Valider une tache");
            System.out.println("- 0 - Quitter");
            try {
                choixMenu = scanner.nextInt();
                switch (choixMenu) {
                    case 1: // Afficher la liste des taches
                        showTaskList(scanner);
                        break;
                    case 2: // Ajouter un compte
                        addAccount(scanner);
                        break;
                    case 3: // Ajouter une catégorie
                        addCategory(scanner);
                        break;
                    case 4: // Ajouter une tache
                        addTask(scanner);
                        break;
                    case 5: // Valider une tache
                        activateTask(scanner);
                        break;
                    case 0:
                        System.out.println("Arrêt du programme");
                        fini = true;
                        break;
                    default:
                        System.out.println("Commande non reconnue");
                        break;
                }
            } catch (Exception e){
                System.out.println("Erreur" + e.getMessage());
            }
        }
    /* ------------------------------------------ **
     * NETTOYAGE
     * ------------------------------------------ */
    scanner.close();
    }

    /* ------------------------------------------ **
     * 1 - Afficher la liste des taches
     * ------------------------------------------ */
    public static void showTaskList(Scanner scanner){
        System.out.println("----- EXISTING TASKS --------");
        ArrayList<Task> tasksList = TaskRepository.findAll();
        for (Task task : tasksList) {
            System.out.println(task.toString());
        }
    }

    /* ------------------------------------------ **
     * 2 - Ajouter un compte
     * ------------------------------------------ */
    public static void addAccount(Scanner scanner){
        Account newAccount = new Account();
        try {
            System.out.println("----- ACCOUNT CREATION ------");
            System.out.println("----- EXISTING ACCOUNTS -----");
            ArrayList<Account> accountsList = AccountRepository.findAll();
            for (Account account : accountsList) {
                System.out.println(account.toString());
            }
            System.out.println("New account: \"1\", exit: \"0\"");
            switch (scanner.nextInt()) {
                case 1 :
                    System.out.println("Type new account informations :");
                    // lastname
                    System.out.println("Lastname ?");
                    newAccount.setLastname(scanner.next());
                    // firstname
                    System.out.println("FirstName ?");
                    newAccount.setFirstname(scanner.next());
                    // email
                    System.out.println("Email ?");
                    newAccount.setEmail(scanner.next());
                    // password
                    System.out.println("Password ?");
                    newAccount.setPassword(scanner.next());
                    if (!AccountRepository.emailAlreadyExists(newAccount.getEmail())) { // pas de doublon sur l'email
                        AccountRepository.add(newAccount);
                    }
                    break;
                case 0 :
                    break;
                default:
                    System.out.println("Bad command");
                    break;
            }
        }catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    /* ------------------------------------------ **
     * 3 - Ajouter une catégorie
     * ------------------------------------------ */
    public static void addCategory(Scanner scanner){
        Category newCategory = new Category();
        try {
            System.out.println("----- CATEGORY CREATION -----");
            System.out.println("----- EXISTING CATEGORIES ---");
            ArrayList<Category> categoriesList = CategoryRepository.findAll();
            for (Category category : categoriesList) {
                System.out.println(category.toString());
            }
            System.out.println("New category: \"1\", exit: \"0\"");
            switch (scanner.nextInt()) {
                case 1 :
                    System.out.println("Type new category informations :");
                    // lastname
                    System.out.println(" Category name ?");
                    newCategory.setName(scanner.next());
                    if (!CategoryRepository.nameAlreadyExists(newCategory.getName())) { // pas de doublon sur le nom
                        CategoryRepository.add(newCategory);
                    }
                    break;
                case 0 :
                    break;
                default:
                    System.out.println("Bad command");
                    break;
            }
        }catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    /* ------------------------------------------ **
     * 4 - Ajouter une tache
     * ------------------------------------------ */
    public static void addTask(Scanner scanner){
        Task newTask = new Task();
        try {
            System.out.println("----- TASK CREATION ---------");
            System.out.println("----- EXISTING TASKS --------");
            ArrayList<Task> tasksList = TaskRepository.findAll();
            for (Task task : tasksList) {
                System.out.println(task.toString());
            }
            System.out.println("New task: \"1\", exit: \"0\"");
            switch (scanner.nextInt()) {
                case 1 :
                    System.out.println("Type new task informations :");
                    // title
                    System.out.println("Title ?");
                    newTask.setTitle(scanner.next());
                    // description
                    System.out.println("Description ?");
                    newTask.setDescription(scanner.next());
                    //date automatique
                    newTask.setCreatedAt(Date.valueOf(LocalDate.now()));
                    // categories
                    System.out.println("Première catégorie ? Entrez l'id");
                    ArrayList<Category> categoriesList = CategoryRepository.findAll();
                    for (Category category : categoriesList) {
                        System.out.println(category.toString());
                    }
                    ArrayList<Integer> categoriesID = new ArrayList<Integer>();
                    categoriesID.add(scanner.nextInt());
                    boolean categoriesEnd = false;
                    while (!categoriesEnd) {
                        System.out.println("Ajouter une catégorie? 1: oui, 0: non");
                        switch (scanner.nextInt()) {
                            case 1 :
                                System.out.println("Ajout catégorie ? Entrez l'id");
                                for (Category category : categoriesList) {
                                    System.out.println(category.toString());
                                }
                                categoriesID.add(scanner.nextInt());
                                break;
                            case 0 :
                                categoriesEnd = true;
                                break;
                            default:
                                System.out.println("Bad command");
                                break;
                        }
                    }
                    // Status automatique
                    newTask.setStatus(false);
                    // account
                    System.out.println("Utilisateur ? Entrez l'id");
                    ArrayList<Account> accountsList = AccountRepository.findAll();
                    for (Account account : accountsList) {
                        System.out.println(account.toString());
                    }
                    newTask.setAccount(AccountRepository.findById(scanner.nextInt()));
                    //enregistrement
                    TaskRepository.add(newTask, categoriesID);
                    break;
                case 0 :
                    break;
                default:
                    System.out.println("Bad command");
                    break;
            }
        }catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    /* ------------------------------------------ **
     * 5 - Valider une tache
     * ------------------------------------------ */
    public static void activateTask(Scanner scanner){
        Task taskToUpdate = new Task();
        try {
            System.out.println("----- TASK ACTIVATION -------");
            ArrayList<Task> tasksList = TaskRepository.findAll();
            System.out.println("Activate / Inactivate task ? yes : 1 - quit : 0");
            switch (scanner.nextInt()) {
                case 1 :
                    System.out.println("Type the task id from the list :");
                    System.out.println("----- EXISTING TASKS --------");
                    for (Task task : tasksList) {
                        System.out.println(task.toString());
                    }
                    taskToUpdate = TaskRepository.findById(scanner.nextInt());
                    System.out.println("Confirm the status change for the following task :");
                    System.out.println(taskToUpdate.toString());
                    System.out.println("Activate : 1 - Inactivate : 2 - Abort : 0");
                    switch (scanner.nextInt()) {
                        case 1 :
                            TaskRepository.activateTask(taskToUpdate);
                            break;
                        case 2 :
                            TaskRepository.deactivateTask(taskToUpdate);
                            break;
                        case 0 :
                            break;
                        default:
                            System.out.println("Bad command");
                            break;
                    }
                    break;
                case 0 :
                    break;
                default:
                    System.out.println("Bad command");
                    break;
            }
        }catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
