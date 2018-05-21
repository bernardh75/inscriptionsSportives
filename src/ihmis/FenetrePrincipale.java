
package ihmis;

//import java.awt.Color;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//public class javaswingis extends JFrame {
//	  /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	public javaswingis(){
//	    this.setTitle("InscriptionsSportives M2L");
//	    //dimensions
//	    this.setSize(400, 500);
//	    //au centre
//	    this.setLocationRelativeTo(null);
//	    //boutonrougeclose
//	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//	    //premier plan tj
//	    this.setAlwaysOnTop(true);
//	    //redimensionnable
//	    this.setResizable(true);
//	    //Instanciation d'un objet JPanel
//	    JPanel pan = new JPanel();
//	    //Définition de sa couleur de fond
//	    pan.setBackground(Color.CYAN);        
//	    //On prévient notre JFrame que notre JPanel sera son content pane
//	    this.setContentPane(pan);
//	    //visibilité de la fenêtre on
//	    this.setVisible(true);
//	    
//	  }

import java.awt.BorderLayout;
import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hibernateis.passerelle;
 
public class FenetrePrincipale extends JFrame {
  public FenetrePrincipale(){             
    this.setTitle("Inscriptions Sportives M2L");
    this.setSize(700, 700);
    this.setLocationRelativeTo(null);     
    
    JPanel background = new JPanel();
    
	passerelle p = new passerelle();
	p.open();
    
    ispanel pan = new ispanel();
    background.add(pan, BorderLayout.CENTER);

    this.setContentPane(background); 
    this.setVisible(true);
  }  
  
  public static void main(String[] args){
	  FenetrePrincipale fen = new FenetrePrincipale();
  }       
}
