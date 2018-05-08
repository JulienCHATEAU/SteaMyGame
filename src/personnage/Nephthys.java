/*
*Cette classe est une classe de Nephthys
*
*@author CHATEAU Julien
*@version 1.0
*/

package personnage;
import effet.effetnocif.*;
import java.util.*;
import effet.effetnocif.nocifmalus.*;
import effet.effetnocif.nocifmalusprofit.*;
import effet.effetnocif.nocifprofit.*;

import effet.effetbenefique.*;
import effet.effetbenefique.benefiquemalus.*;
import effet.effetbenefique.benefiquemalusprofit.*;
import effet.effetbenefique.benefiqueprofit.*;

import effet.effettour.*;
import effet.*;
import java.util.*;
import application.*;


public class Nephthys extends Personnage {

/**Constructeur de la classe Nephthys qui fait appel au contructeur de la classe Personnage
*@param n qui représente le nom de Nephthys
*(les autres paramètres sont prédéfinis)
*/
  public Nephthys(String n) {
    super(380, 111, 134, 83, n, "Toucher de Séduction", "Malédiction de la Beauté", "Bénédiction de l'Oasis", "Charme Mortel", 3, 4, 0, true, false, false);
    this.type_sort[0] = 2;
    this.type_sort[1] = 1;
    this.type_sort[2] = 3;
    this.type_sort[3] = 0;
  }

/**Méthode qui correspond au sort n°1 de Nephthys
*@param ennemi qui représente le Personnage sur lequel le sort est lancé
*/
  public int[] sort1(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*70/100 - cibles[0].getDefense()*30/100;
    cibles[0].appliquerEffet(new BreakAttaque(2), 50+this.sort4(cibles, ennemisCibles)[0]);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°2 de Nephthys
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort2(Personnage[] cibles, Personnage[] ennemisCibles) {
    Random r = new Random();
    int bd = 3;
    int ah = 3;
    int bd2 = 3;
    int ah2 = 3;
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 0;
    degatseffectue[1] = 0;
    for (int i = 0; i<3; i++) {
      degatseffectue[0] += this.getAttaque()*20/100 + this.getPdvMax()*2/100 - cibles[0].getDefense()/10;
      degatseffectue[1] += this.getAttaque()*20/100 + this.getPdvMax()*2/100 - cibles[1].getDefense()/10;
      if (r.nextInt(100) < 30+this.sort4(cibles, ennemisCibles)[0]) {
        cibles[0].appliquerEffet(new BreakDefense(2));
        bd--;
      }
      if (r.nextInt(100) < 30+this.sort4(cibles, ennemisCibles)[0]) {
        cibles[0].appliquerEffet(new AntiHeal(2));
        ah--;
      }
      if (r.nextInt(100) < 30+this.sort4(cibles, ennemisCibles)[0]) {
        cibles[1].appliquerEffet(new BreakDefense(2));
        bd2--;
      }
      if (r.nextInt(100) < 30+this.sort4(cibles, ennemisCibles)[0]) {
        cibles[1].appliquerEffet(new AntiHeal(2));
        ah2--;
      }
    }
    if (bd == 3) {
      Combat.ajouterCommentaire("-"+cibles[0].getClass().getName().substring(11)+" a résisté aux 3 break def !");
    }
    if (ah == 3) {
      Combat.ajouterCommentaire("-"+cibles[0].getClass().getName().substring(11)+" a résisté aux 3 anti-heal !");
    }
    if (bd2 == 3) {
      Combat.ajouterCommentaire("-"+cibles[1].getClass().getName().substring(11)+" a résisté aux 3 break def !");
    }
    if (ah2 == 3) {
      Combat.ajouterCommentaire("-"+cibles[1].getClass().getName().substring(11)+" a résisté aux 3 anti-heal !");
    }
    this.setCooldown(1, this.cooldown_max2);
    return degatseffectue;
  }

 /**Méthode qui correspond au sort n°3 de Nephthys
 *@param ennemi qui représente le Personnage sur lequel le sort est lancé
 */
  public int[] sort3(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 10000;
    cibles[0].appliquerEffet(new BuffAttaque(3));
    cibles[1].appliquerEffet(new BuffAttaque(3));
    cibles[0].setShield(this.getPdvMax()*20/100);
    cibles[1].setShield(this.getPdvMax()*20/100);
    this.setCooldown(2, this.cooldown_max3);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°4 de Nephthys
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort4(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getPdvMax()/20;
    return degatseffectue;
  }

  public int iaChoixSort(Personnage ennemi) {
    int res = 0;
    Effet ah = new AntiHeal(0);
    Effet inv = new Invincibilite(0);
    Effet imm = new Immunite(0);
    Effet sil = new Silence(0);

    if (this.possedeEffet(sil)) {
      res = 1;
    } else if (this.cooldown[2] == 0) {
      res = 3;
    } else if (ennemi.possedeEffet(imm) || ennemi.possedeEffet(inv)) {
      res = 1;
    } else if (this.cooldown[1] == 0) {
      res = 2;
    } else {
      res = 1;
    }

    return res;
  }


  public int[] cibleSortIa(Personnage allieLanceur, Personnage[] ennemis) {
    int[] cibleSort = new int[2];
    boolean ko = false;
    Effet sil = new Silence(0);
    Random r = new Random();
    cibleSort[0] = r.nextInt(2)+1;//choix d'une cible ennemis au hazare

    if (ennemis[cibleSort[0]-1].getPdv() == 0) {//si la cible choisis est KO
      cibleSort[0] = this.changementCible(cibleSort[0]);//change la cible
      ko = true;//la cible initiale est KO donc on a changé
    }

    if (this.possedeEffet(sil)) {//si silence
      cibleSort[1] = 1;//sort 1
    } else if (this.cooldown[2] == 0) {
      cibleSort[0] = 3;
      cibleSort[1] = 3;//sort 3
    } else if (this.cooldown[1] == 0 && !(ennemis[0].possedeEffet(new Immunite(0)) && ennemis[1].possedeEffet(new Immunite(0)))) {
      cibleSort[1] = 2;//sort 2
    } else {
      cibleSort[1] = 1;//sort 1
    }


    /*** Optimisation du choix de la cible dans le cas d'une attaque à cible unique sur un ennemi si l'allié de la cible n'est pas KO ***/
    if (!ko) {
      if (cibleSort[1] == 1) {
        cibleSort[0] = this.choixIaPreferenceEffets(cibleSort[0], ennemis);
      }
    }

    return cibleSort;
  }

  /**Méthode qui permet de décrire un Nephthys
  *@return la description de Nephthys
  */
  public String toString() {
    return "Votre personnage est un Nephthys et s'appelle "+this.getNom()+", il possède actuellement "+this.getPdv()+" points de vie, "+this.getAttaque()+" d'attaque, "+this.getDefense()+" de défense et "+this.getVitesse()+" de vitesse.";
  }

  /**Méthode qui permet de décrire le sort n°1 de Nephthys
  *@return la description du sort n°1 de Nephthys
  */
  public String descriptionSort1() {
    return "Attaque l'ennemi et réduis son attaque pendant 2 tours avec 50% de chance (cooldown = "+this.cooldown_max1+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Nephthys
  *@return la description du sort n°1 de Nephthys
  */
  public String descriptionSort2() {
    return "Une malédiction s'abat sur les ennemis leur infligeant des dégats 3 fois. A chaque coup diminue leur défense et perturbe leur régénération pendant 2 tours avec 50% de chance (cooldown = "+this.cooldown_max2+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Nephthys
  *@return la description du sort n°1 de Nephthys
  */
  public  String descriptionSort3() {
    return "Augmente l'attaque des alliés pendant 3 tours et leur procure un bouclier d'une valeur dépendant de tes points de vie maximums (cooldown = "+this.cooldown_max3+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Nephthys
  *@return la description du sort n°1 de Nephthys
  */
  public String descriptionSort4() {
    return "Vous êtes immunisé contre les étourdissements et la précision d'application des effets sur les ennemis est augmentée en fonction de vos points de vie maximums";
  }
}
