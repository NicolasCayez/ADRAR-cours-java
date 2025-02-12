package TpTabCollectionEtSQL;

import java.util.ArrayList;
import java.util.regex.Pattern;

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
    public String getGenreAsString() {
        String genreString = "";
        for (String unGenre: this.getGenre()){
            genreString = genreString + unGenre + ",";
        }
        return genreString;
    }
    public void setGenreFromString(String genre) {
        String[] genresList = genre.split(Pattern.quote(","));
        ArrayList<String> genreList = new ArrayList<>();
        for(String unGenre : genresList){
            genreList.add(unGenre);
        }
        this.genre = genreList;
    }
}
