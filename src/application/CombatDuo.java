package application;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import application.*;
import personnage.*;
import runes.*;
import effet.*;
import effet.effetnocif.*;
import effet.effetnocif.nocifmalus.*;
import effet.effetnocif.nocifmalusprofit.*;
import effet.effetnocif.nocifprofit.*;
import comptes.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CombatDuo extends JPanel {

  private Jeu jeu;
  private Personnage[] p;
  private Personnage[] ia;
  public boolean[] aJoue;
  private int joueurCourant;
  private int sortChoisis;
  private int cible;
  private String action;
  // private CoGestionSorts cgs;
  public static int etage = 0;

  private JPanel panelcentre;
  private Fleche fleche;
  private JLabel info_ia;
  private JLabel effets_ia;
  private JLabel info_p;
  private JLabel effets_p;
  private BarreDeVie bdv_ia;
  private BarreDeVie bdv_p;

  private JLabel info_ia2;
  private JLabel effets_ia2;
  private JLabel info_p2;
  private JLabel effets_p2;
  private BarreDeVie bdv_ia2;
  private BarreDeVie bdv_p2;

  private JTextArea paneldroite;
  public static String a_afficher;
  public String contenu;

  private JPanel panelbas;
  private CardLayout cl;
  private String[] liste_cl;
  private CoAffichageCarac cac;

  private int tour;
  private JPanel panelbascibles;
  private JLabel label_pbc;
  private JLabel labelTour;
  private JButton bouton_pbc1;
  private JButton bouton_pbc2;
  private JButton bouton_pbc3;
  private JButton bouton_pbc4;

  private int wFleche;
  private int hFleche;

  private JPanel panelbasaction;
  private JLabel label_pba;
  private JButton bouton_pba1;
  private JButton bouton_pba2;
  private JButton bouton_pba3;
  private JButton bouton_pba4;

  private JPanel panelbassort;
  private JLabel label_pbs;
  private JButton bouton_pbs1;
  private JButton bouton_pbs2;
  private JButton bouton_pbs3;
  private JButton bouton_pbs4;


public CombatDuo(Jeu jeu) {
  super();
//Initialisation de CombatDuo//
  this.jeu = jeu;
  this.tour = 1;
  this.p = new Personnage[2];
  this.ia = new Personnage[2];
  this.p[0] = new Erwin("");
  this.p[1] = new Cuivre("");
  this.ia[0] = new Kaito("");
  this.ia[1] = new Ritesh("");
  this.sortChoisis = 0;
  this.cible = -1;
  this.aJoue = new boolean[4];
  for (int i = 0; i<4; i++) {
    this.aJoue[i] = false;
  }
  this.setLayout(new BorderLayout());

//Initialisation variables locales//
  Color gris = Color.decode("#F2E5CF");
  Color beige = Color.decode("#C3C1BF");
  Color mycolor2 = Color.decode("#B0AFAA");
  Font font15 = new Font("Arial",Font.BOLD,15);
  Font font14 = new Font("Arial",Font.BOLD,14);
  Font font13 = new Font("Arial",Font.BOLD,13);
  CombatDuo current = this;

//Panel Central//
  this.panelcentre = new JPanel();
  panelcentre.setLayout(null);
  panelcentre.setBackground(beige);

  this.labelTour = new JLabel("Tour n°1");
  this.labelTour.setFont(font15);

  this.info_ia = new JLabel("                                                           ");
  this.effets_ia = new JLabel();
  this.effets_ia.setPreferredSize(new Dimension(1000, 15));
  this.info_p = new JLabel("                                                           ");
  this.effets_p = new JLabel();
  this.effets_p.setPreferredSize(new Dimension(1000, 15));

  this.info_ia2 = new JLabel("                                                           ");
  this.effets_ia2 = new JLabel();
  this.effets_ia2.setPreferredSize(new Dimension(1000, 15));
  this.info_p2 = new JLabel("                                                           ");
  this.effets_p2 = new JLabel();
  this.effets_p2.setPreferredSize(new Dimension(1000, 15));

  this.info_ia.setFont(font14);
  this.effets_ia.setFont(font15);
  this.info_p.setFont(font14);
  this.effets_p.setFont(font15);


  this.info_ia2.setFont(font14);
  this.effets_ia2.setFont(font15);
  this.info_p2.setFont(font14);
  this.effets_p2.setFont(font15);

  this.effets_p.setHorizontalAlignment(SwingConstants.TRAILING);
  this.effets_p2.setHorizontalAlignment(SwingConstants.TRAILING);

  this.bdv_ia = new BarreDeVie();
  this.bdv_ia.setPreferredSize(new Dimension(250,15));
  this.bdv_p = new BarreDeVie();
  this.bdv_p.setPreferredSize(new Dimension(250,15));
  this.bdv_ia2 = new BarreDeVie();
  this.bdv_ia2.setPreferredSize(new Dimension(250,15));
  this.bdv_p2 = new BarreDeVie();
  this.bdv_p2.setPreferredSize(new Dimension(250,15));

  JPanel bordure1 = new JPanel();
  bordure1.setPreferredSize(new Dimension(270,55));
  bordure1.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.BLACK));
  // bordure1.setOpaque(false);
  bordure1.setBackground(Color.decode("#F4F4EE"));

  JPanel bordure2 = new JPanel();
  bordure2.setPreferredSize(new Dimension(270,55));
  bordure2.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.BLACK));
  // bordure2.setOpaque(false);
  bordure2.setBackground(Color.decode("#F4F4EE"));

  JPanel bordure3 = new JPanel();
  bordure3.setPreferredSize(new Dimension(270,55));
  bordure3.setBorder(BorderFactory.createMatteBorder(2, 1, 1, 2, Color.BLACK));
  // bordure3.setOpaque(false);
  bordure3.setBackground(Color.decode("#F4F4EE"));

  JPanel bordure4 = new JPanel();
  bordure4.setPreferredSize(new Dimension(270,55));
  bordure4.setBorder(BorderFactory.createMatteBorder(2, 1, 1, 2, Color.BLACK));
  // bordure4.setOpaque(false);
  bordure4.setBackground(Color.decode("#F4F4EE"));

  this.fleche = new Fleche(2);
  panelcentre.add(this.fleche);
  Dimension size = this.fleche.getPreferredSize();
  this.fleche.setBounds(274, 394, size.width, size.height);

  panelcentre.add(this.labelTour);

  panelcentre.add(this.info_ia);
  panelcentre.add(this.bdv_ia);
  panelcentre.add(bordure1);
  panelcentre.add(this.effets_ia);

  panelcentre.add(this.info_ia2);
  panelcentre.add(this.bdv_ia2);
  panelcentre.add(bordure2);
  panelcentre.add(this.effets_ia2);


  panelcentre.add(this.info_p);
  panelcentre.add(this.bdv_p);
  panelcentre.add(bordure3);
  panelcentre.add(this.effets_p);


  panelcentre.add(this.info_p2);
  panelcentre.add(this.bdv_p2);
  panelcentre.add(bordure4);
  panelcentre.add(this.effets_p2);

  panelcentre.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

  this.add(panelcentre, BorderLayout.CENTER);


