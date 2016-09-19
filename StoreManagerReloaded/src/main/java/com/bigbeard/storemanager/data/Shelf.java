package com.bigbeard.storemanager.data;

import java.util.List;

import com.bigbeard.storemanager.representations.GeometricalItem;
import com.bigbeard.storemanager.representations.GeometricalRepresentation;

public class Shelf implements GeometricalRepresentation {
	
	private String name;
	private List<ProductPlace> productPlacesList;
	private GeometricalItem geometricalItem;
	
	public Shelf(String name, List<ProductPlace> productPlacesList, GeometricalItem item){
		this.name = name;
		this.geometricalItem = item;
		this.productPlacesList = productPlacesList;
	}
	
	//---------------------------------------------------------------------
	// Gettlers/Settlers
	//---------------------------------------------------------------------
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductPlace> getProductPlacesList() {
		return productPlacesList;
	}

	public void setProductPlacesList(List<ProductPlace> productPlacesList) {
		this.productPlacesList = productPlacesList;
	}

	public GeometricalItem getGeometricalItem() {
		return geometricalItem;
	}

	public void setGeometricalItem(GeometricalItem geometricalItem) {
		this.geometricalItem = geometricalItem;
	}

	//--------------------------------------------------------------
	// Geometrical Representation methods
	//--------------------------------------------------------------
	
	@Override
	public int getX() {
		return geometricalItem.getX();
	}

	@Override
	public int getY() {
		return geometricalItem.getY();
	}

	@Override
	public int getWidth() {
		return geometricalItem.getWidth();
	}

	@Override
	public int getHeight() {
		return geometricalItem.getHeight();
	}

}
