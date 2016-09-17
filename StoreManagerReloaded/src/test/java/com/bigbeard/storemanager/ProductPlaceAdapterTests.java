package com.bigbeard.storemanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.engine.spi.PersistenceContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.bigbeard.storemanager.adapters.BatchAdapter;
import com.bigbeard.storemanager.adapters.ProductPlaceAdapter;
import com.bigbeard.storemanager.dao.Batch;
import com.bigbeard.storemanager.dao.BatchRepository;
import com.bigbeard.storemanager.dao.Category;
import com.bigbeard.storemanager.dao.Product;
import com.bigbeard.storemanager.dao.ProductRepository;
import com.bigbeard.storemanager.data.ProductPlace;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import junit.framework.Assert;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={RepoFactory4Test.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
@DatabaseSetup("product-entries.xml")
@DatabaseSetup("batch-entries.xml")
public class ProductPlaceAdapterTests {

	@Autowired
	private ProductRepository products;
	
	@Autowired
	private BatchRepository stock;
	
	 	@Test
	    public void fillSimpleExample() {
	 		
	 		//TODO : Ce code est à dégager
	 		Product product = new Product("nesquik", Category.FOOD.toString(), 12.5, "FR", 12.57);
	 		products.save(product);
	 		Batch batch = new Batch("aaaa-2016", "2016-08-18", "2016-08-28", 10, 10, product);
	 		stock.save(batch);
	 		
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
