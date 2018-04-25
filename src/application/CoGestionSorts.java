package application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import effet.*;
import effet.effettour.*;

import effet.effetnocif.*;
import effet.effetnocif.nocifmalus.*;
import effet.effetnocif.nocifmalusprofit.*;
import effet.effetnocif.nocifprofit.*;

import effet.effetbenefique.*;
import effet.effetbenefique.benefiquemalus.*;
import effet.effetbenefique.benefiquemalusprofit.*;
import effet.effetbenefique.benefiqueprofit.*;

import application.*;
import personnage.*;


public class CoGestionSorts implements ActionListener {

  private CombatDuo c2;
  private static boolean crit;
  private static boolean rejouer = false;
  private static int compteur = 0;

  public static void setRejouer(boolean rejouer) {
    CoGestionSorts.rejouer = rejouer;
  }

  public static boolean getRejouer() {
    return CoGestionSorts.rejouer;
  }

  public static void setCompteur(int cmp) {
    CoGestionSorts.compteur = cmp;
  }

  public static void compteurPlusPlus() {
    CoGestionSorts.compteur = CoGestionSorts.compteur + 1;
  }

  public static int getCompteur() {
    return CoGestionSorts.compteur;
  }

  public CoGestionSorts(CombatDuo c2) {
    this.c2 = c2;
    this.crit = false;
  }

