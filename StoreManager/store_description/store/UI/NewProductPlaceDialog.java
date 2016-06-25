package store.UI;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Window.Type;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.GridLayout;

public class NewProductPlaceDialog extends JFrame
{
	public NewProductPlaceDialog() {
		setSize(new Dimension(500, 600));
		setTitle("Nouvelle \u00E9tag\u00E8re");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		initComponents();
	}
	
	private void initComponents(){
		
	}
}