//Panel de Droite
  this.paneldroite = new JTextArea();
  this.contenu = "";
  this.a_afficher = "";
  paneldroite.setFont(font15);
  paneldroite.setColumns(33);
  paneldroite.setEditable(false);
  paneldroite.setBackground(gris);
  this.add(this.paneldroite, BorderLayout.EAST);


//Panel Bas
  this.panelbas = new JPanel();
  this.cl = new CardLayout();
  this.liste_cl = new String[4];
  this.liste_cl[0] = "Action";
  this.liste_cl[1] = "Sort";
  this.liste_cl[2] = "Cibles";
  this.panelbas.setLayout(this.cl);



  //Panel bas cibles
    this.panelbascibles = new JPanel();
    this.panelbascibles.setLayout(new GridLayout(1, 2));
    JPanel basgauchecibles = new JPanel();
    basgauchecibles.setLayout(new GridBagLayout());
    basgauchecibles.setBackground(gris);
    GridBagConstraints gb = new GridBagConstraints();
    gb.weightx = 0.3;
    gb.weighty = 1;
    gb.anchor = GridBagConstraints.LINE_START; //bottom of space
    JButton retourcibles = new JButton("Retour");
    retourcibles.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        current.resetBoutonCibles();
        current.fenetreN(2);
      }
    });
    retourcibles.setFont(font13);
    retourcibles.setBackground(beige);
    this.label_pbc = new JLabel("      Sur quel ennemi lancez le sort ... ?");
    this.label_pbc.setFont(font15);
    this.label_pbc.setBackground(gris);
    this.label_pbc.setOpaque(true);
    basgauchecibles.add(this.label_pbc, gb);
    basgauchecibles.add(retourcibles, gb);
    this.panelbascibles.add(basgauchecibles);

    JPanel grillec = new JPanel();
    grillec.setLayout(new GridLayout(2, 2));
    CoGestionSorts cgs = new CoGestionSorts(this);
    this.bouton_pbc1 = new JButton(this.ia[0].getClass().getName().substring(11));
    this.bouton_pbc1.addActionListener(cgs);
    this.bouton_pbc2 = new JButton(this.ia[1].getClass().getName().substring(11));
    this.bouton_pbc2.addActionListener(cgs);
    this.bouton_pbc3 = new JButton(this.p[0].getClass().getName().substring(11));
    this.bouton_pbc3.addActionListener(cgs);
    this.bouton_pbc4 = new JButton(this.p[1].getClass().getName().substring(11));
    this.bouton_pbc4.addActionListener(cgs);

    this.bouton_pbc1.setFont(font13);
    this.bouton_pbc2.setFont(font13);
    this.bouton_pbc3.setFont(font13);
    this.bouton_pbc4.setFont(font13);

    this.bouton_pbc1.setPreferredSize(new Dimension(25,50));
    this.bouton_pbc2.setPreferredSize(new Dimension(25,50));
    this.bouton_pbc3.setPreferredSize(new Dimension(25,50));
    this.bouton_pbc4.setPreferredSize(new Dimension(25,50));

    this.bouton_pbc1.setBackground(beige);
    this.bouton_pbc2.setBackground(beige);
    this.bouton_pbc3.setBackground(beige);
    this.bouton_pbc4.setBackground(beige);

    grillec.add(this.bouton_pbc1);
    grillec.add(this.bouton_pbc2);
    grillec.add(this.bouton_pbc3);
    grillec.add(this.bouton_pbc4);
    this.panelbascibles.add(grillec);
    this.panelbascibles.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));




