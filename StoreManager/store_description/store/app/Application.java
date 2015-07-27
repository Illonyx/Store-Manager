package store.app;

import java.awt.Rectangle;
import java.util.ArrayList;

import store.model.Department;
import store.model.Product;
import store.model.ProductPlace;
import store.model.Shelf;
import store.model.Store;
import store.utils.StoreUtils;

/* Readme: 
 * 
 * 
 * -> Modifier les classes ProductPlace et Produit pour qu'elles soient traitables par l'IHM et 
 * éventuellement la BDD 
 * Lien pour moi : http://cynober.developpez.com/tutoriel/java/xml/jdom/
 * 
 * Partie Bdd pour les produits: 
 * -> Telecharger projet JDBC, créer spécification pour les produits et tester avec bdd mySQL
 * 
 * Partie Statistiques: 
 * -> Pas encore commençable pour le moment =)
 */


public class Application 
{
	public static Store generateStoreExample()
	{
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
		sh.setRepresentation(new Rectangle(40,60,75,120));
		ArrayList<Shelf> allShelves = new ArrayList<Shelf>();
		allShelves.add(sh);
		
		Department dp = new Department("dpt", allShelves);
		ArrayList<Department> allDepartments = new ArrayList<Department>();
		allDepartments.add(dp);
		
		Store myStore= new Store("myStore", allDepartments);
		return myStore;
	}
	
	
	public static void main(String args[])
	{
		  try {
		        StoreUtils.saveStore(generateStoreExample());
		        //StoreUtils.loadStore("myStore.xml");
		    } catch(Exception e) {
		        e.printStackTrace();
		    }
		
		//System.out.println("I'm working");
	}
	
}
