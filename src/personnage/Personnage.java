/*
*Cette classe est une classe de Personnage
*
*@author CHATEAU Julien
*@version 1.0
*/

package personnage;
import java.lang.String;
import java.util.*;
import effet.*;
import effet.effetnocif.nocifmalus.*;
import effet.effetnocif.*;
import effet.effetnocif.nocifmalusprofit.*;
import effet.effetnocif.nocifprofit.*;
import effet.effettour.*;
import effet.effetbenefique.*;
import effet.effetbenefique.benefiquemalus.*;
import effet.effetbenefique.benefiquemalusprofit.*;
import effet.effetbenefique.benefiqueprofit.*;
import comptes.*;
import runes.*;
import application.*;

public abstract class Personnage {
  protected int pdv;
  protected int pdv_max;
  protected int attaque;
  protected int attaque_max;
  protected int defense;
  protected int defense_max;
  protected int vitesse;
  protected int vitesse_max;
  protected int tx_crit;
  protected int dgts_crit;
  protected String nom;
  protected int[] cooldown;
  protected final int cooldown_max1 = 0;
  protected int cooldown_max2;
  protected int cooldown_max3;
  protected int cooldown_max4;
  protected String nomsort1;
  protected String nomsort2;
  protected String nomsort3;
  protected String nomsort4;
  protected boolean possede_passif;
  protected boolean passifactivable;
  protected boolean passif_revive;
  protected boolean possede_passif_revive;
  protected int sort4active;
  protected int shield;
  protected ArrayList<Effet> liste_effet;
  protected Rune[] equipees;
  protected int nb_rune;
  protected int[] stats_suppl;
  protected int[] type_rune;
  protected int[] bonus_rune;
  protected int[] type_sort;

  /**Constructeur de la classe Personnage
  *@param p qui représente les points de vie maximum du personnage
  *@param a qui représente l'attaque maximale du personnage
  *@param d qui représente la défense maximale du personnage
  *@param v qui représente la vitesse maximale du personnage
  *@param n qui représente le nom du personnage
  *@param s1 qui représente le nom du sort n°1 du personnage
  *@param s2 qui représente le nom du sort n°2 du personnage
  *@param s3 qui représente le nom du sort n°3 du personnage
  *@param s4 qui représente le nom du sort n°4 du personnage
  *@param cd2 qui représente le cooldown maximal du sort n°2 du personnage
  *@param cd3 qui représente le cooldown maximal du sort n°3 du personnage
  *@param cd4 qui représente le cooldown maximal du sort n°4 du personnage
  *@param p_p qui représente le fait que le personnage possède ou non un sort passif
  */
  public Personnage(int p, int a, int d, int v, String n, String s1, String s2, String s3, String s4, int cd2, int cd3, int cd4, boolean p_p, boolean pa, boolean p_r) {
    this(p, a, d, v, n, s1, s2, s3, s4, cd2, cd3, cd4);
    this.possede_passif = p_p;
    this.passifactivable = pa;
    this.passif_revive = p_r;
    this.possede_passif_revive = this.passif_revive;
    // this.pdv_max = p;
    // this.attaque_max = a;
    // this.defense_max = d;
    // this.vitesse_max = v;
    // this.pdv = p;
    // this.attaque = a;
    // this.defense = d;
    // this.vitesse = v;
    // this.tx_crit = 15;
    // this.dgts_crit = 25;
    // this.nom = n;
    // this.cooldown = new int[4];
    // this.nomsort1 = s1;
    // this.nomsort2 = s2;
    // this.nomsort3 =  s3;
    // this.nomsort4 = s4;
    // this.cooldown_max2 = cd2;
    // this.cooldown_max3 = cd3;
    // this.cooldown_max4 = cd4;
    // this.shield = 0;
    // this.liste_effet = new ArrayList<Effet>();
    // this.equipees = new Rune[5];
    // for (int i = 0; i<5; i++) {
    //   this.equipees[i] = null;
    // }
    // this.nb_rune = 0;
    // this.stats_suppl = new int[6];
    // for (int i = 0; i<6; i++) {
    //   this.stats_suppl[i] = 0;
    // }
    // this.type_rune = new int[6];
    // for (int i = 0; i<6; i++) {
    //   this.type_rune[i] = 0;
    // }
    // this.bonus_rune = new int[6];
    // for (int i = 0; i<6; i++) {
    //   this.bonus_rune[i] = 0;
    // }
  }

