
import java.awt.event.*; //importation librairies

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Convertisseur extends JFrame implements ActionListener { // Création classe convertisseur fille à partir de la classe mère fenetre. Ajout fonctions à la classe fenêtre.
   // le taux de conversion.  
   protected double taux = 1.3563;  // 1 euro = 1,3563 dollars. Variables privées accessibles seulement depuis la classe et les classes enfants.
   protected JLabel tauxL; // permet l'affichage du texte : taux de conversion. 
   protected JTextField euro; // champs de saisie de la valeur a convertir
   protected JTextField dollar; // champs de texte valeur convertie.
   
   protected JPanel pListe = new JPanel(); // element tache de fond pour décomposer la fenetre
   protected JComboBox liste = new JComboBox(); // liste de choix des monnaies 
   protected JLabel lListe = new JLabel(" Monnaie"); // label monnaie convertie au dessus combobox
   protected JLabel image = new JLabel( new ImageIcon("src/euros.jpg"));
   protected Graphe graphic = new Graphe();
   
   
   public double [] tauxValues = {1.3594, 138.463, 0.8134, 11.2339892};
   protected ImageIcon [] images = { new ImageIcon("src/dollars.jpg"), new ImageIcon("src/yen.jpg"), new ImageIcon("src/livres.jpg"), new ImageIcon("src/dirham.jpg") };
   
   protected String [] graphsDataPath = {"src/EuroDollars.txt", "src/EurosYen.txt", "src/EuroLivres.txt"};
   protected double [] graphsYFactor = {-465.84, -0.013463, -949.37}; 
		   
   public Convertisseur(){ // constructeur 

   // appel du constructeur de la superclasse (classe mere) JFrame : creation d'une fenetre.
      super("Convertisseur d'Alexia Clémence et Louis");
      
      //--------------------------------------------------------------------
      //                 Les composants graphiques 
      // -------------------------------------------------------------------
      // recuperation de l'objet ContentPane : fond de la fenêtre
      Container contentPane = this.getContentPane();

      // mise en place d'un BorderLayout, division nord, sud, est, ouest, centre.
      contentPane.setLayout(new BorderLayout());

      
      JPanel pNord=new JPanel(); // décomposition tâche de fond partie nord (pNord) du BorderLayout
      JLabel titre = new JLabel("CONVERTISSEUR EURO    "); // label titre
      Font f=new Font("AR BLANCA",Font.BOLD,20); // creation police f
 
      titre.setFont(f); // police f dans titre
      pNord.add(titre); // titre dans pNord
      JPanel pEuro =new JPanel(new BorderLayout()); // nouveau Jpanel dans le JPanelNord puis nouveau Border Layout
      pEuro.add(new JLabel("Euro"),BorderLayout.NORTH); // JLabel euros au nord  
      pEuro.add(euro = new JTextField(10),BorderLayout.SOUTH); // JTextField pour la valeur en euros au sud
      pNord.add(pEuro); //ajout pEuro dans pNord
      pNord.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)); // marge entre les JPanel
      
      JPanel pEst=new JPanel(new BorderLayout()); // a l'est
      
      contentPane.add(pEst,BorderLayout.EAST);
      
      
      JPanel pListe =new JPanel(new BorderLayout()); // JLabel et JTextField pour la valeur en dollar
      
      pListe.add(lListe,BorderLayout.NORTH); // ajout protected Label monnaie dans pliste JPanel dollars
      pListe.add(liste,BorderLayout.SOUTH); // ajout protected ComboBox 
      pNord.add(pListe); // pliste dans Pnord du BorderLayout
      
      liste.setPreferredSize(new Dimension(100, 20)); // reglage taille ComboBox
      liste.addItem("Dollar"); 
      liste.addItem("Yen");
      liste.addItem("Livre");
      liste.addItem("Dirham");
      
      contentPane.add(pNord,BorderLayout.NORTH);
      
      JButton bt = new JButton("CONVERTIR"); // Creation et ajout du JButton declenchant la conversion
      JPanel pBouton =new JPanel(); 
      pBouton.add(bt);
      contentPane.add(pBouton,BorderLayout.SOUTH);

  
      JPanel pCentre =new JPanel(new BorderLayout()); // JLabel et  JTextField pour la valeur en dollar
      JPanel pResultat =new JPanel();
      JLabel lResultat = new JLabel("Valeur en Dollars");   
      dollar = new JTextField(10);
      pResultat.add(lResultat);
      pResultat.add(dollar);
      pResultat.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
      
      pEst.add(pResultat,BorderLayout.NORTH); // pResultat.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
      pEst.add(graphic,BorderLayout.CENTER);
      
      image.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
      image.setPreferredSize(new Dimension(200,200));
      
      pCentre.add(image,BorderLayout.CENTER);
      JPanel pTaux =new JPanel(); 
      tauxL = new JLabel("");
      pTaux.add(tauxL);
      
      pCentre.add(pTaux,BorderLayout.SOUTH);
      
      dollar.setEditable(false);
            
      contentPane.add(pCentre,BorderLayout.CENTER);

      //--------------------------------------------------------------------------
      // enrigistrement des "Listeners" pour gerer l'interaction.

      //this.addWindowListener(this);
      bt.addActionListener(this);
      liste.addActionListener(this);
      this.setJMenuBar(new MenuConvertisseur(this));

      // ajustement de la taille de la fenetre en fonction des composants qu'elle
      // contient et des layout utilises.
      this.pack();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // la fenetre n'est pas redimensionnable par l'utilisateur
      //this.setResizable(false);

   }

   /**
    * convertit une valeur en lui appliquant le taux de conversion.
    * @param v valeur a convertir.
    */
   public double convertir(double v,int k) {
      return v * tauxValues[k];
   }
   
   
   /**
    * retourne le taux de conversion.
    * @return le taux de conversion.
    */
   public double taux() {
      return taux;
   }

   //---------------- la methode de l'interface ActionListener ---------------
   // lorsque le bouton a ete active recupere la chaine contenue dans le
   // JTextFiel vIn, la convertit en un double et l'affiche dans le JTextField
   // vOut. Au cas o� la cha�ne contenue dans vIn ne repr�sente pas un r�el, 
   // un panneau d'alerte avec un message d'erreur le signale � l'utilisateur.
  public void actionPerformed(ActionEvent e) {
        try {  //essai 
           String strEuros = euro.getText().replace(',','.'); // recupere la valeur euros, transforme virgule en point
           double val = Double.parseDouble(strEuros); // appel fonction classe double. parseDouble : Transforme le string (tableau de caractères) en double pour le calcul
           System.out.println(val); // sortie double sur console
           int item = liste.getSelectedIndex(); // indexage des valeurs de la ComboBox
           dollar.setText(""+CurrencyFormatter.formatte(convertir(val,item),2,item));
           image.setIcon(images[item]);
           tauxL.setText("taux : 1 euro = "+ CurrencyFormatter.formatte(convertir(1,item),5,item));
           
           //TODO if >3
           graphic.drawGraphique(graphsDataPath[item]);
        }
        catch (NumberFormatException ex) { // si exception du try : catch
              JOptionPane.showMessageDialog(null,"Entrez un nombre !!!",
                   "alert",  JOptionPane.ERROR_MESSAGE); 
         }
    }

  
   public static void main(String[] args) { // execution du code    
      // creation de la fenetre
      Convertisseur c =  new Convertisseur();
      // affichage de la fenetre
      c.setVisible(true);  
   }

public void setTauxValues(double[] values) {
	this.tauxValues = values;
    tauxL.setText("Taux modifiés");
    if(euro.getText() != ""){
    	System.out.println("ACTION?  "); 
    }
}  
 
}// Convertisseur

 






