package store.UI;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;

import store.model.Product;
import store.model.ProductPlace;
import store.model.Shelf;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ComponentOrientation;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class SubFrame extends JFrame{
	
	private Shelf shelfSelected;
	private ProductPlacePanel[] productPlacePanels;
	
	public SubFrame(Shelf selected){
		this.shelfSelected=selected;
		
		//Ajout d'une banane pour tester XD
		ProductPlace pp = new ProductPlace(new Product("banane"),30);
		pp.setProductsNumber(13);
		this.shelfSelected.getProductPlacesList().add(pp);
		
		productPlacePanels = new ProductPlacePanel[6];
		for(int i=0;i<productPlacePanels.length;i++){
			ProductPlace productPlace = (i < this.shelfSelected.getProductPlacesList().size()) ? 
					this.shelfSelected.getProductPlacesList().get(i) : new ProductPlace();
			productPlacePanels[i] = new ProductPlacePanel(productPlace);
		}
		
		initComponents();
	}

	private void initComponents() {
		setTitle(this.shelfSelected.getShelfName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(500,300));
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(150, 100));
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		//boxPanel1
		
		JPanel boxPanel1 = new JPanel();
		mainPanel.add(boxPanel1);
		boxPanel1.setLayout(new BoxLayout(boxPanel1, BoxLayout.PAGE_AXIS));
		
		JPanel box11 = new JPanel();
		box11.setBorder(new LineBorder(new Color(0, 0, 0)));
		boxPanel1.add(box11);
		box11.setLayout(null);
		box11.add(productPlacePanels[0]);
		
		JPanel box12 = new JPanel();
		boxPanel1.add(box12);
		box12.setLayout(null);
		box12.add(productPlacePanels[3]);
		
		//boxPanel2
		
		JPanel boxPanel2 = new JPanel();
		mainPanel.add(boxPanel2);
		boxPanel2.setLayout(new BoxLayout(boxPanel2, BoxLayout.PAGE_AXIS));
		
		JPanel box21 = new JPanel();
		boxPanel2.add(box21);
		box21.setLayout(null);
		box21.add(productPlacePanels[1]);
		
		
		JPanel box22 = new JPanel();
		boxPanel2.add(box22);
		box22.setLayout(null);
		box22.add(productPlacePanels[4]);
		
		//boxpanel3
		
		JPanel boxPanel3 = new JPanel();
		mainPanel.add(boxPanel3);
		boxPanel3.setLayout(new BoxLayout(boxPanel3, BoxLayout.PAGE_AXIS));
		
		JPanel box31 = new JPanel();
		boxPanel3.add(box31);
		box31.setLayout(null);
		box31.add(productPlacePanels[2]);
		
		JPanel box32 = new JPanel();
		boxPanel3.add(box32);
		box32.setLayout(null);
		box32.add(productPlacePanels[5]);
		
		pack();
	}
}