  public void actionPerformed(ActionEvent e) {
    Random r = new Random();
    CoGestionSorts.rejouer = false;
    boolean ia = false;
    Personnage lanceur = this.c2.getJoueurCourant();
    Personnage allieLanceur = this.c2.getAllieLanceur();
    Personnage[] tabEnnemisLanceurSansOrdre = this.c2.getEnnemisLanceurSansOrdre();
    Personnage[] tabAlliesLanceur = new Personnage[2];
    tabAlliesLanceur[0] = allieLanceur;
    tabAlliesLanceur[1] = lanceur;

    if (this.c2.getCible() != 0) {
      JButton source = (JButton) e.getSource();
      this.c2.setCible(this.c2.jbuttonToInt(source));
    } else {//choix cible et choix sort ia
      // this.c2.setSortChoisis(1);
      // this.c2.setCible(1);
      ia = true;
      int[] cibleSort = lanceur.cibleSortIa(allieLanceur, tabEnnemisLanceurSansOrdre);
      if (cibleSort[0] == 3 && !(lanceur.equals(this.c2.getJoueur(3)))) {//si le lanceur cible lui même et qu'il est numero 4 alors
      this.c2.setCible(4);
    } else if (cibleSort[0] == 4 && lanceur.equals(this.c2.getJoueur(4))) {//si le lanceur cible son allié et qu'il est numero 4 alors
    this.c2.setCible(3);
  } else {
    this.c2.setCible(cibleSort[0]);
  }
  this.c2.setSortChoisis(cibleSort[1]);
}

Personnage[] tabEnnemisLanceur = this.c2.getEnnemisLanceur();
Personnage cible = this.c2.getPersoCible();
Personnage allieCible = this.c2.getAlliePersoCible();
Personnage[] tabEnnemisCibles = this.c2.getEnnemisCible();
// tabEnnemisLanceur[0].setPdv(0);
// tabEnnemisLanceur[1].setPdv(0);
Personnage[] tabCibles = new Personnage[2];
tabCibles[0] = cible;
tabCibles[1] = allieCible;
Personnage[] tab = new Personnage[2];
tab[0] = lanceur;
if (tab[0].equals(tabEnnemisCibles[0])) {
  tab[1] = tabEnnemisCibles[1];
} else if (tab[0].equals(tabEnnemisCibles[1])) {
  tab[1] = tabEnnemisCibles[0];
}


int sortChoisis = this.c2.getSortChoisis();
int cooldownSortChoisis = lanceur.getCooldown(sortChoisis-1);
int coupcrit = r.nextInt(100);
int typeSortChoisis = lanceur.getTypeSort(sortChoisis);
int[] degatseffectue = new int[2];

CombatDuo c2bis = this.c2;

boolean marqueCible = false;
boolean marqueAllieCible = false;
boolean etourdis = false;
boolean stop = false;

// System.out.println("cible : "+cible.getClass().getName().substring(11));
// System.out.println("lanceur : "+lanceur.getClass().getName().substring(11));
// System.out.println("ennemi cible 0 : "+tabEnnemisCibles[0].getClass().getName().substring(11));
// System.out.println("ennemi cible 1 : "+tabEnnemisCibles[1].getClass().getName().substring(11));
// System.out.println("ennemi lanceur 0 : "+tabEnnemisLanceur[0].getClass().getName().substring(11));
// System.out.println("ennemi lanceur 1 : "+tabEnnemisLanceur[1].getClass().getName().substring(11));

if (!(lanceur.getPossedePassif() && lanceur.getPassifActivable())) {
  Combat.ajouterCommentaire(" ");
  this.c2.tirets();
  Combat.ajouterCommentaire(" ");
}

if (!stop && lanceur.getPdv() != 0) {
  if (lanceur.possedeEffet(new Silence(0)) && sortChoisis != 1) {//lanceur silence et sort différent du premier
    Combat.ajouterCommentaire("-"+lanceur.getClass().getName().substring(11)+" est silence !!");
    this.c2.fenetreN(2);
    Combat.ajouterCommentaire(" ");
  } else {// tout est ok

    if (cooldownSortChoisis == 0) {//sort pas en cooldown

      if (!lanceur.possedeEffet(new Etourdissement(0))) {//si pas étourdi

        if (coupcrit < lanceur.getTxCrit()) {//on vois si le sort sera un coup critique
          CoGestionSorts.setCrit(true);
        } else {
          CoGestionSorts.setCrit(false);
        }

        if (cible.possedeEffet(new Marque(0))) {//si avant de jouer l'adversaire possède une marque
        marqueCible = true;//on le sauvegarde
      }
      if (typeSortChoisis == 1 && allieCible.possedeEffet(new Marque(0))) {//si avant de jouer l'adversaire possède une marque
      marqueAllieCible = true;//on le sauvegarde
    }

    String tmp2 = Combat.a_afficher;
    Combat.a_afficher = "";

    if (sortChoisis == 1) {
      degatseffectue = lanceur.sort1(tabCibles, tabEnnemisCibles);
    } else if (sortChoisis == 2) {
      degatseffectue = lanceur.sort2(tabCibles, tabEnnemisCibles);
    } else if (sortChoisis == 3) {
      degatseffectue = lanceur.sort3(tabCibles, tabEnnemisCibles);
    } else if (sortChoisis == 4) {
      degatseffectue = lanceur.sort4(tabCibles, tabEnnemisCibles);
    }

    if (CoGestionSorts.getCrit() && degatseffectue[0] != 10000) {
      String tmp = Combat.a_afficher;
      Combat.a_afficher = "";
      Combat.ajouterCommentaire("-"+lanceur.getClass().getName().substring(11)+" utilise "+lanceur.getNomSort(sortChoisis));
      Combat.ajouterCommentaire("-Coup Critique !!");
      Combat.ajouterCommentaire(" ");
      Combat.a_afficher = tmp2 + Combat.a_afficher + tmp;
    } else {
      String tmp = Combat.a_afficher;
      Combat.a_afficher = "";
      Combat.ajouterCommentaire("-"+lanceur.getClass().getName().substring(11)+" utilise "+lanceur.getNomSort(sortChoisis));
      if (!tmp.equals("") || degatseffectue[0] != 10000) {
        Combat.ajouterCommentaire(" ");
      }
      Combat.a_afficher = tmp2 + Combat.a_afficher + tmp;
    }


    if (degatseffectue[0] != 10000) {//10000 points de dégats signifie que le sort lancé était un boost
      if (!cible.possedeEffet(new Invincibilite(0)) || (lanceur instanceof Sige && sortChoisis == 4)) {//si cibles pas invincible ou que le lanceur est un Sige et lance son sort 4

        if (lanceur instanceof Sige && sortChoisis == 4 && cible.possedeEffet(new Invincibilite(0))) {
          Combat.ajouterCommentaire("-Sige passe à travers l'invincibilité de "+cible.getClass().getName().substring(11)+" !!");
        }
        if (degatseffectue[0] != 0) {
          degatseffectue[0] = this.getDegatsModifies(lanceur, degatseffectue[0], marqueCible);
          this.gestionDegats(lanceur, tabEnnemisLanceur[0], degatseffectue[0]);
        }
      } else if (cible.possedeEffet(new Invincibilite(0))) {
        Combat.ajouterCommentaire("-"+cible.getClass().getName().substring(11)+" est invincible et ne subit aucun dégat !!");
      }

      if (!allieCible.possedeEffet(new Invincibilite(0)) || (lanceur instanceof Sige && sortChoisis == 4)) {
        if (degatseffectue[1] != 0) {
          if (lanceur instanceof Sige && sortChoisis == 4 && allieCible.possedeEffet(new Invincibilite(0))) {
            Combat.ajouterCommentaire("-Sige passe à travers l'invincibilité de "+allieCible.getClass().getName().substring(11)+" !!");
          }
          degatseffectue[1] = this.getDegatsModifies(lanceur, degatseffectue[1], marqueAllieCible);
          this.gestionDegats(lanceur, tabEnnemisLanceur[1], degatseffectue[1]);
        }
      } else if (allieCible.possedeEffet(new Invincibilite(0)) && degatseffectue[1] != 0) {
        Combat.ajouterCommentaire("-"+allieCible.getClass().getName().substring(11)+" est invincible et ne subit aucun dégat !!");
      }

      if (cible.possedeEffet(new ContreAttaque(0)) && cible.getPdv() > 0) {
        degatseffectue[0] = cible.sort1(tab, tabCibles)[0]*75/100;
        this.gestionContreAttaque(tabEnnemisLanceur[0], lanceur, degatseffectue[0]);
        if (degatseffectue[1] != 0 && allieCible.possedeEffet(new ContreAttaque(0)) && allieCible.getPdv() > 0) {
          degatseffectue[1] = allieCible.sort1(tab, tabCibles)[0]*75/100;
          this.gestionContreAttaque(tabEnnemisLanceur[1], lanceur, degatseffectue[1]);
        }
      }
    }
  } else {//si étourdi
    Combat.ajouterCommentaire("-"+lanceur.getClass().getName().substring(11)+" est étourdi !!");
    etourdis = true;
    lanceur.setSort4Active(0);
  }

  //réajustement des points de vies des 4 personnages si ceux si sont inferieur à 0
  if (tabEnnemisCibles[0].getPdv() < 0) {
    tabEnnemisCibles[0].clearEffets();
    tabEnnemisCibles[0].setPdv(0);
  }
  if (tabEnnemisCibles[1].getPdv() < 0) {
    tabEnnemisCibles[1].clearEffets();
    tabEnnemisCibles[1].setPdv(0);
  }
  if (cible.getPdv() < 0) {
    cible.clearEffets();
    cible.setPdv(0);
  }
  if (allieCible.getPdv() < 0) {
    allieCible.clearEffets();
    allieCible.setPdv(0);
  }


  this.gestionRevive(cible, tabEnnemisLanceur, tabAlliesLanceur);
  this.gestionRevive(allieCible, tabEnnemisLanceur, tabAlliesLanceur);
  this.gestionRevive(tabEnnemisCibles[0], tabEnnemisLanceur, tabAlliesLanceur);
  this.gestionRevive(tabEnnemisCibles[1], tabEnnemisLanceur, tabAlliesLanceur);

  stop = false;

  if (tabAlliesLanceur[0].getPdv() == 0 && tabAlliesLanceur[1].getPdv() == 0) {
    boolean gagne = this.c2.getIntJoueurCourant() > 2;
    this.c2.afficherCommentaires();
    this.c2.setAllBoutonPBA(false);
    this.c2.miseAJourPC();
    this.c2.fenetreN(1);
    ActionListener listener = new ActionListener() {
      public void actionPerformed(ActionEvent event){
        System.out.println((gagne) ? "gagné" : "perdu");
        new FinCombatDuo(c2bis, gagne);
      }
    };
    Timer timer = new Timer(1500, listener);
    timer.setRepeats(false);
    timer.start();
    stop = true;
  } else if (tabEnnemisLanceur[0].getPdv() == 0 && tabEnnemisLanceur[1].getPdv() == 0) {
    boolean gagne = this.c2.getIntJoueurCourant() <= 2;
    this.c2.afficherCommentaires();
    this.c2.setAllBoutonPBA(false);
    this.c2.miseAJourPC();
    this.c2.fenetreN(1);
    ActionListener listener = new ActionListener() {
      public void actionPerformed(ActionEvent event){
        System.out.println((gagne) ? "gagné" : "perdu");
        new FinCombatDuo(c2bis, gagne);
      }
    };
    Timer timer = new Timer(1500, listener);
    timer.setRepeats(false);
    timer.start();
    stop = true;
  }


  if (!stop) {
    EffetNocif.tourEffetsMoinsMoins(lanceur);
    EffetBenefique.tourEffetsMoinsMoins(lanceur);
    lanceur.cooldownmoinsmoins();


    if (CoGestionSorts.getRejouer()) {
      System.out.println("rejoue");
      this.c2.rejouer(lanceur, ia);
    }

    this.c2.afficherCommentaires();
    this.c2.setJoueurCourant();

    this.c2.miseAJourPC();
    this.c2.setAllBoutonPBA(false);
    this.c2.fenetreN(1);
    this.c2.miseAJourPBA();





    //passif joueur courant
    Personnage nouveauLanceur = this.c2.getJoueurCourant();
    Personnage[] nouveauTabEnnemisLanceur = this.c2.getEnnemisLanceur();
    Personnage nouvelAllieLanceur = this.c2.getAllieLanceur();
    Personnage[] nouveauTabAlliesLanceur = new Personnage[2];
    nouveauTabAlliesLanceur[0] = nouvelAllieLanceur;
    nouveauTabAlliesLanceur[1] = nouveauLanceur;


    CoGestionSorts current = this;


    if (!stop) {
      if (nouveauLanceur.getPossedePassif() && nouveauLanceur.getPassifActivable()) {
        this.c2.afficherPassif(nouveauLanceur);
        Timer timer = new Timer(1000, new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            Combat.ajouterCommentaire(" ");
            boolean stop2 = false;
            CoGestionSorts.appliquerBombe(nouveauLanceur);
            EffetTour.appliquerEffet(nouveauLanceur);
            EffetTour.tourEffetsMoinsMoins(nouveauLanceur);
            nouveauLanceur.desappliqueJuste();

            if (Combat.a_afficher.equals("       \n")) {
              Combat.a_afficher = "";
            }

            c2bis.afficherCommentaires();

            //réajustement des points de vies des 4 personnages si ceux si sont inferieur à 0
            if (tabEnnemisCibles[0].getPdv() < 0) {
              tabEnnemisCibles[0].clearEffets();
              tabEnnemisCibles[0].setPdv(0);
            }
            if (tabEnnemisCibles[1].getPdv() < 0) {
              tabEnnemisCibles[1].clearEffets();
              tabEnnemisCibles[1].setPdv(0);
            }
            if (cible.getPdv() < 0) {
              cible.clearEffets();
              cible.setPdv(0);
            }
            if (allieCible.getPdv() < 0) {
              allieCible.clearEffets();
              allieCible.setPdv(0);
            }

            c2bis.miseAJourPC();

            current.gestionRevive(nouveauLanceur, nouveauTabAlliesLanceur, nouveauTabEnnemisLanceur);
            current.gestionRevive(nouvelAllieLanceur, nouveauTabAlliesLanceur, nouveauTabEnnemisLanceur);
            current.gestionRevive(nouveauTabEnnemisLanceur[0], nouveauTabAlliesLanceur, nouveauTabEnnemisLanceur);
            current.gestionRevive(nouveauTabEnnemisLanceur[1], nouveauTabAlliesLanceur, nouveauTabEnnemisLanceur);

            if (nouveauTabAlliesLanceur[0].getPdv() == 0 && nouveauTabAlliesLanceur[1].getPdv() == 0) {
              boolean gagne = c2bis.getIntJoueurCourant() > 2;
              c2bis.afficherCommentaires();
              c2bis.setAllBoutonPBA(false);
              c2bis.miseAJourPC();
              c2bis.fenetreN(1);
              ActionListener listener = new ActionListener() {
                public void actionPerformed(ActionEvent event){
                  System.out.println((gagne) ? "gagné" : "perdu");
                  new FinCombatDuo(c2bis, gagne);
                }
              };
              Timer timer = new Timer(1500, listener);
              timer.setRepeats(false);
              timer.start();
              stop2 = true;
            } else if (nouveauTabEnnemisLanceur[0].getPdv() == 0 && nouveauTabEnnemisLanceur[1].getPdv() == 0) {
              boolean gagne = c2bis.getIntJoueurCourant() <= 2;
              c2bis.afficherCommentaires();
              c2bis.setAllBoutonPBA(false);
              c2bis.miseAJourPC();
              c2bis.fenetreN(1);
              ActionListener listener = new ActionListener() {
                public void actionPerformed(ActionEvent event){
                  System.out.println((gagne) ? "gagné" : "perdu");
                  new FinCombatDuo(c2bis, gagne);
                }
              };
              Timer timer = new Timer(1500, listener);
              timer.setRepeats(false);
              timer.start();
              stop2 = true;
            }

            if (!stop2) {
              Combat.ajouterCommentaire(" ");
              c2bis.tirets();
              Combat.ajouterCommentaire(" ");
              if (nouveauLanceur.getPdv() == 0) {
                c2bis.setJoueurCourant();
              } else {
                nouveauLanceur.sort4(nouveauTabAlliesLanceur, nouveauTabEnnemisLanceur);
              }
              c2bis.miseAJourPBA();
              c2bis.miseAJourPC();
              if (c2bis.getIntJoueurCourant() > 2) {
                c2bis.setCible(0);
                Timer timer = new Timer(1000, new CoGestionSorts(c2bis));
                timer.setRepeats(false);
                timer.start();
              } else {
                c2bis.setAllBoutonPBA(true);
              }
            }
          }
        });
        timer.setRepeats(false);
        timer.start();
      } else {
        Combat.ajouterCommentaire(" ");
        CoGestionSorts.appliquerBombe(nouveauLanceur);
        EffetTour.appliquerEffet(nouveauLanceur);
        EffetTour.tourEffetsMoinsMoins(nouveauLanceur);
        nouveauLanceur.desappliqueJuste();

        if (Combat.a_afficher.equals("       \n")) {
          Combat.a_afficher = "";
        }

        c2bis.afficherCommentaires();

        //réajustement des points de vies des 4 personnages si ceux si sont inferieur à 0
        if (tabEnnemisCibles[0].getPdv() < 0) {
          tabEnnemisCibles[0].clearEffets();
          tabEnnemisCibles[0].setPdv(0);
        }
        if (tabEnnemisCibles[1].getPdv() < 0) {
          tabEnnemisCibles[1].clearEffets();
          tabEnnemisCibles[1].setPdv(0);
        }
        if (cible.getPdv() < 0) {
          cible.clearEffets();
          cible.setPdv(0);
        }
        if (allieCible.getPdv() < 0) {
          allieCible.clearEffets();
          allieCible.setPdv(0);
        }

        this.c2.miseAJourPC();

        this.gestionRevive(nouveauLanceur, nouveauTabAlliesLanceur, nouveauTabEnnemisLanceur);
        this.gestionRevive(nouvelAllieLanceur, nouveauTabAlliesLanceur, nouveauTabEnnemisLanceur);
        this.gestionRevive(nouveauTabEnnemisLanceur[0], nouveauTabAlliesLanceur, nouveauTabEnnemisLanceur);
        this.gestionRevive(nouveauTabEnnemisLanceur[1], nouveauTabAlliesLanceur, nouveauTabEnnemisLanceur);

        if (nouveauTabAlliesLanceur[0].getPdv() == 0 && nouveauTabAlliesLanceur[1].getPdv() == 0) {
          boolean gagne = this.c2.getIntJoueurCourant() > 2;
          this.c2.afficherCommentaires();
          this.c2.setAllBoutonPBA(false);
          this.c2.miseAJourPC();
          this.c2.fenetreN(1);
          ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event){
              System.out.println((gagne) ? "gagné" : "perdu");
              new FinCombatDuo(c2bis, gagne);
            }
          };
          Timer timer = new Timer(1500, listener);
          timer.setRepeats(false);
          timer.start();
          stop = true;
        } else if (nouveauTabEnnemisLanceur[0].getPdv() == 0 && nouveauTabEnnemisLanceur[1].getPdv() == 0) {
          boolean gagne = this.c2.getIntJoueurCourant() <= 2;
          this.c2.afficherCommentaires();
          this.c2.setAllBoutonPBA(false);
          this.c2.miseAJourPC();
          this.c2.fenetreN(1);
          ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event){
              System.out.println((gagne) ? "gagné" : "perdu");
              new FinCombatDuo(c2bis, gagne);
            }
          };
          Timer timer = new Timer(1500, listener);
          timer.setRepeats(false);
          timer.start();
          stop = true;
        }

        if (!stop) {
          if (nouveauLanceur.getPdv() == 0) {
            c2bis.setJoueurCourant();
            c2bis.miseAJourPC();
            c2bis.miseAJourPBA();
          }

          if (c2bis.getIntJoueurCourant() > 2) {
            c2bis.setCible(0);
            Timer timer = new Timer(2000, new CoGestionSorts(c2bis));
            timer.setRepeats(false);
            timer.start();
          } else {
            c2bis.setAllBoutonPBA(true);
          }
        }
      }
    }
  }
} else {//sort en cooldown
  // Combat.ajouterCommentaire("-"+lanceur.getNomSort(sortChoisis)+" est en cooldown !!");
  // this.c2.afficherCommentaires();
  this.c2.fenetreN(2);
}

}

}

}


