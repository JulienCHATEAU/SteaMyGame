Auteur : Julien CHATEAU

_Ce document est à lire attentivement et vous permettra de comprendre le fonctionnement de ce jeu._

__SteaMyGame__

## Prérequis

Vous devez posséder la version la plus récente de Java, téléchargeable [ici](https://www.java.com/fr/download/)

## L'initialisation et le lancement du jeu

Une fois que vous avez téléchargé l'archive *SteaMyGame-master.zip* via le bouton vert *clone or download*, il faut tout extraire (**Clique droit sur l'archive** --> **Extraire tout...** --> **Extraire**). Dans le dossier créé, double-cliquer sur le script *installer* qui initialisera le jeu.

Vous n'aurez qu'à supprimer le dossier *SteaMyGame-master* qui est maintenant vide et à double-cliquer sur le raccourcis *SteaMyGame.lnk*, fraichement créé sur votre bureau, pour lancer le jeu !


Si le dossier *SteaMyGame-master* n'est pas vide, l'initialisation n'a pas fonctionner mais vous pouvez quand même lancer le jeu avec le raccourcis *SteaMyGame.lnk* (sans icone)

Si le raccourcis n'est pas créer sur votre bureau, Allez dans *Ce PC*, dans l'onglet *Périphériques et lecteurs*, allez dans *OS (C:)*. Dans le dossier *SteaMyGame*, vous trouverez le raccourcis (avec icone) que vous pourrez récupérer.

Il serait préférable de me contacter (`steamygamecontact@gmail.com`) si une erreur se produit pour que je puisse adapter/corriger mon launcher d'initialisation.

## La compilation

Si vous êtes dans la capacité de modifier le code source, la compilation de l'ensemble des classes se fait de la manière suivante :

`$javac -cp bin -d bin -encoding UTF-8 src/X/*.java`  
(avec X le répertoire dans lequel se trouve la(les) classe(s) que vous avez modifiée(s))

Pour compiler tous les fichiers d'un coup (possible seulement sur Linux) :  
`$shopt -s globstar`
`$javac -cp bin -d bin -encoding UTF-8 src/**/*.java`

## Les mécaniques du jeu

   Pour jouer à ce jeu vous devez vous créer un compte avec un login et un mot de passe (à ne pas oublier).
Lorsque vous vous connectez à votre compte pour la première fois, vous devez choisir un personnage parmi les 32 disponibles.

**/!\\ VOUS NE POUVEZ PAS CHANGER DE PERSONNAGE APRÈS VOTRE CHOIX /!\\**

Après une longue réflexion (aidée par le guide des personnages plus bas) et après avoir fait votre choix, vous arrivez sur l'interface de l'inventaire.
Le jeu peut commencer !!


### Synopsis, déroulement et règles du jeu
Après un voyage de plusieurs jours, vous arrivez devant la gigantesque **Tour Celeste**. Vous avez été envoyé par le roi de votre village pour vaincre Imleryth,
le démon de glace, qui est sur le point de ravager votre monde depuis le haut de cette tour. Cependant cela ne va pas être chose facile car il vous
faudra d'abord gravir les 50 étages de cette tour. De plus, durant votre voyage vous avez été attaqué par un groupe de bandit qui vous a dérobé tout votre
équipement. Vous allez donc commencer votre ascenssion sans rien dans les poches (ou peut-être pas). Chaque étages est composées de 3 salles et d'un monstre par salle.
Plus vous avancerez dans la tour, plus les monstres seront fort. Les combats se déroulent en tour par tour.


### La Tour :
* Dès que vous battez un monstre vous gagnez des **$teamy**, l'argent du jeu.
* Si vous perdez ou abandonnez un combat, vous perdez de l'argent et devez recommencer tout l'étage.
* Si vous battez les 3 monstres d'un étage à la suite, vous gagnez une rune (voir explication sur les runes plus bas).
* Entre chaque salle vous regagnez 75% de vos points de vie manquant.
* Tous les 10 étages les monstres sont plus fort que la normale et vous gagnerez une rune supplémentaire à la première victoire.
* Vous ne pouvez refaire que les 4 derniers étages que vous avez vaincu et gagnerez deux fois moins de $teamy.


