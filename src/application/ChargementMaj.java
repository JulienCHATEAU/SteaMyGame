package application;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import application.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class ChargementMaj extends JWindow {

  public static void main(String[] args) {
    ChargementMaj chargementMaj = new ChargementMaj();
  }

  public ChargementMaj() {
    super();
    JPanel main = new JPanel();
    main.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.decode("#C3C1BF")));
    main.setBackground(Color.decode("#F2E5CF"));
    main.setLayout(new GridBagLayout());
    JLabel imageAccueil = new JLabel();
    try {
      imageAccueil = new JLabel(new ImageIcon(Application.getImageMaj(1)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    imageAccueil.setBackground(Color.decode("#F2E5CF"));
    main.add(imageAccueil);
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    this.setPreferredSize(new Dimension(550, 320));
    this.setLocation((screen.width - this.getPreferredSize().width)/2,(screen.height - this.getPreferredSize().height)/2);
    this.setContentPane(main);
    this.pack();
    this.setVisible(true);
    int i = 2;
    while (0 == 0) {
      try {
        Thread.sleep(1000);
        imageAccueil = new JLabel(new ImageIcon(Application.getImageMaj(i)));
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
      i++;
      if (i == 4) {
        i = 1;
      }
    }
  }

}