//Panel bas action
  this.panelbasaction = new JPanel();
  this.panelbasaction.setLayout(new GridLayout(1, 2));
  this.label_pba = new JLabel("      Que voulez-vous faire "+this.jeu.getCompte().getLogin()+"? ("+this.p[0].getClass().getName().substring(11)+")");
  this.label_pba.setFont(font15);
  this.label_pba.setBackground(gris);
  this.label_pba.setOpaque(true);
  this.panelbasaction.add(this.label_pba);

  JPanel grille = new JPanel();
  grille.setLayout(new GridLayout(2, 2));
  this.bouton_pba1 = new JButton("Sort");
  CardLayout cl = this.cl;
  JPanel pb = this.panelbas;
  this.bouton_pba1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      current.coBoutonSorts();
    }
  });
  this.bouton_pba2 = new JButton("Description");
  this.bouton_pba2.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      new DescriptionSorts(jeu);
    }
  });

  this.bouton_pba3 = new JButton("Caracteristiques");
  this.cac = new CoAffichageCarac(this.jeu);
  this.bouton_pba3.addActionListener(this.cac);

  this.bouton_pba4 = new JButton("Abandonner");
  this.bouton_pba4.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      new ConfirmAbandon(jeu);
    }
  });

  this.bouton_pba1.setFont(font13);
  this.bouton_pba2.setFont(font13);
  this.bouton_pba3.setFont(font13);
  this.bouton_pba4.setFont(font13);

  this.bouton_pba1.setPreferredSize(new Dimension(25,50));
  this.bouton_pba2.setPreferredSize(new Dimension(25,50));
  this.bouton_pba3.setPreferredSize(new Dimension(25,50));
  this.bouton_pba4.setPreferredSize(new Dimension(25,50));

  this.bouton_pba1.setBackground(mycolor2);
  this.bouton_pba2.setBackground(mycolor2);
  this.bouton_pba3.setBackground(mycolor2);
  this.bouton_pba4.setBackground(mycolor2);

  grille.add(this.bouton_pba1);
  grille.add(this.bouton_pba2);
  grille.add(this.bouton_pba3);
  grille.add(this.bouton_pba4);
  this.panelbasaction.add(grille);
  this.panelbasaction.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));


