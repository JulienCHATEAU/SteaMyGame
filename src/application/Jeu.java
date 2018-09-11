package application;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;
import java.awt.*;

import personnage.*;
import runes.*;
import comptes.*;

public class Jeu extends JFrame {

  private Compte compte;
  private GestionFichiers gfPersonnel;
  private GestionFichiers gfComptes;
  public static java.util.List<Personnage> liste_perso;
  public static Map<Integer, String> categoriePerso;
  private String[] tab_cl;

  public static String getCategoriePerso(int i) {
    return Jeu.categoriePerso.get(i);
  }

  public static int indexOf(Personnage p) {
    return Jeu.liste_perso.indexOf(p);
  }

  private JPanel tout;
  private CardLayout cl;

  private Accueil accueil;
  private Histoire h2;
  private Histoire h5;
  private Inventaire inv;
  private Combat combatSolo;
  private CombatDuo combatDuo;

  public Jeu(String titre) {
    super(titre);
    this.compte = new Compte("", "");
    Jeu.categoriePerso = new HashMap<Integer, String>();
    Jeu.categoriePerso.put(0, "Nuker");
    Jeu.categoriePerso.put(1, "Tank");
    Jeu.categoriePerso.put(2, "Nuker");
    Jeu.categoriePerso.put(3, "Support");
    Jeu.categoriePerso.put(4, "Polyvalent");
    Jeu.categoriePerso.put(5, "Polyvalent");
    Jeu.categoriePerso.put(6, "Nuker");
    Jeu.categoriePerso.put(7, "Nuker");
    Jeu.categoriePerso.put(8, "Tank");
    Jeu.categoriePerso.put(9, "Tank");
    Jeu.categoriePerso.put(10, "Polyvalent");
    Jeu.categoriePerso.put(11, "Tank");
    Jeu.categoriePerso.put(12, "Support");
    Jeu.categoriePerso.put(13, "Tank");
    Jeu.categoriePerso.put(14, "Tank");
    Jeu.categoriePerso.put(15, "Nuker");
    Jeu.categoriePerso.put(16, "Nuker");
    Jeu.categoriePerso.put(17, "Support");
    Jeu.categoriePerso.put(18, "Tank");
    Jeu.categoriePerso.put(19, "Polyvalent");
    Jeu.categoriePerso.put(20, "Tank");
    Jeu.categoriePerso.put(21, "Nuker");
    Jeu.categoriePerso.put(22, "Polyvalent");
    Jeu.categoriePerso.put(23, "Polyvalent");
    Jeu.categoriePerso.put(24, "Polyvalent");
    Jeu.categoriePerso.put(25, "Polyvalent");
    Jeu.categoriePerso.put(26, "Support");
    Jeu.categoriePerso.put(27, "Nuker");
    Jeu.categoriePerso.put(28, "Support");
    Jeu.categoriePerso.put(29, "Support");
    Jeu.categoriePerso.put(30, "Support");
    Jeu.categoriePerso.put(31, "Support");

    Jeu.liste_perso = new ArrayList<Personnage>();
    Jeu.liste_perso.add(new Kaito(""));
    Jeu.liste_perso.add(new Ritesh(""));
    Jeu.liste_perso.add(new Zaiross(""));
    Jeu.liste_perso.add(new Veromos(""));
    Jeu.liste_perso.add(new Okeanos(""));
    Jeu.liste_perso.add(new Fuco(""));
    Jeu.liste_perso.add(new Sian(""));
    Jeu.liste_perso.add(new Zeratu(""));
    Jeu.liste_perso.add(new Seara(""));
    Jeu.liste_perso.add(new Cuivre(""));
    Jeu.liste_perso.add(new Eludia(""));
    Jeu.liste_perso.add(new Thrain(""));
    Jeu.liste_perso.add(new Woonhak(""));
    Jeu.liste_perso.add(new XiongFei(""));
    Jeu.liste_perso.add(new Icaru(""));
    Jeu.liste_perso.add(new XiaoLin(""));
    Jeu.liste_perso.add(new Oberon(""));
    Jeu.liste_perso.add(new Nephthys(""));
    Jeu.liste_perso.add(new Basalt(""));
    Jeu.liste_perso.add(new Tablo(""));
    Jeu.liste_perso.add(new Eshir(""));
    Jeu.liste_perso.add(new Stella(""));
    Jeu.liste_perso.add(new Ethna(""));
    Jeu.liste_perso.add(new Thebae(""));
    Jeu.liste_perso.add(new Cadiz(""));
    Jeu.liste_perso.add(new Erwin(""));
    Jeu.liste_perso.add(new Nicki(""));
    Jeu.liste_perso.add(new Sige(""));
    Jeu.liste_perso.add(new Haken(""));
    Jeu.liste_perso.add(new Cichlid(""));
    Jeu.liste_perso.add(new Chasun(""));
    Jeu.liste_perso.add(new Artamiel(""));
    //Rajouter perso ICI

    this.tout = new JPanel();
    CoClose cc = new CoClose(this);
    this.addWindowListener(cc);
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int jwidth = screen.width*85/100;
    if (jwidth > 1161 && screen.width > 1600) {
      jwidth = screen.width*70/100;
    }
    int jheight = jwidth*55/100;
    System.out.println("Screen width : "+screen.width);
    System.out.println("Screen height : "+screen.height);
    this.setPreferredSize(new Dimension(jwidth, jheight));
    this.setLocation((screen.width - this.getPreferredSize().width)/2,(screen.height - this.getPreferredSize().height)/2);
    this.setResizable(false);
    this.setContentPane(this.tout);
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.pack();
    this.setVisible(true);
    this.cl = new CardLayout();
    this.tout.setLayout(this.cl);
    this.tab_cl = new String[16];
    this.tab_cl[0] = "Accueil";
    this.tab_cl[1] = "Histoire1";
    this.tab_cl[2] = "CreationPerso";
    this.tab_cl[3] = "Histoire2";
    this.tab_cl[5] = "CombatSolo";
    this.tab_cl[6] = "CombatDuo";
    this.tab_cl[7] = "Histoire3";
    this.tab_cl[8] = "AjouterPerso";
    this.tab_cl[9] = "Histoire4";
    this.tab_cl[10] = "Histoire5";
    this.tab_cl[11] = "Histoire6";

    this.accueil = new Accueil(this);
    this.accueil.setName(this.tab_cl[0]);
    Histoire h1 = new Histoire(this, 1);
    h1.setName(this.tab_cl[1]);
    CreationPerso cp = new CreationPerso(this, 1);
    cp.setName(this.tab_cl[2]);
    this.h2 = new Histoire(this, 2);
    this.h2.setName(this.tab_cl[3]);
    this.combatSolo = new Combat(this);
    this.combatSolo.setName(this.tab_cl[5]);
    this.combatDuo = new CombatDuo(this);
    this.combatDuo.setName(this.tab_cl[6]);
    Histoire h3 = new Histoire(this, 3);
    h3.setName(this.tab_cl[7]);
    CreationPerso cp2 = new CreationPerso(this, 2);
    cp2.setName(this.tab_cl[8]);
    Histoire h4 = new Histoire(this, 4);
    h4.setName(this.tab_cl[9]);
    this.h5 = new Histoire(this, 5);
    this.h5.setName(this.tab_cl[10]);
    Histoire h6 = new Histoire(this, 6);
    h6.setName(this.tab_cl[11]);

    this.tout.add(this.accueil, this.tab_cl[0]);
    this.tout.add(h1, this.tab_cl[1]);
    this.tout.add(cp, this.tab_cl[2]);
    this.tout.add(this.h2, this.tab_cl[3]);
    this.tout.add(this.combatSolo, this.tab_cl[5]);
    this.tout.add(this.combatDuo, this.tab_cl[6]);
    this.tout.add(h3, this.tab_cl[7]);
    this.tout.add(cp2, this.tab_cl[8]);
    this.tout.add(h4, this.tab_cl[9]);
    this.tout.add(this.h5, this.tab_cl[10]);
    this.tout.add(h6, this.tab_cl[11]);
    try {
      this.gfComptes = new GestionFichiers(new File("save/Comptes.txt"));
    } catch (Exception e) {
      System.out.println("Erreur : "+e.getMessage());
    }
  }

