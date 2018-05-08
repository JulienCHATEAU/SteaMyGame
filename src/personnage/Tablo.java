/*
*Cette classe est une classe de Tablo
*
*@author CHATEAU Julien
*@version 1.0
*/

package personnage;
import java.util.*;
import effet.effetnocif.*;
import effet.effetnocif.nocifmalus.*;
import java.util.*;
import effet.effetnocif.nocifmalusprofit.*;
import effet.effetnocif.nocifprofit.*;

import effet.effetbenefique.*;
import effet.effetbenefique.benefiquemalus.*;
import effet.effetbenefique.benefiquemalusprofit.*;
import effet.effetbenefique.benefiqueprofit.*;

import application.*;
import effet.effettour.*;
import effet.*;


public class Tablo extends Personnage {

  /**Constructeur de la classe Tablo qui fait appel au contructeur de la classe Personnage
  *@param n qui représente le nom de Tablo
  *(les autres paramètres sont prédéfinis)
  */
  public Tablo(String n) {
    super(356, 137, 115, 83, n, "Lancer de Dés", "Malchance 7", "Dé de la Folie", "Relancer les Dés", 3, 4, 0, true, true, false);
    this.type_sort[0] = 2;
    this.type_sort[1] = 1;
    this.type_sort[2] = 2;
    this.type_sort[3] = 0;
  }

  /**Méthode qui correspond au sort n°1 de Tablo
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort1(Personnage[] cibles, Personnage[] ennemisCibles) {
    Random r = new Random();
    int lancer1 = r.nextInt(6)+1;
    int lancer2 = r.nextInt(6)+1;
    Combat.ajouterCommentaire("-Tablo a lancé 2 dés et a obtenu un "+lancer1+" et un "+lancer2);
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*37/100 - cibles[0].getDefense()*15/100;
    this.lancerDeSort1(cibles[0], lancer1);
    degatseffectue[0] += this.getAttaque()*38/100 - cibles[0].getDefense()*15/100;
    this.lancerDeSort1(cibles[0], lancer2);
    this.boostLancerDouble(lancer1, lancer2);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°2 de Tablo
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort2(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*85/100 - cibles[0].getDefense()*30/100;
    degatseffectue[1] = this.getAttaque()*85/100 - cibles[1].getDefense()*30/100;
    Random r = new Random();
    int lancer1 = r.nextInt(6)+1;
    int lancer2 = r.nextInt(6)+1;
    Combat.ajouterCommentaire("-Tablo a lancé 2 dés et a obtenu un "+lancer1+" et un "+lancer2);
    if (lancer1+lancer2 > 7) {
      cibles[0].appliquerEffet(new Etourdissement(1), 50);
      cibles[1].appliquerEffet(new Etourdissement(1), 50);
    } else {
      cibles[0].appliquerEffet(new Ralentissement(2), 75);
      cibles[1].appliquerEffet(new Ralentissement(2), 75);
    }
    this.boostLancerDouble(lancer1, lancer2);
    this.setCooldown(1, this.cooldown_max2);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°3 de Tablo
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort3(Personnage[] cibles, Personnage[] ennemisCibles) {
    Random r = new Random();
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 0;
    int lancer1 = r.nextInt(6)+1;
    degatseffectue[0] += this.getAttaque()*lancer1*6/100 - cibles[0].getDefense()*7/100;
    int lancer2 = r.nextInt(6)+1;
    if (lancer1 == lancer2) {
      degatseffectue[0] += this.getAttaque()*lancer2*6/100;
    } else {
      degatseffectue[0] += this.getAttaque()*lancer2*6/100 - cibles[0].getDefense()*8/100;
    }
    int lancer3 = r.nextInt(6)+1;
    if (lancer2 == lancer3) {
      degatseffectue[0] += this.getAttaque()*lancer3*6/100;
    } else {
      degatseffectue[0] += this.getAttaque()*lancer3*6/100 - cibles[0].getDefense()*7/100;
    }
    int lancer4 = r.nextInt(6)+1;
    if (lancer3 == lancer4) {
      degatseffectue[0] += this.getAttaque()*lancer4*6/100;
    } else {
      degatseffectue[0] += this.getAttaque()*lancer4*6/100 - cibles[0].getDefense()*8/100;
    }
    Combat.ajouterCommentaire("-Tablo a lancé 4 dés et a obtenu un "+lancer1+", un "+lancer2+", un "+lancer3+" et un "+lancer4);
    this.setCooldown(2, this.cooldown_max3);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°4 de Tablo
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort4(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 10000;
    Random r = new Random();
    int lancer1 = r.nextInt(6)+1;
    int allie = r.nextInt(2);
    if (allie == 0) {
      cibles[1].lancerDeSort4(lancer1);
    } else {
      cibles[0].lancerDeSort4(lancer1);
    }
    Combat.ajouterCommentaire("-Tablo a lancé 1 dés et a obtenu un "+lancer1);
    return degatseffectue;
  }

  public int iaChoixSort(Personnage ennemi) {
    int res = 0;
    Effet ah = new AntiHeal(0);
    Effet slow = new Ralentissement(0);
    Effet ba = new BuffAttaque(0);
    Effet inv = new Invincibilite(0);
    Effet imm = new Immunite(0);
    Effet sil = new Silence(0);

    if (this.possedeEffet(sil)) {
      res = 1;
    } else if (ennemi.possedeEffet(inv) && ennemi.possedeEffet(imm)) {
      res = 1;
    } else if (ennemi.possedeEffet(imm) && this.cooldown[1] == 0 && this.pdv < (this.pdv_max * 75 / 100)) {
      res = 2;
    } else if (ennemi.possedeEffet(imm) && this.pdv >= (this.pdv_max * 75 / 100)) {
      res = 1;
    } else if (this.possedeEffet(ba) && !this.possedeEffet(ah) && this.pdv < (this.pdv_max * 75 / 100) && this.cooldown[1] == 0) {
      res = 2;
    } else if (!ennemi.possedeEffet(slow) && ennemi.getVitesse() > this.vitesse) {
      res = 1;
    } else if (this.pdv < (this.pdv_max * 50 / 100) && this.cooldown[1] == 0 && !this.possedeEffet(ah)) {
      res  = 2;
    } else if (!ennemi.possedeEffet(slow)) {
      res = 1;
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
    boolean ko = false;
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
    } else if (this.cooldown[2] == 0) {
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

  /**Méthode qui permet de décrire un Tablo
  *@return la description de Tablo
  */
  public String toString() {
    return "Votre personnage est un Tablo et s'appelle "+this.getNom()+", il possède actuellement "+this.getPdv()+" points de vie, "+this.getAttaque()+" d'attaque, "+this.getDefense()+" de défense et "+this.getVitesse()+" de vitesse.";
  }

