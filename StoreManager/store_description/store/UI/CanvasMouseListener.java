package store.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;


public class CanvasMouseListener extends MouseAdapter {
	
	protected DepartmentCanvas canvas;
	
	public CanvasMouseListener(DepartmentCanvas _canvas){
		this.canvas=_canvas;
		this.canvas.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				if(this.canvas.getEditionMode().equals(true)){ leftClickAction(e);}
			} else {
				rightClickAction(e);
			}
	}
	
	private void leftClickAction(MouseEvent evt) {	
		Point p = evt.getPoint();
		if(this.canvas.isInRect(p) != true)
		{
			Rectangle r = new Rectangle(p.x,p.y,30,30);
			this.canvas.addRect(r);
		}
		
		
	}
	
	//Création d'un sous-menu contextuel
	private void rightClickAction(MouseEvent evt) {	
		Point p = evt.getPoint();
		if(this.canvas.isInRect(p)){
			this.canvas.invokePopUpMenu(p,this.canvas.seeShelfSelected(p));
		}
	}
	 
}
