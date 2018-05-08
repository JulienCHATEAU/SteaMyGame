package application;
import javax.swing.*;
import java.util.Random;
import java.awt.*;
import application.*;
import personnage.*;
import runes.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinCombatDuo extends JFrame {

  private CombatDuo combat;
  private IhmRune drop1;
  private IhmRune drop2;

  public FinCombatDuo(CombatDuo combat, boolean victoire) {
    super("Fin du combat");
    Random r = new Random();
    this.combat = combat;
    Jeu jeu = this.combat.getJeu();
    int largeur = jeu.getPreferredSize().height*55/100;
    int etage = CombatDuo.getEtage();
    CoGestionSorts.compteurPlusPlus();
    this.drop1 = new IhmRune(jeu.getCompte().dropRune(), jeu, "42", jeu.getInv());
    this.drop2 = new IhmRune(jeu.getCompte().dropRune(), jeu, "42", jeu.getInv());
    JPanel main = new JPanel();
    main.setLayout(new BorderLayout());
    main.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.decode("#C3C1BF")));
    Font font18 = new Font("Arial", Font.BOLD , 18);
    Font font30 = new Font("Arial", Font.BOLD , 30);

    JPanel haut = new JPanel();
    haut.setBackground(Color.decode("#F2E5CF"));
    haut.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#C3C1BF")));
    haut.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(5,0,5,0);
    JLabel result = new JLabel();
    if (victoire) {
      result.setText("Victoire !");
    } else {
      result.setText("Défaite");
    }
    result.setFont(font30);
    haut.add(result, c);
    main.add(haut, BorderLayout.NORTH);
    JPanel centre = new JPanel();
    centre.setPreferredSize(new Dimension(30, 40));
    centre.setBackground(Color.decode("#F2E5CF"));
    centre.setLayout(new GridLayout(2, 1));
    centre.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.decode("#C3C1BF")));
    JPanel rec = new JPanel();
    rec.setBackground(Color.decode("#F2E5CF"));
    JLabel recomp = new JLabel();
    int argent = 650 + etage * 10 + r.nextInt(100);
    if (victoire) {
      if (etage < jeu.getCompte().getEtage()) {
        argent /= (2+(jeu.getCompte().getEtage()-etage)/10);
      }
      recomp.setText("Récompense :       "+argent+" $teamy");
      jeu.getCompte().gainArgent(argent);
    } else {
      argent = argent / 2;
      recomp.setText("Perte :       "+argent+" $teamy");
      jeu.getCompte().perteArgent(argent);
    }
    rec.add(recomp);
    JPanel drop = new JPanel();
    drop.setBorder(BorderFactory.createMatteBorder(0, 0, 8, 0, Color.decode("#F2E5CF")));
    drop.setLayout(new GridLayout(1, 2));
    JPanel pord = new JPanel();
    pord.setBorder(BorderFactory.createMatteBorder(0, 0, 8, 0, Color.decode("#F2E5CF")));
    pord.setBackground(Color.decode("#F2E5CF"));
    pord.add(new JLabel("Drop : "));
    drop.add(pord);
    if (CoGestionSorts.getCompteur() == 3 && victoire) {
      drop.add(new IhmRuneDeux(this.drop1));
      jeu.getCompte().ajouterRuneInventaire(this.drop1.getRune());
      if (etage == jeu.getCompte().getEtage()) {
        CombatDuo.setEtage(etage+1);
        drop.setLayout(new GridLayout(1, 4));
        drop.add(new IhmRuneDeux(this.drop2));
        jeu.getCompte().ajouterRuneInventaire(this.drop2.getRune());
      }
    } else {
      JPanel vide = new JPanel();
      vide.setBackground(Color.decode("#F2E5CF"));
      drop.add(vide);
    }
    centre.add(rec);
    centre.add(drop);
    main.add(centre, BorderLayout.CENTER);

    JFrame current = this;
    JPanel bas = new JPanel();
    bas.setBackground(Color.decode("#F2E5CF"));
    bas.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.decode("#C3C1BF")));
    int sallebis = CoGestionSorts.getCompteur();
    if (!victoire || CoGestionSorts.getCompteur() == 3) {
      bas.setLayout(new GridBagLayout());
      c.insets = new Insets(10,0,10,0);
      JButton continuer = new JButton("Continuer");
      continuer.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.out.println("3");
          CoGestionSorts.setCompteur(0);
          Combat.a_afficher = "";
          combat.resetAjoue();
          combat.setText("");
          combat.getP(0).reajusteCaracFinEtage();
          combat.getP(1).reajusteCaracFinEtage();
          jeu.getCompte().getTour().resetEtage(etage);
          jeu.getInv().rafraichirTout(jeu);
          jeu.getInv().setSelectedEtage(CombatDuo.getEtage()-1);
          if (victoire && etage == 10 && jeu.getCompte().getEtage() == 10) {
            jeu.fenetreN(8);
            jeu.getCompte().etagePlusPlus();
          } else if (victoire && etage == 24 && jeu.getCompte().getEtage() == 24) {
            jeu.gererHistoire5();
            jeu.fenetreN(11);
            jeu.getCompte().etagePlusPlus();
          } else if (victoire && etage == 35 && jeu.getCompte().getEtage() == 35) {
            jeu.fenetreN(12);
            jeu.getCompte().setEtageFin();
          } else {
            jeu.fenetreN(5);
            if (victoire) {
              jeu.getCompte().etagePlusPlus();
            }
          }
          current.dispose();
        }
      });
      continuer.setBackground(Color.decode("#C3C1BF"));
      bas.add(continuer, c);
      if (!victoire) {
        largeur = jeu.getPreferredSize().height*36/100;
      } else {
        largeur = jeu.getPreferredSize().height*41/100;
      }
    } else {
      bas.setLayout(new BorderLayout());
      Font font16 = new Font("Arial", Font.BOLD , 16);
      Font font14 = new Font("Arial", Font.BOLD , 14);

      JPanel titre = new JPanel();
      titre.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.decode("#C3C1BF")));
      titre.setBackground(Color.decode("#F2E5CF"));
      titre.setLayout(new GridBagLayout());
      c.insets = new Insets(5,0,5,0);
      JLabel jl = new JLabel("Etage "+etage);
      jl.setFont(font18);
      titre.add(jl, c);
      bas.add(titre, BorderLayout.NORTH);

      JPanel salles = new JPanel();
      salles.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.decode("#F2E5CF")));
      salles.setBackground(Color.decode("#F2E5CF"));
      salles.setLayout(new GridLayout(1, 3));

      JPanel salle1 = new JPanel();
      JPanel salle2 = new JPanel();
      JPanel salle3 = new JPanel();
      switch (CoGestionSorts.getCompteur()+1) {
        case 1:
        salle1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
        salle2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        salle3.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        break;

        case 2:
        salle1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        salle2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
        salle3.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        break;

        case 3:
        salle1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        salle2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        salle3.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
        break;

        default:
        break;
      }

      salle1.setBackground(Color.decode("#F2E5CF"));
      salle2.setBackground(Color.decode("#F2E5CF"));
      salle3.setBackground(Color.decode("#F2E5CF"));
      salle1.setLayout(new BorderLayout());
      salle2.setLayout(new BorderLayout());
      salle3.setLayout(new BorderLayout());

      JPanel titresalle1 = new JPanel();
      JPanel titresalle2 = new JPanel();
      JPanel titresalle3 = new JPanel();
      switch (CoGestionSorts.getCompteur()+1) {
        case 1:
        titresalle1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED));
        titresalle2.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        titresalle3.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        break;

        case 2:
        titresalle1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        titresalle2.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED));
        titresalle3.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        break;

        case 3:
        titresalle1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        titresalle2.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        titresalle3.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED));
        break;

        default:
        break;
      }
      titresalle1.setBackground(Color.decode("#F2E5CF"));
      titresalle2.setBackground(Color.decode("#F2E5CF"));
      titresalle3.setBackground(Color.decode("#F2E5CF"));
      titresalle1.setLayout(new GridBagLayout());
      titresalle2.setLayout(new GridBagLayout());
      titresalle3.setLayout(new GridBagLayout());

      JLabel s1 = new JLabel("Salle 1 : ");
      JLabel s2 = new JLabel("Salle 2 : ");
      JLabel s3 = new JLabel("Salle 3 : ");
      s1.setFont(font14);
      s2.setFont(font14);
      s3.setFont(font14);

      titresalle1.add(s1);
      titresalle2.add(s2);
      titresalle3.add(s3);
      salle1.add(titresalle1, BorderLayout.NORTH);
      salle2.add(titresalle2, BorderLayout.NORTH);
      salle3.add(titresalle3, BorderLayout.NORTH);

      JPanel persosalle1 = new JPanel();
      JPanel persosalle2 = new JPanel();
      JPanel persosalle3 = new JPanel();

      persosalle1.setLayout(new GridLayout(2, 1));
      persosalle2.setLayout(new GridLayout(2, 1));
      persosalle3.setLayout(new GridLayout(2, 1));

      JPanel perso1salle1 = new JPanel();
      perso1salle1.setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, Color.decode("#F2E5CF")));
      JPanel perso2salle1 = new JPanel();
      perso2salle1.setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, Color.decode("#F2E5CF")));
      perso1salle1.setLayout(new GridBagLayout());
      perso2salle1.setLayout(new GridBagLayout());
      perso1salle1.setBackground(Color.decode("#F2E5CF"));
      perso2salle1.setBackground(Color.decode("#F2E5CF"));

      JPanel perso1salle2 = new JPanel();
      perso1salle2.setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, Color.decode("#F2E5CF")));
      JPanel perso2salle2 = new JPanel();
      perso2salle2.setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, Color.decode("#F2E5CF")));
      perso1salle2.setLayout(new GridBagLayout());
      perso2salle2.setLayout(new GridBagLayout());
      perso1salle2.setBackground(Color.decode("#F2E5CF"));
      perso2salle2.setBackground(Color.decode("#F2E5CF"));

      JPanel perso1salle3 = new JPanel();
      perso1salle3.setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, Color.decode("#F2E5CF")));
      JPanel perso2salle3 = new JPanel();
      perso2salle3.setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, Color.decode("#F2E5CF")));
      perso1salle3.setLayout(new GridBagLayout());
      perso2salle3.setLayout(new GridBagLayout());
      perso1salle3.setBackground(Color.decode("#F2E5CF"));
      perso2salle3.setBackground(Color.decode("#F2E5CF"));

      JLabel ps1 = new JLabel(jeu.getCompte().getTour().getPerso(etage, 1).getClass().getName().substring(11));
      JLabel ps12 = new JLabel(jeu.getCompte().getTour().getPerso(etage, 4).getClass().getName().substring(11));

      JLabel ps2 = new JLabel(jeu.getCompte().getTour().getPerso(etage, 2).getClass().getName().substring(11));
      JLabel ps22 = new JLabel(jeu.getCompte().getTour().getPerso(etage, 5).getClass().getName().substring(11));

      JLabel ps3 = new JLabel(jeu.getCompte().getTour().getPerso(etage, 3).getClass().getName().substring(11));
      JLabel ps32 = new JLabel(jeu.getCompte().getTour().getPerso(etage, 6).getClass().getName().substring(11));

      ps1.setFont(font16);
      ps12.setFont(font16);
      ps2.setFont(font16);
      ps22.setFont(font16);
      ps3.setFont(font16);
      ps32.setFont(font16);

      perso1salle1.add(ps1);
      perso2salle1.add(ps12);
      perso1salle2.add(ps2);
      perso2salle2.add(ps22);
      perso1salle3.add(ps3);
      perso2salle3.add(ps32);

      persosalle1.add(perso1salle1);
      persosalle1.add(perso2salle1);
      persosalle2.add(perso1salle2);
      persosalle2.add(perso2salle2);
      persosalle3.add(perso1salle3);
      persosalle3.add(perso2salle3);

      salle1.add(persosalle1, BorderLayout.CENTER);
      salle2.add(persosalle2, BorderLayout.CENTER);
      salle3.add(persosalle3, BorderLayout.CENTER);

      salles.add(salle1);
      salles.add(salle2);
      salles.add(salle3);

      bas.add(salles, BorderLayout.CENTER);

      JPanel combattre = new JPanel();
      combattre.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, Color.decode("#C3C1BF")));
      combattre.setBackground(Color.decode("#F2E5CF"));
      combattre.setLayout(new GridBagLayout());
      JButton combat2 = new JButton("Combattre");


      combat2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.out.println("4");
          jeu.getCompte().getTour().resetEtage(etage);
          Combat.a_afficher = "";
          combat.setText("");
          combat.resetAjoue();
          combat.getP(0).reajusteCaracFinCombat();
          combat.getP(1).reajusteCaracFinCombat();
          combat.changerIa(jeu.getCompte().getTour().getPerso(etage, CoGestionSorts.getCompteur()+1), jeu.getCompte().getTour().getPerso(etage, CoGestionSorts.getCompteur()+4));
          jeu.fenetreN(7);
          jeu.getCombatDuo().setCible(1);
          jeu.getCombatDuo().setAllBoutonPBA(false);

          Personnage nouveauLanceur = jeu.getCombatDuo().getJoueurCourant();
          Personnage[] nouveauTabEnnemisLanceur = jeu.getCombatDuo().getEnnemisLanceur();
          Personnage nouvelAllieLanceur = jeu.getCombatDuo().getAllieLanceur();
          Personnage[] nouveauTabAlliesLanceur = new Personnage[2];
          nouveauTabAlliesLanceur[0] = nouvelAllieLanceur;
          nouveauTabAlliesLanceur[1] = nouveauLanceur;


          if (nouveauLanceur.getPossedePassif() && nouveauLanceur.getPassifActivable()) {
            jeu.getCombatDuo().afficherPassif(nouveauLanceur);
            Timer timer = new Timer(1000, new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                Combat.ajouterCommentaire(" ");
                jeu.getCombatDuo().tirets();
                Combat.ajouterCommentaire(" ");
                nouveauLanceur.sort4(nouveauTabAlliesLanceur, nouveauTabEnnemisLanceur);
                jeu.getCombatDuo().miseAJourPBA();
                jeu.getCombatDuo().miseAJourPC();
                if (jeu.getCombatDuo().getIntJoueurCourant() > 2) {
                  jeu.getCombatDuo().setCible(0);
                  Timer timer = new Timer(1000, new CoGestionSorts(jeu.getCombatDuo()));
                  timer.setRepeats(false);
                  timer.start();
                } else {
                  jeu.getCombatDuo().setAllBoutonPBA(true);
                }
              }
            });
            timer.setRepeats(false);
            timer.start();
          } else {
            jeu.getCombatDuo().miseAJourPBA();
            jeu.getCombatDuo().miseAJourPC();
            if (jeu.getCombatDuo().getIntJoueurCourant() > 2) {
              jeu.getCombatDuo().setCible(0);
              Timer timer = new Timer(2000, new CoGestionSorts(jeu.getCombatDuo()));
              timer.setRepeats(false);
              timer.start();
            } else {
              jeu.getCombatDuo().setAllBoutonPBA(true);
            }
          }
          current.dispose();
        }
      });


      combat2.setBackground(Color.decode("#C3C1BF"));
      c.insets = new Insets(10,0,10,0);
      combattre.add(combat2, c);

      bas.add(combattre, BorderLayout.SOUTH);
    }
    main.add(bas, BorderLayout.SOUTH);

    this.setAlwaysOnTop(true);
    this.setPreferredSize(new Dimension(jeu.getPreferredSize().width*45/100, largeur));
    this.setLocation((jeu.getPreferredSize().width - this.getPreferredSize().width)/2+(int)jeu.getLocation().getX(),(jeu.getPreferredSize().height - this.getPreferredSize().height)/2+(int)jeu.getLocation().getY());
    this.setContentPane(main);
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.pack();
    this.setVisible(true);
  }

  public void nonVente(Rune r) {
    if (this.drop2.getRune().equals(r)) {
      this.drop2.setEnabled(false);
    } else {
      this.drop1.setEnabled(false);
    }
  }

}
