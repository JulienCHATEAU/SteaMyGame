package application;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import application.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class ChargementMaj extends JWindow {

  public static JLabel imageAccueil;

  public static void main(String[] args) {
    ChargementMaj chargementMaj = new ChargementMaj();
  }

  public ChargementMaj() {
    super();
    JPanel main = new JPanel();
    main.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.decode("#C3C1BF")));
    main.setBackground(Color.decode("#F2E5CF"));
    main.setLayout(new GridBagLayout());
    ChargementMaj.imageAccueil = new JLabel();
    try {
      ChargementMaj.imageAccueil = new JLabel(new ImageIcon(Application.getImageMaj(1)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    ChargementMaj.imageAccueil.setBackground(Color.decode("#F2E5CF"));
    main.add(ChargementMaj.imageAccueil);
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    this.setPreferredSize(new Dimension(550, 320));
    this.setLocation((screen.width - this.getPreferredSize().width)/2,(screen.height - this.getPreferredSize().height)/2);
    this.setContentPane(main);
    this.pack();
    this.setVisible(true);
    Thread t = new Thread() {
      public void run() {
        int i = 2;
        while (true) {
          try {
            Thread.sleep(1000);
            ChargementMaj.imageAccueil.setIcon(new ImageIcon(Application.getImageMaj(i)));
          } catch (IOException | InterruptedException e) {
            e.printStackTrace();
          }
          i++;
          if (i == 4) {
            i = 1;
          }
        }
      }
    };
    t.start();
  }

}