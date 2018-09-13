package application;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import application.*;
import personnage.*;
import runes.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inventaire extends JPanel {

  private Jeu jeu;
  private Personnage perso;

  private JComboBox<Integer> etage;
  private JList<String> listePerso;

  private JLabel nomPerso;
  private JLabel hp;
  private JLabel attaque;
  private JLabel defense;
  private JLabel vitesse;
  private JLabel tx_crit;
  private JLabel dgts_crit;
  private JLabel argentl;

  private IhmRune rune0;
  private IhmRune rune1;
  private IhmRune rune2;
  private IhmRune rune3;
  private IhmRune rune4;

  private JPanel toutes;
  private JPanel fatale;
  private JPanel swift;
  private JPanel gardien;
  private JPanel energie;
  private JPanel lame;
  private JPanel rage;

  public Inventaire(Jeu jeu) {
    Inventaire current = this;
    this.jeu = jeu;
    this.perso = this.jeu.getCompte().getPerso(0);
    this.setLayout(new BorderLayout());
    this.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.decode("#C3C1BF")));
    this.setBackground(Color.decode("#F2E5CF"));
    Font font14 = new Font("Arial", Font.BOLD , 14);
    Font font18 = new Font("Arial", Font.BOLD , 18);
    Font font23 = new Font("Arial", Font.BOLD , 23);

//Construction panel de gauche :
    JPanel gauche = new JPanel();
    gauche.setLayout(new BorderLayout());

    JPanel pseudo_sorts = new JPanel();
    pseudo_sorts.setBackground(Color.decode("#F2E5CF"));
    pseudo_sorts.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, Color.decode("#C3C1BF")));
    this.listePerso = new JList<String>(this.getListePersoInString());
    this.listePerso.setBackground(Color.decode("#F2E5CF"));
    this.listePerso.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.decode("#C3C1BF")));
    this.listePerso.setSelectedIndex(0);
    CoChangerPersoInventaire ccpi = new CoChangerPersoInventaire(this.jeu);
    this.listePerso.addListSelectionListener(ccpi);
    // JLabel pseudo = new JLabel(this.jeu.getCompte().getLogin()+"      ");
    // pseudo.setFont(font14);
    JButton sorts = new JButton("Sorts");
    sorts.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new DescriptionSorts(jeu);
      }
    });
    sorts.setBackground(Color.decode("#C3C1BF"));
    // pseudo_sorts.add(pseudo);
    pseudo_sorts.add(this.listePerso);
    pseudo_sorts.add(sorts);
    gauche.add(pseudo_sorts, BorderLayout.NORTH);

    JPanel carac = new JPanel();
    carac.setBackground(Color.decode("#F2E5CF"));
    carac.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2, Color.decode("#C3C1BF")));
    carac.setLayout(new BoxLayout(carac, BoxLayout.Y_AXIS));
    int hpbonus = this.perso.getStatsSuppl(3);
    int attbonus = this.perso.getStatsSuppl(0);
    int defbonus = this.perso.getStatsSuppl(2);
    int vitbonus = this.perso.getStatsSuppl(1);
    int txcrbonus = this.perso.getStatsSuppl(4);
    int dgcrbonus = this.perso.getStatsSuppl(5);
    this.hp = new JLabel("    Hp :                "+this.perso.getPdv()+" (+"+hpbonus+")");
    this.attaque = new JLabel("   Attaque :       "+this.perso.getAttaque()+" (+"+attbonus+")");
    this.defense = new JLabel("   Défense :      "+this.perso.getDefense()+" (+"+defbonus+")");
    this.vitesse = new JLabel("   Vitesse :        "+this.perso.getVitesse()+" (+"+vitbonus+")");
    this.tx_crit = new JLabel("   Taux Crit. :     15% (+"+txcrbonus+"%)");
    this.dgts_crit = new JLabel("   Dégats Crit. : 25% (+"+dgcrbonus+"%)");
    carac.add(new JLabel(" "));
    this.argentl = new JLabel("    Argent : "+this.jeu.getCompte().getArgent()+" $teamy");
    // carac.add(this.argentl);
    // carac.add(new JLabel(" "));
    String space = "";
    int tailleSpace = 10 - (this.jeu.getCompte().getLogin().length()/2);
    for (int i = 0; i<tailleSpace; i++) {
      space += " ";
    }
    JLabel pseudo = new JLabel(space + this.jeu.getCompte().getLogin());
    pseudo.setFont(font23);
    carac.add(pseudo);
    carac.add(new JLabel(" "));
    JLabel titre = new JLabel("   "+"Caractéristiques"+"                 ");
    carac.add(titre);
    carac.add(new JLabel(" "));
    this.nomPerso = new JLabel("    "+this.perso.getClass().getName().substring(11)+" : ");
    carac.add(this.nomPerso);
    carac.add(new JLabel(" "));
    carac.add(this.hp);
    carac.add(this.attaque);
    carac.add(this.defense);
    carac.add(this.vitesse);
    carac.add(new JLabel(" "));
    carac.add(this.tx_crit);
    carac.add(this.dgts_crit);
    carac.add(new JLabel(" "));
    carac.add(new JLabel(" "));
    carac.add(new JLabel(" "));
    carac.add(this.argentl);
    titre.setFont(font14);
    this.argentl.setFont(font14);
    this.nomPerso.setFont(font14);
    this.hp.setFont(font14);
    this.attaque.setFont(font14);
    this.defense.setFont(font14);
    this.vitesse.setFont(font14);
    this.tx_crit.setFont(font14);
    this.dgts_crit.setFont(font14);
    gauche.add(carac, BorderLayout.CENTER);

    this.add(gauche, BorderLayout.WEST);


