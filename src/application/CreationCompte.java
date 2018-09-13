package application;
import javax.swing.*;
import java.awt.*;
import application.*;
import personnage.*;
import java.awt.event.*;

public class CreationCompte extends JFrame {

  private Jeu jeu;
  private JTextField loginf;
  private JTextField mdpf;

  public CreationCompte(Jeu jeu) {
    super("Création d'un Compte");
    this.jeu = jeu;
    JPanel principal = new JPanel();
    principal.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.decode("#C3C1BF")));
    principal.setLayout(new BorderLayout());

    JPanel haut = new JPanel();
    haut.setBackground(Color.decode("#F2E5CF"));
    haut.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#C3C1BF")));
    haut.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(5,0,5,0);
    JLabel creerCompte = new JLabel("Créer votre compte : ");
    creerCompte.setFont(Application.font);
    haut.add(creerCompte, c);
    principal.add(haut, BorderLayout.NORTH);

    JPanel centre = new JPanel();
    // centre.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.decode("#C3C1BF")));
    centre.setLayout(new BoxLayout(centre, BoxLayout.Y_AXIS));
    centre.setBackground(Color.decode("#F2E5CF"));

    JLabel loginl = new JLabel("Login : ");
    loginl.setFont(Application.font);
    JLabel mdpl = new JLabel("Mot de Passe : ");
    mdpl.setFont(Application.font);
    JPanel loginp = new JPanel();
    JPanel mdpp = new JPanel();
    loginp.setBackground(Color.decode("#F2E5CF"));
    mdpp.setBackground(Color.decode("#F2E5CF"));
    this.loginf = new JTextField();
    this.mdpf = new JPasswordField();
    loginf.setPreferredSize(new Dimension(200, 24));
    mdpf.setPreferredSize(new Dimension(200, 24));
    centre.add(loginl);
    loginp.add(loginf);
    centre.add(loginp);
    centre.add(mdpl);
    mdpp.add(mdpf);
    centre.add(mdpp);
    JPanel tmp = new JPanel();
    tmp.setBackground(Color.decode("#F2E5CF"));
    tmp.setLayout(new BoxLayout(tmp, BoxLayout.Y_AXIS));
    JPanel tmp2 = new JPanel();
    tmp2.setBackground(Color.decode("#F2E5CF"));
    tmp.add(tmp2);
    tmp.add(centre);
    JPanel tmp3 = new JPanel();
    tmp3.setBackground(Color.decode("#F2E5CF"));
    tmp.add(tmp3);
    principal.add(tmp, BorderLayout.CENTER);

    JPanel bas = new JPanel();
    bas.setBackground(Color.decode("#F2E5CF"));
    bas.setLayout(new GridBagLayout());
    JButton creation = new JButton("Créer");
    CoCreationCompte cocc = new CoCreationCompte(this);
    creation.addActionListener(cocc);
    creation.setBackground(Color.decode("#C3C1BF"));
    c.insets = new Insets(0,0,15,0);
    bas.add(creation, c);
    principal.add(bas, BorderLayout.SOUTH);

    this.setPreferredSize(new Dimension(jeu.getPreferredSize().width*28/100, jeu.getPreferredSize().height*58/100));
    this.setLocation((jeu.getPreferredSize().width - this.getPreferredSize().width)/2+(int)jeu.getLocation().getX(),(jeu.getPreferredSize().height - this.getPreferredSize().height)/2+(int)jeu.getLocation().getY());
    this.setContentPane(principal);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.pack();
    this.setVisible(true);
  }

  public String getLogin() {
    return this.loginf.getText();
  }

  public String getMdp() {
    return this.mdpf.getText();
  }

  public Jeu getJeu() {
    return this.jeu;
  }
}
