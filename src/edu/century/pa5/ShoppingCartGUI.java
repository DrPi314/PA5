package edu.century.pa5;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.*;


public class ShoppingCartGUI extends JFrame implements ActionListener {
	private final String path = "E:/Eclipse/inclassprojects/PA5/src/edu/century/pa5/";
	private JTextField browseText = new JTextField(10);
	private JButton browseButton = new JButton("Browse");
	private JFileChooser fileChooser = new JFileChooser(path);
	private JComboBox sortList = new JComboBox(Product.getCriteria());
	private JButton sortButton = new JButton("Sort");
	private JTextArea oFile = new JTextArea("Original File");
	private JTextArea sFile = new JTextArea("Sorted File");
	private JPanel topPanel = new JPanel(new BorderLayout());
	private JPanel searchPanel =  new JPanel(new GridLayout(1,4));
	private JPanel bottomPanel =  new JPanel(new GridLayout(1,2));
	
	private ArrayList<Product> products = new ArrayList<Product>();
	private static int numProducts = 0;
		
	public ShoppingCartGUI(String title) {
		super(title);
		setSize(800,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		createSearchPanel();
		createTopPanel();
		createBottomPanel();
		addPanelsToFrame();
		setListeners();
		setVisible(true);
	}
	
	private void setListeners() {
		browseButton.addActionListener(this);
		sortButton.addActionListener(this);
	}
	
	private void addPanelsToFrame() {
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.CENTER);
	}
	
	private void createSearchPanel() {
		searchPanel.add(browseText);
		searchPanel.add(browseButton);
		searchPanel.add(sortList);
		searchPanel.add(sortButton);
	}
	
	private void createTopPanel() {
		topPanel.add(searchPanel, BorderLayout.CENTER);
	}
	
	private void createBottomPanel() {
		bottomPanel.add(oFile);
		bottomPanel.add(sFile);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String callingBtn = e.getActionCommand();
		if(callingBtn.equalsIgnoreCase("Sort")) {
			sortProducts(sortList.getSelectedItem().toString()); 
			showSort(products);
			//saveSort(products);
		} else if(callingBtn.equalsIgnoreCase("Browse")) {
			add(fileChooser);
			fileChooser.showOpenDialog(null);
			int result = fileChooser.showOpenDialog(null);
			if(result == JFileChooser.APPROVE_OPTION)
			browseText.setText(fileChooser.getSelectedFile().getName());
			File file = fileChooser.getSelectedFile();
			fileChooserActionPerformed(file);
		}
	}
	
	private void showSort(ArrayList<Product> p) {
		sFile.append("\n" + sortList.getSelectedItem().toString());
		for(Product product : p)
			sFile.append(product.toString());	
	}

	private void fileChooserActionPerformed(File file) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			while (line != null) {
				oFile.append("\n" + line);
				++numProducts;
				this.setProducts(numProducts, line);
				line = in.readLine();
			}
			in.close();
		} catch (IOException e1) {
			oFile.append("Could not read file");
		}
	}
	
	private void saveSort(ArrayList<Product> p) {
		
	}
	
	private void setProducts(int numProducts, String line) {
		String[] s = line.split(",");
		int id = Integer.parseInt(s[0]);
		double pr = Double.parseDouble(s[1]);
		String na = s[2];
		String de = s[3];
		products.add(new Product(na, id, de, pr));
	}

	//could not get Collections to work for the life of me.
	//was recommended to try Arrays
	private void sortProducts(String c) {
		switch(c) {
		case "ID":
			Collections.sort(products, Product.CompareById);
			break;
		case "Price":
			Collections.sort(products, Product.CompareByPrice);
			break;
		case "Name":
			Collections.sort(products, Product.CompareByName);
			break;
		case "Description":
			Collections.sort(products, Product.CompareByDescription);
			break;
		}
	}
	
	public static void main(String[] args) {
		ShoppingCartGUI gui = new ShoppingCartGUI("Shopping Cart");
	}
}
