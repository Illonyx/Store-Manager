package store.model;

public class Batch {
	
	private String id;
	private int number;
	
	
	//Permet de gérer les versions des produits
	public Batch(String id, int number){
		this.id = id;
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Boolean isOutOfStock(){
		return (number==0);
	}
	
	
	
}