  /**Méthode qui permet de décrire le sort n°1 de Tablo
  *@return la description du sort n°1 de Tablo
  */
  public String descriptionSort1() {
    return "Lance 2 dés sur l'ennemi. Tu appliques les effets nocifs suivants sur l'ennemi avec 75% de chance chaucun, en fonction du chiffre obtenu par les dés : 4 -> Anti Heal, 5 -> Break Def, 6 -> Marque. Si tu obtiens un double, tu es soigné en fonction de tes points de vie maximums (cooldown = "+this.cooldown_max1+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Tablo
  *@return la description du sort n°1 de Tablo
  */
  public String descriptionSort2() {
    return "Lance 2 dés sur les ennemis. Si la somme des 2 dés est inferieur ou égal à 7, tu ralentis les ennemis pendant 2 tours avec 75% de chance, sinon tu les étourdis avec 50% de chance. Si tu obtiens un double, tu es soigné en fonction de tes points de vie maximums (cooldown = "+this.cooldown_max2+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Tablo
  *@return la description du sort n°1 de Tablo
  */
  public  String descriptionSort3() {
    return "Attaque consécutivement l'ennemi avec 4 dés. A chaque dés lancés tu infliges des dégats en fonction du chiffre obtenu. Si deux lancés consécutifs donnent un double, tu ignores la défense de l'ennemi sur un des 2 lancés (cooldown = "+this.cooldown_max3+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Tablo
  *@return la description du sort n°1 de Tablo
  */
  public String descriptionSort4() {
    return "Lance un dé au début de chaque tour, ce qui procures à un alliés choisis aléatoirement un des effets suivants en fonction du chiffre obtenu : 1 -> Immunite (2 tours), 2 -> Buff Attaque (2 tours), 3 -> Buff Défense (2 tours), 4 -> Buff Vitesse (2 tours), 5 -> Régénération (2 tours),  6 -> Soin (15% des HP max)";
  }


    public void lancerDeSort1(Personnage ennemi, int lancer) {
      switch (lancer) {
        case 4:
        ennemi.appliquerEffet(new AntiHeal(2), 75);
        break;

        case 5:
        ennemi.appliquerEffet(new BreakDefense(2), 75);
        break;

        case 6:
        ennemi.appliquerEffet(new Marque(2), 75);
        break;

        default:
        break;
      }
    }

    public void boostLancerDouble(int lancer1, int lancer2) {
      if (lancer1 == lancer2) {
        this.heal(this.getPdvMax()*5/100);
      }
    }












}
