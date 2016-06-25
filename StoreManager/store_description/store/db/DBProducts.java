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
	
	//--------------------------------------------------------
	// Requ�tes de base produit
	//--------------------------------------------------------
	
	public String getAProductName(String id)
	{
		String productName = "";
		try {
			String query = "select nom from products where id=?";
			connection = em.getStoreDB();
			ps = connection.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery(query);
			while (rs.next()) {
				productName = rs.getString("nom");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		} finally {
			closeDb();
		}
		return productName;
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
	
	
	
	
	
	
	
	
	
	
	//-----------------------------------------------------------------
	// Category and Subcategory
	//-----------------------------------------------------------------
	
	
	
	public ArrayList<String> filterByCategoryAndSubcategory(String category, String subcategory){
		ArrayList<String> idsConcerned = new ArrayList<String>();
		String filterQuery = "select id from products where category=? and subcategory=?";
		
		try {
			connection = em.getStoreDB();
			
			ps = connection.prepareStatement(filterQuery);
			ps.setString(1, category);
			ps.setString(2, subcategory);
			rs = ps.executeQuery();
			while (rs.next()) {
				idsConcerned.add(rs.getString("id"));
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		} finally {
			closeDb();
		}
		
		return idsConcerned;
	}

	
	
	
}
