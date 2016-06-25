package com.bigbeard.storemanager.dao;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface BatchRepository extends CrudRepository<Batch, Long>{
	public Collection<Batch> findByRefProductOrderByComingDateAsc(Product product);
}
