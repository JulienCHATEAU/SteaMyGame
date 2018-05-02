package application;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import application.*;
import personnage.*;
import runes.*;
import comptes.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Combat extends JPanel {

  private Jeu jeu;
  private Personnage p;
  private Personnage ia;
  private String action;
  private CoGestionSortsSolo cgs;
  public static int etage = 0;


  public Dimension getPanelDroiteDimension() {
    return this.getLayout().preferredLayoutSize(this.paneldroite);
  }

  public Dimension getPanelBasDimension() {
    return this.getLayout().preferredLayoutSize(this.panelbas);
  }


  public static int getEtage() {
    return Combat.etage;
  }

  public static void setEtage(int etage) {
    Combat.etage = etage;
  }

  private JPanel panelcentre;
  private int tour;
  private JLabel labelTour;
  private JLabel info_ia;
  private JLabel effets_ia;
  private JLabel info_p;
  private JLabel effets_p;
  private BarreDeVie bdv_ia;
  private BarreDeVie bdv_p;

  private JTextArea paneldroite;
  public static String a_afficher;
  public String contenu;

  private JPanel panelbas;
  private CardLayout cl;
  private String[] liste_cl;
  private CoAffichageCarac cac;

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

public Combat(Jeu jeu) {
  super();
//Initialisation de Combat//
  this.jeu = jeu;
  this.tour = 1;
  this.p = new Kaito("");
  this.ia = new Kaito("");
  this.setLayout(new BorderLayout());

//Initialsation variables locales//
  Color gris = Color.decode("#F2E5CF");
  Color beige = Color.decode("#C3C1BF");
  Color mycolor2 = Color.decode("#B0AFAA");
  Font font15 = new Font("Arial",Font.BOLD,15);
  Font font14 = new Font("Arial",Font.BOLD,14);
  Font font13 = new Font("Arial",Font.BOLD,13);
  Combat current = this;

//Panel Central//
  this.panelcentre = new JPanel();
  panelcentre.setLayout(null);
  panelcentre.setBackground(beige);

  this.labelTour = new JLabel("Tour n°1000");
  this.labelTour.setFont(font15);

  this.info_ia = new JLabel("                                                           ");
  this.effets_ia = new JLabel();
  this.effets_ia.setPreferredSize(new Dimension(1000, 15));
  this.info_p = new JLabel("                                                           ");
  this.effets_p = new JLabel();
  this.effets_p.setPreferredSize(new Dimension(1000, 15));


  this.info_ia.setFont(font14);
  this.effets_ia.setFont(font15);
  this.info_p.setFont(font14);
  this.effets_p.setFont(font15);
  this.effets_p.setHorizontalAlignment(SwingConstants.TRAILING);

  this.bdv_ia = new BarreDeVie();
  this.bdv_ia.setPreferredSize(new Dimension(250,15));
  this.bdv_p = new BarreDeVie();
  this.bdv_p.setPreferredSize(new Dimension(250,15));

  JPanel bordure1 = new JPanel();
  bordure1.setPreferredSize(new Dimension(270,55));
  bordure1.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.BLACK));
  // bordure1.setOpaque(false);
  bordure1.setBackground(Color.decode("#F4F4EE"));
  JPanel bordure3 = new JPanel();
  bordure3.setPreferredSize(new Dimension(270,55));
  bordure3.setBorder(BorderFactory.createMatteBorder(2, 1, 1, 2, Color.BLACK));
  // bordure3.setOpaque(false);
  bordure3.setBackground(Color.decode("#F4F4EE"));


  panelcentre.add(this.labelTour);

  panelcentre.add(this.info_ia);
  panelcentre.add(this.bdv_ia);
  panelcentre.add(bordure1);
  panelcentre.add(this.effets_ia);



  panelcentre.add(this.info_p);
  panelcentre.add(this.bdv_p);
  panelcentre.add(bordure3);
  panelcentre.add(this.effets_p);

  panelcentre.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

  this.add(panelcentre, BorderLayout.CENTER);


//Panel Bas
  this.panelbas = new JPanel();
  this.cl = new CardLayout();
  this.liste_cl = new String[4];
  this.liste_cl[0] = "Action";
  this.liste_cl[1] = "Sort";
  this.panelbas.setLayout(this.cl);


//Panel de Droite
  this.paneldroite = new JTextArea();
  this.contenu = "";
  this.a_afficher = "";
  paneldroite.setFont(font15);
  paneldroite.setColumns(35);
  paneldroite.setEditable(false);
  paneldroite.setBackground(gris);
  this.add(this.paneldroite, BorderLayout.EAST);


