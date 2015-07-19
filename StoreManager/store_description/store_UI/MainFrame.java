package store_UI;

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

import store_interactions.StoreIOInteractions;
import department_description.*;

public class MainFrame extends JFrame {

    private static Store currentStore;
    private JMenuBar menuBar;
    private JMenu mainMenu;
    private JMenuItem newStoreItem, loadStoreItem, saveStoreItem;
    private JToolBar toolbar;
    private JTabbedPane tabBar;
    private DepartmentCanvas currentView;
    private CanvasMouseListener cml;
    private Department departmentEdited;
    private JComboBox<String> comboBox;
    private ArrayList<DepartmentCanvas> allViews;
    private Boolean isEditing = false;

    public MainFrame() {
        initComponents();
    }

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

	    pnlTab.add (addTab);

	    tabBar.setTabComponentAt (tabBar.getTabCount () - 1, pnlTab);

	    ActionListener listener = new ActionListener () {
	    	public void actionPerformed(ActionEvent evt) {
	    		addTabActionPerformed(evt);
	    	}
	    };
	    addTab.setFocusable (false);
	    addTab.addActionListener (listener);
	    
	    ChangeListener changeListener = new ChangeListener() {
	        public void stateChanged(ChangeEvent changeEvent) {
	          JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
	          int index = sourceTabbedPane.getSelectedIndex();
	          if (tabBar.getTabCount() > 2 && tabBar.getSelectedIndex()!=0)
	        	  cml = new CanvasMouseListener((DepartmentCanvas) tabBar.getSelectedComponent());
	          System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
	          currentView = (DepartmentCanvas) tabBar.getSelectedComponent();
	          if (isEditing)
	        	  currentView.setEditionMode(true);
	          else
	        	  currentView.setEditionMode(false);
	        }
	    };
	    tabBar.addChangeListener(changeListener);  
	    
    }

    private void initToolBar() {
        toolbar = new JToolBar();
        // Boutons à rajouter dans la toolbar
        JToggleButton editButton = new JToggleButton("Edit");
        editButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                editButtonStateChanged(evt);
            }
        });
        JButton addDepartmentButton = new JButton("Add Department");
        addDepartmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addDepartmentButtonActionPerformed(evt);
            }
        });
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        comboBox = new JComboBox<String>();

        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                //editButton.setSelected(false);
                //changeDepartment(evt);
            }
        });

        // Ajout à notre petite toolbar
        toolbar.add(editButton);
        toolbar.add(deleteButton);
        toolbar.add(addDepartmentButton);
        toolbar.add(comboBox);
    }

    private void initMenu() {
        menuBar = new JMenuBar();
        mainMenu = new JMenu("File");
        newStoreItem = new JMenuItem("New Store");
        loadStoreItem = new JMenuItem("Load Store");
        saveStoreItem = new JMenuItem("Save Store");

        menuBar.add(mainMenu);
        mainMenu.add(newStoreItem);
        mainMenu.add(loadStoreItem);
        mainMenu.add(saveStoreItem);
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
    }

    // Code des listeners
    // Listeners des outils du menu

    private void newStoreMenuItemActionPerformed(ActionEvent evt) {
        
        try {
            remove(currentView);
            comboBox.removeAllItems();
        } catch (Exception e) {
            
        }
        // Initialiser le store et le département en édition
        allViews = new ArrayList<DepartmentCanvas>();
        ArrayList<Department> departements = new ArrayList();
        String storeName = JOptionPane.showInputDialog(this, "Nom du magasin");
        if (storeName != null || !storeName.isEmpty()) {
            currentStore = new Store(storeName, departements);
            setTitle(storeName);

            String departmentName = JOptionPane.showInputDialog(this,
                    "Nom du département");
            if (departmentName == null || departmentName.isEmpty()) {
                departmentName = "Default";
            }
            departmentEdited = new Department(departmentName,
                    new ArrayList<Shelf>());
            currentStore.getDepartmentsList().add(departmentEdited);

            // Notifier la combobox
            comboBox.addItem(departmentName);
            
            // Afficher les barres
            toolbar.setVisible(true);
            
            
            // Afficher la zone d'edition dans un premier onglet
            DepartmentCanvas cd = new DepartmentCanvas(departmentEdited);
            tabBar.add(departmentName,cd);
            int index = tabBar.getTabCount() - 1;
            tabBar.setSelectedIndex(index);
            tabBar.setVisible(true);
            allViews.add(cd);
            currentView = cd;
            
            /*// Rajouter la zone d'édition dans la page
            DepartmentCanvas cd = new DepartmentCanvas(departmentEdited);
            allViews.add(cd);
            currentView = cd;*/

            cml = new CanvasMouseListener(currentView);
            //getContentPane().add(currentView, BorderLayout.CENTER);
            pack();
        }
    }

    private void loadStoreMenuItemActionPerformed(ActionEvent evt) {
        allViews = new ArrayList<DepartmentCanvas>();
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        // dans le champ AddressField via une interface d'exploration de
        // fichiers
        chooser.setDialogTitle("Ouvrir fichier");
        chooser.setApproveButtonText("Choix du fichier");

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String pathName = chooser.getSelectedFile().getAbsolutePath();

            // Chargement du store concerné
            StoreIOInteractions interactions = new StoreIOInteractions();
            try {
                currentStore = interactions.loadStore(pathName);
                departmentEdited = currentStore.getDepartmentsList().get(0);

                // Mettre à jour la comboBox
                for (Department d : currentStore.getDepartmentsList()) {
                    allViews.add(new DepartmentCanvas(d));
                    comboBox.addItem(d.getDepartmentName());
                }

                // Rajouter la zone d'édition dans la page
                currentView = allViews.get(0);
                cml = new CanvasMouseListener(currentView);
                getContentPane().add(currentView, BorderLayout.CENTER);
                pack();
            } catch (JDOMException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // JOptionPane.showMessageDialog(this,"Fichier chargé avec succès","Store Manager",JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveStoreMenuItemActionPerformed(ActionEvent evt) {
        // Demande de confirmation à faire..

        // Mise à jour du store avant sauvegarde
        departmentEdited = currentView.getUpdatedDepartment();
        for (Department d : currentStore.getDepartmentsList())
            if (d.getDepartmentName().equals(
                    departmentEdited.getDepartmentName()))
                d.setShelvesList(departmentEdited.getShelvesList());

        // Stockage du store en xml
        StoreIOInteractions interactions = new StoreIOInteractions();
        interactions.saveStore(currentStore);

    }

    // Listeners des différents composants de la ToolBar
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

    private void addTabActionPerformed (ActionEvent evt) {
    	String departmentName = JOptionPane.showInputDialog(this,
                        "Nom du département");
                if (departmentName != null || !departmentName.isEmpty()) {
                    departmentEdited = new Department(departmentName,
                            new ArrayList<Shelf>());
                    currentStore.getDepartmentsList().add(departmentEdited);

                    // Notifier la combobox
                    comboBox.addItem(departmentName);

                    // Rajouter la zone d'édition dans la page
                    DepartmentCanvas newView = new DepartmentCanvas(
                            departmentEdited);
                    tabBar.add(departmentName, newView);
                    int index = tabBar.getTabCount() - 1;
                    tabBar.setSelectedIndex(index);
                    //allViews.add(newView);
                    comboBox.setSelectedItem(departmentName);
                    //getContentPane().remove(currentView);
                    currentView = newView;
                    
                    //cml = new CanvasMouseListener(currentView);
                    //getContentPane().add(currentView, BorderLayout.SOUTH);
                    repaint();
                    pack();
                }
    }
        
    
    
    private void addDepartmentButtonActionPerformed(ActionEvent evt) {
        // try pour si currentView non existant
        try {
            if (currentView.isValid()) {
                String departmentName = JOptionPane.showInputDialog(this,
                        "Nom du département");
                if (departmentName != null || !departmentName.isEmpty()) {
                    departmentEdited = new Department(departmentName,
                            new ArrayList<Shelf>());
                    currentStore.getDepartmentsList().add(departmentEdited);

                    // Notifier la combobox
                    comboBox.addItem(departmentName);

                    // Rajouter la zone d'édition dans la page
                    DepartmentCanvas newView = new DepartmentCanvas(
                            departmentEdited);
                    allViews.add(newView);
                    getContentPane().remove(currentView);
                    currentView = newView;
                    comboBox.setSelectedItem(departmentName);
                    
                    cml = new CanvasMouseListener(currentView);
                    getContentPane().add(currentView, BorderLayout.SOUTH);
                    repaint();
                    pack();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Magasin non présent",
                    "Store Manager", JOptionPane.ERROR_MESSAGE);
        }

    }

    /*
    private void changeDepartment(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            JComboBox<String> combo = (JComboBox<String>) event.getSource();
            String itemClicked = combo.getSelectedItem().toString();
            int indexToLoad = 0;
            if (allViews.size() != 0) {
                for (int i = 0; i < currentStore.getDepartmentsList().size(); i++)
                    if (itemClicked.equals(currentStore.getDepartmentsList()
                            .get(i).getDepartmentName())) {
                        indexToLoad = i;
                        break;
                    } else {
                        indexToLoad = 0;
                    }
                getContentPane().remove(currentView);
                currentView = allViews.get(indexToLoad);
                repaint();
                cml = new CanvasMouseListener(currentView);
                getContentPane().add(currentView, BorderLayout.CENTER);
                pack();
                repaint();
            }
            pack();
        }
    }
*/

    // Pour démarrer la fenêtre
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

}