  //méthode qui permet de récupérer les informations concernant le compte ayant le login donné en paramètre sous la forme d'une liste
  //login decodé
  public java.util.List<String> getInfosCompte(String login) {
    java.util.List<String> res = new ArrayList<String>();
    String tmp = "";
    int ligne = this.loginExiste(login);//ligne où se trouve les infos
    int i = 1;
    try {
      this.gfComptes.ouvrir();
      while (i != ligne) {
        this.gfComptes.lire();
        i++;
      }
      tmp = this.gfComptes.lire();//on récupère la ligne
      if (tmp != null) {
        String[] splitedTmp = tmp.split(" ");//on séprare les infos
        for (String info : splitedTmp) {//qu'on ajoute dans la liste
        res.add(info);
      }
      res.set(0, Application.decodage(res.get(0)));
      res.set(1, Application.decodage(res.get(1)));
    } else {
      System.out.println("Erreur lors de la recherche de la ligne");
    }
    this.gfComptes.fermer();
  } catch (Exception e) {
    System.out.println("Erreur récupération infos compte : "+e.getMessage());
  }
  return res;
}

//méthode qui permet d'enregistrer les données du compte courant
public void enregistrerDonneesCompte() {
  try {
    this.mettreAJourCompte();//met à jour la ligne de données sauvegardée
    this.gfPersonnel.getFile().delete();//supprime le fichier de rune correspondant au compte courant
    File newf = new File("save/"+this.compte.getLogin()+"_runes.txt");
    newf.createNewFile();//pour en recréer un mis à jour
    this.gfPersonnel = new GestionFichiers(new File("save/"+this.compte.getLogin()+"_runes.txt"));
    this.gfPersonnel.ouvrir();
    java.util.List<String> infosCompte = this.getInfosCompte(this.compte.getLogin());
    //on sauvegarde toutes les runes
    for (int j = 0; j<Integer.parseInt(infosCompte.get(4)); j++) {
      Personnage persoCourant = this.compte.getPerso(j);
      for (int i = 0; i<5; i++) {
        if (persoCourant.getRune(i) != null) {
          this.gfPersonnel.ecrire(persoCourant.getRune(i).toString());
        } else {
          this.gfPersonnel.ecrire("");
        }
      }
    }

    for (Rune r : this.compte.getInventaire()) {
      this.gfPersonnel.ecrire(r.toString());
    }
    this.gfPersonnel.fermer();
  } catch (Exception e) {
    System.out.println("Erreur enregistrement données compte : "+e.getMessage());
  }
}

//méthode qui permet d'initialiser un compte existant avec ses données sauvegardées
//login et mdp décodé
public void initialisationCompte(String login, String mdp) {
  this.newCompte(login, mdp);//On initialise le compte

  java.util.List<String> infosCompte = this.getInfosCompte(login);//On récupère les infos sauvegardées de ce compte
  //On ajoute les infos au compte
  this.compte.setEtage(Integer.parseInt(infosCompte.get(2)));
  this.compte.setArgent(Integer.parseInt(infosCompte.get(3)));
  for (int i = 0; i<Integer.parseInt(infosCompte.get(4)); i++) {
    this.compte.ajouterPersonnage(Integer.parseInt(infosCompte.get(i+5)));
  }

  //on ajoute les runes à chaque perso et à l'inventaire du compte
  try {
    this.gfPersonnel = new GestionFichiers(new File("save/"+login+"_runes.txt"));//On initialise notre gestionnaire de fichier avec celui sauvegardant les runes du compte courant
    // this.gfPersonnel = new GestionFichiers(new File("save/"+login+"_"+mdp+".txt"));
    this.gfPersonnel.ouvrir();
    String tmp = "";
    for (int j = 0; j<Integer.parseInt(infosCompte.get(4)); j++) {//pour chaque perso du compte
      Personnage persoCourant = this.compte.getPerso(j);
      for (int i = 0; i<5; i++) {//pour chaque rune
        tmp = this.gfPersonnel.lire();
        if (tmp != null) {//si pas fin de fichier
          if (tmp.length() > 0) {//si pas ligne vide
            this.compte.equiperRune(persoCourant, Application.stringToRune(tmp));//on equipe la rune au perso courant
          }
        }
      }
      persoCourant.appliquerEffetsRunes();
    }


    do {
      tmp = this.gfPersonnel.lire();
      if (tmp != null) {
        this.compte.getInventaire().add(Application.stringToRune(tmp));
      }
    } while (tmp != null);

    this.gfPersonnel.fermer();
  } catch (Exception e) {
    System.out.println("Erreur Init : ");
    e.printStackTrace();
  }
}

//méthode qui permet de créer un compte avec le login et le mot de passe donnée en paramètre (créer une nouvelle ligne dans le fichier des comptes)
//login et mdp décodé
public void creerCompte(String login, String mdp) {
  try {
    this.gfComptes.ecrire(Application.encodage(login)+" "+Application.encodage(mdp)+" 1 2000 0");//on sauvegarde le compte créée dans le fichier de sauvegarde
    File newf = new File("save/"+login+"_runes.txt");
    // File newf = new File("save/"+secretlogin+"_"+secretmdp+".txt");
    newf.createNewFile();//on créer un nouveau fichier texte de sauvegarde du nouveau compte
  } catch (Exception e) {
    System.out.println("Erreur Creation Compte : "+e.getMessage());
  }
}

//méthode qui permet de mettre à jour la ligne de donnée du compte courant
public void mettreAJourCompte() {
  try {
    int n = this.loginExiste(this.compte.getLogin());
    if (n != -1) {
      this.gfComptes.ouvrir();
      String debutdufichier = "";
      String findufichier = "";
      for (int i = 0; i<n-1; i++) {
        debutdufichier += this.gfComptes.lire()+"\r\n";
      }
      boolean stop = false;
      String tmp = "";
      this.gfComptes.lire();
      while (!stop) {
        tmp = this.gfComptes.lire();
        if (tmp == null) {
          stop = true;
        } else {
          findufichier += tmp+"\r\n";
        }
      }
      String personnages = "";
      int persos[] = this.persoToIntTab(this.compte.getPersos());
      for (int i = 0; i<this.compte.getNbrPerso(); i++) {
        personnages = personnages + " " + persos[i];
      }
      String change = Application.encodage(this.compte.getLogin())+" "+Application.encodage(this.compte.getMdp())+" "+this.compte.getEtage()+" "+this.compte.getArgent()+" "+this.compte.getNbrPerso()+personnages+"\r\n";
      String res = debutdufichier+change+findufichier;//tout est dis pour le fonctionnement de ce cette méthode
      this.gfComptes.remplacer(res);
      this.gfComptes.fermer();
    } else {
      System.out.println("Ce compte n'existe pas");
    }
  } catch (Exception e) {
    System.out.println("Erreur MAJ compte : "+e.getMessage());
  }
}

//Si le login existe, retourne la ligne où il se trouve dans le fichier de sauvegarde sinon -1
//login décodé
public int loginExiste(String login) {
  int existe = -1;
  int i = 1;
  try {
    this.gfComptes.ouvrir();
    String ligne = "";
    boolean stop = false;
    while (ligne != null && !stop) {
      ligne = this.gfComptes.lire();
      if (ligne.split(" ")[0].equals(Application.encodage(login))) {
        stop = true;
        existe = i;
      }
      i++;
    }
  } catch (Exception e) {
    System.out.println("Erreur login existe : "+e.getMessage());
  }
  return existe;
}

//Si le mdp existe, retourne la ligne où il se trouve dans le fichier de sauvegarde sinon -1
//mdp décodé
public int mdpExiste(String mdp) {
  int existe = -1;
  int i = 1;
  try {
    this.gfComptes.ouvrir();
    String ligne = "";
    boolean stop = false;
    while (ligne != null && !stop) {
      ligne = this.gfComptes.lire();
      if (ligne.split(" ")[1].equals(Application.encodage(mdp))) {
        stop = true;
        existe = i;
      }
      i++;
    }
  } catch (Exception e) {
    System.out.println("Erreur mdp existe : "+e.getMessage());
  }
  return existe;
}

//Login et mdp décodé
public boolean loginEtMdpCoincide(String login, String mdp) {
  return this.mdpExiste(mdp) == this.loginExiste(login) && this.mdpExiste(mdp) != -1;
}

//méthode qui fait correspondre le tableau de personnage donné en paramètre avec un tableau de nombre
public int[] persoToIntTab(Personnage[] persos) {
  int[] res = new int[persos.length];
  java.util.List<Personnage> tmp = Jeu.liste_perso;
  boolean trouve = false;
  int j = 0;
  for (int i = 0; i<persos.length; i++) {
    if (persos[i] != null) {
      j = 0;
      trouve = false;
      while (j < tmp.size() && !trouve) {
        if (tmp.get(j).getClass().equals(persos[i].getClass())) {
          trouve = true;
        }
        j++;
      }
      res[i] = j-1;
    } else {
      res[i] = -1;
    }
  }
  return res;
}

//retourne true si on se trouve sur l'écran donné en paramètre
public boolean isEcran(String ecran) {
  JPanel card = null;
  for (Component comp : this.tout.getComponents()) {
    if (comp.isVisible() == true) {
      card = (JPanel) comp;
    }
  }
  if (card.getName().equals(ecran)) {
    return true;
  } else {
    return false;
  }
}

//ferme les descriptions de rune actuellement ouverte
public void fermerDescriptionsRune() {
  Window[] liste = JFrame.getWindows();
  DescriptionRune.nbOuverte = 0;
  //itération sur ces composants
  for (int i = 0; i < liste.length; i++){
    if (liste[i] instanceof DescriptionRune){
      liste[i].dispose();
    }
  }
}

//méthode qui permet d'ajouter l'inventaire
public void ajouterInventaire() {
  this.tab_cl[4] = "Inventaire";
  this.inv = new Inventaire(this);
  this.inv.setName(this.tab_cl[4]);
  this.tout.add(this.inv, this.tab_cl[4]);
}


public void initCombatDuo(int persoChoisis1, int persoChoisis2) {
  CombatDuo.setEtage(this.getInv().getEtage());
  this.combatDuo.init(persoChoisis1, persoChoisis2, this.getCompte().getTour().getEtage(this.getInv().getEtage()).getPersoSalle(1), this.getCompte().getTour().getEtage(this.getInv().getEtage()).getPersoSalle(4));
}

//méthode qui ajoute le perso récupérer aléatoirements
public void ajouterPersoRandHistoire() {
  this.h2.ajouterPerso(this.compte.getPerso(1).getClass().getName().substring(11) + " !");
}

//méthode qui permet d'ajouter un perso dans l'inventaire et le rafraichi
public void ajouterPersoInventaire(Personnage p) {
  this.inv.setPerso(p);
  this.inv.rafraichirTout(this);
}

public void rafraichirCombatDuo() {
  this.combatDuo.rafraichirTout();
}

//méthode permettant de passer à l'écran suivant
public void fenetreSuivante() {
  this.cl.next(this.tout);
}

public void placerFleche() {
  this.combatDuo.placerFleche();
}


//méthode permettant de passer au nième écran
public void fenetreN(int n) {
  this.cl.show(this.tout, this.tab_cl[n-1]);
}

//méthode qui créer un nouveau compte
public void newCompte(String login, String mdp) {
  this.compte = new Compte(login, mdp);
}

//méthode qui récupère l'inventaire
public Inventaire getInv() {
  return this.inv;
}

//méthode qui permet de récupérer le compte courant
public CombatDuo getCombatDuo() {
  return this.combatDuo;
}

public boolean comptePossedePerso(Personnage p) {
  boolean possede = false;
  int i = 0;
  while (i < this.compte.getNbrPerso() && !possede) {
    if (this.compte.getPerso(i).getClass().equals(p.getClass())) {
      possede = true;
    }
    i++;
  }
  return possede;
}

public boolean comptePossedeCategorie(int rand) {
  boolean possede = false;
  int i = 0;
  int[] intPersos = this.persoToIntTab(this.compte.getPersos());
  while (i < this.compte.getNbrPerso() && !possede) {
    if (Jeu.categoriePerso.get(intPersos[i]).equals(Jeu.categoriePerso.get(rand))) {
      possede = true;
    }
    i++;
  }
  return possede;
}

public void gererHistoire5() {
  Random r = new Random();
  int rand = r.nextInt(30);
  while (this.comptePossedeCategorie(rand)) {
    rand = r.nextInt(30);
  }
  this.getCompte().ajouterPersonnage(rand);
  this.getInv().rafraichirListPerso();
  this.h5.ajouterQuatriemePerso(Jeu.liste_perso.get(rand).getClass().getName().substring(11));
}

//méthode qui permet de récupérer le compte courant
public Combat getCombatSolo() {
  return this.combatSolo;
}

//méthode qui permet de récupérer le compte courant
public Compte getCompte() {
  return this.compte;
}
}
