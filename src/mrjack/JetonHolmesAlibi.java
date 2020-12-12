package mrjack;
import java.util.Scanner;

public class JetonHolmesAlibi extends JetonAction
{
	private Scanner scanner = new Scanner(System.in);

	public JetonHolmesAlibi() {
		this.mNom1 = "Holmes";
		this.mNom2 = "Alibi";
		this.mPileFace = -1;
	}

	public void fonction1(Plateau plateau) {
		int nb = 0;

		System.out.println("Tapez 1 ou 2 pour avancer de 1 ou 2 cases avec Holmes");
		while (nb != 1 && nb != 2) {
			nb = scanner.nextInt();
			if (nb != 1 && nb != 2)
				System.out.println("Veuillez retaper 1 ou 2 :");
		}
		if (plateau.mDetective[0].getPosition() == 10 && nb == 2)
			plateau.mDetective[0].setPosition(0);
		else if (plateau.mDetective[0].getPosition() == 11)
			plateau.mDetective[0].setPosition(nb - 1);
		else
			plateau.mDetective[0].setPosition(plateau.mDetective[0].getPosition() + nb);
	}

	public void fonction2(Plateau plateau) {
		// Ecrire code ici
	}
}
