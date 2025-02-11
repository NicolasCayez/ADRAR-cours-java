import java.awt.image.ByteLookupTable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExoScanner {
    public static void main(String[] args) {
        /* ***************************************** **
         * On instancie une fois le scanner
         ** ***************************************** */
        Scanner scanner = new Scanner(System.in);

        /* ***************************************** **
         * Exercices
         ** ***************************************** */
        // Scanner EX1
//        System.out.println(square(scanner));
        // Scanner EX2
//        System.out.println(sum2Nbr(scanner));
        // Scanner EX3
//        System.out.println(moy3Nbr(scanner));
        // Scanner EX4
//        System.out.println(calculTVA(scanner));
        // Scanner EX5
        mathCalcul(scanner);

        /* ***************************************** **
        * Nettoyage
        ** ***************************************** */
        scanner.close();
    }

    /* ***************************************** **
     * Exercices
     ** ***************************************** */
//    static String scanString(Scanner scanner) {
//        try {
//            return scanner.nextLine();
//        } catch (Exception e) {
//            System.out.println("Il faut une chaine de caractères" + e.getMessage());
//            return "";
//        }
//    }
//    static int scanInt(Scanner scanner) {
//        try {
//            return scanner.nextInt();
//        } catch (Exception e) {
//            System.out.println("Il faut un entier" + e.getMessage());
//            return 0;
//        }
//    }
//    static float scanFloat(Scanner scanner) {
//        try {
//            return scanner.nextFloat();
//        } catch (Exception e) {
//            System.out.println("Il faut un nombre à virgule" + e.getMessage());
//            return 0;
//        }
//    }
    // Scanner Ex1
//    static int square(Scanner scanner) {
//        System.out.println("Veuillez entrer un nombre");
//        int n = scanInt(scanner);
//        return n*n;
//    }

    // Scanner Ex2
//    static int sum2Nbr(Scanner scanner) {
//        System.out.println("Veuillez entrer un premier nombre");
//        int n1 = scanInt(scanner);
//        System.out.println("Veuillez entrer un second nombre");
//        int n2 = scanInt(scanner);
//        return n1+n2;
//    }
    // Scanner Ex3
//    static float moy3Nbr(Scanner scanner) {
//        System.out.println("Veuillez entrer un premier nombre");
//        float n1 = scanFloat(scanner);
//        System.out.println("Veuillez entrer un second nombre");
//        float n2 = scanFloat(scanner);
//        System.out.println("Veuillez entrer un troisième nombre");
//        float n3 = scanFloat(scanner);
//        return (n1+n2+n3)/3;
//    }
    // Scanner Ex4
//    static String calculTVA(Scanner scanner) {
//        System.out.println("Veuillez entrer le prix HT");
//        float prixHT = scanFloat(scanner);
//        System.out.println("Veuillez entrer la quantité");
//        float qte = scanInt(scanner);
//        System.out.println("Veuillez entrer le taux de TVA");
//        float tva = scanFloat(scanner);
//        float prixTTC = prixHT * qte * tva;
//        return ("Le prix TTC est " + prixTTC);
//    }
    // Scanner EX5
    static void mathCalcul(Scanner scanner) {
        try {
            // Saisie des infos
            Double n1 = 0.0, n2 = 0.0;
            System.out.println("Veuillez entrer le type d'opération: add (addition), sous (soustraction), div (division), multi (mutliplication)");
            String calcul = scanner.nextLine();
            if (calcul == "add" || calcul == "sous" || calcul == "div" || calcul == "multi") {
                System.out.println("Veuillez entrer un premier nombre");
                n1 = scanner.nextDouble();
                System.out.println("Veuillez entrer un second nombre");
                n2 = scanner.nextDouble();
            } else {
                System.out.println("La commande n'est pas reconnue");
            }


            // Traitement
            Double result;
            switch (calcul) {
                case "add" :
                    result = n1 + n2;
                    System.out.println(n1 + " + " + n2 + " = " + result);
                    break;
                case "sous" :
                    result = n1 - n2;
                    System.out.println(n1 + " - " + n2 + " = " + result);
                    break;
                case "div" :
                    if (n2 == 0) {
                        System.out.println("Division par zéro impossible");
                    } else {
                        result = n1 / n2;
                        System.out.println(n1 + " / " + n2 + " = " + result);
                    }
                    break;
                case "multi" :
                    result = n1 * n2;
                    System.out.println(n1 + " * " + n2 + " = " + result);
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            System.out.println("erreur" + e.getMessage());
        }
    }
    /* ***************************************** **
     * Correction
     ** ***************************************** */
    //Méthode qui retourne le carré d'un nombre
//    private static int carre(Scanner scanner) {
//        try {
//            //Message invite de saisie d'un nombre entier
//            System.out.println("Veuillez saisir un nombre de type int");
//            //Récupération du nombre avec le Scanner
//            int nbr = scanner.nextInt();
//            //retouner le carre
//            return (nbr*nbr);
//
//        } catch (InputMismatchException e) {
//            //Retourner une  InputMismatchException dans le cas ou ce n'est pas un nombre
//            throw  new InputMismatchException("Le format est incorrect");
//        }
//    }
    //Méthode qui retourne la somme
//    private static int somme(Scanner scanner) {
//        try {
//            //Message invite de saisie d'un nombre entier
//            System.out.println("1 - Veuillez saisir un nombre");
//            //Récupération du nombre avec le Scanner
//            int nbr1 = scanner.nextInt();
//            System.out.println("2 - Veuillez saisir un nombre");
//            //Récupération du nombre avec le Scanner
//            int nbr2 = scanner.nextInt();
//            //Retourner la somme
//            return nbr1 + nbr2;
//        }
//        catch (InputMismatchException e) {
//            //Retourner une  InputMismatchException dans le cas ou ce n'est pas un nombre
//            throw new InputMismatchException("Le format est incorrect");
//        }
//    }
    //Moyenne
//    private static double moyenne(Scanner scanner) {
//        try {
//            //Message invite de saisie d'un nombre entier
//            System.out.println(" 1 - Veuillez saisir un nombre de type int");
//            double nbr1 = scanner.nextDouble();
//            System.out.println("2 - Veuillez saisir un nombre de type int");
//            double nbr2 = scanner.nextDouble();
//            System.out.println("3 - Veuillez saisir un nombre de type int");
//            double nbr3 = scanner.nextDouble();
//
//            return (nbr1+nbr2+nbr3) / 3;
//        } catch (InputMismatchException e) {
//            throw  new InputMismatchException("Le format est incorrect");
//        }
//    }
    //prix TTC
//    private static double prixTtc(Scanner scanner) {
//        try {
//            //Message invite de saisie d'un nombre entier
//            System.out.println(" 1 - Veuillez saisir le prix HT de l'article");
//            double prixHt = scanner.nextDouble();
//            System.out.println("2 - Veuillez saisir La quantité");
//            int quantite = scanner.nextInt();
//            System.out.println("3 - Veuillez saisir le taux de TVA : ex  20 pour 20%");
//            double tva = scanner.nextDouble();
//            if(tva > 1) {
//                return prixHt * quantite * ((tva /100) + 1);
//            }
//            return prixHt * quantite * (tva + 1);
//        }
//        catch (InputMismatchException e) {
//            throw new InputMismatchException("Le format est incorrect");
//        }
//    }
}
