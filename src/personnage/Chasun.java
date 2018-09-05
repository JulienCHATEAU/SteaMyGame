/*
*Cette classe est une classe de Chasun
*
*@author CHATEAU Julien
*@version 1.0
*/

package personnage;
import effet.effetnocif.*;
import effet.effetnocif.nocifmalus.*;
import effet.effetnocif.nocifmalusprofit.*;
import effet.effetnocif.nocifprofit.*;

import effet.effetbenefique.*;
import effet.effetbenefique.benefiquemalus.*;
import effet.effetbenefique.benefiquemalusprofit.*;
import effet.effetbenefique.benefiqueprofit.*;

import application.*;
import java.util.*;
import effet.effettour.*;
import effet.*;

import java.util.*;


public class Chasun extends Personnage {

/**Constructeur de la classe Chasun qui fait appel au contructeur de la classe Personnage
*@param n qui représente le nom de Chasun
*(les autres paramètres sont prédéfinis)
*/
  public Chasun(String n) {
    super(393, 116, 120, 76, n, "Toucher gracieux", "Tempête Florale", "Danse Soignante", "Défense Absolue", 3, 4, 5);
    this.type_sort[0] = 2;
    this.type_sort[1] = 1;
    this.type_sort[2] = 4;
    this.type_sort[3] = 6;
  }

/**Méthode qui correspond au sort n°1 de Chasun
*@param ennemi qui représente le Personnage sur lequel le sort est lancé
*/
  public int[] sort1(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*75/100 - cibles[0].getDefense()*30/100;
    cibles[0].appliquerEffet(new BreakAttaque(2), 66);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°2 de Chasun
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort2(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*80/100 - cibles[0].getDefense()*30/100;
    degatseffectue[1] = this.getAttaque()*80/100 - cibles[1].getDefense()*30/100;
    ennemisCibles[0].heal(ennemisCibles[0].getPdvMax()*15/100);
    ennemisCibles[1].heal(ennemisCibles[1].getPdvMax()*15/100);
    this.setCooldown(1, this.cooldown_max2);
    return degatseffectue;
  }

 /**Méthode qui correspond au sort n°3 de Chasun
 *@param ennemi qui représente le Personnage sur lequel le sort est lancé
 */
  public int[] sort3(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 10000;
    cibles[0].heal(this.getPdvMax()*30/100);
    cibles[1].heal(this.getPdvMax()*30/100);
    cibles[0].appliquerEffet(new BuffAttaque(2));
    cibles[1].appliquerEffet(new BuffAttaque(2));
    this.setCooldown(2, this.cooldown_max3);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°4 de Chasun
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort4(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 10000;
    cibles[0].cleanToutLesEffetNocif();
    cibles[0].appliquerEffet(new Invincibilite(2));
    this.setCooldown(3, this.cooldown_max4);
    return degatseffectue;
  }

  public int iaChoixSort(Personnage ennemi) {
    int res = 0;
    Effet ah = new AntiHeal(0);
    Effet sil = new Silence(0);

    if (this.possedeEffet(sil)) {
      res = 1;
    } else if (this.getPdv() < (this.getPdvMax() * 50 / 100) && this.cooldown[2] == 0 && !this.possedeEffet(ah)) {
      res = 3;
    } else if (this.cooldown[3] == 0) {
      res = 4;
    } else if (this.cooldown[1] == 0) {
      res = 2;
    } else {
      res = 1;
    }
    return res;
  }


  public int[] cibleSortIa(Personnage allieLanceur, Personnage[] ennemis) {
    int[] cibleSort = new int[2];
    Effet sil = new Silence(0);
    Random r = new Random();
    boolean ko = false;
    cibleSort[0] = r.nextInt(2)+1;//choix d'une cible ennemis au hazare

    if (ennemis[cibleSort[0]-1].getPdv() == 0) {//si la cible choisis est KO
      cibleSort[0] = this.changementCible(cibleSort[0]);//change la cible
      ko = true;//la cible initiale est KO donc on a changé
    }


    if (this.possedeEffet(sil)) {//si silence
      cibleSort[1] = 1;//sort 1
    } else if (allieLanceur.nbrEffetsNocifs() >= 1 && allieLanceur.getPdv() != 0 && this.cooldown[3] == 0) {//si allié possède plus de 1 effets nocifs
      cibleSort[0] = 3;
      cibleSort[1] = 4;//sort 4
    } else if (allieLanceur.getPdv() < allieLanceur.getPdvMax()*65/100 && this.cooldown[3] == 0) {
      cibleSort[0] = 3;
      cibleSort[1] = 4;//sort 4
    } else if (allieLanceur.getPdv() < allieLanceur.getPdvMax()*50/100 && !this.possedeEffet(new AntiHeal(0)) && allieLanceur.getPdv() != 0 && !allieLanceur.possedeEffet(new AntiHeal(0)) && this.cooldown[2] == 0) {//si allié a moins de 50% de ses Hp max et pas KO et pas anti-heal
      cibleSort[0] = 3;
      cibleSort[1] = 3;//sort 3
    } else if (this.getPdv() < this.getPdvMax()*50/100 && !this.possedeEffet(new AntiHeal(0)) && this.cooldown[2] == 0) {//si moins de 50% des Hp max et pas anti-heal
      cibleSort[0] = 3;
      cibleSort[1] = 3;//sort 3
    } else if (this.cooldown[1] == 0) {//sinon
      cibleSort[1] = 2;//sort 2
    } else if (this.cooldown[3] == 0 && allieLanceur.getPdv() != 0) {//sinon
      cibleSort[0] = 3;
      cibleSort[1] = 4;//sort 4
    } else if (this.cooldown[2] == 0) {//sinon
      cibleSort[0] = 3;
      cibleSort[1] = 3;//sort 3
    } else if (this.cooldown[3] == 0) {//sinon
      cibleSort[0] = 4;
      cibleSort[1] = 4;//sort 4
    } else {//si vraiment
      cibleSort[1] = 1;//sort 1
    }


    /*** Optimisation du choix de la cible dans le cas d'une attaque à cible unique sur un ennemi si l'allié de la cible n'est pas KO ***/
    if (!ko) {
      if (cibleSort[1] == 1) {
        cibleSort[0] = this.choixIaPreferenceEffets(cibleSort[0], ennemis);
      } else if (cibleSort[1] == 2) {
        cibleSort[0] = this.choixIaPreferenceDegats(cibleSort[0], ennemis);
      }
    }

    return cibleSort;
  }

  /**Méthode qui permet de décrire un Chasun
  *@return la description de Chasun
  */
  public String toString() {
    return "Votre personnage est un Chasun et s'appelle "+this.getNom()+", il possède actuellement "+this.getPdv()+" points de vie, "+this.getAttaque()+" d'attaque, "+this.getDefense()+" de défense et "+this.getVitesse()+" de vitesse.";
  }

  /**Méthode qui permet de décrire le sort n°1 de Chasun
  *@return la description du sort n°1 de Chasun
  */
  public String descriptionSort1() {
    return "Attaque l'ennemi avec un toucher gracieux pour lui réduire son attaque pendant 2 tours avec 75% de chance (cooldown = "+this.cooldown_max1+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Chasun
  *@return la description du sort n°1 de Chasun
  */
  public String descriptionSort2() {
    return "Une tempête de fleurs s'abat sur les ennemis pour leurs infliger des dégats et revient vers vous pour soigner les alliés de 15% de leur points de vie maximums (cooldown = "+this.cooldown_max2+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Chasun
  *@return la description du sort n°1 de Chasun
  */
  public  String descriptionSort3() {
    return "Effectue une danse qui soigne les alliés d'un pourcentage de tes points de vie maximums et augmente leur attaque pendant 2 tours (cooldown = "+this.cooldown_max3+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Chasun
  *@return la description du sort n°1 de Chasun
  */
  public String descriptionSort4() {
    return "Défend votre allié en lui retirant tous ses effets nocifs et en le rendant invincible pendant 2 tours. Cet effet s'active automatiquement sur vous lors d'un combat solo (cooldown = "+this.cooldown_max4+")";
  }
}
