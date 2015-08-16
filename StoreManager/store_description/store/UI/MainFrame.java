package store.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.chrono.Era;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.ComponentView;

import org.jdom2.JDOMException;

import store.model.*;
import store.utils.DepartmentUtils;
import store.utils.StoreUtils;

public class MainFrame extends JFrame {

	//Données
    private static Store currentStore;
    private Boolean isEditing = false;
    private DepartmentCanvas currentView;
    private CanvasMouseListener cml;
    
    //Composants
    private JMenuBar menuBar;
    private JMenu mainMenu, departmentMenu;
    private JMenuItem newStoreItem, loadStoreItem, saveStoreItem;
    private JMenuItem addDepartmentItem, loadSelectedDepartmentsItem;
    private JToolBar toolbar;
    private JTabbedPane tabBar;
   
    public MainFrame() {
        initComponents();
    }

    //----------------------------------------------------------------------------------------
    // Initializers
    //----------------------------------------------------------------------------------------
    
    private void initComponents() {

        // Caractéristiques de la fenêtre

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        initMenu();
        initToolBar();
        initTabs();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolbar, BorderLayout.NORTH);
        getContentPane().add(tabBar, BorderLayout.CENTER);
        toolbar.setVisible(false);
        tabBar.setVisible(false);
        pack();
    }
    
    private void initTabs() {
    	tabBar = new JTabbedPane ();
	    tabBar.addTab ("test", null);
	    FlowLayout f = new FlowLayout (FlowLayout.CENTER, 5, 0);

	    // Make a small JPanel with the layout and make it non-opaque
	    JPanel pnlTab = new JPanel (f);
	    pnlTab.setOpaque (false);
	    // Create a JButton for adding the tabs
	    JButton addTab = new JButton ();
	    addTab.setIcon(new ImageIcon("images/add24.png","add"));
	    addTab.setOpaque (false); //
	    addTab.setBorder (null);
	    addTab.setContentAreaFilled (false);
	    addTab.setFocusPainted (false);
	    addTab.setFocusable (false);
	    addTab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	addTabActionPerformed(evt);
            }
        });
	    
	    pnlTab.add(addTab);
	    tabBar.setTabComponentAt (tabBar.getTabCount () - 1, pnlTab);
	    tabBar.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
            	tabBarStateChanged(evt);
            }
        }); 
    }

    private void initToolBar() {
        toolbar = new JToolBar();
        // Boutons à rajouter dans la toolbar
        JToggleButton editButton = new JToggleButton();
        editButton.setIcon(new ImageIcon("images/edit_24.png","edit"));
        editButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                editButtonStateChanged(evt);
            }
        });
        JButton saveDepartmentButton = new JButton();
        saveDepartmentButton.setIcon(new ImageIcon("images/save24.png","save"));
        saveDepartmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveDepartmentButtonActionPerformed(evt);
            }
        });
        JButton saveAllDepartmentsButton = new JButton();
        saveAllDepartmentsButton.setIcon(new ImageIcon("images/saveAll24.png","saveAll"));
        saveAllDepartmentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveAllDepartmentsButtonActionPerformed(evt);
            }
        });
        JButton deleteButton = new JButton();
        deleteButton.setIcon(new ImageIcon("images/delete24.png","delete"));
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        
        // Ajout à notre petite toolbar
        toolbar.add(editButton);
        toolbar.add(deleteButton);
        toolbar.add(saveDepartmentButton);
        toolbar.add(saveAllDepartmentsButton);
    }

    private void initMenu() {
        menuBar = new JMenuBar();
        
        mainMenu = new JMenu("File");
        newStoreItem = new JMenuItem("New Store");
        loadStoreItem = new JMenuItem("Load Store");
        saveStoreItem = new JMenuItem("Save Store");
        
        departmentMenu = new JMenu("Departments");
        addDepartmentItem = new JMenuItem("Add Department");
        loadSelectedDepartmentsItem = new JMenuItem("Select Departments");
        
        mainMenu.add(newStoreItem);
        mainMenu.add(loadStoreItem);
        mainMenu.add(saveStoreItem);
        
        departmentMenu.add(addDepartmentItem);
        departmentMenu.add(loadSelectedDepartmentsItem);
        
        menuBar.add(mainMenu);
        menuBar.add(departmentMenu);
        setJMenuBar(menuBar);

        // Listeners Composants Menus
        newStoreItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                newStoreMenuItemActionPerformed(evt);
            }
        });
        loadStoreItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadStoreMenuItemActionPerformed(evt);
            }
        });
        saveStoreItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveStoreMenuItemActionPerformed(evt);
            }
        });
        addDepartmentItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	addDepartmentMenuItemActionPerformed(evt);
            }
        });
        loadSelectedDepartmentsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadSelectedDepartmentsMenuItemActionPerformed(evt);
            }
        });
        
    }

    //----------------------------------------------------------------------
    // Listeners - ToolBar, TabBar
    //----------------------------------------------------------------------
    
    //Listeners Toolbar
    
    private void editButtonStateChanged(ItemEvent evt) {
        // try pour si currentView non existant
        try {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                currentView.setEditionMode(true);
                isEditing = true;
            } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
                currentView.setEditionMode(false);
                isEditing = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Magasin non présent",
                    "Store Manager", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteButtonActionPerformed(ActionEvent evt) {
        // try pour si currentView non existant
        try {
            currentView.deleteAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Magasin non présent",
                    "Store Manager", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveDepartmentButtonActionPerformed(ActionEvent evt){
    	//Récupérer le canvas sélectionné
    	DepartmentCanvas dc = (DepartmentCanvas) tabBar.getSelectedComponent();
    	currentStore = saveUpdatedDepartment(dc, tabBar.getSelectedIndex());
    	StoreUtils.saveStore(currentStore);
    }
    
    private void saveAllDepartmentsButtonActionPerformed(ActionEvent evt){
    	//Pour tous les canvas existants dans l'éditeur, trouver ceux qui ont subi des changements
    	for(int i=1;i<tabBar.getTabCount();i++){
    		DepartmentCanvas dc = (DepartmentCanvas) tabBar.getComponentAt(i);
    		if(dc.hasChanged()==true){
    			currentStore = saveUpdatedDepartment(dc,i);
    		}
    		StoreUtils.saveStore(currentStore);
    	}
    }
    
    //Bout de code répété
    private Store saveUpdatedDepartment(DepartmentCanvas dc, int tabIndex){
    	Department d = dc.getUpdatedDepartment();
    	if(DepartmentUtils.isDepartmentAlreadyExisting(currentStore,d) == true){
    		String departmentName = JOptionPane.showInputDialog(this,
                    "Nom du département");
            if (departmentName == null || departmentName.isEmpty()) {
            	//Pas de sauvegarde faite
            	return currentStore;
            }
            else d.setDepartmentName(departmentName);
    	}
    	dc.setHasChanged(false);
    	tabBar.setTitleAt(tabIndex, d.getDepartmentName());
    	return DepartmentUtils.departmentHasBeenChanged(currentStore,d);
    }
    
    //Listeners TabBar
    
    private void addTabActionPerformed (ActionEvent evt) {

    	//Mettre un numéro derrière
    	String departmentName = "new";
    	Department aDepartment = new Department(departmentName,
    			new ArrayList<Shelf>());
    	DepartmentCanvas newView = new DepartmentCanvas(aDepartment);
    	
    	// Rajout du nouvel onglet avec le canvas associé
    	
    	tabBar.add(departmentName, newView);
    	int index = tabBar.getTabCount() - 1;
    	tabBar.setSelectedIndex(index);
    	
    	//Changement de currentView
    	currentView = newView;
    	
    	repaint();
    	pack();
                
    }
    
    private void tabBarStateChanged(ChangeEvent evt){
    	  JTabbedPane sourceTabbedPane = (JTabbedPane) evt.getSource();
          int index = sourceTabbedPane.getSelectedIndex();
          if (tabBar.getTabCount() > 2 && tabBar.getSelectedIndex()!=0)
        	  cml = new CanvasMouseListener((DepartmentCanvas) tabBar.getSelectedComponent());
          System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
          
          //Changment de Department, adaptation au mode édition en course
          currentView = (DepartmentCanvas) tabBar.getSelectedComponent();
          currentView.setEditionMode(isEditing);
    }
    
    
    //-----------------------------------------------------------------------
    // Listeners - Main Menu
    //-----------------------------------------------------------------------

    private void newStoreMenuItemActionPerformed(ActionEvent evt) {
        
        //remove(currentView);
       
        // Initialiser le store et le département en édition
        ArrayList<Department> departements = new ArrayList();
        String storeName = JOptionPane.showInputDialog(this, "Nom du magasin");
        if (storeName != null || !storeName.isEmpty()) {
            currentStore = new Store(storeName, departements);
            setTitle(storeName);

            String departmentName = JOptionPane.showInputDialog(this,
                    "Nom du département");
            if (departmentName == null || departmentName.isEmpty()) {
                departmentName = "new";
            }
            Department aDepartment = new Department(departmentName,
                    new ArrayList<Shelf>());
            DepartmentUtils.departmentHasBeenChanged(currentStore, aDepartment);
            
            // Afficher les barres
            toolbar.setVisible(true);
            
            
            // Afficher la zone d'edition dans un premier onglet
            DepartmentCanvas cd = new DepartmentCanvas(aDepartment);
            tabBar.add(departmentName,cd);
            int index = tabBar.getTabCount() - 1;
            tabBar.setSelectedIndex(index);
            tabBar.setVisible(true);
            currentView = cd;
            cml = new CanvasMouseListener(currentView);
        }
    }

    private void loadStoreMenuItemActionPerformed(ActionEvent evt) {
        
    	//L'utilisateur choisit le magasin à ouvrir
    	JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        chooser.setDialogTitle("Ouvrir fichier");
        chooser.setApproveButtonText("Choix du fichier");

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String pathName = chooser.getSelectedFile().getAbsolutePath();

            try {
                currentStore = StoreUtils.loadStore(pathName);

                // Mettre à jour la comboBox
                for (Department d : currentStore.getDepartmentsList()) {
                	tabBar.add(d.getDepartmentName(), new DepartmentCanvas(d));
                }
                
                toolbar.setVisible(true);
                tabBar.setSelectedIndex(1);
                tabBar.setVisible(true);
                currentView = (DepartmentCanvas) tabBar.getSelectedComponent();
                cml = new CanvasMouseListener(currentView);
                
                
            } catch (JDOMException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private void saveStoreMenuItemActionPerformed(ActionEvent evt) {
    	
    	// Demander à l'utilisateur où il veut sauvegarder (Nom de la fenetre : Enregistrer sous)
    	// Puis sauvegarde des modifications
        // Stockage du store en xml
        StoreUtils.saveStore(currentStore);

    }

    // ----------------------------------------------------------------------------
    // Listeners - DepartmentMenu
    // ----------------------------------------------------------------------------
    
    private void addDepartmentMenuItemActionPerformed(ActionEvent evt){
    	System.out.println("To Do");
    }
    
    private void loadSelectedDepartmentsMenuItemActionPerformed(ActionEvent evt){
    	System.out.println("To do too");
    }

    
    // -----------------------------------------------------------------------------
    // Launcher
    // -----------------------------------------------------------------------------
  
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

}