//Panel bas action
  this.panelbasaction = new JPanel();
  this.panelbasaction.setLayout(new GridLayout(1, 2));
  this.label_pba = new JLabel("      Que voulez vous faire "+this.jeu.getCompte().getLogin()+"? ("+this.p.getClass().getName().substring(11)+")");
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
  GridBagConstraints gb = new GridBagConstraints();
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
      cl.next(pb);
    }
  });
  retour.setFont(font13);
  retour.setBackground(beige);
  this.label_pbs = new JLabel("     Quel sort voulez vous lancer "+this.jeu.getCompte().getLogin()+"? ("+this.p.getClass().getName().substring(11)+")");
  this.label_pbs.setFont(font15);
  this.label_pbs.setBackground(gris);
  this.label_pbs.setOpaque(true);
  basgauche.add(this.label_pbs, gb);
  basgauche.add(retour, gb);
  this.panelbassort.add(basgauche);

  JPanel grille2 = new JPanel();
  grille2.setLayout(new GridLayout(2, 2));
  this.cgs = new CoGestionSortsSolo(this);
  this.bouton_pbs1 = new JButton(this.p.getNomSort(1) + " ("+this.p.getCooldown(0)+")");
  this.bouton_pbs1.addActionListener(this.cgs);
  this.bouton_pbs2 = new JButton(this.p.getNomSort(2) + " ("+this.p.getCooldown(1)+")");
  this.bouton_pbs2.addActionListener(this.cgs);
  this.bouton_pbs3 = new JButton(this.p.getNomSort(3) + " ("+this.p.getCooldown(2)+")");
  this.bouton_pbs3.addActionListener(this.cgs);
  this.bouton_pbs4 = new JButton(this.p.getNomSort(4) + " ("+this.p.getCooldown(3)+")");
  this.bouton_pbs4.addActionListener(this.cgs);

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
  Dimension size = this.labelTour.getPreferredSize();

  this.labelTour.setBounds(wCentre - 120, 20, size.width, size.height);

  this.placerInfoPanelCentre(insets, 20, this.bdv_ia, this.info_ia, this.effets_ia, bordure1, 1);
  this.placerInfoPanelCentre(insets, hCentre - 220, this.bdv_p, this.info_p, this.effets_p, bordure3, 0);


//Panel Bas
  this.panelbas.add(this.panelbasaction, this.liste_cl[0]);
  this.panelbas.add(this.panelbassort, this.liste_cl[1]);
  this.add(this.panelbas, BorderLayout.SOUTH);
}

//***************//
//METHODES COMBAT//
//***************//

  public void setSort4Active() {
    this.p.setSort4Active(1);
    this.bouton_pba4.setEnabled(true);
  }


  public void setP(Personnage p) {
    this.p = p;
    if (!this.p.getPossedePassif()) {
      this.bouton_pbs4.setText(this.p.getNomSort(4) + " ("+this.p.getCooldown(3)+")");
      // this.bouton_pbs4.removeActionListener(this.cgs);
      // this.bouton_pbs4.addActionListener(this.cgs);
    }
    this.miseAJourLabel(this.p, this.ia);
    this.miseAJourPBA(this.p);
    this.miseAJourPBS(this.p);
  }

  public Personnage getP() {
    return this.p;
  }

  public void setIA(Personnage ia) {
    this.ia = ia;
    this.miseAJourLabel(this.p, this.ia);
  }

  public Personnage getIA() {
    return this.ia;
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
    this.action = "consulter";
    this.unableBouton4();
    if (this.p.getPossedePassif()) {
      this.bouton_pbs4.setText(this.p.getNomSort(4) + " ("+this.p.getCooldown(3)+")");
      // this.bouton_pbs4.removeActionListener(this.cgs);
      // this.bouton_pbs4.addActionListener(this.cgs);
    }
    this.fenetreSuivante();
  }

  public void unableAllButonPBA(boolean unable) {
    this.bouton_pba1.setEnabled(unable);
    this.bouton_pba2.setEnabled(unable);
    this.bouton_pba3.setEnabled(unable);
    this.bouton_pba4.setEnabled(unable);
  }

  public void disableBouton4() {
    this.bouton_pbs4.setEnabled(false);
  }

  public void unableBouton4() {
    this.bouton_pbs4.setEnabled(true);
  }

  public void coBoutonSorts() {
    if (this.p.getPossedePassif()) {
      this.disableBouton4();
    }
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
    this.miseAJourPBS(this.p);
    this.miseAJourPBA(this.p);
    this.miseAJourLabel(this.p, this.ia);
    this.setText("");
  }

  public Jeu getJeu() {
    return this.jeu;
  }



