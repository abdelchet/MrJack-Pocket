package mrjack;

public class Quartier
{
	private int mPosition; /* Position du mur ; 0 vers le nord
	1 vers l'est, 2 vers le sud et 3 vers l'ouest */
	private Personnage mPerso;
	private int mPileFace; /* 0 pour pile (etat de base)
	1 pour face et -1 pour la carte retournee sans aucun mur */

	public Quartier(int position, Personnage perso) {
		this.mPosition = position;
		this.mPerso = perso;
		this.mPileFace = 0;
	}

	public void displayQuartier() {
		System.out.println("Mur : " + this.mPosition + " ; Nom : "
		+ this.mPerso.getNom() + " ; Face : " + this.mPileFace);
	}

	public void setPosition(int position) {
		this.mPosition = position;
	}

	public void setPileFace(int pileFace) {
		this.mPileFace = pileFace;
	}

	public int getPosition() {
		return (this.mPosition);
	}

	public Personnage getPerso() {
		return (this.mPerso);
	}

	public int getPileFace() {
		return (this.mPileFace);
	}
}
