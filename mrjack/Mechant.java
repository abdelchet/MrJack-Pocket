package mrjack;
import java.util.ArrayList;

public class Mechant
{
	private int mSablier;
	private int mSablierReel;
	private Personnage mCarteMechant;
	public ArrayList<Personnage> mTasCarteMechant;

	public Mechant(Personnage carteMechant) {
		this.mSablier = 0;
		this.mTasCarteMechant = new ArrayList<Personnage>();
		this.mSablierReel = 0;
		this.mCarteMechant = carteMechant;
	}

	public void setSablier(int sablier) {
		this.mSablier = sablier;
	}

	public void setSablierReel(int sablierReel) {
		this.mSablierReel = sablierReel;
	}

	public int getSablier() {
		return (this.mSablier);
	}

	public int getSablierReel() {
		return (this.mSablierReel);
	}

	public Personnage getCarteMechant() {
		return (this.mCarteMechant);
	}
}