//Panel Bas Sorts
  this.panelbassort = new JPanel();
  this.panelbassort.setLayout(new GridLayout(1, 2));
  JPanel basgauche = new JPanel();
  basgauche.setLayout(new GridBagLayout());
  basgauche.setBackground(gris);
  gb.weightx = 0.3;
  gb.weighty = 1;
  gb.anchor = GridBagConstraints.LINE_START; //bottom of space
  JButton retour = new JButton("Retour");
  retour.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      if (!current.getContenu().equals(" ")) {
        current.remettreContenuSauvegarde();
      }
      if (current.getAction().equals("consulter") && current.getP() instanceof Sige) {
        current.disableBouton4();
      }
      current.fenetreN(1);
    }
  });
  retour.setFont(font13);
  retour.setBackground(beige);
  this.label_pbs = new JLabel("     Quel sort voulez-vous lancer "+this.jeu.getCompte().getLogin()+"? ("+this.p[0].getClass().getName().substring(11)+")");
  this.label_pbs.setFont(font15);
  this.label_pbs.setBackground(gris);
  this.label_pbs.setOpaque(true);
  basgauche.add(this.label_pbs, gb);
  basgauche.add(retour, gb);
  this.panelbassort.add(basgauche);

  JPanel grille2 = new JPanel();
  grille2.setLayout(new GridLayout(2, 2));
  // this.cgs = new CoGestionSorts(this);
  this.bouton_pbs1 = new JButton(this.p[0].getNomSort(1) + " ("+this.p[0].getCooldown(0)+")");
  this.bouton_pbs1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      current.fenetreSuivante();
      current.setSortChoisis(1);
      current.majLabelCibles();
      current.majBoutonCibles();
    }
  });
  this.bouton_pbs2 = new JButton(this.p[0].getNomSort(2) + " ("+this.p[0].getCooldown(1)+")");
  this.bouton_pbs2.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      current.fenetreSuivante();
      current.setSortChoisis(2);
      current.majLabelCibles();
      current.majBoutonCibles();
    }
  });
  this.bouton_pbs3 = new JButton(this.p[0].getNomSort(3) + " ("+this.p[0].getCooldown(2)+")");
  this.bouton_pbs3.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      current.fenetreSuivante();
      current.setSortChoisis(3);
      current.majLabelCibles();
      current.majBoutonCibles();
    }
  });

  this.bouton_pbs4 = new JButton(this.p[0].getNomSort(4) + " ("+this.p[0].getCooldown(3)+")");
  this.bouton_pbs4.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      current.fenetreSuivante();
      current.setSortChoisis(4);
      current.majLabelCibles();
      current.majBoutonCibles();
    }
  });

  this.bouton_pbs1.setFont(font13);
  this.bouton_pbs2.setFont(font13);
  this.bouton_pbs3.setFont(font13);
  this.bouton_pbs4.setFont(font13);

  this.bouton_pbs1.setPreferredSize(new Dimension(25,50));
  this.bouton_pbs2.setPreferredSize(new Dimension(25,50));
  this.bouton_pbs3.setPreferredSize(new Dimension(25,50));
  this.bouton_pbs4.setPreferredSize(new Dimension(25,50));

  this.bouton_pbs1.setBackground(beige);
  this.bouton_pbs2.setBackground(beige);
  this.bouton_pbs3.setBackground(beige);
  this.bouton_pbs4.setBackground(beige);

  grille2.add(this.bouton_pbs1);
  grille2.add(this.bouton_pbs2);
  grille2.add(this.bouton_pbs3);
  grille2.add(this.bouton_pbs4);

  this.panelbassort.add(grille2);
  this.panelbassort.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));


  Insets insets = panelcentre.getInsets();
  int hCentre = this.jeu.getPreferredSize().height - this.getPanelBasDimension().height;
  int wCentre = this.jeu.getPreferredSize().width - this.getPanelDroiteDimension().width;
  int p1taille = 205;

  this.hFleche = hCentre - 424;
  this.wFleche = wCentre - 416;

  this.labelTour.setBounds(wCentre - 120, -10, size.width, size.height);

  this.placerInfoPanelCentre(insets, 20, this.bdv_ia, this.info_ia, this.effets_ia, bordure1, 1);
  this.placerInfoPanelCentre(insets, 112, this.bdv_ia2, this.info_ia2, this.effets_ia2, bordure2, 2);
  this.placerInfoPanelCentre(insets, hCentre - p1taille - 92, this.bdv_p, this.info_p, this.effets_p, bordure3, 0);
  this.placerInfoPanelCentre(insets, hCentre - p1taille, this.bdv_p2, this.info_p2, this.effets_p2, bordure4, 0);


//Panel Bas
  this.panelbas.add(this.panelbasaction, this.liste_cl[0]);
  this.panelbas.add(this.panelbassort, this.liste_cl[1]);
  this.panelbas.add(this.panelbascibles, this.liste_cl[2]);
  this.add(this.panelbas, BorderLayout.SOUTH);
}

//***************//
//METHODES COMBAT//
//***************//

  public void setSort4Active() {
    this.p[0].setSort4Active(1);
    this.bouton_pba4.setEnabled(true);
  }

  public Personnage getP() {
    return this.p[0];
  }

  public Personnage getIA() {
    return this.ia[0];
  }

  public String getAction() {
    return this.action;
  }

  public void fenetreSuivante() {
    this.cl.next(this.panelbas);
  }

  public void fenetreN(int n) {
    this.cl.show(this.panelbas, this.liste_cl[n-1]);
  }

  public void coBoutonDescription() {
    if (this.getCompteurCoAffichageCarac() % 2 == 1) {//si les carac sont affichées sur le panel droite
      this.setCompteurCoAffichageCarac(this.getCompteurCoAffichageCarac()+1);//on remet le compteur à la normal(retrait affichage)
      this.setText("");
    } else {
      this.sauvegarderEtViderPanelDroite();
    }
    this.action = "consulter";
    this.unableBouton4();
    if (this.p[0].getPossedePassif()) {
      this.bouton_pbs4.setText(this.p[0].getNomSort(4) + " ("+this.p[0].getCooldown(3)+")");
      // this.bouton_pbs4.removeActionListener(this.cgs);
      // this.bouton_pbs4.addActionListener(this.cgs);
    }
    this.fenetreSuivante();
  }

  public void disableBouton4() {
    this.bouton_pbs4.setEnabled(false);
  }

  public void unableBouton4() {
    this.bouton_pbs4.setEnabled(true);
  }

  public void coBoutonSorts() {
    this.miseAJourPBS();
    if (this.getCompteurCoAffichageCarac() % 2 == 1) {//si les carac sont affichées sur le panel droite
      this.setCompteurCoAffichageCarac(this.getCompteurCoAffichageCarac()+1);//on remet le compteur à la normal(retrait affichage)
      this.remettreContenuSauvegarde();
    }
    this.sauvegarderPanelDroite();
    // if (this.p[0].getPossedePassif()) {
    //   if (this.p[0].getCooldMax4() > 0) {
    //     this.unableBouton4();
    //   } else {
    //     this.disableBouton4();
    //     // this.bouton_pbs4.removeActionListener(this.cgs);
    //   }
    // }
    this.action = "lancer";
    this.fenetreSuivante();
  }

  public void sauvegarderEtViderPanelDroite() {
    this.contenu = this.getText();
    this.setText("");
  }

  public void sauvegarderPanelDroite() {
    this.contenu = this.getText();
  }

  public void remettreContenuSauvegarde() {
    this.setText(this.getContenu());
    this.setContenu("");
  }

  public void rafraichirTout() {
    this.fenetreN(1);
    this.miseAJourPBS();
    this.miseAJourPBA();
    this.miseAJourPC();
    this.setText("");
  }

  public Jeu getJeu() {
    return this.jeu;
  }



