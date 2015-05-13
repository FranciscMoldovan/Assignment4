package controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import model.DrawingBoard;
import model.DrawingFunctions;
import view.ViewDrawingBoard;

public class Controller {
	
	
	private DrawingBoard drawB = new DrawingBoard();
	private ViewDrawingBoard viewDrawB = new ViewDrawingBoard(drawB);
	private Color strokeColor = Color.BLACK;
	private Color fillColor = Color.BLACK;
	private float transparentVal = 1.0f;
	public Controller(){

		viewDrawB.addButtonActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==viewDrawB.getBtnBrush()){
						drawB.setCurrentAction(1);
						//System.out.println("CRTACC="+currentAction);
				}
			}
		});
		viewDrawB.addButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==viewDrawB.getBtnLine()){
					drawB.setCurrentAction(2);
				//System.out.println("CRTACC="+currentAction);
				}
			}
		});
		viewDrawB.addButtonActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==viewDrawB.getBtnEllipse()){
					drawB.setCurrentAction(3);
				}
			}
		});
		
		DrawingFunctions myFunctions = new DrawingFunctions();
		
		drawB.addMyMouseActivity(new MouseAdapter() {
		      public void mousePressed(MouseEvent e){
		    	  if(drawB.getCurrentAction() != 1)
		    	  {
		    		  drawB.setDrawStart(new Point(e.getX(), e.getY()));
		    		  drawB.setDrawEnd(drawB.getDrawStart());
		    		  drawB.repaint();
		    		  System.out.println("PRESSED:"+drawB.getDrawStart());
		    	  }
		      }
		      public void mouseReleased(MouseEvent e){
		    	  
		    	  if(drawB.getCurrentAction()!=1){
		    	  
		    	  Shape aShape = null; 
		    	  if(drawB.getCurrentAction()==2)
		    	  {
		    		  aShape=myFunctions.drawLine(drawB.getDrawStart().x,
		    				  drawB.getDrawStart().y,
		    				  e.getX(),
		    				  e.getY());
		    	  }
		    	  else if(drawB.getCurrentAction()==3)
		    	  {	    		  
                  		aShape = myFunctions.drawEllipse(drawB.getDrawStart().x,
  		    				  drawB.getDrawStart().y,
                  				e.getX(), e.getY());        	
		    	  }
		    	  drawB.getShapes().add(aShape);
		    	  drawB.getShapeFill().add(fillColor);
		    	  drawB.getShapeStroke().add(strokeColor);
		    	  
		    	  drawB.getTransPercent().add(transparentVal);
		    	  drawB.setDrawStart(null);
		    	  drawB.setDrawEnd(null);
		    	  
		    	  drawB.repaint();
		    	  }
		      }
		});
		drawB.addMyMotionActivity(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e){
			if (drawB.getCurrentAction()==1){	
				int x = e.getX();
				int y = e.getY();
				
				Shape aShape=null; 
				strokeColor=fillColor; 
				aShape = myFunctions.drawBrush(x,y,5,35);
				
				drawB.getShapes().add(aShape);
				drawB.getShapeFill().add(fillColor);
				drawB.getShapeStroke().add(strokeColor);
				drawB.getTransPercent().add(transparentVal);
				
//				drawB.setDrawStart(null);
//				drawB.setDrawEnd(null);
//				drawB.repaint();
			}
			drawB.setDrawEnd(new Point(e.getX(),e.getY()));
			drawB.repaint();
			}
		});
	}
	



	public static void main (String[] args){
		Controller c = new Controller();
		c.viewDrawB.setVisible(true);
	}
}
