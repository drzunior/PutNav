package pl.poznan.put.nav.admin;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -7706750255666688523L;

	private MapPanel mapPanel = AppFactory.getMapPanel();
	private PropertiesPanel propertiesPanel = AppFactory.getPropertiesPanel();
	private ActionsPanel actionsPanel = AppFactory.getActionsPanel();
	
	public MainFrame() {
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		
		this.add(mapPanel, BorderLayout.CENTER);
		this.add(propertiesPanel, BorderLayout.WEST);
		this.add(actionsPanel, BorderLayout.NORTH);
		
		createMenu();
	}
	
	public void createMenu() {
		JMenu fileMenu = new JMenu("Plik");
		JMenu addMenuItem = new JMenu("Dodaj");
		JMenuItem addBuildingMenuItem = new JMenuItem("Budynek");
		JMenuItem addMapMenuItem = new JMenuItem("Mape");
		JMenuItem addImageMenuItem = new JMenuItem("Zdjecie");
		JMenuItem openMenuItem = new JMenuItem("Otw�rz...");
		JMenuItem saveMenuItem = new JMenuItem("Zapisz");
		JMenuItem saveAsMenuItem = new JMenuItem("Zapisz jako...");
		JMenuItem exitMenuItem =  new JMenuItem("Zakoncz");
		addMenuItem.add(addBuildingMenuItem);
		addMenuItem.add(addMapMenuItem);
		addMenuItem.add(addImageMenuItem);
		fileMenu.add(addMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.add(exitMenuItem);
		
		JMenu editMenu = new JMenu("Edycja");
		JMenuItem undoMenuItem = new JMenuItem("Cofnij");
		JMenuItem redoMenuItem = new JMenuItem("Ponow");
		JMenuItem deleteAllPoints = new JMenuItem("Usun wszystkie punkty");
		editMenu.add(undoMenuItem);
		editMenu.add(redoMenuItem);
		editMenu.add(deleteAllPoints);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		setJMenuBar(menuBar);
	}
}