//**********************//
//METHODES PANEL CENTRAL//
//**********************//
  public BarreDeVie getBarreDeVie(int i) {
    BarreDeVie b = this.bdv_ia;
    if (i == 2) {
      b = this.bdv_p;
    }
    return b;
  }

  public Personnage getJoueur(int i) {
    Personnage j = this.p[0];
    if (i == 2) {
      j = this.ia[0];
    }
    return j;
  }

  public void miseAJourPC() {
    this.placerFleche();
    if (this.p[0].getShield() > 0) {
      this.info_p.setText(this.p[0].getClass().getName().substring(11)+"      "+this.p[0].getPdv()+"/"+this.p[0].getPdvMax()+"   ["+this.p[0].getShield()+"]");
    } else {
      this.info_p.setText(this.p[0].getClass().getName().substring(11)+"      "+this.p[0].getPdv()+"/"+this.p[0].getPdvMax());
    }
    if (this.ia[0].getShield() > 0) {
      this.info_ia.setText(this.ia[0].getClass().getName().substring(11)+"      "+this.ia[0].getPdv()+"/"+this.ia[0].getPdvMax()+"   ["+this.ia[0].getShield()+"]");
    } else {
      this.info_ia.setText(this.ia[0].getClass().getName().substring(11)+"      "+this.ia[0].getPdv()+"/"+this.ia[0].getPdvMax());
    }
    if (this.p[0].getPdv() > 0) {
      this.effets_p.setText(this.p[0].afficherEffet(false));
    } else {
      this.effets_p.setText(" ");
    }
    if (this.ia[0].getPdv() > 0) {
      this.effets_ia.setText(this.ia[0].afficherEffet(true));
    } else {
      this.effets_ia.setText(" ");
    }
    this.bdv_p.setV(this.p[0]);
    this.bdv_ia.setV(this.ia[0]);


    if (this.p[1].getShield() > 0) {
      this.info_p2.setText(this.p[1].getClass().getName().substring(11)+"      "+this.p[1].getPdv()+"/"+this.p[1].getPdvMax()+"   ["+this.p[1].getShield()+"]");
    } else {
      this.info_p2.setText(this.p[1].getClass().getName().substring(11)+"      "+this.p[1].getPdv()+"/"+this.p[1].getPdvMax());
    }
    if (this.ia[1].getShield() > 0) {
      this.info_ia2.setText(this.ia[1].getClass().getName().substring(11)+"      "+this.ia[1].getPdv()+"/"+this.ia[1].getPdvMax()+"   ["+this.ia[1].getShield()+"]");
    } else {
      this.info_ia2.setText(this.ia[1].getClass().getName().substring(11)+"      "+this.ia[1].getPdv()+"/"+this.ia[1].getPdvMax());
    }
    if (this.p[1].getPdv() > 0) {
      this.effets_p2.setText(this.p[1].afficherEffet(false));
    } else {
      this.effets_p2.setText(" ");
    }
    if (this.ia[1].getPdv() > 0) {
      this.effets_ia2.setText(this.ia[1].afficherEffet(true));
    } else {
      this.effets_ia2.setText(" ");
    }
    this.bdv_p2.setV(this.p[1]);
    this.bdv_ia2.setV(this.ia[1]);
  }



//************************//
//METHODES PANEL DE DROTIE//
//************************//

public void tirets() {
  Combat.a_afficher = Combat.a_afficher + "               - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n";
}

  public void afficherCommentaires() {
      this.paneldroite.setText(Combat.a_afficher+this.paneldroite.getText());
      Combat.a_afficher = "";
  }

  public void afficherCarac() {
      this.paneldroite.setText(CombatDuo.a_afficher+this.paneldroite.getText());
      CombatDuo.a_afficher = "";
  }

  public String getContenu() {
    return this.contenu;
  }

  public void setContenu(String contenu) {
    this.contenu = contenu;
  }

  public void setText(String texte) {
    this.paneldroite.setText(texte);
  }

  public String getText() {
    return this.paneldroite.getText();
  }



