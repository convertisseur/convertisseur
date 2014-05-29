
import javax.swing.JDialog;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentListener;

// JDialog permettant de fixer le taux de conversion
public class DialogTauxConversion extends JDialog implements ActionListener {

	private Convertisseur conv;//l'objet convertisseur auquel est associ� ce Dialog
	private JButton btV; // le bouton pour la validation du taux
	private JButton btA;// le bouton pour  annulation
	
	private String titles[] = CurrencyFormatter.monnaieSymb;
	private double values[];
	
	private JPanel getLinePannel(String monney, JTextField value){
	    JPanel pannel = new JPanel();
	    pannel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	    JLabel label = new JLabel("Taux "+monney+" :");
	    label.setSize(500, 20);
	    pannel.add(label);
	    pannel.add(value);
		return pannel;
	}
	
	private void resetFormulaire(){
        for(int i = 0; i< this.getContentPane().getComponentCount()-1 ; i++){
            JPanel pannel = (JPanel) this.getContentPane().getComponent(i);
            JTextField value = (JTextField) pannel.getComponent(1);
            value.setText(Double.toString(values[i]));
        }
	}
	
  //constructeur du dialogue pour fixer le taux.
  public DialogTauxConversion(Convertisseur c) {
    super(c,"TAUX DE CONVERSION",true); // dialog modal centr�e sur le Convertisseur c
    conv = c;
    values = conv.tauxValues;
    Container contentPane = this.getContentPane();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    for (int i = 0; i<titles.length; i++) {
    	JTextField value =  new JTextField(10);
    	value.setText(String.valueOf(values[i]));
    	contentPane.add(getLinePannel(titles[i],value));
    }    
    JPanel pannel = new JPanel();
    pannel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    pannel.add(btV = new JButton("Valider"));
    pannel.add(btA = new JButton("Annuler"));      
   
    this.getContentPane().add(pannel);    
    btV.addActionListener(this);
    btA.addActionListener(this);

    this.setResizable(false);
    this.pack();

  }

  // methode invoqu� lorsque l'on clique sur l'un des boutons
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btV){
      try { 
    	  this.getContentPane().getAccessibleContext();
          for(int i = 0; i< this.getContentPane().getComponentCount()-1 ; i++){
              JPanel pannel = (JPanel) this.getContentPane().getComponent(i);
              JTextField value = (JTextField) pannel.getComponent(1);
              values[i] = Double.parseDouble(value.getText());
          }
          conv.setTauxValues(values);
      }
      catch (NumberFormatException ex) {
	JOptionPane.showMessageDialog(this,"Entrez un nombre !!!",
				      "alert", JOptionPane.ERROR_MESSAGE); 
      }
      finally{
    	  this.setVisible(false);
      }
    }
    else{
    	resetFormulaire();
        this.setVisible(false);
    }
  }
}// DialogTauxConversion