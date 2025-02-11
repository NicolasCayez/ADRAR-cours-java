public class ExoBase {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        int nbr1 = 12;

        System.out.println(nbr1);
        int nbr2 = 58;
        System.out.println(nbr2);
        nbr2 = 20;
        System.out.println(nbr2);
        String nom1 = "Nicolas";
        System.out.println(nom1);

        int sNbr1;
        int sNbr2;

        // Variables Ex1
        System.out.println("somme 2+3 : " + somme(2,3));
        // Variables Ex2
        System.out.println("soustraction 6-2-3 : " + soustraction(6,2,3));
        // Variables Ex3
        System.out.println("moyenne 12 5 8 22 : " + moyenne(12, 5, 8, 22));
        // Variables Ex4

//        System.out.println("avant switch - i: " + sNbr1 + " j : " + sNbr2);
//        switchNumbers(sNbr1, sNbr2);
//        System.out.println("après switch - i: " + sNbr1 + " j : " + sNbr2);
    }



    // Variables Ex1
    static int somme(int i, int j) {
        return i+j;
    }

    // Variables Ex2
    static int soustraction(int i, int j, int k) {
        return i-j-k;
    }

    // Variables Ex3
    static int moyenne(int i, int j, int k, int l) {
        return (i+j+k+l)/4;
    }

    // Variables Ex4
    public int sNbr1 = 2;
    public int sNbr2 = 3;
    static void switchNumbers(int i, int j) {
        int temp = i;
        i = j;
        j = temp;
    }
}

