package com.bigbeard.storemanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bigbeard.storemanager.dao.Batch;
import com.bigbeard.storemanager.dao.BatchRepository;
import com.bigbeard.storemanager.dao.Product;
import com.bigbeard.storemanager.dao.ProductRepository;

@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.bigbeard.storemanager.dao")
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
	
	@Bean
	public CommandLineRunner demo(ProductRepository productRepository, BatchRepository batchRepository) {
		return (args) -> {
			
			//save a product
			Product atry = new Product("Poule", "Food", 10.45, "FR", 0.58);
			productRepository.save(atry);
			
			
			//save a batch
			//String batchCode, String comingDate, String dlc, int number, String productId
			//Batch aBatch = new Batch("aaaa-2529", "125", "48", 21, productRepository.findOne(1L));
			//batchRepository.save(aBatch);

			// find all existing products
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Product customer : productRepository.findAll()) {
				log.info(customer.toString());
			}
            log.info("");

			// fetch customers by last name
			log.info("Screen batches with product with id=1:");
			log.info("--------------------------------------------");
			//List<Batch> batches = 
			//Batch batch1 = batchRepository.findOne(1L);
            //log.info("A batch example :" + batch1);
		};
	}
	
	
}
