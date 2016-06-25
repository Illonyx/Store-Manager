package store.db;

import java.util.ArrayList;

public class DBBatch extends DBBase{
	
	private DBBatch() {}

	private static class DBBatchHolder {
		/** Instance unique non prÃ©initialisÃ©e */
		private final static DBBatch instance = new DBBatch();
	}

	/** Point d'accÃ¨s pour l'instance unique du singleton */
	public static DBBatch getInstance() {
		return DBBatchHolder.instance;
	}
	
	// -------------------------------------------------------
	// Basic Stock Interactions - Add/Delete Product
	// -------------------------------------------------------
	
	//Retourne et trie du plus ancien au plus récent les lots
	public ArrayList<String> findBatchesforProduct(String productId){
		ArrayList<String> batches = new ArrayList<String>();
		try {
			String query = "select batchCode from batch INNER JOIN productAndBatch ON productAndBatch.batchId=batch.id where productId='"+ productId + "' ORDER BY comingDate ASC";
			connection = em.getStoreDB();
			stmt = connection.prepareStatement(query);
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				batches.add(rs.getString("batchCode"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			LOG.error(e.getMessage());
		} finally {
			closeDb();
		}
		return batches;
	}
	
	//Incrémenter stock - remettre produits dans stock
	public void addProductToStock(String batchId){
		try {
			String query = "update batch set number=number+1 where batchCode=?";
			connection = em.getStoreDB();
			ps = connection.prepareStatement(query);
			ps.setString(1, batchId);
			ps.execute(query);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		} finally {
			closeDb();
		}
	}
	
	//Incrémenter stock - remettre produits dans stock
		public void takeProductToStock(String batchId){
			try {
				String query = "update batch set number=number-1 where batchCode='" + batchId + "'";
				connection = em.getStoreDB();
				stmt = connection.prepareStatement(query);
				
				stmt.execute(query);
			} catch (Exception e) {
				System.out.println("Fail");
				LOG.error(e.getMessage());
			} finally {
				closeDb();
			}
		}
	
	//Checker le stock
	public Boolean isOutOfStock(String batchId){
		Boolean isOutOfStock = false;
		String anId = null;
		try{
			String query = "select id from batch where batchCode='" + batchId +  "' and number=0";
			connection = em.getStoreDB();
			stmt = connection.prepareStatement(query);
			rs = ps.executeQuery(query);
			if(rs.next()){
				anId = rs.getString("id");
				if (!rs.wasNull()) {
					isOutOfStock = true;
			    }
			}
			
		} catch (Exception e) {
			System.out.println("error ots" + e.getMessage());
			LOG.error(e.getMessage());
		} finally {
			closeDb();
		}
		
		return isOutOfStock;
	}
	
}
