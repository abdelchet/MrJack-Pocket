import mrjack.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main
{
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Plateau plateau = new Plateau();
		int nb;

		ArrayList<JetonAction> jeton = new ArrayList<JetonAction>();
		jeton.add(new JetonHolmesAlibi());
		jeton.add(new JetonRotationEchange());
		jeton.add(new JetonRotationJoker());
		jeton.add(new JetonWatsonLeChien());

		String[] joueur = new String[2];
		joueur[0] = "MrJack";
		joueur[1] = "M. Le Detective";

		for (int nbTour = 1 ; nbTour <= 8 ; nbTour++) {
			plateau.displayPlateau();
			if (nbTour % 2 == 1) // impair
				for (int i = 0 ; i < 4 ; i++) jeton.get(i).ftHasard();
			else // pair
				for (int i = 0 ; i < 4 ; i++) jeton.get(i).changePileFace();
			ArrayList<JetonAction> jetonFlag = (ArrayList<JetonAction>)jeton.clone();

			System.out.println("C'est au tour de " + joueur[nbTour % 2]
					+ ", voici les jetons actions :");
			for (int i = 0 ; i < 4 ; i++) {
				if (jetonFlag.get(i).getPileFace() == 1)
					System.out.println(i + " : " + jetonFlag.get(i).getNom1());
				else
					System.out.println(i + " : " + jetonFlag.get(i).getNom2());
			}
			nb = -1;
			System.out.println("Tapez de 0 a 3 pour choisir le jeton action a piocher");
			while (nb != 0 && nb != 1 && nb != 2 && nb != 3) {
				nb = scanner.nextInt();
				if (nb != 0 && nb != 1 && nb != 2 && nb != 3)
					System.out.println("Veuillez retaper :");
			}
			if (jetonFlag.get(nb).getPileFace() == 1)
				jetonFlag.get(nb).fonction1(plateau);
			else
				jetonFlag.get(nb).fonction2(plateau);
			plateau.displayPlateau();
			jetonFlag.remove(jetonFlag.get(nb));

			System.out.println("C'est au tour de " + joueur[(nbTour + 1) % 2]
					+ ", voici les jetons actions :");
			for (int i = 0 ; i < 3 ; i++) {
				if (jetonFlag.get(i).getPileFace() == 1)
					System.out.println(i + " : " + jetonFlag.get(i).getNom1());
				else
					System.out.println(i + " : " + jetonFlag.get(i).getNom2());
			}
			nb = -1;
			System.out.println("Tapez de 0 a 2 pour choisir le jeton action a piocher");
			while (nb != 0 && nb != 1 && nb != 2) {
				nb = scanner.nextInt();
				if (nb != 0 && nb != 1 && nb != 2)
					System.out.println("Veuillez retaper :");
			}
			if (jetonFlag.get(nb).getPileFace() == 1)
				jetonFlag.get(nb).fonction1(plateau);
			else
				jetonFlag.get(nb).fonction2(plateau);
			plateau.displayPlateau();
			jetonFlag.remove(jetonFlag.get(nb));

			nb = -1;
			System.out.println("Tapez de 0 a 1 pour choisir le jeton action a piocher");
			while (nb != 0 && nb != 1) {
				nb = scanner.nextInt();
				if (nb != 0 && nb != 1)
					System.out.println("Veuillez retaper :");
			}
			if (jetonFlag.get(nb).getPileFace() == 1)
				jetonFlag.get(nb).fonction1(plateau);
			else
				jetonFlag.get(nb).fonction2(plateau);
			plateau.displayPlateau();
			jetonFlag.remove(jetonFlag.get(nb));

			System.out.println("C'est au tour de " + joueur[nbTour % 2]
                                        + ", voici le jetons action restant :");
                        System.out.println("0 : " + jetonFlag.get(0).getNom1());
			if (jetonFlag.get(nb).getPileFace() == 1)
                                jetonFlag.get(nb).fonction1(plateau);
                        else
                                jetonFlag.get(nb).fonction2(plateau);
                        plateau.displayPlateau();
                        jetonFlag.remove(jetonFlag.get(nb));

			// Retourner cartes visibles
			update(plateau);
			// Regarder si y'en a pas un qui a gagne
			if (end(plateau, nbTour) == true)
				break;
		}
	}

	public static void update(Plateau plateau) {
		;
	}

	public static boolean end(Plateau plateau, int nbTour) {
		// 8eme tour et il y a + de 1 quartier visible -> MrJack
		if (nbTour >= 8 && plateau.getNbCase0() > 1) {
			System.out.println("MrJack gagne car nous sommes au 8eme tour et que les detectives ne l'ont pas trouve");
			return (true);
		}
		// 8eme tour et il y a 1 quartier visible mais pas par un des detectives + 6 sabliers -> MrJack
		else if (nbTour >= 8 && plateau.getNbCase0() <= 1 && plateau.mMechant.getSablierReel() >= 6/*&& le quartier pas visible par les detectives*/) {
			System.out.println("MrJack gagne car nous sommes au 8eme tour et que les detectives ne l'ont pas trouve");
			return (true);
		}
		// Seulement 1 quartier visible par les detectives et c'est MrJack -> Detective
		else if (plateau.getNbCase0() <= 1) {//&& visible par detective && c'est MrJack) {
			System.out.println("Le detective gagne car il ne reste qu'un quartier et que MrJack ne possede pas 6 sabliers");
			return (true);
		}
		// 6 sablier et + de 1 quartier visible -> MrJack
		else if (plateau.getNbCase0() > 1 && plateau.mMechant.getSablierReel() >= 6) {
			System.out.println("MrJack gagne car il possede plus de 6 sabliers");
			return (true);
		}
		// Plus que 1 quartier visible (par forcement par les detectives) + moins de 6 sabliers -> Detective
		else if (plateau.getNbCase0() <= 1 && plateau.mMechant.getSablierReel() < 6) {
			System.out.println("Le detective gagne car il ne reste qu'un quartier et que MrJack ne possede pas 6 sabliers");
			return (true);
		}
		return (false);
	}
}
