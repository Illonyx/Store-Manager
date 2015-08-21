package store.UI;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import store.model.ProductPlace;

public class ProductPlacePanel extends JPanel {
	
	public String ADD_PRODUCT_ICON_PATH = "images/productImages/addProduct.png";
	public String DELETE_PRODUCT_ICON_PATH = "images/productImages/deleteProduct.png";
	public String CREATE_NEW_PRODUCT_PLACE = "images/productImages/createProductPlace.png";
	public String PRODUCT_PLACE_PICTURE = "images/productImages/";
	
	
	private JButton mainButton;
	private JButton addProduct, deleteProduct;
	private JLabel productCaption;
	
	private ProductPlace pp;
	
	
	public ProductPlacePanel(ProductPlace pp){
		super();
		this.pp=pp;
		initComponents();
	}
	
	
	private void initComponents(){
		setLayout(null);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(0, 0, 161, 130);
		
		addProduct = new JButton();
		addProduct.setBounds(0, 107, 40, 23);
		addProduct.setIcon(new ImageIcon(ADD_PRODUCT_ICON_PATH,"addProduct"));
		add(addProduct);
		
		deleteProduct = new JButton();
		deleteProduct.setBounds(121, 107, 40, 23);
		deleteProduct.setIcon(new ImageIcon(DELETE_PRODUCT_ICON_PATH,"deleteProduct"));
		add(deleteProduct);
		
		productCaption = new JLabel("productLegend");
		productCaption.setBounds(50, 111, 61, 19);
		add(productCaption);
		
		mainButton = new JButton();
		String path = (pp.getProductInPlace().getProductName() == "") ? CREATE_NEW_PRODUCT_PLACE : PRODUCT_PLACE_PICTURE + "bananas.png";
		mainButton.setIcon(new ImageIcon(path,"ppButton"));
		mainButton.setBounds(40, 17, 80, 80);
		add(mainButton);
	}

}

