package store.UI;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

public class CanvasMouseMotionListener extends MouseInputAdapter
{
	 protected DepartmentCanvas canvas;
	
	public CanvasMouseMotionListener(DepartmentCanvas _canvas){
		this.canvas=_canvas;
		this.canvas.addMouseMotionListener(this);
	}

	public void mouseMoved(MouseEvent evt){
		Point p = evt.getPoint();
		if(this.canvas.isInRect(p)){
			this.canvas.setToolTipText(this.canvas.seeShelfSelected(p).getShelfName());
		}
	}
	
}
