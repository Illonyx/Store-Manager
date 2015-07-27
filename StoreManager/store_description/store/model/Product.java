package store.model;

import java.util.ArrayList;

public class Product {
	
	//private ArrayList<ProductPlace> possiblePlaces;
	private double productPrice;
	private String productName, productDLC;
	private int productId;
	
	//Produit pour tester
	public Product(String _productName){
		this.productName=_productName;
		this.productId=1;
		this.productDLC="XX/XX/XXXX";
		this.productPrice=3.42;
	}
	
	public Product(int _productId, String _productName, double _productPrice, String _productDLC){
		//this.possiblePlaces=_possiblePlaces;
		this.productId=_productId;
		this.productName=_productName;
		this.productPrice=_productPrice;
		this.productDLC=_productDLC;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