  public Personnage(int p, int a, int d, int v, String n, String s1, String s2, String s3, String s4, int cd2, int cd3, int cd4) {
    this.pdv_max = p;
    this.attaque_max = a;
    this.defense_max = d;
    this.vitesse_max = v;
    this.tx_crit = 15;
    this.dgts_crit = 25;
    this.pdv = p;
    this.attaque = a;
    this.defense = d;
    this.vitesse = v;
    this.nom = n;
    this.cooldown = new int[4];
    this.nomsort1 = s1;
    this.nomsort2 = s2;
    this.nomsort3 =  s3;
    this.nomsort4 = s4;
    this.cooldown_max2 = cd2;
    this.cooldown_max3 = cd3;
    this.cooldown_max4 = cd4;
    this.possede_passif = false;
    this.passifactivable = false;
    this.shield = 0;
    this.passif_revive = false;
    this.sort4active = 0;
    this.possede_passif_revive = this.passif_revive;
    this.liste_effet = new ArrayList<Effet>();
    this.equipees = new Rune[5];
    for (int i = 0; i<5; i++) {
      this.equipees[i] = null;
    }
    this.stats_suppl = new int[6];
    for (int i = 0; i<6; i++) {
      this.stats_suppl[i] = 0;
    }
    this.nb_rune = 0;
    this.type_rune = new int[6];
    for (int i = 0; i<6; i++) {
      this.type_rune[i] = 0;
    }
    this.bonus_rune = new int[6];
    for (int i = 0; i<6; i++) {
      this.bonus_rune[i] = 0;
    }
    this.type_sort = new int[4];
    for (int i = 0; i<4; i++) {//à enlevé plus tard
      this.type_sort[i] = 1;
    }
  }

  public Personnage(String n) {
    this.nom = n;
  }


  public abstract int[] sort1(Personnage[] cibles, Personnage[] ennemisCibles);
  public abstract int[] sort2(Personnage[] cibles, Personnage[] ennemisCibles);
  public abstract int[] sort3(Personnage[] cibles, Personnage[] ennemisCibles);
  public abstract int[] sort4(Personnage[] cibles, Personnage[] ennemisCibles);
  public abstract String toString();
  public abstract String descriptionSort1();
  public abstract String descriptionSort2();
  public abstract String descriptionSort3();
  public abstract String descriptionSort4();
  public abstract int iaChoixSort(Personnage ennemi);
  public abstract int[] cibleSortIa(Personnage allieLanceur, Personnage[] ennemis);


  /**Sélecteur qui permet d'acceder au point de vie du personnage
  *@return les points de vie du personnage
  */
  public int getTypeSort(int i) {
    return this.type_sort[i-1];
  }

  /**Sélecteur qui permet d'acceder au point de vie du personnage
  *@return les points de vie du personnage
  */
  public int getPdv() {
    return this.pdv;
  }

  /**Sélecteur qui permet d'acceder au point de vie maximum du personnage
  *@return les points de vie maximum du personnage
  */
  public int getPdvMax() {
    return this.pdv_max;
  }


  /**Sélecteur qui permet d'acceder à l'attaque du personnage
  *@return l'attaque du personnage
  */
  public int getAttaque() {
    return this.attaque;
  }

  /**Sélecteur qui permet d'acceder à l'attaque maximale du personnage
  *@return l'attaque maximale du personnage
  */
  public int getAttaqueMax() {
    return this.attaque_max;
  }

  /**Sélecteur qui permet d'acceder à la défense du personnage
  *@return la défense du personnage
  */
  public int getDefense() {
    return this.defense;
  }

  /**Sélecteur qui permet d'acceder à la défense maximale du personnage
  *@return la défense maximale du personnage
  */
  public int getDefenseMax() {
    return this.defense_max;
  }

  /**Sélecteur qui permet d'acceder à la vitesse du personnage
  *@return la vitesse du personnage
  */
  public int getVitesse() {
    return this.vitesse;
  }

  /**Sélecteur qui permet d'acceder à la vitesse maximale du personnage
  *@return la vitesse maximale du personnage
  */
  public int getVitesseMax() {
    return this.vitesse_max;
  }


  /**Sélecteur qui permet d'acceder aux taux de critique du personnage
  *@return le taux de critique du personnage
  */
  public int getTxCrit() {
    return this.tx_crit;
  }

  /**Sélecteur qui permet d'acceder aux dégats critiques du personnage
  *@return les dégats critiques du personnage
  */
  public int getDgtsCrit() {
    return this.dgts_crit;
  }

  /**Sélecteur qui permet d'acceder au nom du personnage
  *@return le nom du personnage
  */
  public String getNom() {
    return this.nom;
  }

