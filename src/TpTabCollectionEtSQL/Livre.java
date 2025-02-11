package TpTabCollectionEtSQL;

import java.util.ArrayList;

public class Livre {
    /* ------------------------------------------ **
     * ATTRIBUTES
     * ------------------------------------------ */
    String titre;
    String description;
    String datePublication;
    ArrayList<String> genre = new ArrayList<>();

    /* ------------------------------------------ **
     * CONSTRUCTORS
     * ------------------------------------------ */
    //vide (sans paramètre),
    public Livre() {
    }
    //complet (titre, description, datePublication, genre),
    public Livre(String titre, String description, String datePublication, ArrayList<String> genre) {
        this.titre = titre;
        this.description = description;
        this.datePublication = datePublication;
        this.genre = genre;
    }

    /* ------------------------------------------ **
     * GET / SET
     * ------------------------------------------ */
    // Titre
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    // Description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    // DatePublication
    public String getDatePublication() {
        return datePublication;
    }
    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }
    // Genre
    public ArrayList<String> getGenre() {
        return genre;
    }
    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }
}
