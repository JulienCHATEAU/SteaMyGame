/*
*Cette classe est une classe de Nicki
*
*@author CHATEAU Julien
*@version 1.0
*/

package personnage;
import effet.effetnocif.*;
import effet.effetnocif.nocifmalus.*;
import effet.effetnocif.nocifmalusprofit.*;
import effet.effetnocif.nocifprofit.*;
import java.util.*;

import effet.effetbenefique.*;
import effet.effetbenefique.benefiquemalus.*;
import effet.effetbenefique.benefiquemalusprofit.*;
import effet.effetbenefique.benefiqueprofit.*;
import java.util.*;

import application.*;
import effet.effettour.*;
import effet.*;


public class Nicki extends Personnage {

/**Constructeur de la classe Nicki qui fait appel au contructeur de la classe Personnage
*@param n qui représente le nom de Nicki
*(les autres paramètres sont prédéfinis)
*/
  public Nicki(String n) {
    super(337, 136, 109, 84, n, "Tybbers", "Chevalier Jouet", "Prière de la Fille", "Ange Gardien des Ténèbres", 3, 4, 5);
    this.type_sort[0] = 2;
    this.type_sort[1] = 1;
    this.type_sort[2] = 3;
    this.type_sort[3] = 1;
  }

/**Méthode qui correspond au sort n°1 de Nicki
*@param ennemi qui représente le Personnage sur lequel le sort est lancé
*/
  public int[] sort1(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*75/100 - cibles[0].getDefense()*30/100;
    if (cibles[0].appliquerEffet(new Etourdissement(1), 18)) {
      degatseffectue[0] += (degatseffectue[0]*25/100);
    }
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°2 de Nicki
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort2(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*55/100 + this.getPdv()*15/100 - cibles[0].getDefense()*30/100;
    degatseffectue[1] = this.getAttaque()*55/100 + this.getPdv()*15/100 - cibles[1].getDefense()*30/100;
    Random r = new Random();
    int ral = 0;
    int dc = 0;
    int ral2 = 0;
    int dc2 = 0;
    boolean immun1 = cibles[0].possedeEffet(new Immunite(0));
    boolean immun2 = cibles[1].possedeEffet(new Immunite(0));
    for (int i = 0; i<3; i++) {
      if (!immun1) {
        if (r.nextInt(100) < 50) {
          cibles[0].appliquerEffet(new Ralentissement(2));
        } else {
          ral++;
        }
        if (r.nextInt(100) < 50) {
          cibles[0].appliquerEffet(new DegatContinu(2));
        } else {
          dc++;
        }
      }
      if (!immun2) {
        if (r.nextInt(100) < 50) {
          cibles[1].appliquerEffet(new Ralentissement(2));
        } else {
          ral2++;
        }
        if (r.nextInt(100) < 50) {
          cibles[1].appliquerEffet(new DegatContinu(2));
        } else {
          dc2++;
        }
      }
    }
    if (immun1) {
      Combat.ajouterCommentaire("-"+cibles[0].getClass().getName().substring(11)+" est immunisé contre les effets nocifs");
    } else if (ral == 3 && !(cibles[0] instanceof Poutch)) {
      Combat.ajouterCommentaire("-"+cibles[0].getClass().getName().substring(11)+" a résisté aux 3 ralentissements");
    }
    if (dc == 3 && !(cibles[0] instanceof Poutch) && !immun1) {
      Combat.ajouterCommentaire("-"+cibles[0].getClass().getName().substring(11)+" a résisté aux 3 dégats continus");
    } else if (dc > 0 && !(cibles[0] instanceof Poutch)) {
      Combat.ajouterCommentaire("-"+cibles[0].getClass().getName().substring(11)+" a résisté à "+dc+" dégats continus");
    }

    if (immun2) {
      Combat.ajouterCommentaire("-"+cibles[0].getClass().getName().substring(11)+" est immunisé contre les effets nocifs");
    } else if (ral2 == 3 && !(cibles[1] instanceof Poutch)) {
      Combat.ajouterCommentaire("-"+cibles[1].getClass().getName().substring(11)+" a résisté aux 3 ralentissements");
    }
    if (dc2 == 3 && !(cibles[1] instanceof Poutch) && !immun2) {
      Combat.ajouterCommentaire("-"+cibles[1].getClass().getName().substring(11)+" a résisté aux 3 dégats continus");
    } else if (dc2 > 0 && !(cibles[1] instanceof Poutch)) {
      Combat.ajouterCommentaire("-"+cibles[1].getClass().getName().substring(11)+" a résisté à "+dc2+" dégats continus");
    }
    this.setCooldown(1, this.cooldown_max2);
    return degatseffectue;
  }

 /**Méthode qui correspond au sort n°3 de Nicki
 *@param ennemi qui représente le Personnage sur lequel le sort est lancé
 */
  public int[] sort3(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = 10000;
    cibles[0].cleanToutLesEffetNocif();
    cibles[1].cleanToutLesEffetNocif();
    cibles[0].heal(this.getAttaque()/3 + this.getPdvMax()/10);
    cibles[1].heal(this.getAttaque()/3 + this.getPdvMax()/10);
    this.setCooldown(2, this.cooldown_max3);
    return degatseffectue;
  }

  /**Méthode qui correspond au sort n°4 de Nicki
  *@param ennemi qui représente le Personnage sur lequel le sort est lancé
  */
  public int[] sort4(Personnage[] cibles, Personnage[] ennemisCibles) {
    int[] degatseffectue = new int[2];
    degatseffectue[0] = this.getAttaque()*80/100 - cibles[0].getDefense()*30/100;
    degatseffectue[1] = this.getAttaque()*80/100 - cibles[1].getDefense()*30/100;
    int ah = 0;
    int ah2 = 0;
    Random r = new Random();
    boolean immun1 = cibles[0].possedeEffet(new Immunite(0));
    boolean immun2 = cibles[1].possedeEffet(new Immunite(0));
    for (int i = 0; i<5; i++) {
      if (!immun1) {
        if (r.nextInt(100) < 28) {
          cibles[0].appliquerEffet(new AntiHeal(3));
        } else {
          ah++;
        }
      }
      if (!immun2) {
        if (r.nextInt(100) < 28) {
          cibles[1].appliquerEffet(new AntiHeal(3));
        } else {
          ah2++;
        }
      }
    }
    if (immun1) {
      Combat.ajouterCommentaire("-"+cibles[0].getClass().getName().substring(11)+" est immunisé contre les effets nocifs");
    } else if (ah == 5 && !(cibles[1] instanceof Poutch)) {
      Combat.ajouterCommentaire("-"+cibles[0].getClass().getName().substring(11)+" a résisté aux 5 anti-heals");
    }
    if (immun2) {
      Combat.ajouterCommentaire("-"+cibles[1].getClass().getName().substring(11)+" est immunisé contre les effets nocifs");
    } else if (ah2 == 5 && !(cibles[1] instanceof Poutch)) {
      Combat.ajouterCommentaire("-"+cibles[1].getClass().getName().substring(11)+" a résisté aux 5 anti-heals");
    }
    this.appliquerEffet(new BuffAttaque(3));
    this.setCooldown(3, this.cooldown_max4);
    return degatseffectue;
  }

  public int iaChoixSort(Personnage ennemi) {
    int res = 0;
    Effet inv = new Invincibilite(0);
    Effet imm = new Immunite(0);
    Effet sil = new Silence(0);

    if (this.possedeEffet(sil)) {
      res = 1;
    } else if (((this.nbrEffetsNocifs() > 1 && this.getPdv() < this.getPdvMax()*80/100) || this.getPdv() < this.getPdvMax()*33/100) && this.cooldown[2] == 0) {
      res = 3;
    } else if (ennemi.possedeEffet(inv) && ennemi.possedeEffet(imm)) {
      res = 1;
    } else if (ennemi.possedeEffet(inv)) {
      res = 1;
    } else if (ennemi.possedeEffet(imm)) {
      if (this.cooldown[3] == 0) {
        res = 4;
      } else {
        res = 1;
      }
    } else if (this.cooldown[3] == 0) {
      res = 4;
    } else if (this.cooldown[1] == 0) {
      res = 2;
    } else if (this.cooldown[2] == 0 && this.getPdv() < this.getPdvMax()*75/100) {
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
    } else if (this.nbrEffetsNocifs() >= 2 && this.getPdv() < this.getPdvMax()*70/100 && this.cooldown[2] == 0) {
      cibleSort[0] = 3;
      cibleSort[1] = 3;//sort 3
    } else if (allieLanceur.nbrEffetsNocifs() >= 2 && allieLanceur.getPdv() < allieLanceur.getPdvMax()*70/100 && allieLanceur.getPdv() != 0 && this.cooldown[2] == 0) {
      cibleSort[0] = 3;
      cibleSort[1] = 3;//sort 3
    } else if (allieLanceur.getPdv() < allieLanceur.getPdvMax()*50/100 && allieLanceur.getPdv() != 0 && this.cooldown[2] == 0) {
      cibleSort[0] = 3;
      cibleSort[1] = 3;//sort 3
    } else if (this.cooldown[3] == 0) {
      cibleSort[1] = 4;//sort 4
    } else if (this.cooldown[1] == 0 && !(ennemis[0].possedeEffet(new Immunite(0)) && ennemis[1].possedeEffet(new Immunite(0)))) {
      cibleSort[1] = 2;//sort 2
    } else {
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

  /**Méthode qui permet de décrire un Nicki
  *@return la description de Nicki
  */
  public String toString() {
    return "Votre personnage est un Nicki et s'appelle "+this.getNom()+", il possède actuellement "+this.getPdv()+" points de vie, "+this.getAttaque()+" d'attaque, "+this.getDefense()+" de défense et "+this.getVitesse()+" de vitesse.";
  }

  /**Méthode qui permet de décrire le sort n°1 de Nicki
  *@return la description du sort n°1 de Nicki
  */
  public String descriptionSort1() {
    return "Invoque un nounours qui va attaquer l'ennemi et l'étourdir avec 18% de chance. Si l'étourdissement est un succès, les dégats augmentent de 25% (cooldown = "+this.cooldown_max1+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Nicki
  *@return la description du sort n°1 de Nicki
  */
  public String descriptionSort2() {
    return "Lance un chevalier jouet qui attaque les ennemis 3 fois avec pour chaque attaque 50% de chance de les ralentir et leur appliquer un dégat continu pendant 2 tours (cooldown = "+this.cooldown_max2+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Nicki
  *@return la description du sort n°1 de Nicki
  */
  public  String descriptionSort3() {
    return "Retire tous les effets nocifs des alliés et les soigne. Le montant du soin est proportionnel à ton attaque et tes points de vie maximums (cooldown = "+this.cooldown_max3+")";
  }

  /**Méthode qui permet de décrire le sort n°1 de Nicki
  *@return la description du sort n°1 de Nicki
  */
  public String descriptionSort4() {
    return "Ton ange gardien augmente ton attaque pendant 3 tours puis envoye une rafale de 5 attaques sur les ennemis, attaques qui auront 15% de chance chacunes d'empêcher les ennemis de se soigner pendant 3 tours (cooldown = "+this.cooldown_max4+")";
  }
}