//Construction panel du bas
    JTabbedPane jtp = new JTabbedPane();
    jtp.setPreferredSize(new Dimension(150, 120));
    jtp.setBackground(Color.decode("#F2E5CF"));

    UIManager.put("TabbedPane.selected", Color.decode("#C3C1BF"));
    SwingUtilities.updateComponentTreeUI(jtp);


    int it = 0;

    //TOUTES
        this.toutes = new JPanel();
        // toutes.setLayout(new BoxLayout(toutes, BoxLayout.X_AXIS));
        this.toutes.setLayout(new GridLayout(1, 100));
        for (Rune r : this.jeu.getCompte().getInventaire()) {
          it++;
          this.toutes.add(new IhmRuneDeux(new IhmRune(r, this.jeu, "S'équiper", this)));
        }
        for (int j = 0; j<9-it; it++) {
          this.toutes.add(new IhmRuneDeux(new IhmRune(null, this.jeu, "S'équiper", this)));
        }
        JScrollPane toutes1 = new JScrollPane(toutes);
        toutes1.setBackground(Color.decode("#F2E5CF"));

//FATALE
    this.fatale = new JPanel();
    it = 0;
    // fatale.setLayout(new BoxLayout(fatale, BoxLayout.X_AXIS));
    this.fatale.setLayout(new GridLayout(1, 100));
    for (Rune r : this.jeu.getCompte().getInventaire()) {
      if (r instanceof Fatale) {
        it++;
        this.fatale.add(new IhmRuneDeux(new IhmRune(r, this.jeu, "S'équiper", this)));
      }
    }
    for (int j = 0; j<9-it; it++) {
      this.fatale.add(new IhmRuneDeux(new IhmRune(null, this.jeu, "S'équiper", this)));
    }
    JScrollPane fatale1 = new JScrollPane(fatale);
    fatale1.setBackground(Color.decode("#F2E5CF"));

//SWIFT
    this.swift = new JPanel();
    it = 0;
    // swift.setLayout(new BoxLayout(swift, BoxLayout.X_AXIS));
    this.swift.setLayout(new GridLayout(1, 100));
    for (Rune r : this.jeu.getCompte().getInventaire()) {
      if (r instanceof Swift) {
        it++;
        this.swift.add(new IhmRuneDeux(new IhmRune(r, this.jeu, "S'équiper", this)));
      }
    }
    for (int j = 0; j<9-it; it++) {
      this.swift.add(new IhmRuneDeux(new IhmRune(null, this.jeu, "S'équiper", this)));
    }
    JScrollPane swift1 = new JScrollPane(swift);
    swift1.setBackground(Color.decode("#F2E5CF"));

