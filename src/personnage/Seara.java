/*
*Cette classe est une classe de Seara
*
*@author CHATEAU Julien
*@version 1.0
*/

package personnage;
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

import java.util.*;


public class Seara extends Personnage {

/**Constructeur de la classe Seara qui fait appel au contructeur de la classe Personnage
*@param n qui représente le nom de Seara
*(les autres paramètres sont prédéfinis)
*/
  public Seara(String n) {
    super(387, 126, 109, 80, n, "Temps Passant", "Destinée de la Destruction", "Futur Prédit", "Rêve Eveillé", 3, 4, 5);
    this.type_sort[0] = 2;
    this.type_sort[1] = 2;
    this.type_sort[2] = 1;
    this.type_sort[3] = 3;
  }

/**Méthode qui correspond au sort n°1 de Seara
*@param ennemi qui représente le Personnage sur lequel le sort est lancé
*/
  public int[] sort1(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*75/100 - cibles[0].getDefense()*30/100;
    if (cibles[0].nbrEffetsNocifs() > 1) {
      this.appliquerEffet(new BuffVitesse(1));
    }
    if (cibles[0].possedeEffet(new Bombe(0, 0))) {
      CoGestionSorts.forcerBombe(cibles[0]);
    }
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°2 de Seara
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort2(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 10000;
    cibles[0].appliquerEffet(new Bombe(2, this.getAttaque()*75/100), 100);
    cibles[0].appliquerEffet(new DegatContinu(2), 75);
    this.setCooldown(1, this.cooldown_max2);
    return degatseffectue;
  }

 /**Méthode qui correspond au sort n°3 de Seara
 *@param ennemi qui représente le Personnage sur lequel le sort est lancé
 */
  public int[] sort3(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*60/100 + this.getPdv()*15/100 - cibles[0].getDefense()*30/100;
    degatseffectue[1] = this.getAttaque()*60/100 + this.getPdv()*15/100 - cibles[1].getDefense()*30/100;
    Random r = new Random();
    if (r.nextInt(100) < 80) {
      cibles[0].EffetsBenefiquesToDegatContinu();
    } else {
      Combat.ajouterCommentaire("-"+cibles[0].getClass().getName().substring(11)+" a résisté à la cleans");
    }
    if (r.nextInt(100) < 80) {
      cibles[1].EffetsBenefiquesToDegatContinu();
    } else if (!(cibles[1] instanceof Poutch)) {
      Combat.ajouterCommentaire("-"+cibles[1].getClass().getName().substring(11)+" a résisté à la cleans");
    }
    this.setCooldown(2, this.cooldown_max3);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°4 de Seara
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort4(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 10000;
    cibles[0].heal(cibles[0].getPdvMax() * 60 / 100);
    cibles[1].heal(cibles[1].getPdvMax() * 60 / 100);
    this.appliquerEffet(new Etourdissement(2), 100);//1 seul tour d'étourdissement
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
    } else if (this.pdv < (this.pdv_max * 25 / 100) && this.cooldown[3] == 0 && !this.possedeEffet(ah)) {
      res = 4;
    } else if (ennemi.possedeEffetBenefique()) {
      res = 3;
    } else if (this.cooldown[1] == 0) {
      res = 2;
    } else if (this.pdv < (this.pdv_max * 40 / 100) && this.cooldown[3] == 0 && !this.possedeEffet(ah)) {
      res = 4;
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

    int nbrEffetsBenefiquesCible = ennemis[cibleSort[0]-1].nbrEffetsBenefiques();
    int nbrEffetsBenefiquesAutre = ennemis[this.changementCible(cibleSort[0])-1].nbrEffetsBenefiques();


    if (this.possedeEffet(sil)) {//si silence
      cibleSort[1] = 1;//sort 1
    } else if (this.getPdv() < this.getPdvMax()*40/100 && !this.possedeEffet(new AntiHeal(0)) && this.cooldown[3] == 0) {
      cibleSort[0] = 3;
      cibleSort[1] = 4;
    } else if (allieLanceur.getPdv() < allieLanceur.getPdvMax()*40/100 && allieLanceur.getPdv() != 0 && !allieLanceur.possedeEffet(new AntiHeal(0)) && this.cooldown[3] == 0) {
      cibleSort[0] = 3;
      cibleSort[1] = 4;
    } else if (nbrEffetsBenefiquesCible+nbrEffetsBenefiquesAutre >= 2 && this.cooldown[2] == 0) {
      cibleSort[1] = 3;
    } else if (!ennemis[cibleSort[0]-1].possedeEffet(new Immunite(0)) && this.cooldown[1] == 0) {
      cibleSort[1] = 2;
    } else if (!ennemis[this.changementCible(cibleSort[0])-1].possedeEffet(new Immunite(0)) && this.cooldown[1] == 0) {
      cibleSort[0] = this.changementCible(cibleSort[0]);
      cibleSort[1] = 2;
    } else {
      cibleSort[1] = 1;
    }

    /*** Optimisation du choix de la cible dans le cas d'une attaque à cible unique sur un ennemi si l'allié de la cible n'est pas KO ***/
    if (!ko) {
      if (cibleSort[1] == 1) {
        cibleSort[0] = this.choixIaPreferenceEffets(cibleSort[0], ennemis);
      } else if (cibleSort[1] == 2) {
        cibleSort[0] = this.choixIaPreferenceEffets(cibleSort[0], ennemis);
      }
    }

    return cibleSort;
  }

  /**Méthode qui permet de décrire un Seara
  *@return la description de Seara
  */
  public String toString() {
    return "Votre personnage est un Seara et s'appelle "+this.getNom()+", il possède actuellement "+this.getPdv()+" points de vie, "+this.getAttaque()+" d'attaque, "+this.getDefense()+" de défense et "+this.getVitesse()+" de vitesse.";
  }

  /**Méthode qui permet de décrire le sort n°1 de Seara
  *@return la description du sort n°1 de Seara
  */
  public String descriptionSort1() {
    return "Attaque l'ennemi et lui inflige des dégats. Si l'ennemi possède au moins 2 effet nocif, alors augmente votre vitesse pendant 2 tours. De plus si la cible possède une bombe, l'effet de cette dernière est activée (cooldown = "+this.cooldown_max1+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Seara
  *@return la description du sort n°1 de Seara
  */
  public String descriptionSort2() {
    return "Pose une bombe qui explosera 2 tours plus tard et un dégats continu de 2 tours sur l'ennemi (cooldown = "+this.cooldown_max2+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Seara
  *@return la description du sort n°1 de Seara
  */
  public  String descriptionSort3() {
    return "Prédit le futur des ennemis pour leur infliger des dégats dont une partie dépend de vos points de vies maximums. Peut aussi convertir chaque effets bénéfique qu'ils possèdent par un dégat continu de 2 tours avec 80% de chance. (cooldown = "+this.cooldown_max3+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Seara
  *@return la description du sort n°1 de Seara
  */
  public String descriptionSort4() {
    return "Vous utiliser la force qu'il vous reste pour soigner les alliés de 60% de leurs points de vie maximums. Vous est ensuite étourdis pendant 1 tour (cooldown = "+this.cooldown_max4+")";
  }
}
