package model;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class DrawingBoard extends JComponent{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Shape>shapes=new ArrayList<Shape>();
	private ArrayList<Color>shapeFill=new ArrayList<Color>();
	private ArrayList<Color>shapeStroke=new ArrayList<Color>();
	private ArrayList<Float> transPercent = new ArrayList<Float>();
	private Point drawStart; 
	private Point drawEnd;
	private Graphics2D graphicSettings;
	
	public DrawingBoard(){
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();	
		Border compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
		Border redline = BorderFactory.createLineBorder(Color.red);
		compound = BorderFactory.createCompoundBorder(
                redline, compound);	
		this.setBorder(compound);
		
	}
	
	public void paint(Graphics g){
		//Class used to define the shapes to be drawn
		graphicSettings=(Graphics2D)g;
		//Antialiasing cleans up the jagged lines
		graphicSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

		// Defines the line width of the stroke
         graphicSettings.setStroke(new BasicStroke(4));
         
        //Iterators created to cycle through stroked and fills
         Iterator<Color>strokeCounter=shapeStroke.iterator();
         Iterator<Color>fillCounter=shapeFill.iterator();
         
        //Iterator for transparency
         Iterator<Float>transCounter=transPercent.iterator();
         for (Shape s: shapes) {
        	 //Sets the shapes transparency value
        	 graphicSettings.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, transCounter.next()));
        	// Grabs the next stroke from the color arraylist
         	graphicSettings.setPaint(strokeCounter.next()); 
         	graphicSettings.draw(s);
            // Grabs the next fill from the color arraylist
        	graphicSettings.setPaint(fillCounter.next()); 	
        	graphicSettings.fill(s);
        }
         
        //Guide shape used for drawing
        if (drawStart != null && drawEnd != null){
        	//makes the guide shape transparent
        	graphicSettings.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.40f));
        	
        	// Make guide shape gray for professional look            
        	graphicSettings.setPaint(Color.LIGHT_GRAY);
        	Shape aShape = null;
        	
        	aShape = drawLine(drawStart.x, drawStart.y,
            		drawEnd.x, drawEnd.y);
        	
        	graphicSettings.draw(aShape);
        }
	}
	
	 public Line2D.Float drawLine(
             int x1, int y1, int x2, int y2)
     {

             return new Line2D.Float(
                     x1, y1, x2, y2);
     }
	
	public Point getDrawStart() {
		return drawStart;
	}

	public void setDrawStart(Point drawStart) {
		this.drawStart = drawStart;
	}

	public Point getDrawEnd() {
		return drawEnd;
	}

	public void setDrawEnd(Point drawEnd) {
		this.drawEnd = drawEnd;
	}

	
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public ArrayList<Color> getShapeFill() {
		return shapeFill;
	}

	public void setShapeFill(ArrayList<Color> shapeFill) {
		this.shapeFill = shapeFill;
	}

	public ArrayList<Color> getShapeStroke() {
		return shapeStroke;
	}

	public void setShapeStroke(ArrayList<Color> shapeStroke) {
		this.shapeStroke = shapeStroke;
	}

	
	
	public ArrayList<Float> getTransPercent() {
		return transPercent;
	}

	public void setTransPercent(ArrayList<Float> transPercent) {
		this.transPercent = transPercent;
	}

	public void addMyMouseActivity(MouseAdapter mouse){
		this.addMouseListener(mouse);
	}
	public void addMyMotionActivity(MouseMotionAdapter mouseM){
		this.addMouseMotionListener(mouseM);
	}
}