//******************//
//METHODES PANEL BAS//
//******************//
public int getCompteurCoAffichageCarac() {
  return 0;
  // return this.cac.getCompteur();
}

public void setCompteurCoAffichageCarac(int cmpt) {
  // this.cac.setCompteur(cmpt);
}



//*************************//
//METHODES PANEL BAS ACTION//
//*************************//
public void afficherPassif(Personnage pouia) {
  Font font20 = new Font("Arial", Font.BOLD ,20);
  this.label_pba.setFont(font20);
  this.label_pba.setText("          -Exécution du passif de "+pouia.getClass().getName().substring(11)+"...");
}

public void afficherChargement() {
  this.label_pba.setText("     -Chargement...");
}

public void setAllBoutonPBA(boolean actif) {
  this.bouton_pba1.setEnabled(actif);
  this.bouton_pba2.setEnabled(actif);
  this.bouton_pba3.setEnabled(actif);
  this.bouton_pba4.setEnabled(actif);
}

public void miseAJourPBA() {
  Font font15 = new Font("Arial", Font.BOLD ,15);
  this.label_pba.setFont(font15);
  this.label_pba.setText("      Que voulez-vous faire "+this.jeu.getCompte().getLogin()+"? ("+this.getJoueurCourant().getClass().getName().substring(11)+")");
}



//***********************//
//METHODES PANEL BAS SORT//
//***********************//
public void miseAJourPBS() {
  Color beige = Color.decode("#C3C1BF");
  Color beigePlusFonce = Color.decode("#866F6F");
  Personnage p = this.getJoueurCourant();
  this.label_pbs.setText("     Quel sort voulez-vous lancez "+this.jeu.getCompte().getLogin()+"? ("+p.getClass().getName().substring(11)+")");
  this.bouton_pbs1.setText(p.getNomSort(1) + " ("+p.getCooldown(0)+")");
  this.bouton_pbs2.setText(p.getNomSort(2) + " ("+p.getCooldown(1)+")");
  this.bouton_pbs3.setText(p.getNomSort(3) + " ("+p.getCooldown(2)+")");
  this.bouton_pbs4.setText(p.getNomSort(4) + " ("+p.getCooldown(3)+")");
  if (p.getCooldown(0) > 0) {
    this.bouton_pbs1.setEnabled(false);
    this.bouton_pbs1.setBackground(beigePlusFonce);
  } else {
    this.bouton_pbs1.setEnabled(true);
    this.bouton_pbs1.setBackground(beige);
  }
  if (p.getCooldown(1) > 0) {
    this.bouton_pbs2.setEnabled(false);
    this.bouton_pbs2.setBackground(beigePlusFonce);
  } else {
    this.bouton_pbs2.setEnabled(true);
    this.bouton_pbs2.setBackground(beige);
  }
  if (p.getCooldown(2) > 0) {
    this.bouton_pbs3.setEnabled(false);
    this.bouton_pbs3.setBackground(beigePlusFonce);
  } else {
    this.bouton_pbs3.setEnabled(true);
    this.bouton_pbs3.setBackground(beige);
  }
  if (p.getCooldown(3) > 0) {
    this.bouton_pbs4.setEnabled(false);
    this.bouton_pbs4.setBackground(beigePlusFonce);
  } else {
    this.bouton_pbs4.setEnabled(true);
    this.bouton_pbs4.setBackground(beige);
  }
  if (!p.getPossedePassif()) {
    if (p.getCooldown(3) > 0) {
      this.bouton_pbs4.setEnabled(false);
      this.bouton_pbs4.setBackground(beigePlusFonce);
    } else {
      this.bouton_pbs4.setEnabled(true);
      this.bouton_pbs4.setBackground(beige);
    }
  if (p instanceof Sige) {
    if (p.getSort4Active() == 0) {
      this.bouton_pbs4.setEnabled(false);
    } else {
      this.bouton_pbs4.setEnabled(true);
    }
  }
  } else if (p.getPossedePassif()) {
    this.bouton_pbs4.setEnabled(false);
    this.bouton_pbs4.setBackground(beige);
  }
}

public Personnage getJoueurCourant() {
  if (this.joueurCourant > 2) {
    return this.ia[((this.joueurCourant+1) % 2)];
  } else {
    return this.p[this.joueurCourant-1];
  }
}

public int getIntJoueurCourant() {
  return this.joueurCourant;
}


public Personnage getPersoCible() {
  if (this.cible > 2) {
    return this.ia[(this.cible - 3)];
  } else {
    return this.p[this.cible-1];
  }
}

