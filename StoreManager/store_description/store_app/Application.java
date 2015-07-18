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
 * -> Créer méthode écriture pour magasin en format xml (Fait)
 * -> Créer méthode lecture pour un magasin enregistré au format xml
 * -> Rajouter méthodes pour aider au traitement, prendre en compte les propriétés géométriques dans les  
 * différentes classes (x,y,width,height)
 * -> Modifier les classes ProductPlace et Produit pour qu'elles soient traitables par l'IHM et éventuellement la BDD 
 * Lien pour moi : http://cynober.developpez.com/tutoriel/java/xml/jdom/
 * 
 * Partie Bdd pour les produits: 
 * -> Telecharger projet JDBC, créer spécification pour les produits et tester avec bdd mySQL
 * 
 * Partie Swing
 * -> Relier listeners à code de lecture de la classe StoreIOInteractions
 * 
 * Partie Statistiques: 
 * -> Pas encore commençable pour le moment =)
 */


public class Application 
{
	public static void main(String args[])
	{
		  try {
			  
			  	//Création d'un magasin
				Product a = new Product(1,"café",0.95,"14/01/2017");
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
				
				//Opérations qui permettent de manipuler le contenu du magasin en XML
		        StoreIOInteractions.saveStore(myStore);
		        StoreIOInteractions.loadStore("myStore.xml");
		    } catch(Exception e) {
		        e.printStackTrace();
		    }
		
		//System.out.println("I'm working");
	}
	
}