//GARDIEN
    this.gardien = new JPanel();
    it = 0;
    // gardien.setLayout(new BoxLayout(gardien, BoxLayout.X_AXIS));
    this.gardien.setLayout(new GridLayout(1, 100));
    for (Rune r : this.jeu.getCompte().getInventaire()) {
      if (r instanceof Gardien) {
        it++;
        this.gardien.add(new IhmRuneDeux(new IhmRune(r, this.jeu, "S'équiper", this)));
      }
    }
    for (int j = 0; j<9-it; it++) {
      this.gardien.add(new IhmRuneDeux(new IhmRune(null, this.jeu, "S'équiper", this)));
    }
    JScrollPane gardien1 = new JScrollPane(gardien);
    gardien1.setBackground(Color.decode("#F2E5CF"));

//ENERGIE
    this.energie = new JPanel();
    it = 0;
    // energie.setLayout(new BoxLayout(energie, BoxLayout.X_AXIS));
    this.energie.setLayout(new GridLayout(1, 100));
    for (Rune r : this.jeu.getCompte().getInventaire()) {
      if (r instanceof Energie) {
        it++;
        this.energie.add(new IhmRuneDeux(new IhmRune(r, this.jeu, "S'équiper", this)));
      }
    }
    for (int j = 0; j<9-it; it++) {
      this.energie.add(new IhmRuneDeux(new IhmRune(null, this.jeu, "S'équiper", this)));
    }
    JScrollPane energie1 = new JScrollPane(energie);
    energie1.setBackground(Color.decode("#F2E5CF"));

//LAME
    this.lame = new JPanel();
    it = 0;
    // lame.setLayout(new BoxLayout(lame, BoxLayout.X_AXIS));
    this.lame.setLayout(new GridLayout(1, 100));
    for (Rune r : this.jeu.getCompte().getInventaire()) {
      if (r instanceof Lame) {
        it++;
        this.lame.add(new IhmRuneDeux(new IhmRune(r, this.jeu, "S'équiper", this)));
      }
    }
    for (int j = 0; j<9-it; it++) {
      this.lame.add(new IhmRuneDeux(new IhmRune(null, this.jeu, "S'équiper", this)));
    }
    JScrollPane lame1 = new JScrollPane(lame);
    lame1.setBackground(Color.decode("#F2E5CF"));

//RAGE
    this.rage = new JPanel();
    it = 0;
    // rage.setLayout(new BoxLayout(rage, BoxLayout.X_AXIS));
    this.rage.setLayout(new GridLayout(1, 100));
    for (Rune r : this.jeu.getCompte().getInventaire()) {
      if (r instanceof Rage) {
        it++;
        this.rage.add(new IhmRuneDeux(new IhmRune(r, this.jeu, "S'équiper", this)));
      }
    }
    for (int j = 0; j<9-it; it++) {
      this.rage.add(new IhmRuneDeux(new IhmRune(null, this.jeu, "S'équiper", this)));
    }
    JScrollPane rage1 = new JScrollPane(rage);
    rage1.setBackground(Color.decode("#F2E5CF"));

    jtp.add(toutes1, "Toutes");
    jtp.add(fatale1, "Fatale");
    jtp.add(swift1, "Rapide");
    jtp.add(gardien1, "Gardien");
    jtp.add(energie1, "Energie");
    jtp.add(lame1, "Lame");
    jtp.add(rage1, "Rage");

    JPanel bot = new JPanel();
    bot.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.decode("#C3C1BF")));
    bot.setBackground(Color.decode("#F2E5CF"));
    bot.setLayout(new BorderLayout());
    JPanel inv = new JPanel();
    inv.setPreferredSize(new Dimension(33, 33));
    inv.setBackground(Color.decode("#F2E5CF"));
    inv.setLayout(new GridBagLayout());
    JLabel titre3 = new JLabel("Inventaire");
    titre3.setFont(Application.font);
    inv.add(titre3);
    bot.add(inv, BorderLayout.NORTH);
    bot.add(jtp, BorderLayout.CENTER);
    this.add(bot, BorderLayout.SOUTH);


