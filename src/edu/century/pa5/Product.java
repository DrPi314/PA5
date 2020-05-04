package edu.century.pa5;

import java.util.Comparator;

public class Product {
	private String name;
	private int uid;
	private String description;
	private double price;
	private static String[] criteria = new String[] {"ID","Price","Name","Description"};
	
	public Product(String n, int id, String d, double p) {
		this.name = n;
		this.uid = id;
		this.description = d;
		this.price = p;
	}


	public static Comparator<Product> CompareById = new Comparator<Product>() {
		@Override
		public int compare(Product p1, Product p2) {
			return p1.uid - p2.uid;
		}
	};
	
	public static Comparator<Product> CompareByName = new Comparator<Product>() {
		@Override
		public int compare(Product p1, Product p2) {
			return p1.name.compareTo(p2.name);
		}
	};
	
	public static Comparator<Product> CompareByDescription = new Comparator<Product>() {
		@Override
		public int compare(Product p1, Product p2) {
			return p1.description.compareTo(p2.description);
		}
	};
	
	public static Comparator<Product> CompareByPrice = new Comparator<Product>() {
		@Override
		public int compare(Product p1, Product p2) {
			return (int) (100 * (p1.price - p2.price) );
		}
	};
	
	@Override
	public String toString() {
		String s = "\n" + this.uid + ", " + this.name + ", " + this.description + ", " + price;
		return s;
	}
	
	public static String[] getCriteria() {
		return criteria;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public int getUid() {
		return this.uid;
	}
	
	public void setUid(int id) {
		this.uid = id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String d) {
		this.description = d;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double p) {
		this.price = p;
	}
}
