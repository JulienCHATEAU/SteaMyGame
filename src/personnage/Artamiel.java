/*
*Cette classe est une classe de Artamiel
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


public class Artamiel extends Personnage {

/**Constructeur de la classe Artamiel qui fait appel au contructeur de la classe Personnage
*@param n qui représente le nom de Artamiel
*(les autres paramètres sont prédéfinis)
*/
  public Artamiel(String n) {
    super(360, 116, 139, 80, n, "Epée Sainte", "Miséricorde de l'Archange", "Eau Bénite", "Sanctuaire", 4, 4, 5);
    this.type_sort[0] = 2;
    this.type_sort[1] = 4;
    this.type_sort[2] = 4;
    this.type_sort[3] = 3;
  }

/**Méthode qui correspond au sort n°1 de Artamiel
*@param ennemi qui représente le Personnage sur lequel le sort est lancé
*/
  public int[] sort1(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*65/100 + this.getDefense()*10/100 - cibles[0].getDefense()*30/100;
    EffetBenefique.clean(cibles[0], 100);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°2 de Artamiel
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort2(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    if (cibles[0] == this) {
      this.appliquerEffet(new BuffDefense(2));
      degatseffectue[0] = this.getAttaque()*60/100 + this.getDefense()*15/100 - ennemisCibles[0].getDefense()*30/100;
      degatseffectue[1] = this.getAttaque()*60/100 + this.getDefense()*15/100 - ennemisCibles[1].getDefense()*30/100;
    } else {
      cibles[0].resetCooldown();
    }
    this.setCooldown(1, this.cooldown_max2);
    return degatseffectue;
  }

 /**Méthode qui correspond au sort n°3 de Artamiel
 *@param ennemi qui représente le Personnage sur lequel le sort est lancé
 */
  public int[] sort3(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 10000;
    cibles[0].heal(this.getPdvMax()*50/100);
    this.setCooldown(2, this.cooldown_max3);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°4 de Artamiel
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort4(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 10000;
    cibles[0].cleanToutLesEffetNocif();
    cibles[1].cleanToutLesEffetNocif();
    cibles[0].appliquerEffet(new Immunite(3));
    cibles[1].appliquerEffet(new Immunite(3));
    cibles[0].appliquerEffet(new BuffVitesse(1));
    cibles[1].appliquerEffet(new BuffVitesse(1));
    this.setCooldown(3, this.cooldown_max4);
    return degatseffectue;
  }

  public int iaChoixSort(Personnage ennemi) {
    int res = 0;
    Effet ah = new AntiHeal(0);
    Effet sil = new Silence(0);

    if (this.possedeEffet(sil)) {
      res = 1;
    } else if (this.getPdv() < (this.getPdvMax() * 35 / 100) && this.cooldown[2] == 0 && !this.possedeEffet(ah)) {
      res = 3;
    } else if (this.nbrEffetsNocifs() >= 1 && this.cooldown[3] == 0) {
      res = 4;
    } else if (this.cooldown[1] == 0) {
      res = 2;
    } else if (this.cooldown[3] == 0) {
      res = 4;
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
    } else if (allieLanceur.getPdv() < allieLanceur.getPdvMax()*35/100 && !allieLanceur.possedeEffet(new AntiHeal(0)) && this.cooldown[3] == 0) {//si possède plus de 2 effets nocifs
      cibleSort[0] = 4;
      cibleSort[1] = 3;//sort 3
    } else if (allieLanceur.nbrEffetsNocifs() >= 1 && allieLanceur.getPdv() != 0 && this.cooldown[3] == 0) {//si allié possède plus de 1 effets nocifs
      cibleSort[0] = 4;
      cibleSort[1] = 4;//sort 4
    } else if (this.getPdv() < this.getPdvMax()*30/100 && !this.possedeEffet(new AntiHeal(0)) && this.cooldown[2] == 0) {//si moins de 30% des Hp max et pas anti-heal
      cibleSort[0] = 3;
      cibleSort[1] = 3;//sort 3
    } else if (this.nbrEffetsNocifs() >= 1 && this.cooldown[3] == 0) {//si allié possède plus de 1 effets nocifs
      cibleSort[0] = 3;
      cibleSort[1] = 4;//sort 4
    } else if (this.cooldown[1] == 0) {//sinon
      cibleSort[0] = r.nextInt(2)+3;//soit moi soit mon allié
      cibleSort[1] = 2;//sort 2
    } else if (this.cooldown[3] == 0) {//sinon
      cibleSort[0] = 4;
      cibleSort[1] = 4;//sort 4
    } else {//si vraiment
      cibleSort[1] = 1;//sort 1
    }


    /*** Optimisation du choix de la cible dans le cas d'une attaque à cible unique sur un ennemi si l'allié de la cible n'est pas KO ***/
    if (!ko) {
      if (cibleSort[1] == 1) {
        cibleSort[0] = this.choixIaPreferenceDegats(cibleSort[0], ennemis);
      }
    }

    return cibleSort;
  }

  /**Méthode qui permet de décrire un Artamiel
  *@return la description de Artamiel
  */
  public String toString() {
    return "Votre personnage est un Artamiel et s'appelle "+this.getNom()+", il possède actuellement "+this.getPdv()+" points de vie, "+this.getAttaque()+" d'attaque, "+this.getDefense()+" de défense et "+this.getVitesse()+" de vitesse.";
  }

  /**Méthode qui permet de décrire le sort n°1 de Artamiel
  *@return la description du sort n°1 de Artamiel
  */
  public String descriptionSort1() {
    return "Attaque l'ennemi avec une épée venu des cieux qui lui retire un effet bénéfique (cooldown = "+this.cooldown_max1+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Artamiel
  *@return la description du sort n°1 de Artamiel
  */
  public String descriptionSort2() {
    return "Si lancé sur un allié, réduit le cooldown de ses sorts à zéro. Si lancé sur vous même, augmente votre défense pendant 2 tours puis inflige des dégats aux ennemis. (cooldown = "+this.cooldown_max2+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Artamiel
  *@return la description du sort n°1 de Artamiel
  */
  public  String descriptionSort3() {
    return "Une eau bénite des dieux soigne l'allié ciblé d'un pourcentage de ses points de vie maximums (cooldown = "+this.cooldown_max3+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Artamiel
  *@return la description du sort n°1 de Artamiel
  */
  public String descriptionSort4() {
    return "Supprime les effets nocifs des alliés, leur accorde l'immunité pendant 3 tours et augmente leur vitesse pendant 1 tour (cooldown = "+this.cooldown_max4+")";
  }
}
