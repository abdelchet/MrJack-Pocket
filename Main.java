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

		plateau.displayPlateau();
		for (int nbTour = 1 ; nbTour <= 8 ; nbTour++) {
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

			plateau.changeTour();
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

			System.out.println("C'est au tour de " + joueur[(nbTour + 1) % 2]
                                        + ", voici les jetons actions :");
                        for (int i = 0 ; i < 2 ; i++) {
                                if (jetonFlag.get(i).getPileFace() == 1)
                                        System.out.println(i + " : " + jetonFlag.get(i).getNom1());
                                else
                                        System.out.println(i + " : " + jetonFlag.get(i).getNom2());
                        }
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

			plateau.changeTour();
			System.out.println("C'est au tour de " + joueur[nbTour % 2]
                                        + ", voici le jetons action restant :");
			if (jetonFlag.get(0).getPileFace() == 1)
                        	System.out.println(0 + " : " + jetonFlag.get(0).getNom1());
                        else
                        	System.out.println(0 + " : " + jetonFlag.get(0).getNom2());
			if (jetonFlag.get(0).getPileFace() == 1)
                                jetonFlag.get(0).fonction1(plateau);
                        else
                                jetonFlag.get(0).fonction2(plateau);
                        plateau.displayPlateau();
                        jetonFlag.remove(jetonFlag.get(0));

			update(plateau);
			plateau.displayPlateau();
			if (end(plateau, nbTour) == true)
				break;
			plateau.changeTour();
		}
	}

	public static void update(Plateau plateau) {
		ArrayList<Integer> quartierVisible = new ArrayList<Integer>();
		ArrayList<Integer> quartierInVisible = new ArrayList<Integer>();
		int j = 0;
		int index;

		for (int i = 0 ; i < 9 ; i++) {
			if (quartierIsVisible(plateau, i / 3, i % 3))
				quartierVisible.add(i);
			else
				quartierInVisible.add(i);
		}
		System.out.println(quartierVisible); //
		System.out.println(quartierInVisible); //
		System.out.println(plateau.getNbCase0()); //
		plateau.mMechant.getCarteMechant().displayPersonnage(); //
		if (!returnVisible(plateau, quartierVisible)) {
			for (int i = 0 ; i < quartierVisible.size() ; i++) {
				index = quartierVisible.get(i);
				if (plateau.mPlateau[index / 3][index % 3].getPileFace() == 0) {
					plateau.mPlateau[index / 3][index % 3].setPileFace(1);
					j++;
				}
			}
			plateau.mMechant.setSablier(plateau.mMechant.getSablier() + 1);
			plateau.mMechant.setSablierReel(plateau.mMechant.getSablierReel() + 1);
			plateau.setNbCase0(plateau.getNbCase0() - j);
		}
		else {
			for (int i = 0 ; i < quartierInVisible.size() ; i++) {
				index = quartierInVisible.get(i);
				if (plateau.mPlateau[index / 3][index % 3].getPileFace() == 0) {
					plateau.mPlateau[index / 3][index % 3].setPileFace(1);
					j++;
				}
			}
			plateau.setNbCase0(plateau.getNbCase0() - j);
		}
	}

	public static boolean quartierIsVisible(Plateau plateau, int i, int j) {
		for (int or = 0 ; or < 4 ; or++) {
			int j2 = j;
			int i2 = i;
			if (plateau.mPlateau[i][j].getPosition() == or) ;
			else if (or == 0) {
				i2--;
				while (i2 >= 0) {
					if (plateau.mPlateau[i2][j2].getPosition() == 0
							|| plateau.mPlateau[i2][j2].getPosition() == 2)
						i2 = -10;
					i2--;
				}
				i2++;
				if (isDetective(plateau, i2, j2, or) && i2 != -10)
					return (true);
			}
			else if (or == 1) {
				j2++;
				while (j2 <= 2) {
					if (plateau.mPlateau[i2][j2].getPosition() == 1
							|| plateau.mPlateau[i2][j2].getPosition() == 3)
						j2 = 10;
					j2++;
				}
				j2--;
				if (isDetective(plateau, i2, j2, or) && j2 != 10)
					return (true);
			}
			else if (or == 2) {
				i2++;
				while (i2 <= 2) {
					if (plateau.mPlateau[i2][j2].getPosition() == 0
							|| plateau.mPlateau[i2][j2].getPosition() == 2)
						i2 = 10;
					i2++;
				}
				i2--;
				if (isDetective(plateau, i2, j2, or) && i2 != 10)
					return (true);
			}
			else {
				j2--;
				while (j2 >= 0) {
					if (plateau.mPlateau[i2][j2].getPosition() == 1
							|| plateau.mPlateau[i2][j2].getPosition() == 3)
						j2 = -10;
					j2--;
				}
				j2++;
				if (isDetective(plateau, i2, j2, or) && j2 != -10)
					return (true);
			}
		}
		return (false);
	}

	public static boolean isDetective(Plateau plateau, int i, int j, int or) {
		if (i == 0 && j == 0 && or == 0)
			return (isDetective2(plateau, 0));
		else if (i == 0 && j == 0 && or == 3)
			return (isDetective2(plateau, 11));
		else if (i == 0 && j == 1)
			return (isDetective2(plateau, 1));
		else if (i == 0 && j == 2 && or == 0)
			return (isDetective2(plateau, 2));
		else if (i == 0 && j == 2 && or == 1)
			return (isDetective2(plateau, 3));
		else if (i == 1 && j == 0)
			return (isDetective2(plateau, 10));
		else if (i == 1 && j == 2)
			return (isDetective2(plateau, 4));
		else if (i == 2 && j == 0 && or == 3)
			return (isDetective2(plateau, 9));
		else if (i == 2 && j == 0 && or == 2)
			return (isDetective2(plateau, 8));
		else if (i == 2 && j == 1)
			return (isDetective2(plateau, 7));
		else if (i == 2 && j == 2 && or == 1)
			return (isDetective2(plateau, 5));
		else if (i == 2 && j == 2 && or == 2)
			return (isDetective2(plateau, 6));
		return (false);
	}

	public static boolean isDetective2(Plateau plateau, int pos) {
		if (plateau.mDetective[0].getPosition() == pos
				|| plateau.mDetective[1].getPosition() == pos
				|| plateau.mDetective[2].getPosition() == pos)
			return (true);
		return (false);
	}

	public static boolean returnVisible(Plateau plateau, ArrayList<Integer> quartierV) {
		for (int i = 0 ; i < quartierV.size() ; i++) {
			if (plateau.mPlateau[quartierV.get(i) / 3][quartierV.get(i) % 3].getPerso()
					== plateau.mMechant.getCarteMechant())
				return (true);
		}
		return (false);
	}

	public static boolean end(Plateau plateau, int nbTour) {
		// 8eme tour et il y a + de 1 quartier visible -> MrJack
		if (nbTour >= 8 && plateau.getNbCase0() > 1) {
			System.out.println("MrJack gagne car nous sommes au 8eme tour et que les detectives ne l'ont pas trouve");
			return (true);
		}
		// 8eme tour et il y a 1 quartier visible mais pas par un des detectives + 6 sabliers -> MrJack
		else if (nbTour >= 8 && plateau.getNbCase0() <= 1 && plateau.mMechant.getSablierReel() >= 6
				&& !mechantSee(plateau)) {
			System.out.println("MrJack gagne car nous sommes au 8eme tour et que les detectives ne l'ont pas trouve");
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

	public static boolean mechantSee(Plateau plateau) {
		int i2 = 0;
		int j2 = 0;

		for (int i = 0 ; i < 3 ; i++) {
			for (int j = 0 ; j < 3 ; j++) {
				if (plateau.mPlateau[i][j].getPerso() == plateau.mMechant.getCarteMechant()) {
					i2 = i;
					j2 = j;
				}
			}
		}
		if (quartierIsVisible(plateau, i2, j2))
			return (true);
		return (false);
	}
}
