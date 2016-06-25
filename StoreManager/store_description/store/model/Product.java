package store.model;

import java.util.ArrayList;

public class Product {
	
	//private ArrayList<ProductPlace> possiblePlaces;
	private double productPrice;
	private String productName;
	private String productId;
	
	//Produit pour tester
	public Product(){
		this.productName="";
	}
	
	public Product(String _productName){
		this.productName=_productName;
		this.productId="1";
		this.productPrice=3.42;
	}
	
	public Product(String _productId, String _productName, double _productPrice){
		//this.possiblePlaces=_possiblePlaces;
		this.productId=_productId;
		this.productName=_productName;
		this.productPrice=_productPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
