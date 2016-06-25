package store.utils;

import java.util.ArrayList;

import store.db.DBBatch;
import store.model.Batch;
import store.model.ProductPlace;

public class ShelfManager {
	
	public static ProductPlace decrementProductPlaceProducts(ProductPlace pp){
		
		ArrayList<Batch> batchesToEmpty = pp.getBatches();
		DBBatch dbBatch = DBBatch.getInstance();
		String batchToUpdateId = "";
		
		//Trouver les lots à vider en priorité : 
		ArrayList<String> batchesToFill = dbBatch.findBatchesforProduct(pp.getProductInPlace().getProductId());
		
		//Vérification sur nullité
		for(int i=0;i<batchesToFill.size();i++){
			String priotaryBatchId = batchesToFill.get(i);
			Batch batchToFind = findRightBatch(priotaryBatchId,batchesToEmpty);
			if(batchToFind != null){
				if(!batchToFind.isOutOfStock()){
					batchToUpdateId = batchToFind.getId();
					dbBatch.addProductToStock(priotaryBatchId);
					break;
				}	
			}
		}
		return updateProductPlace(pp,batchToUpdateId,false);
		
	}
	
	public static ProductPlace incrementProductPlaceProducts(ProductPlace pp){
		
		ArrayList<Batch> batchesToFill = pp.getBatches();
		DBBatch dbBatch = DBBatch.getInstance();
		String batchToUpdateId="";
		
		//Trouver les lots à prendre en priorité
		ArrayList<String> batchesToEmpty = dbBatch.findBatchesforProduct(pp.getProductInPlace().getProductId());
		for(int i=0;i<batchesToEmpty.size();i++){
			if(!dbBatch.isOutOfStock(batchesToEmpty.get(i)) ){
				dbBatch.takeProductToStock(batchesToEmpty.get(i));
				batchToUpdateId = batchesToEmpty.get(i);
				break;
			}
		}
		
		return updateProductPlace(pp,batchToUpdateId,true);
	}
	
	//----------------------------------------------------------
	// Elementary batch functions
	//----------------------------------------------------------
	
	private static Batch findRightBatch(String idToFind, ArrayList<Batch> batches){
		Batch batchToReturn = null;
		for(Batch b : batches){
			if(b.getId().equals(idToFind)) {
				batchToReturn = b;
				break;
			}
		}
		return batchToReturn;
	}
	
	private static ProductPlace updateProductPlace(ProductPlace pp, String batchId, Boolean isAdd){
		ArrayList<Batch> updated = new ArrayList<Batch>();
		ArrayList<Batch> batches = pp.getBatches();
		Boolean found = false;
		
		
		for(int i=0;i<batches.size();i++){
			Batch batch = batches.get(i);
			if(batch.getId().equals(batchId)){
				found = true;
				int number = batch.getNumber();
				batch.setNumber((isAdd==true)? ++number : --number);
			}
			updated.add(batch);
		}
		
		if(found==false){
			updated.add(new Batch(batchId,1));
		}
		
		pp.setBatches(updated);
		return pp;
	}
	
	
}
