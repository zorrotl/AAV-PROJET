/*
* @Author : Tran Leo - Ibouda El Mahdi
*/
package Tri1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class SacADos {
	private ArrayList<Obj> objets;
	private ArrayList<Obj> objetsnul;
	private String chemin;
	private float poidsMax;

	

	public SacADos(String chemin, float pMax) {
		this.objets = new ArrayList<Obj>();		
		this.objetsnul = new ArrayList<Obj>();

		try{				
				InputStream flux = new FileInputStream(chemin); 
				InputStreamReader lecture = new InputStreamReader(flux);			
				BufferedReader buff = new BufferedReader(lecture);
				String ligne;
				while ((ligne = buff.readLine())!= null){
					String[] sousChaines = ligne.split(";");
					
					String nom = sousChaines[0];
					float poids = Float.parseFloat(sousChaines[1]);
					float valeur = Float.parseFloat(sousChaines[2]);

					Obj obj = new Obj(poids, valeur, nom);
					
					objetsnul.add(obj);
			}
			buff.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		this.setPoidsMax(pMax);
	}

	/*
	 * ajoute un objet dans le sac à dos
	 */
	public void ajouter(Obj o) {
		this.objets.add(o);
	}
	
	/*
	 * retourne le poids Max
	 */
	public float getPoidsMax() {
		return poidsMax;
	}
	
	/*
	 * fixe le poids Max
	 */
	public void setPoidsMax(float pMax) {
		this.poidsMax = pMax;
	}
	
	/*
	 * retourne le chemin du fichier à exploiter
	 */
	public String getChemin() {
		return chemin;
	}
	/*
	 * fixe le chemin du fichier à exploiter
	 */
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public float getPoidsActu() {
		float poids = 0;
		for (Obj o : objets) {
			poids += o.getPoids();
		}
		return poids;
	}
	/*
	 * trie la liste d'objet "nul" de manière décroissante en fonction du rapport valeur/poids
	 */
	public void sortedItemReverse() {
		Collections.sort(objetsnul, Collections.reverseOrder());
	}
	/*
	 * méthode de résolution avec affichage
	 */
	public void resolve(String s) {
		switch(s) {
		case "glouton":
			this.glouton();
			System.out.print(this.toString());
			break;
		case "PSE" : 
			this.PSE();
			System.out.print(this.toString());
			break;
		case "methode_dynamique" : 
			this.methode_Dynamique();
			System.out.print(this.toString());
			break;
		}		
	}
	/*
	 * retourne la valeur des objets mis dans le sac
	 */
	public Float getValeurSac() {
		float val = 0;
		for (Obj o : objets) {
			val+=o.getValeur();
		}
		return val;
	}


	/* Méthode Approchée Gloutonne
	 * return la somme de la valeur des objets contenu dans le sac
	 * */
	public Float glouton() {
		this.sortedItemReverse();
		for (Obj o : objetsnul) {
			if (o.getPoids()+this.getPoidsActu()<=this.getPoidsMax()) {
				this.ajouter(o);
			}	
		}
		return getValeurSac();
	}
	
	/* Méthode Exacte Dynamique
	 * return la somme de la valeur des objets contenu dans le sac
	 * */
	public Float methode_Dynamique() {
		int FtoInt = 100;
		float[][] matrice = new float[objetsnul.size()][(int) (poidsMax * FtoInt) + 1];
		int j;
		
		poidsMax = (poidsMax * FtoInt) + 1;
		
		
		//Remplissage de la 1er ligne de la matrice
		for(j = 0; j < poidsMax; j++) {
			if(objetsnul.get(0).getPoids() * FtoInt > j) {
				matrice[0][j] = 0;
			}
			else {
				matrice[0][j] = objetsnul.get(0).getValeur();
			}
		}
		
		
		//Remplissage du reste des lignes de la matrice
		int i;
		for(i = 1; i < objetsnul.size(); i++) {
			for(j = 0; j < poidsMax; j++) {
				if(objetsnul.get(i).getPoids() * FtoInt > j) {
					matrice[i][j] = matrice[i - 1][j];
				}
				else {
					matrice[i][j] = Math.max(matrice[i- 1][j], (matrice[i - 1][(int) (j - objetsnul.get(i).getPoids() * FtoInt)] + objetsnul.get(i).getValeur()));
				}
			}
		}

		
		//Lecture du tableau pour recuperer les différents Objets à stocker dans le sac
		j = (int) poidsMax - 1;
		i = objetsnul.size() - 1;
		
		while(matrice[i][j] == matrice[i][j - 1] ) {
			j--;
		}
		
		while(j > 0) {
			while(i > 0 && matrice[i][j] == matrice[i - 1][j]) {
				i--;
			}
			j -= (objetsnul.get(i).getPoids() * FtoInt);	
			if(j >= 0) {
					objets.add(objetsnul.get(i));
			}
			i--;
		}
		return this.getValeurSac();
	}
	
	/* Méthode Exacte PSE
	 * return la somme de la valeur des objets contenu dans le sac
	 * */
	public Float PSE() {
		BinaryTree Tree = new BinaryTree(this);
		this.objets = Tree.getBestListObjets();
		return this.getValeurSac();
	}
	/*
	 * tri des objetsnul de manière décroissante en fonction de leur rapport valeur/poids
	 */
	public ArrayList<Obj> getListObjetsNul() {
		this.sortedItemReverse();
		return this.objetsnul;
	}
	/*
	 * affichage des objets dans le sac
	 */
	@Override
	public String toString() {
		return "Valeur du sac : " + this.getValeurSac() + "\n" + "Contenu du sac : " + objets +"\n" + "poids : "+ this.getPoidsActu() + "\n";
	}

}
