package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color; 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import hibernateis.passerelle;
 
public class FenetrePrincipale extends JFrame {
  public FenetrePrincipale(){             
    this.setTitle("Inscriptions Sportives M2L");
    this.setSize(900, 900);
    this.setLocationRelativeTo(null);     
    
    JPanel background = new JPanel();
    
	passerelle p = new passerelle();
	p.open();
    
    PanelMenus pan = new PanelMenus();
    background.add(pan, BorderLayout.CENTER);

    this.setContentPane(background); 
    this.setVisible(true);
  }  
  
  public static void main(String[] args){
	  FenetrePrincipale fen = new FenetrePrincipale();
  }       
}