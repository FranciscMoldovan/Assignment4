package controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import model.DrawingBoard;
import view.ViewDrawingBoard;

public class Controller {
	
	private int currentAction = 2;
	private DrawingBoard drawB = new DrawingBoard();
	private ViewDrawingBoard viewDrawB = new ViewDrawingBoard(drawB);
	private Color strokeColor = Color.BLACK;
	private Color fillColor = Color.BLACK;
	private float transparentVal = 1.0f;
	public Controller(){
		
	
		
		drawB.addMyMouseActivity(new MouseAdapter() {
		      public void mousePressed(MouseEvent e){
		    	  if(currentAction != 1){
		    		  drawB.setDrawStart(new Point(e.getX(), e.getY()));
		    		  drawB.setDrawEnd(drawB.getDrawStart());
		    		  drawB.repaint();
		    		  System.out.println("PRESSED:"+drawB.getDrawStart());
		    	  }
		      }
		      public void mouseReleased(MouseEvent e){
		    	  Shape aShape = null; 
		    	  if(currentAction==2){
		    		  aShape=drawLine(drawB.getDrawStart().x,
		    				  drawB.getDrawStart().y,
		    				  drawB.getDrawEnd().x,
		    				  drawB.getDrawEnd().y);
		    	  }
		      }
		});
		drawB.addMyMotionActivity(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e){
				int x = e.getX();
				int y = e.getY();
				
				Shape aShape=null; 
				strokeColor=fillColor; 
				aShape = drawBrush(x,y,5,5);
				
				drawB.getShapes().add(aShape);
				drawB.getShapeFill().add(fillColor);
				drawB.getShapeStroke().add(strokeColor);
				drawB.getTransPercent().add(transparentVal);
				
				drawB.setDrawStart(null);
				drawB.setDrawEnd(null);
				drawB.repaint();
			}
		});
	}
	
	 private Line2D.Float drawLine(
             int x1, int y1, int x2, int y2)
     {

             return new Line2D.Float(
                     x1, y1, x2, y2);
     }
     private Ellipse2D.Float drawBrush(
             int x1, int y1, int brushStrokeWidth, int brushStrokeHeight)
     {
     	
     	return new Ellipse2D.Float(
                 x1, y1, brushStrokeWidth, brushStrokeHeight);
     	
     }

	public static void main (String[] args){
		Controller c = new Controller();
		c.viewDrawB.setVisible(true);
	}
}
