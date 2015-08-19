package store.db;

import java.util.ArrayList;

import store.model.Product;

public class DBProducts extends DBBase {
	
	private DBProducts() {}

	private static class DBProductsHolder {
		/** Instance unique non préinitialisée */
		private final static DBProducts instance = new DBProducts();
	}

	/** Point d'accès pour l'instance unique du singleton */
	public static DBProducts getInstance() {
		return DBProductsHolder.instance;
	}
	
	
	
	public ArrayList<String> getAllProductNames(){
		ArrayList<String> allProductNames = new ArrayList<String>();
		try {
			String query = "select nom from products";
			connection = em.getStoreDB();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(query);
			while (rs.next()) {
				allProductNames.add(rs.getString("nom"));
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		} finally {
			closeDb();
		}

		return allProductNames;
	}
	
	
	
}
