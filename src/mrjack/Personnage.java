package mrjack;

public class Personnage
{
	private String mNom;
	private int mSablier;
	
	public Personnage(String nom, int sablier) {
		this.mNom = nom;
		this.mSablier = sablier;
	}

	public String getNom() {
		return (this.mNom);
	}

	public int getSablier() {
		return (this.mSablier);
	}

	public void displayPersonnage() {
		System.out.println(this.mNom + " et " + this.mSablier);
	}
}
