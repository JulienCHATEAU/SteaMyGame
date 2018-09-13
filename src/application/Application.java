package application;

import java.util.*;
import java.awt.*;
import runes.*;
import stats.*;
import java.io.*;
import java.nio.file.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import comptes.*;

public class Application {

  public static Font font = new Font("Cochin", Font.BOLD , 18);

  public static void main(String[] args) {
    Jeu j = new Jeu("SteaMyGame");
    // Application.genererDesRunes();
  }

  public static void genererDesRunes() {
    Compte test = new Compte("test", "test");

    Rune r = test.dropRune();
    Rune r1 = test.dropRune();
    Rune r2 = test.createRuneSimple("Energie 2 1 Hp%");
    Rune r3 = test.createRuneSimple("Energie 3 1 Def%");
    Rune r4 = test.createRuneSimple("Swift 0 1 Hp%");

    Rune rbis = test.dropRune();
    Rune r1bis = test.dropRune();
    Rune r2bis = test.createRuneSimple("Energie 3 1 Def%");
    Rune r3bis = test.createRuneSimple("Gardien 0 1 Tc");
    Rune r4bis = test.createRuneSimple("Energie 4 1 Dc");

    Rune rter = test.dropRune();
    Rune r1ter = test.dropRune();
    Rune r2ter = test.createRuneSimple("Swift 4 1 Att%");
    Rune r3ter = test.createRuneSimple("Energie 0 1 Att%");
    Rune r4ter = test.createRuneSimple("Swift 2 1 Tc");

    int niveau_rune = 8;

    for (int i = 0; i<niveau_rune-1; i++) {
      r.ameliorer();
      r1.ameliorer();
      r2.ameliorer();

      rbis.ameliorer();
      r1bis.ameliorer();
      r2bis.ameliorer();

      rter.ameliorer();
      r1ter.ameliorer();
      r2ter.ameliorer();
      if (i<niveau_rune-2) {
      r3.ameliorer();
      r4.ameliorer();
      r3bis.ameliorer();
      r4bis.ameliorer();
      r3ter.ameliorer();
      r4ter.ameliorer();
      }
    }

    System.out.println(r);
    System.out.println(r1);
    System.out.println(r2);
    System.out.println(r3);
    System.out.println(r4);

    System.out.println(rbis);
    System.out.println(r1bis);
    System.out.println(r2bis);
    System.out.println(r3bis);
    System.out.println(r4bis);

    System.out.println(rter);
    System.out.println(r1ter);
    System.out.println(r2ter);
    System.out.println(r3ter);
    System.out.println(r4ter);
  }

  public static void runScript(String scriptName) throws IOException, InterruptedException {
    ProcessBuilder processBuilder = new ProcessBuilder("./"+scriptName);
    //Sets the source and destination for subprocess standard I/O to be the same as those of the current Java process.
    processBuilder.inheritIO();
    Process process = processBuilder.start();

    int exitValue = process.waitFor();
    if (exitValue != 0) {
        // check for errors
        new BufferedInputStream(process.getErrorStream());
        throw new RuntimeException("execution of script failed!");
    }
}

  public static BufferedImage getImageMaj(int i) throws IOException {
    File f = new File("img/maj"+i+".jpeg");
    if (!f.isFile()) {
      f = new File("../SteaMyGame_temp/maj"+i+".jpeg");
    }
    BufferedImage myPicture = ImageIO.read(f);
    return myPicture;
  }