  /**Sélecteur qui permet d'acceder aux cooldowns du personnage
  *@return les cooldowns du personnage
  */
  public int[] getCooldown() {
    return this.cooldown;
  }

  /**Sélecteur qui permet d'acceder au cooldown d'un sort du personnage
  *@param s qui représente le sort choisi
  *@return le cooldown du sort choisi du personnage
  */
  public int getCooldown(int s) {
    return this.cooldown[s];
  }

  public int getCooldMax4() {
    return this.cooldown_max4;
  }

  /**Sélecteur qui permet d'acceder au nom du sort n°1 du personnage
  *@return le nom du sort n°1 du personnage
  */
  public String getNomSort(int i) {
    switch (i) {
      case 1:
      return this.nomsort1;

      case 2:
      return this.nomsort2;

      case 3:
      return this.nomsort3;

      case 4:
      return this.nomsort4;
    }
    return "";
  }


  /**Sélecteur qui permet d'acceder à la liste d'effet du personnage
  *@return la liste d'effet du personnage
  */
  public ArrayList<Effet> getListeEffet() {
    return this.liste_effet;
  }

  /**Sélecteur qui permet de savoir si le personnage possède un passif ou non
  *@return True si le personnage possède un passif, False sinon
  */
  public boolean getPossedePassif() {
    return this.possede_passif;
  }

  /**Sélecteur qui permet de savoir si le personnage possède un passif activable ou non
  *@return True si le personnage possède un passif activable, False sinon
  */
  public boolean getPassifActivable() {
    return this.passifactivable;
  }

  /**Sélecteur qui permet de savoir le nombre de points de bouclier du personnage
  *@return le nombre de points de bouclier du personnage
  */
  public int getShield() {
    return this.shield;
  }

  /**Sélecteur qui permet de savoir le nombre de points de bouclier du personnage
  *@return le nombre de points de bouclier du personnage
  */
  public boolean getPassifRevive() {
    return this.passif_revive;
  }


  public Rune getRune(int i) {
    return this.equipees[i];
  }

  public void supprimerRune(int pos) {
    this.equipees[pos] = null;
  }

//Ordre des stats = ordre runes page livre brouillon
  public int getStatsSuppl(int i) {
    return this.stats_suppl[i];
  }

  //Ordre des stats = ordre runes page livre brouillon
    public int[] getStatsSuppl() {
      return this.stats_suppl;
    }

  public int getTypeRune(int i) {
    return this.type_rune[i];
  }

  public void typeRunePlusPlus(int i) {
    this.type_rune[i] = this.type_rune[i] + 1;
  }

  public void typeRuneMoinsMoins(int i) {
    this.type_rune[i] = this.type_rune[i] - 1;
  }

  public void setStatsSuppl(int i, int valeur) {
    this.stats_suppl[i] = valeur;
  }

  public int getBonusRune(int i) {
    return this.bonus_rune[i];
  }

  public void rajouterBonusRune(int i, int plus) {
    this.bonus_rune[i] += plus;
  }

  public void enleverBonusRune(int i, int moins) {
    this.bonus_rune[i] -= moins;
  }

  /**Modifieur qui permet de changer le nombre de points de bouclier du personnage
  *@param sh qui représente les nouveaux points de bouclier du personnage
  */
  public void setPassifRevive(boolean sh) {
    this.passif_revive = sh;
  }

  public void setSort4Active(int act) {
    this.sort4active = act;
  }

  public int getSort4Active() {
    return this.sort4active;
  }

  /**Modifieur qui permet de changer le nombre de points de bouclier du personnage
  *@param sh qui représente les nouveaux points de bouclier du personnage
  */
  public void setShield(int sh) {
    this.shield = sh;
  }

  /**Modifieur qui permet de changer les points de vie du personnage
  *@param p qui représente les nouveaux points de vie du personnage
  */
  public void setPdv(int p) {
    this.pdv = p;
  }

  /**Modifieur qui permet de changer l'attaque du personnage
  *@param a qui représente la nouvelle attaque du personnage
  */
  public void setAttaque(int a) {
    this.attaque = a;
  }

  /**Modifieur qui permet de changer la défense du personnage
  *@param d qui représente la nouvelle défense du personnage
  */
  public void setDefense(int d) {
    this.defense = d;
  }

  /**Modifieur qui permet de changer la vitesse du personnage
  *@param v qui représente la nouvelle vitesse du personnage
  */
  public void setVitesse(int v) {
    this.vitesse = v;
  }


  /**Modifieur qui permet de changer les points de vie max du personnage
  *@param p qui représente les nouveaux points de vie max du personnage
  */
  public void setPdvMax(int p) {
    this.pdv_max = p;
  }

