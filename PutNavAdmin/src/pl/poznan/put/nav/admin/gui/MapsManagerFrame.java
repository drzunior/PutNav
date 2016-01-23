package pl.poznan.put.nav.admin.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import pl.poznan.put.nav.admin.entities.Map;
import pl.poznan.put.nav.admin.entities.Photo;
import pl.poznan.put.nav.admin.managers.AppFactory;
import pl.poznan.put.nav.admin.managers.EntitiesManager;

public class MapsManagerFrame extends JFrame {

	private static final long serialVersionUID = -1833517033075721226L;

	private JTable table;
	private EntitiesManager em = AppFactory.getEntitiesManager();
	private List<Map> maps;
	
	public MapsManagerFrame() {
		this.setSize(600, 400);
		this.setLocation(100, 20);
		
		table = new JTable(3, 2);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		loadData();
		JScrollPane scrollPane = new JScrollPane(table);
		
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(createButtonPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel createButtonPanel() {
		JButton addButton = new JButton("Dodaj");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
				
				int result = fileChooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String path = copyMapFileToTemp(selectedFile);
					Map map = new Map();
					map.setMapFile(path);
					maps.add(map);
					
					loadData();
				}
			}
		});
		JButton delButton = new JButton("Usun");
		delButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() >= 0) {
					Map map = maps.remove(table.getSelectedRow());
					loadData();
					em.addMapToRemove(map);
				}
			}
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(delButton);
		
		return buttonPanel;
	}
	
	private void loadData() {
		maps = em.getMaps();
		TableModel model = new AbstractTableModel() {

			private static final long serialVersionUID = -3387204221783015479L;
			private String[] columnNames = { "Id", "Plik" };
			
			@Override
			public Object getValueAt(int row, int col) {
				if(col == 0)
					return maps.get(row).getId();
				if(col == 1)
					return maps.get(row).getMapFile();
				return null;
			}
			
			@Override
			public int getRowCount() {
				return maps.size();
			}
			
			@Override
			public int getColumnCount() {
				return columnNames.length;
			}
			
			@Override
			public boolean isCellEditable(int row, int col) { 
			    return false; 
			}
			
			@Override
			public String getColumnName(int col) {
		        return columnNames[col];
		    }
		};
		table.setModel(model);
	}
	
	private String copyMapFileToTemp(File mapFile) {
		
		File temp = new File("temp");
		File source = mapFile;
		File target = new File(temp + File.separator + "maps" + File.separator + mapFile.getName());
		try {
			Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String newFile = target.getPath().substring(temp.getPath().length()+6);
		return newFile;
	}
}
