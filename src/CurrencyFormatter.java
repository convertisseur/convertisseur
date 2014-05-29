import java.text.NumberFormat;
import java.util.Locale;

/**
 * CurrencyFormatter.java<BR>
 * Construit une cha�ne format�e � partir d'un r�el repr�sentant une somme 
 * dans une monnaie donn�e.
 *
 */


public class CurrencyFormatter {
   
  /**
   * Le symbole pour les euros.
   */
  public static final String EURO = "" + (char) (8364);

  /**
   * le symbole pour les Yen
   */
  public static final String JPY = "" + (char) (165);
  
  /**
   * le symbole pour les Dirham
   */
  public static final String MAD = "MAD" ;

  /**
   * le symbole pour les dollars US
   */
  public static final String USD = "$";

  /**
   * le symbole pour les Livres Sterling
   */
  public static final String POUNDS = "£";

  
  static String [] monnaieSymb = {CurrencyFormatter.USD, CurrencyFormatter.JPY , CurrencyFormatter.POUNDS, CurrencyFormatter.MAD};
  
  
  
  /**
   * formatte une valeur r�elle r�pr�sentant une somme dans une monnaie donn�e.
   * Retourne la chaine repr�sentant le nombre r�el arrondi avec un nombre de
   * d�cimales fix� suivant du symbole de la monnaie souhait�e.<BR>
   * Par exemple pour formmatter une somme en $. Si 10.4586 est le montant de
   * la somme, la cha�ne format�e sera : "10.46 $".
   *
   * @param v  la valeur � formatter
   * @param nbdec le nombre de d�cimales souhait�es
   * @param monnaieSymb La cha�ne repr�sentant le symbole de la monnaie
   * @return la cha�ne repr�sentant la valeur formatt�e.
   */
   public static String formatte(double v, int nbdec, String monnaieSymb) {
	NumberFormat fmt = NumberFormat.getInstance();
	fmt.setMaximumFractionDigits(nbdec) ;
        fmt.setMinimumFractionDigits(nbdec) ;
        return fmt.format(v) + " " + monnaieSymb;
   }
  public static String formatte(double v, int nbdec, int item) {
	NumberFormat fmt = NumberFormat.getInstance();
	fmt.setMaximumFractionDigits(nbdec) ;
       fmt.setMinimumFractionDigits(nbdec) ;
       return fmt.format(v) + " " + monnaieSymb[item];
  }
   
} // CurrencyFormatter
