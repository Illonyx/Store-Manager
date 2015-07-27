package store.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import store.model.Department;
import store.model.Shelf;

// --> Créer un controleur

public class DepartmentCanvas extends JPanel{
	
	private Boolean editionMode;
	//private ArrayList<Rectangle> rectsList;
	private JPopupMenu menuPopup = new JPopupMenu();
	private Department target;
	private Shelf shelfSelected;
	private Boolean hasChanged;
	
	public DepartmentCanvas(Department targetDepartment){
		this.target=targetDepartment;
		this.editionMode=false;
		this.hasChanged=true;
		initPopUpMenu();
	}
	
	public Boolean getEditionMode(){
		return this.editionMode;
	}
	
	public void setEditionMode(Boolean _editionMode){
		this.editionMode=_editionMode;
	}
	
	public Department getUpdatedDepartment(){
		return this.target;
	}
	
	public Boolean hasChanged(){
		return hasChanged;
	}
	
	public void setHasChanged(Boolean hasChanged1){
		hasChanged = hasChanged1;
	}
	
	// --------------------------------------------------------------------
	// Painting methods
	// --------------------------------------------------------------------
	
	public void paint(Graphics g) {
		//Dessiner le fond 
		 g.setColor(Color.WHITE);
		 g.fillRect(0, 0, this.getWidth(), this.getHeight());
		 g.setColor(Color.GRAY);
		for (Shelf sh:this.target.getShelvesList()) {
			Rectangle r = sh.getRepresentation();
			g.fillRect(r.x,r.y,r.height,r.width);
		}
	}
	
	
	public void addRect(Rectangle r){
		 String shelfName = JOptionPane.showInputDialog(this, "Nom du rayon");
		 if(isShelfNameAvailable(shelfName) == true)
		 {
	                this.target.getShelvesList().add(new Shelf(shelfName, r));
	                repaint();
	                this.hasChanged = true;
	     }
	}
	
	public Boolean isShelfNameAvailable(String chosen)
	{
		ArrayList<String> alreadyNamedShelves = new ArrayList<String>();
		for(int i=0;i<this.target.getShelvesList().size();i++)
		{
			alreadyNamedShelves.add(this.target.getShelvesList().get(i).getShelfName());
		}
		if(alreadyNamedShelves.contains(chosen) == true || chosen.isEmpty()) 
		{
			return false;
		}
		else return true;
	}

	public void deleteAll() {
		this.target.setShelvesList(new ArrayList<Shelf>());
		repaint();
		this.hasChanged = true;
	}
	
	//Parcourir la liste des rectangles du canvas correspondant
	public Boolean isInRect(Point p)
	{
		Boolean isInRect = false;
		for(Shelf sh: this.target.getShelvesList())
		{
			Rectangle r = sh.getRepresentation();
			if(r.contains(p)) isInRect=true;
		}
		return isInRect;
	}
	
	//Faudrait que je change le nom de cette méthode xP
	public Shelf seeShelfSelected(Point p)
	{
		Shelf s = null;
		for(Shelf sh: this.target.getShelvesList())
		{
			Rectangle r = sh.getRepresentation();
			if(r.contains(p)) s=sh;
		}
		return s;
	}
	
	// ------------------------------------------------------------------------ 
	// Pop-up Menu
	// ------------------------------------------------------------------------
	
	public void invokePopUpMenu(Point p,Shelf s)
	{
		this.shelfSelected=s;
		menuPopup.show(this,p.x,p.y);
	}
	
	private void initPopUpMenu()
	{
		JMenuItem subFrameMenuItem = new JMenuItem("Edit Shelf");
		subFrameMenuItem.addActionListener(
						new ActionListener(){
							 public void actionPerformed(ActionEvent evt)
				                {
				                    subFrameMenuItemActionPerformed(evt);
				                }
						});
		
		
		JMenuItem renameMenuItem = new JMenuItem("Rename");
		renameMenuItem.addActionListener(
				new ActionListener(){
					 public void actionPerformed(ActionEvent evt)
		                {
		                    renameMenuItemActionPerformed(evt);
		                }
				});
		
		JMenuItem removeMenuItem = new JMenuItem("Remove");
		removeMenuItem.addActionListener(
				new ActionListener(){
					 public void actionPerformed(ActionEvent evt)
		                {
		                    removeMenuItemActionPerformed(evt);
		                }
				});
		menuPopup.add(subFrameMenuItem);
		menuPopup.add(renameMenuItem);
		menuPopup.add(removeMenuItem);
	}
	
	private void subFrameMenuItemActionPerformed(ActionEvent evt)
	{
		 SubFrame sf = new SubFrame(this.shelfSelected);
		 sf.setVisible(true);
	}
	
	private void renameMenuItemActionPerformed(ActionEvent evt)
	{
		
	}
	
	private void removeMenuItemActionPerformed(ActionEvent evt)
	{
		
	}
	
}


/*
 * for (Iterator<Rectangle> iter = this.rectsList.iterator(); iter.hasNext();) {
		Rectangle r = (Rectangle) iter.next();
		g.fillRect(r.x,r.y,r.height,r.width);
	}
 * 
 */



