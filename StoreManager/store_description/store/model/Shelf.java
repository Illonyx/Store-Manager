package store.model;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Shelf {
	
	private String shelfName;
	private ArrayList<ProductPlace> productPlacesList;
	private Rectangle representation;
	
	public Shelf(String _shelfName,ArrayList<ProductPlace> _productPlacesList){
		this.shelfName=_shelfName;
		this.productPlacesList= _productPlacesList;
	}
	
	public Shelf(String _shelfName,Rectangle repr){
		this.representation=repr;
		this.shelfName=_shelfName;
		this.productPlacesList=new ArrayList();
	}

	public Rectangle getRepresentation() {
		return representation;
	}

	public void setRepresentation(Rectangle representation) {
		this.representation = representation;
	}

	public String getShelfName() {
		return this.shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public ArrayList<ProductPlace> getProductPlacesList() {
		return this.productPlacesList;
	}

	public void setProductPlacesList(ArrayList<ProductPlace> productPlacesList) {
		this.productPlacesList = productPlacesList;
	}
	
}
