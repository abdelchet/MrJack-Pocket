package mrjack;
import java.util.Scanner;

public class JetonWatsonLeChien extends JetonAction
{
	private Scanner scanner = new Scanner(System.in);

	public JetonWatsonLeChien() {
		this.mNom1 = "Watson";
		this.mNom2 = "LeChien";
		this.mPileFace = -1;
	}

	public void fonction1(Plateau plateau) {
		int nb = 0;

		System.out.println("Tapez 1 ou 2 pour avancer de 1 ou 2 cases avec Watson");
		while (nb != 1 && nb != 2) {
			nb = scanner.nextInt();
			if (nb != 1 && nb != 2)
				System.out.println("Veuillez retaper 1 ou 2 :");
		}
		if (plateau.mDetective[1].getPosition() == 10 && nb == 2)
			plateau.mDetective[1].setPosition(0);
		else if (plateau.mDetective[1].getPosition() == 11)
			plateau.mDetective[1].setPosition(nb - 1);
		else
			plateau.mDetective[1].setPosition(plateau.mDetective[1].getPosition() + nb);
	}

	public void fonction2(Plateau plateau) {
		int nb = 0;

		System.out.println("Tapez 1 ou 2 pour avancer de 1 ou 2 cases avec Monsieur Le Chien");
		while (nb != 1 && nb != 2) {
			nb = scanner.nextInt();
			if (nb != 1 && nb != 2)
				System.out.println("Veuillez retaper 1 ou 2 :");
		}
		if (plateau.mDetective[2].getPosition() == 10 && nb == 2)
			plateau.mDetective[2].setPosition(0);
		else if (plateau.mDetective[2].getPosition() == 11)
			plateau.mDetective[2].setPosition(nb - 1);
		else
			plateau.mDetective[2].setPosition(plateau.mDetective[2].getPosition() + nb);
	}
}
