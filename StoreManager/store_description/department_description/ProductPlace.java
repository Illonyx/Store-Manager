package department_description;

/** 
 * 
 * @author Alexis
 *
 */
public class ProductPlace {
	
	private int placeCapacity;
	private Product productInPlace;
	private int productsNumber; 
	//private int id;
	
	public ProductPlace(Product _productInPlace, int _placeCapacity){
		//this.id=id;
		this.placeCapacity=_placeCapacity;
		this.productInPlace=_productInPlace;
		this.productsNumber=0;
	}

	public int getPlaceCapacity() {
		return placeCapacity;
	}

	public void setPlaceCapacity(int placeCapacity) {
		this.placeCapacity = placeCapacity;
	}

	public Product getProductInPlace() {
		return this.productInPlace;
	}

	public void setProductInPlace(Product productInPlace) {
		this.productInPlace = productInPlace;
	}

	public int getProductsNumber() {
		return productsNumber;
	}

	public void setProductsNumber(int productsNumber) {
		this.productsNumber = productsNumber;
	}
}
