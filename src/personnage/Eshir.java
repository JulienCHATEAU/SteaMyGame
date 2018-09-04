/*
*Cette classe est une classe de Eshir
*
*@author CHATEAU Julien
*@version 1.0
*/

package personnage;
import java.util.*;
import effet.effetnocif.*;
import effet.effetnocif.nocifmalus.*;
import effet.effetnocif.nocifmalusprofit.*;
import effet.effetnocif.nocifprofit.*;

import java.util.*;
import effet.effetbenefique.*;
import effet.effetbenefique.benefiquemalus.*;
import effet.effetbenefique.benefiquemalusprofit.*;
import effet.effetbenefique.benefiqueprofit.*;
import application.*;

import effet.effettour.*;
import effet.*;


public class Eshir extends Personnage {

  /**Constructeur de la classe Eshir qui fait appel au contructeur de la classe Personnage
  *@param n qui représente le nom de Eshir
  *(les autres paramètres sont prédéfinis)
  */
  public Eshir(String n) {
    super(403, 108, 117, 79, n, "Griffes Impitoyables", "Cri du Prédateur", "Massacre", "Dédain", 3, 4, 0, true, false, false);
    this.type_sort[0] = 2;
    this.type_sort[1] = 3;
    this.type_sort[2] = 2;
    this.type_sort[3] = 0;
  }

  /**Méthode qui correspond au sort n°1 de Eshir
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort1(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*75/100 + this.sort4(cibles, ennemisCibles)[0] - cibles[0].getDefense()*30/100;
    cibles[0].appliquerEffet(new AntiHeal(2), 75);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°2 de Eshir
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort2(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 10000;
    cibles[0].heal(cibles[0].getPdvMax()*30/100);
    cibles[1].heal(cibles[1].getPdvMax()*30/100);
    cibles[0].appliquerEffet(new BuffVitesse(2));
    cibles[1].appliquerEffet(new BuffVitesse(2));
    this.setCooldown(1, this.cooldown_max2);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°3 de Eshir
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort3(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*85/100 + this.getPdvMax()*12/100 + this.sort4(cibles, ennemisCibles)[0] - cibles[0].getDefense()*30/100;
    for (int i = 0; i<4; i++) {
      EffetBenefique.clean(cibles[0], 50);
    }
    if (!cibles[0].coupFatal(degatseffectue[0])) {
      this.setCooldown(2, this.cooldown_max3);
    }
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°4 de Eshir
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort4(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 0;
    if (this.getPdv() > cibles[0].getPdv()) {
      degatseffectue[0] = (this.getPdv()-cibles[0].getPdv())/5;
    }
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
    } else if (((ennemi.possedeEffet(inv) && ennemi.possedeEffet(imm)) || ennemi.possedeEffet(imm) || this.possedeEffet(inv)) && this.cooldown[2] == 0) {
      res = 3;
    } else if (((ennemi.possedeEffet(inv) && ennemi.possedeEffet(imm)) || ennemi.possedeEffet(imm) || this.possedeEffet(inv)) && this.cooldown[1] == 0 && !this.possedeEffet(ah)) {
      res = 2;
    } else if (((ennemi.possedeEffet(inv) && ennemi.possedeEffet(imm)) || ennemi.possedeEffet(imm) || this.possedeEffet(inv)) && (this.cooldown[1] != 0 || !this.possedeEffet(ah) || this.cooldown[2] == 0)) {
      res = 1;
    } else if (this.cooldown[2] == 0 && ennemi.nbrEffetsBenefiques() > 0) {
      res = 3;
    } else if (this.cooldown[1] == 0 && !this.possedeEffet(ah) && this.getPdv() < this.getPdvMax()*60/100) {
      res = 2;
    } else if (this.cooldown[2] == 0) {
      res = 3;
    } else {
      res = 1;
    }

    return res;
  }


  public int[] cibleSortIa(Personnage allieLanceur, Personnage[] ennemis) {
    int[] cibleSort = new int[2];
    Effet sil = new Silence(0);
    Random r = new Random();
    cibleSort[0] = r.nextInt(2)+1;//choix d'une cible ennemis au hazare
    boolean ko = false;

    if (ennemis[cibleSort[0]-1].getPdv() == 0) {//si la cible choisis est KO
      cibleSort[0] = this.changementCible(cibleSort[0]);//change la cible
      ko = true;//la cible initiale est KO donc on a changé
    }


    if (this.possedeEffet(sil)) {//si silence
      cibleSort[1] = 1;//sort 1
    } else if (this.getPdv() < this.getPdvMax()*60/100 && !this.possedeEffet(new AntiHeal(0)) && this.cooldown[1] == 0) {//si moins 60% des hp max et pas d'anti-heal
      cibleSort[0] = 3;
      cibleSort[1] = 2;
    } else if (allieLanceur.getPdv() < allieLanceur.getPdvMax()*60/100 && allieLanceur.getPdv() != 0 && !allieLanceur.possedeEffet(new AntiHeal(0)) && this.cooldown[1] == 0) {
      cibleSort[0] = 3;
      cibleSort[1] = 2;
    } else if (ennemis[cibleSort[0]-1].nbrEffetsBenefiques() >= 2 && this.cooldown[2] == 0) {
      cibleSort[1] = 3;
    } else if (!ko && ennemis[this.changementCible(cibleSort[0])-1].nbrEffetsBenefiques() >= 2 && this.cooldown[2] == 0) {
      cibleSort[0] = this.changementCible(cibleSort[0]);
      cibleSort[1] = 3;
    } else if (this.cooldown[2] == 0) {
      cibleSort[1] = 3;
    } else {
      cibleSort[1] = 1;
    }


    /*** Optimisation du choix de la cible dans le cas d'une attaque à cible unique sur un ennemi si l'allié de la cible n'est pas KO ***/
    if (!ko) {
      if (cibleSort[1] == 1) {
        cibleSort[0] = this.choixIaPreferenceDegats(cibleSort[0], ennemis);
      } else if (cibleSort[1] == 3) {
        cibleSort[0] = this.choixIaPreferenceDegats(cibleSort[0], ennemis);
      }
    }