public int getDegatsModifies(Personnage lanceur, int degatseffectue, boolean marque) {
  Random r = new Random();
  if (marque) {//si l'adversaire avait une marque avant de lancer le sort, on augmente les dégats
  degatseffectue += (degatseffectue*25/100);
}

if (CoGestionSorts.getCrit()) {//si le sort est un coup critique on augmente les dégats en fonction des dégats critiques
  degatseffectue += (degatseffectue * lanceur.getDgtsCrit() / 100);
}

if (degatseffectue < lanceur.getAttaque()/10) {//on minimise les dégats à 10% de l'attaque du joueur
degatseffectue = lanceur.getAttaque()/10;
}
return degatseffectue;
}



public void gestionDegats(Personnage lanceur, Personnage cible, int degatseffectue) {
  if (cible.getPdv() > 0) {
    if (cible.getShield() != 0) {
      if (degatseffectue <= cible.getShield()) {
        cible.setShield(cible.getShield()-degatseffectue);
        Combat.ajouterCommentaire("-"+cible.getClass().getName().substring(11)+" a perdu "+degatseffectue+" points de bouclier");
      } else {
        int shield = cible.getShield();
        cible.setShield(0);
        cible.setPdv(cible.getPdv() - (degatseffectue-shield));
        Combat.ajouterCommentaire("-"+cible.getClass().getName().substring(11)+" a perdu "+shield+" points de bouclier et "+(degatseffectue-shield)+" points de vie");
      }
    } else {
      Combat.ajouterCommentaire("-"+cible.getClass().getName().substring(11)+" a perdu "+degatseffectue+" points de vie");
      cible.setPdv(cible.getPdv() - degatseffectue);
    }
  }
}



