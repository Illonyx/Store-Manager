package store.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class Java2MySql 
{
	public static void main(String[] args) {
		DBProducts dbProducts = DBProducts.getInstance();
		ArrayList<String> arr = dbProducts.filterByCategoryAndSubcategory("Food", "Ambient");
		for(String s : arr)
			System.out.println(s);
	}
	
	public void saveAllProducts(){
		
		
		
	}
	
	
	
}