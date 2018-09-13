package application;
import javax.swing.*;
import java.awt.*;
import application.*;
import personnage.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class Accueil extends JPanel {

  private Jeu jeu;
  private JTextField loginf;
  private JPasswordField mdpf;


  public Accueil(Jeu jeu) {
    super();
    this.jeu = jeu;
    this.setLayout(new BorderLayout());
    JPanel steamygame = new JPanel();
    steamygame.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 2, Color.decode("#C3C1BF")));
    steamygame.setBackground(Color.decode("#F2E5CF"));
    Font font = new Font("Permanent Marker", Font.BOLD , 18);
    steamygame.setLayout(null);
    JButton maj = new JButton("Mise à jour");
    maj.setBackground(Color.decode("#C3C1BF"));
    maj.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jeu.dispose();
        try {
          Application.runScript("tmp.bat");
        } catch (IOException | InterruptedException e2) {
          e2.printStackTrace();
        }
      }
    });
    steamygame.add(maj);
    Dimension size = maj.getPreferredSize();
    maj.setBounds(24, 24, size.width, size.height);
    JLabel imageAccueil = new JLabel();
    try {
      BufferedImage myPicture = ImageIO.read(new File("img/imageAccueil.jpg"));
      imageAccueil = new JLabel(new ImageIcon(myPicture));
    } catch (IOException e) {
      e.printStackTrace();
    }
    imageAccueil.setBackground(Color.decode("#F2E5CF"));
    steamygame.add(imageAccueil);
    size = imageAccueil.getPreferredSize();
    Dimension screen = this.jeu.getPreferredSize();
    imageAccueil.setBounds((screen.width*80/100-size.width)/2+8, (screen.height-size.height)/2+8, size.width, size.height);

    JPanel droite = new JPanel();
    droite.setBorder(BorderFactory.createMatteBorder(8, 2, 8, 8, Color.decode("#C3C1BF")));
    droite.setLayout(new GridLayout(2, 1));
    JPanel droite1 = new JPanel();
    JPanel droite2 = new JPanel();

    JButton connexion = new JButton("Connexion");
    ConnexionCompte co = new ConnexionCompte(this);
    connexion.addActionListener(co);
    JButton creercompte = new JButton("Créer un compte");
    creercompte.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new CreationCompte(jeu);
      }
    });
    connexion.setBackground(Color.decode("#C3C1BF"));
    creercompte.setBackground(Color.decode("#C3C1BF"));
    droite1.add(new JLabel(" "));
    droite1.add(connexion);
    droite1.add(new JLabel(" "));
    droite1.add(creercompte);
    droite1.add(new JLabel(" "));

    droite2.setLayout(new BoxLayout(droite2, BoxLayout.Y_AXIS));
    droite2.setBackground(Color.decode("#F2E5CF"));
    droite1.setBackground(Color.decode("#F2E5CF"));
    JLabel loginl = new JLabel(" Login (Pseudo) : ");
    JLabel mdpl = new JLabel(" Mot de Passe : ");
    JPanel loginp = new JPanel();
    JPanel mdpp = new JPanel();
    loginp.setBackground(Color.decode("#F2E5CF"));
    mdpp.setBackground(Color.decode("#F2E5CF"));
    loginl.setFont(font);
    mdpl.setFont(font);
    this.loginf = new JTextField("");
    // this.loginf.setText("Steamy44");//auto connexion
    this.mdpf = new JPasswordField("");
    // this.mdpf.setText("23ae452a16afa9");//auto connexion
    loginf.setPreferredSize(new Dimension(200, 24));
    mdpf.setPreferredSize(new Dimension(200, 24));
    droite2.add(loginl);
    loginp.add(loginf);
    droite2.add(loginp);
    droite2.add(mdpl);
    mdpp.add(mdpf);
    droite2.add(mdpp);
    JPanel tmp = new JPanel();
    tmp.setBackground(Color.decode("#F2E5CF"));
    tmp.setLayout(new BoxLayout(tmp, BoxLayout.Y_AXIS));
    JPanel tmp2 = new JPanel();
    tmp2.setBackground(Color.decode("#F2E5CF"));
    tmp.add(tmp2);
    tmp.add(droite2);
    JPanel tmp3 = new JPanel();
    tmp3.setBackground(Color.decode("#F2E5CF"));
    tmp.add(tmp3);
    droite.add(tmp);
    droite.add(droite1);

    this.add(steamygame, BorderLayout.CENTER);
    this.add(droite, BorderLayout.EAST);
  }


  public String getLogin() {
    return String.valueOf(this.loginf.getText());
  }


  public String getMdp() {
    return String.valueOf(this.mdpf.getPassword());
  }


  public Jeu getJeu() {
    return this.jeu;
  }


  public void rafraichirFields() {
    this.loginf.setText("");
    this.mdpf.setText("");
  }
}


// int[] banana = new int[5];
// int[] dessert = banana.split(" ");
