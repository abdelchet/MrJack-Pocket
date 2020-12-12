package mrjack;
import java.util.Random;

public class JetonAction 
{
	protected String mNom1;
	protected String mNom2;
	protected int mPileFace; // 1 = fonction1, 2 = fonction2 et -1 = Rien du tout

	public void ftHasard() {
		Random rand = new Random();
		this.mPileFace = rand.nextInt(2) + 1;
	}

	public void changePileFace() {
		this.mPileFace = (this.mPileFace == 1) ? 2 : 1;
	}

	public int getPileFace() {
		return (this.mPileFace);
	}

	public String getNom1() {
		return (this.mNom1);
	}

	public String getNom2() {
		return (this.mNom2);
	}

	public void fonction1(Plateau plateau) {
		;
	}

	public void fonction2(Plateau plateau) {
		;
	}
}
