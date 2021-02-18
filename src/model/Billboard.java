package model;

import java.io.Serializable;

public class Billboard implements Serializable{
	private static final long serialVersionUID = 1;
	
	private double width;
	private double height;
	private boolean inUse;
	private String brand;
	
	public Billboard(double w, double h, boolean iu, String b) {
		width = w;
		height = h;
		inUse = iu;
		brand = b;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public boolean getInUse() {
		return inUse;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public double calculateArea(double w, double h) {
		double area = w * h;
		return area;
	}
	
	
	
}
