package com.bigbeard.storemanager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.bigbeard.storemanager.dao.Batch;
import com.bigbeard.storemanager.dao.BatchRepository;
import com.bigbeard.storemanager.dao.ProductRepository;

@Configuration
@EnableJpaRepositories( basePackageClasses = {BatchRepository.class, ProductRepository.class} )
public class RepoFactory4Test
{
    @Bean
    public DataSource dataSource()
    {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType( EmbeddedDatabaseType.H2 ).build();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory()
    {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl( true );

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter( vendorAdapter );
        factory.setPackagesToScan( Batch.class.getPackage().getName() );
        factory.setDataSource( dataSource() );
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager()
    {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory( entityManagerFactory() );
        return txManager;
    }
    
}