  public static String getTextFromFile(String path) {
    String res = "";
    try {
      GestionFichiers gf = new GestionFichiers(new File(path));
      String ligne = "";
      gf.ouvrir();
      while (ligne != null) {
        ligne = gf.lire();
        if (ligne != null) {
          res += " " + ligne.replace("\\n", "\n");
        }
      }
      gf.fermer();
      res = new String(res.getBytes(), "UTF-8");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return res;
  }

  public static int charToAscii(char c) {
    return (int)c;
  }

  public static char asciiToChar(int ascii) {
    return (char)ascii;
  }

  public static char chiffrementAffine(char x) {
    int a = 17, b = 73;
    int res = a * Application.charToAscii(x) + b;
    res = res % 93;
    res += 32;
    return Application.asciiToChar(res);
  }


  public static char dechiffrementAffine(char x) {
    int res = Application.charToAscii(x) - 32;
    int aprime = 11, bprime = (11*20) % 93;
    res = ((res * aprime) + bprime) % 93;
    if (res < 32) {
      res += 93;
    }
    return Application.asciiToChar(res);
  }

  public static String encodage(String s) {
    String res = "";
    for (int i = 0; i<s.length(); i++) {
      res = res + String.valueOf(Application.chiffrementAffine(s.charAt(i)));
    }
    return res;
  }

  public static String decodage(String s) {
    String res = "";
    for (int i = 0; i<s.length(); i++) {
      res = res + String.valueOf(Application.dechiffrementAffine(s.charAt(i)));
    }
    return res;
  }

  public static String toStringPositionRune(String s) {
    return String.valueOf(s.charAt(0));
  }

  public static String toStringTypeRune(String s) {
    return String.valueOf(s.charAt(2));
  }

  public static String toStringNiveauRune(String s) {
    int pos = 4;
    char tmp = s.charAt(pos);
    String res = "";
    while (tmp != '-') {
      res += tmp;
      pos++;
      tmp = s.charAt(pos);
    }
    return String.valueOf(res);
  }

  public static String toStringMainStatRune(String s) {
    int pos = 5 + Application.toStringNiveauRune(s).length();
    char tmp = s.charAt(pos);
    String res = "";
    while (tmp != '-') {
      res += tmp;
      pos++;
      tmp = s.charAt(pos);
    }
    return String.valueOf(res);
  }

  public static Stat[] stringToSubStatsRune(String s) {
    int pos = 6 + Application.toStringNiveauRune(s).length()+Application.toStringMainStatRune(s).length();
    char tmp = s.charAt(pos);
    Stat[] bis = new Stat[4];
    int i = 0;
    String res = "";
    while (pos < s.length()-1) {
      res = "";
      while (tmp != '-' && pos < s.length()-1) {
        res += tmp;
        pos++;
        if (pos < s.length()) {
          tmp = s.charAt(pos);
        }
      }
      if (pos == s.length()-1) {
        res += tmp;
      }
      pos++;
      if (pos < s.length()) {
        tmp = s.charAt(pos);
      }
      bis[i] = Application.stringToStat(res, false);
      i++;
    }
    return bis;
  }

  public static int nbrSubRune(String str) {
    Stat[] s = Application.stringToSubStatsRune(str);
    int cmp = 0;
    for (int i = 0; i<4; i++) {
      if (s[i] != null) {
        cmp++;
      }
    }
    return cmp;
  }

  public static Stat stringToStat(String s, boolean main) {
    Stat st = new Attaque(true);
    switch (s.charAt(0)) {
      case '0':
      st = new AttaqueFlat(main, Integer.parseInt(s.substring(1, s.length())));
      break;

      case '1':
      st = new Attaque(main, Integer.parseInt(s.substring(1, s.length())));
      break;

      case '2':
      st = new PointsDeVieFlat(main, Integer.parseInt(s.substring(1, s.length())));
      break;

      case '3':
      st = new PointsDeVie(main, Integer.parseInt(s.substring(1, s.length())));
      break;

      case '4':
      st = new DefenseFlat(main, Integer.parseInt(s.substring(1, s.length())));
      break;

      case '5':
      st = new Defense(main, Integer.parseInt(s.substring(1, s.length())));
      break;

      case '6':
      st = new Vitesse(main, Integer.parseInt(s.substring(1, s.length())));
      break;

      case '7':
      st = new TauxCritiques(main, Integer.parseInt(s.substring(1, s.length())));
      break;

      case '8':
      st = new DegatsCritiques(main, Integer.parseInt(s.substring(1, s.length())));
      break;
    }
    return st;
  }

  public static Rune stringToRune(String s) {
    Rune res = new Energie();
    switch (Application.toStringTypeRune(s).charAt(0)) {
      case 'F':
      res = new Fatale(Integer.parseInt(Application.toStringPositionRune(s)), Integer.parseInt(Application.toStringNiveauRune(s)), Application.stringToStat(Application.toStringMainStatRune(s), true), Application.stringToSubStatsRune(s), Application.nbrSubRune(s));
      break;

      case 'S':
      res = new Swift(Integer.parseInt(Application.toStringPositionRune(s)), Integer.parseInt(Application.toStringNiveauRune(s)), Application.stringToStat(Application.toStringMainStatRune(s), true), Application.stringToSubStatsRune(s), Application.nbrSubRune(s));
      break;

      case 'G':
      res = new Gardien(Integer.parseInt(Application.toStringPositionRune(s)), Integer.parseInt(Application.toStringNiveauRune(s)), Application.stringToStat(Application.toStringMainStatRune(s), true), Application.stringToSubStatsRune(s), Application.nbrSubRune(s));
      break;

      case 'E':
      res = new Energie(Integer.parseInt(Application.toStringPositionRune(s)), Integer.parseInt(Application.toStringNiveauRune(s)), Application.stringToStat(Application.toStringMainStatRune(s), true), Application.stringToSubStatsRune(s), Application.nbrSubRune(s));
      break;

      case 'L':
      res = new Lame(Integer.parseInt(Application.toStringPositionRune(s)), Integer.parseInt(Application.toStringNiveauRune(s)), Application.stringToStat(Application.toStringMainStatRune(s), true), Application.stringToSubStatsRune(s), Application.nbrSubRune(s));
      break;

      case 'R':
      res = new Rage(Integer.parseInt(Application.toStringPositionRune(s)), Integer.parseInt(Application.toStringNiveauRune(s)), Application.stringToStat(Application.toStringMainStatRune(s), true), Application.stringToSubStatsRune(s), Application.nbrSubRune(s));
      break;
    }
    return res;
  }

}
