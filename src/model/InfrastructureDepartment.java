package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class InfrastructureDepartment {
	public final static String BILLBOARD_FILE_NAME = "data/billboard.bbd";
	public final static String SEPARATOR = ";";
	private List<Billboard> billboards;
	
	public InfrastructureDepartment() {
		billboards = new ArrayList<>();
	}
	
	public void addBillboard(double w, double h, boolean iu, String b) throws IOException {
		billboards.add(new Billboard(w, h, iu, b));
		saveBillboards();
	}
	
	public void saveBillboards() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BILLBOARD_FILE_NAME));
		oos.writeObject(billboards);
		oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadBillboard() throws IOException, ClassNotFoundException {
		File f = new File(BILLBOARD_FILE_NAME);
	    boolean loaded = false;
	    if(f.exists()){
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      billboards = (List<Billboard>)ois.readObject();
	      ois.close();
	      loaded = true;
	    }
	    return loaded;
	}
	
	public void exportDangerousBillboardReport(String fn) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(fn);

	    for(int i=0;i<billboards.size();i++){
	    	Billboard bd = billboards.get(i);
	    	pw.println(bd.getWidth()+SEPARATOR+bd.getHeight()+SEPARATOR+bd.getInUse()+SEPARATOR+bd.getBrand());
	    }

	    pw.close();
	}

	public void inportData(String fn) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fn));
	    String line = br.readLine();
	    while(line!=null){
	    	String[] parts = line.split(SEPARATOR);
	    	double newWidth = Double.parseDouble(parts[0]);
	    	double newheight = Double.parseDouble(parts[1]);
	    	boolean newInUse = Boolean.parseBoolean(parts[2]);
	    	addBillboard(newWidth,newheight,newInUse,parts[3]);
	    	line = br.readLine();
	    }
	    br.close();
	}

	public String toString() {
		String x = "";
		return x;
	}
}