### Les Runes :
* Vous pouvez équiper jusqu'a 5 runes.
* Vous pouvez vendre une rune contre des $teamy.
* Une rune peut être positionnée de 5 façons différentes (en position 0, 1, 2, 3 ou 4).
* Il existe 6 types de runes différents ayant leur propre bonus :
    * **Fatale** (+20% d'attaque)
    * **Energie** (+20% de points de vie)
    * **Gardien** (+20% de défense)
    * **Swift** (+22% de vitesse)
    * **Rage** (+26% de dégats critiques)
    * **Lame** (+12% de taux critiques)
* Si une rune est en position 0, le bonus est activé une fois.
* Sinon le bonus est activé en équipant 2 runes du même type sur les autres positions (cumulable 2 fois => 3 fois max en tout).
* Dans l'inventaire une rune aura un affichage du style : TypePosition (ex : R3 décrit une rune Rage de position 3)
* Une rune possède un stat principale (détail de la stat principal en fonction de la position plus bas) et de 1 à 4 stats secondaires.
* Les stats existantes (flat et/ou en %) sont :
    * **L'attaque** (flat et en %) --> Att/Att%
    * **La défense** (flat et en %) --> Def/Def%
    * **Les points de vie** (flat et en %) --> Hp/Hp%
    * **La vitesse** (flat) --> Vit
    * **Les taux de critiques** --> Tx crit.
    * **Les dégats critiques** (en %) --> Dgts crit.
* Initialement un rune possède 1 stat principale et 1 stat secondaire.
* Un rune possède aussi un niveau allant de 1 à 20 et peut être amélioré contre des $teamy.
* Chaque amélioration augmente un peu la stat principale.
* Tout les 5 niveaux vous gagnez une stat secondaire aléatoire supplémentaire.
* Si vous atteignez le niveau maximum d'une rune, totues ses stats secondaires seront augmentées de 50%.
* Les stats principales possibles en fonction de la position de la rune :
    * **0** : Toutes
    * **1** : Att, Hp, Def
    * **2** : Att%, Hp%, Def%, Vit
    * **3** : Tx crit, Dgts crit, Att%, Hp%, Def%
    * **4** : Att, Hp, Def, Att%, Hp%, Def%


### Les Personnages :
* Un personnage possède les 6 caractéristiques précédement vu.
* La valeur de ses caractéristiques varie en fonction du type du personnage.
* Un personnage possède 4 sorts ayant un cooldown (nombre de tours sans pouvoir utiliser un sort après utilisation) qui se réduit chaque tour.
* Le sort n°1 n'a pas de cooldown et le sort n°4 peut être un passif (non lançable) pour certain personnage.
* Les sorts peuvent appliquer à l'ennemi des effets nocifs et vous procurer des effets bénéfiques, détaillés ci dessous.


### Les Effets Nocifs (appliqué pendant 1 à 3 tours sur la cible) :
* **Break Att** : Réduit l'attaque.
* **Break Def** : Réduit la défense.
* **Break Spd** : Réduit la vitesse.
* **Anti-Heal** : Empèche de se procurer toute sorte de soin.
* **Marque** : Augmente les dégats reçu de 25%.
* **Silence** : Oblige l'utilisation du sort n°1.
* **Etourdissement** : Empèche de jouer.
* **Bombe** : 2 tours après application, inflige des dégats et étourdis avec 35%.
* **Degats Continus** : Chaque tour, inflige des dégats équivalents à 5% des points de vie.

### Les Effets Bénéfiques (appliqué pendant 1 à 3 tours sur la cible):
* **Buff Att** : Augmente l'attaque.
* **Buff Def** : Augmente la défense.
* **Buff Spd** : Augmente la vitesse.
* **Buff Crit** : Augmente les taux critiques.
* **Immunité** : Vous résistez à tout effets nocifs.
* **Invincibilité** : Vous ne subissez aucun dégat.
* **Contre-Attaque** : Si vous subissez des dégats (directs) de l'ennemi, vous contre-attaquez avec votre sort n°1.

PS : Si vous remarquez quelque chose semblant être erroné n'hésitez pas à me le partager, je me ferai un plaisir de le corriger
`steamygamecontact@gmail.com`
