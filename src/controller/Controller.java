package controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DecimalFormat;

import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.DrawingBoard;
import model.DrawingFunctions;
import view.ViewDrawingBoard;

public class Controller {
	
	
	private DrawingBoard drawB = new DrawingBoard();
	private ViewDrawingBoard viewDrawB = new ViewDrawingBoard(drawB);
	private Color strokeColor = Color.BLACK;
	private Color fillColor = Color.BLACK;
	private float transparentVal = 1.0f;
	private DecimalFormat dec = new DecimalFormat("#.##");
	public Controller(){

		DrawingFunctions myFunctions = new DrawingFunctions();
		
		viewDrawB.addMyChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if (arg0.getSource()==viewDrawB.getTransparencySlider()){
					viewDrawB.setTransparencyLabel("Transparent: "+dec.format(viewDrawB.getTransparencySlider().getValue()*.01));
					transparentVal=(float)(viewDrawB.getTransparencySlider().getValue()*.01);
				}
			}
		});
		viewDrawB.addButtonActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==viewDrawB.getBtnBrush()){
						drawB.setCurrentAction(1);
				}
				if (e.getSource()==viewDrawB.getBtnLine()){
					drawB.setCurrentAction(2);
				}
				if (e.getSource()==viewDrawB.getBtnEllipse()){
					drawB.setCurrentAction(3);
				}
				if (e.getSource()==viewDrawB.getBtnRectangle()){
					drawB.setCurrentAction(4);
				}
				if(e.getSource()==viewDrawB.getBtnColor()){
						strokeColor = JColorChooser.showDialog(null, "Pick a Stroke", Color.BLACK);
				}
				if(e.getSource()==viewDrawB.getBtnFill()){
					fillColor = JColorChooser.showDialog(null, "Pick a Fill", Color.BLACK);
				}
				if(e.getSource()==viewDrawB.getBtnRefresh()){
					drawB.getShapes().clear();
					drawB.getShapeFill().clear();
					drawB.getShapeStroke().clear();
					drawB.getTransPercent().clear();
					drawB.repaint();
					drawB.setLoad(false);
					
				}
				if(e.getSource()==viewDrawB.getBtnUndo()){
					if (drawB.getShapes().size()>1 && 
							drawB.getShapeFill().size()>1 && 
								drawB.getShapeStroke().size()>1 &&
									drawB.getTransPercent().size()>1 
								)
					{
						drawB.getShapes().remove(drawB.getShapes().size()-1);
						drawB.getShapeFill().remove(drawB.getShapeFill().size()-1);
						drawB.getShapeStroke().remove(drawB.getShapeStroke().size()-1);
						drawB.getTransPercent().remove(drawB.getTransPercent().size()-1);
					} else 
					{
						drawB.getShapes().clear();
						drawB.getShapeFill().clear();
						drawB.getShapeStroke().clear();
						drawB.getTransPercent().clear();	
					}
					drawB.repaint();
				}
				if(e.getSource()==viewDrawB.getBtnSave()){
					myFunctions.saveImage(drawB.getGraphicSettings(), drawB);
					}
				if(e.getSource()==viewDrawB.getBtnLoad()){
					 drawB.setLoad(true);
					 drawB.repaint();
					// drawB.setLoad(false);
				}
				
			}
		});

		
		
		drawB.addMyMouseActivity(new MouseAdapter() {
		      public void mousePressed(MouseEvent e){
		    	  if(drawB.getCurrentAction() != 1)
		    	  {
		    		  drawB.setDrawStart(new Point(e.getX(), e.getY()));
		    		  drawB.setDrawEnd(drawB.getDrawStart());
		    		  drawB.repaint();
		    		  //System.out.println("\nPRESSED:"+drawB.getDrawStart());
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
		    	  else if(drawB.getCurrentAction()==4)
		    	  {	    		  
                  		aShape = myFunctions.drawRectangle(drawB.getDrawStart().x,
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
