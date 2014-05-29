
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

// La barre de menu du convertisseur
public class MenuConvertisseur extends JMenuBar implements ActionListener {
   private Convertisseur conv;//Le convertisseur
   private DialogTauxConversion dialTaux; // le JDialog pour fixer le taux de conversion
   private JMenuItem itemConfig, itemQuitter, itemApropos;// les items des menus

  // constructeur de la barre de menu de l'application convertisseur.
   public MenuConvertisseur(Convertisseur c) {    
      conv = c; 
      JMenu menuFichier = new JMenu("Fichier");
      itemConfig = new JMenuItem("Configurer");
      itemQuitter = new JMenuItem("Quitter");
      menuFichier.add(itemConfig);
      itemConfig.addActionListener(this);
      menuFichier.add(itemQuitter);
      itemQuitter.addActionListener(this);
      JMenu menuAide = new JMenu("Aide");
      itemApropos = new JMenuItem("A propos");
      menuAide.add(itemApropos);
      itemApropos.addActionListener(this);

      this.add(menuFichier);
      this.add(menuAide);

      dialTaux = new DialogTauxConversion(conv);
   }      
  // traitement des �v�nements issus des items des menus.
   public void actionPerformed(ActionEvent e) {

      if (e.getSource() == itemConfig) {
         dialTaux.setVisible(true);
      }
      else if (e.getSource() == itemQuitter)
         System.exit(0);
      else if (e.getSource() == itemApropos)
         JOptionPane.showMessageDialog(conv, 
             "<HTML><BODY>Alexia Boulay<br>Clemence Fénières<br>Louis Lançon<br>merci à Ibrahim Alame<br><a href=\"http://www.beeg.com\"</a></BODY></HTML>");
   }

}// MenuConvertisseur