public void placerFleche() {
  Dimension size = this.fleche.getPreferredSize();
  int wCentre = this.jeu.getPreferredSize().width - this.getPanelDroiteDimension().width;
  int hCentre = this.jeu.getPreferredSize().height - this.getPanelBasDimension().height;
  this.fleche.setI(this.joueurCourant);

  // this.fleche.repaint();
  if (this.joueurCourant == 3) {
    this.fleche.setBounds(285, -16, size.width, size.height);
  } else if (this.joueurCourant == 4) {
    this.fleche.setBounds(285, 77, size.width, size.height);
  } else if (this.joueurCourant == 1) {
    this.fleche.setBounds(this.wFleche, this.hFleche + 92, size.width, size.height);
  } else if (this.joueurCourant == 2) {
    this.fleche.setBounds(this.wFleche, this.hFleche + 184, size.width, size.height);
  }
}

public Personnage[] getEnnemisCible() {
  if (this.cible > 2) {
    return this.p;
  } else {
    return this.ia;
  }
}

public Personnage[] getEnnemisLanceur() {
  Personnage[] res = new Personnage[2];
  if (this.joueurCourant > 2) {//si ia
    if (this.cible > 2) {//si cible est une ia
      res = this.p;
    } else {
      res[0] = this.getPersoCible();
      res[1] = this.getAlliePersoCible();
    }
  } else {//si joueur
    if (this.cible <= 2) {//si cible est un joueur
      res = this.ia;
    } else {
      res[0] = this.getPersoCible();
      res[1] = this.getAlliePersoCible();
    }
  }
  return res;
}

public Personnage[] getEnnemisLanceurSansOrdre() {
  Personnage[] res = new Personnage[2];
  if (this.joueurCourant > 2) {//si ia
    res = this.p;
  } else {//si joueur
    res = this.ia;
  }
  return res;
}

public Personnage getAlliePersoCible() {
  if (this.cible > 2) {
    if (this.cible - 3 == 1) {
      return this.ia[0];
    } else {
      return this.ia[1];
    }
  } else {
    if (this.cible == 2) {
      return this.p[0];
    } else {
      return this.p[1];
    }
  }
}

public Personnage getAllieLanceur() {
  if (this.joueurCourant == 1) {
    return this.p[1];
  } else if (this.joueurCourant == 2) {
    return this.p[0];
  } else if (this.joueurCourant == 3) {
    return this.ia[1];
  } else if (this.joueurCourant == 4) {
    return this.ia[0];
  }
  return null;
}

//static methods
public static int getEtage() {
  return CombatDuo.etage;
}

public int getCible() {
  return this.cible;
}

public static void setEtage(int etage) {
  CombatDuo.etage = etage;
}

public void setSortChoisis(int sort) {
  this.sortChoisis = sort;
}

public Personnage getP(int i) {
  return this.p[i];
}

public Personnage getIa(int i) {
  return this.ia[i];
}

public void changerIa(Personnage ia1, Personnage ia2) {
  this.resetTour();
  this.ia[0] = ia1;
  this.ia[1] = ia2;
  this.setJoueurCourant();
  this.miseAJourPBA();
  this.miseAJourPC();
}

public void init(int persoChoisis1, int persoChoisis2, Personnage ia1, Personnage ia2) {
  this.p[0] = this.jeu.getCompte().getPerso(persoChoisis1);
  this.p[1] = this.jeu.getCompte().getPerso(persoChoisis2);
  this.changerIa(ia1, ia2);
}

public int getSortChoisis() {
  return this.sortChoisis;
}

public void setJoueurCourant() {
  int vitesseMax = (!this.aJoue[0] && this.p[0].getPdv() > 0) ? this.p[0].getVitesse() : 0;
  this.joueurCourant = (!this.aJoue[0] && this.p[0].getPdv() > 0) ? 1 : 0;
  if (this.p[1].getVitesse() > vitesseMax && !this.aJoue[1] &&  this.p[1].getPdv() > 0) {
    vitesseMax = this.p[1].getVitesse();
    this.joueurCourant = 2;
  }
  if (this.ia[0].getVitesse() > vitesseMax && !this.aJoue[2] &&  this.ia[0].getPdv() > 0) {
    vitesseMax = this.ia[0].getVitesse();
    this.joueurCourant = 3;
  }
  if (this.ia[1].getVitesse() > vitesseMax && !this.aJoue[3] &&  this.ia[1].getPdv() > 0) {
    vitesseMax = this.ia[1].getVitesse();
    this.joueurCourant = 4;
  }
  if (this.joueurCourant == 0) {
    this.resetAjoue();
    this.tourPlusPlus();
    this.setJoueurCourant();
  } else {
    this.aJoue[this.joueurCourant-1] = true;
  }
}

public void rejouer(Personnage p, boolean ia) {
  int posP = 0;
  if (ia) {
    if (this.ia[0].getClass().equals(p.getClass())) {
      posP = 2;
    } else {
      posP = 3;
    }
  } else {
    if (this.p[1].getClass().equals(p.getClass())) {
      posP = 1;
    }
  }
  this.aJoue[posP] = false;
}

public void afficherAJoue() {
  for (int i = 0; i<4; i++) {
    System.out.println(this.aJoue[i]);
  }
}

public void tourPlusPlus() {
  this.tour++;
  this.labelTour.setText("Tour n°"+this.tour);
}

