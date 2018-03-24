package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

import effet.*;
import effet.effettour.*;

import effet.effetnocif.*;
import effet.effetnocif.nocifmalus.*;
import effet.effetnocif.nocifmalusprofit.*;
import effet.effetnocif.nocifprofit.*;

import effet.effetbenefique.*;
import effet.effetbenefique.benefiquemalus.*;
import effet.effetbenefique.benefiquemalusprofit.*;
import effet.effetbenefique.benefiqueprofit.*;

import personnage.*;
import application.*;
import runes.*;

public class SuitePassifDeux implements ActionListener {

  private Personnage j1;
  private Personnage j2;
  private Personnage poutch;
  private Combat combat;
  private JButton source;
  private int change;

  public SuitePassifDeux(Personnage j1, Personnage j2, Personnage poutch, Combat combat, JButton source, int change) {
    this.j1 = j1;
    this.j2 = j2;
    this.poutch = poutch;
    this.combat = combat;
    this.source = source;
    this.change = change;
  }

  public void actionPerformed(ActionEvent e) {
    Random r = new Random();
    boolean revive1 = false;
    boolean revive2 = false;
    boolean etourdis = false;
    boolean crit = false;
    boolean marque = false;

    String nom1 = "";
    String nom2 = "";

    int plus = 0;
    int degatseffectue = 0;
    int shield = 0;
    int coupcrit = r.nextInt(100);

    CoGestionSortsSolo.deroulementTour(j2, j1, poutch, change, source, this.combat);
    this.combat.unableAllButonPBA(true);
    this.combat.tirets();
    this.combat.afficherCommentaires();
    this.combat.tourPlusPlus();
    /*
    *	FIN DU TOUR DE J2
    */
  }


  public void appliquerBombe(Personnage p) {
    for (int i = 0; i<p.getListeEffet().size(); i++) {
      if (p.getListeEffet().get(i) instanceof Bombe && p.getListeEffet().get(i).getNbrTour() == 1) {
        Bombe b = (Bombe)p.getListeEffet().get(i);
        b.effetBombe(p);
        p.getListeEffet().remove(p.getListeEffet().get(i));
      }
    }
  }

}
