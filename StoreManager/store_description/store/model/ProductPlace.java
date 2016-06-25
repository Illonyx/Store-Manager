package store.model;

import java.util.ArrayList;
import java.util.Map;

import store.db.DBBatch;

/** 
 * 
 * @author Alexis
 *
 */
public class ProductPlace {
	
	private int placeCapacity;
	private Product productInPlace;
	private int productsNumber; 
	private ArrayList<Batch> batches;
	
	//private int id;
	
	public ArrayList<Batch> getBatches() {
		return batches;
	}

	public void setBatches(ArrayList<Batch> batches) {
		this.batches = batches;
	}

	public ProductPlace(){
		this.productInPlace = new Product("");
	}
	
	public ProductPlace(Product _productInPlace, int _placeCapacity){
		//this.id=id;
		this.placeCapacity=_placeCapacity;
		this.productInPlace=_productInPlace;
		this.productsNumber=0;
		this.batches= new ArrayList<Batch>();
	}

	public int getPlaceCapacity() {
		return placeCapacity;
	}

	public void setPlaceCapacity(int placeCapacity) {
		this.placeCapacity = placeCapacity;
	}

	public Product getProductInPlace() {
		return this.productInPlace;
	}

	public void setProductInPlace(Product productInPlace) {
		this.productInPlace = productInPlace;
	}

	public int getProductsNumber() {
		return productsNumber;
	}

	public void setProductsNumber(int productsNumber) {
		this.productsNumber = productsNumber;
	}
	
	public int getCurrentNumber(){
		int current = 0;
		if(batches.size() != 0){
			for(Batch b : batches)current += b.getNumber();
		}
		
		return current;
	}
	
	public Boolean isOutOfStock(){
		return (getCurrentNumber() == 0);
	}
	
	public Boolean isFull(){
		return (getCurrentNumber() == placeCapacity);
	}
	
	/*
	public void deleteAProduct(){
		
		if(productsNumber != 0){
			productsNumber--;
			DBBatch dbBatch = DBBatch.getInstance();
			ArrayList<String> availableBatchesInStock = dbBatch.findBatchesforProduct(productInPlace.getProductName());
			
			for(Batch b : batches){
				if(b.getId().equals(availableBatchesInStock.get(availableBatchesInStock.size() - 1)) && b.getNumber() != 0){
					int newNumber = b.getNumber() - 1;
					b.setNumber(newNumber);
					dbBatch.addProductToStock(b.getId());
					break;
				}
			}
				
			
		}
		
		
		
	}
	*/
}
