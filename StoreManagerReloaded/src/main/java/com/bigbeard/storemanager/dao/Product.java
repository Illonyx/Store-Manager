package com.bigbeard.storemanager.dao;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "products")
@Entity
public class Product {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

	private String nom;
    private String provenance;
    
    private String category;
    private String subcategory;
    
    private double price;//TODO : Prendre une décision pour le prix. Dans le batch ou dans le produit même
    //TODO: On pourra probablement appliquer une promotion sur un batch...
    private double weight;
    
    @OneToMany(mappedBy="refProduct")
    Collection<Batch> batches;
    
    protected Product() {}

    public Product(String nom, String category, double price,String provenance, double weight) {
        this.nom = nom;
        this.category=category;
        this.price=price;
        this.provenance=provenance;
        this.weight=weight;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%d, firstName='%s', lastName='%s']",
                id, nom, provenance);
    }
	
}