  /**Modifieur qui permet de changer l'attaque max du personnage
  *@param a qui représente la nouvelle attaque max du personnage
  */
  public void setAttaqueMax(int a) {
    this.attaque_max = a;
  }

  /**Modifieur qui permet de changer la défense max du personnage
  *@param d qui représente la nouvelle défense max du personnage
  */
  public void setDefenseMax(int d) {
    this.defense_max = d;
  }

  /**Modifieur qui permet de changer la vitesse max du personnage
  *@param v qui représente la nouvelle vitesse max du personnage
  */
  public void setVitesseMax(int v) {
    this.vitesse_max = v;
  }


  /**Modifieur qui permet de changer le pseudo du personnage
  *@param v qui représente le nouveau pseudo du personnage
  */
  public void setTxCrit(int t) {
    this.tx_crit = t;
  }

  /**Modifieur qui permet de changer le pseudo du personnage
  *@param v qui représente le nouveau pseudo du personnage
  */
  public void setDgtsCrit(int t) {
    this.dgts_crit = t;
  }


  /**Modifieur qui permet de changer le pseudo du personnage
  *@param v qui représente le nouveau pseudo du personnage
  */
  public void setPseudo(String p) {
    this.nom = p;
  }

  /**Modifieur qui permet de changer le cooldown d'un sort du personnage
  *@param sort qui représente le sort choisi
  *@param number qui représente la nouvelle valeur du cooldown
  */
  public void setCooldown(int sort, int number) {
    this.cooldown[sort] = number;
  }


  public int changementCible(int cibleActuelle) {//changement cible (1 devient 2 et inversement)
    return (((cibleActuelle+4)%3)+2)/2;
  }

  public int choixIaPreferenceDegats(int cibleActuelle, Personnage[] ennemis) {
    int res = cibleActuelle;
    if (ennemis[cibleActuelle-1].possedeEffet(new Invincibilite(0))) {//si invincible
      if (!ennemis[this.changementCible(cibleActuelle)-1].possedeEffet(new Invincibilite(0))) {//si autre pas invincible
        res = this.changementCible(cibleActuelle);//on change
      } else {//si autre invincible
        if (ennemis[cibleActuelle-1].possedeEffet(new Immunite(0))) {// si immunisé
          res = this.changementCible(cibleActuelle);// on change
        }
      }
    } else {//si pas invincible
      if (ennemis[cibleActuelle-1].possedeEffet(new Immunite(0))) {//si immunisé
        if (!ennemis[this.changementCible(cibleActuelle)-1].possedeEffet(new Invincibilite(0))) {//si autre ni invincible
          if (!ennemis[this.changementCible(cibleActuelle)-1].possedeEffet(new Immunite(0))) {//ni immunisé
            res = this.changementCible(cibleActuelle);//on change
          }
        }
      }
    }
    return res;
  }

  public int choixIaPreferenceEffets(int cibleActuelle, Personnage[] ennemis) {
    int res = cibleActuelle;
    if (ennemis[cibleActuelle-1].possedeEffet(new Immunite(0))) {//si immunisé
      if (!ennemis[this.changementCible(cibleActuelle)-1].possedeEffet(new Immunite(0))) {//si autre pas immunisé
        res = this.changementCible(cibleActuelle);//on change
      } else {//si autre immunisé
        if (ennemis[cibleActuelle-1].possedeEffet(new Invincibilite(0))) {// si invincible
          res = this.changementCible(cibleActuelle);// on change
        }
      }
    } else {//si pas immunisé
      if (ennemis[cibleActuelle-1].possedeEffet(new Invincibilite(0))) {//si invincible
        if (!ennemis[this.changementCible(cibleActuelle)-1].possedeEffet(new Immunite(0))) {//si autre ni immunisé
          if (!ennemis[this.changementCible(cibleActuelle)-1].possedeEffet(new Invincibilite(0))) {//ni invincible
            res = this.changementCible(cibleActuelle);//on change
          }
        }
      }
    }
    return res;
  }


  public void equiperRune(Rune r) {
    this.equipees[r.getPosition()] = r;
  }

