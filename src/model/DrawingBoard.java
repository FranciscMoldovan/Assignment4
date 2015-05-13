package model;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JComponent;

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
	private boolean load = false;
	private Graphics2D graphicSettings;
	private int currentAction=1;
	@SuppressWarnings("unused")
	private float transparencyVal = 1.0f;
	private DrawingFunctions myFunctions = new DrawingFunctions();
	public DrawingBoard(){
	
	}
	
	public void paint(Graphics g){
		//Class used to define the shapes to be drawn
		graphicSettings=(Graphics2D)g;
		//Antialiasing cleans up the jagged lines
		graphicSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


         graphicSettings.setStroke(new BasicStroke(4));  
         
//         Image img1 = Toolkit.getDefaultToolkit().getImage("saves/img.png");
//	        graphicSettings.drawImage(img1, 0, 0, this);
         
         for (int i = 0; i < shapes.size(); i++) {
        	//Sets the shapes transparency value
        	 graphicSettings.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, transPercent.get(i)));
        	// Grabs the next stroke from the color arraylist
         	graphicSettings.setPaint(shapeStroke.get(i)); 
         	graphicSettings.draw(shapes.get(i));
            // Grabs the next fill from the color arraylist
        	graphicSettings.setPaint(shapeFill.get(i)); 	
        	graphicSettings.fill(shapes.get(i));
		}
         
        if (load)
        {
        	 graphicSettings.setComposite(AlphaComposite.getInstance(
                     AlphaComposite.SRC_OVER, 1));
	     	Image img1 = Toolkit.getDefaultToolkit().getImage("saves/img.png");
	        graphicSettings.drawImage(img1, 0, 0, this);
	        
	        
	        graphicSettings.setStroke(new BasicStroke(4));       
	         for (int i = 0; i < shapes.size(); i++) {
	        	//Sets the shapes transparency value
	        	 graphicSettings.setComposite(AlphaComposite.getInstance(
	                        AlphaComposite.SRC_OVER, transPercent.get(i)));

	        	 
	        	// Grabs the next stroke from the color arraylist
	         	graphicSettings.setPaint(shapeStroke.get(i)); 
	         	graphicSettings.draw(shapes.get(i));
	            // Grabs the next fill from the color arraylist
	        	graphicSettings.setPaint(shapeFill.get(i)); 	
	        	graphicSettings.fill(shapes.get(i));
			}
        }
         
         
        //Guide shape used for drawing
        if (drawStart != null && drawEnd != null){
        	//makes the guide shape transparent
        	graphicSettings.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.40f));
        	
        	// Make guide shape gray for professional look            
        	graphicSettings.setPaint(Color.LIGHT_GRAY);
        	Shape aShape = null;
        	
        	if (currentAction==2)
        	{
        	aShape = myFunctions.drawLine(drawStart.x, drawStart.y,
            		drawEnd.x, drawEnd.y);
        	} else if (currentAction==3)
        	{
        		aShape = myFunctions.drawEllipse(drawStart.x, drawStart.y,
                		drawEnd.x, drawEnd.y);
        	} else if (currentAction==4)
        	{
        		aShape = myFunctions.drawRectangle(drawStart.x, drawStart.y,
                		drawEnd.x, drawEnd.y);
        	}
        	graphicSettings.draw(aShape);
        	
        	
        
        	 
        }
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

	
	
	public int getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(int currentAction) {
		this.currentAction = currentAction;
	}
	
	
	

	public boolean isLoad() {
		return load;
	}

	public void setLoad(boolean load) {
		this.load = load;
	}

	public Graphics2D getGraphicSettings() {
		return graphicSettings;
	}

	public void setGraphicSettings(Graphics2D graphicSettings) {
		this.graphicSettings = graphicSettings;
	}

	public void addMyMouseActivity(MouseAdapter mouse){
		this.addMouseListener(mouse);
	}
	public void addMyMotionActivity(MouseMotionAdapter mouseM){
		this.addMouseMotionListener(mouseM);
	}
}
