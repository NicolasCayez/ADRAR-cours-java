package TpIHMCollectionLivres.Views;

import TpIHMCollectionLivres.Repositories.LivreRepository;
import TpIHMCollectionLivres.Model.Livre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CollectionMain extends JDialog{
    private JPanel jpMain;
    private JButton btAjouterEtRetourListe;
    private JButton btQuitter;
    private JList lLivres;
    private JPanel jpLivresOuFormulaire;
    private JTextField tfId;
    private JTextField tfTitre;
    private JTextField tfDescription;
    private JTextField tfDatePublication;
    private JTextField tfGenre;
    private JLabel lId;
    private JLabel lTitre;
    private JLabel lDescription;
    private JLabel lDatePubication;
    private JLabel lGenre;
    private JPanel jpFormulaire;
    private JButton btEnregistrer;
    
    /* ------------------------------------------ **
     * CONSTRUCTEUR
     * ------------------------------------------ */
    public CollectionMain(JDialog parent){
        super(parent);
        setTitle("Ma collection de livres");
        setContentPane(jpMain);
        setMinimumSize(new Dimension(800, 600));
        setMaximumSize(new Dimension(800, 600));
        setModal(false);
        setVisible(true);
        
        //Initialisation
//        DefaultListModel<String> model = new DefaultListModel<>();
        refreshList();
        lLivres.setVisible(true);
        jpFormulaire.setVisible(false);
        
        
        btQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btAjouterEtRetourListe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lLivres.isVisible()) {
                    lLivres.setVisible(false);
                    jpFormulaire.setVisible(true);
                    btAjouterEtRetourListe.setText("Retour liste");
                }else if (!lLivres.isVisible()){
                    lLivres.setVisible(true);
                    jpFormulaire.setVisible(false);
                    btAjouterEtRetourListe.setText("Ajouter un livre");
                    
                }
                
            }
        });
        lLivres.addComponentListener(new ComponentAdapter() {
        });
        btEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] genresList = tfGenre.getText().split(Pattern.quote(","));
                ArrayList<String> genreList = new ArrayList<>();
                for(String unGenre : genresList){
                    genreList.add(unGenre);
                }
                Livre nouveauLivre = new Livre(tfTitre.getText(),tfDescription.getText(),tfDatePublication.getText(), genreList);
                try {
                    LivreRepository.insertLivre(nouveauLivre);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                refreshList();
                btAjouterEtRetourListe.doClick();
                
            }
        });
    }
    
    public void refreshList(){
        DefaultListModel<String> model = new DefaultListModel<>();
        lLivres.setModel(model);
        JList<String> liste = new JList<>( model );
        ArrayList<Livre> collection = LivreRepository.getCollection();
        for(int i=0; i< collection.size(); i++){
            model.addElement(collection.get(i).toString());
            
        }
    }
}
