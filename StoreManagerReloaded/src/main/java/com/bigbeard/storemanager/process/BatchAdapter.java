package com.bigbeard.storemanager.process;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bigbeard.storemanager.dao.Batch;
import com.bigbeard.storemanager.dao.BatchRepository;
import com.bigbeard.storemanager.dao.Product;

/*

	For a given product, simplifies the interaction with Batches

*/

//Date de filtrage avec date
public class BatchAdapter {
	
	private BatchRepository batchRepository;
	private Product product;
	
	public BatchAdapter(BatchRepository batchRepository, Product product) {
		this.batchRepository = batchRepository;
		this.product = product;
	}
	
	public int getAvailableNumber() {
		
		int availableNumber = 0;
		Collection<Batch> matchingBatches = batchRepository.findByRefProductOrderByComingDateAsc(this.product);
		for (Batch batch : matchingBatches) {
			availableNumber += batch.getCurrentNumber();
		}
		
		return availableNumber;
	}
	
	public List<Batch> takeProductsFromStock (int nbProducts) {
		List<Batch> batchesToSend = new ArrayList<Batch>();
		Collection<Batch> matchingBatches = batchRepository.findByRefProductOrderByComingDateAsc(product);
		
		for (Batch batch : matchingBatches) {
			if (nbProducts - batch.getCurrentNumber() > 0) {
				batchesToSend.add(batch);
				nbProducts -= batch.getCurrentNumber();
				batch.setCurrentNumber(0);
			} else {
				int productsRemaining = batch.getCurrentNumber() - nbProducts;
				//batchesToSend.add()
				batch.setCurrentNumber(productsRemaining);
				break;
			}
		}
		
		return batchesToSend;
	}
	
	
}
