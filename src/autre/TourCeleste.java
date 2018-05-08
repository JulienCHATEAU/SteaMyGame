package comptes;
import personnage.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import application.*;


public class TourCeleste {

  private Etage[] tour;
  private GestionFichiers init;

  public TourCeleste() {
    this.tour = new Etage[35];
    try {
      this.init = new GestionFichiers(new File("data/RunesTourCeleste.txt"));
      Personnage kaito1 = new Kaito("");
      Personnage ritesh1 = new Ritesh("");
      Personnage zaiross1 = new Zaiross("");

      Personnage basalt2 = new Basalt("");
      Personnage tablo2 = new Tablo("");
      Personnage eshir2 = new Eshir("");

      Personnage sige10 = new Sige("");
      Personnage haken10 = new Haken("");
      Personnage cichlid10 = new Cichlid("");
      Personnage cadiz10 = new Cadiz("");
      Personnage erwin10 = new Erwin("");
      Personnage nicki10 = new Nicki("");

      Personnage eshir20 = new Eshir("");
      Personnage nicki20 = new Nicki("");
      Personnage icaru20 = new Icaru("");

      Personnage tablo30 = new Tablo("");
      Personnage eludia30 = new Eludia("");
      Personnage woonhak30 = new Woonhak("");

      Personnage veromos40 = new Veromos("");
      Personnage zaiross40 = new Zaiross("");
      Personnage stella40 = new Stella("");
      Personnage cichlid40 = new Cichlid("");
      Personnage kaito40 = new Kaito("");
      Personnage nephthys40 = new Nephthys("");

      Personnage cadiz50 = new Cadiz("");
      Personnage cuivre50 = new Cuivre("");
      Personnage seara50 = new Seara("");

/*
* REMPLISSAGE DE LA TOUR
*/


      this.tour[0] = new Etage(kaito1.malusPremiersNiveaux(12), ritesh1.malusPremiersNiveaux(12), zaiross1.malusPremiersNiveaux(12));
      this.tour[1] = new Etage(basalt2.malusPremiersNiveaux(6), tablo2.malusPremiersNiveaux(6), eshir2.malusPremiersNiveaux(6));
      this.tour[2] = new Etage(new Fuco(""), new Veromos(""), new Okeanos(""), new Sian(""), new Zeratu(""), new Seara(""));//1 rune --- 2 runes
      this.tour[3] = new Etage(new Cuivre(""), new Eludia(""), new Thrain(""), new Woonhak(""), new XiongFei(""), new Icaru(""));//3 runes --- 3 runes
      this.tour[4] = new Etage(new XiaoLin(""), new Oberon(""), new Nephthys(""));//4 runes
      this.tour[5] = new Etage(new Stella(""), new Ethna(""), new Thebae(""));//4 runes
      this.tour[6] = new Etage(cadiz10.boostEtageBoss(10), erwin10.boostEtageBoss(10), nicki10.boostEtageBoss(10), sige10.boostEtageBoss(10), cichlid10.boostEtageBoss(10), haken10.boostEtageBoss(10));//4 runes --- 5 runes


      this.tour[7] = new Etage(new Fuco(""), new Cuivre(""), new XiaoLin(""), new Stella(""), new Kaito(""), new Chasun(""));
      this.tour[8] = new Etage(new Haken(""), new Artamiel(""), new Thrain(""));
      this.tour[9] = new Etage(new Eludia(""), new Oberon(""), new Ethna(""), new Nephthys(""), new Zaiross(""), new Cichlid(""));
      this.tour[10] = new Etage(new Sian(""), new Woonhak(""), new Chasun(""));
      this.tour[11] = new Etage(new Cadiz(""), new Zeratu(""), new XiongFei(""), new Tablo(""), new Erwin(""), new Seara(""));
      this.tour[12] = new Etage(new Thebae(""), new Ritesh(""), new Sige(""), new Chasun(""), new Basalt(""), new Veromos(""));
      this.tour[13] = new Etage(icaru20.boostEtageBoss(20), eshir20.boostEtageBoss(20), nicki20.boostEtageBoss(20));


      this.tour[14] = new Etage(new Ethna(""), new Erwin(""), new Basalt(""), new Fuco(""), new Cadiz(""), new Zaiross(""));
      this.tour[15] = new Etage(new Zeratu(""), new XiongFei(""), new Sige(""), new Icaru(""), new Nephthys(""), new Stella(""));
      this.tour[16] = new Etage(new Oberon(""), new Veromos(""), new Okeanos(""));
      this.tour[17] = new Etage(new Kaito(""), new Nicki(""), new XiaoLin(""));
      this.tour[18] = new Etage(new Seara(""), new Haken(""), new Okeanos(""), new Artamiel(""), new Cuivre(""), new Sian(""));
      this.tour[19] = new Etage(new Cichlid(""), new Thrain(""), new Ritesh(""));
      this.tour[20] = new Etage(tablo30.boostEtageBoss(30), eludia30.boostEtageBoss(30), woonhak30.boostEtageBoss(30));


      this.tour[21] = new Etage(new Eludia(""), new Woonhak(""), new Seara(""), new Sian(""), new Erwin(""), new Ethna(""));
      this.tour[22] = new Etage(new Tablo(""), new Oberon(""), new Basalt(""), new Icaru(""), new Ritesh(""), new XiongFei(""));
      this.tour[23] = new Etage(new Thebae(""), new Zeratu(""), new Nicki(""), new Cadiz(""), new Eshir(""), new Sige(""));
      this.tour[24] = new Etage(new Thrain(""), new Cuivre(""), new Haken(""));
      this.tour[25] = new Etage(new XiaoLin(""), new Okeanos(""), new Fuco(""));
      this.tour[26] = new Etage(cichlid40.boostEtageBoss(40), kaito40.boostEtageBoss(40), nephthys40.boostEtageBoss(40), veromos40.boostEtageBoss(40), zaiross40.boostEtageBoss(40), stella40.boostEtageBoss(40));


      this.tour[27] = new Etage(new Thebae(""), new Oberon(""), new Stella(""));
      this.tour[28] = new Etage(new Icaru(""), new Ritesh(""), new Nephthys(""), new XiaoLin(""), new Woonhak(""), new Artamiel(""));
      this.tour[29] = new Etage(new Sian(""), new Erwin(""), new Haken(""));
      this.tour[30] = new Etage(new Eludia(""), new Okeanos(""), new Sige(""), new Veromos(""), new Kaito(""), new Nicki(""));
      this.tour[31] = new Etage(new Cichlid(""), new Zaiross(""), new XiongFei(""), new Tablo(""), new Zeratu(""), new Basalt(""));
      this.tour[32] = new Etage(new Thrain(""), new Eshir(""), new Ethna(""));
      this.tour[33] = new Etage(cadiz50.boostEtageBoss(50), cuivre50.boostEtageBoss(50), seara50.boostEtageBoss(50));


      this.tour[34] = new Etage(new Chasun(""), new Artamiel(""), new Woonhak(""), new Cichlid(""), new Thebae(""), new Imleryth(""));


/*
* EQUIPEMENT DES RUNES DE TOUS LES PERSOS DE LA TOUR EN LISANT LE FICHIER DE SAUVEGARDE
*/
      int etagesdispo = 35;
      this.init.ouvrir();
      for (int i = 2; i<etagesdispo; i++) {
        for (int j = 1; j<7; j++) {
          if (this.tour[i].getPersoSalle(j) != null) {
            if (i == 2 && j < 4) {
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
            } else
            if (i == 2 && j >= 4) {
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
            } else
            if (i == 3) {
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
            } else
            if (i == 4 || i == 5 || (i == 6 && j < 4)) {
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
            } else {
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
              this.tour[i].getPersoSalle(j).equiperRune(Application.stringToRune(this.init.lire()));
            }
          }
        }
      }

      this.init.fermer();

      // // AFFICHE LES RUNES DE TOUS LES PERSOS DE LA TOUR
      // for (int i = 0; i<etagesdispo; i++) {
      //   for (int j = 1; j<7; j++) {
      //     if (this.tour[i].getPersoSalle(j) != null) {
      //       System.out.println(this.tour[i].getPersoSalle(j).toStringRunes());
      //       System.out.println("----------------");
      //     }
      //   }
      //   System.out.println("___________________________________");
      // }

      Personnage p;
      for (int i = 0; i<etagesdispo; i++) {
       for (int j = 1; j<7; j++) {
         p = this.tour[i].getPersoSalle(j);
         if (p != null) {
           p.malusParceQueTropFort();
         }
       }
     }





/*
* APPLICATION DES EFFETS DES RUNES DE TOUS LES PERSOS
*/
      for (int i = 2; i<etagesdispo; i++) {
        for (int j = 1; j<7; j++) {
          if (this.tour[i].getPersoSalle(j) != null) {
            this.tour[i].getPersoSalle(j).appliquerEffetsRunes();
            this.tour[i].getPersoSalle(j).reajusteCarac();
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Erreur Tour : "+e.getMessage());
    }
  }//fin constructeur

  public Personnage getPerso(int etage, int salle) {
    return this.tour[etage-1].getPersoSalle(salle);
  }

  public Etage getEtage(int etage) {
    return this.tour[etage-1];
  }

  public void resetEtage(int etage) {//permet de reset les caractéristiques des persos de l'étage
    this.tour[etage-1].reset();
  }
}
