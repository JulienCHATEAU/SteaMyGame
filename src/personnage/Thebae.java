/*
*Cette classe est une classe de Thebae
*
*@author CHATEAU Julien
*@version 1.0
*/

package personnage;
import effet.effetnocif.*;
import effet.effetnocif.nocifmalus.*;
import effet.effetnocif.nocifmalusprofit.*;
import java.util.*;
import effet.effetnocif.nocifprofit.*;

import effet.effetbenefique.*;
import effet.effetbenefique.benefiquemalus.*;
import effet.effetbenefique.benefiquemalusprofit.*;
import effet.effetbenefique.benefiqueprofit.*;

import effet.effettour.*;
import effet.*;
import application.Combat;
import java.util.*;


public class Thebae extends Personnage {

/**Constructeur de la classe Thebae qui fait appel au contructeur de la classe Personnage
*@param n qui représente le nom de Thebae
*(les autres paramètres sont prédéfinis)
*/
  public Thebae(String n) {
    super(410, 116, 117, 83, n, "Percer l'Âme", "Marque de Royauté", "Colère Mortelle", "Retour du Roi", 3, 3, 10, true, false, true);
    this.type_sort[0] = 2;
    this.type_sort[1] = 1;
    this.type_sort[2] = 2;
    this.type_sort[3] = 0;
  }

/**Méthode qui correspond au sort n°1 de Thebae
*@param ennemi qui représente le Personnage sur lequel le sort est lancé
*/
  public int[] sort1(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*75/100 - cibles[0].getDefense()*30/100;
    cibles[0].appliquerEffet(new AntiHeal(2), 30);
    cibles[0].appliquerEffet(new AntiHeal(2), 30);
    cibles[0].appliquerEffet(new Etourdissement(1), 15);
    cibles[0].appliquerEffet(new Etourdissement(1), 15);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°2 de Thebae
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort2(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*40/100 + this.getPdv()*20/100 + (cibles[0].getPdvMax()-cibles[0].getPdv())*8/100 - cibles[0].getDefense()*30/100;
    degatseffectue[1] = this.getAttaque()*40/100 + this.getPdv()*20/100 + (cibles[1].getPdvMax()-cibles[1].getPdv())*8/100 - cibles[1].getDefense()*30/100;
    boolean resistanceCible0 = true;
    boolean resistanceCible1 = true;
    Random r = new Random();
    for (int i = 0; i<3; i++) {
      if (r.nextInt(100) < 35) {
        cibles[0].appliquerEffet(new Marque(2));
        resistanceCible0 = false;
      }
      if (r.nextInt(100) < 35) {
        cibles[1].appliquerEffet(new Marque(2));
        resistanceCible1 = false;
      }
    }
    if (resistanceCible0) {
      Combat.ajouterCommentaire("-"+cibles[0].getClass().getName().substring(11)+" a résisté aux 3 effets nocifs");
    }
    if (resistanceCible1) {
      Combat.ajouterCommentaire("-"+cibles[1].getClass().getName().substring(11)+" a résisté aux 3 effets nocifs");
    }
    this.setCooldown(1, this.cooldown_max2);
    return degatseffectue;
  }

 /**Méthode qui correspond au sort n°3 de Thebae
 *@param ennemi qui représente le Personnage sur lequel le sort est lancé
 */
  public int[] sort3(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*50/100 + this.getPdvMax()*15/100 - cibles[0].getDefense()*30/100;
    if (this.getPdv() <= this.getPdvMax()*30/100) {
      this.volDeVie(degatseffectue[0], cibles[0], 50);
    }
    this.setCooldown(2, this.cooldown_max3);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°4 de Thebae
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort4(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 10000;
    this.setPdv(this.getPdvMax()*30/100);
    this.setAttaque(this.getAttaque()+this.getAttaqueMax()*50/100);
    this.setCooldown(3, this.cooldown_max4);
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
    } else if (ennemi.possedeEffet(inv) && ennemi.possedeEffet(imm)) {
      res = 1;
    } else if (ennemi.possedeEffet(inv)) {
      res = 1;
    } else if (ennemi.possedeEffet(imm)) {
      if (this.cooldown[2] == 0) {
        res = 3;
      } else {
        res = 1;
      }
    } else if (this.cooldown[2] == 0 && this.cooldown[3] == 0 && this.getPdv() > this.getPdvMax()*75/100) {
      res = 3;
    } else if (this.cooldown[2] == 0 && !this.possedeEffet(ah) && this.getPdv() <= this.getPdvMax()*30/100) {
      res = 3;
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
    } else if (!(ennemis[0].possedeEffet(new Immunite(0)) && ennemis[1].possedeEffet(new Immunite(0))) && this.cooldown[1] == 0) {
      cibleSort[1] = 2;
    } else if (this.cooldown[2] == 0 && (this.getPdv() > this.getPdvMax()*60/100 || this.getPdv() < this.getPdvMax()*30/100)) {
      cibleSort[1] = 3;
    } else {
      cibleSort[1] = 1;
    }


    /*** Optimisation du choix de la cible dans le cas d'une attaque à cible unique sur un ennemi si l'allié de la cible n'est pas KO ***/
    if (!ko) {
      if (cibleSort[1] == 1) {
        cibleSort[0] = this.choixIaPreferenceEffets(cibleSort[0], ennemis);
      } else if (cibleSort[1] == 3) {
        cibleSort[0] = this.choixIaPreferenceDegats(cibleSort[0], ennemis);
      }
    }

    return cibleSort;
  }

  /**Méthode qui permet de décrire un Thebae
  *@return la description de Thebae
  */
  public String toString() {
    return "Votre personnage est un Thebae et s'appelle "+this.getNom()+", il possède actuellement "+this.getPdv()+" points de vie, "+this.getAttaque()+" d'attaque, "+this.getDefense()+" de défense et "+this.getVitesse()+" de vitesse.";
  }

  /**Méthode qui permet de décrire le sort n°1 de Thebae
  *@return la description du sort n°1 de Thebae
  */
  public String descriptionSort1() {
    return "Attaque l'ennemi 2 fois et l'empêche de ce soigner pendant 2 tours avec 30% de chance et l'étourdis avec 15% de chance pour chaque attaque (cooldown = "+this.cooldown_max1+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Thebae
  *@return la description du sort n°1 de Thebae
  */
  public String descriptionSort2() {
    return "Impose ta royauté en frappant 3 fois chaque ennemi avec pour chaque attaque 35% de chance de leur appliquer un effet de marque. Les dégats augmente à mesure que les points de vie de l'ennemi baissent (cooldown = "+this.cooldown_max2+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Thebae
  *@return la description du sort n°1 de Thebae
  */
  public  String descriptionSort3() {
    return "Vous rentrez dans une colère noire et infliger de lourd dégat à l'ennemi dont une partie des dégats dépend de vos points de vie maximums. De plus si vos points de vie sont inférieur à 30% de vos points de vie maximums, se sort vous soigne de 50% des dégats infligés (cooldown = "+this.cooldown_max3+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Thebae
  *@return la description du sort n°1 de Thebae
  */
  public String descriptionSort4() {
    return "Si vous mourrez, vous réanime avec 30% de vos points de vie maximums et augmente votre attaque jusqu'à la fin du combat. Boost cumulable (cooldown = "+this.cooldown_max4+")";
  }
}
