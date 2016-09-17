package com.bigbeard.storemanager.dao;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "batch")
@Entity
public class Batch {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	
	private String batchCode;
	private String comingDate;
	private String dlc;
	private int currentNumber;
	private int arrivalNumber;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Product refProduct;
	
	protected Batch() {}

    public long getId() {
		return id;
	}

	public Batch(String batchCode, String comingDate, String dlc, int arrivalNumber, int currentNumber, Product refProduct) {
        this.batchCode = batchCode;
        this.comingDate = comingDate;
        this.dlc = dlc;
        this.currentNumber = currentNumber;
        this.arrivalNumber = arrivalNumber;
        
        this.refProduct = refProduct;
    }

    public Batch(Batch batch) {
    	this.batchCode = batch.batchCode;
        this.comingDate = batch.comingDate;
        this.dlc = batch.dlc;
        this.currentNumber = batch.currentNumber;
        this.arrivalNumber = batch.arrivalNumber;
        
        this.refProduct = batch.refProduct;
	}

	public int getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
	}

	@Override
    public String toString() {
        return String.format(
                "Batch[id=%d, code='%s', number='%s', comingDate='%s', dlc='%s', arrivalNumber='%s', refProduct = '%s']",
                id, batchCode, this.currentNumber, this.comingDate, this.dlc, this.arrivalNumber, this.refProduct);
    }
	
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if(obj != null && obj instanceof Batch){
			Batch batch = (Batch) obj;
			isEqual = (this.batchCode.equals(batch.batchCode));
		}
        return isEqual;
	}
	
	 @Override
	 public int hashCode() {
        return this.batchCode.hashCode();
    }
    
    //--------------------------------------------------------------------------
    // Stock manipulation
    //--------------------------------------------------------------------------
    
    public Boolean isOutOfStock() {
    	return (this.currentNumber == 0);
    }
    
    public void addProducts(int productsToAdd) {
    	this.currentNumber += productsToAdd;
    }
    
    public void deleteProducts(int productsToDelete) {
    	this.currentNumber -= productsToDelete;
    }

}
