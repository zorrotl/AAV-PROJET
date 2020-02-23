/*
* @Author : Tran Leo - Ibouda El Mahdi
*/
package Tri1;

import java.util.ArrayList;

public class BinaryTree {
	private ArrayList<Obj> objets; //liste d'objets à potentielement mettre dans le sac
	private static ArrayList<Obj> objetsnul; //liste des objets
	private static float poidsMax; //Poids max du sac
	@SuppressWarnings("unused")
	private BinaryTree right; //branche droite
	@SuppressWarnings("unused") 
	private BinaryTree left; //branche gauche
	private int height; // profondeur
	private static int maxHeight; //profondeur max
	private static int nbnoeud = 0; // nombre de noeuds
	private static BinaryTree bestObjets; // branche de la meilleur liste d'objet à mettre dans le sac
	
	public int getNbnoeud() {
		return nbnoeud;
	}
	/*
	 *	Création de la 1ère branche à partir d'un sac à dos
	 */
	@SuppressWarnings("unchecked")
	public BinaryTree(SacADos sac) {
		BinaryTree.objetsnul = (ArrayList<Obj>) sac.getListObjetsNul().clone();
		this.objets = new ArrayList<Obj>();
		this.height = 0;
		BinaryTree.poidsMax = sac.getPoidsMax();
		BinaryTree.maxHeight = BinaryTree.objetsnul.size();
		BinaryTree.bestObjets = this;
		this.createTree();
	}
	/*
	 *	Création d'une autre branche avec comme paramètre la liste d'objet du père, la hauteur du père
	 *	et s'il s'agit de la branche droite ou gauche
	 */
	@SuppressWarnings("unchecked")
	public BinaryTree(ArrayList<Obj> objets,int height, boolean sens) {
		this.objets = (ArrayList<Obj>) objets.clone();
		if (sens) {
			this.objets.add(objetsnul.get(height));
		}
		this.height = height + 1;
	
		if (this.getValeurSac()>BinaryTree.bestObjets.getValeurSac()) {
			BinaryTree.bestObjets.objets=new ArrayList<Obj>(this.objets);
		}
		this.createTree();
	}
	/*
	 *	Création des autres branche avec les conditions optimales
	 */
	public void createTree() {
		nbnoeud++;
		if (this.height == 0) {	
			if(this.getPoidsActu()+BinaryTree.objetsnul.get(height).getPoids()<=poidsMax) {
				this.right = new BinaryTree(this.objets,0,true);			
			}
			this.left = new BinaryTree(this.objets,0,false);
		}
		else {
			if (this.height>=maxHeight) {
				this.right = null;			
				this.left = null;
			}
			else {
				if (this.getPoidsActu()+BinaryTree.objetsnul.get(height).getPoids()<=poidsMax) {

					this.right = new BinaryTree(this.objets,this.height,true);
					if(this.aUnPotentiel() && this.height + 1 < maxHeight) {
						this.left = new BinaryTree(this.objets,this.height,false);
					}
				}
				else if(this.aUnPotentiel() && this.height + 1 < maxHeight) {
					this.left = new BinaryTree(this.objets,this.height,false);
				}
			}
		}
	}
	
	/*
	 * méthode permettant de connaître le potentiel d'une branche gauche
	 */
	public boolean aUnPotentiel() {
		float valPotentielL=this.getValeurSac();
		for (int i = this.height + 1;i<maxHeight;i++) {			
				valPotentielL += objetsnul.get(i).getValeur();
			}
		if (BinaryTree.bestObjets.getValeurSac()<=valPotentielL) {
			return true;
		}
		else {
			return false;
		}

	}

	/*
	 * retourne le poids des objets de la branche
	 */
	public float getPoidsActu() {
		float poids = 0;
		for (Obj o : objets) {
			poids += o.getPoids();
		}
		return poids;
	}
	/*
	 * retourne la valeur des objets de la branche
	 */
	public Float getValeurSac() {
		float val = 0;
		for (Obj o : objets) {
			val+=o.getValeur();
		}
		return val;
	}
	/*
	 * retourne la meilleur liste d'objets
	 */
	public ArrayList<Obj> getBestListObjets() {
		return BinaryTree.bestObjets.objets;
	}

}


