package store_UI;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;

import department_description.Product;
import department_description.ProductPlace;
import department_description.Shelf;

public class SubFrame extends JFrame{
	
	private Shelf shelfSelected;
	
	public SubFrame(Shelf selected){
		ProductPlace pp= new ProductPlace(new Product("laitue"),30);
		pp.setProductsNumber(13);
		ProductPlace pp1= new ProductPlace(new Product("aubergine"),20);
		pp1.setProductsNumber(17);
		this.shelfSelected=selected;
		this.shelfSelected.getProductPlacesList().add(pp);
		this.shelfSelected.getProductPlacesList().add(pp1);
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(500,300));
		JComboBox<String> comboBox = new JComboBox<String>();
		for(ProductPlace p: this.shelfSelected.getProductPlacesList())
			comboBox.addItem(p.getProductInPlace().getProductName()+'('+p.getProductsNumber()+')');
		
		getContentPane().add(comboBox);
		pack();
	}

}
