package mrjack;
import java.awt.Color;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

public class Plateau
{
	private final String ROSE = "\033[35m";
	private final String NOIR = "\033[31m";
	private final String ORANGE = "\033[1m\033[31m";
	private final String VIOLET = "\033[2m\033[35m";
	private final String VERT = "\033[32m";
	private final String JAUNE = "\033[33m";
	private final String BLEU = "\033[34m";
	private final String BLANC = "\033[37m";
	private final String GRIS = "\033[1m\033[30m";
	private final String RESET = "\033[0m";
	private final String ROUGE = "\033[31m";

	public Detective[] mDetective = new Detective[3];
	public Quartier[][] mPlateau = new Quartier[3][3];
	public ArrayList<Personnage> mTasCarte;
	public Mechant mMechant;
	private int mNbCase0;
	private int mTour; // 0 correspond au tour des detective et 1 au tour du mechant

	public Plateau() {
		Random rand = new Random();
		this.mTour = 0;
		this.mNbCase0 = 9;

		mDetective[0] = new Detective("Holmes", 11);
		mDetective[1] = new Detective("Watson", 3);
		mDetective[2] = new Detective("M. Le Chien", 7);

		mTasCarte = new ArrayList<Personnage>();
		mTasCarte.add(new Personnage("Rose", 2));
		mTasCarte.add(new Personnage("Blanc", 1));
		mTasCarte.add(new Personnage("Orange", 1));
		mTasCarte.add(new Personnage("Violet", 1));
		mTasCarte.add(new Personnage("Vert", 1));
		mTasCarte.add(new Personnage("Jaune", 1));
		mTasCarte.add(new Personnage("Gris", 1));
		mTasCarte.add(new Personnage("Noir", 0));
		mTasCarte.add(new Personnage("Bleu", 0));
		Collections.shuffle(mTasCarte);

		mPlateau[0][0] = new Quartier(3, mTasCarte.get(0));
		mPlateau[0][1] = new Quartier(rand.nextInt(4), mTasCarte.get(1));
		mPlateau[0][2] = new Quartier(1, mTasCarte.get(2));
		mPlateau[1][0] = new Quartier(rand.nextInt(4), mTasCarte.get(3));
		mPlateau[1][1] = new Quartier(rand.nextInt(4), mTasCarte.get(4));
		mPlateau[1][2] = new Quartier(rand.nextInt(4), mTasCarte.get(5));
		mPlateau[2][0] = new Quartier(rand.nextInt(4), mTasCarte.get(6));
		mPlateau[2][1] = new Quartier(2, mTasCarte.get(7));
		mPlateau[2][2] = new Quartier(rand.nextInt(4), mTasCarte.get(8));

		Collections.shuffle(mTasCarte);
		mMechant = new Mechant(mTasCarte.get(0));
		mTasCarte.remove(0);
	}

	public void setNbCase0(int nbCase0) {
		this.mNbCase0 = nbCase0;
	}

	public int getNbCase0() {
		return (this.mNbCase0);
	}

	public void changeTour() {
		this.mTour = (this.mTour == 0) ? 1 : 0;
	}

	public int getTour() {
		return (this.mTour);
	}

	public void displayPlateau() {
		String couleur = RESET;
		String signe = "   ";

		System.out.print("\n\n      ");
		System.out.print(ftRond(0) + "      " + ftRond(1) + "      " + ftRond(2) + " \n   ");
		for (int i = 0 ; i < 3 ; i++) {
			for (int j = 0 ; j < 3 ; j++) {
				if (mPlateau[i][j].getPosition() == 0)
					System.out.print("█████████");
				else
					System.out.print("███   ███");
			}
			System.out.print("\n" + ftRond(11 - i));
			for (int j = 0 ; j < 3 ; j++) {
				if (mPlateau[i][j].getPileFace() == 0) {
					if (mPlateau[i][j].getPerso().getNom().equals("Rose"))
						couleur = ROSE;
					else if (mPlateau[i][j].getPerso().getNom().equals("Noir"))
						couleur = NOIR;
					else if (mPlateau[i][j].getPerso().getNom().equals("Orange"))
						couleur = ORANGE;
					else if (mPlateau[i][j].getPerso().getNom().equals("Violet"))
						couleur = VIOLET;
					else if (mPlateau[i][j].getPerso().getNom().equals("Vert"))
						couleur = VERT;
					else if (mPlateau[i][j].getPerso().getNom().equals("Jaune"))
						couleur = JAUNE;
					else if (mPlateau[i][j].getPerso().getNom().equals("Bleu"))
						couleur = BLEU;
					else if (mPlateau[i][j].getPerso().getNom().equals("Blanc"))
						couleur = BLANC;
					else
						couleur = GRIS;
				}
				if (mPlateau[i][j].getPileFace() == 0)
					signe = "■■■";
				else
					signe = "   ";
				if (mPlateau[i][j].getPosition() == 1)
					System.out.print("   " + couleur + signe + "\033[0m" + "███");
				else if (mPlateau[i][j].getPosition() == 3)
					System.out.print("███" + couleur + signe + "\033[0m" + "   ");
				else
					System.out.print("   " + couleur + signe + "\033[0m" + "   ");
			}
			System.out.print(ftRond(3 + i) + "\n   ");
			for (int j = 0 ; j < 3 ; j++) {
				if (mPlateau[i][j].getPosition() == 2)
					System.out.print("█████████");
				else
					System.out.print("███   ███");
			}
			System.out.print("\n   ");
		}
		System.out.print("   " + ftRond(8) + "      " + ftRond(7) + "      " + ftRond(6) + " ");
		System.out.println("\n\n");
	}

	private String ftRond(int num) {
		if (num == mDetective[0].getPosition()
				&& num == mDetective[1].getPosition()
				&& num == mDetective[2].getPosition())
			return (ROUGE + "◙" + VERT + "◙" + JAUNE + "◙" + RESET);
		else if (num == mDetective[0].getPosition()
				&& num == mDetective[1].getPosition())
			return (ROUGE + "◙" + VERT + "◙" + RESET + "○");
		else if (num == mDetective[0].getPosition()
				&& num == mDetective[2].getPosition())
			return (ROUGE + "◙" + RESET + "○" + JAUNE + "◙" + RESET);
		else if (num == mDetective[1].getPosition()
				&& num == mDetective[2].getPosition())
			return ("○" + VERT + "◙" + JAUNE +"◙" + RESET);
		else if (num == mDetective[0].getPosition())
			return (ROUGE + "◙" + RESET  + "○○");
		else if (num == mDetective[1].getPosition())
			return ("○" + VERT  + "◙" + RESET  + "○");
		else if (num == mDetective[2].getPosition())
			return ("○○" + JAUNE  + "◙" + RESET);
		return ("○○○");
	}
}
