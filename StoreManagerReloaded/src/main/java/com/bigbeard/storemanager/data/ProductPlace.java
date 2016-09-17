package com.bigbeard.storemanager.data;

import java.util.ArrayList;
import java.util.List;

import com.bigbeard.storemanager.adapters.ProductPlaceAdapter;
import com.bigbeard.storemanager.dao.Batch;
import com.bigbeard.storemanager.dao.BatchRepository;
import com.bigbeard.storemanager.dao.Product;

//TODO : Prévoir un mécanisme de sérialisation à cette classe
//TODO : Voir comment faire pour enlever le BatchRepo du constructeur
public class ProductPlace {
	
	private ProductPlaceAdapter ppAdapter;
	private List<Batch> batches;
	private int maxCapacity;
	
	public ProductPlace(BatchRepository batchRepository, Product product, final int maxCapacity) {
		this.maxCapacity = maxCapacity;
		this.ppAdapter = new ProductPlaceAdapter(batchRepository,product, maxCapacity);
		this.batches = new ArrayList<Batch>();
	}
	
	public int getCurrentNumber(){
		int currentNumber = 0;
		for(Batch b : this.batches){
			currentNumber += b.getCurrentNumber();
		}
		return currentNumber;
	}
	
	public List<Batch> getBatches() {
		return batches;
	}

	public void setBatches(List<Batch> batches) {
		this.batches = batches;
	}

	public void fillWithProducts(int productsNumber){
		List<Batch> batchesToAdd = this.ppAdapter.takeProductsFromStock(this.getCurrentNumber(), productsNumber);
		if(!batchesToAdd.isEmpty()){
			for(Batch batchToAdd : batchesToAdd){
				
				//Update pp batches products number
				if(this.batches.contains(batchToAdd)){
					Batch batchToUpdate = this.batches.get(this.batches.indexOf(batchToAdd));
					int newCurrentNumber = batchToAdd.getCurrentNumber() + batchToUpdate.getCurrentNumber();
					batchToUpdate.setCurrentNumber(newCurrentNumber);
				} else {
					this.batches.add(batchToAdd);
				}
				
			}
		}
	}
	
	public void fillAll(){
		fillWithProducts(this.maxCapacity);
	}
	
	public void empty(int productsNumber){
		
	}
	
	
	public void emptyAll(){
		
	}
	
	
	
}
