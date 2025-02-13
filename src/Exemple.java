import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exemple extends JDialog{
    /* ------------------------------------------ **
     * ATTRIBUTES (GUI elements)
     * ------------------------------------------ */
    private JPanel jpMain;
    private JLabel jlName;
    private JTextField tfName;
    private JButton btAjouter;
    private JLabel jlDescription;
    private JButton btFermer;
    
    /* ------------------------------------------ **
     * CONSTRUCTOR
     * ------------------------------------------ */
    public Exemple(JDialog parent){
        super(parent);
        setTitle("Mon formulaire");
        // Assigner le conainer
        setContentPane(jpMain);
        // Assigner la taille de l'interface
        setMinimumSize(new Dimension(800,600));
        setMaximumSize(new Dimension(800,600));//si même valeur pas de redimansion possible
        //Choisir si c'est un modal ou non
        setModal(false);
        setVisible(true);
        
        btAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("plop : " + tfName.getText());
            }
        });
        
        btFermer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
    }
}