//Construction panel du centre
  JPanel centre = new JPanel();
  centre.setLayout(new BorderLayout());

  JPanel runes = new JPanel();
  runes.setLayout(new BorderLayout());
  JPanel tmp1 = new JPanel();
  JLabel titre2 = new JLabel("Runes");
  titre2.setFont(Application.font);
  tmp1.add(titre2);
  tmp1.setBackground(Color.decode("#F2E5CF"));
  tmp1.setPreferredSize(new Dimension(150, 50));
  JPanel tmp2 = new JPanel();
  tmp2.setPreferredSize(new Dimension(150, 50));
  tmp2.setBackground(Color.decode("#F2E5CF"));
  runes.setBackground(Color.decode("#F2E5CF"));
  JPanel tmp3 = new JPanel();
  tmp3.setPreferredSize(new Dimension(150, 50));
  tmp3.setBackground(Color.decode("#F2E5CF"));
  runes.setBackground(Color.decode("#F2E5CF"));
  JPanel tmp4 = new JPanel();
  tmp4.setPreferredSize(new Dimension(150, 50));
  tmp4.setBackground(Color.decode("#F2E5CF"));
  runes.setBackground(Color.decode("#F2E5CF"));
  JPanel centrunes = new JPanel();
  centrunes.setLayout(new BorderLayout());
  this.rune0 = new IhmRune(perso.getRune(0), this.jeu, "Retirer", this);
  this.rune1 = new IhmRune(perso.getRune(1), this.jeu, "Retirer", this);
  rune1.setPreferredSize(new Dimension(50, 50));
  this.rune2 = new IhmRune(perso.getRune(2), this.jeu, "Retirer", this);
  rune2.setPreferredSize(new Dimension(100, 50));
  this.rune3 = new IhmRune(perso.getRune(3), this.jeu, "Retirer", this);
  rune3.setPreferredSize(new Dimension(50, 50));
  this.rune4 = new IhmRune(perso.getRune(4), this.jeu, "Retirer", this);
  rune4.setPreferredSize(new Dimension(100, 50));
  runes.add(tmp1, BorderLayout.NORTH);
  runes.add(tmp2, BorderLayout.WEST);
  runes.add(tmp3, BorderLayout.SOUTH);
  runes.add(tmp4, BorderLayout.EAST);
  centrunes.add(rune0, BorderLayout.CENTER);
  centrunes.add(rune1, BorderLayout.NORTH);
  centrunes.add(rune2, BorderLayout.EAST);
  centrunes.add(rune3, BorderLayout.SOUTH);
  centrunes.add(rune4, BorderLayout.WEST);
  runes.add(centrunes, BorderLayout.CENTER);
  runes.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, Color.decode("#C3C1BF")));
  runes.setBackground(Color.decode("#F2E5CF"));
  //TODO runes
  centre.add(runes, BorderLayout.CENTER);

  JPanel next = new JPanel();
  next.setBackground(Color.decode("#F2E5CF"));
  next.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 0, Color.decode("#C3C1BF")));
  JButton continuer = new JButton("Continuer");
  continuer.setFont(Application.font);
  continuer.setBackground(Color.decode("#C3C1BF"));
  JLabel aletage = new JLabel("à l'étage : ");
  aletage.setFont(Application.font);
  Integer[] model = new Integer[35];
  for (int i = 0; i<35; i++) {
    model[i] = i+1;
  }
  this.etage = new JComboBox<Integer>(model);
  this.etage.setFont(Application.font);
  if (this.jeu.getCompte().getEtage() == -35) {
    this.etage.setSelectedIndex(34);
  } else {
    this.etage.setSelectedIndex(this.jeu.getCompte().getEtage()-1);
  }
  this.etage.setBackground(Color.decode("#C3C1BF"));
  JComboBox<Integer> etage2 = this.etage;
  continuer.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      if (current.getEtage() <= jeu.getCompte().getEtage() || jeu.getCompte().getEtage() == -35) {
        if (current.getEtage() >= jeu.getCompte().getEtage() - 4 || jeu.getCompte().getEtage() == -35) {
          // CoGestionSorts.setCompteur(1);
          jeu.fermerDescriptionsRune();
          new ProchainCombat(jeu);
        } else {
          JOptionPane.showMessageDialog(current, "Vous ne pouvez farmer que les 3 étages précédents", "Erreur", JOptionPane.ERROR_MESSAGE);
          etage2.setSelectedIndex(jeu.getCompte().getEtage()-1);
        }
      } else {
        JOptionPane.showMessageDialog(current, "Vous n'avez pas encore débloqué cet étage", "Erreur", JOptionPane.ERROR_MESSAGE);
        etage2.setSelectedIndex(jeu.getCompte().getEtage()-1);
      }
    }
  });
  next.add(continuer);
  next.add(aletage);
  next.add(this.etage);

  centre.add(next, BorderLayout.NORTH);

  this.add(centre, BorderLayout.CENTER);
  }

  public String[] getListePersoInString() {
    int nbrPerso = this.jeu.getCompte().getNbrPerso();
    String[] liste = new String[nbrPerso];
    for (int i = 0; i<nbrPerso; i++) {
      liste[i] = this.jeu.getCompte().getPerso(i).getClass().getName().substring(11);
    }
    return liste;
  }

  public java.util.List<String> getSelectedPerso() {
    return this.listePerso.getSelectedValuesList();
  }

  public int indexOfString(String str) {
    Personnage[] l = this.jeu.getCompte().getPersos();
    for (int i = 0; i<this.jeu.getCompte().getNbrPerso(); i++) {
      if (l[i].getClass().getName().substring(11).equals(str)) {
        return i;
      }
    }
    return 0;
  }

  public Integer getEtage() {
    return (Integer)this.etage.getSelectedItem();
  }

  public Personnage getPerso() {
    return this.perso;
  }

  public void rafraichirListPerso() {
    DefaultListModel<String> modele = new DefaultListModel<String>();
    for (String perso : this.getListePersoInString()) {
      modele.addElement(perso);
    }
    this.listePerso.setModel(modele);
  }

  public void setPerso(Personnage perso) {
    this.perso = perso;
  }

  public void setSelectedEtage(int i) {
    if (i >= 0 && i <= 35) {
      this.etage.setSelectedIndex(i);
    } else {
      System.out.println("setSelectedEtage(int i) : i isn't correct");
    }
  }

  public void rafraichirTout(Jeu jeu) {
    this.rafraichirCarac(jeu);
    this.rafraichirArgent(jeu);
    this.rafraichirRunesEquipees(jeu);
    this.rafraichirInventaire(jeu);
  }

  public void rafraichirCarac(Jeu jeu) {
    this.jeu = jeu;
    this.perso.reinitialiserBoostRunes();
    this.nomPerso.setText("    "+this.perso.getClass().getName().substring(11)+" : ");
    int hpbonus = this.perso.getStatsSuppl(3);
    int attbonus = this.perso.getStatsSuppl(0);
    int defbonus = this.perso.getStatsSuppl(2);
    int vitbonus = this.perso.getStatsSuppl(1);
    int txcrbonus = this.perso.getStatsSuppl(4);
    int dgcrbonus = this.perso.getStatsSuppl(5);
    this.hp.setText("    Hp :                 "+this.perso.getPdv()+" (+"+hpbonus+")");
    this.attaque.setText("   Attaque :        "+this.perso.getAttaque()+" (+"+attbonus+")");
    this.defense.setText("   Défense :       "+this.perso.getDefense()+" (+"+defbonus+")");
    this.vitesse.setText("   Vitesse :         "+this.perso.getVitesse()+" (+"+vitbonus+")");
    this.tx_crit.setText("   Taux Crit. :      15% (+"+txcrbonus+"%)");
    this.dgts_crit.setText("   Dégats Crit. :  25% (+"+dgcrbonus+"%)");
  }

  public void rafraichirArgent(Jeu jeu) {
    this.jeu = jeu;
    this.argentl.setText("    Argent : "+this.jeu.getCompte().getArgent()+" $teamy");
  }

  public void rafraichirRunesEquipees(Jeu jeu) {
    this.jeu = jeu;
    this.rune0.changerRune(perso.getRune(0), this.jeu, this);
    this.rune1.changerRune(perso.getRune(1), this.jeu, this);
    this.rune2.changerRune(perso.getRune(2), this.jeu, this);
    this.rune3.changerRune(perso.getRune(3), this.jeu, this);
    this.rune4.changerRune(perso.getRune(4), this.jeu, this);
  }

  public void rafraichirInventaire(Jeu jeu) {
    this.jeu = jeu;
    this.toutes.removeAll();
    this.toutes.revalidate();
    this.toutes.repaint();
    this.fatale.removeAll();
    this.fatale.revalidate();
    this.fatale.repaint();
    this.swift.removeAll();
    this.swift.revalidate();
    this.swift.repaint();
    this.gardien.removeAll();
    this.gardien.revalidate();
    this.gardien.repaint();
    this.energie.removeAll();
    this.energie.revalidate();
    this.energie.repaint();
    this.lame.removeAll();
    this.lame.revalidate();
    this.lame.repaint();
    this.rage.removeAll();
    this.rage.revalidate();
    this.rage.repaint();
    int it = 0;

    for (Rune r : this.jeu.getCompte().getInventaire()) {
      it++;
      this.toutes.add(new IhmRuneDeux(new IhmRune(r, this.jeu, "S'équiper", this)));
    }
    for (int j = 0; j<9-it; it++) {
      this.toutes.add(new IhmRuneDeux(new IhmRune(null, this.jeu, "S'équiper", this)));
    }

    it = 0;
    for (Rune r : this.jeu.getCompte().getInventaire()) {
      if (r instanceof Fatale) {
        it++;
        this.fatale.add(new IhmRuneDeux(new IhmRune(r, this.jeu, "S'équiper", this)));
      }
    }
    for (int j = 0; j<9-it; it++) {
      this.fatale.add(new IhmRuneDeux(new IhmRune(null, this.jeu, "S'équiper", this)));
    }

    it = 0;
    for (Rune r : this.jeu.getCompte().getInventaire()) {
      if (r instanceof Swift) {
        it++;
        this.swift.add(new IhmRuneDeux(new IhmRune(r, this.jeu, "S'équiper", this)));
      }
    }
    for (int j = 0; j<9-it; it++) {
      this.swift.add(new IhmRuneDeux(new IhmRune(null, this.jeu, "S'équiper", this)));
    }

    it = 0;
    for (Rune r : this.jeu.getCompte().getInventaire()) {
      if (r instanceof Gardien) {
        it++;
        this.gardien.add(new IhmRuneDeux(new IhmRune(r, this.jeu, "S'équiper", this)));
      }
    }
    for (int j = 0; j<9-it; it++) {
      this.gardien.add(new IhmRuneDeux(new IhmRune(null, this.jeu, "S'équiper", this)));
    }

    it = 0;
    for (Rune r : this.jeu.getCompte().getInventaire()) {
      if (r instanceof Energie) {
        it++;
        this.energie.add(new IhmRuneDeux(new IhmRune(r, this.jeu, "S'équiper", this)));
      }
    }
    for (int j = 0; j<9-it; it++) {
      this.energie.add(new IhmRuneDeux(new IhmRune(null, this.jeu, "S'équiper", this)));
    }

    it = 0;
    for (Rune r : this.jeu.getCompte().getInventaire()) {
      if (r instanceof Lame) {
        it++;
        this.lame.add(new IhmRuneDeux(new IhmRune(r, this.jeu, "S'équiper", this)));
      }
    }
    for (int j = 0; j<9-it; it++) {
      this.lame.add(new IhmRuneDeux(new IhmRune(null, this.jeu, "S'équiper", this)));
    }

    it = 0;
    for (Rune r : this.jeu.getCompte().getInventaire()) {
      if (r instanceof Rage) {
        it++;
        this.rage.add(new IhmRuneDeux(new IhmRune(r, this.jeu, "S'équiper", this)));
      }
    }
    for (int j = 0; j<9-it; it++) {
      this.rage.add(new IhmRuneDeux(new IhmRune(null, this.jeu, "S'équiper", this)));
    }

  }
}