  public void appliquerEffetsRunes() {
    for (int i = 0; i<6; i++) {
      this.stats_suppl[i] = 0;
    }
    for (int i = 0; i<5; i++) {
      if (this.equipees[i] != null) {
        this.equipees[i].appliquerStats(this);
      }
    }
    if (this.equipees[0] != null) {
      this.equipees[0].appliquerBonus(this);
      int typeposzero = this.type_rune[Compte.typeRuneToInt(this.equipees[0])];
      if (typeposzero % 2 == 1) {
        for (int i = 0; i<(typeposzero-1)/2; i++) {
          this.equipees[0].appliquerBonus(this);
        }
      } else if (typeposzero == 4) {
        this.equipees[0].appliquerBonus(this);
      }
      if (typeposzero != 5) {
        for (int j = 0; j<6; j++) {
          if (j != Compte.typeRuneToInt(this.equipees[0])) {
            if (this.type_rune[j] % 2 == 0) {
              for (int k = 0; k<this.type_rune[j]/2; k++) {
                Compte.intToRune(j).appliquerBonus(this);
              }
            } else if (this.type_rune[j] == 3) {
              Compte.intToRune(j).appliquerBonus(this);
            }
          }
        }
      }
    } else {
      for (int j = 0; j<6; j++) {
        if (this.type_rune[j] % 2 == 0) {
          for (int k = 0; k<this.type_rune[j]/2; k++) {
            Compte.intToRune(j).appliquerBonus(this);
          }
        } else if (this.type_rune[j] == 3) {
          Compte.intToRune(j).appliquerBonus(this);
        }
      }
    }
  }

  public void desappliquerEffetsRunes() {
    this.attaque_max -= this.stats_suppl[0];
    this.vitesse_max -= this.stats_suppl[1];
    this.defense_max -= this.stats_suppl[2];
    this.pdv_max -= this.stats_suppl[3];
    this.tx_crit -= this.stats_suppl[4];
    this.dgts_crit -= this.stats_suppl[5];
    for (int i = 0; i<6; i++) {
      this.stats_suppl[i] = 0;
    }
  }

  public void reinitialiserBoostRunes() {
    this.desappliquerEffetsRunes();
    this.appliquerEffetsRunes();
  }


  public boolean appliquerEffet(Effet e, int pourcentage) {
    if (this.getPdv() > 0 && !(this instanceof Poutch)) {
      boolean applique = false;
      if (!this.possedeEffet(new Immunite(0))) {
        Random r = new Random();
        int chance = r.nextInt(100);
        if ((this.getClass().equals(new Nephthys("").getClass()) && (e instanceof Etourdissement))) {
          Combat.ajouterCommentaire("-"+this.getClass().getName().substring(11)+" ne peut pas être étourdis !");
        } else {
          if (e instanceof EffetNocif || e instanceof DegatContinu) {
            if (chance < pourcentage) {
              this.appliquerEffet(e);
              applique = true;
            } else {
              Combat.ajouterCommentaire("-"+this.getClass().getName().substring(11)+" a résisté à votre effet nocif");
            }
          } else {
            this.appliquerEffet(e);
          }
        }
      } else {
        Combat.ajouterCommentaire("-"+this.getClass().getName().substring(11)+" est immunisé contre les effets nocifs !");
      }
      return applique;
    } else {
      return false;
    }
  }

  public boolean appliquerEffet(Effet e) {
    if (e instanceof EffetTour || e instanceof Bombe) {
      this.liste_effet.add(e);
    } else {
      int i = 0;
      boolean trouve = false;
      while (i<this.liste_effet.size() && !trouve) {
        if (this.liste_effet.get(i).getClass().equals(e.getClass())) {
          if (this.liste_effet.get(i).getNbrTour() < e.getNbrTour()) {
            this.liste_effet.get(i).setNbrTour(e.getNbrTour());
          }
          trouve = true;
        }
        i++;
      }
      if (!trouve) {
        this.liste_effet.add(e);
        e.effetPerso(this);
      }
    }
    return true;
  }

  public boolean coupFatal(int degats) {
    int crit = (CoGestionSorts.getCrit()) ? degats*this.dgts_crit/100 : 0;
    return (this.pdv + this.shield) - degats + crit <= 0 && !this.possedeEffet(new Invincibilite(0)) && degats != 10000;
  }

  public void desappliqueJuste() {
    for (int i = 0; i<this.liste_effet.size(); i++) {
      if (this.liste_effet.get(i).getJusteApplique()) {
        this.liste_effet.get(i).setJusteApplique(false);
      }
    }
  }

  public void cooldownmoinsmoins() {
    for (int i = 0; i < 4 ; i++) {
      this.setCooldown(i, this.getCooldown(i)-1);
      if (this.getCooldown(i)<0) {
        this.setCooldown(i, 0);
      }
    }
  }

  public boolean possedeEffet(Effet e) {
    int i = 0;
    boolean test = false;
    while (i<this.getListeEffet().size() && !test) {
      if (this.getListeEffet().get(i).getClass().equals(e.getClass())) {
        test = true;
      }
      i++;
    }
    return test;
  }

