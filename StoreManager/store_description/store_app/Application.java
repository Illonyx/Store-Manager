package store_app;

import java.util.ArrayList;

import department_description.ProductPlace;
import department_description.Store;
import department_description.Department;
import department_description.Shelf;
import department_description.Product;
import store_interactions.StoreIOInteractions;

/* Readme: 
 * 
 * Partie I/O
 * -> Cr�er m�thode �criture pour magasin en format xml (Fait)
 * -> Cr�er m�thode lecture pour un magasin enregistr� au format xml
 * -> Rajouter m�thodes pour aider au traitement, prendre en compte les propri�t�s g�om�triques dans les  
 * diff�rentes classes (x,y,width,height)
 * -> Modifier les classes ProductPlace et Produit pour qu'elles soient traitables par l'IHM et �ventuellement la BDD 
 * Lien pour moi : http://cynober.developpez.com/tutoriel/java/xml/jdom/
 * 
 * Partie Bdd pour les produits: 
 * -> Telecharger projet JDBC, cr�er sp�cification pour les produits et tester avec bdd mySQL
 * 
 * Partie Swing
 * -> Relier listeners � code de lecture de la classe StoreIOInteractions
 * 
 * Partie Statistiques: 
 * -> Pas encore commen�able pour le moment =)
 */


public class Application 
{
	public static void main(String args[])
	{
		  try {
			  
			  	//Cr�ation d'un magasin
				Product a = new Product(1,"caf�",0.95,"14/01/2017");
				Product b = new Product(1,"biberon",2.95,"14/01/2018");
				Product c = new Product(1,"truc",0.95,"24/01/2015");
				
				ProductPlace pp0 = new ProductPlace(a,20);
				ProductPlace pp1 = new ProductPlace(b,10);
				ProductPlace pp2 = new ProductPlace(c,30);
				
				ArrayList<ProductPlace> allPlaces = new ArrayList<ProductPlace>();
				allPlaces.add(pp0);
				allPlaces.add(pp1);
				allPlaces.add(pp2);
				
				Shelf sh = new Shelf("alimentation",allPlaces);
				ArrayList<Shelf> allShelves = new ArrayList<Shelf>();
				allShelves.add(sh);
				
				Department dp = new Department("dpt", allShelves);
				ArrayList<Department> allDepartments = new ArrayList<Department>();
				allDepartments.add(dp);
				
				Store myStore= new Store("myStore", allDepartments);
				
				//Op�rations qui permettent de manipuler le contenu du magasin en XML
		        StoreIOInteractions.saveStore(myStore);
		        StoreIOInteractions.loadStore("myStore.xml");
		    } catch(Exception e) {
		        e.printStackTrace();
		    }
		
		//System.out.println("I'm working");
	}
	
}