    return cibleSort;
  }

  /**Méthode qui permet de décrire un Eshir
  *@return la description de Eshir
  */
  public String toString() {
    return "Votre personnage est un Eshir et s'appelle "+this.getNom()+", il possède actuellement "+this.getPdv()+" points de vie, "+this.getAttaque()+" d'attaque, "+this.getDefense()+" de défense et "+this.getVitesse()+" de vitesse.";
  }

  /**Méthode qui permet de décrire le sort n°1 de Eshir
  *@return la description du sort n°1 de Eshir
  */
  public String descriptionSort1() {
    return "Attaque l'ennemi avec des griffes et perturbe sa récupération pendant 2 tours avec 75% chance (cooldown = "+this.cooldown_max1+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Eshir
  *@return la description du sort n°1 de Eshir
  */
  public String descriptionSort2() {
    return "Récupère 30% des points de vie maximums des alliés et augmente leur vitesse pendant 2 tours (cooldown = "+this.cooldown_max2+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Eshir
  *@return la description du sort n°1 de Eshir
  */
  public  String descriptionSort3() {
    return "Attaque violemment l'ennemi 4 fois et inflige des dégats dont une partie dépend de tes points de vie maximums. Chaque coup a 50% de chance de supprimer un effet bénéfique de l'ennemi. Le cooldown se réinitialise si le coup est fatal à l'ennemi (cooldown = "+this.cooldown_max3+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Eshir
  *@return la description du sort n°1 de Eshir
  */
  public String descriptionSort4() {
    return "Si tes points de vie actuels sont superieur à ceux de l'ennemi, tes attaques infligeront des dégats supplémentaires équivalent à une partie de la différence entre vos points de vie";
  }
}