  public Effet returnEffet(Effet e) {
    int i = 0;
    boolean test = false;
    Effet res = e;
    while (i<this.getListeEffet().size() && !test) {
      if (this.getListeEffet().get(i).getClass().equals(e.getClass())) {
        test = true;
        res = this.getListeEffet().get(i);
      }
      i++;
    }
    return res;
  }


  public boolean possedeEffetBenefique() {
    int i = 0;
    boolean test = false;
    while (i<this.getListeEffet().size() && !test) {
      if (this.getListeEffet().get(i).getClass().getName().substring(0, 20).equals("effet.effetbenefique") || this.liste_effet.get(i) instanceof Regeneration) {
        test = true;
      }
      i++;
    }
    return test;
  }

  public boolean possedeEffetNTour(Effet e, int n) {
    int i = 0;
    boolean test = false;
    while (i<this.getListeEffet().size() && !test) {
      if (this.getListeEffet().get(i).getClass().equals(e.getClass()) && this.getListeEffet().get(i).getNbrTour() == n) {
        test = true;
      }
      i++;
    }
    return test;
  }

  public boolean possedeEffetJusteApplique(Effet e) {
    int i = 0;
    boolean test = false;
    while (i<this.getListeEffet().size() && !test) {
      if (this.getListeEffet().get(i).getClass().equals(e.getClass()) && this.getListeEffet().get(i).getJusteApplique()) {
        test = true;
      }
      i++;
    }
    return test;
  }

  public String afficherEffet(boolean droite) {
    String liste = "";
    String couleur = "";
    if (droite) {
      for (int i = 0; i<this.liste_effet.size(); i++) {
        if (this.liste_effet.get(i).getClass().getName().substring(0, 16).equals("effet.effetnocif") || this.liste_effet.get(i) instanceof Bombe || this.liste_effet.get(i) instanceof DegatContinu) {
          couleur = "FE0000";
        } else {
          couleur = "00539B";
        }
        liste += "<html><font color = #"+couleur+" >["+this.liste_effet.get(i).getEtiquette()+": "+this.liste_effet.get(i).getNbrTour()+"]</font></hmtl> ";
      }
    } else {
      for (int i = 0; i<this.liste_effet.size(); i++) {
        if (this.liste_effet.get(i).getClass().getName().substring(0, 16).equals("effet.effetnocif") || this.liste_effet.get(i) instanceof Bombe || this.liste_effet.get(i) instanceof DegatContinu) {
          couleur = "FE0000";
        } else {
          couleur = "00539B";
        }
        liste = "<html><font color = #"+couleur+" >["+this.liste_effet.get(i).getEtiquette()+": "+this.liste_effet.get(i).getNbrTour()+"]</font></hmtl> "+liste;
      }
    }
    return liste;
  }

  public void remplacerEffetParUnAutre(Effet remplacelui, Effet parlui) {
    if (this.possedeEffet(remplacelui)) {
      int i = 0;
      boolean trouve = false;
      int tour = 0;
      while (i<this.liste_effet.size() && !trouve) {
        if (this.liste_effet.get(i).getClass().equals(remplacelui.getClass())) {
          trouve = true;
          if (parlui.getNbrTour() == -1) {
            tour = this.liste_effet.get(i).getNbrTour();
          }
        }
        i++;
      }
      if (trouve) {
        if (parlui.getNbrTour() == -1) {
          parlui.setNbrTour(tour);
        }
        this.appliquerEffet(parlui);
        this.liste_effet.get(i-1).supprEffetPerso(this);
        this.liste_effet.remove(this.liste_effet.get(i-1));
      }
    }
  }

  public int cleanDegatsContinus() {
    int i = 0;
    if (this.possedeEffet(new DegatContinu(0))) {
      for (int j = 0; j<this.liste_effet.size(); j++) {
        if (this.liste_effet.get(j).getClass().equals(new DegatContinu(0).getClass())) {
          this.liste_effet.remove(this.liste_effet.get(j));
          i++;
          j--;
        }
      }
    }
    return i;
  }

  public int cleanToutLesEffetNocif() {
    int i = 0;
    for (int j = 0; j<this.liste_effet.size(); j++) {
      if (this.liste_effet.get(j).getClass().getName().substring(0, 16).equals("effet.effetnocif") || this.liste_effet.get(j) instanceof Bombe || this.liste_effet.get(j) instanceof DegatContinu) {
        this.liste_effet.get(j).supprEffetPerso(this);
        this.liste_effet.remove(this.liste_effet.get(j));
        i++;
        j--;
      }
    }
    return i;
  }

