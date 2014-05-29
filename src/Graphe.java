import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;


public class Graphe extends JPanel {  // JPanel permet de dessiner des graphs
	
	protected double[] values;
	protected double max;
	protected double min;
	protected Graphics2D graph2d;
	
	 public Graphe(){
		 setPreferredSize(new Dimension(250,200));
	}
	 
	/////////////////////  LIRE FICHIER   //////////////////////////////
	/**
	* Lecture  des donn�es dans un fichier
	*/
	public static String lireFichier(String fichier){
	StringBuilder sb = new StringBuilder();
	try{
	BufferedReader buff = new BufferedReader(new FileReader(fichier));
	try {
	String line;
	while ((line = buff.readLine()) != null) {
	sb.append(line).append("\n");
	}
	} 
	finally {
	buff.close();
	}
	} 
	catch (IOException e) {
	e.printStackTrace();
	}
	return sb.toString();
	}
	
	public void getGraphValue(String sourceFile){
		values = new double[190];
		String[] lignes = lireFichier(sourceFile).split("\n");
	    String[] elements;
	    for(int k=0;k<190;k++){
	    	  elements =  lignes[k].split(";");
	    	  values[k]=Double.parseDouble(elements[1]);
	      }
	}
	
	public double getDelta(){
		if(values != null && values.length >0){
			getMaxValue();
			getMinValue();
		}
		else{
			min = 0;
			max=0;
		}
		return max-min;
	}
	
	private void getMaxValue(){
		max=values[0];
		for(int i=1 ; i<values.length; i++){
			if(values[i] > max)
				max = values[i];
		}
	}
	private void getMinValue(){
		min=values[0];
		for(int i=1 ; i<values.length; i++){
			if(values[i] < min)
				min = values[i];
		}
	}
	
	public void drawGraphique(String source){
		
		if(source != null && !source.isEmpty()){ // source= lien vers fichier diffrent de null(objet vide) ET diffrent de "" (rien)
			resetGraph();
			getGraphValue(source);
			drawAxis();
			drawData();
		}
	}
	
	
	private void resetGraph() {
		super.paint(super.getGraphics()); // initialise nouveau graph
		graph2d = (Graphics2D) super.getGraphics(); // creer un objet graphi2d (plus facile à manipuler pour la 2D) depuis le nouveau graph
	}

	public void drawAxis(){
		graph2d.setColor(Color.GRAY);
		graph2d.drawLine(10, 150, 200, 150); // axe X
		graph2d.drawLine(10, 150, 10, 10);  // axe Y
	}
	
	public void drawData(){
		graph2d.setColor(Color.BLACK);
		double value = values[0];
		double a = -140/getDelta();
		double b = - a*max+10;
		int lastValDisplay = (int) (a*value+b);
		int x = 15;
		
		System.out.println("a:"+a);
		System.out.println("b:"+b);
		
		for(int i=1; i<values.length;i++){
			int valToDisplay = (int) (a*values[i]+b);
			System.out.println("draw x:"+x+" y:"+values[i]+" y0:"+lastValDisplay+" y1:"+valToDisplay);
			graph2d.drawLine(x, lastValDisplay, x++, valToDisplay);
			lastValDisplay = valToDisplay;
		}
	}
}
