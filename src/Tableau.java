import java.util.ArrayList;
import java.util.Scanner;

public class Tableau {

    /* ------------------------------------------ **
     * Exécution
     * ------------------------------------------ */
    public static void main(String[] args) {
        //Cours
//        exemple();
        System.out.println("EX1 min : " + tabMin());
        System.out.println("EX2 max : " + tabMax());
        System.out.println("EX3 moyenne : " + tabAvg());
//        System.out.println("EX4 notes saisies : " + tabAvgScanner());
//        tabPosNegScanner();
        System.out.println("mini absolu : " + tabMinAbsolute());
    }

    /* ------------------------------------------ **
     * Cours
     * ------------------------------------------ */
    //Tableaux
//    static int [] tabExemple1 = {10,25,33,88,45,77}; // taille figée, ici 6 colonnes
//    int [] TabExemple2 = new int [10]; // taille figée, ici 10 colonnes
//    int [] TabExemple1Copie = tabExemple1.clone(); // copier un tableau, sans le clone, on copie l'adresse
//    //ArrayList : tableau modifiable
//    static ArrayList<String> tabExemple3= new ArrayList<>();
//    public static void exemple() {
//        System.out.println("tabExemple1 : " + tabExemple1); // Affiche l'adresse mémoire
//        tabExemple3.add("valeur0");
//        tabExemple3.add("valeur1");
//        tabExemple3.add("valeur2");
//        tabExemple3.add("valeur3");
//        System.out.println("tabExemple3[0] : " + tabExemple3.get(0));
//        //parcourir
//        for(String str : tabExemple3){
//            System.out.println("La valeur est : " + str);
//        }
//    }

    /* ------------------------------------------ **
     * Exercices
     * ------------------------------------------ */
    static int [] tab = {10,33,56,89,7,11,2,16} ;
    // EX 1 : Créer une fonction qui va trouver dans le tableau suivant la valeur minimale et la retourner.
    static int tabMin() {
        int mini = -1;
        for (int value : tab) {
            if (mini == -1){
                mini = value;
            } else if (mini > value) {
                mini = value;
            }
        }
        return mini;
    }
    // EX 2 : Créer une fonction qui va trouver dans le tableau suivant la valeur maximale et la retourner.
    static int tabMax() {
        int max = -1;
        for (int value : tab) {
            if (max == -1){
                max = value;
            } else if (max < value) {
                max = value;
            }
        }
        return max;
    }
    // EX 3 : Créer une fonction qui va calculer dans le tableau suivant la valeur moyenne et la retourner.
    static Double tabAvg() {
        Double avg = 0.0;
        for (int value : tab) {
            avg += value;
        }
        avg /= tab.length;
        return avg;
    }
    // EX 4 : Ecrire une fonction qui demande à un utilisateur de saisir des notes (int), les notes seront ajoutées à
    //  un tableau (ArrayList<Integer>) et qui va retourner la moyenne de la classe (depuis le tableau)
    static Double tabAvgScanner(){
        Scanner scanner = new Scanner(System.in);
        boolean fini = false;
        ArrayList<Double> notes = new ArrayList<>();
        System.out.println("Entrez des notes entre 0 et 20, note -1 pour arrêter la saisie");
        do {
            try {
                Double noteSaisie = scanner.nextDouble();
                if (noteSaisie == -1) {
                    fini = true;
                } else {
                    if (noteSaisie >= 0.0 && noteSaisie <= 20.0) {
                        notes.add(noteSaisie);
                    } else {
                        System.out.println("La note n'est pas entre 0 et 20");
                    }
                }
            }  catch (Exception e) {
                System.out.println("erreur" + e.getMessage());
            }
            System.out.println("Il y a actuellement : " + notes.size() + " notes");
        } while (!fini);
        Double avg = 0.0;
        for (int i = 0; i< notes.size(); i++) {
            avg += notes.get(i);
        }
        avg /= notes.size();
        return avg;
    }
    // EX 5 : Ecrivez une fonction permettant à l’utilisateur de saisir un nombre quelconque de valeurs, qui devront être stockées dans un tableau.
    // L’utilisateur doit donc commencer par entrer le nombre de valeurs qu’il compte saisir.
    // Il effectuera ensuite cette saisie.
    // Enfin, une fois la saisie terminée, la fonction affichera le nombre de valeurs négatives et le nombre de valeurs positives.
    static void tabPosNegScanner(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nombre de valeurs à saisir, 0 pour annuler");
        try {
            int nbValeurs = scanner.nextInt();
            Double [] tab = new Double[nbValeurs];
            for (int i=0; i< tab.length; i++){
                System.out.println("Entrez la valeur numéro " + i);
                tab[i] = scanner.nextDouble();
            }
            // comptage
            int nbNeg = 0;
            for (Double valeur : tab){
                if(valeur < 0.0) {
                    nbNeg ++;
                }
            }
            System.out.println("Il y a : " + tab.length + " valeurs dont " + nbNeg +
                    " valeurs négatives et " + (tab.length - nbNeg) + " valeurs positives");
        }  catch (Exception e) {
            System.out.println("erreur" + e.getMessage());
        }

        // Nettoyage
        scanner.close();
    }
    // EX 6 : Créer une fonction qui va trouver dans le tableau suivant la valeur absolue la plus petite et la retourner.
    //exemple pour le tableau : int [] tab = {2,18,-5, 12, 36, -1, 14}
    static int [] tabExo6 = {2,18,-5, 12, 36, -1, 14};
    static int tabMinAbsolute(){
        int mini = tabExo6[0];
        for (int i=0; i<tabExo6.length; i++) {
            if(tabExo6[i]>=0 && mini>=0 && tabExo6[i]<mini) { // positif ET mini positif ET plus proche de 0
                mini = tabExo6[i];
            } else if (tabExo6[i]>=0 && mini<0 && tabExo6[i]<-mini){ // positif et mini négatif ET plus proche de 0
                mini = tabExo6[i];
            } else if (tabExo6[i]<0 && mini>=0 && tabExo6[i]>-mini){ // négatif et mini positif ET plus proche de 0
                mini = tabExo6[i];
            } else if (tabExo6[i]<0 && mini<0 && tabExo6[i]>mini){ // négatif et mini négatif ET plus proche de 0
                mini = tabExo6[i];
            }
        }
        return mini;
    }
}