//**********************//
//METHODES PANEL CENTRAL//
//**********************//

  public void tourPlusPlus() {
    this.tour++;
    this.labelTour.setText("Tour n°"+this.tour);
  }

  public void resetTour() {
    this.tour = 1;
    this.labelTour.setText("Tour n°"+this.tour);
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
    effets.setBounds(leftPaddingEffet + insets.left, topInfo + 65 + insets.top, size.width, size.height);

    size = bordure.getPreferredSize();
    bordure.setBounds(leftPadding - 8 + insets.left, topInfo - 5 + insets.top, size.width, size.height);
  }


  public BarreDeVie getBarreDeVie(int i) {
    BarreDeVie b = this.bdv_ia;
    if (i == 2) {
      b = this.bdv_p;
    }
    return b;
  }

  public Personnage getJoueur(int i) {
    Personnage j = this.p;
    if (i == 2) {
      j = this.ia;
    }
    return j;
  }

  public void miseAJourLabel(Personnage p, Personnage ia) {
    this.p = p;
    this.ia = ia;
    if (this.p.getShield() > 0) {
      this.info_p.setText("["+this.p.getShield()+"]   "+this.p.getClass().getName().substring(11)+"      "+this.p.getPdv()+"/"+this.p.getPdvMax());
    } else {
      this.info_p.setText(this.p.getClass().getName().substring(11)+"      "+this.p.getPdv()+"/"+this.p.getPdvMax());
    }
    if (this.ia.getShield() > 0) {
      this.info_ia.setText(this.ia.getClass().getName().substring(11)+"      "+this.ia.getPdv()+"/"+this.ia.getPdvMax()+"   ["+this.ia.getShield()+"]");
    } else {
      this.info_ia.setText(this.ia.getClass().getName().substring(11)+"      "+this.ia.getPdv()+"/"+this.ia.getPdvMax());
    }
    this.effets_p.setText(this.p.afficherEffet(true));
    this.effets_ia.setText(this.ia.afficherEffet(false));
    this.bdv_p.setV(this.p);
    this.bdv_ia.setV(this.ia);
  }



//************************//
//METHODES PANEL DE DROITE//
//************************//
  public static void ajouterCommentaire(String commentaire) {
    int taille = 50;
    String string = "";
    int cut = 0;
    String vide = "";
    if (commentaire.length() > taille) {
      cut = commentaire.length() / taille;
    }
    // string += "\n";
    if (cut == 0) {
      string += "      "+commentaire+"\n";
    } else {
      for (int i = 0; i<cut+1; i++) {
        if (i == cut) {
          if (commentaire.substring(taille*i, commentaire.length()).substring(0, 1).equals(" ")) {
            string += "      "+commentaire.substring(taille*i+1, commentaire.length())+"\n";
          } else {
            string += "      "+commentaire.substring(taille*i, commentaire.length())+"\n";
          }
        } else {
          if (commentaire.substring(taille*i, taille*(i+1)).substring(0, 1).equals(" ")) {
            string += "      "+commentaire.substring(taille*i+1, taille*(i+1))+"\n";
          } else {
            string += "      "+commentaire.substring(taille*i, taille*(i+1))+"\n";
          }
        }
      }
    }
    Combat.a_afficher = Combat.a_afficher + string;
  }

  public void tirets() {
    Combat.a_afficher = Combat.a_afficher + "               - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n";
  }

  public void afficherCommentaires() {
      this.paneldroite.setText(Combat.a_afficher+this.paneldroite.getText());
      Combat.a_afficher = "";
  }

  public void afficherCarac() {
      this.paneldroite.setText(Combat.a_afficher+this.paneldroite.getText());
      Combat.a_afficher = "";
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

public void miseAJourPBA(Personnage p) {
  this.p = p;
  Font font15 = new Font("Arial", Font.BOLD ,15);
  this.label_pba.setFont(font15);
  this.label_pba.setText("      Que voulez vous faire "+this.jeu.getCompte().getLogin()+"? ("+this.p.getClass().getName().substring(11)+")");
}



//***********************//
//METHODES PANEL BAS SORT//
//***********************//
public void miseAJourPBS(Personnage p) {
  this.p = p;
  Color beige = Color.decode("#C3C1BF");
  Color beigePlusFonce = Color.decode("#866F6F");
  this.label_pbs.setText("     Quel sort voulez vous lancez "+this.jeu.getCompte().getLogin()+"? ("+this.p.getClass().getName().substring(11)+")");
  this.bouton_pbs1.setText(this.p.getNomSort(1) + " ("+this.p.getCooldown(0)+")");
  this.bouton_pbs2.setText(this.p.getNomSort(2) + " ("+this.p.getCooldown(1)+")");
  this.bouton_pbs3.setText(this.p.getNomSort(3) + " ("+this.p.getCooldown(2)+")");
  this.bouton_pbs4.setText(this.p.getNomSort(4) + " ("+this.p.getCooldown(3)+")");
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

}
