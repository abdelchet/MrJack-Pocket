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
		if (plateau.getTour() == 0) { // detective
			System.out.print("Carte piochee : ");
			plateau.mTasCarte.get(0).displayPersonnage();
			int i2 = 0;
                	int j2 = 0;

                	for (int i = 0 ; i < 3 ; i++) {
                        	for (int j = 0 ; j < 3 ; j++) {
                                	if (plateau.mPlateau[i][j].getPerso() == plateau.mTasCarte.get(0)) {
                                        	i2 = i;
                                        	j2 = j;
                                	}
                        	}
                	}
			if (plateau.mPlateau[i2][j2].getPileFace() == 0) {
				plateau.mPlateau[i2][j2].setPileFace(1);
				if (plateau.mPlateau[i2][j2].getPerso().getNom() == "Gris") {
					plateau.mPlateau[i2][j2].setPileFace(-1);
					plateau.mPlateau[i2][j2].setPosition(-1);
				}
				plateau.setNbCase0(plateau.getNbCase0() - 1);
			}
			plateau.mTasCarte.remove(0);
		}
		else {
			System.out.println("Secret - Carte piochee : ");
			plateau.mTasCarte.get(0).displayPersonnage();
			plateau.mMechant.setSablierReel(plateau.mMechant.getSablierReel() + plateau.mTasCarte.get(0).getSablier());
			plateau.mTasCarte.remove(0);
		}
	}
}