  public int nbrEffetsNocifs() {
    int i = 0;
      for (int j = 0; j<this.liste_effet.size(); j++) {
        if (this.liste_effet.get(j).getClass().getName().substring(0, 16).equals("effet.effetnocif") || this.liste_effet.get(j) instanceof Bombe || this.liste_effet.get(j) instanceof DegatContinu) {
          i++;
        }
      }
    return i;
  }

  public int nbrEffetsBenefiques() {
    int i = 0;
      for (int j = 0; j<this.liste_effet.size(); j++) {
        if (this.liste_effet.get(j).getClass().getName().substring(0, 20).equals("effet.effetbenefique") || this.liste_effet.get(j) instanceof Regeneration) {
          i++;
        }
      }
    return i;
  }

  public int nbrDegatsContinus() {
    int i = 0;
    if (this.possedeEffet(new DegatContinu(0))) {
      for (int j = 0; j<this.liste_effet.size(); j++) {
        if (this.liste_effet.get(j).getClass().equals(new DegatContinu(0).getClass())) {
          i++;
        }
      }
    }
    return i;
  }

  public void EffetsBenefiquesToDegatContinu() {
    if (this.possedeEffetBenefique()) {
      int i = 1;
      while (i == 1) {
        i = EffetBenefique.clean(this, 100);
        if (i == 1) {
          this.appliquerEffet(new DegatContinu(2));
        }
      }
    }
  }

  public void cleanToutLesEffetsBenefiques(int pourcentage) {
    Random r = new Random();
    if (this.possedeEffetBenefique()) {
      if (r.nextInt(100) < pourcentage) {
        int i = 1;
        while (i == 1) {
          i = EffetBenefique.clean(this, 100);
        }
      }
    }
  }

  // public int calculDegatsEffectue(int degats) {
  //   int degatseffectue = degats;
  //   if (CoGestionSorts.getCrit()) {
  //     Combat.ajouterCommentaire("-Coup Critique !!");
  //     degatseffectue += (degatseffectue * this.getDgtsCrit() / 100);
  //   }
  //   return degatseffectue;
  // }

  public void heal(int heal) {
    if (this.getPdv() > 0 && !(this instanceof Poutch)) {
      if (!this.possedeEffet(new AntiHeal(0)) && this.getPdv() != this.getPdvMax()) {
        this.setPdv(this.getPdv()+heal);
        Combat.ajouterCommentaire("-"+this.getClass().getName().substring(11)+" se soigne du "+heal);
        if (this.getPdv() > this.getPdvMax()) {
          this.setPdv(this.getPdvMax());
        }
      } else if (this.getPdv() != this.getPdvMax()) {
        Combat.ajouterCommentaire("-"+this.getClass().getName().substring(11)+" ne peut pas se soin");
      }
    }
  }

  public void volDeVie(int degats, Personnage ennemi, int pourcentage) {
    int degatseffectue = degats;
    if (ennemi.possedeEffet(new Marque(0))) {
      degatseffectue += (degatseffectue*25/100);
    }
    if (CoGestionSorts.getCrit()) {
      degatseffectue += (degatseffectue * this.getDgtsCrit() / 100);
    }
    if (!(ennemi instanceof Poutch)) {
      this.heal(degatseffectue*pourcentage/100);
    }
  }

  public String descriptionSorts() {
    String des = "";
    des += "\n\n      -"+this.getNomSort(1)+":  "+descriptionSort1();
    des += "\n\n\n      -"+this.getNomSort(2)+":  "+descriptionSort2();
    des += "\n\n\n      -"+this.getNomSort(3)+":  "+descriptionSort3();
    des += "\n\n\n      -"+this.getNomSort(4)+":  "+descriptionSort4();
    return des;
  }

  public String descriptionCaracteristiques() {
    String des = "\n      "+this.getClass().getName().substring(11)+" : ";
    des += "\n\n      Points de vie :      "+this.getPdv()+"/"+this.getPdvMax();
    des += "\n      Attaque :               "+this.getAttaque()+"/"+this.getAttaqueMax();
    des += "\n      Defense :              "+this.getDefense()+"/"+this.getDefenseMax();
    des += "\n      Vitesse :                "+this.getVitesse()+"/"+this.getVitesseMax();
    des += "\n\n      Tx crit. :                "+this.getTxCrit()+"/"+this.getTxCrit()+"%";
    des += "\n      Dgts crit. :             +"+this.getDgtsCrit()+"/"+this.getDgtsCrit()+"%";
    return des;
  }

