package store.utils;


import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.*;

import store.model.*;

public class StoreUtils {
	
	public static Element createSubNode(String type,String nom, Element pere){
		Element subnode = new Element(type);
		subnode.setAttribute("nom",nom);
		pere.addContent(subnode);
		return subnode;
	}
	
	public Boolean existsInWorkSpace(){
		return true;
	}
	
	
	public static void saveStore(Store s)
	{
		//Création de la racine store et de son nom
		Element racine = new Element("store");
		String fileName = s.getStoreName() + ".xml";
		String pathToWrite = (s.path.equals("")) ? fileName : s.path;
		racine.setAttribute("nom",s.getStoreName());
		
		//Création de tous les sous-éléments
		
		for(int i=0;i<s.getDepartmentsList().size();i++)
		{
			
			Department d = s.getDepartmentsList().get(i);
			Element departmentElement = createSubNode("department",d.getDepartmentName(),racine);
			for(int j=0;j<d.getShelvesList().size();j++)
			{
				Shelf sh = d.getShelvesList().get(j);
				Element shelfElement = createSubNode("shelf",sh.getShelfName(),departmentElement);
				shelfElement.setAttribute("x",String.valueOf(sh.getRepresentation().x));
				shelfElement.setAttribute("y",String.valueOf(sh.getRepresentation().y));
				shelfElement.setAttribute("width",String.valueOf(sh.getRepresentation().width));
				shelfElement.setAttribute("height",String.valueOf(sh.getRepresentation().height));
				for(int h=0;h<sh.getProductPlacesList().size();h++)
				{
					ProductPlace pp = sh.getProductPlacesList().get(h);
					Element productPlaceElement = createSubNode("productPlace",pp.getProductInPlace().getProductName(),shelfElement);
				}
				
			}
			
		}
		Document document = new Document(racine);
		
		//Ecriture dans le fichier
		 try
	      {
	         XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	         sortie.output(document, new FileOutputStream(pathToWrite));
	      }
	      catch (java.io.IOException e){}	
	}
	
	
	public static Store loadStore(String pathname, String storeName) throws JDOMException, IOException
	{
		
		ArrayList<Department> allDepartments = new ArrayList<Department>();
		//Préparation de l'importation de données
		File f = new File(pathname);
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(f);
		Element racine = document.getRootElement();
		List listeDepartments = racine.getChildren();
		Iterator it_departments = listeDepartments.iterator();
		
		while(it_departments.hasNext())
		{
			Element currentDepartment = (Element)it_departments.next();
			
			//Définition des attributs du département
			ArrayList<Shelf> allShelves = new ArrayList<Shelf>();
			String departmentName = currentDepartment.getAttributeValue("nom");
			List listShelves = currentDepartment.getChildren();
			Iterator it_shelves = listShelves.iterator();
			
			while(it_shelves.hasNext())
			{
				//Récupération des données d'une shelf particulière et initialisation
				Element currentShelf= (Element)it_shelves.next();
				int x=Integer.valueOf(currentShelf.getAttributeValue("x"));
				int y=Integer.valueOf(currentShelf.getAttributeValue("y"));
				int width=Integer.valueOf(currentShelf.getAttributeValue("width"));
				int height=Integer.valueOf(currentShelf.getAttributeValue("height"));
				String nom=currentShelf.getAttributeValue("nom");
				allShelves.add(new Shelf(nom,new Rectangle(x,y,width,height)));
				
				//Rajouter suite du code ici pour les ProductPlace.. 
			}
			allDepartments.add(new Department(departmentName,allShelves));
		}
	return new Store(storeName,allDepartments);
	}

}
	

/* Code pour ajouter les productPlace (inutiles pour le moment)
 * 
 * List listProductPlaces = current2.getChildren();
 * 
 * 
			Iterator it_pp = listProductPlaces.iterator();
			while(it_pp.hasNext())
			{
				Element current3 = (Element) it_pp.next();
				System.out.println(current3.getAttribute("nom"));
			}
*/