public void resetTour() {
  this.tour = 1;
  this.labelTour.setText("Tour n°"+this.tour);
}

public void majLabelCibles() {
  this.label_pbc.setText("      Sur quel ennemi lancez le sort "+this.getJoueurCourant().getNomSort(this.sortChoisis)+" ?");
}

public void resetBoutonCibles() {
  this.bouton_pbc3.setEnabled(true);
  this.bouton_pbc4.setEnabled(true);
  this.bouton_pbc1.setEnabled(true);
  this.bouton_pbc2.setEnabled(true);
}

public int jbuttonToInt(JButton b) {
  int res = 1;
  if (b.equals(this.bouton_pbc4)) {
    res = 2;
  } else if (b.equals(this.bouton_pbc1)) {
    res = 3;
  } else if (b.equals(this.bouton_pbc2)) {
    res = 4;
  }
  return res;
}

public void placerInfoPanelCentre(Insets insets, int topInfo, BarreDeVie bdv, JLabel info, JLabel effets, JPanel bordure, int ennemi) {
  int wCentre = this.jeu.getPreferredSize().width - this.getPanelDroiteDimension().width;
  int leftPadding = (ennemi == 1 || ennemi == 2) ? 25 : wCentre - 295;
  int leftPaddingEffet = (ennemi == 1 || ennemi == 2) ? 25 : wCentre - 1042;


  Dimension size = bdv.getPreferredSize();
  bdv.setBounds(leftPadding + insets.left, topInfo + 25 + insets.top, size.width, size.height);

  size = info.getPreferredSize();
  info.setBounds(leftPadding + insets.left, topInfo + insets.top, size.width, size.height);

  size = effets.getPreferredSize();
  effets.setBounds(leftPaddingEffet + insets.left, topInfo + 53 + insets.top, size.width, size.height);

  size = bordure.getPreferredSize();
  bordure.setBounds(leftPadding - 8 + insets.left, topInfo - 5 + insets.top, size.width, size.height);
}

public void setCible(int cible) {
  this.cible = cible;
}

public void resetAjoue() {
  for (int i = 0; i<4; i++) {
    this.aJoue[i] = false;
  }
}

public void majBoutonCibles() {
  this.bouton_pbc1.setText(this.ia[0].getClass().getName().substring(11));
  this.bouton_pbc2.setText(this.ia[1].getClass().getName().substring(11));
  this.bouton_pbc3.setText(this.p[0].getClass().getName().substring(11));
  this.bouton_pbc4.setText(this.p[1].getClass().getName().substring(11));
  this.resetBoutonCibles();
  int typeSortChoisis = this.getJoueurCourant().getTypeSort(this.sortChoisis);
  if (typeSortChoisis == 1 || typeSortChoisis == 2) {
    if (typeSortChoisis == 1) {
    }
    this.bouton_pbc3.setEnabled(false);
    this.bouton_pbc4.setEnabled(false);
    if (this.ia[0].getPdv() == 0) {
      this.bouton_pbc1.setEnabled(false);
    } else if (this.ia[1].getPdv() == 0) {
      this.bouton_pbc2.setEnabled(false);
    }
  } else if (typeSortChoisis == 5) {
    this.bouton_pbc1.setEnabled(false);
    this.bouton_pbc2.setEnabled(false);
    this.bouton_pbc3.setEnabled(false);
    this.bouton_pbc4.setEnabled(false);
    switch (this.joueurCourant) {
      case 1:this.bouton_pbc3.setEnabled(true);break;
      case 2:this.bouton_pbc4.setEnabled(true);break;
      case 3:this.bouton_pbc1.setEnabled(true);break;
      case 4:this.bouton_pbc2.setEnabled(true);break;
      default:System.out.println("Erreur majBoutonCibles");break;
    }
  } else if (typeSortChoisis == 6) {
    this.bouton_pbc1.setEnabled(false);
    this.bouton_pbc2.setEnabled(false);
    this.bouton_pbc3.setEnabled(false);
    this.bouton_pbc4.setEnabled(false);
    switch (this.joueurCourant) {
      case 1:this.bouton_pbc4.setEnabled(true);break;
      case 2:this.bouton_pbc3.setEnabled(true);break;
      case 3:this.bouton_pbc2.setEnabled(true);break;
      case 4:this.bouton_pbc1.setEnabled(true);break;
      default:System.out.println("Erreur majBoutonCibles");break;
    }
  } else {
    this.bouton_pbc1.setEnabled(false);
    this.bouton_pbc2.setEnabled(false);
    if (this.p[0].getPdv() == 0) {
      this.bouton_pbc3.setEnabled(false);
    } else if (this.p[1].getPdv() == 0) {
      this.bouton_pbc4.setEnabled(false);
    }
  }
}



public Dimension getPanelDroiteDimension() {
  return this.getLayout().preferredLayoutSize(this.paneldroite);
}

public Dimension getPanelBasDimension() {
  return this.getLayout().preferredLayoutSize(this.panelbas);
}

}