  public void cooldownPlusPlus() {
    this.setCooldown(1, this.getCooldown(1)+1);
    if (this.getCooldown(1) > this.cooldown_max2) {
      this.setCooldown(1, this.cooldown_max2);
    }
    this.setCooldown(2, this.getCooldown(2)+1);
    if (this.getCooldown(2) > this.cooldown_max3) {
      this.setCooldown(2, this.cooldown_max3);
    }
    this.setCooldown(3, this.getCooldown(3)+1);
    if (this.getCooldown(3) > this.cooldown_max4) {
      this.setCooldown(3, this.cooldown_max4);
    }
  }

  public void resetCooldown() {
    this.setCooldown(1, 0);
    this.setCooldown(2, 0);
    this.setCooldown(3, 0);
  }

  public void reajusteCarac() {
    this.pdv = this.pdv_max;
    this.attaque = this.attaque_max;
    this.defense = this.defense_max;
    this.vitesse = this.vitesse_max;
  }

  public Personnage boostEtageBoss(int i) {
    int boost = i/10*5;
    this.attaque_max += (this.attaque_max * boost / 100);
    this.vitesse_max += (this.vitesse_max * boost / 100);
    this.defense_max += (this.defense_max * boost / 100);
    this.pdv_max += (this.pdv_max * boost / 100);
    this.reajusteCarac();
    return this;
  }

  public Personnage malusPremiersNiveaux(int i) {
    this.attaque_max -= (this.attaque_max * i / 100);
    this.vitesse_max -= (this.vitesse_max * i / 100);
    this.defense_max -= (this.defense_max * i / 100);
    this.pdv_max -= (this.pdv_max * i / 100);
    this.reajusteCarac();
    return this;
  }

  public void malusParceQueTropFort() {
    this.malusPremiersNiveaux(8);
  }

  public void reajusteCaracFinCombat() {
    if (this.shield != 0) {
      this.shield = 0;
    }
    this.pdv += (this.pdv_max - this.pdv) * 75 / 100;
    if (this.pdv > this.pdv_max) {
      this.pdv = this.pdv_max;
    }
    this.attaque = this.attaque_max;
    this.defense = this.defense_max;
    this.vitesse = this.vitesse_max;
    if (this.possedeEffet(new BuffCrit(0))) {
      this.returnEffet(new BuffCrit(0)).supprEffetPerso(this);
    }
    this.liste_effet.clear();
    this.cooldownmoinsmoins();
    this.sort4active = 0;
  }

  public void reajusteCaracFinEtage() {
    this.shield = 0;
    this.attaque = this.attaque_max - this.stats_suppl[0];
    this.vitesse = this.vitesse_max - this.stats_suppl[1];
    this.defense = this.defense_max - this.stats_suppl[2];
    this.pdv = this.pdv_max - this.stats_suppl[3];
    if (this.possedeEffet(new BuffCrit(0))) {
      this.returnEffet(new BuffCrit(0)).supprEffetPerso(this);
    }
    this.liste_effet.clear();
    this.cooldownZero();
    if (this.possede_passif_revive) {
      this.passif_revive = true;
    }
    this.sort4active = 0;
  }

  public void clearEffets() {
    for (Effet e : this.liste_effet) {
      e.supprEffetPerso(this);
    }
    this.liste_effet.clear();
  }

  public void reajusteCaracDefaite() {
    this.reajusteCarac();
    if (this.shield != 0) {
      this.shield = 0;
    }
    this.liste_effet.clear();
    this.cooldownZero();
    if (this.possede_passif_revive) {
      this.passif_revive = true;
    }
    this.sort4active = 0;
  }

  public void cooldownZero() {
    for (int i = 1; i<4; i++) {
      this.setCooldown(i, 0);
    }
  }

  public void lancerDeSort4(int lancer) {
    switch (lancer) {

      case 1:
      this.appliquerEffet(new Immunite(2));
      break;

      case 2:
      this.appliquerEffet(new BuffAttaque(2));
      break;

      case 3:
      this.appliquerEffet(new BuffDefense(2));
      break;

      case 4:
      this.appliquerEffet(new BuffVitesse(2));
      break;

      case 5:
      this.appliquerEffet(new Regeneration(2));
      break;

      case 6:
      this.heal(this.getPdvMax()*15/100);
      break;

      default:
      break;
    }
  }


  public String toStringRunes() {
    String s = "";
    for (int i = 0; i<5; i++) {
      if (this.equipees[i] == null) {
        s += "\n";
      } else {
        s += this.equipees[i].toString()+"\n";
      }
    }
    return s;
  }
}
