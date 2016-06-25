package com.bigbeard.storemanager.dao;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long>{
	public List<Product> findByCategory(String category);
	
}
