package com.bigbeard.storemanager.process;

import java.util.ArrayList;
import java.util.List;

import com.bigbeard.storemanager.dao.Batch;
import com.bigbeard.storemanager.dao.BatchRepository;
import com.bigbeard.storemanager.dao.Product;

public class ProductPlaceAdapter extends BatchAdapter {
	
	private final int maxCapacity;
	
	public ProductPlaceAdapter(BatchRepository repository, Product product, int maxCapacity){
		super(repository, product);
		this.maxCapacity = maxCapacity;
	}
	
	//Exception?
	public List<Batch> takeProductsFromStock(int currentCapacity, int nbProducts){
		List<Batch> batchesToSend = new ArrayList<Batch>();
		int remainingProductCapacity = this.maxCapacity - currentCapacity;
		
		//We cannot fill productPlace with asked products number
		//TODO: Decide what to do here (try to fill the best way seems to be the best solution)
		if (remainingProductCapacity < nbProducts){
			batchesToSend = this.takeProducts(remainingProductCapacity);
		} else {
			batchesToSend = this.takeProducts(nbProducts);
		}
		
		return batchesToSend;
	}
	
	
}
