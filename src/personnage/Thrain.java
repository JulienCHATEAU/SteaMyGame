/*
*Cette classe est une classe de Thrain
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

import application.*;
import effet.effettour.*;
import effet.*;


public class Thrain extends Personnage {

/**Constructeur de la classe Thrain qui fait appel au contructeur de la classe Personnage
*@param n qui représente le nom de Thrain
*(les autres paramètres sont prédéfinis)
*/
  public Thrain(String n) {
    super(372, 109, 147, 81, n, "Faux Sinistre", "Peste", "Peine de Mort", "Jour du Jugement Dernier", 2, 3, 4);
    this.type_sort[0] = 2;
    this.type_sort[1] = 1;
    this.type_sort[2] = 2;
    this.type_sort[3] = 1;
  }

/**Méthode qui correspond au sort n°1 de Thrain
*@param ennemi qui représente le Personnage sur lequel le sort est lancé
*/
  public int[] sort1(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*80/100 - cibles[0].getDefense()*30/100;
    if (cibles[0].coupFatal(degatseffectue[0]) && !(cibles[1] instanceof Poutch)) {
      CoGestionSorts.setRejouer(true);
      Combat.ajouterCommentaire("-Thrain rejoue !");
      Combat.ajouterCommentaire("");
    }
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°2 de Thrain
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort2(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 10000;
    for (int i = 0; i<3; i++) {
      cibles[0].appliquerEffet(new DegatContinu(2), 75);
      cibles[1].appliquerEffet(new DegatContinu(2), 75);
    }
    this.setCooldown(1, this.cooldown_max2);
    return degatseffectue;
  }

 /**Méthode qui correspond au sort n°3 de Thrain
 *@param ennemi qui représente le Personnage sur lequel le sort est lancé
 */
  public int[] sort3(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*85/100 + ((cibles[0].getPdvMax()-cibles[0].getPdv())*15/100) - cibles[0].getDefense()*30/100;
    if (!cibles[0].coupFatal(degatseffectue[0])) {
      this.setCooldown(2, this.cooldown_max3);
    }
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°4 de Thrain
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort4(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    int tourBuffAttaque = 0;
    degatseffectue[0] = this.getAttaque()*70/100 - cibles[0].getDefense()*30/100;
    degatseffectue[1] = this.getAttaque()*70/100 - cibles[1].getDefense()*30/100;
    if (cibles[0].nbrDegatsContinus() > 0) {
      degatseffectue[0] += cibles[0].nbrDegatsContinus()*this.getAttaque()*15/100;
      boolean etourdis = cibles[0].appliquerEffet(new Etourdissement(1), 80);
      if (etourdis) {
        tourBuffAttaque++;
      }
    }
    if (cibles[1].nbrDegatsContinus() > 0) {
      degatseffectue[1] += cibles[1].nbrDegatsContinus()*this.getAttaque()*15/100;
      boolean etourdis2 = cibles[1].appliquerEffet(new Etourdissement(1), 80);
      if (etourdis2) {
        tourBuffAttaque++;
      }
    }
    if (tourBuffAttaque > 0) {
      this.appliquerEffet(new BuffAttaque(tourBuffAttaque));
    }
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
    } else if (ennemi.possedeEffet(inv) && this.cooldown[1] == 0) {
      res = 2;
    } else if (ennemi.possedeEffet(inv) && this.cooldown[1] != 0) {
      res = 1;
    } else if (ennemi.possedeEffet(imm) && this.cooldown[2] == 0 && ennemi.getPdv() < ennemi.getPdvMax()/2) {
      res = 3;
    } else if (ennemi.possedeEffet(imm) && (this.cooldown[2] != 0 || ennemi.getPdv() >= ennemi.getPdvMax()/2)) {
      res = 1;
    } else if (ennemi.getPdv() < ennemi.getPdvMax()/2 && this.cooldown[2] == 0) {
      res = 3;
    } else if (this.cooldown[1] == 0) {
      res = 2;
    } else if (this.cooldown[3] == 0 && ennemi.nbrDegatsContinus() > 0) {
      res = 4;
    } else if (this.cooldown[2] == 0) {
      res = 3;
    } else {
      res = 1;
    }

    return res;
  }


  public int[] cibleSortIa(Personnage allieLanceur, Personnage[] ennemis) {
    int[] cibleSort = new int[2];
    Random r = new Random();
    Effet sil = new Silence(0);
    boolean ko = false;
    cibleSort[0] = r.nextInt(2)+1;//choix d'une cible ennemis au hazare

    if (ennemis[cibleSort[0]-1].getPdv() == 0) {//si la cible choisis est KO
      cibleSort[0] = this.changementCible(cibleSort[0]);//change la cible
      ko = true;//la cible initiale est KO donc on a changé
    }


    if (this.possedeEffet(sil)) {//si silence
      cibleSort[1] = 1;//sort 1
    } else if (!(ennemis[0].possedeEffet(new Immunite(0)) && ennemis[1].possedeEffet(new Immunite(0))) && this.cooldown[1] == 0) {
      cibleSort[1] = 2;
    } else if (this.cooldown[3] == 0 && (ennemis[0].possedeEffet(new DegatContinu(0)) || ennemis[1].possedeEffet(new DegatContinu(0)))) {
      cibleSort[1] = 4;
    } else if (this.cooldown[2] == 0 && ennemis[cibleSort[0]-1].getPdv() < ennemis[cibleSort[0]-1].getPdvMax()*35/100) {
      cibleSort[1] = 3;
    } else if (this.cooldown[3] == 0) {
      cibleSort[1] = 4;
    } else if (this.cooldown[2] == 0 && ennemis[cibleSort[0]-1].getPdv() < ennemis[cibleSort[0]-1].getPdvMax()*50/100) {
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

  /**Méthode qui permet de décrire un Thrain
  *@return la description de Thrain
  */
  public String toString() {
    return "Votre personnage est un Thrain et s'appelle "+this.getNom()+", il possède actuellement "+this.getPdv()+" points de vie, "+this.getAttaque()+" d'attaque, "+this.getDefense()+" de défense et "+this.getVitesse()+" de vitesse.";
  }

  /**Méthode qui permet de décrire le sort n°1 de Thrain
  *@return la description du sort n°1 de Thrain
  */
  public String descriptionSort1() {
    return "Moissonne la vie de l'ennemi avec une faux mortelle. Si l'ennemi meurt, vous rejouer instantanément si vous participez à un combat duo (cooldown = "+this.cooldown_max1+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Thrain
  *@return la description du sort n°1 de Thrain
  */
  public String descriptionSort2() {
    return "Transmet la peste aux ennemis pour leur appliquer 3 dégats continus avec une chance de réussite de 75% pour chaque (cooldown = "+this.cooldown_max2+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Thrain
  *@return la description du sort n°1 de Thrain
  */
  public  String descriptionSort3() {
    return "Prédis la mort de l'ennemi avec le vent de l'enfer. Les dégats de cette attaque augmentent lorsque les points de vie de l'ennemi baissent et le cooldown est réstauré si l'ennemi meurt (cooldown = "+this.cooldown_max3+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Thrain
  *@return la description du sort n°1 de Thrain
  */
  public String descriptionSort4() {
    return "Des météorites de l'apocalypse s'abattent sur les ennemis. Si un ennemi possède des dégats continus, il est étourdis avec 80% de chance, les dégats de ce sort augmentent en fonction du nombre de dégats continus de l'ennemi. De plus, votre attaque augmente pour un nombre de tour égal au nombre d'ennemis étourdis par ce sort (cooldown = "+this.cooldown_max4+")";
  }
}
