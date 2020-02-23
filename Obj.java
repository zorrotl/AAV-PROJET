/*
* @Author : Tran Leo - Ibouda El Mahdi
*/
package Tri1;

public class Obj implements Comparable<Obj> {
	private String nom; 
	private float poids;
	private float valeur;
	
	
	public Obj(float p,float v, String nom) {
		this.setNom(nom);
		this.poids = p;
		this.valeur = v;
		
	}
	/*
	 * getter du Rapport
	 */
	public Float getRapport () {
		return this.valeur/this.poids;		
	}
	/*
	 * getter de poids
	 */
	public float getPoids() {
		return poids;
	}

	/*
	 * setter de Poids
	 */
	public void setPoids(float poids) {
		this.poids = poids;
	}
	/*
	 * getter de Valeur
	 */
	public float getValeur() {
		return valeur;
	}
	/*
	 * setter de Valeur
	 */
	public void setValeur(float valeur) {
		this.valeur = valeur;
	}
	/*
	 * getter de Nom
	 */
	public String getNom() {
		return nom;
	}
	/*
	 * setter de Nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/*
	 * compare deux objet en fonction de leur rapport valeur/poids
	 */
	@Override
	public int compareTo(Obj o) {
		if (this == null || o == null)
			return 0;
		else
			return this.getRapport().compareTo(o.getRapport());
	}
	/*
	 * affichage de l'objet
	 */
	@Override
	public String toString() {
		return "[" + nom + ", poids=" + poids + ", valeur=" + valeur + "]";
	}



}
