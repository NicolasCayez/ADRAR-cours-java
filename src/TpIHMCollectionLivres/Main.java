package TpIHMCollectionLivres;

import TpIHMCollectionLivres.Views.CollectionMain;
import TpIHMCollectionLivres.Database;
import TpIHMCollectionLivres.Model.Livre;
import TpIHMCollectionLivres.Repositories.LivreRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /* ------------------------------------------ **
     * ATTRIBUTS
     * ------------------------------------------ */
    static ArrayList<Livre> collection = new ArrayList<>();

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
        //Initialisation
        CollectionMain app = new CollectionMain(null);
       
        
        
        
        int choixMenu = -1;
        boolean fini = false;
        while (fini == false) {
            System.out.println("----- ACTIONS POSSIBLES -----");
            System.out.println("- 1 - Afficher la collection");
            System.out.println("- 2 - Ajout d'un livre");
            System.out.println("- 3 - Suppression d'un livre");
            System.out.println("- 0 - Quitter");
            try {
                choixMenu = scanner.nextInt();
                switch (choixMenu) {
                    case 1:
                        findAll(scanner);
                        break;
                    case 2:
                        add(scanner);
                        break;
                    case 3:
                        remove(scanner);
                        break;
                    case 0:
                        System.out.println("Arrêt du programme");
                        app.dispose();
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
     * METHODES
     * ------------------------------------------ */
    //add : ajouter un livre à la collection (qui utilise un scanner et ajoute le livre à une ArraList),
    static void add(Scanner scanner){
        Livre nouveauLivre = new Livre();
        try {
            System.out.println("----- CREATION DE LIVRE -----");
            System.out.println("Saisie d'un nouveau livre");
            // Titre
            System.out.println("Titre ?");
            nouveauLivre.setTitre(scanner.next());
            // Description
            System.out.println("Description ?");
            nouveauLivre.setDescription(scanner.next());
            // Date de publication
            System.out.println("Date de publication ?");
            nouveauLivre.setDatePublication(scanner.next());
            // Genres
            boolean fin = false;
            String unGenre = "";
            ArrayList<String> genreList = new ArrayList<>();
            while (!fin) {
                System.out.println("Ajouter un genre ? entrez 'fin' pour arrêter");
                unGenre = scanner.next();
                switch (unGenre){
                    case "fin" -> fin = true;
                    default -> genreList.add(unGenre);
                }
            }
            nouveauLivre.setGenre(genreList);
//            collection.add(nouveauLivre);
            if (!LivreRepository.isExistingLivreByTitre(nouveauLivre)) { // pas de doublon sur le titre
                LivreRepository.insertLivre(nouveauLivre);
            }
            // Ancienne version sans repo
//            Database.addLivre(nouveauLivre);
        }catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
    //remove : supprimer un livre de la collection (qui utilise un scanner et supprime le livre de l'ArraList),
    static void remove(Scanner scanner){
        findAll(scanner);
        try {
            System.out.println("----- SUPPRESSION -----------");
            System.out.println("Choix du livre à supprimer (N° ?)");
            int index = scanner.nextInt() - 1; //car la liste commence à 1 et l'indice à 0
            if (collection.get(index) != null) { // le livre existe dans la collection
                System.out.println("Suppression du livre N°" + (index+1) + " - Titre: " + collection.get(index).getTitre());
                Database.removeLivre(collection.get(index));
//                collection.remove(index);
                //*********************************
            } else {
                System.out.println("Livre introuvable");
            }
        }catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
    //findAll : afficher la liste de tous les livres de la collection (qui utilise un scanner et affiche les enregistrements de l'ArrayList).
    static void findAll(Scanner scanner){
        System.out.println("----- LISTE DES LIVRES ------");
        System.out.println("Voici la liste des livres");
        collection = Database.getCollection();
        if (collection.size() == 0) {
            System.out.println("Pas de livres dans la collection");
        }
        try {
            Livre leLivre = new Livre();
            for(int i = 0; i< collection.size(); i++) {
                leLivre = collection.get(i);
//                String genresDuLivre = "";
//                for (String unGenre : leLivre.getGenre()) {
//                    genresDuLivre = unGenre + ",";
//                }
                String genresDuLivre = leLivre.getGenreAsString();
                System.out.println("N°" + (i+1) +
                        "- Titre: " + leLivre.getTitre() +
                        "- Description: " + leLivre.getTitre() +
                        "- Date Publivation: " + leLivre.getDatePublication() +
                        "- Genres: " + genresDuLivre);
            }
        }catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}


//Partie 2 :
//Mettre en place une base de données MySQL avec une table livre (id, titre, description, date_publication, genre)
//Modifier les méthodes pour qu'elles interagissent avec cette base de données. (modifié)
//
//1
//[12:45]
//NB : déposer le code de l'exercice dans le chanel discord rendu-travail-scéance-asynchrone.
//la partie du cours sur les requêtes SQL se trouve dans le chanel Java fil support de cours