public void gestionContreAttaque(Personnage lanceur, Personnage cible, int degatseffectue) {
  if (!lanceur.possedeEffet(new Etourdissement(0))) {
    Combat.ajouterCommentaire("");
    Combat.ajouterCommentaire("-"+lanceur.getClass().getName().substring(11)+" contre-attaque en utilisant "+lanceur.getNomSort(1));
    if (!cible.possedeEffet(new Invincibilite(0))) {
      if (cible.possedeEffet(new Marque(0))) {
        degatseffectue += (degatseffectue*25/100);
      }
      if (degatseffectue < lanceur.getAttaque()/10) {
        degatseffectue = lanceur.getAttaque()/10;
      }
      this.gestionDegats(lanceur, cible, degatseffectue);
    } else {
      Combat.ajouterCommentaire("-"+cible.getClass().getName().substring(11)+" est invincible et ne subit aucun dégat !");
    }
  } else {
    Combat.ajouterCommentaire("-"+lanceur.getClass().getName().substring(11)+" est étourdi et ne peut pas contre-attaquer");
  }
}


public void gestionRevive(Personnage j2, Personnage[] allies, Personnage[] ennemis) {
  boolean revive2 = false;
  if (j2.getPassifRevive() && j2.getCooldown(3) == 0) {
    if (j2.getPdv() == 0) {
      j2.sort4(allies, ennemis);
      revive2 = true;
    }
  }
  if (revive2) {
    Combat.ajouterCommentaire("-"+j2.getClass().getName().substring(11)+" est revenu à la vie !!");
    this.c2.miseAJourPC();
  }
}


public static void appliquerBombe(Personnage p) {
  for (int i = 0; i<p.getListeEffet().size(); i++) {
    if (p.getListeEffet().get(i) instanceof Bombe && p.getListeEffet().get(i).getNbrTour() == 1) {
      Bombe b = (Bombe)p.getListeEffet().get(i);
      b.effetBombe(p);
      p.getListeEffet().remove(p.getListeEffet().get(i));
    }
  }
}

public static void forcerBombe(Personnage p) {
  for (int i = 0; i<p.getListeEffet().size(); i++) {
    if (p.getListeEffet().get(i) instanceof Bombe) {
      Bombe b = (Bombe)p.getListeEffet().get(i);
      b.effetBombe(p);
      p.getListeEffet().remove(p.getListeEffet().get(i));
    }
  }
}

public static void setCrit(boolean crit) {
  CoGestionSorts.crit = crit;
}

public static boolean getCrit() {
  return CoGestionSorts.crit;
}



}
