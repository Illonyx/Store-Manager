package com.bigbeard.storemanager.io;

import org.junit.Test;

import com.bigbeard.storemanager.DBUnitTestHelper;
import com.bigbeard.storemanager.dao.Batch;
import com.bigbeard.storemanager.data.ProductPlace;

import junit.framework.Assert;

public class StoreSerializationTest extends DBUnitTestHelper {	

	private ProductPlace nesquikProductPlace;
	

	@Test
	public void writeProductPlaceJson(){
		Batch batch = this.stock.findOne(1L);
		Assert.assertEquals(1, 1);
	}
	
	
	
}
