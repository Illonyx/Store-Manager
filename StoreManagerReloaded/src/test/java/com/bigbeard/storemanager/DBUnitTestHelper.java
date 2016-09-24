package com.bigbeard.storemanager;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.bigbeard.storemanager.dao.BatchRepository;
import com.bigbeard.storemanager.dao.ProductRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={RepoFactory4Test.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
@DatabaseSetup("/datasets/product-entries.xml")
@DatabaseSetup("/datasets/batch-entries.xml")
@DirtiesContext(hierarchyMode = DirtiesContext.HierarchyMode.EXHAUSTIVE)
public abstract class DBUnitTestHelper {

	@Autowired
	protected ProductRepository products;
	
	@Autowired
	protected BatchRepository stock;
	
}
