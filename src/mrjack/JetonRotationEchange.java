package mrjack;
import java.util.Scanner;

public class JetonRotationEchange extends JetonAction
{
	private int mPileFace; // 0 = Rotation, 1 = Joker et -1 = Rien du tout
	private Scanner scanner = new Scanner(System.in);

	public JetonRotationEchange() {
		this.mNom1 = "Rotation";
		this.mNom2 = "Echange";
		this.mPileFace = -1;
	}

	public void fonctionRotation(Plateau plateau) {
		int nb = 0;
		int rota = -1;
		int i;
		int j;

		System.out.println("Tapez un nombre entre 1 et 9 pour selection la case a tourner (1 etant haut / gauche et 9 etant bas / droit)");
		while (!(nb > 0 && nb < 10)) {
			nb = scanner.nextInt();
			if (!(nb > 0 && nb < 10))
				System.out.println("Veuillez retaper un nombre entre 1 et 9");
		}
		System.out.println("Tapez un nombre entre 1 et 4 pour orienter le mur (1 = nord, 2 = est, 3 = sud et 4 = ouest)");
		while (!(rota > 0 && rota < 5)) {
			rota = scanner.nextInt();
			if (!(rota > 0 && rota < 5))
				System.out.println("Veuillez retaper un nombre entre 1 et 4");
		}
		i = (nb - 1) / 3;
		j = (nb - 1) % 3;
		plateau.mPlateau[i][j].setPosition(rota - 1);
	}

	public void fonctionEchange(Plateau plateau) {
		int quartier1 = 0;
		int quartier2 = 0;
		int i;
		int j;
		int i2;
		int j2;

		System.out.println("Tapez un nombre entre 1 et 9 pour selection la premiere case a echanger (1 etant haut / gauche et 9 etant bas / droit)");
		while (!(quartier1 > 0 && quartier1 < 10)) {
			quartier1 = scanner.nextInt();
			if (!(quartier1 > 0 && quartier1 < 10))
				System.out.println("Veuillez retaper un nombre entre 1 et 9");
		}

		System.out.println("Tapez un nombre entre 1 et 9 pour selection la premiere case a echanger (1 etant haut / gauche et 9 etant bas / droit)");
		while (!(quartier2 > 0 && quartier2 < 10)) {
			quartier2 = scanner.nextInt();
			if (!(quartier2 > 0 && quartier2 < 10))
				System.out.println("Veuillez retaper un nombre entre 1 et 9");
		}
		i = (quartier1 - 1) / 3;
		j = (quartier1 - 1) % 3;
		i2 = (quartier2 - 1) / 3;
		j2 = (quartier2 - 1) % 3;
		Quartier flag = plateau.mPlateau[i][j];
		plateau.mPlateau[i][j] = plateau.mPlateau[i2][j2];
		plateau.mPlateau[i2][j2] = flag;
	}
}
