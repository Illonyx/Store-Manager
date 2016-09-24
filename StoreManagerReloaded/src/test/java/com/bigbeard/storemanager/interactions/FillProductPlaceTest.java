package com.bigbeard.storemanager.interactions;

import java.util.Collection;

import org.junit.Test;

import com.bigbeard.storemanager.DBUnitTestHelper;
import com.bigbeard.storemanager.adapters.BatchAdapter;
import com.bigbeard.storemanager.dao.Batch;
import com.bigbeard.storemanager.dao.Category;
import com.bigbeard.storemanager.dao.Product;
import com.bigbeard.storemanager.data.ProductPlace;

import junit.framework.Assert;

public class FillProductPlaceTest extends DBUnitTestHelper{
	
	@Test
    public void fillSimpleExample() {
 		
 		//TODO : Ce code est à dégager
 		Collection<Product> allProducts = products.findByCategory(Category.FOOD.toString());
 		Assert.assertEquals(allProducts.size(), 1);
 		
 		Product product = products.findOne(1L);
 		Batch batch = stock.findOne(1L);
 		//Batch batch = new Batch("aaaa-2016", "2016-08-18", "2016-08-28", 10, 10, product);
 		//stock.save(batch);
 		
 		//Create product containers (product place/Stock)
 		ProductPlace nesquikProductPlace = new ProductPlace(stock, product, 30);
 		BatchAdapter stockAdapter = new BatchAdapter(stock, product);
 		
 		//Fill product place with seven products - PP:7/30
 		//Should stay only 3 products in stock out of 10 - ST:3/10
        nesquikProductPlace.fillWithProducts(7);
        Assert.assertEquals(7, nesquikProductPlace.getCurrentNumber());
        Assert.assertEquals(3, stockAdapter.getAvailableNumber());
        
    }
	
}
